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
      cells.get(calcNumberInRow(x - 1, y - 1)).setLive(true);
    }

    for (int i = 0; i < a * b; i++) {
      Cell c = cells.get(i);
      Integer x = c.getX() - 1;
      Integer y = c.getY() - 1;

      Integer[][] nn = {
          { 0,  1}, // up
          {-1,  0}, // left
          { 0, -1}, // down
          { 1,  0}, // right
          {-1,  1}, // up left
          { 1,  1}, // up right
          {-1, -1}, // down left
          { 1, -1}, // down right
      };

      for (Integer[] pos : nn) {
        Integer x2 = x + pos[0];
        Integer y2 = y + pos[1];

        y2 = y2 < 0 ? y2 + b : y2;
        Integer n = calcNumberInRow(x2, y2);
        c.addNeighbors(cells.get(n));
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

    String t = sb.toString();
    return t;
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

  private Integer calcNumberInRow(Integer x, Integer y) {
    return modulo(x, a) + modulo(y, b) * b;
  }

  private Integer modulo(Integer x, Integer m) {
    return ((x % m) + m) % m;
  }
}
