/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tf2mapgenerator;

import java.util.ArrayList;

/**
 *
 * @author IV
 */
public class Connector {

    private int xs, ys, zs, xs0, ys0, zs0;
    private int x, y, z, x0, y0, z0;

    private boolean mirrored;

    private ArrayList<Entity> entities;
    private ArrayList<Spire> spires;
    private ArrayList<Ramp> ramps;
    private ArrayList<Incline> inclines;
    private ArrayList<Room> rooms;

    public Connector(int xcoord, int ycoord, int zcoord, int l, int w, int h) {
        x = x0 = xcoord;
        y = y0 = ycoord;
        z = z0 = zcoord;
        xs = xs0 = l;
        ys = ys0 = w;
        zs = zs0 = h;
    }

    public Connector(Frame fr1, Frame fr2) {
//        fr1.makeDoor();
//        fr2.makeDoor();
        spires = new ArrayList<>();
        ramps = new ArrayList<>();
        inclines = new ArrayList<>();
        rooms = new ArrayList<>();
        entities = new ArrayList<>();
    }

    public String getOutput(int id) {
        return "";
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

    public int getXSize() {
        return xs0;
    }

    public int getYSize() {
        return ys0;
    }

    public int getZSize() {
        return zs0;
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
}
