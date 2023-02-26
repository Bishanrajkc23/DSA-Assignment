//Question 2
// b
// You are given an array of binary trees that represent different cities where a certain corporation has
//        its branch office and the organization wishes to provide service by constructing a service center.
//        Building service centers at any node, i.e., a city can give service to its directly connected cities
//        where it can provide service to its parents, itself, and its immediate children. Returns the smallest
//        number of service centers required by the corporation to provide service to all connected cities.
//        Note that: the root node represents the head office and other connected nodes represent the branch
//        office connected to the head office maintaining some kind of hierarchy.
//
//        Input: tree= {0,0, null, 0, null, 0, null, null, 0}
//        Output: 2
//        Explanation: construction of two service centers denoted by black markk will be enough to provide
//        service to all cities.



public class Q2_b {
    int res = 0;

    public int minCameraCover(TreeNode root) {
        return (dfs(root) < 1 ? 1 : 0) + res;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public int dfs(TreeNode root) {
        if (root == null) return 2;
        int left = dfs(root.left), right = dfs(root.right);
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        return left == 1 || right == 1 ? 2 : 0;
    }

    public static void main(String[] args) {
        // Create the binary tree from the given input
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = null;
        root.left.right = new TreeNode(0);
        root.left.right.left = null;
        root.left.right.right = new TreeNode(0);
        root.right = null;

        // Call the minCameraCover function after creating the Q2 b object.
        Q2_b solution = new Q2_b();
        int result = solution.minCameraCover(root);

        // Result is printed
        System.out.println(result);  // Output: 2
    }
}
