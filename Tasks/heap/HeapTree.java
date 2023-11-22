public class HeapTree {

    public static class TreeNode{
        public Integer value;
        public TreeNode left, right;

        public TreeNode(Integer value){
            this.value = value;
            this.left = this.right = null;
        }
    }

    public static TreeNode root;
    private static int size;

    /*
        this method inserts a new element into the min heap, if the root is null
        create a new root node with the provided value. if not empty, it calls the
        insertNode below which recursively finds the appropriate locations for
        the new element
     */
    public static void insert(Integer value) {
        if (root == null) {
            root = new TreeNode(value);
        } else {
            insertNode(root, value);
        }
        size++;
    }

    private static void insertNode(TreeNode current, Integer data) {
        if (current.left == null) {
            current.left = new TreeNode(data);
        } else if (current.right == null) {
            current.right = new TreeNode(data);
        } else if (current.left.value <= current.right.value) {
            insertNode(current.left, data);
        } else {
            insertNode(current.right, data);
        }
    }

    public static Integer extractMin() {
        if (root == null) {
            return null;
        }

        Integer min = root.value;
        TreeNode lastNode = removeLastNode(root);
        if (lastNode != null) {
            root.value = lastNode.value;
        }
        size--;

        pushDown(root);

        return min;
    }

    private static TreeNode removeLastNode(TreeNode current) {
        if (current == null) {
            return null;
        }

        if (current.left == null || (current.right != null && current.left.value > current.right.value)) {
            TreeNode lastNode = removeLastNode(current.right);
            if (lastNode != null) {
                return lastNode;
            }
            return current.left;
        } else {
            TreeNode lastNode = removeLastNode(current.left);
            if (lastNode != null) {
                return lastNode;
            }
            return current.right;
        }
    }


    private static void pushDown(TreeNode current) {
        if (current == null) {
            return;
        }

        TreeNode minChild = null;

        if (current.left != null && (current.right == null || current.left.value < current.right.value)) {
            minChild = current.left;
        } else if (current.right != null) {
            minChild = current.right;
        }

        if (minChild != null && minChild.value < current.value) {
            swapNodes(current, minChild);
            pushDown(minChild);
        }
    }

    private static void swapNodes(TreeNode n1, TreeNode n2) {
        Integer temp = n1.value;
        n1.value = n2.value;
        n2.value = temp;
    }
    public static int size() {
        return size;
    }
    private static void printTree(TreeNode node) {
        if (node != null) {
            printTree(node.left);
            System.out.println("Data: " + node.value);
            printTree(node.right);
        }
    }

    public static boolean isEmpty() {
        return size == 0;
    }

    public static void clear() {
        root = null;
        size = 0;
    }
}


