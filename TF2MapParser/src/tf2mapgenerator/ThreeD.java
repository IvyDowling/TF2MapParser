package tf2mapgenerator;

public enum ThreeD {

    Zpos, Zneg, Xpos, Xneg, Ypos, Yneg;

    public static ThreeD interpret(String wall) {
        switch (wall) {
            case "+z":
            case "up":
                return Zpos;
            case "-z":
            case "down":
                return Zneg;
            case "+x":
            case "n":
                return Xpos;
            case "-x":
            case "s":
                return Xneg;
            case "+y":
            case "e":
                return Ypos;
            case "-y":
            case "w":
                return Yneg;
        }
        return null;
    }

    public ThreeD negate() {
        switch (this) {
            case Zpos:
                return Zneg;
            case Zneg:
                return Zpos;
            case Ypos:
                return Yneg;
            case Yneg:
                return Ypos;
            case Xpos:
                return Xneg;
            case Xneg:
                return Xpos;
        }
        return null;
    }
}
