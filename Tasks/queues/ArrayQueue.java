public class ArrayQueue<Item> {
    private Item[] queue;
    private int front; // Index of the front element
    private int rear;  // Index of the rear element
    private int size;  // Current size of the queue

    public ArrayQueue(int capacity) {
        queue = (Item[]) new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void add(Item item) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % queue.length;
        queue[rear] = item;
        size++;
    }

    public Item remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        Item removedItem = queue[front];
        front = (front + 1) % queue.length;
        size--;
        return removedItem;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == queue.length;
    }

    public int size() {
        return size;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        int current = front;
        System.out.println("Queue elements:");
        for (int i = 0; i < size; i++) {
            System.out.print(queue[current] + " ");
            current = (current + 1) % queue.length;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        System.out.println("Removed item: " + queue.remove());

        queue.print();
    }
}
