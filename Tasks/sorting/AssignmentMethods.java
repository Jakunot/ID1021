import java.util.Random;

public class AssignmentMethods {

    public static boolean search_unsorted(int[] array, int key){
        for(int index = 0; index < array.length; index++){
            if(array[index] == key){
                return true;
            }
        }
        return false;
    }

    public static boolean search_sorted(int[] array, int key){
        for(int index = 0; index < array.length; index++){
            if(array[index] == key){
                return true;
            }else if(key < array[index])
                return false;
        }
        return false;
    }

    public static int[] sorted(int n){
        Random rnd = new Random();
        int [] array = new int[n];
        int nxt = 0;
        for(int i = 0; i < n; i++){
            nxt += rnd.nextInt(10) + 1;
            array[i] = nxt;
        }
        return array;
    }
    public static int [] unsorted(int n){
        Random rnd = new Random();
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array [i] = rnd.nextInt(100_000);
        }
        return array;
    }

    public static boolean binary_search(int[] array, int key){
        int first = 0;
        int last = array.length-1;
        int mid = 0;
        while(last <= first){
            mid = ((first + last)/2);
            if(array[mid] == key){
                return true;
            } else if (key > array[mid]) {
                first = mid + 1;
            }else
                last = mid - 1;
        }
        return false;
    }

    public static void search_duplicates(int[] arr1, int[] arr2){
        for(int i = 0; i < arr1.length; i++){
            binary_search(arr2, arr1[i]);
        }
    }
}


