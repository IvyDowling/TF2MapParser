/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tf2mapgenerator;

/**
 *
 * @author IV
 */
public class Point implements Drawable {

    private int x, y, z;

    public Point(int xp, int yp, int zp) {
        x = xp;
        y = yp;
        z = zp;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public int getXs() {
        return 0;
    }

    @Override
    public int getYs() {
        return 0;
    }

    @Override
    public int getZs() {
        return 0;
    }

    @Override
    public Type getType() {
        return Type.ENTITY;
    }

    @Override
    public String getOutput(int id) {
        return "";
    }

    public Point getMirror(int xSky, int ySky, int xsSky, int ysSky) {
        //THESE 0s need to be replaced by the fixed size of our points
        return new Point(xSky + (xsSky - (this.getX() + (0))), ySky + (ysSky - (this.getY() + (0))), z);
    }
}
