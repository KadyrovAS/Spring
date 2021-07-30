package h1.topic1;

public class AdvancedCalculator implements ICalculator{
    @Override
    public int sum(int a, int b) {
        long result = (long)a + b;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            throw new RuntimeException("Overflow");
        return a + b;
    }

    @Override
    public int diff(int a, int b) {
        long result = (long)a - b;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            throw new RuntimeException("Overflow");
        return a - b;
    }

    @Override
    public int div(int a, int b) {
        long result = (long)a / b;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            throw new RuntimeException("Overflow");
        return a / b;
    }

    @Override
    public int mult(int a, int b) {
        long result = (long)a * b;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            throw new RuntimeException("Overflow");
        return a * b;
    }
}