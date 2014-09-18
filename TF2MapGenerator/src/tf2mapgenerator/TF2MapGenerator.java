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

        //Let'initKill take a look at that file then
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
                skybox = new Skybox((scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt());
            } else {
                throw new Exception("\nSyntax error in skybox declaration Line " + lineCount);
            }
        } else {
            if (firstString.equalsIgnoreCase("skybox")) {
                skybox = new Skybox((scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt());
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
                    int[] intParams = new int[7];                       // *********LIKELY TO CHANGE**
                    String[] strParams = new String[2];                 // *********BEWARE CONSTANTS**

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
                        skybox.addSpire(new Spire((scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], (scale) * intParams[4], (scale) * intParams[5]));
                    } else if (strParams[0].equalsIgnoreCase("ramp")) {
                        //ramps.add(new Ramp());
                    } else if (strParams[0].equalsIgnoreCase("room")) {
                        // format: x y z xs ys zs (thickness)
                        skybox.addRoom(new Room((scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], (scale) * intParams[4], (scale) * intParams[5], (scale) * intParams[6]));
                    } else if (strParams[0].equalsIgnoreCase("-del")) {
                        skybox.getRooms().get(skybox.getRoomsSize() - 1).deleteWall(strParams[1]);  //roomsSize - 1 to get last value
                    } else if (strParams[0].equalsIgnoreCase("-door")) {
                        System.out.println("\nDoors aren't working yet\n");
                    }
                }
            }
        } catch (NumberFormatException n) {
            throw new NumberFormatException("Number Format Exception found when parsing line " + (lineCount - 1) + "\n");
        } catch (Exception e) {
            System.out.println("There is a syntax error in your input file on line " + (lineCount - 1) + "\n");
            throw new IllegalArgumentException("Improper syntax on line " + (lineCount - 1) + "\n");
        }

        //CHECK FOR EMPTY ONES AND KILLS
        int initKill = skybox.getRoomsSize();
        for (int i = 0; i < initKill; i++) {
            for (int w = 0; w < skybox.getRooms().get(i).getWalls().size(); w++) {
                //lets see if this room has any walls that need to die
                if (skybox.getRooms().get(i).getWalls().get(w).getKill()) {
                    skybox.getRooms().get(i).getWalls().remove(w);
                    w = w - 1; //ACCOUNT FOR ARRAYLIST SHIFT
                }
            }
        }
        initKill = skybox.getSpireSize();
        for (int i = 0; i < skybox.getSpireSize(); i++) {
            if (skybox.getSpires().get(i).getKill()) {
                skybox.getSpires().remove(i);
                i = i - 1; //ARRAYLIST SHIFT
            }
        }
        //end

        //ADD MIRRORED OBJECTS
        //ALG ==> [skyboxSize -(abs)|coordinate| - width] + skyboxCoord
        if (skybox.getMirrored()) {
            int initMirror = skybox.getSpireSize();
            for (int i = 0; i < initMirror; i++) {
//                skybox.addSpire(new Spire((skybox.getX()) + (skybox.getXSize() - (skybox.getSpires().get(i).getX() + skybox.getSpires().get(i).getXs())), (skybox.getY()) + (skybox.getYSize() - (skybox.getSpires().get(i).getY() + skybox.getSpires().get(i).getYs())), skybox.getSpires().get(i).getZ(), skybox.getSpires().get(i).getXs(), skybox.getSpires().get(i).getYs(), skybox.getSpires().get(i).getZs()));
                skybox.addSpire(skybox.getSpires().get(i).getMirror(skybox.getX() + skybox.getXSize(), skybox.getY() + skybox.getYSize()));
            }
            initMirror = skybox.getRoomsSize();
            for (int i = 0; i < initMirror; i++) {
//                skybox.addRoom(new Room((skybox.getX()) + (skybox.getXSize() - (skybox.getRooms().get(i).getX() + skybox.getRooms().get(i).getXs())), (skybox.getY()) + (skybox.getYSize() - (skybox.getRooms().get(i).getY() + skybox.getRooms().get(i).getYs())), skybox.getRooms().get(i).getZ(), skybox.getRooms().get(i).getXs(), skybox.getRooms().get(i).getYs(), skybox.getRooms().get(i).getZs(), skybox.getRooms().get(i).getDw()));
                skybox.addRoom(new Room(skybox.getRooms().get(i).getMirroredRoom(skybox.getX() + skybox.getXSize(), skybox.getY() + skybox.getYSize())));
            }
        }

        //BEGIN WRITE
        int id = 1;         //PENDING INVESTIGATION (not sure if these even matter...)
        try {
            writer.print(skybox.getOutput(id));
            id = 44;  //number of skybox ids + 1
            for (int i = 0; i < skybox.getSpireSize(); i++) {
                writer.print(skybox.getSpires().get(i).getOutput(id));
                id = id + 6;
            }
//            for (int i = 0; i < ramps.size(); i++) {
//                writer.print(skybox.getRamps.get(i).getOutput(id));
//                id++;
//            }
            for (int r = 0; r < skybox.getRoomsSize(); r++) {
                writer.print(skybox.getRooms().get(r).getOutput(id));
                id = id + 6;
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
