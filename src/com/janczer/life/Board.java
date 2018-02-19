/*
 * MIT License
 *
 * Copyright (c) 2018 janczer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.janczer.life;

import java.util.HashMap;
import java.util.Map;

public class Board {

  private Map<Integer, Cell> cells;

  /**
   * Size of board
   */
  private Integer a;

  /**
   * Size of board
   */
  private Integer b;

  private StringBuilder board;

  /**
   * @param a Size of board
   * @param b Size of board
   * @param live Add live Cell to board: Cell(1, 1)
   */
  public Board(Integer a, Integer b, Cell... live) {
    cells = new HashMap<>();
    board = new StringBuilder();

    this.a = a;
    this.b = b;

    for (int i = 0; i < a * b; i++) {
      Integer x = i % a + 1;
      Integer y = i / a + 1;
      Cell c = new Cell(x, y);

      cells.put(i, c);
      board.append(i);
    }

    for (Cell cell : live) {
      Integer x = cell.getX();
      Integer y = cell.getY();
      cells.get(a * (y - 1) + x - 1).setLive(true);
    }

    for (int i = 0; i < a * b; i++) {
      Cell c = cells.get(i);
      Integer x = c.getX() - 1;
      Integer y = c.getY() - 1;

      Integer[] nn = new Integer[8];

      if (x - 1 >= 0) {
        nn[0] = a * y + x - 1; // left
      }
      if (x + 1 < a) {
        nn[1] = a * y + x + 1; // right
      }
      if (y - 1 >= 0) {
        nn[2] = a * (y - 1) + x; // down
      }
      if (y + 1 < b) {
        nn[3] = a * (y + 1) + x; // up
      }

      if (y - 1 >= 0 && x - 1 >= 0) {
        nn[4] = a * (y - 1) + x - 1; // left down
      }
      if (y + 1 < b && x - 1 >= 0) {
        nn[5] = a * (y + 1) + x - 1; // left up
      }

      if (y - 1 >= 0 && x + 1 < a) {
        nn[6] = a * (y - 1) + x + 1; // right down
      }
      if (y + 1 < b && x + 1 < a) {
        nn[7] = a * (y + 1) + x + 1; // right up
      }

      for (Integer n : nn) {
        if (n != null) {
          c.addNeighbors(cells.get(n));
        }
      }
    }
  }

  public void nextGeneration() {
    cells.forEach((i, c) -> c.checkStatus());
    cells.forEach((i, c) -> c.nextGeneration());
  }

  @Override
  public String toString() {
    cells.forEach((i, c) -> board.setCharAt(i, c.getLive() ? 'o' : ' '));

    StringBuilder sb = new StringBuilder();

    sb.append(getWrapperTop());
    for (int i = b - 1; i >= 0; i--) {
      sb.append("|");
      sb.append(board.substring(i * a, (i + 1) * a));
      sb.append("|");
      sb.append(System.lineSeparator());
    }
    sb.append(getWrapperTop());

    return sb.toString();
  }

  private String getWrapperTop() {
    return "+" + getCharTimes('-', a) + "+" + System.lineSeparator();
  }

  private String getCharTimes(char c, int n) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb.append(c);
    }

    return sb.toString();
  }
}
