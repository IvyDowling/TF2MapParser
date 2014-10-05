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
public class Square {

    private int x;
    private int y;
    private int xs;
    private int ys;

    public Square(int xinit, int yinit, int xsinit, int ysinit) {
        x = xinit;
        y = yinit;
        xs = xsinit;
        ys = ysinit;
    }

    public Spire makeSpire(int wall, int xR, int yR, int zR, int xsR, int ysR, int zsR, int dw) {
        // When the wall was created, the variables were mapped as follows
        // -Z wall: x = Wx y = Wy
        // -X wall: x = Wy y = Wz
        // -Y wall: x = Wx y = Wz
        Spire temp;
        switch (wall) {
            case 0: //+Z PLANE
                temp = new Spire(x, y, zR + (zsR - dw), xs, ys, dw);
                break;
            case 1: //-Z
                temp = new Spire(x, y, zR - dw, xs, ys, dw);
                break;
            case 2: //+X PLANE
                temp = new Spire(xR + (xsR - dw), x, y, dw, xs, ys);
                break;
            case 3: // -X
                temp = new Spire(xR, x, y, dw, xs, ys);
                break;
            case 4: //+Y PLANE
                temp = new Spire(x, yR + (ysR - dw), y, xs, dw, ys);
                break;
            case 5: // -Y
                temp = new Spire(x, yR, y, xs, dw, ys);
                break;
            default:    //bad parse
                //000000 should be caught by the output method in spire
                temp = new Spire(0, 0, 0, 0, 0, 0);
        }
        return temp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXs() {
        return xs;
    }

    public int getYs() {
        return ys;
    }

    @Override
    public String toString() {
        return "Door at x: " + x + ", y: " + y + ", xs: " + xs + ", ys: " + ys;
    }
}
