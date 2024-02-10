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
        int permutations = Maths.factorial(this.available_digits.size()) / Maths.factorial(this.available_digits.size()-counter);

        return 0;
    }

    public Map<Integer, Integer> permutationMap(String row,int num_zero) {
        Map<Integer, Integer> num_of_digits = mapBuild();

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
                int counter =0;
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
            case 2:
                //Case when there are 2 zeroes, There is one constant
                break;

            case 3:
                //Case when there are 3 zeroes, There are no constants
                break;
        }

        return num_of_digits;
    }

    public Map<Integer,Integer> mapBuild(){
        Map<Integer, Integer> num_of_digits = new HashMap<Integer,Integer>();
        num_of_digits.put(6,0);
        num_of_digits.put(7,0);
        num_of_digits.put(8,0);
        num_of_digits.put(9,0);
        num_of_digits.put(10,0);
        num_of_digits.put(11,0);
        num_of_digits.put(12,0);
        num_of_digits.put(13,0);
        num_of_digits.put(14,0);
        num_of_digits.put(15,0);
        num_of_digits.put(16,0);
        num_of_digits.put(17,0);
        num_of_digits.put(18,0);
        num_of_digits.put(19,0);
        num_of_digits.put(20,0);
        num_of_digits.put(21,0);
        num_of_digits.put(22,0);
        num_of_digits.put(23,0);
        num_of_digits.put(24,0);
        return num_of_digits;
    }

}
