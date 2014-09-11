package tf2mapgenerator;

public class Spire {

    private int xs, ys, zs;
    private int x, y, z;            //COORDINATES ==> bottom left corner

    public Spire(int xcoord, int ycoord, int zcoord, int l, int w, int h) {
        x = xcoord;
        y = ycoord;
        z = zcoord;
        xs = l;
        ys = w;
        zs = h;
    }

    public String toString(int id) {
        return "solid\n"
                + "	{\n"
                + "		\"id\" \" " + id + "\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id + 1) + "\"\n"
                + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n"
                + "			\"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //-z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id + 2) + "\"\n"
                + "			\"plane\" \"(" + x + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (ys + y) + " " + (z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n"
                + "			\"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //-x
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id + 3) + "\"\n"
                + "			\"plane\" \"(" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n"
                + "			\"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //+x
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id + 4) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n"
                + "			\"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //+y
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id + 5) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n"
                + "			\"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //-y
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id + 6) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n"
                + "			\"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                + "		editor\n"
                + "		{\n"
                + "			\"color\" \"0 146 143\"\n"
                + "			\"visgroupshown\" \"1\"\n"
                + "			\"visgroupautoshown\" \"1\"\n"
                + "		}\n"
                + "	}";
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

}
