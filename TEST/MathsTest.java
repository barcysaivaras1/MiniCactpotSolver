import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MathsTest {

    @ParameterizedTest
    @DisplayName("Testing if Factorial function gives correct output")
    @CsvSource({
            "2,2",
            "4,24",
            "12,479001600"
    })
    void factorial(int n,int expected) {
        int ans = Maths.factorial(n);
        Assert.assertEquals(ans,expected);
    }
}