import org.junit.Assert;

public class Maths {
    public static int factorial(int n){
        int total = 1;
        for(int i=1;i<n+1;i++){
            total*=i;
        }
        return total;
    }
}