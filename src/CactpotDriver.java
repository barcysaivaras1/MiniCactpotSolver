import java.util.Map;

public class CactpotDriver {
    public static void main(String[] args) {
        Grid gc = new Grid();
        gc.inputRows();
        gc.printGrid();
        Map<String,Integer> LINE_SCORES = gc.MapsTheAverageScoreForEachLine();
        int HIGHEST_VAL = 0;
        String HIGHEST_VAL_POSITION = "";
        for(Map.Entry<String,Integer> entry : LINE_SCORES.entrySet()){
            String key = entry.getKey();
            System.out.println(entry.getKey()+" = "+entry.getValue());
            if (entry.getValue()>HIGHEST_VAL){
                HIGHEST_VAL = entry.getValue();
                HIGHEST_VAL_POSITION = entry.getKey();
            }
        }
        System.out.println("Reveal a position in "+HIGHEST_VAL_POSITION);
    }

    //Create more code to allow user to input what value was revealed when they made the suggested choice. This should print out new grid and averages
}