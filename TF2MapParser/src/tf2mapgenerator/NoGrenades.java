package tf2mapgenerator;

public class NoGrenades {

    Spire spire;

    public NoGrenades(int x, int y, int z, int l, int w, int h) {
        spire = new Spire(x, y, z, l, w, h);
    }
    
    public String getOutput(int id){
        return spire.getOutput(id, "func_nogrenades");
    }
    
    public void kill() {
        spire.kill();
    }

    public boolean isKilled() {
        return spire.isKilled();
    }

    public int getXs() {
        return spire.getXs();
    }

    public int getYs() {
        return spire.getYs();
    }

    public int getZs() {
        return spire.getZs();
    }

    public int getX() {
        return spire.getX();
    }

    public int getY() {
        return spire.getY();
    }

    public int getZ() {
        return spire.getZ();
    }
}
