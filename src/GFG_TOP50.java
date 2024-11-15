import java.util.*;
public class GFG_TOP50 {
    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            left=null;
            right=null;
        }
    }
    public static boolean isSymmetric(Node root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }
    public static boolean isMirror(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return (root1.data == root2.data) && isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
    }

    public static boolean isBST(Node root) {
        if (root == null) {
            return true;
        }
        if((root.left.data < root.data) && (root.right.data < root.data)) {
            return true;
        }
        return isBST(root.left) && isBST(root.right);
    }
    public ArrayList<Integer> largestValues(Node root)
    {
        ArrayList<Integer> largest = new ArrayList<>();
        if(root == null){
            return largest;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int max = Integer.MIN_VALUE;
        queue.add(null);  // Marker for end of level
        while(!queue.isEmpty()){
            Node data2 = queue.remove();
            if(data2 == null) {  // Check for end of level
                largest.add(max);
                if(queue.isEmpty()){
                    break;
                } else {
                    queue.add(null);  // Add marker for next level
                    max = Integer.MIN_VALUE;  // Reset max for next level
                }
            } else {
                if(data2.data > max) {
                    max = data2.data;
                }
                if(data2.left != null) {
                    queue.add(data2.left);
                }
                if(data2.right != null) {
                    queue.add(data2.right);
                }
            }
        }
        return largest;
    }
    public static int gcd(int a, int b) {
        while(b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    static int maxBinTreeGCD(ArrayList<ArrayList<Integer>> arr, int N) {
        int max = Integer.MIN_VALUE;
        int i = 0;
        while(i < N) {
            int root1 = arr.get(i).get(0);
            // Check if i+1 is within bounds before accessing arr.get(i+1)
            if(i < N - 1) {
                int root2 = arr.get(i + 1).get(0);
                if(root2 == root1) {
                    int GCD = gcd(arr.get(i).get(1), arr.get(i + 1).get(1));
                    if(GCD > max) {
                        max = GCD;
                    }
                    // Skip the next element if root1 == root2
                    i = i + 2;
                } else {
                    max = Math.max(arr.get(i).get(1), max);
                    i++;
                }
            } else {
                // Handle the last element if i is the last index
                max = Math.max(arr.get(i).get(1), max);
                i++;
            }
        }
        return max;
    }
    public static ArrayList<Integer> zigZagTraversal(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        int i= 0;
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()) {
            Node data = queue.remove();
            result.add(root.data);
            if (data == null) {
                i++;
                if (queue.isEmpty()) {
                    break;
                } else {
                    queue.add(null);
                }
            } else {
                System.out.print(data.data + " ");
                if(i%2!=0){
                    if (data.left != null) {
                        queue.add(data.left);
                    }
                    if (data.right != null) {
                        queue.add(data.right);
                    }
                }
                else{
                    if (data.right != null) {
                        queue.add(data.right);
                    }
                    if (data.left != null) {
                        queue.add(data.left);
                    }
                }

            }
        }
        return result;
    }




}
