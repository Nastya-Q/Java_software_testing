package qa.hometask.sandbox;

class MyFirstProgram {

    public static void main(String[] args) {
/*      //Implement via static function
        Point p1 = new Point (4, 6);
        Point p2 = new Point (10, 8);
        System.out.println("Distance via static function:");
        System.out.println("Distance between point 1 with coordinate (x;y) = (" + p1.x + "; " + p1.y + ")" +
                "and point2 with coordinate (x;y) = ("  + p2.x + "; " + p2.y + ")" + "equals " + distance(p1, p2) );*/

        //Implement via object method
        Point p = new Point (4, 6, 10, 8);
        System.out.println("Distance via object method");
        System.out.println("Distance between point 1 with coordinate (x1;y1) = (" + p.x1 + ";" + p.y1 + ")" +
                "and point2 with coordinate (x2;y2) = ("  + p.x2 + ";" + p.y2 + ")" + "equals " + p.distance());
    }

/*
    public static double distance(Point p1, Point p2) {
        double distance = Math.sqrt((Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y),2)));
        return distance;
    }
*/

}