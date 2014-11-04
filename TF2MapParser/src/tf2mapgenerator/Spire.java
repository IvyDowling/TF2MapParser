package tf2mapgenerator;

public class Spire {

    private int xs, ys, zs;
    private int x, y, z;            //COORDINATES ==> bottom left corner
    private boolean kill = false;

    public Spire(int xcoord, int ycoord, int zcoord, int l, int w, int h) {
        x = xcoord;
        y = ycoord;
        z = zcoord;
        xs = l;
        ys = w;
        zs = h;
        if (xs < 0) {
            x = x + xs;
            xs = Math.abs(xs);
        }
        if (ys < 0) {
            y = y + ys;
            ys = Math.abs(ys);
        }
        if (zs < 0) {
            z = z + zs;
            zs = Math.abs(zs);
        }
    }

    public Spire getMirror(int xSky, int ySky, int xsSky, int ysSky) {
        return new Spire(xSky + (xsSky - (this.getX() + this.getXs())), ySky + (ysSky - (this.getY() + this.getYs())), this.getZ(), this.getXs(), this.getYs(), this.getZs());
    }

    public String getOutput(int id) {
        if (xs <= 0 || ys <= 0 || zs <= 0) {
            return "";  //DONT EVEN PARSE IT
        } else {
            return "solid\n"
                    + "	{\n"
                    + "		\"id\" \" " + id++ + "\"\n"
                    //+z
                    + "		side\n"
                    + "		{\n"
                    + "			\"id\" \"" + (id++) + "\"\n"
                    + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                    + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                    + "                 \"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis stuff
                    + "                 \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                    + "			\"rotation\" \"0\"\n"
                    + "			\"lightmapscale\" \"16\"\n"
                    + "			\"smoothing_groups\" \"0\"\n"
                    + "		}\n"
                    //-z
                    + "		side\n"
                    + "		{\n"
                    + "			\"id\" \"" + (id++) + "\"\n"
                    + "			\"plane\" \"(" + x + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (ys + y) + " " + (z) + ")\" \n"
                    + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                    + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                    + "                 \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                    + "			\"rotation\" \"0\"\n"
                    + "			\"lightmapscale\" \"16\"\n"
                    + "			\"smoothing_groups\" \"0\"\n"
                    + "		}\n"
                    //-x
                    + "		side\n"
                    + "		{\n"
                    + "			\"id\" \"" + (id++) + "\"\n"
                    + "			\"plane\" \"(" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
                    + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                    + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                    + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                    + "			\"rotation\" \"0\"\n"
                    + "			\"lightmapscale\" \"16\"\n"
                    + "			\"smoothing_groups\" \"0\"\n"
                    + "		}\n"
                    //+x
                    + "		side\n"
                    + "		{\n"
                    + "			\"id\" \"" + (id++) + "\"\n"
                    + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                    + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                    + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                    + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                    + "			\"rotation\" \"0\"\n"
                    + "			\"lightmapscale\" \"16\"\n"
                    + "			\"smoothing_groups\" \"0\"\n"
                    + "		}\n"
                    //+y
                    + "		side\n"
                    + "		{\n"
                    + "			\"id\" \"" + (id++) + "\"\n"
                    + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (z) + ")\" \n"
                    + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                    + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                    + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                    + "			\"rotation\" \"0\"\n"
                    + "			\"lightmapscale\" \"16\"\n"
                    + "			\"smoothing_groups\" \"0\"\n"
                    + "		}\n"
                    //-y
                    + "		side\n"
                    + "		{\n"
                    + "			\"id\" \"" + (id++) + "\"\n"
                    + "			\"plane\" \"(" + (xs + x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ")\" \n"
                    + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
                    + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                    + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
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
    }

    public String getOutput(int id, String newTexture) {
        if (xs <= 0 || ys <= 0 || zs <= 0) {
            return "";  //DONT EVEN PARSE IT
        } else {
            return "solid\n"
                    + "	{\n"
                    + "		\"id\" \" " + id++ + "\"\n"
                    //+z
                    + "		side\n"
                    + "		{\n"
                    + "			\"id\" \"" + (id++) + "\"\n"
                    + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                    + "			\"material\" \"" + newTexture + "\"\n"
                    + "                 \"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis stuff
                    + "                 \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                    + "			\"rotation\" \"0\"\n"
                    + "			\"lightmapscale\" \"16\"\n"
                    + "			\"smoothing_groups\" \"0\"\n"
                    + "		}\n"
                    //-z
                    + "		side\n"
                    + "		{\n"
                    + "			\"id\" \"" + (id++) + "\"\n"
                    + "			\"plane\" \"(" + x + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (ys + y) + " " + (z) + ")\" \n"
                    + "			\"material\" \"" + newTexture + "\"\n"
                    + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                    + "                 \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                    + "			\"rotation\" \"0\"\n"
                    + "			\"lightmapscale\" \"16\"\n"
                    + "			\"smoothing_groups\" \"0\"\n"
                    + "		}\n"
                    //-x
                    + "		side\n"
                    + "		{\n"
                    + "			\"id\" \"" + (id++) + "\"\n"
                    + "			\"plane\" \"(" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
                    + "			\"material\" \"" + newTexture + "\"\n"
                    + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                    + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                    + "			\"rotation\" \"0\"\n"
                    + "			\"lightmapscale\" \"16\"\n"
                    + "			\"smoothing_groups\" \"0\"\n"
                    + "		}\n"
                    //+x
                    + "		side\n"
                    + "		{\n"
                    + "			\"id\" \"" + (id++) + "\"\n"
                    + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                    + "			\"material\" \"" + newTexture + "\"\n"
                    + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                    + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                    + "			\"rotation\" \"0\"\n"
                    + "			\"lightmapscale\" \"16\"\n"
                    + "			\"smoothing_groups\" \"0\"\n"
                    + "		}\n"
                    //+y
                    + "		side\n"
                    + "		{\n"
                    + "			\"id\" \"" + (id++) + "\"\n"
                    + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (z) + ")\" \n"
                    + "			\"material\" \"" + newTexture + "\"\n"
                    + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                    + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                    + "			\"rotation\" \"0\"\n"
                    + "			\"lightmapscale\" \"16\"\n"
                    + "			\"smoothing_groups\" \"0\"\n"
                    + "		}\n"
                    //-y
                    + "		side\n"
                    + "		{\n"
                    + "			\"id\" \"" + (id++) + "\"\n"
                    + "			\"plane\" \"(" + (xs + x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ")\" \n"
                    + "			\"material\" \"" + newTexture + "\"\n"
                    + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                    + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
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
    }

    public void kill() {
        kill = true;    //Not evaluated until right before mirroring
    }

    public boolean getKill() {
        if (xs == 0 && ys == 0 && zs == 0) {
            return true;
        } else {
            return kill;
        }
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

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y + ", z: " + z + ", xs: " + xs + ", ys: " + ys + ", zs:" + zs;
    }

    public boolean equals(Spire s) {
        if ((s.getX() == this.x) && (s.getY() == this.y) && (s.getZ() == this.z) && (s.getXs() == this.xs) && (s.getYs() == this.y) && (s.getZs() == this.zs)) {
            return true;
        }
        return false;
    }
}
