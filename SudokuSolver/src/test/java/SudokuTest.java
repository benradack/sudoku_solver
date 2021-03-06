import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import java.util.Arrays;

public class SudokuTest {
    SudokuSolver solver = new SudokuSolver();

    @Test
    public void board1Test() {
        int[][] testBoard = {
                {4,0,0,0,0,7,6,3,8},
                {7,0,2,0,8,0,4,0,0},
                {3,0,0,5,1,0,0,2,0},
                {9,7,1,0,5,8,0,0,0},
                {0,6,0,0,2,0,7,5,0},
                {0,0,3,0,7,9,0,8,0},
                {0,3,0,9,0,0,2,0,4},
                {0,5,9,7,0,1,8,0,0},
                {0,0,0,8,0,2,0,0,1}};
        int[][] solution = {
                {4,1,5,2,9,7,6,3,8},
                {7,9,2,3,8,6,4,1,5},
                {3,8,6,5,1,4,9,2,7},
                {9,7,1,6,5,8,3,4,2},
                {8,6,4,1,2,3,7,5,9},
                {5,2,3,4,7,9,1,8,6},
                {1,3,8,9,6,5,2,7,4},
                {2,5,9,7,4,1,8,6,3},
                {6,4,7,8,3,2,5,9,1}};
        assertTrue(Arrays.deepEquals(solver.solve(testBoard), solution));
    }
    @Test
    public void board2Test() {
        int[][] testBoard = {
                {0,0,0,0,0,0,0,0,0},
                {0,6,5,3,0,0,0,0,4},
                {4,0,0,8,5,0,0,0,0},
                {0,4,0,0,0,0,6,0,0},
                {0,0,6,2,4,3,0,0,5},
                {1,0,7,0,0,0,0,2,3},
                {0,0,0,1,9,0,2,0,0},
                {0,0,0,0,3,0,0,0,9},
                {0,0,1,0,0,6,0,8,0}};
        int[][] solution = {
                {7,3,2,9,6,4,8,5,1},
                {8,6,5,3,1,2,9,7,4},
                {4,1,9,8,5,7,3,6,2},
                {2,4,3,5,7,1,6,9,8},
                {9,8,6,2,4,3,7,1,5},
                {1,5,7,6,8,9,4,2,3},
                {5,7,4,1,9,8,2,3,6},
                {6,2,8,7,3,5,1,4,9},
                {3,9,1,4,2,6,5,8,7}};
        assertTrue(Arrays.deepEquals(solver.solve(testBoard), solution));
    }
    @Test
    public void board3Test() {
        int[][] testBoard = {
                {0,0,0,0,0,2,1,0,4},
                {0,0,8,0,0,1,0,0,3},
                {5,0,0,0,6,0,0,9,0},
                {0,9,0,0,8,0,0,4,6},
                {6,0,0,7,0,0,0,0,0},
                {1,0,0,0,0,0,0,8,0},
                {0,3,7,2,0,0,0,1,9},
                {0,0,0,0,0,0,0,3,0},
                {0,0,0,0,9,0,0,0,0}};
        int[][] solution = {
                {3,6,9,8,7,2,1,5,4},
                {7,4,8,9,5,1,2,6,3},
                {5,1,2,4,6,3,8,9,7},
                {2,9,3,1,8,5,7,4,6},
                {6,8,5,7,3,4,9,2,1},
                {1,7,4,6,2,9,3,8,5},
                {8,3,7,2,4,6,5,1,9},
                {9,2,6,5,1,7,4,3,8},
                {4,5,1,3,9,8,6,7,2}};
        assertTrue(Arrays.deepEquals(solver.solve(testBoard), solution));
    }
}
