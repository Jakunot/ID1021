public class DynamicStack {
    private int capacity;
    private int[] array;
    private int top;

    public DynamicStack() {
        capacity = 4; // Initial capacity is at 4
        array = new int[capacity];
        top = -1;
    }

    public void push(int value) {
        if (isFull()) {
            resize(); // if array is full, increase array size
        }
        array[++top] = value; //insert value into the array
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return -1; // Or throw an exception
        }else
            return array[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    private boolean isFull() {
        return top == capacity - 1;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        int[] newStackArray = new int[newCapacity];
        System.arraycopy(array, 0, newStackArray, 0, capacity);
        array = newStackArray;
        capacity = newCapacity;
    }

}
