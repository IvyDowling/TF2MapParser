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
public class Frame {

    private int xs, ys, zs, xs0, ys0, zs0;
    private int x, y, z, x0, y0, z0;
    private int dw = 64;

    private boolean mirrored;
    private ArrayList<FrameWall> frameWalls = new ArrayList<>();

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
        frameWalls.add(new FrameWall(x, y, (z + zs), xs, ys, dw));
        frameWalls.add(new FrameWall(x, y, (z - dw), xs, ys, dw));
        frameWalls.add(new FrameWall((x + xs), y, z, dw, ys, zs));
        frameWalls.add(new FrameWall((x - dw), y, z, dw, ys, zs));
        frameWalls.add(new FrameWall(x, (y + ys), z, xs, dw, zs));
        frameWalls.add(new FrameWall(x, (y - dw), z, xs, dw, zs));

    }

    public Frame() {
        x = x0 = 0;
        y = y0 = 0;
        z = z0 = 0;
        xs = xs0 = 0;
        ys = ys0 = 0;
        zs = zs0 = 0;
    }

    public void makeDoor(String wallface, int x, int y) {

    }
    
//    public Spire[] getMirroredFrame(int xmap, int ymap, int xsmap, int ysmap) {
//        Spire[] mirroredFrame = new Spire[6];
//        for (int i = 0; i < frameWalls.size(); i++) {
//            mirroredFrame[i] = (frameWalls.get(i).getMirror(xmap, ymap, xsmap, ysmap));
//        }
//        return mirroredFrame;
//    }


    public String getOutput(int id) {

        String out = "";
        for(int fw = 0; fw < frameWalls.size(); fw++){
            out += frameWalls.get(fw).getOutput(id);
            id = id + 6;
        }
        return out;
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
