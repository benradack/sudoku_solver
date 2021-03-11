import java.util.Arrays;

/**
 * Simple Java program to solve legal sudoku boards through backtracking.
 */
public class SudokuSolver {
    /**
     * Input a sudoku board as a 9x9 matrix. Print the input, solve the board, and print solution.
     * @param args Takes no arguments
     */
    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();
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

        System.out.println(boardToString(solver.solve(testBoard)));
    }

    private int colIndex = 0;
    private int rowIndex = 0;

    /**
     * Method to solve a sudoku board. The lowest value without conflicts is added. If there are no
     * values that can be added, backtrack until changing a previous value will produce legal values
     * in the original spot.
     * @param input Input board
     * @return Solved Sudoku board
     */
    public int[][] solve(int[][] input) {
        int[][] solution = new int[9][9];
        for(int i = 0; i < 9; i++) {
            solution[i] = Arrays.copyOf(input[i], input[i].length);
        }

        // Loop through the rows (y-axis) of the board
        while(rowIndex < 9) {
            // Loop through the columns (x-axis) of the board
            while(colIndex < 9) {
                // Only add a value when the cell isn't pre-filled
                if(input[rowIndex][colIndex] == 0) {
                    // Loop through 1 to 9
                    for(int i = 1; i < 10; i++) {
                        // Set cell to i when there are no conflicts
                        if(isAllowed(solution, colIndex, rowIndex, i)) {
                            solution[rowIndex][colIndex] = i;
                            break;
                        }
                    }
                    // If no value was found, backtrack
                    if(solution[rowIndex][colIndex] == 0) {
                        backtrack(input, solution);
                    }
                }
                // Move to next column position
                colIndex++;
            }
            // If the end of the row is reached, reset colIndex and move to next row
            rowIndex++;
            colIndex = 0;
        }

        rowIndex = 0;
        colIndex = 0;
        return solution;
    }
    /**
     * Method to backtrack through a sudoku board. Traverse through the board in reverse-order until
     * a value can be changed.
     * @param input Input board
     * @param solution Solution board
     */
    private void backtrack(int[][] input, int[][] solution) {
        // Start at the position before the issue cell
        colIndex--;
        // Loop until the beginning of the board
        while(rowIndex >= 0) {
            while(colIndex >= 0) {
                // Check that the cell isn't pre-filled
                if(input[rowIndex][colIndex] == 0) {
                    // Store cell value and set to 0
                    int val = solution[rowIndex][colIndex];
                    solution[rowIndex][colIndex] = 0;
                    // Look for a legal candidate greater than the previous value
                    for(int i = val; i < 10; i++) {
                        if(i > val && isAllowed(solution, colIndex, rowIndex, i)) {
                            solution[rowIndex][colIndex] = i;
                            return;
                        }
                    }
                }
                // If no viable candidate is found, move to previous cell
                colIndex--;
            }
            // If beginning of row is reached, move to end of previous row
            rowIndex--;
            colIndex = 8;
        }
    }
    /**
     * Wrapper method to check whether there are conflicts for the value in a cell.
     * @param solution Solution board
     * @param x X-coordinate of cell
     * @param y Y-coordinate of cell
     * @param val Value to be checked
     * @return There are no conflicts for the value at the cell
     */
    private boolean isAllowed(int[][] solution, int x, int y, int val) {
        return checkRow(solution, y, val) && checkCol(solution, x, val) && checkSquare(solution, x, y, val);
    }
    /**
     * Method to check whether there are conflicts for the value in the row.
     * @param solution Solution board
     * @param y Row to be checked
     * @param val Value to be checked
     * @return There are no conflicts for the value in the row
     */
    private boolean checkRow(int[][] solution, int y, int val) {
        // Check each cell in the row to see if val already exists
        for(int i = 0; i < 9; i++) {
            if(solution[y][i] == val) return false;
        }
        return true;
    }
    /**
     * Method to check whether there are conflicts for the value in the column.
     * @param solution Solution board
     * @param x Column to be checked
     * @param val Value to be checked
     * @return There are no conflicts for the value in the column
     */
    private boolean checkCol(int[][] solution, int x, int val) {
        // Check each cell in the column to see if val already exists
        for(int i = 0; i < 9; i++) {
            if(solution[i][x] == val) return false;
        }
        return true;
    }
    /**
     * Method to check there are conflicts for the value in the 3x3 subgrid.
     * @param solution Solution board
     * @param x X-coordinate of cell
     * @param y Y-coordinate of cell
     * @param val Value to be checked
     * @return There areno conflicts for the value in the subgrid
     */
    private boolean checkSquare(int[][] solution, int x, int y, int val) {
        int xStart, yStart;
        // Find the first x-coordinate of the 3x3 subgrid that the cell is in
        if(x < 3) xStart = 0;
        else if(x < 6) xStart = 3;
        else xStart = 6;

        // Find the first y-coordinate of the 3x3 subgrid that the cell is in
        if(y < 3) yStart = 0;
        else if(y < 6) yStart = 3;
        else yStart = 6;

        // Check each cell in the subgrid to see if val already exists
        for(int i = yStart; i < yStart+3; i++) {
            for(int j = xStart; j < xStart+3; j++) {
                if(solution[i][j] == val) return false;
            }
        }
        return true;
    }
    /**
     * Static method to turn the sudoku board into a String.
     * @param board Sudoku board to turn into a String
     * @return String representation of the sudoku board
     */
    public static String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        // Loop through each cell in the board and append to sb
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                sb.append(board[i][j]);

                // Add divider to separate subgrids vertically
                if(j == 2 || j == 5) {
                    sb.append("|");
                } else if(j == 8) {
                    sb.append("\n");
                }
            }
            // Add divider row to separate subgrids horizontally
            if(i == 2 || i == 5) {
                sb.append("-----------\n");
            }
        }

        return sb.toString();
    }
}
