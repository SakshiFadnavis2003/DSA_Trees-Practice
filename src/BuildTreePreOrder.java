public class BuildTreePreOrder {
    // Make Node a static class
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Helper method to build the tree with a mutable index
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

    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        int[] idx = {0}; // Use an array to maintain the index across recursive calls
        Node root = buildTree(nodes, idx);
        System.out.println(root.data);
    }
}
