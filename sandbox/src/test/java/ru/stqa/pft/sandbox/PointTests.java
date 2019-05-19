package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {
    Point p = new Point(2, 3, 6, 6);
    Assert.assertEquals(p.distance(), 5.0);
  }
}
