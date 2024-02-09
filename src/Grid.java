import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

    public static int rowAverage(String row){
        int int_row = Integer.parseInt(row);
        int total = 0;
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
        int permutations;
        return 0;
    }


}
