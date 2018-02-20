package com.janczer.life;

import static org.junit.Assert.*;

import java.util.Objects;
import org.junit.Before;
import org.junit.Test;

public class CellTest {

  private Integer x;
  private Integer y;

  private Cell c;

  @Before
  public void setUp() {
    x = 1;
    y = 1;
    c = new Cell(x, y);
  }

  @Test
  public void defaultParams() {
    // default params
    assertEquals(x, c.getX());
    assertEquals(y, c.getY());
    assertFalse(c.getLive());
    assertFalse(c.getLiveNextGeneration());
  }

  @Test
  public void testHash() {
    assertEquals(Objects.hash(x, y), c.hashCode());
  }

  @Test
  public void cellShouldDieWithOutNeighbours() {
    c.setLive(true);
    c.checkStatus();
    c.nextGeneration();
    assertFalse(c.getLive());
  }

  @Test
  public void cellShouldDieWithAllLiveNeighbours() {
    c.setLive(true);
    Cell n = new Cell(1, 1);
    n.setLive(true);
    for (int i = 0; i < 8; i++) {
      c.addNeighbors(n);
    }

    c.checkStatus();
    c.nextGeneration();
    assertFalse(c.getLive());
  }

  @Test
  public void cellShouldDieWithAllDieNeighbours() {
    c.setLive(true);
    for (int i = 0; i < 8; i++) {
      Cell n = new Cell(i, 1);
      c.addNeighbors(n);
    }

    c.checkStatus();
    c.nextGeneration();
    assertFalse(c.getLive());
  }

  @Test
  public void cellShouldLiveWith3LiveNeighbours() {
    c.setLive(true);
    for (int i = 0; i < 3; i++) {
      Cell n = new Cell(i, 1);
      n.setLive(true);
      c.addNeighbors(n);
    }

    c.checkStatus();
    c.nextGeneration();
    assertTrue(c.getLive());
  }

  @Test
  public void cellShouldLiveWith2LiveNeighbours() {
    c.setLive(true);
    for (int i = 0; i < 2; i++) {
      Cell n = new Cell(i, 1);
      n.setLive(true);
      c.addNeighbors(n);
    }

    c.checkStatus();
    c.nextGeneration();
    assertTrue(c.getLive());
  }

  @Test
  public void cellShouldDieWith4LiveNeighbours() {
    c.setLive(true);
    for (int i = 0; i < 4; i++) {
      Cell n = new Cell(1, 1);
      n.setLive(true);
      c.addNeighbors(n);
    }

    c.checkStatus();
    c.nextGeneration();
    assertFalse(c.getLive());
  }

  @Test
  public void cellShouldBornWith3LiveNeighbours() {
    for (int i = 0; i < 3; i++) {
      Cell n = new Cell(i, 1);
      n.setLive(true);
      c.addNeighbors(n);
    }

    c.checkStatus();
    c.nextGeneration();
    assertTrue(c.getLive());
  }
}