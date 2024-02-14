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
        // NOTE TO SELF
        // You to calculate the number of possible permutations with no replacements of digits
        // You need to be able to carry out the score of every possible permutation and add it to the total
        // Total needs to be divided by the number of permutations
        int counter = 0;
        for(int i=0;i<row.length();i++){
            char digit = row.charAt(i);
            if(digit == '0'){
                counter+=1;
            }
        }
        //Need factorial method to calculate
        //I think i might need to calculate combinations and not permutations. Requires more thinking.
        int combinations = Maths.TakesDigitsAndOutputsNumOfCombinations(this.available_digits.size(),counter);
        Map<Integer,Integer> SCORE_MAP = CreatesAHashMapShowingWhatNumbersCanBeMadeWithTheRow(row,counter);
        //Loop starts from six because hashmap keys go from 6-24
        int TOTAL_POSSIBLE_SCORE = 0;
        PossibleScores ps = new PossibleScores();
        for(int i=6;i<SCORE_MAP.size()+6;i++){
            int score = ps.possible_scores.get(i);
            TOTAL_POSSIBLE_SCORE = TOTAL_POSSIBLE_SCORE+(score*SCORE_MAP.get(i));
        }
        return TOTAL_POSSIBLE_SCORE/combinations;
    }

    public Map<Integer, Integer> CreatesAHashMapShowingWhatNumbersCanBeMadeWithTheRow(String row,int num_zero) {
        Map<Integer, Integer> num_of_digits = ThisBuildsAMapForCalculatingTheAverageScore();
        int counter = 0;
        //This needs to create a hash map with the number of times a digit can be created with the
        // Available values, I have a test, base my code to succeed in that test.
        switch (num_zero){
            case 0:
                int val1 = Integer.parseInt(String.valueOf(row.charAt(0)));
                int val2 = Integer.parseInt(String.valueOf(row.charAt(1)));
                int val3 = Integer.parseInt(String.valueOf(row.charAt(2)));
                num_of_digits.put(val1+val2+val3, num_of_digits.get(val1+val2+val3) + 1);
                break;
            case 1:
                //Case when there is 1 zero, There are two constants
                //You Iterate 1 digit through list of available digits and add it to the total

                int[] constant_vals = new int[]{0,0};
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
        Map<Integer, Integer> num_of_digits = new HashMap<Integer,Integer>();
        for(int i=6;i<25;i++){
            num_of_digits.put(i,0);
        }
        return num_of_digits;
    }
    public Map<String,Integer> BuildsAMapForStoringTheAverageScoreOfEachLineInTheGrid(){
        Map<String, Integer> AVERAGE_SCORE_FOR_EACH_LINE = new HashMap<String,Integer>();
        String[] LINES_IN_THE_GRID = new String[]{"Row1","Row2","Row3","Column1","Column2","Column3","Diagonal1","Diagonal2"};
        for(int i=0;i<LINES_IN_THE_GRID.length;i++){
            AVERAGE_SCORE_FOR_EACH_LINE.put(LINES_IN_THE_GRID[i],0);
        }
        return AVERAGE_SCORE_FOR_EACH_LINE;
    }
}
