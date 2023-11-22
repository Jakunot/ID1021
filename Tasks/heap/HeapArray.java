public class HeapArray {
    private Integer[] heap;
    private int size;
    private final int capacity;

    public HeapArray(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new Integer[capacity + 1];
    }

    public void insert(int key) {
        if (size >= capacity) {
            System.out.println("Heap is full. Cannot insert " + key);
            return;
        }
        size++;
        heap[size] = key;
        heapifyUp(size);
    }

    public int remove() {
        if (size == 0) {
            System.out.println("Heap is empty.");
            return Integer.MIN_VALUE;
        }

        int min = heap[1];
        heap[1] = heap[size];
        size--;
        heapifyDown(1);

        return min;
    }

    private void heapifyUp(int index) {
        int parent = index / 2;
        while (index > 1 && heap[index] < heap[parent]) {
            swap(index, parent);
            index = parent;
            parent = index / 2;
        }
    }

    private void heapifyDown(int index) {
        int left = 2 * index;
        int right = left + 1;
        int smallest = index;

        if (left <= size && heap[left] < heap[smallest]) {
            smallest = left;
        }

        if (right <= size && heap[right] < heap[smallest]) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void print() {
        for (int i = 1; i <= size / 2; i++) {
            int parent = heap[i];
            int leftChild = heap[2 * i];
            int rightChild = (2 * i + 1 <= size) ? heap[2 * i + 1] : -1;
            System.out.println("PARENT: " + parent + " LEFT CHILD: " + leftChild + " RIGHT CHILD: " + rightChild);
        }
    }
}
