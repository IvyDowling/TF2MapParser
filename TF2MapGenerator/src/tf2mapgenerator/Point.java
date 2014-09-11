package tf2mapgenerator;

public class Point {

    private int xs, ys, zs;
    private int x0, y0, z0;
    private int x, y, z;
    private int d = 64;     //Thickness of skybox walls

    private boolean mirrored;

    public Point(int xsi, int ysi, int zsi) {
        xs = x0 = xsi;
        ys = y0 = ysi;
        zs = z0 = zsi;
    }

    public boolean getMirrored() {
        return mirrored;
    }

    public void setMirror(boolean mr) {
        mirrored = mr;
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

    @Override
    public String toString() {

        x = 0;
        y = 0;
        z = zs;
        zs = d;
        String out = "versioninfo\n"
                + "{\n"
                + "	\"editorversion\" \"400\"\n"
                + "	\"editorbuild\" \"6488\"\n"
                + "	\"mapversion\" \"1\"\n"
                + "	\"formatversion\" \"100\"\n"
                + "	\"prefab\" \"0\"\n"
                + "}\n"
                + "visgroups\n"
                + "{\n"
                + "}\n"
                + "viewsettings\n"
                + "{\n"
                + "	\"bSnapToGrid\" \"1\"\n"
                + "	\"bShowGrid\" \"1\"\n"
                + "	\"bShowLogicalGrid\" \"0\"\n"
                + "	\"nGridSpacing\" \"128\"\n"
                + "	\"bShow3DGrid\" \"0\"\n"
                + "}\n"
                + "world\n"
                + "{\n"
                + "	\"id\" \"1\"\n"
                + "	\"mapversion\" \"1\"\n"
                + "	\"classname\" \"worldspawn\"\n"
                + "	\"skyname\" \"sky_day01_01\"\n"
                + "	\"maxpropscreenwidth\" \"-1\"\n"
                + "	\"detailvbsp\" \"detail.vbsp\"\n"
                + "	\"detailmaterial\" \"detail/detailsprites\"\n"
                //
                //  SOLID 1 -- BOTTOM
                //
                + "solid\n"
                + "	{\n"
                + "		\"id\" \"2\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"3\"\n"
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
                + "			\"id\" \"4\"\n"
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
                + "			\"id\" \"5\"\n"
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
                + "			\"id\" \"6\"\n"
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
                + "			\"id\" \"7\"\n"
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
                + "			\"id\" \"8\"\n"
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
        //
        //  SOLID 2
        //

        x = 0;
        y = 0;
        z = -d;
        out += "solid\n"
                + "	{\n"
                + "		\"id\" \"9\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"10\"\n"
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
                + "			\"id\" \"11\"\n"
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
                + "			\"id\" \"12\"\n"
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
                + "			\"id\" \"13\"\n"
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
                + "			\"id\" \"14\"\n"
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
                + "			\"id\" \"15\"\n"
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
        //
        //  SOLID 3
        //
        zs = z0;//reset from z thickness

        x = xs;
        y = 0;
        z = 0;
        xs = d;
        out += "solid\n"
                + "	{\n"
                + "		\"id\" \"16\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"17\"\n"
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
                + "			\"id\" \"18\"\n"
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
                + "			\"id\" \"19\"\n"
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
                + "			\"id\" \"20\"\n"
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
                + "			\"id\" \"21\"\n"
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
                + "			\"id\" \"22\"\n"
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
        //
        //  SOLID 4
        //
        x = -d;
        y = 0;
        z = 0;
        out += "solid\n"
                + "	{\n"
                + "		\"id\" \"23\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"24\"\n"
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
                + "			\"id\" \"25\"\n"
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
                + "			\"id\" \"26\"\n"
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
                + "			\"id\" \"27\"\n"
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
                + "			\"id\" \"28\"\n"
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
                + "			\"id\" \"29\"\n"
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
        //
        //  SOLID 5
        //
        xs = x0;//reset from x sides
        
        x = 0;
        y = ys;
        z = 0;
        ys = d;
        out += "solid\n"
                + "	{\n"
                + "		\"id\" \"30\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"31\"\n"
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
                + "			\"id\" \"32\"\n"
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
                + "			\"id\" \"33\"\n"
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
                + "			\"id\" \"34\"\n"
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
                + "			\"id\" \"35\"\n"
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
                + "			\"id\" \"36\"\n"
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
        //
        //  SOLID 6
        //
        x = 0;
        y = -d;
        z = 0;
        out += "solid\n"
                + "	{\n"
                + "		\"id\" \"37\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"38\"\n"
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
                + "			\"id\" \"39\"\n"
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
                + "			\"id\" \"40\"\n"
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
                + "			\"id\" \"41\"\n"
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
                + "			\"id\" \"42\"\n"
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
                + "			\"id\" \"43\"\n"
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
        ys = y0;
        // ITS THE END OF THE WORLD AS WE KNOW IT
        return out;
    }
}
