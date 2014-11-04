package tf2mapgenerator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author IV
 */
public class Frame {

    private int xs, ys, zs, xs0, ys0, zs0;
    private int x, y, z, x0, y0, z0;
    private int dw = 64;

    private boolean mirrored, isConnector;
    private ArrayList<Spire> frameWalls = new ArrayList<>();
    private Light frameLight;

    private ArrayList<Spire> spires = new ArrayList<>();
    private ArrayList<Ramp> ramps = new ArrayList<>();
    private ArrayList<Incline> inclines = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Entity> entities = new ArrayList<>();

    public Frame(int xcoord, int ycoord, int zcoord, int l, int w, int h) {
        x = x0 = xcoord;
        y = y0 = ycoord;
        z = z0 = zcoord;
        xs = xs0 = l;
        ys = ys0 = w;
        zs = zs0 = h;
        frameLight = new Light((x + (xs / 2)), (y + (ys / 2)), (z + (zs / 2)), ((xs * ys * zs / (200)) + 300));

        //+z
        frameWalls.add(new FrameWall(x, y, (z + zs), xs, ys, dw));
        //-z 
        frameWalls.add(new FrameWall(x, y, (z - dw), xs, ys, dw));
        //+x
        frameWalls.add(new FrameWall((x + xs), y, z, dw, ys, zs));
        //-x 
        frameWalls.add(new FrameWall((x - dw), y, z, dw, ys, zs));
        //+y
        frameWalls.add(new FrameWall(x, (y + ys), z, xs, dw, zs));
        //-y
        frameWalls.add(new FrameWall(x, (y - dw), z, xs, dw, zs));

    }

    public String getOutput(int id) {
        //gotta kill the walls we made doors in
        int i = 0;
        while (i < frameWalls.size()) {
            if (frameWalls.get(i).getKill()) {
                frameWalls.remove(i);
            } else {
                i++;
            }
        }
        String out = "";
        for (int fw = 0; fw < frameWalls.size(); fw++) {
            out += frameWalls.get(fw).getOutput(id);
            id = id + 6;
        }
        return out;
    }

    public String getLightOutput() {
        return frameLight.getOutput();
    }

    public ArrayList<Spire> getFrameWalls() {
        return frameWalls;
    }

    public int getFrameWallsSize() {
        return frameWalls.size();
    }

    public ArrayList<Spire> getSpires() {
        return spires;
    }

    public ArrayList<Ramp> getRamps() {
        return ramps;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Incline> getInclines() {
        return inclines;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public int getSpireSize() {
        return spires.size();
    }

    public int getRampSize() {
        return ramps.size();
    }

    public int getRoomsSize() {
        return rooms.size();
    }

    public int getInclinesSize() {
        return inclines.size();
    }

    public int getEntitiesSize() {
        return entities.size();
    }

    public void addSpire(Spire spire) {
        spires.add(spire);
    }

    public void addRamp(Ramp ramp) {
        ramps.add(ramp);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addIncline(Incline incline) {
        inclines.add(incline);
    }

    public void addEntity(Entity res) {
        entities.add(res);
    }

    //ENDING HERE
    public void setMirror() {
        mirrored = true;
    }

    public boolean getMirrored() {
        return mirrored;
    }

    public void setConnector() {
        isConnector = true;
    }

    public boolean getConnector() {
        return isConnector;
    }

    private boolean isIntersecting(int x, int y, int z, int xs, int ys, int zs) {
        //If either of these conditions do not pass, quit.
        //checks to see overlap between planes
        if (!(((this.x + this.xs) >= x) && (x >= this.x)) && !(((x + xs) >= this.x) && (this.x >= x))) {
            return false;//dont' need to
        }
        if (!(((this.y + this.ys) >= y) && (y >= this.y)) && !(((y + ys) >= this.y) && (this.y >= y))) {
            return false;//dont' need to
        }
        if (!(((this.z + this.zs) >= z) && (z >= this.z)) && !(((z + zs) >= this.z) && (this.z >= z))) {
            return false;//dont' need to
        }
        return true;
    }

    private ArrayList<Spire> deleteDoubles(ArrayList<Spire> l) {
        ArrayList<Spire> temp = l;
        int reps = 0;
        for (int i = 0; i < temp.size(); i++) {
            for (int e = 0; e < temp.size(); e++) {
                if (temp.get(e).equals(temp.get(i)) && e != i) {
                    reps++;
                }
            }
            if (reps > 1) {
                for (int del = 0; del < reps - 1; del++) {
                    temp.remove(temp.get(i));
                }
            }
        }
        return temp;
    }

    public void cutOutDoors(int x, int y, int z, int xs, int ys, int zs) {
        if (!isIntersecting(x, y, z, xs, ys, zs)) {
            return;
        }
        //these values we were given are the general frame variables.
        boolean[] doorsNeeded = new boolean[6];

        Iterator it = frameWalls.iterator();

        FrameWall side;
        if (it.hasNext()) {
            side = (FrameWall) it.next();
        } else {
            return;
        }
        ArrayList<Spire> addThese = new ArrayList<>();

        //TOP: +z
        //This check is done before each side, it sees if side needs to be skipped,
        //if a side would be 0 or the length after we made it, lets not.
        if (((side.getZ() + side.getZs()) >= z) && (z >= side.getZ()) && (y - side.getY() < side.getYs()) && (((side.getY() + side.getYs()) - (y + ys)) < side.getYs()) && (x - side.getX() < side.getXs()) && (((side.getX() + side.getXs()) - (x + xs)) < side.getXs())) {
            doorsNeeded[0] = true;
            if (x - side.getX() > 0) {
                addThese.add(new FrameWall(side.getX(), side.getY(), side.getZ(), x - side.getX(), side.getYs(), side.getZs()));
            }
            if (((side.getX() + side.getXs()) - (x + xs)) > 0) {
                //Then there isn't a door at the far edge, so we can  make the last square too
                addThese.add(new FrameWall(x + xs, side.getY(), side.getZ(), (side.getX() + side.getXs()) - (x + xs), side.getYs(), side.getZs()));
            }
            //TOPS AND BOTTOMS
            if ((y - side.getY()) > 0) {
                //bottom square
                addThese.add(new FrameWall(x, side.getY(), side.getZ(), xs, y - side.getY(), side.getZs()));
            }
            if ((side.getY() + side.getYs() - (y + ys)) > 0) {
                //top square
                addThese.add(new FrameWall(x, y + ys, side.getZ(), xs, (side.getY() + side.getYs()) - (y + ys), side.getZs()));
            }
        }
        //next side of the frame
        if (it.hasNext()) {
            side = (FrameWall) it.next();
        } else {
            return;
        }
        //BOTTOM: -z
        if (((z + zs) >= side.getZ()) && (side.getZ() >= z) && (y - side.getY() < side.getYs()) && (((side.getY() + side.getYs()) - (y + ys)) < side.getYs()) && (x - side.getX() < side.getXs()) && (((side.getX() + side.getXs()) - (x + xs)) < side.getXs())) {
            doorsNeeded[1] = true;
            if (x - side.getX() > 0) {
                addThese.add(new FrameWall(side.getX(), side.getY(), side.getZ(), x - side.getX(), side.getYs(), side.getZs()));
            }
            if (((side.getX() + side.getXs()) - (x + xs)) > 0) {
                //Then there isn't a door at the far edge, so we can  make the last square too
                addThese.add(new FrameWall(x + xs, side.getY(), side.getZ(), (side.getX() + side.getXs()) - (x + xs), side.getYs(), side.getZs()));
            }
            //TOPS AND BOTTOMS
            if ((y - side.getY()) > 0) {
                //bottom square
                addThese.add(new FrameWall(side.getX(), side.getY(), side.getZ(), side.getXs(), y - side.getY(), side.getZs()));
            }
            if (((side.getY() + side.getYs()) - (y + ys)) > 0) {
                //top square
                addThese.add(new FrameWall(side.getX(), y + ys, side.getZ(), side.getXs(), (side.getY() + side.getYs()) - (y + ys), side.getZs()));
            }
        }
        if (it.hasNext()) {
            side = (FrameWall) it.next();
        } else {
            return;
        }
        //**
        //RIGHT: +x
        if ((((side.getX() + side.getXs()) >= x) && (x >= side.getX())) && (y - side.getY() < side.getYs()) && (((side.getY() + side.getYs()) - (y + ys)) < side.getYs()) && (z - side.getZ() < side.getZs()) && (((side.getZ() + side.getZs()) - (z + zs)) < side.getZs())) {
            doorsNeeded[2] = true;
            if (y - side.getY() > 0) {
                addThese.add(new FrameWall(side.getX(), side.getY(), side.getZ(), side.getXs(), (y - side.getY()), side.getZs()));
            }
            if (side.getY() + side.getYs() - (y + ys) > 0) {
                //Then there isn't a door at the far edge, so we can  make the last square too
                addThese.add(new FrameWall(side.getX(), y + ys, side.getZ(), side.getXs(), (side.getY() + side.getYs()) - (y + ys), side.getZs()));
            }
            //TOPS AND BOTTOMS
            if (z - side.getZ() > 0) {
                //bottom square
                addThese.add(new FrameWall(side.getX(), y, side.getZ(), side.getXs(), ys, z - side.getZ()));
            }
            if (side.getZ() + side.getZs() - (z + zs) > 0) {
                //top square
                addThese.add(new FrameWall(side.getX(), y, z + zs, side.getXs(), ys, (side.getZ() + side.getZs()) - (z + zs)));
            }
        }
        if (it.hasNext()) {
            side = (FrameWall) it.next();
        } else {
            return;
        }
        //LEFT: -x
        if ((((x + xs) >= side.getX()) && (side.getX() >= x)) && (y - side.getY() < side.getYs()) && (((side.getY() + side.getYs()) - (y + ys)) < side.getYs()) && (z - side.getZ() < side.getZs()) && (((side.getZ() + side.getZs()) - (z + zs)) < side.getZs())) {
            doorsNeeded[3] = true;
            if (y - side.getY() > 0) {
                addThese.add(new FrameWall(side.getX(), side.getY(), side.getZ(), side.getXs(), (y - side.getY()), side.getZs()));
            }
            if (side.getY() + side.getYs() - (y + ys) > 0) {
                //Then there isn't a door at the far edge, so we can  make the last square too
                addThese.add(new FrameWall(side.getX(), y + ys, side.getZ(), side.getXs(), (side.getY() + side.getYs()) - (y + ys), side.getZs()));
            }
            //TOPS AND BOTTOMS
            if (z - side.getZ() > 0) {
                //bottom square
                addThese.add(new FrameWall(side.getX(), side.getY(), side.getZ(), side.getXs(), side.getYs(), z - side.getZ()));
            }
            if ((side.getZ() + side.getZs()) - (z + zs) > 0) {
                //top square
                addThese.add(new FrameWall(side.getX(), side.getY(), z + zs, side.getXs(), side.getYs(), (side.getZ() + side.getZs()) - (z + zs)));
            }
        }
        if (it.hasNext()) {
            side = (FrameWall) it.next();
        } else {
            return;
        }
        //**
        //BACK: +y
        if ((((side.getY() + side.getYs()) >= y) && (y >= side.getY()) && (x - side.getX() < side.getXs()) && (((side.getX() + side.getXs()) - (x + xs)) < side.getXs())) && (z - side.getZ() < side.getZs()) && (((side.getZ() + side.getZs()) - (z + zs)) < side.getZs())) {
            doorsNeeded[4] = true;
            if ((x - side.getX() > 0)) {
                addThese.add(new FrameWall(side.getX(), side.getY(), side.getZ(), x - side.getX(), side.getYs(), side.getZs()));
            }
            if (((side.getX() + side.getXs()) - (x + xs) > 0)) {
                //Then there isn't a door at the far edge, so we can  make the last square too
                addThese.add(new FrameWall(x + xs, side.getY(), side.getZ(), (side.getX() + side.getXs()) - (x + xs), side.getYs(), side.getZs()));
            }
            //TOPS AND BOTTOMS
            if (((side.getZ() + side.getZs()) - (z + zs)) > 0) {
                //top square
                addThese.add(new FrameWall(side.getX(), side.getY(), z + zs, side.getXs(), side.getYs(), (side.getZ() + side.getZs()) - (z + zs)));
            }
            if ((z - side.getZ()) > 0) {
                //bottom square
                //THIS ONE IS DIFFERENT side.getY() - side.getYs() so that we compensate for the wall thickness.
                addThese.add(new FrameWall(side.getX(), y, side.getZ(), side.getXs(), side.getYs(), z - side.getZ()));
            }
        }
        if (it.hasNext()) {
            side = (FrameWall) it.next();
        } else {
            return;
        }
        //FRONT: -y
        if ((((y + ys) >= side.getY()) && (side.getY() >= y)) && (x - side.getX() < side.getXs()) && (((side.getX() + side.getXs()) - (x + xs)) < side.getXs()) && (z - side.getZ() < side.getZs()) && (((side.getZ() + side.getZs()) - (z + zs)) < side.getZs())) {
            doorsNeeded[5] = true;
            if ((x - side.getX() > 0)) {
                //THIS IS BROKEN
                addThese.add(new FrameWall(side.getX(), side.getY(), side.getZ(), x - side.getX(), side.getYs(), side.getZs()));
            }
            if ((((side.getX() + side.getXs()) - (x + xs)) > 0)) {
                //Then there isn't a door at the far edge, so we can  make the last square too
                addThese.add(new FrameWall(x + xs, side.getY(), side.getZ(), (side.getX() + side.getXs()) - (x + xs), side.getYs(), side.getZs()));
            }
            //TOPS AND BOTTOMS
            if ((side.getZ() + side.getZs() - (z + zs)) > 0) {
                //top square
                addThese.add(new FrameWall(side.getX(), side.getY(), z + zs, side.getXs(), side.getYs(), (side.getZ() + side.getZs()) - (z + zs)));
            }
            if ((z - side.getZ()) > 0) {
                //bottom square
                addThese.add(new FrameWall(side.getX(), side.getY(), side.getZ(), side.getXs(), side.getYs(), z - side.getZ()));
            }
        }

        int i = 0;
        while (i < doorsNeeded.length) {
            if (doorsNeeded[i]) {
                frameWalls.get(i).kill();
            }
            i++;
        }
//        addThese = deleteDoubles(addThese);
        frameWalls.addAll(addThese);
    }

    public int getXSize() {
        return xs;
    }

    public int getYSize() {
        return ys;
    }

    public int getZSize() {
        return zs;
    }

    public int getX() {
        return x0;
    }

    public int getY() {
        return y0;
    }

    public int getZ() {
        return z0;
    }

    public boolean isFrame() {
        return true;
    }

    private class FrameWall extends Spire {

        public FrameWall(int xcoord, int ycoord, int zcoord, int l, int w, int h) {
            super(xcoord, ycoord, zcoord, l, w, h);
        }

    }

}
