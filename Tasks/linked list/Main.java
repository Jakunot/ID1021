
public class Main {
    public static void main(String[] args) {
        benchmarkAppendVaryingASize();
        benchmarkAppendVaryingBSize();
        }
    public static void benchmarkAppendVaryingASize() {
        int bSize = 1000; // Size of the fixed-size list b
        int[] aSizes = { 1000, 2000, 5000, 10000 }; // Varying sizes of list a

        for (int aSize : aSizes) {
            LinkedList a = createLinkedList(aSize);
            LinkedList b = createLinkedList(bSize);

            long startTime = System.nanoTime();
            b.append(a);
            long endTime = System.nanoTime();

            long elapsedTime = endTime - startTime;
            System.out.println("Size of a: " + aSize + ", Time (ns): " + elapsedTime);
        }
    }

    public static void benchmarkAppendVaryingBSize() {
        int aSize = 1000; // Size of list a
        int[] bSizes = { 1000, 2000, 5000, 10000}; // Varying sizes of list b

        LinkedList a = createLinkedList(aSize);

        for (int bSize : bSizes) {
            LinkedList b = createLinkedList(bSize);

            long startTime = System.nanoTime();
            b.append(a);
            long endTime = System.nanoTime();

            long elapsedTime = endTime - startTime;
            System.out.println("Size of b: " + bSize + ", Time (ns): " + elapsedTime);
        }
    }

    public static LinkedList createLinkedList(int size) {
        LinkedList list = new LinkedList();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }
}
