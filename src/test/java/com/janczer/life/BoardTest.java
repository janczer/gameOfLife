package com.janczer.life;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

  @Test
  public void shouldDrawEmptyBoard() {
    Board b = new Board(3, 3);
    String test = "+---+" + System.lineSeparator() +
                  "|   |" + System.lineSeparator() +
                  "|   |" + System.lineSeparator() +
                  "|   |" + System.lineSeparator() +
                  "+---+" + System.lineSeparator();

    assertEquals(test, b.toString());
  }

  @Test
  public void shouldOneLiveCellDie() {
    Board b = new Board(3, 3, new Cell(2, 2));
    String test = "+---+" + System.lineSeparator() +
                  "|   |" + System.lineSeparator() +
                  "| o |" + System.lineSeparator() +
                  "|   |" + System.lineSeparator() +
                  "+---+" + System.lineSeparator();

    assertEquals(test, b.toString());

    b.nextGeneration();

    test = "+---+" + System.lineSeparator() +
           "|   |" + System.lineSeparator() +
           "|   |" + System.lineSeparator() +
           "|   |" + System.lineSeparator() +
           "+---+" + System.lineSeparator();

    assertEquals(test, b.toString());
  }

  @Test
  public void testTwoSteps() {
    String step1 =
        "+-----+" + System.lineSeparator() +
        "|     |" + System.lineSeparator() +
        "|     |" + System.lineSeparator() +
        "|o    |" + System.lineSeparator() +
        "|o    |" + System.lineSeparator() +
        "|o    |" + System.lineSeparator() +
        "+-----+" + System.lineSeparator();

    String step2 =
        "+-----+" + System.lineSeparator() +
        "|     |" + System.lineSeparator() +
        "|     |" + System.lineSeparator() +
        "|     |" + System.lineSeparator() +
        "|oo  o|" + System.lineSeparator() +
        "|     |" + System.lineSeparator() +
        "+-----+" + System.lineSeparator();

    Board b = new Board(5, 5, new Cell(1, 2), new Cell(1, 1), new Cell(1, 3));

    assertEquals(step1, b.toString());
    b.nextGeneration();
    assertEquals(step2, b.toString());
    b.nextGeneration();
    assertEquals(step1, b.toString());
  }
}