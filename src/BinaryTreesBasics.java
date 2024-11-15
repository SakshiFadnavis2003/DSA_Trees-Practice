public class BinaryTreesBasics {
    public static class Node {
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
        if (idx[0] >= nodes.length || nodes[idx[0]] == -1) { // Boundary check
            idx[0]++; // Move to the next index
            return null;
        }

        Node newNode = new Node(nodes[idx[0]]);
        idx[0]++; // Increment index for the next recursive calls
        newNode.left = buildTree(nodes, idx);
        newNode.right = buildTree(nodes, idx);
        return newNode;
    }

    public static int countOfNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + countOfNodes(root.left) + countOfNodes(root.right);
    }

    public static int sumOfNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return root.data + sumOfNodes(root.left) + sumOfNodes(root.right);
    }

    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static int diameter(Node root) {
        if (root == null) {
            return 0;
        }
        int leftDiameter = diameter(root.left);
        int rightDiameter = diameter(root.right);
        int diameter = height(root.left) + height(root.right) + 1;
        return Math.max(diameter, Math.max(leftDiameter, rightDiameter));
    }

    static class TreeInfo {
        int height;
        int diam;

        public TreeInfo(int height, int diam) {
            this.height = height;
            this.diam = diam;
        }
    }

    public static TreeInfo diameterTree(Node root) {
        if (root == null) {
            return new TreeInfo(0, 0);
        }

        TreeInfo left = diameterTree(root.left);
        TreeInfo right = diameterTree(root.right);

        int height = Math.max(left.height, right.height) + 1;

        int diameterThroughRoot = left.height + right.height + 1;
        int diam = Math.max(diameterThroughRoot, Math.max(left.diam, right.diam));

        return new TreeInfo(height, diam);
    }

    public static boolean isIdentical(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return (root1.data == root2.data) &&
                isIdentical(root1.left, root2.left) &&
                isIdentical(root1.right, root2.right);
    }

    //if root2 is the subtree of root1;
    public static boolean isSubtree(Node root1, Node root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (isIdentical(root1, root2)) {
            return true;
        }
        return isSubtree(root1.left, root2) || isSubtree(root1.right, root2);
    }

    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        int subtree1[] = {2, 4, -1, -1, 5, -1, -1};
        int invalidSubtree1[] = {2, 5, -1, -1, 4, -1, -1};

        int[] idx1 = {0};
        Node root = buildTree(nodes, idx1);

        int[] idx2 = {0};
        Node root2 = buildTree(subtree1, idx2);

        int[] idx3 = {0};
        Node root3 = buildTree(invalidSubtree1, idx3);

        System.out.println("Total number of nodes: " + countOfNodes(root));
        System.out.println("Sum of nodes: " + sumOfNodes(root));
        System.out.println("Height of tree: " + height(root));
        System.out.println("Diameter of tree: " + diameter(root));
        System.out.println("Optimized Diameter of tree: " + diameterTree(root).diam);

        System.out.println("Valid Subtree: " + isSubtree(root, root2));
        System.out.println("Invalid Subtree: " + isSubtree(root, root3));
    }
}
