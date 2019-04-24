package assertions;

public class Assertions extends Exception {
    public static void assertTrue(boolean cond) throws TestAssertionError
    {
        if (cond == false)
        {
            throw new TestAssertionError("test assertion");
        }
    }
}