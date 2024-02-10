import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class GridTest {
    @ParameterizedTest
    @DisplayName("Checks if a valid grid is stored. Invalid Grids are denied")
    @MethodSource("gridInsertTestVals")
    void gridInsert(String row1, String row2, String row3,String[] expected) {
        Grid gc = new Grid();
        gc.gridInsert(row1,row2,row3);
        System.out.println(gc.grid);
        Assert.assertArrayEquals(expected,gc.grid);
    }
    private static Stream<Arguments> gridInsertTestVals(){
        return Stream.of(
                //TESTS SHOULD BE SUCCESSFUL WITH VALID INPUT
                        Arguments.of("100","002","004",new String[]{"100","002","004"}),
                        Arguments.of("002","000","000",new String[]{"002","000","000"}),

                //INVALID INPUT HANDLING
                        Arguments.of("100","002","4",new String[]{}),
                        Arguments.of("100","002","JPG",new String[]{}),
                //DUPLICATE NUMBERS SHOULD NOT BE ALLOWED
                        Arguments.of("100","002","002",new String[]{}),
                        Arguments.of("100","002","112",new String[]{})
        );
    }

    @ParameterizedTest
    @DisplayName("Checks if there are duplicate digits between rows and in rows")
    @MethodSource("duplicateCheckTestVals")
    void duplicateCheck(String row1, String row2, String row3,boolean expected) {
        Grid gc = new Grid();
        boolean duplicate = gc.duplicateCheck(row1,row2,row3);
        Assert.assertTrue(expected == duplicate);
    }

    private static Stream<Arguments> duplicateCheckTestVals() {
        return Stream.of(
                //True means there are duplicates within the rows
                Arguments.of("100","002","002",true),
                Arguments.of("100","002","112",true),
                //False means there are no duplicates within the rows
                Arguments.of("100","002","000",false),
                Arguments.of("123","456","789",false)
        );
    }

    @ParameterizedTest
    @DisplayName("Testing if average score calculated correctly")
    @CsvSource({
            "001 , 528",
            "146 , 252",
            "000 , 332",
            "312 , 10000",
            "302 , 1761"
    })
    void rowAverageTest(String line ,int expected) {
        Grid gc = new Grid();
        int avgScore = gc.rowAverage(line);
        Assert.assertEquals(avgScore,expected);
    }

    @ParameterizedTest
    @DisplayName("Testing method that creates the number of possible permutations of each digit")
    @MethodSource("permutationMapTestVals")
    void permutationMapTest(Grid gc,String num,int counter, Map<Integer,Integer> expected) {
        Map<Integer,Integer> map = gc.permutationMap(num,counter);
        Assert.assertEquals(map,expected);
    }

    private static Stream<Arguments> permutationMapTestVals() {
        Grid gc1 = new Grid();
        Grid gc2 = new Grid();
        gc2.gridInsert("000","100","240");
        Map<Integer, Integer> ans = gc2.mapBuild();
        ans.put(7,1);
        ans.put(8,1);
        ans.put(9,1);
        ans.put(10,1);
        ans.put(11,1);
        ans.put(12,1);
        ans.put(13,1);
        ans.put(14,1);
        ans.put(15,1);
        return Stream.of(
                //More Tests to be added
                Arguments.of(gc2,"240",1,ans)
        );
    }
}