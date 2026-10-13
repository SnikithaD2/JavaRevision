import java.util.*;

class QueueProblems {

    // 1. Level Order Traversal
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null)
            return ans;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int n = q.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                TreeNode node = q.poll();
                level.add(node.val);

                if (node.left != null)
                    q.add(node.left);

                if (node.right != null)
                    q.add(node.right);
            }

            ans.add(level);
        }

        return ans;
    }

    // 2. Minimum Steps to Reduce N to 1
    static class Pair {
        int val, steps;

        Pair(int val, int steps) {
            this.val = val;
            this.steps = steps;
        }
    }

    public int reduceToOne(int n) {
        if (n <= 0)
            return -1;

        Queue<Pair> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        q.add(new Pair(n, 0));
        visited[n] = true;

        while (!q.isEmpty()) {
            Pair p = q.poll();

            if (p.val == 1)
                return p.steps;

            if (p.val % 2 == 0 && !visited[p.val / 2]) {
                q.add(new Pair(p.val / 2, p.steps + 1));
                visited[p.val / 2] = true;
            }

            if (p.val % 3 == 0 && !visited[p.val / 3]) {
                q.add(new Pair(p.val / 3, p.steps + 1));
                visited[p.val / 3] = true;
            }

            if (p.val > 1 && !visited[p.val - 1]) {
                q.add(new Pair(p.val - 1, p.steps + 1));
                visited[p.val - 1] = true;
            }
        }

        return -1;
    }

    // 3. Burn a Tree
    public int burnTree(TreeNode root, int startValue) {
        if (root == null)
            return 0;

        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        // Store each node's parent.
        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            if (node.left != null) {
                parent.put(node.left, node);
                q.add(node.left);
            }

            if (node.right != null) {
                parent.put(node.right, node);
                q.add(node.right);
            }
        }

        TreeNode start = search(root, startValue);

        if (start == null)
            return 0;

        Set<TreeNode> visited = new HashSet<>();
        q.add(start);
        visited.add(start);

        int time = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            boolean burnedMore = false;

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                // Fire spreads to the parent.
                if (parent.containsKey(node)
                        && visited.add(parent.get(node))) {
                    q.add(parent.get(node));
                    burnedMore = true;
                }

                // Fire spreads to the left child.
                if (node.left != null && visited.add(node.left)) {
                    q.add(node.left);
                    burnedMore = true;
                }

                // Fire spreads to the right child.
                if (node.right != null && visited.add(node.right)) {
                    q.add(node.right);
                    burnedMore = true;
                }
            }

            if (burnedMore)
                time++;
        }

        return time;
    }

    private TreeNode search(TreeNode root, int value) {
        if (root == null || root.val == value)
            return root;

        TreeNode left = search(root.left, value);

        if (left != null)
            return left;

        return search(root.right, value);
    }
}
