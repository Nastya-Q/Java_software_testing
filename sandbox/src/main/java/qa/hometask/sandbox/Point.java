package qa.hometask.sandbox;

public class Point {
    public double x1, y1;
    public double x2, y2;

    public Point (double x1, double y1, double x2, double y2 ) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double distance() {
        double distance = Math.sqrt((Math.pow((x1 - x2), 2) + Math.pow((y1 - y2),2)));
        return distance;
    }
}
