package tf2mapgenerator;

public enum Type {

    SPIRE, RAMP, INCLINE, ENTITY;

    @Override
    public String toString() {
        switch (this) {
            case SPIRE:
                return "spire";
            case RAMP:
                return "ramp";
            case INCLINE:
                return "incline";
            case ENTITY:
                return "entity";
            default:
                return this.toString();
        }
    }
}
