package tf2mapgenerator;

import java.util.ArrayList;

public class Skybox extends Frame {

//    private int dw = 64;     //Thickness of skybox walls
//
//    private ArrayList<SkyboxWall> skyboxWalls = new ArrayList<>();

    public Skybox(int xcoord, int ycoord, int zcoord, int l, int w, int h) {
        super(xcoord, ycoord, zcoord, l, w, h);

//        skyboxWalls.add(new SkyboxWall(xcoord, ycoord, (zcoord + h), l, w, dw, false));
//        skyboxWalls.add(new SkyboxWall(xcoord, ycoord, (zcoord - dw), l, w, dw, true));
//        skyboxWalls.add(new SkyboxWall((xcoord + l), ycoord, zcoord, dw, w, h, false));
//        skyboxWalls.add(new SkyboxWall((xcoord - dw), ycoord, zcoord, dw, w, h, false));
//        skyboxWalls.add(new SkyboxWall(xcoord, (ycoord + w), zcoord, l, dw, h, false));
//        skyboxWalls.add(new SkyboxWall(xcoord, (ycoord - dw), zcoord, l, dw, h, false));
    }

    @Override
    public boolean isFrame() {
        return false;
    }

    @Override
    public String getOutput(int id) {

        ArrayList<SkyboxWall> temp = new ArrayList<>();
        String out = "";
        for (int sw = 0; sw < super.getFrameWallsSize(); sw++) {
            temp.add(new SkyboxWall(super.getFrameWalls().get(sw)));
        }
        for(int o = 0; o < temp.size();o++){
            out += temp.get(o).getOutput(id);
        }

        // ITS THE END (OF THE WOLRD AS WE KNOW IT)
        return out;

    }

    private class SkyboxWall extends Spire {

        private boolean isFloor = false;
        private int xs, ys, zs;
        private int x, y, z;

        public SkyboxWall(int xcoord, int ycoord, int zcoord, int l, int w, int h, boolean floor) {
            super(xcoord, ycoord, zcoord, l, w, h);
            isFloor = floor;
            x = xcoord;
            y = ycoord;
            z = zcoord;
            xs = l;
            ys = w;
            zs = h;
        }

        public SkyboxWall(Spire s) {
            super(s.getX(), s.getY(), s.getZ(), s.getXs(), s.getYs(), s.getZs());
        }

        @Override
        public String getOutput(int id) {
            if (super.getXs() <= 0 || super.getYs() <= 0 || super.getZs() <= 0) {
                return "";  //DONT EVEN PARSE IT
            } else {
                if (isFloor) {
                    return super.getOutput(id);
//                    return "solid\n"
//                            + "	{\n"
//                            + "		\"id\" \" " + id++ + "\"\n"
//                            //+z
//                            + "		side\n"
//                            + "		{\n"
//                            + "			\"id\" \"" + (id++) + "\"\n"
//                            + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
//                            + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
//                            + "                 \"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis stuff
//                            + "                 \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
//                            + "			\"rotation\" \"0\"\n"
//                            + "			\"lightmapscale\" \"16\"\n"
//                            + "			\"smoothing_groups\" \"0\"\n"
//                            + "		}\n"
//                            //-z
//                            + "		side\n"
//                            + "		{\n"
//                            + "			\"id\" \"" + (id++) + "\"\n"
//                            + "			\"plane\" \"(" + x + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (ys + y) + " " + (z) + ")\" \n"
//                            + "			\"material\" \"DEV/DEV_BLENDMEASURE2\"\n"
//                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
//                            + "                 \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
//                            + "			\"rotation\" \"0\"\n"
//                            + "			\"lightmapscale\" \"16\"\n"
//                            + "			\"smoothing_groups\" \"0\"\n"
//                            + "		}\n"
//                            //-x
//                            + "		side\n"
//                            + "		{\n"
//                            + "			\"id\" \"" + (id++) + "\"\n"
//                            + "			\"plane\" \"(" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
//                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
//                            + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
//                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
//                            + "			\"rotation\" \"0\"\n"
//                            + "			\"lightmapscale\" \"16\"\n"
//                            + "			\"smoothing_groups\" \"0\"\n"
//                            + "		}\n"
//                            //+x
//                            + "		side\n"
//                            + "		{\n"
//                            + "			\"id\" \"" + (id++) + "\"\n"
//                            + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
//                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
//                            + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
//                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
//                            + "			\"rotation\" \"0\"\n"
//                            + "			\"lightmapscale\" \"16\"\n"
//                            + "			\"smoothing_groups\" \"0\"\n"
//                            + "		}\n"
//                            //+y
//                            + "		side\n"
//                            + "		{\n"
//                            + "			\"id\" \"" + (id++) + "\"\n"
//                            + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (z) + ")\" \n"
//                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
//                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
//                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
//                            + "			\"rotation\" \"0\"\n"
//                            + "			\"lightmapscale\" \"16\"\n"
//                            + "			\"smoothing_groups\" \"0\"\n"
//                            + "		}\n"
//                            //-y
//                            + "		side\n"
//                            + "		{\n"
//                            + "			\"id\" \"" + (id++) + "\"\n"
//                            + "			\"plane\" \"(" + (xs + x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ")\" \n"
//                            + "			\"material\" \"DEV/DEV_MEASUREWALL01A\"\n"
//                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
//                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
//                            + "			\"rotation\" \"0\"\n"
//                            + "			\"lightmapscale\" \"16\"\n"
//                            + "			\"smoothing_groups\" \"0\"\n"
//                            + "		}\n"
//                            + "		editor\n"
//                            + "		{\n"
//                            + "			\"color\" \"0 146 143\"\n"
//                            + "			\"visgroupshown\" \"1\"\n"
//                            + "			\"visgroupautoshown\" \"1\"\n"
//                            + "		}\n"
//                            + "	}";
                } else {
                    return super.getOutput(id, "TOOLS/TOOLSSKYBOX");
//                    return "solid\n"
//                            + "	{\n"
//                            + "		\"id\" \" " + id++ + "\"\n"
//                            //+z
//                            + "		side\n"
//                            + "		{\n"
//                            + "			\"id\" \"" + (id++) + "\"\n"
//                            + "			\"plane\" \"(" + x + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
//                            + "			\"material\" \"TOOLS/TOOLSSKYBOX\"\n"
//                            + "                 \"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis stuff
//                            + "                 \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
//                            + "			\"rotation\" \"0\"\n"
//                            + "			\"lightmapscale\" \"16\"\n"
//                            + "			\"smoothing_groups\" \"0\"\n"
//                            + "		}\n"
//                            //-z
//                            + "		side\n"
//                            + "		{\n"
//                            + "			\"id\" \"" + (id++) + "\"\n"
//                            + "			\"plane\" \"(" + x + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (ys + y) + " " + (z) + ")\" \n"
//                            + "			\"material\" \"TOOLS/TOOLSSKYBOX\"\n"
//                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
//                            + "                 \"vaxis\" \"[0 -1 0 0] 0.25\"\n"
//                            + "			\"rotation\" \"0\"\n"
//                            + "			\"lightmapscale\" \"16\"\n"
//                            + "			\"smoothing_groups\" \"0\"\n"
//                            + "		}\n"
//                            //-x
//                            + "		side\n"
//                            + "		{\n"
//                            + "			\"id\" \"" + (id++) + "\"\n"
//                            + "			\"plane\" \"(" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ") (" + (x) + " " + (y) + " " + (z) + ")\" \n"
//                            + "			\"material\" \"TOOLS/TOOLSSKYBOX\"\n"
//                            + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
//                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
//                            + "			\"rotation\" \"0\"\n"
//                            + "			\"lightmapscale\" \"16\"\n"
//                            + "			\"smoothing_groups\" \"0\"\n"
//                            + "		}\n"
//                            //+x
//                            + "		side\n"
//                            + "		{\n"
//                            + "			\"id\" \"" + (id++) + "\"\n"
//                            + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (z) + ") (" + (xs + x) + " " + (y) + " " + (zs + z) + ")\" \n"
//                            + "			\"material\" \"TOOLS/TOOLSSKYBOX\"\n"
//                            + "			\"uaxis\" \"[0 1 0 0] 0.25\"\n" //new axis 
//                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
//                            + "			\"rotation\" \"0\"\n"
//                            + "			\"lightmapscale\" \"16\"\n"
//                            + "			\"smoothing_groups\" \"0\"\n"
//                            + "		}\n"
//                            //+y
//                            + "		side\n"
//                            + "		{\n"
//                            + "			\"id\" \"" + (id++) + "\"\n"
//                            + "			\"plane\" \"(" + (xs + x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (zs + z) + ") (" + (x) + " " + (ys + y) + " " + (z) + ")\" \n"
//                            + "			\"material\" \"TOOLS/TOOLSSKYBOX\"\n"
//                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
//                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
//                            + "			\"rotation\" \"0\"\n"
//                            + "			\"lightmapscale\" \"16\"\n"
//                            + "			\"smoothing_groups\" \"0\"\n"
//                            + "		}\n"
//                            //-y
//                            + "		side\n"
//                            + "		{\n"
//                            + "			\"id\" \"" + (id++) + "\"\n"
//                            + "			\"plane\" \"(" + (xs + x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (z) + ") (" + (x) + " " + (y) + " " + (zs + z) + ")\" \n"
//                            + "			\"material\" \"TOOLS/TOOLSSKYBOX\"\n"
//                            + "			\"uaxis\" \"[1 0 0 0] 0.25\"\n" //new axis
//                            + "                 \"vaxis\" \"[0 0 -1 0] 0.25\"\n"
//                            + "			\"rotation\" \"0\"\n"
//                            + "			\"lightmapscale\" \"16\"\n"
//                            + "			\"smoothing_groups\" \"0\"\n"
//                            + "		}\n"
//                            + "		editor\n"
//                            + "		{\n"
//                            + "			\"color\" \"0 146 143\"\n"
//                            + "			\"visgroupshown\" \"1\"\n"
//                            + "			\"visgroupautoshown\" \"1\"\n"
//                            + "		}\n"
//                            + "	}";
                }
            }
        }
    }
}
