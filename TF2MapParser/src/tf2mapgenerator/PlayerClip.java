package tf2mapgenerator;

public class PlayerClip extends Spire{

    public PlayerClip(int xcoord, int ycoord, int zcoord, int l, int w, int h){
        super(xcoord, ycoord, zcoord, l, w, h);
    }
    
    @Override
    public String getOutput(int id){
        return super.getOutput(id,"TOOLS/TOOLSPLAYERCLIP");
    }
}
