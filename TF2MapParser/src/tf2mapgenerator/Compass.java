package tf2mapgenerator;

public enum Compass {

    NORTH, SOUTH, EAST, WEST;

    public static Compass interpret(String s) {
        switch (s) {
            case "+x":
            case "n":
                return NORTH;
            case "-x":
            case "s":
                return SOUTH;
            case "+y":
            case "e":
                return EAST;
            case "-y":
            case "w":
                return WEST;
        }
        return null;
    }

    public Compass invert180() {
        switch (this) {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
        }
        return null;
    }
}
