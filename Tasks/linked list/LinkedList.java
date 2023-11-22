public class LinkedList {

    private Node first; //points to the first node in the list

    public LinkedList(){
        first = null;
    }


    //adds an item as the first node in the sequence
    public void add(int item){
        Node newNode = new Node(item, first);
        first = newNode;
    }

    //in order to return the length of the sequence
    public int length(){
        int count = 0;
        Node current = first;
        while(current != null){
            count++;
            current = current.next;
        }
        return count;
    }

    //checking whether an item is in the linked list
    public boolean find(int item){
        Node current = first;
        while(current != null){
            if(current.head == item){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    //removing an item from the linked list
    public void remove(int item) {
        if (first == null) {
            return; // Empty list, nothing to remove
        }

        if (first.head == item) {
            first = first.next; // Remove the item by updating the head
            return;
        }

        Node current = first;
        while (current.next != null && current.next.head != item) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next; // Remove the item by updating references
        }
    }

    //add a sequence to the end of a sequence
    public void append(LinkedList node) {
        if (node.first == null) {
            return; // Nothing to append from the other list
        }

        if (first == null) {
            first = node.first; // If this list is empty, simply point to the other list's head
        } else {
            Node current = first;
            while (current.next != null) {
                current = current.next; // Move to the last node of this list
            }
            current.next = node.first; // Make the last node point to the head of the other list
        }

        // Clear the other list
        node.first = null;
    }

    public void appendToBeginning(LinkedList node) {
        if (node.first == null) {
            return; // Nothing to append from the other list
        }

        if (first == null) {
            first = node.first; // If this list is empty, simply point to the other list's head
        } else {
            // Make the head of the other list point to the current list's head
            node.first.next = first;

            // Update the current list's head to be the head of the other list
            first = node.first;
        }

        // Clear the other list
        node.first = null;
    }

}
