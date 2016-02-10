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
public class Entity implements Drawable {

    private int x, y, z, ang;
    private String entityCode;    //the kind of entity

    public Entity(int x, int y, int z, int angle, String ty) {
        entityCode = ty;
        this.x = x;
        this.y = y;
        this.z = z;
        ang = angle;
    }

    @Override
    public Entity getMirror(int xSky, int ySky, int xsSky, int ysSky) {
        return new Entity(xSky + (xsSky - (this.getX())), ySky + (ysSky - (this.getY())), this.getZ(), (ang + 180), entityCode);
    }

    @Override
    public String getOutput(int id) {
        return "entity\n"
                + "{\n"
                + "	\"id\" \"999\"\n"
                + "	\"classname\" \" " + entityCode + "\"\n"
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
    public Type getType() {
        return Type.ENTITY;
    }

    public String getEntityCode() {
        return entityCode;
    }

}
