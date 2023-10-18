
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void saveArrays(String[] arrSavedPath){
        System.out.println(Arrays.toString(arrSavedPath)+"new light");
    }
    public static void main(String[] args) {
        Scanner sUserInput = new Scanner(System.in);

        // Ask the user to input the top right corner coordinates
        System.out.print("Enter the top right corner coordinates (x,y): ");
        String sCoordinates = sUserInput.next();
        sCoordinates.split(",");
        String[] arrCordinates;
        arrCordinates = sCoordinates.split(",");
        int iTopRightX = Integer.parseInt(arrCordinates[0]);
        int iTopRightY = Integer.parseInt(arrCordinates[1]);
            // Create the grid

        RoverGrid grid = new RoverGrid(iTopRightY + 1, iTopRightX + 1); // Adding 1 to include (0,0)


        System.out.print("Enter the number of drones you want to control: ");
        int numDrones = sUserInput.nextInt();

        for (int i = 1; i <= numDrones; i++) {
            System.out.print("Enter starting position for Drone " + i + " (e.g., '0,2'): ");
            String sStartingPosition = sUserInput.next();
            System.out.println("Please endter the commands for Drone" + i + "(e.g., 'NNEEWWSSS')");
            String sCommands = sUserInput.next().toUpperCase();
            char[] sCommandArray = sCommands.toCharArray();


            try {
                //sStartingPosition = sStartingPosition.replaceAll("[^0-9,]", "");
                String[] coordinates = sStartingPosition.split(",");
                int startX = Integer.parseInt(coordinates[0]);
                int startY = Integer.parseInt(coordinates[1]);
                grid.followMovments(sCommandArray,startX,startY);

                if (startX >= 0 && startX <= iTopRightY && startY >= 0 && startY <= iTopRightX) {
                    grid.setCoordinate(startX, startY, 'X');
                } else {
                    System.out.println("Invalid starting position. Please enter coordinates within the grid's dimensions.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input format for starting position. Please use the format '0,2'.");
                return;
            }
        }

        grid.compareAndDisplayCommonPaths();

    }
}