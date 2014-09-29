package tf2mapgenerator;

public class Incline {

    private int zs, run, dw, l;
    private int x, y, z;            //COORDINATES ==> bottom left corner
    private String direction;

    public Incline(String direct, int xcoord, int ycoord, int zcoord, int r, int rn, int length, int thickness) {
        direction = direct.trim().toLowerCase();
        x = xcoord;
        y = ycoord;
        z = zcoord;
        zs = r;
        run = rn;
        l = length;
        dw = thickness;
    }

    public Incline getMirror(int xSky, int ySky, int xsSky, int ysSky) {
        String newDirect = "";
        switch (direction) {
            case "+x":
            case "n":
                newDirect = "s";
                return new Incline(newDirect, xSky + (xsSky - (x + l)), ySky + (ysSky - (y + run)), z, zs, run, l, dw);
            case "-x":
            case "s":
                newDirect = "n";
                return new Incline(newDirect, xSky + (xsSky - (x + l)), ySky + (ysSky - (y + run)), z, zs, run, l, dw);
            case "+y":
            case "e":
                newDirect = "w";
                return new Incline(newDirect, xSky + (xsSky - (x + run)), ySky + (ysSky - (y + l)), z, zs, run, l, dw);
            case "-y":
            case "w":
                newDirect = "e";
                return new Incline(newDirect, xSky + (xsSky - (x + run)), ySky + (ysSky - (y + l)), z, zs, run, l, dw);
        }
        //something got messed up
        return null;

    }

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
                case "n":
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
                case "s":
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
                case "e":
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
                case "w":
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

    public int getRise() {
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getDirection() {
        return direction;
    }

}
