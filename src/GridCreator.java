import java.util.Scanner;

public class GridCreator {
    String[] grid;

    public GridCreator() {
        this.grid = new String[]{};
    }

    //This method should only accept 3 sets of numbers of length 3.
    public void gridInsert(String row1, String row2, String row3){
        if (row1.length() == 3 && row2.length() == 3 && row3.length() == 3){
            if(numCheck(row1,row2,row3)){
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
    public boolean duplicateCheck(String row1,String row2, String row3){
        return false;
    }


}
