import java.util.Random;


public class Main {

    public static void main(String[] args) {
        System.out.println(binary_search_benchmark(100000));
        System.out.println(sorted_search_benchmark(100000));
        System.out.println(unsorted_search_benchmark(100000));
        benchmark2();

    }

    public static double binary_search_benchmark(int n){
        Random rnd = new Random();
        int [] array = AssignmentMethods.sorted(n);
        double result = 0;
        for(int i = 0; i < 100000; i++){
            int key = rnd.nextInt(array.length -1);
            AssignmentMethods.binary_search(array, key);
            long startTime = System.nanoTime();
            AssignmentMethods.binary_search(array, key);
            result += (double)(System.nanoTime() - startTime);
        }
        return result;
    }

    public static double sorted_search_benchmark(int n){
        Random rnd = new Random();
        int [] array = AssignmentMethods.sorted(n);
        double result = 0;
        for(int i = 0; i < 100000; i++){
            int key = rnd.nextInt(array.length -1);
            long startTime = System.nanoTime();
            AssignmentMethods.search_sorted(array, key);
            result += (double)(System.nanoTime() - startTime);
        }
        return result;
    }

    public static double unsorted_search_benchmark(int n){
        Random rnd = new Random();
        int [] array = AssignmentMethods.unsorted(n);
        double result = 0;
        for(int i = 0; i < 100000; i++){
            int key = rnd.nextInt(array.length -1);
            AssignmentMethods.search_unsorted(array, key);
            long startTime = System.nanoTime();
            AssignmentMethods.search_unsorted(array, key);
            result += (double)(System.nanoTime() - startTime);
        }
        return result;
    }

    public static void benchmark2(){
        int [ ] nValues = {1_000, 10_000, 100_000, 1000_000};
        long t = 0, t1,t2;
        int n = 1000;
        Random r = new Random();
        for (int i = 0; i < nValues.length; i++) {
            int key = r.nextInt();
            for (int j = 0; j < n ; j++) {
                int [] a1 = AssignmentMethods.sorted(nValues[i]);
                t1 = System.nanoTime();
                AssignmentMethods.binary_search(a1,key);
                t2 = System.nanoTime();
                t += t2 - t1;
            }
            System.out.println("Time taken for n = " + nValues[i] + "--->" + t);
        }
    }
}