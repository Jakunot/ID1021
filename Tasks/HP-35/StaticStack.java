public class StaticStack {
        private static final int MAX_SIZE = 1024; // Maximum stack size
        private static final int[] array = new int[MAX_SIZE];
        private static int top = -1;

        // Push an element onto the stack
        public static void push(int value) {
            if (top < MAX_SIZE - 1) {
                array[++top] = value;
            } else {
                System.out.println("Stack Overflow" + value);
            }
        }

        // Pop an element from the stack
        public static int pop() {
            if (top >= 0) {
                return array[top--];
            } else {
                System.out.println("Stack is empty.");
                return -1;
            }
        }

}
