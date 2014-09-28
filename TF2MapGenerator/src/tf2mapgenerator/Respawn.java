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
public class Respawn {

    private int x, y, z, ang;

    public Respawn(int x, int y, int z, int angle) {
        this.x = x;
        this.y = y;
        this.z = z;
        ang = angle;
    }
    
    public Respawn getMirroredRespawn(int xSky, int ySky, int xsSky, int ysSky){
        return new Respawn(xSky + (xsSky - (this.getX())), ySky + (ysSky - (this.getY())), this.getZ(), (ang + 180));
    }

    public String getOutput() {
        return "entity\n"
                + "{\n"
                + "	\"id\" \"10\"\n"
                + "	\"classname\" \"info_player_teamspawn\"\n"
                + "	\"angles\" \"0 "+ ang +" 0 \"\n"
                + "	\"origin\" \"" + x + " " + y + " " + (z + 4) + "\"\n"   // + 4 so you can put it on the surface declared
                + "	editor\n"
                + "	{\n"
                + "		\"color\" \"220 30 220\"\n"
                + "		\"visgroupshown\" \"1\"\n"
                + "		\"visgroupautoshown\" \"1\"\n"
                + "		\"logicalpos\" \"[0 0]\"\n"
                + "	}\n"
                + "}";
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

}
