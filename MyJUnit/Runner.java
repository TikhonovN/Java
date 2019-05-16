import java.util.LinkedList;

public class Runner {
    private static LinkedList<Class<?>> classesForTesting;
    private static LinkedList<LinkedList<String>> testingResults;
    
    public static void main(String[] args) {
        if(args.length < 2 || !args[0].matches("\\d+")) {
            System.out.println("Invalid input values");
            System.out.println("Run command: java -cp <junit_jar>;<tested_classes> Runner <number_of_threads> [tested_class_name]+");
            return;
        }
        loadClasses(args, args.length);
        int numberOfClasses = classesForTesting.size();
        testingResults = new LinkedList();
              
        int numberOfThreads = Integer.parseInt(args[0]);
        for (int i = 0; i < numberOfThreads; ++i) {
            new Tester(classesForTesting, testingResults).start();
        }
        while (testingResults.size() != numberOfClasses) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        printResults();
    }

    private static void printResults() {
        while (!testingResults.isEmpty()) {
            System.out.println();
            LinkedList<String> res = testingResults.removeFirst();
            while (!res.isEmpty()) {
                System.out.println(res.removeFirst());
            }
        }
    }
    
    private static void loadClasses(String[] args, int size) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        classesForTesting = new LinkedList();
        for (int i = 1; i < size; ++i) {
            try {
                classesForTesting.add(classLoader.loadClass(args[i]));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}