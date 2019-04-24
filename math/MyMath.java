public class MyMath {

    public int fibonacci(int n) {
        if ((n == 0) || (n == 1)) {
            return 1;
        } else {
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }

    public int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return factorial(n-1)*n;
        }
    }

    public int factorialWithException(int n) throws FunctionIsNotApplyedException {
        if (n < 0) {
            throw new FunctionIsNotApplyedException("factorial(n < 0)");
        } else if (n == 0) {
            return 1;
        } else {
            return factorial(n-1)*n;
        }
    }

}
