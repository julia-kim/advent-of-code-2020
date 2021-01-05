package days.day12;

public enum Direction {
    // cardinal directions: EAST, WEST, NORTH, SOUTH
    E(90), W(270), N(0), S(180);

    private int angle;

    private Direction(final int angle) {
        this.angle = angle;
    }

    public int getAngle() {
        return angle;
    }

    public Direction getDirection(int a) {
        for (Direction d : Direction.values()) {
            if (d.getAngle() == a) {
                return d;
            }
        }
        return null;
    }

    public Direction toLeft(int deg) {
        int a = angle - deg;
        if (a < 0) {
            a += 360;
        }
        return getDirection(a);
    }

    public Direction toRight(int deg) {
        int a = (angle + deg) % 360;
        return getDirection(a);
    }
}
