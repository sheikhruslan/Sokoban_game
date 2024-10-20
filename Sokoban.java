import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Sokoban {

    public static final char UP = 'W';
    public static final char DOWN = 'S';
    public static final char LEFT = 'A';
    public static final char RIGHT = 'D';
    public static char PLAYER = 'o';
    public static final char BOX = '@';
    public static final char WALL = '#';
    public static final char GOAL = '.';
    public static final char BOXONGOAL = '%';

    public static void main(String[] args) {
        new Sokoban().runApp();
    }
 
    public void runApp() {

        String mapfile = "map1.txt"; //the map file that is being read
        char[][] map = readmap(mapfile); //reading the map file
        char[][] oldMap = readmap(mapfile); //reading an original unaltered version of the map

        if (map == null) { //check if the map exists, if it does not then it prints "Map file not found"
            System.out.println("Map file not found");
            return;
        }
        int[] start = findPlayer(map); //finding the player's location on the map
        if (start.length == 0) { //checks if the length of the start array is equal to 0, which means that the player's location was not found
            System.out.println("Player not found");
            return;
        }
        int row = start[0];
        int col = start[1];
        while (!gameOver(map)) { //essentially means while the game is NOT over i.e. still going on
            printMap(map);
            System.out.println("\nPlease enter a move (WASD): ");
            char input = readValidInput(); //reads the player's input (the method checks if it is valid and returns the input if it is)
            if (input == 'q')  //if the input is q then the Quit game command is observed, as stated in the code
                break;
            if (input == 'r') {  //if the input is r then the Restart game command is observed, as stated in the code
                map = readmap(mapfile);
                row = start[0];    //player's position is reset to its original point
                col = start[1];
                continue;
            }
            if (input == 'h') { //if the input is h then the Help Menu method is observed, as stated in the code
                printHelp();
            }
            if (!isValid(map, row, col, input)) //check for a valid move
                continue;
            movePlayer(map, row, col, input); //method to move the player

            fixMap(map, oldMap);  //fix the map by updating certain elements based on the contents of another map

            int[] newPos = findPlayer(map); // locate and store the player's new position
            row = newPos[0];
            col = newPos[1];

        }
        System.out.println("Bye!");
    }
    
    public void printHelp() {
        System.out.println("Sokoban Help:");
        System.out.println("Help Menu: h");
        System.out.println();
        System.out.println("Controls:");
        System.out.println("Move up: W");
        System.out.println("Move down: S");
        System.out.println("Move left: A");
        System.out.println("Move right: D");
        System.out.println();
        System.out.println("Map:");
        System.out.println("Player: o");
        System.out.println("Box: @");
        System.out.println("Wall: #");
        System.out.println("Goal: .");
        System.out.println("Box on Goal: %");
        System.out.println();
        System.out.println("Restart: r");
        System.out.println("Quit: q");

    }
   
    public char readValidInput() {

        Scanner inp = new Scanner(System.in);
        String input = inp.nextLine();
        char firstChar = input.charAt(0);

        char[] validInputs = {UP, DOWN, LEFT, RIGHT, 'q', 'r', 'h'};

        boolean validInput = false;

        while (!validInput) {
            for (int i = 0; i < validInputs.length; i++) {
                if (firstChar == validInputs[i] && input.length() == 1) {
                    validInput = true;
                    break;
                }
            }

            if (!validInput) {
                System.out.println("Invalid input. Please enter a valid character.");
                input = inp.nextLine();
                firstChar = input.charAt(0);
            }
        }

        return firstChar;
    }

    
    public void fixMap(char[][] updated, char[][] base) {

        char[][] tempMap = new char[updated.length][];

        for (int i = 0; i < updated.length; i++) {
            tempMap[i] = new char[updated[i].length];

            for (int j = 0; j < updated[i].length; j++) {

                if (base[i][j] == GOAL && updated[i][j] == ' ') {
                    tempMap[i][j] = base[i][j];
                } else {
                    tempMap[i][j] = updated[i][j];
                }

            }
        }

        for (int i = 0; i < updated.length; i++) {
            for (int j = 0; j < updated[i].length; j++) {
                updated[i][j] = tempMap[i][j];
            }
        }

    }

    public void moveBox(char[][] map, int row, int col, char direction) {

        switch (direction) {
            case UP:
                if (map[row - 1][col] == GOAL){
                    map[row - 1][col] = BOXONGOAL;
                } else {
                    map[row - 1][col] = BOX;
                }
                break;

            case DOWN:
                if (map[row + 1][col] == GOAL){
                    map[row + 1][col] = BOXONGOAL;
                } else {
                    map[row + 1][col] = BOX;
                }
                break;

            case LEFT:
                if (map[row][col - 1] == GOAL){
                    map[row][col - 1] = BOXONGOAL;
                } else {
                    map[row][col - 1] = BOX;
                }
                break;

            case RIGHT:
                if (map[row][col + 1] == GOAL){
                    map[row][col + 1] = BOXONGOAL;
                } else {
                    map[row][col + 1] = BOX;
                }
                break;
        }

        map[row][col] = ' ';

    }
    
    public void movePlayer(char[][] map, int row, int col, char direction) {

        switch (direction){

            case UP:

                if (map[row - 1][col] == BOX) moveBox(map, row - 1, col, direction);
                if (map[row - 1][col] == BOXONGOAL) moveBox(map, row - 1, col, direction);
                map[row - 1][col] = PLAYER;

                break;

            case DOWN:

                if (map[row + 1][col] == BOX) moveBox(map, row + 1, col, direction);
                if (map[row + 1][col] == BOXONGOAL) moveBox(map, row + 1, col, direction);
                map[row + 1][col] = PLAYER;

                break;

            case LEFT:

                if (map[row][col - 1] == BOX) moveBox(map, row, col - 1, direction);
                if (map[row][col - 1] == BOXONGOAL) moveBox(map, row, col - 1, direction);
                map[row][col - 1] = PLAYER;

                break;

            case RIGHT:

                if (map[row][col + 1] == BOX) moveBox(map, row, col + 1, direction);
                if (map[row][col + 1] == BOXONGOAL) moveBox(map, row, col + 1, direction);
                map[row][col + 1] = PLAYER;

                break;

        }

        map[row][col] = ' ';

    }

    public boolean gameOver(char[][] map) {

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){

                if (map[i][j] == GOAL){
                    return false;
                }

            }
        }
        return true;

    }
    
    public int numberOfRows(String fileName) {

        int rows = 0;

        try {

            File inputFile = new File(fileName);
            Scanner file = new Scanner(inputFile);

            while (file.hasNextLine()){
                rows++;
                file.nextLine();
            }
            file.close();

        } catch (FileNotFoundException e){
            rows--;
        }

        return rows;

    }
    
    public char[][] readmap(String fileName){

        try {

            File inputFile = new File(fileName);
            Scanner file = new Scanner(inputFile);

            int rows = numberOfRows(fileName);
            String[] mapLines = new String[rows];

            int maxColumns = 0;
            for (int i = 0; i < rows; i++){
                mapLines[i] = file.nextLine();
                if (mapLines[i].length() > maxColumns){
                    maxColumns = mapLines[i].length();
                }
            }

            char[][] map = new char[rows][maxColumns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < mapLines[i].length(); j++) {
                    map[i][j] = mapLines[i].charAt(j);
                }
            }

            file.close();
            return map;

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            return null;
        }
    }

    public int[] findPlayer(char[][] map) {

        int[] playerPosition = new int[2];

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){

                if (map[i][j] == PLAYER){
                    playerPosition[0] = i;
                    playerPosition[1] = j;
                    return playerPosition;
                }
            }
        }
        return null;
    }
    
    public boolean isValid(char[][] map, int row, int col, char direction) {

        switch (direction) {

            case UP:

                if (map[row - 1][col] == WALL) return false;

                if ((map[row - 1][col] == BOX || map[row - 1][col] == BOXONGOAL) && (map[row - 2][col] == WALL || map[row - 2][col] == BOX || map[row - 2][col] == BOXONGOAL)) return false;

            break;

            case DOWN:

                if (map[row + 1][col] == WALL) return false;

                if ((map[row + 1][col] == BOX || map[row + 1][col] == BOXONGOAL) &&(map[row + 2][col] == WALL || map[row + 2][col] == BOX || map[row + 2][col] == BOXONGOAL)) return false;

            break;

            case LEFT:

                if (map[row][col - 1] == WALL) return false;

                if ((map[row][col - 1] == BOX || map[row][col - 1] == BOXONGOAL) &&(map[row][col - 2] == WALL || map[row][col - 2] == BOX || map[row][col - 2] == BOXONGOAL)) return false;

            break;

            case RIGHT:

                if (map[row][col + 1] == WALL) return false;

                if ((map[row][col + 1] == BOX || map[row][col + 1] == BOXONGOAL) &&(map[row][col + 2] == WALL || map[row][col + 2] == BOX || map[row][col + 2] == BOXONGOAL)) return false;

            break;

        }

        return true;

    }

    public void printMap(char[][] map) {

        int max = map[0].length;
        for (int row = 0; row < map.length; row++) {
            if (max < map[row].length)
                max = map[row].length;
        }

        System.out.print("  ");

        int columnCount = 0;
        for (int i = 0; i < max; i++) {
            System.out.print(columnCount % 10);
            columnCount++;
            if (columnCount == 10)
                columnCount -= 10;
        }

        System.out.println();

        for (int row = 0; row < map.length; row++) {
            System.out.printf("%-2d", row);
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == '\0') {
                    System.out.print("");
                    break;
                } else {
                    System.out.print(map[row][col]);
                }
            }
            System.out.println();
        }
    }
}