package tf2mapgenerator;

public class Incline implements Drawable {

    private int zs, run, dw, l;
    private int x, y, z;            //COORDINATES ==> bottom left corner
    private Compass direction;

    public Incline(Compass direct, int xcoord, int ycoord, int zcoord, int r, int rn, int length, int thickness) {
        direction = direct;
        x = xcoord;
        y = ycoord;
        z = zcoord;
        zs = r;
        run = rn;
        l = length;
        dw = thickness;
    }

    @Override
    public Drawable getMirror(int xSky, int ySky, int xsSky, int ysSky) {
        return new Incline(direction.invert180(), xSky + (xsSky - (x + l)), ySky + (ysSky - (y + run)), z, zs, run, l, dw);
    }

    @Override
    public String getOutput(int id) {
        int ratio = Math.round(run / zs) * dw;
        if (dw <= 0 || run <= 0 || zs <= 0 || l <= 0 || ratio <= 0) {
            return "";  //DONT EVEN PARSE IT
        } else {
            String out = "";
            int x0, y0, z0;
            x0 = x;
            y0 = y;
            z0 = z;
            int run0 = run;
            switch (direction) {
                case NORTH:
                    out = "solid\n"
                            + "	{\n"
                            + "		\"id\" \" " + id++ + "\"\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x + l) + " " + (y + run) + " " + (z + (zs - dw)) + ") (" + (x + l) + " " + (y + run) + " " + (z + zs) + ") (" + (x) + " " + (y + run) + " " + (z + zs) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                            + "                     \"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis stuff
                            + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x + l) + " " + (y) + " " + (z) + ") (" + (x + l) + " " + (y + ratio) + " " + (z) + ") (" + (x) + " " + (y + ratio) + " " + (z) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                            + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x) + " " + (y + run) + " " + (z + zs) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y + ratio) + " " + (z) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                            + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                            + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x + l) + " " + (y) + " " + (z) + ") (" + (x + l) + " " + (y + run) + " " + (z + zs) + ") (" + (x + l) + " " + (y + run) + " " + (z + (zs - dw)) + ") \" \n"
                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                            + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                            + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x) + " " + (y + run) + " " + (z + (zs - dw)) + ") (" + (x) + " " + (y + ratio) + " " + (z) + ") (" + (x + l) + " " + (y + ratio) + " " + (z) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                            + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y + run) + " " + (z + zs) + ") (" + (x + l) + " " + (y + run) + " " + (z + zs) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                            + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n";
                    break;
                case SOUTH:
                    out = "solid\n"
                            + "	{\n"
                            + "		\"id\" \" " + id++ + "\"\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x + l) + " " + (y) + " " + (z + zs - dw) + ") (" + (x) + " " + (y) + " " + (z + zs - dw) + ") (" + (x) + " " + (y) + " " + (z + zs) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                            + "                 \"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis stuff
                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x) + " " + (y + (run - ratio)) + " " + (z) + ") (" + (x + l) + " " + (y + (run - ratio)) + " " + (z) + ") (" + (x + l) + " " + (y + run) + " " + (z) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                            + "                 \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x) + " " + (y) + " " + (z + zs - dw) + ") (" + (x) + " " + (y + (run - ratio)) + " " + (z) + ") (" + (x) + " " + (y + run) + " " + (z) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                            + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x + l) + " " + (y + (run - ratio)) + " " + (z) + ") (" + (x + l) + " " + (y) + " " + (z + zs - dw) + ") (" + (x + l) + " " + (y) + " " + (z + zs) + ") \" \n"
                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                            + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x) + " " + (y) + " " + (z + zs) + ") (" + (x) + " " + (y + run) + " " + (z) + ") (" + (x + l) + " " + (y + run) + " " + (z) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x) + " " + (y + (run - ratio)) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z + zs - dw) + ") (" + (x + l) + " " + (y) + " " + (z + zs - dw) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n";
                    break;
                case EAST:
                    out = "solid\n"
                            + "	{\n"
                            + "		\"id\" \" " + id++ + "\"\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x + run) + " " + (y) + " " + (z + zs) + ") (" + (x + run) + " " + (y + l) + " " + (z + zs) + ") (" + (x + run) + " " + (y + l) + " " + (z + zs - dw) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                            + "                 \"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis stuff
                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x) + " " + (y + l) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x + ratio) + " " + (y) + " " + (z) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                            + "                 \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x) + " " + (y + l) + " " + (z) + ") (" + (x + run) + " " + (y + l) + " " + (z + zs) + ") (" + (x + run) + " " + (y) + " " + (z + zs) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                            + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x + run) + " " + (y + l) + " " + (z + zs - dw) + ") (" + (x + ratio) + " " + (y + l) + " " + (z) + ") (" + (x + ratio) + " " + (y) + " " + (z) + ") \" \n"
                            + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                            + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x + ratio) + " " + (y + l) + " " + (z) + ") (" + (x + run) + " " + (y + l) + " " + (z + zs - dw) + ") (" + (x + run) + " " + (y + l) + " " + (z + zs) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x + run) + " " + (y) + " " + (z + zs - dw) + ") (" + (x + ratio) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n";
                    break;
                case WEST:
                    out = "solid\n"
                            + "	{\n"
                            + "		\"id\" \" " + id++ + "\"\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x) + " " + (y + l) + " " + (z + zs) + ") (" + (x) + " " + (y) + " " + (z + zs) + ") (" + (x) + " " + (y) + " " + (z + zs - dw) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                            + "                 \"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis stuff
                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x + run) + " " + (y) + " " + (z) + ") (" + (x + run) + " " + (y + l) + " " + (z) + ") (" + (x + run - ratio) + " " + (y + l) + " " + (z) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                            + "                 \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x + run) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z + zs) + ") (" + (x) + " " + (y + l) + " " + (z + zs) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                            + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x) + " " + (y) + " " + (z + zs - dw) + ") (" + (x + run - ratio) + " " + (y) + " " + (z) + ") (" + (x + run - ratio) + " " + (y + l) + " " + (z) + ") \" \n"
                            + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                            + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x + run - ratio) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z + zs - dw) + ") (" + (x) + " " + (y) + " " + (z + zs) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n"
                            //
                            + "		side\n"
                            + "		{\n"
                            + "			\"id\" \"" + (id++) + "\"\n"
                            + "			\"plane\" \"(" + (x) + " " + (y + l) + " " + (z + zs - dw) + ") (" + (x + run - ratio) + " " + (y + l) + " " + (z) + ") (" + (x + run) + " " + (y + l) + " " + (z) + ")\" \n"
                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                            + "			\"rotation\" \"0\"\n"
                            + "			\"lightmapscale\" \"16\"\n"
                            + "			\"smoothing_groups\" \"0\"\n"
                            + "		}\n";
                    break;
            }

            out += "		editor\n"
                    + "		{\n"
                    + "			\"color\" \"0 146 143\"\n"
                    + "			\"visgroupshown\" \"1\"\n"
                    + "			\"visgroupautoshown\" \"1\"\n"
                    + "		}\n"
                    + "	}";
            return out;
        }
    }

    @Override
    public int getZs() {
        return zs;
    }

    public int getRun() {
        return run;
    }

    public int getDw() {
        return dw;
    }

    public int getL() {
        return l;
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
    public Type getType() {
        return Type.INCLINE;
    }

    public Compass getDirection() {
        return direction;
    }

    @Override
    public int getXs() {
        if(direction == Compass.NORTH || direction == Compass.SOUTH){
            return run;
        } else {
            return l;
        }
    }

    @Override
    public int getYs() {
        if(direction == Compass.NORTH || direction == Compass.SOUTH){
            return l;
        } else {
            return run;
        }
    }

}
