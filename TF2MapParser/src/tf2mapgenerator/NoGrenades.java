package tf2mapgenerator;

public class NoGrenades implements Drawable {

    Spire spire;

    public NoGrenades(int x, int y, int z, int l, int w, int h) {
        spire = new Spire(x, y, z, l, w, h);
    }

    @Override
    public String getOutput(int id) {
        return spire.getOutput(id, "func_nogrenades");
    }

    @Override
    public Drawable getMirror(int xSky, int ySky, int xsSky, int ysSky) {
        return spire.getMirror(xSky, ySky, xsSky, ysSky);
    }

    public void kill() {
        spire.kill();
    }

    public boolean isKilled() {
        return spire.isKilled();
    }

    @Override
    public int getXs() {
        return spire.getXs();
    }

    @Override
    public int getYs() {
        return spire.getYs();
    }

    @Override
    public int getZs() {
        return spire.getZs();
    }

    @Override
    public int getX() {
        return spire.getX();
    }

    @Override
    public int getY() {
        return spire.getY();
    }

    @Override
    public int getZ() {
        return spire.getZ();
    }

    @Override
    public Type getType() {
        return Type.SPIRE;
    }
}
