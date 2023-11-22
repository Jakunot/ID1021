public class SortingMethods {

    public static void selectionSort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            // Assume the current index has the minimum value
            int minIndex = i;
            // Find the index of the minimum element in the remaining unsorted part
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the minimum element with the first element in the unsorted part
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void insertionSort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return; // Base case: If the array has one or zero elements, it's already sorted.
        }

        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        // Split the array into two halves
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }

        // Recursively sort the two halves
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted halves
        merge(array, left, right);
    }

    private static void merge(int[] array, int[] left, int[] right) {

        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < left.length) {
            array[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            array[k] = right[j];
            j++;
            k++;
        }
    }

    public static void sort(int[] original) {
        if (original.length == 0) {
            return;
        }
        int[] auxiliary = new int[original.length];
        sort(original, auxiliary, 0, original.length - 1);
    }

    private static void sort(int[] original, int[] auxiliary, int low, int high) {
        if (low != high) {
            int middle = low + (high - low) / 2;
            sort(original, auxiliary, low, middle);
            sort(original, auxiliary, middle + 1, high);
            merge(original, auxiliary, low, middle, high);
        }
    }

    private static void merge(int[] original, int[] auxiliary, int low, int middle, int high) {
        // copy all items from lo to hi from org to aux
        for (int i = low; i <= high; i++) {
            auxiliary[i] = original[i];
        }
        // let's do the merging
        int i = low; // the index in the first part
        int j = middle + 1; // the index in the second part
        // for all indices from low to high
        for (int k = low; k <= high; k++) {
            // corner case
            // if i (low) is greater than mid, move the j item to the org array, update j
            if (i > middle) {
                original[k] = auxiliary[j++];

                // corner case
                // else if j is greater than hi, move the i item to the org array, update i
            } else if (j > high) {
                original[k] = auxiliary[i++];

                // else if the i item is smaller than the j item,
                // move it to the org array, update i
            } else if (auxiliary[i] < auxiliary[j]) {
                original[k] = auxiliary[i++];

                // else you can move the j item to the org array, update j
            } else {
                original[k] = auxiliary[j++];
            }
        }
    }
}

