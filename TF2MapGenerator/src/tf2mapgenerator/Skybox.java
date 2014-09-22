package tf2mapgenerator;

import java.util.ArrayList;

public class Skybox {

    private int xs, ys, zs, xs0, ys0, zs0;
    private int x, y, z, x0, y0, z0;
    private int dw = 64;     //Thickness of skybox walls

    private boolean mirrored;
    private String additionalOutput;

    private ArrayList<Spire> spires = new ArrayList<>();
    private ArrayList<Ramp> ramps = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();

    public Skybox(int xcoord, int ycoord, int zcoord, int l, int w, int h) {
        x = x0 = xcoord;
        y = y0 = ycoord;
        z = z0 = zcoord;
        xs = xs0 = l;
        ys = ys0 = w;
        zs = zs0 = h;
        additionalOutput = "";
    }
    
    public String getAdditionalOutput(){
        //method for rooms lights and other skybox related content
        return additionalOutput;
    }
    
    public void additionalOutput(String add){
        additionalOutput += add;
    }

    public String getOutput(int id) {

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
                + "	\"id\" \"" + (id++) + "\"\n"
                + "	\"mapversion\" \"1\"\n"
                + "	\"classname\" \"worldspawn\"\n"
                + "	\"skyname\" \"sky_trainyard_01\"\n"
                + "	\"maxpropscreenwidth\" \"-1\"\n"
                + "	\"detailvbsp\" \"detail.vbsp\"\n"
                + "	\"detailmaterial\" \"detail/detailsprites\"\n";
        //
        //  SOLID 1 -- TOP -- (+z)
        //
        x = x0;
        y = y0;
        z = z0;
        xs = xs0;
        ys = xs0;
        zs = zs0;

        z = z + zs;
        zs = dw;

        out += "solid\n"
                + "	{\n"
                + "		\"id\" \" " + (id++) + "\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
        //  SOLID 2 -- BOTTOM -- (-z)
        //
        x = x0;
        y = y0;
        z = z0;
        xs = xs0;
        ys = xs0;
        zs = zs0;

        z = z - dw;
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

        x = x + xs;
        xs = dw;

        out += "solid\n"
                + "	{\n"
                + "		\"id\" \"" + (id++) + "\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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

        x = x - dw;
        xs = dw;

        out += "solid\n"
                + "	{\n"
                + "		\"id\" \"" + (id++) + "\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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

        y = y + ys;
        ys = dw;

        out += "solid\n"
                + "	{\n"
                + "		\"id\" \"" + (id++) + "\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
        //  SOLID 6 (-y)
        //
        x = x0;
        y = y0;
        z = z0;
        xs = xs0;
        ys = xs0;
        zs = zs0;

        y = y - dw;
        ys = dw;

        out += "solid\n"
                + "	{\n"
                + "		\"id\" \"" + (id++) + "\"\n"
                //+z
                + "		side\n"
                + "		{\n"
                + "			\"id\" \"" + (id++) + "\"\n"
                + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
                + "			 \"material\" \"TOOLS/TOOLSSKYBOX\"\n"
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
        x = x0;
        y = y0;
        z = z0;
        xs = xs0;
        ys = xs0;
        zs = zs0;

        // ITS THE END (OF THE WOLRD AS WE KNOW IT)
        return out;

    }

    //
    // ARRAY LISTS
    //
    public ArrayList<Spire> getSpires() {
        return spires;
    }

    public ArrayList<Ramp> getRamps() {
        return ramps;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public int getSpireSize() {
        return spires.size();
    }

    public int getRampSize() {
        return ramps.size();
    }

    public int getRoomsSize() {
        return rooms.size();
    }

    public void addSpire(Spire spire) {
        spires.add(spire);
    }

    public void addRamp(Ramp ramp) {
        ramps.add(ramp);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    //ENDING HERE
    public boolean getMirrored() {
        return mirrored;
    }

    public void setMirror(boolean mir) {
        mirrored = mir;
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

    public int getX() {
        return x0;
    }

    public int getY() {
        return y0;
    }

    public int getZ() {
        return z0;
    }

}
