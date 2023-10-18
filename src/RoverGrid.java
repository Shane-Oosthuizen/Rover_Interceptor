import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoverGrid {
    private int rows;
    private int columns;
    private char[][] grid;

    private List<String[]> dronePaths = new ArrayList<>();

    public RoverGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new char[rows][columns];
        initializeRoverGrid();
    }

    private void initializeRoverGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = '.';
            }
        }
    }

    public void setCoordinate(int row, int col, char value) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            grid[col][row] = value;
        }
    }

    public void followMovments(char[] instructions, int iStartX, int iStartY) {
        int currentRow = iStartX;
        int currentCol = iStartY;
        String sPath = currentRow + "," + currentCol;
        String[] arrPath = new String[instructions.length + 1]; // add +1 for saving the final position
        int iInstructionNumber = 0;

        for (int i = 0; i < instructions.length; i++) {
            char c = instructions[i];
            char previousDirection = (i > 0) ? instructions[i - 1] : '\0'; // Initialize with a non-direction character

            if (c == 'N') {
                if (previousDirection == 'E' || previousDirection == 'W') {
                    setCoordinate(currentRow, currentCol, '+');
                } else {
                    setCoordinate(currentRow, currentCol, '|');
                }
                sPath = currentRow + "," + currentCol;
                arrPath[iInstructionNumber] = sPath;
                iInstructionNumber++;
                currentCol++;
            } else if (c == 'S') {
                if (previousDirection == 'E' || previousDirection == 'W') {
                    setCoordinate(currentRow, currentCol, '+');
                } else {
                    setCoordinate(currentRow, currentCol, '|');
                }
                sPath = currentRow + "," + currentCol;
                arrPath[iInstructionNumber] = sPath;
                iInstructionNumber++;
                currentCol--;
            } else if (c == 'E') {
                if (previousDirection == 'N' || previousDirection == 'S') {
                    setCoordinate(currentRow, currentCol, '+');
                } else {
                    setCoordinate(currentRow, currentCol, '-');
                }
                sPath = currentRow + "," + currentCol;
                arrPath[iInstructionNumber] = sPath;
                iInstructionNumber++;
                currentRow++;
            } else if (c == 'W') {
                if (previousDirection == 'N' || previousDirection == 'S') {
                    setCoordinate(currentRow, currentCol, '+');
                } else {
                    setCoordinate(currentRow, currentCol, '-');
                }
                sPath = currentRow + "," + currentCol;
                arrPath[iInstructionNumber] = sPath;
                iInstructionNumber++;
                currentRow--;
            }
        }

        // Mark the final position with '0'
        setCoordinate(currentRow, currentCol, '0');
        saveArrays(arrPath);
    }

    public void saveArrays(String[] arrDronePath){
        dronePaths.add(arrDronePath);
    }

    public void compareAndDisplayCommonPaths() {
        for (int i = 0; i < dronePaths.size() - 1; i++) {
            String[] path1 = dronePaths.get(i);
            for (int j = i + 1; j < dronePaths.size(); j++) {
                String[] path2 = dronePaths.get(j);
                // Compare the paths for drones using my array list to find intercept points
                for (String coordinate1 : path1) {
                    if (coordinate1 != null && Arrays.asList(path2).contains(coordinate1)) {
                        System.out.println("Intersecting points: " + coordinate1);
                        String[] coordinates = coordinate1.split(",");
                        int iCommonX = Integer.parseInt(coordinates[0]);
                        int iCommonY = Integer.parseInt(coordinates[1]);
                        setCoordinate(iCommonX, iCommonY, '*'); // used to set all intersections with a *
                    }
                }
            }
        }

        // Print the grid with the * added for intersections.
        displayRoverGrid();
    }

    // creating the grid to display using -1 to offset the array to get the 0,0 to be at the bottom left opposed from the top left
    public void displayRoverGrid() {
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < columns; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
