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

import com.janczer.life.*;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    Integer a = 10;
    Integer b = 10;

    Board board = new Board(a, b,
        new Cell(5, 5),
        new Cell(5, 7),
        new Cell(5, 8),
        new Cell(4, 6),
        new Cell(6, 6),
        new Cell(5, 4)
    );

    for (int i = 0; i < 50; i++) {
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.printf("Step %d\n", i+1);
      System.out.println(board);
      board.nextGeneration();
      TimeUnit.MILLISECONDS.sleep(150);
    }
  }
}
