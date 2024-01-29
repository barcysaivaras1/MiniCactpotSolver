import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class GridCreatorTest {

    @ParameterizedTest
    @MethodSource("gridInsertTestVals")
       //     "100,002,004",
        //    "002,000,000",
         //   "100,234,000"
    void gridInsert(String row1, String row2, String row3,String[] expected) {
        GridCreator gc = new GridCreator();
        gc.gridInsert(row1,row2,row3);
        Assert.assertArrayEquals(expected,gc.grid);
    }
    private static Stream<Arguments> gridInsertTestVals(){
        return Stream.of(
                //TESTS SHOULD BE SUCCESSFUL
                Arguments.of("100","002","004",new String[]{"100","002","004"}),
                Arguments.of("002","000","000",new String[]{"002","000","000"}),

                //TESTS SHOULD FAIL
                Arguments.of("100","002","4",new String[]{"100","002","4"}),
                Arguments.of("100","002","JPG",new String[]{"100","002","JPG"})
        );
    }
}