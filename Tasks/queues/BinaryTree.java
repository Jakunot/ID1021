import java.util.*;

public class BinaryTree implements Iterable<Integer>{

    /*
        A nested class Node to describe the internal structure of the tree.
    */
    public class Node{

        public Integer key;
        public Integer value;
        public Node left, right;

        public Node(Integer key, Integer value){
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }

        public void add(Integer key, Integer value) {
            if(this.key == key){
                this.value = value;
            }
            if(this.key > key){
                if(this.left == null){
                    this.left = new Node(key, value);
                }else{
                    this.left.add(key, value);
                }
            }else{
                if(this.right == null){
                    this.right = new Node(key, value);
                }else{
                    this.right.add(key, value);
                }
            }
        }

        public Integer lookup(Integer key){
            Node current = this;
            while(current != null){
                if(current.key == key){
                    return current.value;
                }else if(current.key > key){
                    current = current.left;
                }else{
                    current = current.right;
                }
            }
            return null;
        }

        public void print(){
            if(left != null)
                left.print();
            System.out.println(" key: " + key + "\tvalue: " + value);
            if(right != null)
                right.print();
        }
    }

    Node root;

    public BinaryTree(){
        root = null;
    }

    public void add(Integer key, Integer value){
        if(root == null){
            root = new Node(key, value);
        }else{
            root.add(key, value);
        }
    }

    public Integer lookup(Integer key){
        return root.lookup(key);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.add(5, 105);
        tree.add(2, 102);
        tree.add(7, 107);
        tree.add(1, 101);
        tree.add(8, 108);
        tree.add(6, 106);
        tree.add(3, 103);

        for (int i : tree) {
            System.out.println("next value " + i);
        }
    }

    @Override
    public Iterator<Integer> iterator(){
        return new TreeIterator(root);
    }

    public class TreeIterator implements Iterator<Integer>{
        private Queue<Node> queue;

        public TreeIterator(Node root){
            queue = new Queue<Node>();
            queue.add(root);
        }

        @Override
        public Integer next(){
            if(!hasNext()){
                throw new IllegalStateException("No more elements");
            }
            Node current = queue.remove();
            if(current.left != null){
                queue.add(current.left);
            }
            if (current.right != null){
                queue.add(current.right);
            }
            return current.value;
        }

        @Override
        public boolean hasNext(){
            return !queue.isEmpty();
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
