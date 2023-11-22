import java.util.Random;

public class Main {
    static Random random = new Random();
    public static void main(String[] args) {
        benchMarkHeapList();
        System.out.println("\n \n");
        benchmarkHeapTree();
        System.out.println("\n \n");
        benchmarkHeapArray();
    }

    public static void benchMarkHeapList() {

        int[] values = { 1_000, 2_000, 5_000, 10_000, 20_000};
        for (int size : values) {
            HeapList heapList = createLinkedList(size);

            // measuring time for adding elements
            long addStartTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                heapList.addConstant(i);
            }
            long addEndTime = System.nanoTime();

            // measuring time removing elements
            long removeStartTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                heapList.removeLinear(i);
            }
            long removeEndTime = System.nanoTime();

            // Calculate execution times and converting to ms
            double addExecutionTime = (addEndTime - addStartTime) / 1_000_000.0;
            double removeExecutionTime = (removeEndTime - removeStartTime) / 1_000_000.0;

            System.out.println("Input Size: " + size);
            System.out.println("Add Execution Time: " + addExecutionTime + " ms");
            System.out.println("Remove Execution Time: " + removeExecutionTime + " ms");
        }
    }

    public static void benchmarkHeapTree(){

        HeapTree heapTree = new HeapTree();

        // Add 1023 elements with random values
        for (int i = 0; i < 1023; i++) {
            int randomValue = random.nextInt(10001); // Random values between 0 and 10000
            heapTree.insert(randomValue);
        }

        int pushOperations = 1000; // Adjust this value as needed

        int totalDepth = 0;

        for (int i = 0; i < pushOperations; i++) {
            int randomIncrement = random.nextInt(91) + 10; // Random increment between 10 and 100
            totalDepth += heapTree.extractMin();
            heapTree.insert(heapTree.extractMin() + randomIncrement);
        }

        double averageDepth = (double) totalDepth / pushOperations;
        System.out.println("Average Depth for " + pushOperations + " push operations: " + averageDepth);
        // Clear the heap
        heapTree.clear();
    }

    public static void benchmarkHeapArray(){
        int[] values = { 1_000, 2_000, 5_000, 10_000, 20_000};
        for (int size : values) {
            HeapArray heap = new HeapArray(size);

            // Measure insert time
            long startTimeInsert = System.nanoTime();
            for (int i = 1; i <= size; i++) {
                heap.insert(i);
            }
            long endTimeInsert = System.nanoTime();
            double insertTime = (endTimeInsert - startTimeInsert) / 1_000_000.0; // in milliseconds

            // Measure remove time
            long startTimeRemove = System.nanoTime();
            for (int i = 1; i <= size; i++) {
                heap.remove();
            }
            long endTimeRemove = System.nanoTime();
            double removeTime = (endTimeRemove - startTimeRemove) / 1_000_000.0; // in milliseconds

            System.out.println("Heap Size: " + size);
            System.out.println("Insert Time: " + insertTime + " ms");
            System.out.println("Remove Time: " + removeTime + " ms");
        }
    }

    public static HeapList createLinkedList(int size) {
        HeapList list = new HeapList();
        for (int i = 0; i < size; i++) {
            int x = random.nextInt(size * 10);
            list.addConstant(x);
        }
        return list;
    }

}