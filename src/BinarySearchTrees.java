import java.util.*;

public class BinarySearchTrees {
    static class Node {
        int data;
        Node left, right;
        public Node(int data) {
            this.data = data;
        }
    }

    public static Node insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            return root;
        }
        if (root.data > val) {
            root.left = insert(root.left, val);
        } else if (root.data < val) {
            root.right = insert(root.right, val);
        }
        return root;
    }

    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static boolean search(Node root, int val) {
        if (root == null) {
            return false;
        }
        if (root.data == val) {
            return true;
        } else if (root.data > val) {
            return search(root.left, val); // Fixed: return result of recursive search
        } else {
            return search(root.right, val); // Fixed: return result of recursive search
        }
    }

    public static Node delete(Node root, int val) {
        if (root == null) {
            return null;
        }
        if (root.data == val) {
            // Case 1: No child
            if (root.left == null && root.right == null) {
                return null;
            }
            // Case 2: One child
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // Case 3: Two children
            Node is = inorderSuccessor(root.right);
            root.data = is.data;
            root.right = delete(root.right, is.data);
        } else if (root.data < val) {
            root.right = delete(root.right, val);
        } else {
            root.left = delete(root.left, val);
        }
        return root;
    }

    public static Node inorderSuccessor(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static void PrintInRange(Node root, int x, int y) {
        if (root == null) {
            return;
        }
        if (root.data >= x && root.data <= y) {
            PrintInRange(root.left, x, y);
            System.out.print(root.data + " ");
            PrintInRange(root.right, x, y);
        } else if (root.data >= y) {
            PrintInRange(root.left, x, y);
        } else {
            PrintInRange(root.right, x, y);
        }
    }

    public static void printPath(ArrayList<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + " ");
        }
        System.out.println();
    }

    public static void printRoot2Leaf(Node root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.data);
        if (root.left == null && root.right == null) {
            printPath(list);
        } else {
            printRoot2Leaf(root.left, list);
            printRoot2Leaf(root.right, list);
        }
        list.remove(list.size() - 1);
    }

    private void storeInOrder(Node root, List<Node> nodes) {
        if (root == null) {
            return;
        }
        storeInOrder(root.left, nodes);
        nodes.add(root);
        storeInOrder(root.right, nodes);
    }

    // Function to convert sorted list to a balanced BST
    private Node sortedListToBST(List<Node> nodes, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node node = nodes.get(mid);

        // Recursively construct the left and right subtrees
        node.left = sortedListToBST(nodes, start, mid - 1);
        node.right = sortedListToBST(nodes, mid + 1, end);

        return node;
    }

    // Main function to balance a given BST
    public Node balanceBST(Node root) {
        List<Node> nodes = new ArrayList<>();

        // Store nodes of the BST in sorted order
        storeInOrder(root, nodes);

        // Convert sorted nodes list to a balanced BST
        return sortedListToBST(nodes, 0, nodes.size() - 1);
    }

    // Helper function to print the tree in order (for testing purposes)
    public void printInOrder(Node node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.data + " ");
        printInOrder(node.right);
    }

    public static void main(String[] args) {
        int[] values = {5, 1, 3, 4, 2, 7};
        Node root = null;
        for (int value : values) {
            root = insert(root, value);
        }

        System.out.print("Inorder Traversal: ");
        inorder(root);

        System.out.println("\nSearching for 7: " + search(root, 7)); // Fixed: ensure proper return in `search`

        root = delete(root, 5); // Updated: don't print `delete` result directly
        System.out.print("Inorder Traversal after deletion of 5: ");
        inorder(root);

        System.out.println("\nPrint nodes in range (1, 5): ");
        PrintInRange(root, 1, 5);

        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("\nRoot-to-Leaf Paths:");
        printRoot2Leaf(root, list);
    }
}
