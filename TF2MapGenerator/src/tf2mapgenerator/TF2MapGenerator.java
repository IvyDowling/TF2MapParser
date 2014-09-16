package tf2mapgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TF2MapGenerator {

    private static String loadPath;
    private static String generatedFilename;

    private static Scanner reader;
    private static PrintWriter writer;

    private static ArrayList<Spire> spires = new ArrayList<>();
    private static ArrayList<Ramp> ramps = new ArrayList<>();
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Skybox> skyboxes = new ArrayList<>();
    private static int scale = 1;

    public static void main(String[] args) throws Exception {

        // argument checking
        if (args.length > 0) {
            if (args[0].equals("-h")) {
                System.out.println("**HELP MENU**");
                System.out.println("tf2mapgen <filename>");
                System.out.println("output is saved as tf2mapgenfile.vmf");
                System.out.println("For use, see README");
                throw new Exception("Help menu exit");
            } else if (args.length > 2 && args[1].equals("-o")) {
                loadPath = args[0];
                generatedFilename = args[2];
            } else {
                loadPath = args[0];
                generatedFilename = "tf2mapgenfile";
            }
        } else {
            System.out.println("No build-file given, -h for help.");
            throw new Exception("No build-file given.");
        }

        //Let's take a look at that file then
        try {
            reader = new Scanner(new File(loadPath));
        } catch (FileNotFoundException e) {
            System.out.println("No file found at " + loadPath);
            throw new FileNotFoundException("Missing file or wrong file-name");
        }

        boolean savingFlag = true;
        int reiterations = 1;
        while (savingFlag) {
            File f = new File(generatedFilename + ".vmf");
            if (f.exists() && !f.isDirectory()) {
                if (reiterations == 1) {
                    generatedFilename = generatedFilename + "(" + reiterations + ")";
                } else {
                    generatedFilename = generatedFilename.substring(0, generatedFilename.length() - 3) + "(" + reiterations + ")";
                }
                reiterations++;
            } else {
                try {
                    writer = new PrintWriter(generatedFilename + ".vmf", "UTF-8");
                } catch (IOException io) {
                    System.out.println("Coudn't make the " + generatedFilename + "file");
                    throw new IOException("Coudn't make the " + generatedFilename + "file");
                }
                savingFlag = false;
            }
        }

        //
        //MAIN SCANNING LOOP
        //
        //FIRST LINE CHECK GOES HERE
        int lineCount = 0;
        Skybox skybox;
        String firstString = reader.next();
        if (firstString.equalsIgnoreCase("scale")) {
            scale = reader.nextInt();
            lineCount++;
            if (reader.next().equalsIgnoreCase("skybox")) {
                skybox = new Skybox((scale)*reader.nextInt(), (scale)*reader.nextInt(), (scale)*reader.nextInt(), (scale)*reader.nextInt(), (scale)*reader.nextInt(), (scale)*reader.nextInt());
            } else {
                throw new Exception("\nSyntax error in skybox declaration Line " + lineCount);
            }
        } else {
            if (firstString.equalsIgnoreCase("skybox")) {
                skybox = new Skybox((scale)*reader.nextInt(), (scale)*reader.nextInt(), (scale)*reader.nextInt(), (scale)*reader.nextInt(), (scale)*reader.nextInt(), (scale)*reader.nextInt());
            } else {
                throw new Exception("\nSyntax error in skybox declaration Line " + lineCount);
            }
        }
        lineCount++;
        //
        try {
            while (reader.hasNext()) {
                lineCount++;
                String holder = reader.nextLine();
                holder = holder.trim();
                if (holder.equals("")) {
                    //skip the whitespace
                } else if (holder.charAt(0) == '#') {
                    //skip the line because of comment
                } else if (holder.equalsIgnoreCase("mirror")) {
                    skybox.setMirror(true);
                } else {
                    Scanner lineBreaker = new Scanner(holder);
                    int[] intParams = new int[7];               // *********LIKELY TO CHANGE**
                    String[] strParams = new String[2];         // *********BEWARE CONSTANTS**

                    //so lets read all of this in
                    //and save the ints as ints and the strings as strings
                    int intCount = 0;
                    int strCount = 1;
                    strParams[0] = lineBreaker.next();  //COMMAND NAME ALWAYS GOES FIRST
                    while (lineBreaker.hasNext()) {
                        try {
                            String holdTheWord = lineBreaker.next();
                            if (canBeInt(holdTheWord)) { // see static method at bottom
                                intParams[intCount] = Integer.parseInt(holdTheWord);
                                intCount++;
                            } else {
                                strParams[strCount] = holdTheWord;
                                strCount++;
                            }
                        } catch (NumberFormatException n) {
                            System.out.println("Number Format Exception while parsing ints " + lineCount);
                            throw n;
                        }
                    }

                    if (strParams[0].equalsIgnoreCase("spire")) {
                        //format is xcoord, ycoord, zcoord, xsize, ysize, zsize
                        spires.add(new Spire((scale)*intParams[0], (scale)*intParams[1], (scale)*intParams[2], (scale)*intParams[3], (scale)*intParams[4], (scale)*intParams[5]));
                    } else if (strParams[0].equalsIgnoreCase("ramp")) {
                        //ramps.add(new Ramp());
                    } else if (strParams[0].equalsIgnoreCase("room")) {
                        // format: x y z xs ys zs (thickness)
                        rooms.add(new Room((scale)*intParams[0], (scale)*intParams[1], (scale)*intParams[2], (scale)*intParams[3], (scale)*intParams[4], (scale)*intParams[5], (scale)*intParams[6]));
                    }
                }
            }
        } catch (NumberFormatException n) {
            throw new NumberFormatException("Number Format Exception found when parsing line " + lineCount);
        } catch (Exception e) {
            System.out.println("There is a syntax error in your input file on line " + lineCount);
            throw new IllegalArgumentException("Improper syntax on line " + lineCount);
        }

        //ADD MIRRORED OBJECTS
        //ALG ==> [skyboxSize -(abs)|coordinate| - width] + skyboxCoord
        if (skybox.getMirrored()) {
            int s = spires.size();
            for (int i = 0; i < s; i++) {
                spires.add(new Spire((skybox.getX()) + (skybox.getXSize() - (spires.get(i).getX() + spires.get(i).getXs())), (skybox.getY()) + (skybox.getYSize() - (spires.get(i).getY() + spires.get(i).getYs())), spires.get(i).getZ(), spires.get(i).getXs(), spires.get(i).getYs(), spires.get(i).getZs()));
            }
            s = rooms.size();
            for (int i = 0; i < s; i++) {
                rooms.add(new Room((skybox.getX()) + (skybox.getXSize() - (rooms.get(i).getX() + rooms.get(i).getXs())), (skybox.getY()) + (skybox.getYSize() - (rooms.get(i).getY() + rooms.get(i).getYs())), rooms.get(i).getZ(), rooms.get(i).getXs(), rooms.get(i).getYs(), rooms.get(i).getZs(), rooms.get(i).getDw()));
            }
        }

        //
        //BEGIN WRITE
        int id = 0;
        try {
            writer.print(skybox.getOutput(id));
            for (int i = 0; i < spires.size(); i++) {
                writer.print(spires.get(i).getOutput(id));
                id++;
            }
//            for (int i = 0; i < ramps.size(); i++) {
//                writer.print(ramps.get(i).getOutput(id));
//                id++;
//            }
            for (int r = 0; r < rooms.size(); r++) {
                writer.print(rooms.get(r).getOutput(id));
                id++;
            }
            writer.print("}\n"
                    + "cameras\n"
                    + "{\n"
                    + "	\"activecamera\" \"-1\"\n"
                    + "}\n");
            writer.close();
        } catch (Exception ee) {
            System.out.println("There was an error found during the write-to-file for the file named " + generatedFilename);
            throw new IOException("Error while writing to file on id " + id);
        }
        System.out.println("\nProcess Complete!");
    }

    public static boolean canBeInt(String str) {
        //hate this method
        int letsSee;
        Scanner testInt = new Scanner(str);
        try {
            letsSee = testInt.nextInt();
            return letsSee >= 0 || letsSee < 0;
        } catch (InputMismatchException imex) {
            return false;
        }
    }
}
