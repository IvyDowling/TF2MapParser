package tf2mapgenerator;

public interface Drawable {

    public String getOutput(int id);
    
    public Type getType();
    
    public Drawable getMirror(int xSky, int ySky, int xsSky, int ysSky);

    public int getXs();

    public int getYs();

    public int getZs();

    public int getX();

    public int getY();

    public int getZ();
}
