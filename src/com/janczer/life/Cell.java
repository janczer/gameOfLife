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

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cell {

  private Set<Cell> neighbors = new HashSet<>();

  /**
   * Position of Cell
   */
  private Integer x;
  private Integer y;

  private Boolean live = false;

  private Boolean liveNextGeneration = false;

  public Cell() {
  }

  public Cell(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }

  public Boolean addNeighbors(Cell c) {
    return neighbors.add(c);
  }

  public void setLive(Boolean live) {
    this.live = live;
  }

  public Boolean getLive() {
    return live;
  }

  public Boolean getLiveNextGeneration() {
    return liveNextGeneration;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  public Integer getX() {
    return x;
  }

  public Integer getY() {
    return y;
  }

  public void checkStatus() {
    Integer count = 0;
    for (Cell c : neighbors) {
      count += c.getLive() ? 1 : 0;
    }

    if (count < 2) {
      liveNextGeneration = false;
    }

    if (live && (count == 2 || count == 3)) {
      liveNextGeneration = true;
    }

    if (count > 3) {
      liveNextGeneration = false;
    }

    if (!live && count == 3) {
      liveNextGeneration = true;
    }
  }

  public void nextGeneration() {
    live = liveNextGeneration;
    liveNextGeneration = false;
  }
}
