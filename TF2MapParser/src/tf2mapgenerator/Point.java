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
public class Point {

    private int x, y, z;

    public Point(int xp, int yp, int zp) {
        x = xp;
        y = yp;
        z = zp;
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

    public String getOuptut(int id) {
        return "";
    }

    public Point getMirror(int xSky, int ySky, int xsSky, int ysSky) {
        //THESE 0s need to be replaced by the fixed size of our points
        return new Point(xSky + (xsSky - (this.getX() + (0))), ySky + (ysSky - (this.getY() + (0))), z);
    }
}
