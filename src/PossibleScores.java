import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PossibleScores {
    public Map<Integer, Integer> possible_scores = new HashMap<Integer,Integer>();

    //This is a constant value that will be checked to see how much each sum of digits is worth.
    PossibleScores(){
        possible_scores.put(6,10000);
        possible_scores.put(7,36);
        possible_scores.put(8,720);
        possible_scores.put(9,360);
        possible_scores.put(10,80);
        possible_scores.put(11,252);
        possible_scores.put(12,108);
        possible_scores.put(13,72);
        possible_scores.put(14,54);
        possible_scores.put(15,180);
        possible_scores.put(16,72);
        possible_scores.put(17,180);
        possible_scores.put(18,119);
        possible_scores.put(19,36);
        possible_scores.put(20,306);
        possible_scores.put(21,1080);
        possible_scores.put(22,144);
        possible_scores.put(23,1800);
        possible_scores.put(24,3600);
        possible_scores = Collections.unmodifiableMap(possible_scores);
    }

}