package tf2mapgenerator;

public class Ramp {

    private int xs, ys, zs;
    private int x, y, z;
    private String direction;

    public Ramp(int xcoord, int ycoord, int zcoord, int l, int w, int h, String direct) {
        x = xcoord;
        y = ycoord;
        z = zcoord;
        xs = l;
        ys = w;
        zs = h; //All ramps start at 0, and go to h
        direction = direct;//N-E-S-W
    }

    public String toString(int id) {
        return "";
    }
}
