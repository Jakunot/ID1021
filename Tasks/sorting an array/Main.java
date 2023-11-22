import java.util.Random;

public class Main {
    public static void main(String[] args) {
        benchmarkSelectionSort(10_000);
        benchmarkInsertSort(10_000);
        benchmarkMergeSort(10_000);
    }

    public static void benchmarkSelectionSort(int elements) {
        long startTime;
        long endTime;
        double min = Double.MAX_VALUE;
        int[] unsortedArray = randomArray(elements);
        startTime = System.nanoTime();
        SortingMethods.selectionSort(unsortedArray);
        endTime = System.nanoTime();
        if ((double) (endTime - startTime) < min) {
            min = (double) (endTime - startTime);
        }
        min = min / 1_000_000;
        System.out.println("Selection sort: " + min + "ms");
    }

    public static void benchmarkInsertSort(int elements) {
        long startTime;
        long endTime;
        double min = Double.MAX_VALUE;
        int[] unsortedArray = randomArray(elements);
        startTime = System.nanoTime();
        SortingMethods.insertionSort(unsortedArray);
        endTime = System.nanoTime();
        if ((double) (endTime - startTime) < min) {
            min = (double) (endTime - startTime);
        }
        min = min / 1_000_000;
        System.out.println("Insert sort: " + min + "ms");
    }

    public static void benchmarkMergeSort(int elements) {
        long startTime;
        long endTime;
        double min = Double.MAX_VALUE;
        int[] unsortedArray = randomArray(elements);
        startTime = System.nanoTime();
        SortingMethods.mergeSort(unsortedArray);
        endTime = System.nanoTime();
        if ((double) (endTime - startTime) < min) {
            min = (double) (endTime - startTime);
        }
        min = min / 1_000_000;
        System.out.println("Merge sort: " + min + "ms");
    }

    public static int[] randomArray(int arraySize){
        Random rnd = new Random();
        int[] array = new int[arraySize];
        for(int i = 0; i < array.length; i++){
            array[i] = rnd.nextInt(arraySize);
        }
        return array;
    }
}
