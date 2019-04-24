public class FunctionIsNotApplyedException extends Exception {
    private String message;

    public FunctionIsNotApplyedException() {
        super();
    }

    public FunctionIsNotApplyedException(String message) {
        super();
        this.message = message;
    }

    public String toString() {
        return "FunctionIsNotApplyedException: " + message;
    }

}