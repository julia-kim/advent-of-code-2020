package days.day12;

public class Waypoint {
    private int x;
    private int y;

    public Waypoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveWaypoint(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void rotateWaypoint(String direction, int degrees) {
        // RULES OF ROTATION:
        // CLOCKWISE                COUNTERCLOCKWISE
        // 90°  (x,y)→(y,-x)        90°  (x,y)→(−y,x)
        // 180° (x,y)→(−x,−y)       180° (x,y)→(−x,−y)
        // 270° (x,y)→(-y,x)        270° (x,y)→(y,−x)

        if (direction.equals("L")) {
            switch (degrees) {
                case 90:
                    swap();
                    x *= -1;
                    break;
                case 180:
                    x *= -1;
                    y *= -1;
                    break;
                case 270:
                    swap();
                    y *= -1;
                    break;
            }
        } else if (direction.equals("R")) {
            switch (degrees) {
                case 90:
                    swap();
                    y *= -1;
                    break;
                case 180:
                    x *= -1;
                    y *= -1;
                    break;
                case 270:
                    swap();
                    x *= -1;
                    break;
            }
        }
    }

    private void swap() {
        int temp = x;
        x = y;
        y = temp;
    }
}
