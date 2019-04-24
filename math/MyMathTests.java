import annotations.*;
import assertions.*;

public class MyMathTests {

    private MyMath instance;

    @Before
    public void beforeEach() {
        instance = new MyMath();
    }

    @Test
    public void fibonacciTest() throws TestAssertionError {
        int x = instance.fibonacci(7);
        Assertions.assertTrue(x == 21);
    }

    @Test
    public void factorialTest() throws TestAssertionError {
        int x = instance.factorial(10);
        Assertions.assertTrue(x == 3628800);
    }

    @Test(expected = FunctionIsNotApplyedException.class)
    public void testFactorialWithException() throws FunctionIsNotApplyedException {
        instance.factorialWithException(-42);
    }

    @After
    public void afterEach() {
        System.out.println("After annotation complete");
    }

}