package assertions;

public class TestAssertionError extends Exception {
    public TestAssertionError() {}

    public TestAssertionError(String message)
    {
        super (message);
    }
}