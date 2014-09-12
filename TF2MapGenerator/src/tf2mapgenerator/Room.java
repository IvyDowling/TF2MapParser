package tf2mapgenerator;

public class Room {

    private int xs, ys, zs, xs0, ys0, zs0;
    private int x, y, z, x0, y0, z0;
    private int dw;                 //thickness of walls

    public Room(int xcoord, int ycoord, int zcoord, int l, int w, int h, int thickness) {
        x = x0 = xcoord;
        y = y0 = ycoord;
        z = z0 = zcoord;
        xs = xs0 = l;
        ys = ys0 = w;
        zs = zs0 = h;
        dw = thickness;
    }

    public String getOutput(int id) {

        String out = "";
        //
        //  SOLID 1 -- BOTTOM -- (-z)
        //

        zs = dw;
        out += "solid\n"
                + "	{\n"
                + "		\"id\" \" " + (id++) + "\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "                     \"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis stuff
                + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
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
                + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //-x
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //+x
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //+y
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //-y
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
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
        //
        //  SOLID 2 -- TOP -- (+z)
        //
        x = x0;
        y = y0;
        z = z0;
        xs = xs0;
        ys = xs0;
        zs = zs0;
        
        z = z + (zs - dw);
        zs = dw;        //reset to req value
        
        out += "solid\n"
                + "	{\n"
                + "		\"id\" \" " + (id++) + "\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "                     \"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis stuff
                + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
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
                + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //-x
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //+x
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //+y
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //-y
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
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
        //
        //  SOLID 3 -- RIGHT -- (+x)
        //
        x = x0;
        y = y0;
        z = z0;
        xs = xs0;
        ys = xs0;
        zs = zs0;
        
        z = z + dw;
        x = x + (xs - dw);
        xs = dw;
        zs = (zs-(2*dw));
        out += "solid\n"
                + "	{\n"
                + "		\"id\" \"" + (id++) + "\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "                     \"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis stuff
                + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
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
                + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //-x
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //+x
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //+y
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //-y
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
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
        //
        //  SOLID 4 -- LEFT -- (-x)
        //
        x = x0;
        y = y0;
        z = z0;
        xs = xs0;
        ys = xs0;
        zs = zs0;
        
        z = z + dw;
        xs = dw;
        zs = zs - (2*dw);
        
        out += "solid\n"
                + "	{\n"
                + "		\"id\" \"" + (id++) + "\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "                     \"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis stuff
                + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
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
                + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //-x
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //+x
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //+y
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //-y
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
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
        //
        //  SOLID 5 --BACK-- (+y)
        //
        x = x0;
        y = y0;
        z = z0;
        xs = xs0;
        ys = xs0;
        zs = zs0;

        x = x + dw;
        y = y + (ys-dw);
        z = z + dw;
        xs = xs - (2 * dw);
        ys = dw;
        zs = zs - (2 * dw);
        out += "solid\n"
                + "	{\n"
                + "		\"id\" \"" + (id++) + "\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "                     \"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis stuff
                + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
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
                + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //-x
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //+x
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //+y
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //-y
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
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
        //
        //  SOLID 6
        //
        x = x0;
        y = y0;
        z = z0;
        xs = xs0;
        ys = xs0;
        zs = zs0;
        
        x= x + dw;
        z = z + dw;
        xs = xs - (2 * dw);
        ys = dw;
        zs = zs - (2 * dw);
        out += "solid\n"
                + "	{\n"
                + "		\"id\" \"" + (id++) + "\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "                     \"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis stuff
                + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
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
                + "                     \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //-x
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //+x
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //+y
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
                + "                     \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
                + "			\"rotation\" \"0\"\n"
                + "			\"lightmapscale\" \"16\"\n"
                + "			\"smoothing_groups\" \"0\"\n"
                + "		}\n"
                //-y
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + (xs + x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
                + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
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

        ys = ys0;
        // ITS THE END (OF THE ROOM AS WE KNOW IT)
        return out;

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

    public int getDw() {
        return dw;
    }
}
