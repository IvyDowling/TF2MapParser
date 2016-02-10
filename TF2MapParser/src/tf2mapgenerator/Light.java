package tf2mapgenerator;

public class Light implements Drawable {

    private int x, y, z, brightness;

    public Light(int lx, int ly, int lz, int bright) {
        x = lx;
        y = ly;
        z = lz;
        brightness = bright;
    }

    @Override
    public String getOutput(int id) {
        return "\nentity\n"
                + "{\n"
                + "	\"id\" \"30\"\n"
                + "	\"classname\" \"light\"\n"
                + "	\"_light\" \"255 255 255 " + brightness + "\"\n"
                + "	\"_lightHDR\" \"-1 -1 -1 1\"\n"
                + "	\"_lightscaleHDR\" \"1\"\n"
                + "	\"_quadratic_attn\" \"1\"\n"
                + "	\"origin\" \"" + (x) + " " + (y) + " " + (z) + "\"\n"
                + "	editor\n"
                + "	{\n"
                + "		\"color\" \"220 30 220\"\n"
                + "		\"visgroupshown\" \"1\"\n"
                + "		\"visgroupautoshown\" \"1\"\n"
                + "		\"logicalpos\" \"[0 3000]\"\n"
                + "	}\n"
                + "}";
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
    public int getXs() {
        return 0;
    }

    @Override
    public int getYs() {
        return 0;
    }

    @Override
    public int getZs() {
        return 0;
    }

    @Override
    public Type getType() {
        return Type.ENTITY;
    }

    public int getBrightness() {
        return brightness;
    }

    @Override
    public Drawable getMirror(int xSky, int ySky, int xsSky, int ysSky) {
        return new Light(xSky + (xsSky - (this.getX())), ySky + (ysSky - (this.getY())), this.getZ(), brightness);
    }

}
