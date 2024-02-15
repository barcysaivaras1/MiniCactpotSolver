import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class CactpotDriver {
    //This provides a more user-friendly  way of interacting with the program
    public static void main(String[] args) {
        //PRINT OUTPUT (ENTRY MESSAGE)
        System.out.println("Welcome to the Mini Cactpot Solver");
        System.out.println("This program will help you make the correct decisions to achieve a higher score");
        String[] USER_CHOICES = new String[]{"1. Insert/Replace grid","2. Display Grid","3. Show next best move","4. Display Average Score","5. Display Available Scores","6. Exit"};
        System.out.println("Please pick a number 1-"+USER_CHOICES.length);
        for(int i=0;i<USER_CHOICES.length;i++){
            System.out.println(USER_CHOICES[i]);
        }
        //VARIABLES
        Scanner sc = new Scanner(System.in);
        Grid gc = new Grid();
        boolean PROGRAM_RUNNING = true;
        int NUM_DIGITS_REVEALED = 0;
        final int MAX_DIGITS = 4;
        final int AVAILABLE_DIGITS = 9;

        //USER INTERFACE LOOP
        while(PROGRAM_RUNNING){
            String USER_INPUT = sc.nextLine();
            switch (USER_INPUT){
                case "1":
                    gc = new Grid();
                    gc.inputRows();
                    NUM_DIGITS_REVEALED = AVAILABLE_DIGITS-gc.available_digits.size();
                    break;
                case "2":
                    if(gc.grid.length != 0) {
                        gc.printGrid();
                    }
                    else{
                        System.out.println("Grid does not exist, Please insert a grid first");
                    }
                    break;
                case "3":
                    Map<String,Integer> LINE_SCORES = gc.MapsTheAverageScoreForEachLine();
                    int HIGHEST_VAL = 0;
                    String HIGHEST_VAL_POSITION = "";
                    for(Map.Entry<String,Integer> entry : LINE_SCORES.entrySet()){
                        if (entry.getValue()>HIGHEST_VAL){
                            HIGHEST_VAL = entry.getValue();
                            HIGHEST_VAL_POSITION = entry.getKey();
                        }
                    }
                    if(NUM_DIGITS_REVEALED<MAX_DIGITS) {
                        System.out.println("Reveal a position in " + HIGHEST_VAL_POSITION);}
                    else{
                        System.out.println("Pick " + HIGHEST_VAL_POSITION+" for the highest score on average");
                    }
                    break;
                case "4":
                    LINE_SCORES = gc.MapsTheAverageScoreForEachLine();
                    for(Map.Entry<String,Integer> entry : LINE_SCORES.entrySet()){
                        String key = entry.getKey();
                        System.out.println(entry.getKey()+" = "+entry.getValue());
                    }
                    break;
                case "5":
                    PossibleScores ps = new PossibleScores();
                    Map<Integer, Integer> possible_scores = ps.possible_scores;
                    for(Map.Entry<Integer,Integer> entry : possible_scores.entrySet()){
                        System.out.println(entry.getKey()+" = "+ entry.getValue());
                    }
                    break;
                case "6":
                    PROGRAM_RUNNING = false;
                    break;
                default:
                    System.out.println("Invalid Input, Please pick a number 1-"+USER_CHOICES.length);
            }
        }
    }

    //Create more code to allow user to input what value was revealed when they made the suggested choice. This should print out new grid and averages
}