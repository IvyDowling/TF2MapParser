/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tf2mapgenerator;

/**
 *
 * @author IV
 */
public class Light {

    private int xl, yl, zl, brightness;

    public Light(int lx, int ly, int lz, int bright) {
        xl = lx;
        yl = ly;
        zl = lz;
        brightness = bright;
    }

    public String getOutput() {
        return "\nentity\n"
                + "{\n"
                + "	\"id\" \"30\"\n"
                + "	\"classname\" \"light\"\n"
                + "	\"_light\" \"255 255 255 " + brightness + "\"\n"
                + "	\"_lightHDR\" \"-1 -1 -1 1\"\n"
                + "	\"_lightscaleHDR\" \"1\"\n"
                + "	\"_quadratic_attn\" \"1\"\n"
                + "	\"origin\" \"" + (xl) + " " + (yl) + " " + (zl) + "\"\n"
                + "	editor\n"
                + "	{\n"
                + "		\"color\" \"220 30 220\"\n"
                + "		\"visgroupshown\" \"1\"\n"
                + "		\"visgroupautoshown\" \"1\"\n"
                + "		\"logicalpos\" \"[0 3000]\"\n"
                + "	}\n"
                + "}";
    }

    public int getXl() {
        return xl;
    }

    public int getYl() {
        return yl;
    }

    public int getZl() {
        return zl;
    }

    public int getBrightness() {
        return brightness;
    }

}
