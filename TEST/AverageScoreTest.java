import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AverageScoreTest {

    @ParameterizedTest
    @CsvSource({
            "001 , 528",
            "146 , 252",
            "000 , 332",
            "312 , 10000",
            "302 , 1761"
    })
    void rowAverageTest() {

    }

}