import org.junit.Assert;

public class Maths {

    //Takes a digit an returns the factorial of that digit
    public static int factorial(int n){
        int total = 1;
        for(int i=1;i<n+1;i++){
            total*=i;
        }
        return total;
    }
    //This works out the number of combinations using the equation for combinations with replacement
    public static int TakesDigitsAndOutputsNumOfCombinations(int n,int r){
        return factorial(n)/(factorial(n-r)*factorial(r));
    }
}
