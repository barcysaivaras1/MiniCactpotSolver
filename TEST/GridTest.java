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
    @MethodSource("ChecksIfTheValuesGivenAsParametersAreStoredInTheArrayGridCorrectlyTests")
    void ChecksIfTheValuesGivenAsParametersAreStoredInTheArrayGridCorrectly(String row1, String row2, String row3,String[] expected) {
        Grid gc = new Grid();
        gc.gridInsert(row1,row2,row3);
        System.out.println(gc.grid);
        Assert.assertArrayEquals(expected,gc.grid);
    }
    private static Stream<Arguments> ChecksIfTheValuesGivenAsParametersAreStoredInTheArrayGridCorrectlyTests(){
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
    @MethodSource("ChecksIfThereAreAnyDuplicateNumbersWithinEachRowTests")
    void ChecksIfThereAreAnyDuplicateNumbersWithinEachRow(String row1, String row2, String row3,boolean expected) {
        Grid gc = new Grid();
        boolean duplicate = gc.duplicateCheck(row1,row2,row3);
        Assert.assertTrue(expected == duplicate);
    }

    private static Stream<Arguments> ChecksIfThereAreAnyDuplicateNumbersWithinEachRowTests() {
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
    @MethodSource("ChecksIfTheCorrectAverageIsReturnedForEachRowTests")
    void ChecksIfTheCorrectAverageIsReturnedForEachRow(Grid gc,String row,int expected) {
        Assert.assertEquals(expected,gc.rowAverage(row));
    }
    private static Stream<Arguments> ChecksIfTheCorrectAverageIsReturnedForEachRowTests() {
        Grid gc1 = new Grid();
        Grid gc2 = new Grid();
        Grid gc3 = new Grid();
        Grid gc4 = new Grid();
        Grid gc5 = new Grid();
        gc1.gridInsert("000","000","001");
        gc2.gridInsert("000","000","146");
        gc3.gridInsert("000","012","000");
        gc4.gridInsert("000","000","312");
        gc5.gridInsert("000","000","302");

        return Stream.of(
                Arguments.of(gc1,"001",528),
                Arguments.of(gc2,"146",252),
                Arguments.of(gc3,"000",363),
                Arguments.of(gc4,"312",10000),
                Arguments.of(gc5,"302",1560)
        );
    }


    @ParameterizedTest
    @DisplayName("Testing method that creates the number of possible combinations of each digit")
    @MethodSource("ChecksIfTheCorrectHashMapIsReturnedToCalculateAverageTest")
    void ChecksIfTheCorrectHashMapIsReturnedToCalculateAverage(Grid gc,String num,int counter, Map<Integer,Integer> expected) {
        Map<Integer,Integer> map = gc.CreatesAHashMapShowingWhatNumbersCanBeMadeWithTheRow(num,counter);
        Assert.assertEquals(expected,map);
    }

    private static Stream<Arguments> ChecksIfTheCorrectHashMapIsReturnedToCalculateAverageTest() {
        Grid gc1 = new Grid();
        Grid gc2 = new Grid();
        Grid gc3 = new Grid();
        Grid gc4 = new Grid();
        gc1.gridInsert("269","000","430");
        gc2.gridInsert("000","100","240");
        gc3.gridInsert("678","135","200");
        gc4.gridInsert("678","135","000");
        Map<Integer, Integer> ans1 = gc2.mapBuild();
        Map<Integer, Integer> ans2 = gc1.mapBuild();
        Map<Integer, Integer> ans3 = gc4.mapBuild();
        Map<Integer, Integer> ans4 = gc3.mapBuild();
        ans1.put(9,1);
        ans1.put(11,1);
        ans1.put(12,1);
        ans1.put(13,1);
        ans1.put(14,1);
        ans1.put(15,1);
        ans2.put(17,1);
        ans3.put(15,1);
        ans4.put(15,1);
        return Stream.of(
                //More Tests to be added
                Arguments.of(gc2,"240",1,ans1),
                Arguments.of(gc1,"269",0,ans2),
                Arguments.of(gc4,"000",3,ans3),
                Arguments.of(gc3,"200",2,ans4)
        );
    }
}