package me.elmajni.aspects;


class Point {
    public double x;
    public double y;
}

public class MetricsCalculator
{
    public double calculer(Point p1, Point p2) {
        return ((p2.x + p1.x)) * 1.5;
    }

}
