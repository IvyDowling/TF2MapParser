package tf2mapgenerator;

import java.util.ArrayList;
import java.util.List;

public class SpawnRoom {

    //Last spawns are bigger
    private int x, y, z;
    private Frame frame;
    private Room room;
    private List<Entity> entities;

    public SpawnRoom(int xcoord, int ycoord, int zcoord, boolean last) {
        x = xcoord;
        y = ycoord;
        z = zcoord;
        entities = new ArrayList<>();
        //these need the sizes of the rooms to continue
        if (last) {
            //Welcome to the last spawn
            //frame = new Frame(x,y,z,);
            //room = new Room(x,y,z,);
            //and then a bunch of entities
        } else {
            //This is a forward spawn
            //frame = new Frame(x,y,z,);
            //room = new Room(x,y,z,);
            //and then the entities
        }
    }

    public String getFrameOuptut(int id) {
        return frame.getOutput(id) + room.getOutput(id);
    }

    public String getEntityOutput(int id) {
        return "";
    }

}
