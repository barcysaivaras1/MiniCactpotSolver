import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.stream.Stream;

class GridCreatorTest {
    @ParameterizedTest
    @DisplayName("Checks if a valid grid is stored. Invalid Grids are denied")
    @MethodSource("gridInsertTestVals")
    void gridInsert(String row1, String row2, String row3,String[] expected) {
        GridCreator gc = new GridCreator();
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
        GridCreator gc = new GridCreator();
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
}