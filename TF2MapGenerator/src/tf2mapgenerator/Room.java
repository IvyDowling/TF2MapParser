package tf2mapgenerator;

import java.util.ArrayList;

public class Room {

    private int xs, ys, zs, xs0, ys0, zs0;
    private int x, y, z, x0, y0, z0;
    private int dw;                 //thickness of walls
    private ArrayList<Spire> exterior;

    public Room(int xcoord, int ycoord, int zcoord, int l, int w, int h, int thickness) {
        this.exterior = new ArrayList<>();
        x = x0 = xcoord;
        y = y0 = ycoord;
        z = z0 = zcoord;
        xs = xs0 = l;
        ys = ys0 = w;
        zs = zs0 = h;
        dw = thickness;
        exterior.add(new Spire(x, y, (z + (zs - dw)), xs, ys, dw)); // TOP +z
        exterior.add(new Spire(x, y, z, xs, ys, dw)); // BOTTOM -z   
        exterior.add(new Spire(x + (xs - dw), y, z + dw, dw, ys, (zs - (2 * dw)))); // RIGHT +x
        exterior.add(new Spire(x, y, z + dw, dw, ys, zs - (2 * dw))); // LEFT -x
        exterior.add(new Spire(x + dw, y + (ys - dw), z + dw, xs - (2 * dw), dw, zs - (2 * dw))); // BACK +y
        exterior.add(new Spire(x + dw, y, z + dw, xs - (2 * dw), dw, zs - (2 * dw))); // FRONT -y
    }
    public Room(ArrayList<Spire> walls){
        exterior = walls;
    }

    public ArrayList<Spire> getMirroredRoom(int xXs, int yYs) {
        ArrayList<Spire> mirroredRoom = new ArrayList<>();
        for (int i = 0; i < exterior.size(); i++) {
            mirroredRoom.add(exterior.get(i).getMirror(xXs, yYs));
        }
        return mirroredRoom;
    }

    public ArrayList<Spire> getWalls() {
        return exterior;
    }

    public void deleteWall(String wall) {
        wall = wall.trim().toLowerCase();
        switch (wall) {
            case "+z":
//                exterior.set(0, new Spire(0,0,0,0,0,0));    //I did this so that the list retains it's shape
                exterior.get(0).kill();
                break;
            case "-z":
//                exterior.set(1, new Spire(0, 0, 0, 0, 0, 0));    //I did this so that the list retains it's shape
                exterior.get(1).kill();
                break;
            case "+x":
//                exterior.set(2, new Spire(0, 0, 0, 0, 0, 0));    //I did this so that the list retains it's shape
                exterior.get(2).kill();
                break;
            case "-x":
//                exterior.set(3, new Spire(0, 0, 0, 0, 0, 0));    //I did this so that the list retains it's shape
                exterior.get(3).kill();
                break;
            case "+y":
//                exterior.set(4, new Spire(0, 0, 0, 0, 0, 0));    //I did this so that the list retains it's shape
                exterior.get(4).kill();
                break;
            case "-y":
//                exterior.set(5, new Spire(0, 0, 0, 0, 0, 0));    //I did this so that the list retains it's shape
                exterior.get(5).kill();
                break;
        }
    }

    public String getOutput(int id) {
        int givenID = id;
        String out = "";
        for (int i = 0; i < exterior.size(); i++) {
            out += exterior.get(i).getOutput(givenID);
            givenID = givenID + 6;        //there are 6 id hits in each spire
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
}
