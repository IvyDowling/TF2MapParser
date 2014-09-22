package tf2mapgenerator;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private int xs, ys, zs, xs0, ys0, zs0;
    private int x, y, z, x0, y0, z0;
    private int dw;                 //thickness of walls

    private Spire[] exterior = new Spire[6];
    private ArrayList<Spire> croppedWalls;
    private ArrayList<Square> squares;
    private List<List<Door>> doors;

    public Room(int xcoord, int ycoord, int zcoord, int l, int w, int h, int thickness) {
        croppedWalls = new ArrayList<>();
        squares = new ArrayList<>();
        doors = new ArrayList<>();
        doors.add(new ArrayList<Door>());
        doors.add(new ArrayList<Door>());
        doors.add(new ArrayList<Door>());
        doors.add(new ArrayList<Door>());
        doors.add(new ArrayList<Door>());
        doors.add(new ArrayList<Door>());
        x = x0 = xcoord;
        y = y0 = ycoord;
        z = z0 = zcoord;
        xs = xs0 = l;
        ys = ys0 = w;
        zs = zs0 = h;
        dw = thickness;
        //TEST WALLS
        exterior[0] = (new Spire(x, y, (z + (zs - dw)), xs, ys, dw)); // TOP +z
        exterior[1] = (new Spire(x, y, z, xs, ys, dw)); // BOTTOM -z   
        exterior[2] = (new Spire(x + (xs - dw), y, z, dw, ys, zs)); // RIGHT +x
        exterior[3] = (new Spire(x, y, z, dw, ys, zs)); // LEFT -x
        exterior[4] = (new Spire(x, y + (ys - dw), z, xs, dw, zs)); // BACK +y
        exterior[5] = (new Spire(x, y, z, xs, dw, zs)); // FRONT -y
        //PROPER WALLS
//        exterior[0] = (new Spire(x, y, (z + (zs - dw)), xs, ys, dw)); // TOP +z
//        exterior[1] = (new Spire(x, y, z, xs, ys, dw)); // BOTTOM -z   
//        exterior[2] = (new Spire(x + (xs - dw), y, z + dw, dw, ys, (zs - (2 * dw)))); // RIGHT +x
//        exterior[3] = (new Spire(x, y, z + dw, dw, ys, zs - (2 * dw))); // LEFT -x
//        exterior[4] = (new Spire(x + dw, y + (ys - dw), z + dw, xs - (2 * dw), dw, zs - (2 * dw))); // BACK +y
//        exterior[5] = (new Spire(x + dw, y, z + dw, xs - (2 * dw), dw, zs - (2 * dw))); // FRONT -y
    }

    public Room(Spire[] walls, ArrayList<Spire> crops) {
        exterior = walls;
        croppedWalls = crops;
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

    public Spire[] getWalls() {
        return exterior;
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
        int theWall = -1;
        switch (wall) {
            case "+z":
                theWall = 0;
                break;
            case "-z":
                theWall = 1;
                break;
            case "+x":
                theWall = 2;
                break;
            case "-x":
                theWall = 3;
                break;
            case "+y":
                theWall = 4;
                break;
            case "-y":
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
                exterior[0].kill();
                break;
            case "-z":
                exterior[1].kill();
                break;
            case "+x":
                exterior[2].kill();
                break;
            case "-x":
                exterior[3].kill();
                break;
            case "+y":
                exterior[4].kill();
                break;
            case "-y":
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
        //cropped walls method
        if (croppedWalls.size() > 0) {
            for (int i = 0; i < croppedWalls.size(); i++) {
                out += croppedWalls.get(i).getOutput(givenID);
                givenID = givenID + 7;
            }
        }
        return out;

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

    public String getLight() {
        return "";

    }
}
