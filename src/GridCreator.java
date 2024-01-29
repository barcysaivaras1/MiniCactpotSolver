public class GridCreator {
    String[] grid;

    //This method should only accept 3 sets of numbers of length 3.
    public void gridInsert(String row1, String row2, String row3){
        if (row1.length() == 3 && row2.length() == 3 && row3.length() == 3){
            if(numCheck(row1,row2,row3)){
                this.grid = new String[]{row1, row2, row3};}
        }
        System.out.println("Invalid input");
    }
    public boolean numCheck(String row1, String row2, String row3){
        try{
            int row = Integer.parseInt(row1);
            row = Integer.parseInt(row2);
            row = Integer.parseInt(row3);
            return true;
        }
        catch (NumberFormatException ex){
            return false;
        }
    }


}
