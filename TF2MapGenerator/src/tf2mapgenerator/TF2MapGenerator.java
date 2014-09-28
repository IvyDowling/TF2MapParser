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
            throw new FileNotFoundException("\nNo file found at " + loadPath + "\n" + e);
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
                    throw new IOException("\nCoudn't make the " + generatedFilename + "file\n" + io);
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
                throw new Exception("\nSyntax error in skybox declaration on line " + lineCount + "\n");
            }
        } else {
            if (firstString.equalsIgnoreCase("skybox")) {
                skybox = new Skybox((scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt());
            } else {
                throw new Exception("\nSyntax error in skybox declaration Line " + lineCount + "\n");
            }
        }
        lineCount++;
        //
        try {
            while (reader.hasNext()) {
                lineCount++;
                String holder = reader.nextLine();
                holder = holder.trim();
                if (holder.equals("") || holder.charAt(0) == '#') {
                    //skip the whitespace
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
                            throw new NumberFormatException("Number Format Exception while parsing ints " + lineCount + "\n" + n);
                        }
                    }

                    if (strParams[0].equalsIgnoreCase("spire") || strParams[0].equalsIgnoreCase("walkway")) {
                        //format is xcoord, ycoord, zcoord, xsize, ysize, zsize
                        skybox.addSpire(new Spire((scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], (scale) * intParams[4], (scale) * intParams[5]));
                    } else if (strParams[0].equalsIgnoreCase("ramp")) {
                        skybox.getRamps().add(new Ramp(strParams[1], (scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], (scale) * intParams[4], (scale) * intParams[5]));
                    } else if (strParams[0].equalsIgnoreCase("room")) {
                        // format: x y z xs ys zs (thickness)
                        skybox.addRoom(new Room((scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], (scale) * intParams[4], (scale) * intParams[5], (scale) * intParams[6]));
                    } else if (strParams[0].equalsIgnoreCase("-del")) {
                        skybox.getRooms().get(skybox.getRoomsSize() - 1).deleteWall(strParams[1]);  //roomsSize - 1 to get last value
                    } else if (strParams[0].equalsIgnoreCase("-port") || strParams[0].equalsIgnoreCase("-door")) {
                        skybox.getRooms().get(skybox.getRoomsSize() - 1).addDoor(strParams[1], (scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3]);  //roomsSize - 1 to get last value
                    } else if (strParams[0].equalsIgnoreCase("barr")) {
                        skybox.addSpire(new Spire((scale) * intParams[0], (scale) * intParams[1], skybox.getZ(), (scale) * intParams[2], (scale) * intParams[3], skybox.getZSize()));
                    } else if (strParams[0].equalsIgnoreCase("-wall")) {
                        skybox.getRooms().get(skybox.getRoomsSize() - 1).addInteriorWall(strParams[1], (scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2]);
                    } else if (strParams[0].equalsIgnoreCase("incl")) {
                        skybox.addIncline(new Incline(strParams[1], (scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], (scale) * intParams[4], (scale) * intParams[5], (scale) * intParams[6]));
                    } else if (strParams[0].equalsIgnoreCase("respawn")) {
                        skybox.addRespawn(new Respawn((scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3]));
                    }
                }
            }
        } catch (NumberFormatException n) {
            throw new NumberFormatException("Number Format Exception found when parsing line " + (lineCount - 1) + "\n" + n);
        } catch (Exception e) {
            throw new IllegalArgumentException("There is a syntax error in your input file on line " + (lineCount - 1) + "\n" + e);
        }

        //CHECK FOR EMPTY, KILLS, and DOORS
        int size = skybox.getRoomsSize();
        for (int i = 0; i < size; i++) {
            // Once per room
            skybox.getRooms().get(i).cutOutDoors();
            //before delete
            for (int w = 0; w < skybox.getRooms().get(i).getExteriorWalls().length; w++) {
                //lets see if this room has any walls that need to die
                if (skybox.getRooms().get(i).getExteriorWalls()[w].getKill()) {
                    skybox.getRooms().get(i).getExteriorWalls()[w] = new Spire(0, 0, 0, 0, 0, 0);
                }
            }
        }
        size = skybox.getSpireSize();
        for (int i = 0; i < size; i++) {
            if (skybox.getSpires().get(i).getKill()) {
                skybox.getSpires().remove(i);
            }
        }
        //end

        //ADD MIRRORED OBJECTS
        //ALG ==> [skyboxSize -(abs)|coordinate| - width] + skyboxCoord
        if (skybox.getMirrored()) {
            int initMirror = skybox.getSpireSize();
            for (int i = 0; i < initMirror; i++) {
//                skybox.addSpire(new Spire((skybox.getX()) + (skybox.getXSize() - (skybox.getSpires().get(i).getX() + skybox.getSpires().get(i).getXs())), (skybox.getY()) + (skybox.getYSize() - (skybox.getSpires().get(i).getY() + skybox.getSpires().get(i).getYs())), skybox.getSpires().get(i).getZ(), skybox.getSpires().get(i).getXs(), skybox.getSpires().get(i).getYs(), skybox.getSpires().get(i).getZs()));
                skybox.addSpire(skybox.getSpires().get(i).getMirror(skybox.getX(), skybox.getY(), skybox.getX() + skybox.getXSize(), skybox.getY() + skybox.getYSize()));
            }
            initMirror = skybox.getRoomsSize();
            for (int i = 0; i < initMirror; i++) {
                //get the mirrored walls, and the mirrored walls from any doors
                skybox.addRoom(new Room(skybox.getRooms().get(i).getMirroredRoom(skybox.getX(), skybox.getY(), skybox.getX() + skybox.getXSize(), skybox.getY() + skybox.getYSize()), skybox.getRooms().get(i).getMirroredDoor(skybox.getX(), skybox.getY(), skybox.getX() + skybox.getXSize(), skybox.getY() + skybox.getYSize()), skybox.getRooms().get(i).getMirroredInterior(skybox.getX(), skybox.getY(), skybox.getX() + skybox.getXSize(), skybox.getY() + skybox.getYSize())));
            }
            initMirror = skybox.getInclinesSize();
            for (int i = 0; i < initMirror; i++) {
                skybox.addIncline(skybox.getInclines().get(i).getMirror(skybox.getX(), skybox.getY(), skybox.getX() + skybox.getXSize(), skybox.getY() + skybox.getYSize()));
            }
            initMirror = skybox.getRampSize();
            for (int i = 0; i < initMirror; i++) {
                skybox.addRamp(skybox.getRamps().get(i).getMirroredRamp(skybox.getX(), skybox.getY(), skybox.getX() + skybox.getXSize(), skybox.getY() + skybox.getYSize()));
            }
            initMirror = skybox.getRespawnSize();
            for (int i = 0; i < initMirror; i++) {
                skybox.addRespawn(skybox.getRespawns().get(i).getMirroredRespawn(skybox.getX(), skybox.getY(), skybox.getX() + skybox.getXSize(), skybox.getY() + skybox.getYSize()));
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
            for (int i = 0; i < skybox.getRampSize(); i++) {
                writer.print(skybox.getRamps().get(i).getOutput(id));
                id = id + 5;
            }
            for (int r = 0; r < skybox.getInclinesSize(); r++) {
                writer.print(skybox.getInclines().get(r).getOutput(id));
                id = id + 6;
            }
            for (int r = 0; r < skybox.getRoomsSize(); r++) {
                writer.print(skybox.getRooms().get(r).getOutput(id));
                id = id + 6;
            }

            //END WORLD WRITE
            writer.print("}\n"
                    + "cameras\n"
                    + "{\n"
                    + "	\"activecamera\" \"-1\"\n"
                    + "}\n");

            //
            //ENTITY CREATION
            //
            //ROOM LIGHTS LOOP
            if (skybox.getMirrored()) {
                for (int r = 0; r < skybox.getRoomsSize(); r++) {
                    writer.print(skybox.getRooms().get(r).getLight());
                    writer.print(skybox.getRooms().get(r).getMirroredLight(skybox.getX(), skybox.getY(), skybox.getX() + skybox.getXSize(), skybox.getY() + skybox.getYSize()));
                }
                for (int r = 0; r < skybox.getRespawnSize(); r++) {
                   writer.print(skybox.getRespawns().get(r).getOutput());
                }
            } else {
                for (int r = 0; r < skybox.getRoomsSize(); r++) {
                    writer.print(skybox.getRooms().get(r).getLight());
                }
            }
            //This last write is just for single instance environmental entities
            writer.print("entity\n"
                    + "{\n"
                    + "\"id\" \"256\"\n"
                    + "\"classname\" \"light_environment\"\n"
                    + "\"_ambient\" \"156 178 255 250\"\n"
                    + "\"_ambientHDR\" \"-1 -1 -1 1\"\n"
                    + "\"_AmbientScaleHDR\" \"1\"\n"
                    + "\"_light\" \"244 215 193 750\"\n"
                    + "\"_lightHDR\" \"-1 -1 -1 1\"\n"
                    + "\"_lightscaleHDR\" \"1\"\n"
                    + "\"angles\" \"0 51 0\"\n"
                    + "\"pitch\" \"-37\"\n"
                    + "\"SunSpreadAngle\" \".5\"\n"
                    + "\"origin\" \"0 0 256\"\n"
                    + "editor\n"
                    + "{\n"
                    + "\"color\" \"220 30 220\"\n"
                    + "\"visgroupshown\" \"1\"\n"
                    + "\"visgroupautoshown\" \"1\"\n"
                    + "\"logicalpos\" \"[5500 12000]\"\n"
                    + "}\n"
                    + "}"
                    + "entity\n"
                    + "{\n"
                    + "	\"id\" \"16\"\n"
                    + "	\"classname\" \"env_cubemap\"\n"
                    + "	\"sides\" \"\"\n"
                    + "	\"origin\" \"0 0 128\"\n"
                    + "	editor\n"
                    + "	{\n"
                    + "		\"color\" \"230 127 0\"\n"
                    + "		\"groupid\" \"14\"\n"
                    + "		\"visgroupshown\" \"1\"\n"
                    + "		\"visgroupautoshown\" \"1\"\n"
                    + "		\"logicalpos\" \"[0 6000]\"\n"
                    + "	}\n"
                    + "}\n"
                    + "entity\n"
                    + "{\n"
                    + "	\"id\" \"2\"\n"
                    + "	\"classname\" \"shadow_control\"\n"
                    + "	\"angles\" \"80 145 0\"\n"
                    + "	\"color\" \"151 152 170\"\n"
                    + "	\"distance\" \"75\"\n"
                    + "	\"origin\" \"0 0 160\"\n"
                    + "	editor\n"
                    + "	{\n"
                    + "		\"color\" \"220 30 220\"\n"
                    + "		\"visgroupshown\" \"1\"\n"
                    + "		\"visgroupautoshown\" \"1\"\n"
                    + "		\"logicalpos\" \"[0 -16268]\"\n"
                    + "	}\n"
                    + "}\n"
                    + "entity\n"
                    + "{\n"
                    + "	\"id\" \"4\"\n"
                    + "	\"classname\" \"env_fog_controller\"\n"
                    + "	\"angles\" \"0 0 0\"\n"
                    + "	\"farz\" \"3700\"\n"
                    + "	\"fogblend\" \"0\"\n"
                    + "	\"fogcolor\" \"130 139 170\"\n"
                    + "	\"fogcolor2\" \"255 255 255\"\n"
                    + "	\"fogdir\" \"1 0 0\"\n"
                    + "	\"fogenable\" \"1\"\n"
                    + "	\"fogend\" \"8000\"\n"
                    + "	\"foglerptime\" \"1.5\"\n"
                    + "	\"fogmaxdensity\" \".9\"\n"
                    + "	\"fogstart\" \"100\"\n"
                    + "	\"maxdxlevel\" \"0\"\n"
                    + "	\"mindxlevel\" \"0\"\n"
                    + "	\"spawnflags\" \"1\"\n"
                    + "	\"targetname\" \"fog_controller\"\n"
                    + "	\"use_angles\" \"0\"\n"
                    + "	\"origin\" \"0 0 64\"\n"
                    + "	editor\n"
                    + "	{\n"
                    + "		\"color\" \"255 255 255\"\n"
                    + "		\"visgroupshown\" \"1\"\n"
                    + "		\"visgroupautoshown\" \"1\"\n"
                    + "		\"logicalpos\" \"[0 -15768]\"\n"
                    + "	}\n"
                    + "}\n"
                    + "entity\n"
                    + "{\n"
                    + "	\"id\" \"6\"\n"
                    + "	\"classname\" \"color_correction\"\n"
                    + "	\"fadeInDuration\" \"0.0\"\n"
                    + "	\"fadeOutDuration\" \"0.0\"\n"
                    + "	\"filename\" \"scripts/2fort_global.raw\"\n"
                    + "	\"maxfalloff\" \"-1\"\n"
                    + "	\"maxweight\" \"1.0\"\n"
                    + "	\"minfalloff\" \"-1\"\n"
                    + "	\"targetname\" \"color_global\"\n"
                    + "	\"origin\" \"0 0 32\"\n"
                    + "	editor\n"
                    + "	{\n"
                    + "		\"color\" \"220 30 220\"\n"
                    + "		\"visgroupshown\" \"1\"\n"
                    + "		\"visgroupautoshown\" \"1\"\n"
                    + "		\"logicalpos\" \"[0 -13268]\"\n"
                    + "	}\n"
                    + "}\n"
                    + "entity\n"
                    + "{\n"
                    + "	\"id\" \"10\"\n"
                    + "	\"classname\" \"env_tonemap_controller\"\n"
                    + "	\"targetname\" \"tonemap_global\"\n"
                    + "	\"origin\" \"0 0 278\"\n"
                    + "	editor\n"
                    + "	{\n"
                    + "		\"color\" \"220 30 220\"\n"
                    + "		\"visgroupshown\" \"1\"\n"
                    + "		\"visgroupautoshown\" \"1\"\n"
                    + "		\"logicalpos\" \"[0 -14268]\"\n"
                    + "	}\n"
                    + "}");
            writer.close();
        } catch (NullPointerException ioexc) {
            throw new IOException("There was an error found during the write-to-file for the file named " + generatedFilename + "\nCheck file for syntax errors. " + ioexc);
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
