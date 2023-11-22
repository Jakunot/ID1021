public class Queue<Item> {
    private Node head;
    private Node tail;

    private class Node{
        Item item;
        Node next;

        private Node(Item item, Node next){
            this.item = item;
            this.next = next;
        }
    }

    public Queue(){
        head = null;
        tail = null;
    }

    public void add(Item item){
        Node newNode = new Node(item, null);
        if(isEmpty()){
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
    }

    public Item remove(){
        if(isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        Item removedItem = head.item;
        head = head.next;
        if(head == null){
            tail = null;
        }
        return removedItem;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void print() {
        if (this.head == null) {
            System.out.println("print(): queue is empty");
            return;
        }
        Node current = this.head;
        System.out.println("start of queue");
        while (current != null) {
            System.out.println(current.item);
            current = current.next;
        }
    }
    public static void main(String[] args) {
        Queue queue = new Queue<Integer>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        queue.add(4);
        queue.add(6);
        System.out.println(queue.remove());
        queue.print();
    }
}
