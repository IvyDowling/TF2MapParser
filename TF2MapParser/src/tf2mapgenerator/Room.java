package tf2mapgenerator;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private int xs, ys, zs;
    private int x, y, z;
    private int dw;                 //thickness of walls

    private Spire[] exterior = new Spire[6];
    private ArrayList<Spire> croppedWalls;
    private ArrayList<Spire> interior;
    private ArrayList<Square> squares;
    private List<List<Door>> doors;
    private Light roomLight;

    public Room(int xcoord, int ycoord, int zcoord, int l, int w, int h, int thickness) {
        croppedWalls = new ArrayList<>();
        interior = new ArrayList<>();
        squares = new ArrayList<>();
        doors = new ArrayList<>();
        doors.add(new ArrayList<Door>());
        doors.add(new ArrayList<Door>());
        doors.add(new ArrayList<Door>());
        doors.add(new ArrayList<Door>());
        doors.add(new ArrayList<Door>());
        doors.add(new ArrayList<Door>());
        x = xcoord;
        y = ycoord;
        z = zcoord;
        xs = l;
        ys = w;
        zs = h;
        dw = thickness;
        //make a light in the center of the room with a brightness that fits the volume
        // 262144= 64^3
        roomLight = new Light((x + (xs / 2)), (y + (ys / 2)), (z + (zs / 2)), ((xs * ys * zs / (262144)) + 300));
        //TEST WALLS
        exterior[0] = (new Spire(x, y, (z + (zs - dw)), xs, ys, dw)); // TOP +z
        exterior[1] = (new Spire(x, y, z, xs, ys, dw)); // BOTTOM -z   
        exterior[2] = (new Spire(x + (xs - dw), y, z, dw, ys, zs)); // RIGHT +x
        exterior[3] = (new Spire(x, y, z, dw, ys, zs)); // LEFT -x
        exterior[4] = (new Spire(x, y + (ys - dw), z, xs, dw, zs)); // BACK +y
        exterior[5] = (new Spire(x, y, z, xs, dw, zs)); // FRONT -y
        //PROPER WALLS
//        exterior[0] = (new Spire(x, y, (z + (zs0 - dw)), xs0, ys0, dw)); // TOP +z
//        exterior[1] = (new Spire(x, y, z, xs0, ys0, dw)); // BOTTOM -z   
//        exterior[2] = (new Spire(x + (xs0 - dw), y, z + dw, dw, ys0, (zs0 - (2 * dw)))); // RIGHT +x
//        exterior[3] = (new Spire(x, y, z + dw, dw, ys0, zs0 - (2 * dw))); // LEFT -x
//        exterior[4] = (new Spire(x + dw, y + (ys0 - dw), z + dw, xs0 - (2 * dw), dw, zs0 - (2 * dw))); // BACK +y
//        exterior[5] = (new Spire(x + dw, y, z + dw, xs0 - (2 * dw), dw, zs0 - (2 * dw))); // FRONT -y
    }

    public Room(Spire[] walls, ArrayList<Spire> crops, ArrayList<Spire> interWalls, Light newLight) {
        exterior = walls;
        croppedWalls = crops;
        interior = interWalls;
        roomLight = newLight;
    }

    public Spire[] getMirroredRoom(int xSky, int ySky, int xXs, int yYs) {
        Spire[] mirroredRoom = new Spire[6];
        for (int i = 0; i < exterior.length; i++) {
            mirroredRoom[i] = (exterior[i].getMirror(xSky, ySky, xXs, yYs));
        }
        return mirroredRoom;
    }

    public ArrayList<Spire> getMirroredDoor(int xSky, int ySky, int xXs, int yYs) {
        ArrayList<Spire> crops = new ArrayList<>();
        for (int i = 0; i < croppedWalls.size(); i++) {
            crops.add(croppedWalls.get(i).getMirror(xSky, ySky, xXs, yYs));
        }
        return crops;
    }

    public ArrayList<Spire> getMirroredInterior(int xSky, int ySky, int xXs, int yYs) {
        ArrayList<Spire> inter = new ArrayList<>();
        for (int i = 0; i < interior.size(); i++) {
            inter.add(interior.get(i).getMirror(xSky, ySky, xXs, yYs));
        }
        return inter;
    }

    public Spire[] getExteriorWalls() {
        return exterior;
    }

    public List<Spire> getInteriorWalls() {
        // not quite sure about that forward cast
        return interior;
    }

    public void addInteriorWall(String plane, int wallX, int wallY, int length) {
        if (plane.trim().equalsIgnoreCase("x")) {
            interior.add(new Spire(x + wallX, y + wallY, z, length, dw, zs)); // x
        }
        if (plane.trim().equalsIgnoreCase("y")) {
            interior.add(new Spire(x + wallX, y + wallY, z, dw, length, zs)); // y
        }
    }

    private Door[] sortDoors(int whichWall) {
        ArrayList<Door> doorTempList = new ArrayList<>();
        doorTempList.addAll(doors.get(whichWall));              //copy doors into temp list
        Door[] temp = new Door[doors.get(whichWall).size()];
        if (temp.length > 0) {
            for (int i = 0; i < temp.length; i++) {
                int smallest = 0;
                for (int d = 0; d < doorTempList.size(); d++) {
                    if (doorTempList.get(d).getX() < doorTempList.get(smallest).getX()) {
                        smallest = d;
                    }
                }
                temp[i] = doorTempList.get(smallest);
                doorTempList.remove(smallest);
            }
        }
        return temp;
    }

    public void addDoor(String wall, int xD, int yD, int xsD, int ysD) {
        wall = wall.trim().toLowerCase();
        int theWall;
        switch (wall) {
            case "+z":
            case "up":
                theWall = 0;
                break;
            case "-z":
            case "down":
                theWall = 1;
                break;
            case "+x":
            case "n":
                theWall = 2;
                break;
            case "-x":
            case "s":
                theWall = 3;
                break;
            case "+y":
            case "e":
                theWall = 4;
                break;
            case "-y":
            case "w":
                theWall = 5;
                break;
            default:
                return;
        }
        exterior[theWall].kill();
        doors.get(theWall).add(new Door(xD, yD, xsD, ysD));
    }

    public void cutOutDoors() {
        //these variables all depend upon the wall in question.
        // x and y are used for simplicity.
        // HOW IT WORKS (W: wall)
        // Z wall: x = Wx y = Wy
        // X wall: x = Wy y = Wz
        // Y wall: x = Wx y = Wz

        //for every wall, lets set the variables pretaining to the wall,
        // then evaluate any doors that may have been added to the wall
        for (int wall = 0; wall < doors.size(); wall++) {
            //Plane variables
            int xW;
            int yW;
            int xsW;
            int ysW;
            //INSIDE THIS SWITCH 
            //is where all of the specific assignment of each variable take place.
            switch (wall) {
                case 0:
                    xW = exterior[wall].getX();
                    yW = exterior[wall].getY();
                    xsW = exterior[wall].getXs();
                    ysW = exterior[wall].getYs();
                    break;
                case 1:
                    xW = exterior[wall].getX();
                    yW = exterior[wall].getY();
                    xsW = exterior[wall].getXs();
                    ysW = exterior[wall].getYs();
                    break;
                case 2:
                    xW = exterior[wall].getY();
                    yW = exterior[wall].getZ();
                    xsW = exterior[wall].getYs();
                    ysW = exterior[wall].getZs();
                    break;
                case 3:
                    xW = exterior[wall].getY();
                    yW = exterior[wall].getZ();
                    xsW = exterior[wall].getYs();
                    ysW = exterior[wall].getZs();
                    break;
                case 4:
                    xW = exterior[wall].getX();
                    yW = exterior[wall].getZ();
                    xsW = exterior[wall].getXs();
                    ysW = exterior[wall].getZs();
                    break;
                case 5:
                    xW = exterior[wall].getX();
                    yW = exterior[wall].getZ();
                    xsW = exterior[wall].getXs();
                    ysW = exterior[wall].getZs();
                    break;
                default:    //bad parse
                    return;
            }

            //WALL BREAK-UP
            Door[] sortedDoors = sortDoors(wall);       // sorted with respect to the x value

            if (sortedDoors.length == 1) {
                for (int dr = 0; dr < sortedDoors.length; dr++) {
                    //EDGE CHECK
                    if (sortedDoors[dr].getX() != 0) {
                        //This makes the first square before any doors, if there isn't a door at x = 0
                        squares.add(new Square(xW, yW, sortedDoors[dr].getX() + dw, ysW));
                    }
                    if (sortedDoors[dr].getX() + sortedDoors[dr].getXs() < xsW) {
                        //Then there isn't a door at the far edge, so we can  make the last square too
                        //new Square(whereTheLastDoorEnds,bottom,edgeAfterLastDoor,wholeWallHeight)
                        squares.add(new Square(xW + dw + (sortedDoors[dr].getX() + sortedDoors[dr].getXs()), yW, (xsW - (sortedDoors[dr].getX() + sortedDoors[dr].getXs())) - dw, ysW));
                    }
                    //TOPS AND BOTTOMS
                    if (sortedDoors[dr].getY() != 0) {
                        //bottom square
                        squares.add(new Square(xW + sortedDoors[dr].getX() + dw, yW, sortedDoors[dr].getXs(), sortedDoors[dr].getY()));
                    }
                    if (ysW - (sortedDoors[dr].getY() + sortedDoors[dr].getYs()) != 0) {
                        //top square
                        squares.add(new Square(xW + sortedDoors[dr].getX() + dw, yW + sortedDoors[dr].getY() + sortedDoors[dr].getYs(), sortedDoors[dr].getXs(), (ysW - (sortedDoors[dr].getY() + sortedDoors[dr].getYs()))));
                    }
                }
            } else {
                // Life just got hard
            }
            if (squares.size() > 0) {
                int sqSize = squares.size();
                int squ = 0;
                while (sqSize > 0) {
                    //square method makes spires from squares when given the wall vals
                    croppedWalls.add(squares.get(0).makeSpire(wall, exterior[wall].getX(), exterior[wall].getY(), exterior[wall].getZ(), exterior[wall].getXs(), exterior[wall].getYs(), exterior[wall].getZs(), dw));
                    squares.remove(0);
                    sqSize = sqSize - 1;
                }
            }
        }
    }

    public void deleteWall(String wall) {
        wall = wall.trim().toLowerCase();
        switch (wall) {
            case "+z":
            case "up":
                exterior[0].kill();
                break;
            case "-z":
            case "down":
                exterior[1].kill();
                break;
            case "+x":
            case "n":
                exterior[2].kill();
                break;
            case "-x":
            case "s":
                exterior[3].kill();
                break;
            case "+y":
            case "e":
                exterior[4].kill();
                break;
            case "-y":
            case "w":
                exterior[5].kill();
                break;
        }
    }

    public String getOutput(int id) {
        int givenID = id;
        String out = "";
        for (int i = 0; i < exterior.length; i++) {
            if (!exterior[i].getKill()) {
                out += exterior[i].getOutput(givenID);
                givenID = givenID + 7;        //there are 6 id hits in each spire
            }
        }
        //cropped walls
        if (croppedWalls.size() > 0) {
            for (int i = 0; i < croppedWalls.size(); i++) {
                out += croppedWalls.get(i).getOutput(givenID);
                givenID = givenID + 7;
            }
        }
        //interior walls
        if (interior.size() > 0) {
            for (int i = 0; i < interior.size(); i++) {
                out += interior.get(i).getOutput(givenID);
                givenID = givenID + 7;
            }
        }
        return out;

    }

    public String getLightOutput() {
        return roomLight.getOutput();
    }

    public int getLightBrightness() {
        return roomLight.getBrightness();
    }

    public Light makeMirroredLight(int xSky, int ySky, int xsSky, int ysSky, int brightness) {
        // 262144= 64^3
        //mirror: xSky + (xsSky - (this.getX() + this.getXs()))
        int x0 = xSky + (xsSky - (this.getX() + this.getXs())) + (this.getXs() / 2);
        int y0 = ySky + (ysSky - (this.getY() + this.getYs())) + (this.getYs() / 2);
        int z0 = this.getZ() + (this.getZs() / 2);
        Light mirrorLight = new Light(x0, y0, z0, brightness);

        return mirrorLight;
    }

    public int getXs() {
        return xs;
    }

    public int getYs() {
        return ys;
    }

    public int getZs() {
        return zs;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getDw() {
        return dw;

    }

    public void setXs(int xs) {
        this.xs = xs;
    }

    public void setYs(int ys) {
        this.ys = ys;
    }

    public void setZs(int zs) {
        this.zs = zs;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setDw(int dw) {
        this.dw = dw;
    }

    private class Door extends Square {

        public Door(int xD, int yD, int xsD, int ysD) {
            super(xD, yD, xsD, ysD);
        }

    }

}
