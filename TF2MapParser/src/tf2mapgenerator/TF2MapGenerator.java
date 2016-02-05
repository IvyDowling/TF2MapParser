package tf2mapgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TF2MapGenerator {

    private static String loadPath;
    private static String generatedFilename;

    private static Scanner reader;
    private static PrintWriter writer;

    private static List<Frame> frames = new ArrayList<>();
    private static List<SpawnRoom> spawns = new ArrayList<>();
    private static int mapCenter = -1;
    private static boolean is5cp = false;
    private static int scale = 1;

    public static void main(String[] args) throws Exception {
        //arguments
        input(args);
        //makes base file
        createVMF();

        //
        //MAIN SCANNING LOOP
        //
        String firstString = reader.next().trim();
        if (firstString.equalsIgnoreCase("5cp")) {
            make5cp();
        } else if (firstString.equalsIgnoreCase("mid")) {
            makeMid();
        } else if (firstString.equalsIgnoreCase("jump")) {
            makeJump();
        } else {
            throw new Exception("\nSyntax error: map type not specified on line 1\n");
        }
        //f is the frame we are working in. When a new frame/skybox is declared, f++;
        int frame = 0;
        try {
            while (reader.hasNext()) {
                String holder = reader.nextLine();
                holder = holder.trim();
                if (holder.equals("") || holder.charAt(0) == '#') {
                    //skip the whitespace
                } else {
                    Scanner lineBreaker = new Scanner(holder);
                    int[] intParams = new int[10];                       // *********LIKELY TO CHANGE**
                    String[] strParams = new String[10];                 // *********BEWARE CONSTANTS**

                    //so lets read all of this in
                    //and save the ints as ints and the strings as strings
                    int intCount = 0;
                    int strCount = 1;
                    strParams[0] = lineBreaker.next().trim();  //COMMAND NAME ALWAYS GOES FIRST
                    //this while loop breaks up the line and saves each input as an int or string
                    while (lineBreaker.hasNext()) {
                        try {
                            String holdTheWord = lineBreaker.next();
                            if (canBeInt(holdTheWord)) { // see static method at bottom
                                intParams[intCount] = Integer.parseInt(holdTheWord);
                                intCount++;
                            } else {
                                strParams[strCount] = holdTheWord.trim();
                                strCount++;
                            }
                        } catch (NumberFormatException n) {
                            throw new NumberFormatException("Number Format Exception while parsing ints\n" + n);
                        }
                    }

                    if (strParams[0].equalsIgnoreCase("spire") || strParams[0].equalsIgnoreCase("walkway")) {
                        //format is xcoord, ycoord, zcoord, xsize, ysize, zsize
                        frames.get(frame).addSpire(new Spire((scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], (scale) * intParams[4], (scale) * intParams[5]));
                    } else if (strParams[0].equalsIgnoreCase("ramp")) {
                        frames.get(frame).getRamps().add(new Ramp(strParams[1], (scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], (scale) * intParams[4], (scale) * intParams[5]));
                    } else if (strParams[0].equalsIgnoreCase("room")) {
                        // format: x y z xs ys zs (thickness)
                        frames.get(frame).addRoom(new Room((scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], (scale) * intParams[4], (scale) * intParams[5], (scale) * intParams[6]));
                    } else if (strParams[0].equalsIgnoreCase("-del")) {
                        frames.get(frame).getRooms().get(frames.get(frame).getRoomsSize() - 1).deleteWall(strParams[1]);  //roomsSize - 1 to get last value
                    } else if (strParams[0].equalsIgnoreCase("-port") || strParams[0].equalsIgnoreCase("-door")) {
                        frames.get(frame).getRooms().get(frames.get(frame).getRoomsSize() - 1).addDoor(strParams[1], (scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3]);  //roomsSize - 1 to get last value
                    } else if (strParams[0].equalsIgnoreCase("barr")) {
                        frames.get(frame).addSpire(new Spire((scale) * intParams[0], (scale) * intParams[1], frames.get(frame).getZ(), (scale) * intParams[2], (scale) * intParams[3], frames.get(frame).getZSize()));
                    } else if (strParams[0].equalsIgnoreCase("-wall")) {
                        frames.get(frame).getRooms().get(frames.get(frame).getRoomsSize() - 1).addInteriorWall(strParams[1], (scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2]);
                    } else if (strParams[0].equalsIgnoreCase("incl")) {
                        frames.get(frame).addIncline(new Incline(strParams[1], (scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], (scale) * intParams[4], (scale) * intParams[5], (scale) * intParams[6]));
                    } else if (strParams[0].equalsIgnoreCase("entity")) {
                        frames.get(frame).addEntity(new Entity((scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], strParams[1]));
                    } else if (strParams[0].equalsIgnoreCase("pl-clip")) {
                        //player clip
                        frames.get(frame).addSpire(new PlayerClip((scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], (scale) * intParams[4], (scale) * intParams[5]));
                    } else if (strParams[0].equalsIgnoreCase("skybox")) {
                        frames.add(new Skybox((scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], (scale) * intParams[4], (scale) * intParams[5]));
                        frame = frame + 1;    //We're now working in a new frame/skybox now
                    } else if (strParams[0].equalsIgnoreCase("frame")) {
                        frames.add(new Frame((scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], (scale) * intParams[4], (scale) * intParams[5]));
                        frame = frame + 1;    //We're now working in a new frame/skybox now
                    } else if (strParams[0].equalsIgnoreCase("conn")) {
                        if (strParams[1].equalsIgnoreCase("frame")) {
                            frames.add(new Frame((scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], (scale) * intParams[4], (scale) * intParams[5]));
                            frames.get(frames.size() - 1).setConnector();
                            frame = frame + 1;
                        } else if (strParams[1].equalsIgnoreCase("skybox")) {
                            frames.add(new Skybox((scale) * intParams[0], (scale) * intParams[1], (scale) * intParams[2], (scale) * intParams[3], (scale) * intParams[4], (scale) * intParams[5]));
                            frames.get(frames.size() - 1).setConnector();
                            frame = frame + 1;
                        } else {
//                            connectors.add(new Connector(frames.get(intParams[intParams[0]]), strParams[1], (scale) * intParams[1], (scale) * intParams[2], frames.get(intParams[3]), strParams[2], (scale) * intParams[4], (scale) * intParams[5]));
                        }
                    } else if (strParams[0].equalsIgnoreCase("mirror")) {
                        frames.get(frame).setMirror();
                    } else if (strParams[0].equalsIgnoreCase("map-center")) {
                        //The skybox-fr we are in is the mid
                        mapCenter = frame;
                        frames.get(frame).setMirror();
                    } else {
                        throw new Exception();
                    }
                }
            }
        } catch (NumberFormatException n) {
            throw new NumberFormatException("Number Format Exception found\n" + n);
        } catch (Exception e) {
            throw new Exception("There is a syntax error in your input file\n" + e);
        }

        for (int fr = 0; fr < frames.size(); fr++) {
            //CHECK FOR ROOM: EMPTY, KILLS, and DOORS
            int size = frames.get(fr).getRoomsSize();
            for (int i = 0; i < size; i++) {
                // Once per room
                frames.get(fr).getRooms().get(i).cutOutDoors();
                //before delete
                for (int w = 0; w < frames.get(fr).getRooms().get(i).getExteriorWalls().length; w++) {
                    //lets see if this room has any walls that need to die
                    if (frames.get(fr).getRooms().get(i).getExteriorWalls()[w].getKill()) {
                        frames.get(fr).getRooms().get(i).getExteriorWalls()[w] = new Spire(0, 0, 0, 0, 0, 0);
                    }
                }
            }

        }

        // 5 CP SKYBOX MIRRORING
        if (is5cp) {
            mirror();
        }

        //FRAME-CONNECTOR DOORS
        for (int i = 0; i < frames.size(); i++) {
            for (int e = 0; e < frames.size(); e++) {
                if (i == e) {
                    //Then we would be checking if the current frame is intersecting itself.
                } else {
                    frames.get(i).cutOutDoors(frames.get(e).getX(), frames.get(e).getY(), frames.get(e).getZ(), frames.get(e).getXSize(), frames.get(e).getYSize(), frames.get(e).getZSize());
                }
            }
        }
        for (int fr = 0; fr < frames.size(); fr++) {
            for (int i = 0; i < frames.get(fr).getFrameWallsSize(); i++) {
                if (frames.get(fr).getFrameWalls().get(i).getKill()) {
                    frames.get(fr).getFrameWalls().remove(i);
                    i = i - 1;
                }
            }
        }

        //BEGIN WRITE
        write();
        System.out.println("\nProcess Complete!");
    }

    private static void write() throws IOException {
        int id = 1;         //The ids don't really matter as far as I've seen
        try {
            writer.print("versioninfo\n"
                    + "{\n"
                    + "	\"editorversion\" \"400\"\n"
                    + "	\"editorbuild\" \"6488\"\n"
                    + "	\"mapversion\" \"1\"\n"
                    + "	\"formatversion\" \"100\"\n"
                    + "	\"prefab\" \"0\"\n"
                    + "}\n"
                    + "visgroups\n"
                    + "{\n"
                    + "}\n"
                    + "viewsettings\n"
                    + "{\n"
                    + "	\"bSnapToGrid\" \"1\"\n"
                    + "	\"bShowGrid\" \"1\"\n"
                    + "	\"bShowLogicalGrid\" \"0\"\n"
                    + "	\"nGridSpacing\" \"128\"\n"
                    + "	\"bShow3DGrid\" \"0\"\n"
                    + "}\n"
                    + "world\n"
                    + "{\n"
                    + "	\"id\" \"" + id + "\"\n"
                    + "	\"mapversion\" \"1\"\n"
                    + "	\"classname\" \"worldspawn\"\n"
                    + "	\"skyname\" \"sky_trainyard_01\"\n"
                    + "	\"maxpropscreenwidth\" \"-1\"\n"
                    + "	\"detailvbsp\" \"detail.vbsp\"\n"
                    + "	\"detailmaterial\" \"detail/detailsprites\"\n");

            //
            // Main write area for each skybox
            //
            for (int fr = 0; fr < frames.size(); fr++) {
                writer.print(frames.get(fr).getOutput(id));
                id = 44;  //number of skybox ids + 1
                for (int i = 0; i < frames.get(fr).getSpireSize(); i++) {
                    writer.print(frames.get(fr).getSpires().get(i).getOutput(id));
                    id = id + 6;
                }
                for (int i = 0; i < frames.get(fr).getRampSize(); i++) {
                    writer.print(frames.get(fr).getRamps().get(i).getOutput(id));
                    id = id + 5;
                }
                for (int r = 0; r < frames.get(fr).getInclinesSize(); r++) {
                    writer.print(frames.get(fr).getInclines().get(r).getOutput(id));
                    id = id + 6;
                }
                for (int r = 0; r < frames.get(fr).getRoomsSize(); r++) {
                    writer.print(frames.get(fr).getRooms().get(r).getOutput(id));
                    id = id + 6;
                }
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
            for (int fr = 0; fr < frames.size(); fr++) {
                for (int r = 0; r < frames.get(fr).getRoomsSize(); r++) {
                    writer.print(frames.get(fr).getRooms().get(r).getLightOutput());
                }
            }
            //Frame Lights Loop
            for (int fr = 0; fr < frames.size(); fr++) {
                for (int r = 0; r < frames.get(fr).getRoomsSize(); r++) {
                    writer.print(frames.get(fr).getLightOutput());
                }
            }
            for (int fr = 0; fr < frames.size(); fr++) {
                for (int e = 0; e < frames.get(fr).getEntitiesSize(); e++) {
                    writer.print(frames.get(fr).getEntities().get(e).getOutput());
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
    }

    private static void mirror() throws Exception {
        int initialSize = frames.size();
        if (mapCenter == -1) {
            throw new Exception("map-center was never declared.");
        }
        for (int fr = 0; fr < initialSize; fr++) {
            if (fr != mapCenter) {
                //This loop should be where detailing of red-blue sides should go
                //make a new skybox rotated around the mapCenter skybox.
                int newX = frames.get(mapCenter).getX() + (frames.get(mapCenter).getX() + frames.get(mapCenter).getXSize() - (frames.get(fr).getX() + frames.get(fr).getXSize()));
                int newY = frames.get(mapCenter).getY() + (frames.get(mapCenter).getY() + frames.get(mapCenter).getYSize() - (frames.get(fr).getY() + frames.get(fr).getYSize()));
                //mid + (mid - skyCoord)
                if (frames.get(fr).isFrame()) {
                    frames.add(new Frame(newX, newY, frames.get(fr).getZ(), frames.get(fr).getXSize(), frames.get(fr).getYSize(), frames.get(fr).getZSize()));
                } else {
                    frames.add(new Skybox(newX, newY, frames.get(fr).getZ(), frames.get(fr).getXSize(), frames.get(fr).getYSize(), frames.get(fr).getZSize()));
                }
                //We use frames.size() to get the last element in 'frames'
                int initMirror = frames.get(fr).getSpireSize();
                for (int i = 0; i < initMirror; i++) {
                    frames.get(frames.size() - 1).addSpire(frames.get(fr).getSpires().get(i).getMirror(frames.get(mapCenter).getX(), frames.get(mapCenter).getY(), frames.get(mapCenter).getX() + frames.get(mapCenter).getXSize(), frames.get(mapCenter).getY() + frames.get(mapCenter).getYSize()));
                }
                initMirror = frames.get(fr).getRoomsSize();
                for (int i = 0; i < initMirror; i++) {
                    //get the mirrored walls, and the mirrored walls from any doors, the lights, and the interior walls
                    frames.get(frames.size() - 1).addRoom(new Room(frames.get(fr).getRooms().get(i).getMirroredRoom(frames.get(mapCenter).getX(), frames.get(mapCenter).getY(), frames.get(mapCenter).getX() + frames.get(mapCenter).getXSize(), frames.get(mapCenter).getY() + frames.get(mapCenter).getYSize()), frames.get(fr).getRooms().get(i).getMirroredDoor(frames.get(mapCenter).getX(), frames.get(mapCenter).getY(), frames.get(mapCenter).getX() + frames.get(mapCenter).getXSize(), frames.get(mapCenter).getY() + frames.get(mapCenter).getYSize()), frames.get(fr).getRooms().get(i).getMirroredInterior(frames.get(mapCenter).getX(), frames.get(mapCenter).getY(), frames.get(mapCenter).getX() + frames.get(mapCenter).getXSize(), frames.get(mapCenter).getY() + frames.get(mapCenter).getYSize()), frames.get(fr).getRooms().get(i).makeMirroredLight(frames.get(mapCenter).getX(), frames.get(mapCenter).getY(), frames.get(mapCenter).getX() + frames.get(mapCenter).getXSize(), frames.get(mapCenter).getY() + frames.get(mapCenter).getYSize(), frames.get(fr).getRooms().get(i).getLightBrightness())));
                    //NOW we need to set the coordinates and thickness for the lights and mirroring to work in the future.
                    frames.get(frames.size() - 1).getRooms().get(frames.get(frames.size() - 1).getRoomsSize() - 1).setX(frames.get(mapCenter).getX() + (frames.get(mapCenter).getX() + frames.get(mapCenter).getXSize() - (frames.get(fr).getRooms().get(i).getX() + frames.get(fr).getRooms().get(i).getXs())));
                    frames.get(frames.size() - 1).getRooms().get(frames.get(frames.size() - 1).getRoomsSize() - 1).setY(frames.get(mapCenter).getX() + (frames.get(mapCenter).getY() + frames.get(mapCenter).getYSize() - (frames.get(fr).getRooms().get(i).getY() + frames.get(fr).getRooms().get(i).getYs())));
                    frames.get(frames.size() - 1).getRooms().get(frames.get(frames.size() - 1).getRoomsSize() - 1).setZ(frames.get(fr).getRooms().get(i).getZ());
                    frames.get(frames.size() - 1).getRooms().get(frames.get(frames.size() - 1).getRoomsSize() - 1).setXs(frames.get(fr).getRooms().get(i).getXs());
                    frames.get(frames.size() - 1).getRooms().get(frames.get(frames.size() - 1).getRoomsSize() - 1).setYs(frames.get(fr).getRooms().get(i).getYs());
                    frames.get(frames.size() - 1).getRooms().get(frames.get(frames.size() - 1).getRoomsSize() - 1).setZs(frames.get(fr).getRooms().get(i).getZs());
                }
                initMirror = frames.get(fr).getInclinesSize();
                for (int i = 0; i < initMirror; i++) {
                    frames.get(frames.size() - 1).addIncline(frames.get(fr).getInclines().get(i).getMirror(frames.get(mapCenter).getX(), frames.get(mapCenter).getY(), frames.get(mapCenter).getX() + frames.get(mapCenter).getXSize(), frames.get(mapCenter).getY() + frames.get(mapCenter).getYSize()));
                }
                initMirror = frames.get(fr).getRampSize();
                for (int i = 0; i < initMirror; i++) {
                    frames.get(frames.size() - 1).addRamp(frames.get(fr).getRamps().get(i).getMirroredRamp(frames.get(mapCenter).getX(), frames.get(mapCenter).getY(), frames.get(mapCenter).getX() + frames.get(mapCenter).getXSize(), frames.get(mapCenter).getY() + frames.get(mapCenter).getYSize()));
                }
                initMirror = frames.get(fr).getEntitiesSize();
                for (int i = 0; i < initMirror; i++) {
                    frames.get(frames.size() - 1).addEntity(frames.get(fr).getEntities().get(i).getMirroredEntity(frames.get(mapCenter).getX(), frames.get(mapCenter).getY(), frames.get(mapCenter).getX() + frames.get(mapCenter).getXSize(), frames.get(mapCenter).getY() + frames.get(mapCenter).getYSize()));
                }
                if (frames.get(fr).getMirrored()) {
                    frames.get(frames.size() - 1).setMirror();
                }
            }
        }

        //ADD General MIRRORED OBJECTS
        for (int fr = 0; fr < frames.size(); fr++) {
            if (frames.get(fr).getMirrored()) {
                int initMirror = frames.get(fr).getSpireSize();
                for (int i = 0; i < initMirror; i++) {
                    frames.get(fr).addSpire(frames.get(fr).getSpires().get(i).getMirror(frames.get(fr).getX(), frames.get(fr).getY(), frames.get(fr).getX() + frames.get(fr).getXSize(), frames.get(fr).getY() + frames.get(fr).getYSize()));
                }
                initMirror = frames.get(fr).getRoomsSize();
                for (int i = 0; i < initMirror; i++) {
                    //get the mirrored walls, and the mirrored walls from any doors, the interior walls, and the light
                    frames.get(fr).addRoom(new Room(frames.get(fr).getRooms().get(i).getMirroredRoom(frames.get(fr).getX(), frames.get(fr).getY(), frames.get(fr).getX() + frames.get(fr).getXSize(), frames.get(fr).getY() + frames.get(fr).getYSize()), frames.get(fr).getRooms().get(i).getMirroredDoor(frames.get(fr).getX(), frames.get(fr).getY(), frames.get(fr).getX() + frames.get(fr).getXSize(), frames.get(fr).getY() + frames.get(fr).getYSize()), frames.get(fr).getRooms().get(i).getMirroredInterior(frames.get(fr).getX(), frames.get(fr).getY(), frames.get(fr).getX() + frames.get(fr).getXSize(), frames.get(fr).getY() + frames.get(fr).getYSize()), frames.get(fr).getRooms().get(i).makeMirroredLight(frames.get(fr).getX(), frames.get(fr).getY(), frames.get(fr).getX() + frames.get(fr).getXSize(), frames.get(fr).getY() + frames.get(fr).getYSize(), frames.get(fr).getRooms().get(i).getLightBrightness())));
                }
                initMirror = frames.get(fr).getInclinesSize();
                for (int i = 0; i < initMirror; i++) {
                    frames.get(fr).addIncline(frames.get(fr).getInclines().get(i).getMirror(frames.get(fr).getX(), frames.get(fr).getY(), frames.get(fr).getX() + frames.get(fr).getXSize(), frames.get(fr).getY() + frames.get(fr).getYSize()));
                }
                initMirror = frames.get(fr).getRampSize();
                for (int i = 0; i < initMirror; i++) {
                    frames.get(fr).addRamp(frames.get(fr).getRamps().get(i).getMirroredRamp(frames.get(fr).getX(), frames.get(fr).getY(), frames.get(fr).getX() + frames.get(fr).getXSize(), frames.get(fr).getY() + frames.get(fr).getYSize()));
                }
                initMirror = frames.get(fr).getEntitiesSize();
                for (int i = 0; i < initMirror; i++) {
                    frames.get(fr).addEntity(frames.get(fr).getEntities().get(i).getMirroredEntity(frames.get(fr).getX(), frames.get(fr).getY(), frames.get(fr).getX() + frames.get(fr).getXSize(), frames.get(fr).getY() + frames.get(fr).getYSize()));
                }
            }
        }
    }

    private static void createVMF() throws IOException {
        boolean savingFlag = true;
        int iterations = 1;
        //this loop creates a file with an indexed value on the end
        //instead of failing to save or overwriting
        while (savingFlag) {
            File f = new File(generatedFilename + ".vmf");
            if (f.exists() && !f.isDirectory()) {
                if (iterations == 1) {
                    generatedFilename = generatedFilename + "(" + iterations + ")";
                } else {
                    generatedFilename = generatedFilename.substring(0, generatedFilename.length() - 3) + "(" + iterations + ")";
                }
                iterations++;
            } else {
                try {
                    writer = new PrintWriter(generatedFilename + ".vmf", "UTF-8");
                } catch (IOException io) {
                    throw new IOException("\nCoudn't make the file named " + generatedFilename + ".\n" + io);
                }
                savingFlag = false;
            }
        }
    }

    private static void makeJump() throws Exception {
        System.out.println("Making a jump!");
        String firstString = reader.next();
        if (firstString.equalsIgnoreCase("scale")) {
            scale = reader.nextInt();
            if (reader.next().equalsIgnoreCase("skybox")) {
                frames.add(new Skybox((scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt()));
            } else {
                throw new Exception("\nSyntax error in skybox declaration on line\n");
            }
        } else {
            if (firstString.equalsIgnoreCase("skybox")) {
                frames.add(new Skybox((scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt()));
            } else {
                throw new Exception("\nSyntax error in skybox declaration Line\n");
            }
        }
    }

    private static void makeMid() throws Exception {
        System.out.println("Just making a mid for fun.");
        String firstString = reader.next();
        if (firstString.equalsIgnoreCase("scale")) {
            scale = reader.nextInt();
            if (reader.next().equalsIgnoreCase("skybox")) {
                frames.add(new Skybox((scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt()));
            } else {
                throw new Exception("\nSyntax error in skybox declaration on line \n");
            }
        } else {
            if (firstString.equalsIgnoreCase("skybox")) {
                frames.add(new Skybox((scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt()));
            } else {
                throw new Exception("\nSyntax error in skybox declaration Line\n");
            }
        }
    }

    private static void make5cp() throws Exception {
        System.out.println("It's a 5 cp.");
        is5cp = true;
        String firstString = reader.next();
        if (firstString.equalsIgnoreCase("scale")) {
            scale = reader.nextInt();
            String line = reader.next().trim();
            if (line.equalsIgnoreCase("skybox")) {
                frames.add(new Skybox((scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt()));
            } else if (line.equalsIgnoreCase("frame")) {
                frames.add(new Frame((scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt()));
            } else {
                throw new Exception("\nSyntax error in initial declaration on line\n");
            }
        } else {
            String line = reader.next().trim();
            if (line.equalsIgnoreCase("mid") || line.equalsIgnoreCase("2nd") || line.equalsIgnoreCase("last")) {
                line = reader.next();
                if (line.equalsIgnoreCase("skybox")) {
                    frames.add(new Skybox((scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt()));
                } else if (line.equalsIgnoreCase("frame")) {
                    frames.add(new Frame((scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt(), (scale) * reader.nextInt()));
                }
            } else {
                throw new Exception("\nSyntax error in initial declaration on line\n");
            }
        }
    }

    private static void input(String[] args) throws Exception {
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
    }

    private static boolean canBeInt(String str) {
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
