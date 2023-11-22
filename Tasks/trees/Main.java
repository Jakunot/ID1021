import java.util.*;

public class Main {

    private static final int MIN_KEY = 1;
    private static final int MAX_KEY = 1000;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 100;

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.add(5, 105);
        tree.add(2, 102);
        tree.add(7, 107);
        tree.add(1, 101);
        tree.add(8, 108);
        tree.add(6, 106);
        tree.add(3, 103);

        for(int i : tree){
            System.out.println("next value " + i);
        }

        BinaryTree binaryTree = new BinaryTree();

        // Define the number of items you want to add
        int numberOfItems = 15_000;

        // Benchmark the addRandomSampleData method
        long addStartTime = System.nanoTime();
        SampleData(binaryTree, numberOfItems);
        long addEndTime = System.nanoTime();
        double addElapsedTime = addEndTime - addStartTime;
        System.out.println("Adding " + numberOfItems + " random items took " + addElapsedTime + " nanoseconds.");

        if ((double) (addEndTime - addStartTime) < addElapsedTime) {
            addElapsedTime = (double) (addEndTime - addStartTime);
        }
        addElapsedTime = addElapsedTime / 1_000_000;
        System.out.println("Adding: " + addElapsedTime + "ms");

        benchmarkLookup(binaryTree, numberOfItems);

    }

    public static void benchmarkLookup(BinaryTree binaryTree, int numberOfLookups) {
        Random random = new Random();
        long totalLookupTime = 0;

        for (int i = 0; i < numberOfLookups; i++) {
            // Generate a random key to look up
            int randomKey = random.nextInt(MAX_KEY - MIN_KEY + 1) + MIN_KEY;

            // Measure the time it takes to perform the lookup
            long lookupStartTime = System.nanoTime();
            Integer result = binaryTree.lookup(randomKey);
            long lookupEndTime = System.nanoTime();

            // Check if the lookup was successful and update the total time
            if (result != null) {
                totalLookupTime += lookupEndTime - lookupStartTime;
            }
        }

        // Calculate and print the average lookup time
        double averageLookupTime = (double) totalLookupTime / numberOfLookups;
        System.out.println("Average lookup time for " + numberOfLookups + " lookups: " + averageLookupTime + " nanoseconds.");
    }




    public static void SampleData(BinaryTree binaryTree, int numberOfItems) {

        Random random = new Random();
        for (int i = 0; i < numberOfItems; i++) {
            // Generate random key and value within the specified range
            int randomKey = random.nextInt(MAX_KEY - MIN_KEY + 1) + MIN_KEY;
            int randomValue = random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;

            // Add the random key and value to the binary tree
            binaryTree.add(randomKey, randomValue);
        }
    }
}