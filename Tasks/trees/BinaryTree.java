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

    @Override
    public Iterator<Integer> iterator(){
        return new TreeIterator(root);
    }

    public class TreeIterator implements Iterator<Integer>{
        private Node next;
        private Stack<Node> stack;

        public TreeIterator(Node current){
            stack = new Stack<Node>();
            initiateStack(current);
        }

        private void initiateStack(Node current){
            while(current != null){
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public Integer next(){
            if(!hasNext()){
                throw new IllegalStateException("No more elements");
            }
            Node current = stack.pop();
            if(current.right != null){
                initiateStack(current.right);
            }
            next = current;
            return current.value;
        }

        @Override
        public boolean hasNext(){
            return !stack.isEmpty();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
