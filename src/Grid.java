import java.util.*;

public class Grid {
    String[] grid;

    //Use array list so that i can search and remove values with ease
    ArrayList<Character> available_digits;
    public Grid() {
        this.grid = new String[]{};
        this.available_digits = new ArrayList<>(Arrays.asList('1','2','3','4','5','6','7','8','9'));
    }

    //This method should only accept 3 sets of numbers of length 3.
    public void gridInsert(String row1, String row2, String row3){
        if (row1.length() == 3 && row2.length() == 3 && row3.length() == 3){
            if(numCheck(row1,row2,row3) && !(this.duplicateCheck(row1,row2,row3))){
                this.grid = new String[]{row1, row2, row3};
                System.out.println("Grid Inserted");
                return;
            }
        }
        System.out.println("Invalid input");
    }
    //This method will check if the values supplied are numbers
    public boolean numCheck(String row1, String row2, String row3){
        try{
            Integer.parseInt(row1);
            Integer.parseInt(row2);
            Integer.parseInt(row3);
            return true;
        }
        catch (NumberFormatException ex){
            return false;
        }
    }

    //Will collect user input
    public void inputRows(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input Row 1 the grid");
        String row1 = sc.nextLine();
        System.out.println("Please input Row 2 the grid");
        String row2 = sc.nextLine();
        System.out.println("Please input Row 3 the grid");
        String row3 = sc.nextLine();
        gridInsert(row1,row2,row3);
    }
    //This will print the grid, in a grid format
    public void printGrid(){
        for(int i = 0; i< this.grid.length;i++){
            String row = "";
            String temp = String.valueOf(this.grid[i].charAt(0));
            row = row +"[" + temp + "]";
            temp = String.valueOf(this.grid[i].charAt(1));
            row = row +" [" + temp + "] ";
            temp = String.valueOf(this.grid[i].charAt(2));
            row = row +"[" + temp + "]";
            System.out.println(row);
        }
    }
    //Return TRUE if there is a shared number between the 3 rows. 0 not included.
    public boolean duplicateCheck(String row1,String row2, String row3){
        String grid = row1+row2+row3;
        for(int i=0;i<9;i++){
            char digit = grid.charAt(i);
            if(digit == '0'){}
            else if((this.available_digits.contains(digit))){
                //A digit is removed as it is no longer an available choice
                this.available_digits.remove(Character.valueOf(digit));
            }
            else{ //This means there is a duplicate is found
                return true;
            }
        }
        return false;
    }

    public int rowAverage(String row){
        //This loop finds the number of zeroes present in a row
        int counter = 0;
        for(int i=0;i<row.length();i++){
            char digit = row.charAt(i);
            if(digit == '0'){
                counter+=1;
            }
        }
        //Calculates the number of combinations
        int combinations = Maths.TakesDigitsAndOutputsNumOfCombinations(this.available_digits.size(),counter);
        Map<Integer,Integer> SCORE_MAP = CreatesAHashMapShowingWhatNumbersCanBeMadeWithTheRow(row,counter);
        //Loop starts from six because hashmap keys go from 6-24
        int TOTAL_POSSIBLE_SCORE = 0;
        PossibleScores ps = new PossibleScores();
        //Finds the total score
        for(int i=6;i<SCORE_MAP.size()+6;i++){
            int score = ps.possible_scores.get(i);
            TOTAL_POSSIBLE_SCORE = TOTAL_POSSIBLE_SCORE+(score*SCORE_MAP.get(i));
        }
        //Returns the mean
        return TOTAL_POSSIBLE_SCORE/combinations;
    }

    public Map<Integer, Integer> CreatesAHashMapShowingWhatNumbersCanBeMadeWithTheRow(String row,int num_zero) {
        Map<Integer, Integer> num_of_digits = ThisBuildsAMapForCalculatingTheAverageScore();
        int counter = 0;
        //Based on the number of zeroes, the method will have a to treat the row differently
        switch (num_zero){
            case 0:
                //Returns the sum of digits
                int val1 = Integer.parseInt(String.valueOf(row.charAt(0)));
                int val2 = Integer.parseInt(String.valueOf(row.charAt(1)));
                int val3 = Integer.parseInt(String.valueOf(row.charAt(2)));
                num_of_digits.put(val1+val2+val3, num_of_digits.get(val1+val2+val3) + 1);
                break;
            case 1:
                //Case when there is 1 zero, There are two constants
                //You Iterate 1 digit through list of available digits and add it to the total

                int[] constant_vals = new int[]{0,0};
                //This finds the non-zero values in the row
                for(int j=0;j<row.length();j++){
                    char digit = row.charAt(j);
                    if(digit != '0'){
                        constant_vals[counter] = Integer.parseInt(String.valueOf(digit));
                        counter+=1;
                    }
                }
                for (int i = 0; i < this.available_digits.size(); i++) {
                    int valTotal = Integer.parseInt(String.valueOf(this.available_digits.get(i))) + constant_vals[0] + constant_vals[1];
                    num_of_digits.put(valTotal, num_of_digits.get(valTotal) + 1);
                }
                break;
            case 2://Case when there are 2 zeroes, There is one constant

                int NON_ZERO_DIGITS_IN_ROW=0;
                //This finds the one constant in the row
                for(int j=0;j<row.length();j++){
                    char digit = row.charAt(j);
                    if(digit != '0'){
                        NON_ZERO_DIGITS_IN_ROW = Integer.parseInt(String.valueOf(digit));
                        counter+=1;
                    }
                }

                for(int i=0;i<this.available_digits.size()-1; i++){
                    //We do j=i+1 because we do not want combinations that have occurred already or any duplicates to be considered
                    for(int j=i+1;j<this.available_digits.size();j++){
                        int SUM_OF_DIGITS_IN_ROW = Integer.parseInt(String.valueOf(this.available_digits.get(i))) + Integer.parseInt(String.valueOf(this.available_digits.get(j))) + NON_ZERO_DIGITS_IN_ROW;
                        num_of_digits.put(SUM_OF_DIGITS_IN_ROW,num_of_digits.get(SUM_OF_DIGITS_IN_ROW)+1);
                    }
                }
                break;

            case 3:
                //Case when there are 3 zeroes, There are no constants
                for(int i=0;i<this.available_digits.size()-2; i++){
                    //We do j=i+1 because we do not want combinations that have occurred already or any duplicates to be considered
                    for(int j=i+1;j<this.available_digits.size()-1;j++){
                        //We do k=j+1 because we do not want combinations that have occurred already or any duplicates to be considered
                        for(int k=j+1;k<this.available_digits.size();k++) {
                            int SUM_OF_DIGITS_IN_ROW = Integer.parseInt(String.valueOf(this.available_digits.get(i))) + Integer.parseInt(String.valueOf(this.available_digits.get(j))) + Integer.parseInt(String.valueOf(this.available_digits.get(k)));
                            num_of_digits.put(SUM_OF_DIGITS_IN_ROW, num_of_digits.get(SUM_OF_DIGITS_IN_ROW) + 1);
                        }
                    }
                }
                break;
        }

        return num_of_digits;
    }

    public Map<Integer,Integer> ThisBuildsAMapForCalculatingTheAverageScore(){
        //This creates the skeleton of the hashmap that will have its values updated for working out the average
        Map<Integer, Integer> num_of_digits = new HashMap<Integer,Integer>();
        for(int i=6;i<25;i++){
            num_of_digits.put(i,0);
        }
        return num_of_digits;
    }
    public Map<String,Integer> BuildsAMapForStoringTheAverageScoreOfEachLineInTheGrid(){
        //This creates the skeleton for the hashmap that holds every possible position in the grid, average scores will be assigned to each position
        Map<String, Integer> AVERAGE_SCORE_FOR_EACH_LINE = new HashMap<String,Integer>();
        String[] LINES_IN_THE_GRID = new String[]{"Row1","Row2","Row3","Column1","Column2","Column3","Diagonal1","Diagonal2"};
        for(int i=0;i<LINES_IN_THE_GRID.length;i++){
            AVERAGE_SCORE_FOR_EACH_LINE.put(LINES_IN_THE_GRID[i],0);
        }
        return AVERAGE_SCORE_FOR_EACH_LINE;
    }

    public Map<String, Integer> MapsTheAverageScoreForEachLine() {
        //Assigns the value average score to each position of the grid
        Map<String, Integer> AVERAGE_SCORE_FOR_EACH_LINE = BuildsAMapForStoringTheAverageScoreOfEachLineInTheGrid();
        //Inserting Average Score for Each Row
        AVERAGE_SCORE_FOR_EACH_LINE.put("Row1",rowAverage(this.grid[0]));
        AVERAGE_SCORE_FOR_EACH_LINE.put("Row2",rowAverage(this.grid[1]));
        AVERAGE_SCORE_FOR_EACH_LINE.put("Row3",rowAverage(this.grid[2]));

        //Creates an array to hold the columns
        String[] columns = new String[]{"","",""};
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length();j++){
                char digit = grid[j].charAt(i);
                columns[i] = columns[i]+digit;
            }
        }
        //Inserting Average Score for Each Column
        AVERAGE_SCORE_FOR_EACH_LINE.put("Column1",rowAverage(columns[0]));
        AVERAGE_SCORE_FOR_EACH_LINE.put("Column2",rowAverage(columns[1]));
        AVERAGE_SCORE_FOR_EACH_LINE.put("Column3",rowAverage(columns[2]));


        String[] diagonals = new String[]{"",""};
        for(int i=0;i<grid.length;i++){
            char digit1 = grid[i].charAt(i);
            char digit2 = grid[i].charAt(grid[i].length()-(i+1));
            diagonals[0] = diagonals[0] + digit1;
            diagonals[1] = diagonals[1] + digit2;
        }

        //Inserting Average Score for each Diagonal
        AVERAGE_SCORE_FOR_EACH_LINE.put("Diagonal1",rowAverage(diagonals[0]));
        AVERAGE_SCORE_FOR_EACH_LINE.put("Diagonal2",rowAverage(diagonals[1]));

        return AVERAGE_SCORE_FOR_EACH_LINE;
    }
}
