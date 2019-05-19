package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;


  public Point(double x1, double y1) {
    this.x = x;
    this.y = y;

  }

  public double distance(Point p2){
    return Math.sqrt((this.x - x2) * (this.x - x2) + (this.y - y2) * (this.y - y2));

  }

}

