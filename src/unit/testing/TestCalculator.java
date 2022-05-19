package unit.testing;

import org.testng.Assert;

public class TestCalculator {
    public static void main(String[] args) {
        Calculator cal = new Calculator();
        int actualAdditionResult = cal.addition(10,15);
        System.out.println(actualAdditionResult);
        Assert.assertEquals(actualAdditionResult,25);

        int actualSubtractionResult = cal.subtraction(20,8);
        System.out.println(actualSubtractionResult);
        Assert.assertEquals(actualSubtractionResult,12);
    }
}
