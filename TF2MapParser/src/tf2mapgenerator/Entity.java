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
public class Entity {

    private int x, y, z, ang;
    private String type;    //the kind of entity

    public Entity(int x, int y, int z, int angle, String ty) {
        type = ty;
        this.x = x;
        this.y = y;
        this.z = z;
        ang = angle;
    }

    public Entity getMirroredEntity(int xSky, int ySky, int xsSky, int ysSky) {
        return new Entity(xSky + (xsSky - (this.getX())), ySky + (ysSky - (this.getY())), this.getZ(), (ang + 180), type);
    }

    public String getOutput() {
        return "entity\n"
                + "{\n"
                + "	\"id\" \"999\"\n"
                + "	\"classname\" \" " + type + "\"\n"
                + "	\"angles\" \"0 " + ang + " 0 \"\n"
                + "	\"origin\" \"" + x + " " + y + " " + (z + 5) + "\"\n" // + 5 so you can put it on the surface declared
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
    public String getType(){
        return type;
    }

}
