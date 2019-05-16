import annotations.*;
import assertions.*;
import java.lang.reflect.Method;
import java.util.LinkedList;


public class Tester extends Thread {

    private final LinkedList<Class<?>> testingClasses;
    private LinkedList<LinkedList<String>> testingResults;
    private Method[] methods;
    private Class<?> testingClass;
    private int pass;
    private int fail;
    private LinkedList<String> result;
    private Object testingClassObject;

    public Tester(LinkedList<Class<?>> itestingClasses, LinkedList<LinkedList<String>> itestingResults) {
        testingClasses = itestingClasses;
        testingResults = itestingResults;
    }

    public void run() {

        while (!testingClasses.isEmpty()) {
            pass = 0;
            fail = 0;
            synchronized (testingClasses) {
                if (testingClasses.isEmpty()) break;
                testingClass = testingClasses.removeLast();
            }
            methods = testingClass.getMethods();
            result = new LinkedList();
            try {
                testingClassObject = testingClass.newInstance();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if(testingClassObject == null) {
                result.addFirst("Testing of class " + testingClass.getName() + " finished!");
                result.addLast("Could not create instance");
            } else {
                runTestMethods();
                result.addFirst("Testing of class " + testingClass.getName() + " finished!");
                result.addLast("Successed:" + pass + "   Failed:" + fail);
            }

            synchronized (testingResults) {
                testingResults.addLast(result);
            }
        }
    }

    private void addResultToList(boolean isSuccessed, String exceptionName, String methodName) {

        StringBuffer strBuf = new StringBuffer ("");

        strBuf.append((isSuccessed) ? "Success. " : "FAIL!!! ");
        strBuf.append((exceptionName.equals("")) ? "No exceptions" : exceptionName);
        strBuf.append(" have(s) been thrown in method ");
        strBuf.append(methodName);

        result.addLast(strBuf.toString());
    }

    private void runTestMethods() {

        Method beforeMethod = null, afterMethod = null;
        LinkedList<Method> testMethods = new LinkedList();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) beforeMethod = method;
            else if (method.isAnnotationPresent(Test.class)) testMethods.add(method);
            else if (method.isAnnotationPresent(After.class)) afterMethod = method;
        }

        for (Method testMethod : testMethods) {

            Test test = testMethod.getAnnotation(Test.class);
            Class<?> expected = test.expected();

            try {
                beforeMethod.invoke(testingClassObject);
            } catch (Exception e) {
                e.printStackTrace();
                Class<?> thrownException = getCauseException(e);
                addResultToList(false, thrownException.getName(), "Before method :" + beforeMethod.getName());
                return;
            }

            try {
                testMethod.invoke(testingClassObject);
                if (expected == Exception.class) {
                    pass++;
                    addResultToList(true, "", testMethod.getName());
                } else {
                    fail++;
                    addResultToList(false, "", testMethod.getName());
                }
            } catch (Exception e) {
                Class<?> thrownException = getCauseException(e);
                if (thrownException == TestAssertionError.class) {
                    addResultToList(false, "TestAssertionException", testMethod.getName());
                    fail++;
                } else if (thrownException != expected) {
                    addResultToList(false, thrownException.getName(), testMethod.getName());
                    fail++;
                } else {
                    addResultToList(false, "Expected exception", testMethod.getName());
                    pass++;
                }
            }

            try {
                afterMethod.invoke(testingClassObject);
            } catch (Exception e) {
                e.printStackTrace();
                Class<?> thrownException = getCauseException(e);
                addResultToList(false, thrownException.getName(), "After method :" + afterMethod.getName());
                return;
            }

        }

    }

    private Class<?> getCauseException(Exception e) {
        if (e.getCause() == null) {
            return e.getClass();
        } else {
            return e.getCause().getClass();
        }
    }
}