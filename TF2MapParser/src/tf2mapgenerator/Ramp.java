package tf2mapgenerator;

public class Ramp implements Drawable {

    private int xs, ys, zs;
    private int x, y, z;
    private String direction;

    public Ramp(String direct, int xcoord, int ycoord, int zcoord, int l, int w, int h) {
        x = xcoord;
        y = ycoord;
        z = zcoord;
        xs = l;
        ys = w;
        zs = h; //All ramps start at 0, and go to h
        direction = direct.trim().toLowerCase();//N-E-S-W
    }

    @Override
    public Drawable getMirror(int xSky, int ySky, int xsSky, int ysSky) {
        String newDirect = "";
        switch (direction) {
            case "n":
                newDirect = "s";
                break;
            case "s":
                newDirect = "n";
                break;
            case "e":
                newDirect = "w";
                break;
            case "w":
                newDirect = "e";
                break;
        }
        return new Ramp(newDirect, xSky + (xsSky - (x + xs)), ySky + (ysSky - (y + ys)), z, xs, ys, zs);
    }

    @Override
    public String getOutput(int id) {
        switch (direction) {
            case "n":
                return "solid\n"
                        + "	{\n"
                        + "		\"id\" \" " + id++ + "\"\n"
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x + xs) + " " + (y + ys) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y + ys) + " " + (zs + z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                        + "                     \"uaxis\" \"[0 -1 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 0 1 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x) + " " + (y + ys) + " " + (z + zs) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (ys + y) + " " + (z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                        + "			\"uaxis\" \"[0 -1 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 0 1 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x + xs) + " " + (ys + y) + " " + (zs + z) + ") (" + (x + xs) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                        + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[-1 0 0 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        //+x
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x) + " " + (ys + y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                        + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[-1 0 0 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        //+y
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (z) + ") (" + (x + xs) + " " + (ys + y) + " " + (z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                        + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
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
            case "s":
                return "solid\n"
                        + "	{\n"
                        + "		\"id\" \" " + id++ + "\"\n"
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y + ys) + " " + (z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                        + "                     \"uaxis\" \"[0 -1 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x + xs) + " " + (y) + " " + (z + zs) + ") (" + (x + xs) + " " + (y + ys) + " " + (z) + ") (" + (x + xs) + " " + (y) + " " + (z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                        + "			\"uaxis\" \"[0 -1 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x + xs) + " " + (ys + y) + " " + (z) + ") (" + (x + xs) + " " + (y) + " " + (z + zs) + ") (" + (x) + " " + (y) + " " + (z + zs) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                        + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[-1 0 0 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        //+x
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x + xs) + " " + (y) + " " + (z) + ") (" + (x + xs) + " " + (y + ys) + " " + (z) + ") (" + (x) + " " + (y + ys) + " " + (z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                        + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[-1 0 0 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        //+y
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x + xs) + " " + (y) + " " + (zs + z) + ") (" + (x + xs) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                        + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
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
            case "e":
                return "solid\n"
                        + "	{\n"
                        + "		\"id\" \" " + id++ + "\"\n"
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x + xs) + " " + (y) + " " + (z + zs) + ") (" + (x + xs) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                        + "                     \"uaxis\" \"[1 0 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x) + " " + (y + ys) + " " + (z) + ") (" + (x + xs) + " " + (y + ys) + " " + (z) + ") (" + (x + xs) + " " + (y + ys) + " " + (z + zs) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                        + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x + xs) + " " + (ys + y) + " " + (z + zs) + ") (" + (x + xs) + " " + (y) + " " + (z + zs) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                        + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        //+x
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x + xs) + " " + (y) + " " + (z) + ") (" + (x + xs) + " " + (y + ys) + " " + (z) + ") (" + (x) + " " + (y + ys) + " " + (z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                        + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        //+y
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x + xs) + " " + (y) + " " + (zs + z) + ") (" + (x + xs) + " " + (y + ys) + " " + (z + zs) + ") (" + (x + xs) + " " + (y + ys) + " " + (z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                        + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
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
            case "w":
                return "solid\n"
                        + "	{\n"
                        + "		\"id\" \" " + id++ + "\"\n"
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x) + " " + (y + ys) + " " + (z + zs) + ") (" + (x) + " " + (y + ys) + " " + (z) + ") (" + (x + xs) + " " + (y + ys) + " " + (z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                        + "                     \"uaxis\" \"[1 0 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x + xs) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z + zs) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                        + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x) + " " + (y) + " " + (z + zs) + ") (" + (x) + " " + (y + ys) + " " + (z + zs) + ") (" + (x + xs) + " " + (y + ys) + " " + (z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                        + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        //+x
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x) + " " + (y + ys) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x + xs) + " " + (y) + " " + (z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                        + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                        + "			\"rotation\" \"0\"\n"
                        + "			\"lightmapscale\" \"16\"\n"
                        + "			\"smoothing_groups\" \"0\"\n"
                        + "		}\n"
                        //+y
                        + "		side\n"
                        + "		{\n"
                        + "			\"id\" \"" + (id++) + "\"\n"
                        + "			\"plane\" \"(" + (x) + " " + (y + ys) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (z + zs) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
                        + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                        + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n"
                        + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
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
            default:
                //bad parse
                return "";
        }

    }

    @Override
    public int getXs() {
        return xs;
    }

    @Override
    public int getYs() {
        return ys;
    }

    @Override
    public int getZs() {
        return zs;
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
    public Type getType(){
        return Type.RAMP;
    }
}
