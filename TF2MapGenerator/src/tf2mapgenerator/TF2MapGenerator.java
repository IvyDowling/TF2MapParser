package tf2mapgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TF2MapGenerator {

    private static String loadPath;
    private static String generatedFilename;

    private static Scanner reader;
    private static PrintWriter writer;

    private static ArrayList<Spire> spires = new ArrayList<>();
    private static ArrayList<Ramp> ramps = new ArrayList<>();

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
        int xs = reader.nextInt();
        int ys = reader.nextInt();
        int zs = reader.nextInt();
        Point point = new Point(xs, ys, zs);
        //
        int lineCount = 0;
        try {
            while (reader.hasNext()) {
                lineCount++;
                String holder = reader.nextLine();
                if (holder.trim().equals("")) {
                    //skip the whitespace
                } else if (holder.charAt(0) == '#') {
                    //skip the line because of comment
                } else {
                    Scanner lineBreaker = new Scanner(holder);
                    if (lineBreaker.next().equalsIgnoreCase("spire")) {
                        //format is xcoord, ycoord, zcoord, xsize, ysize, zsize
                        spires.add(new Spire(lineBreaker.nextInt(), lineBreaker.nextInt(), lineBreaker.nextInt(), lineBreaker.nextInt(), lineBreaker.nextInt(), lineBreaker.nextInt()));
                    } else if (lineBreaker.next().equalsIgnoreCase("ramp")) {
                        ramps.add(new Ramp(lineBreaker.nextInt(), lineBreaker.nextInt(), lineBreaker.nextInt(), lineBreaker.nextInt(), lineBreaker.nextInt(), lineBreaker.nextInt(), lineBreaker.next()));
                    } else if (lineBreaker.next().equalsIgnoreCase("mirror")) {
                        point.setMirror(lineBreaker.hasNextBoolean());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("There is a syntax error in your input file on line " + lineCount);
            throw new IllegalArgumentException("Improper syntax on line " + lineCount);
        }

        //ADD MIRRORED OBJECTS
        //ALG ==> [((mapSize)-(coordinate))-width]
        if (point.getMirrored()) {
            for (int i = 0; i < spires.size(); i++) {
                spires.add(new Spire((point.getXSize() - spires.get(i).getX() - spires.get(i).getXs()), (point.getYSize() - spires.get(i).getY() - spires.get(i).getYs()), spires.get(i).getZ(), spires.get(i).getXs(), spires.get(i).getYs(), spires.get(i).getZs()));
            }
        }

        //
        //BEGIN WRITE
        writer.print(point.toString());
        int id = 36;
        for (int i = 0; i < spires.size(); i++) {
            writer.print(spires.get(i).toString(id));
            id++;
        }
        for (int i = 0; i < ramps.size(); i++) {
            writer.print(ramps.get(i).toString(id));
            id++;
        }
        writer.print("}\n"
                + "cameras\n"
                + "{\n"
                + "	\"activecamera\" \"-1\"\n"
                + "}\n");
        writer.close();
    }
}
