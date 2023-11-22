public class HeapList {

    public class Node{

        public int value;
        public Node next;

        Node(int data){
            this.value = value;
        }
    }

    public Node head;
    public Node tail;

    /*
        add new values at the end of the list with constant time O(1) since it
        adds to the end. This is because the size of the list does not matter
     */
    public void addConstant(int value){
        Node newNode = new Node(value);
        if(head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
    }

    /*
        this adds a new node with the given 'key' to the list while maintaining the nodes
        in ascending order based on their value. It has a time complexity of O(n)
        in the worst case since it may need to traverse the list to find the correct position.
     */
    public void addLinear(int key) {
        Node newNode = new Node(key);

        if (head == null || head.value >= key) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            while ((current.next != null) && (current.next.value < key)) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    /*
        this method removes the node with highest priority, basically the head of
        the list which should have time complexity of O(1) since the size of the list
        does not matter
     */

    public Node removeConstant(){
        if(head == null){
            return null;
        }
        else{
            Node temp = head;
            head = head.next;
            return temp;
        }
    }

    /*
        This method removes the node with the given value from the list and returns
        it. Has a complexity of O(n) in the worst case since the list might be huge.
     */
    public Node removeLinear(int value) {
        if (head == null) {
            return null; // Nothing to remove from an empty list.
        }

        if (head.value == value) {
            Node removedNode = head;
            head = head.next;
            return removedNode;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.value == value) {
                Node removedNode = current.next;
                current.next = current.next.next;
                return removedNode;
            }
            current = current.next;
        }

        return null; // Data not found in the list.
    }

    public int length(){
        Node current = head;
        int size = 0;
        while(current!=null){
            size++;
            current = current.next;
        }
        return size;
    }

    @Override
    public String toString() {
        System.out.println("Elements in this List :\t");
        StringBuilder stringBuilder = new StringBuilder();
        Node current = head;
        if(head == null){
            return "No Elements";
        }
        else{
            while(current != null){
                stringBuilder.append(current.value + "\t");
                current = current.next;
            }
        }
        return stringBuilder.toString();
    }
}
