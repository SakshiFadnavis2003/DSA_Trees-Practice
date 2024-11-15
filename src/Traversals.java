import java.util.LinkedList;
import java.util.Queue;

public class Traversals {

    public static class Node{
        int data;
        Node left;
        Node right;
        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static Node buildTree(int[] nodes, int[] idx) {
        if (nodes[idx[0]] == -1) { // if null
            idx[0]++; // Move to the next index
            return null;
        }

        Node newNode = new Node(nodes[idx[0]]);
        idx[0]++; // Increment index for the next recursive calls
        newNode.left = buildTree(nodes, idx);
        newNode.right = buildTree(nodes, idx);
        return newNode;
    }

    //O(N) complexity
    //DFS
    public static void preorder(Node root) {
            if (root == null) {
                return;
            }
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
    }

    //O(N) complexity
    //DFS
    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    //O(N) complexity
    //DFS
    public static void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    public static void levelOrder(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()) {
            Node data = queue.remove();
            if (data == null) {
                System.out.println();
                if (queue.isEmpty()) {
                    break;
                } else {
                    queue.add(null);
                }
            } else {
                System.out.print(data.data + " "); // Access the data field to print the integer value
                if (data.left != null) {
                    queue.add(data.left);
                }
                if (data.right != null) {
                    queue.add(data.right);
                }
            }
        }
    }


    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        int[] idx = {0}; // Use an array to maintain the index across recursive calls
        Node root = buildTree(nodes, idx);
        System.out.println("\nPreorder : ");
        preorder(root);
        System.out.println();
        System.out.println("\nInorder : ");
        inorder(root);
        System.out.println();
        System.out.println("\nPostorder : ");
        postorder(root);
        System.out.println();
        System.out.println("\nLevelorder : ");
        levelOrder(root);

    }
}
