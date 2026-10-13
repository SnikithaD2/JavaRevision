import java.util.*;

// General Binary Tree revision methods.
// File name starts with a number, so this class is package-private.
class TreesA {

    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    // 1. Diameter: number of nodes on the longest path.
    private int diameter;

    int diameterOfTree(Node root) {
        diameter = 0;
        height(root);
        return diameter;
    }

    private int height(Node root) {
        if (root == null)
            return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        diameter = Math.max(diameter, leftHeight + rightHeight + 1);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 2. Count nodes at level k (root is at level 0).
    int nodesAtKLevel(Node root, int k) {
        if (root == null || k < 0)
            return 0;

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            if (level == k)
                return size;

            for (int i = 0; i < size; i++) {
                Node node = q.poll();

                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }

            level++;
        }

        return 0;
    }

    // 3. Right view.
    List<Integer> rightView(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node node = q.poll();

                if (i == size - 1)
                    ans.add(node.val);

                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }
        }

        return ans;
    }

    // 4. Left view.
    List<Integer> leftView(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node node = q.poll();

                if (i == 0)
                    ans.add(node.val);

                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }
        }

        return ans;
    }

    // 5. Zigzag level-order traversal.
    List<Integer> zigZagTraversal(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        boolean leftToRight = true;

        while (!q.isEmpty()) {
            int size = q.size();
            int[] level = new int[size];

            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                int index = leftToRight ? i : size - 1 - i;
                level[index] = node.val;

                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }

            for (int value : level)
                ans.add(value);

            leftToRight = !leftToRight;
        }

        return ans;
    }

    static class ViewPair {
        Node node;
        int hd;

        ViewPair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    // 6. Top view.
    List<Integer> topView(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<ViewPair> q = new LinkedList<>();
        q.add(new ViewPair(root, 0));

        while (!q.isEmpty()) {
            ViewPair pair = q.poll();

            if (!map.containsKey(pair.hd))
                map.put(pair.hd, pair.node.val);

            if (pair.node.left != null)
                q.add(new ViewPair(pair.node.left, pair.hd - 1));
            if (pair.node.right != null)
                q.add(new ViewPair(pair.node.right, pair.hd + 1));
        }

        ans.addAll(map.values());
        return ans;
    }

    // 7. Bottom view.
    List<Integer> bottomView(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<ViewPair> q = new LinkedList<>();
        q.add(new ViewPair(root, 0));

        while (!q.isEmpty()) {
            ViewPair pair = q.poll();
            map.put(pair.hd, pair.node.val);

            if (pair.node.left != null)
                q.add(new ViewPair(pair.node.left, pair.hd - 1));
            if (pair.node.right != null)
                q.add(new ViewPair(pair.node.right, pair.hd + 1));
        }

        ans.addAll(map.values());
        return ans;
    }

    // 8. Lowest Common Ancestor in a binary tree.
    Node lca(Node root, int a, int b) {
        if (root == null || root.val == a || root.val == b)
            return root;

        Node left = lca(root.left, a, b);
        Node right = lca(root.right, a, b);

        if (left != null && right != null)
            return root;

        return left != null ? left : right;
    }

    // 9. Find path from root to a target node.
    boolean findPath(Node root, int target, List<Node> path) {
        if (root == null)
            return false;

        path.add(root);

        if (root.val == target)
            return true;

        if (findPath(root.left, target, path)
                || findPath(root.right, target, path))
            return true;

        path.remove(path.size() - 1);
        return false;
    }

    // 10. Sum of left boundary, excluding leaves.
    private int sumLeftBoundary(Node node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;

        if (node.left != null)
            return node.val + sumLeftBoundary(node.left);

        return node.val + sumLeftBoundary(node.right);
    }

    // 11. Sum of right boundary, excluding leaves.
    private int sumRightBoundary(Node node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;

        if (node.right != null)
            return node.val + sumRightBoundary(node.right);

        return node.val + sumRightBoundary(node.left);
    }

    private int sumLeaves(Node node) {
        if (node == null)
            return 0;

        if (node.left == null && node.right == null)
            return node.val;

        return sumLeaves(node.left) + sumLeaves(node.right);
    }

    private int sumAll(Node node) {
        if (node == null)
            return 0;

        return node.val + sumAll(node.left) + sumAll(node.right);
    }

    // 12. Sum of uncovered nodes.
    int sumUncovered(Node root) {
        if (root == null)
            return 0;

        int sum = root.val;

        if (root.left != null)
            sum += sumLeftBoundary(root.left);
        if (root.right != null)
            sum += sumRightBoundary(root.right);

        return sum + sumLeaves(root.left) + sumLeaves(root.right);
    }

    // 13. Sum of covered nodes.
    int sumCovered(Node root) {
        return sumAll(root) - sumUncovered(root);
    }

    // 14. Mirror a tree in-place.
    void mirror(Node root) {
        if (root == null)
            return;

        Node temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirror(root.left);
        mirror(root.right);
    }

    // 15. Check whether two trees are mirror images.
    boolean isMirror(Node first, Node second) {
        if (first == null && second == null)
            return true;
        if (first == null || second == null)
            return false;

        return first.val == second.val
                && isMirror(first.left, second.right)
                && isMirror(first.right, second.left);
    }

    // 16. Count nodes at distance k from a target node.
    int nodesAtDistanceK(Node root, int target, int k) {
        if (root == null || k < 0)
            return 0;

        List<Node> path = new ArrayList<>();

        if (!findPath(root, target, path))
            return 0;

        int count = countAtDistance(path.get(path.size() - 1), k);

        // Each ancestor contributes nodes from the opposite subtree.
        for (int i = path.size() - 2, distance = 1; i >= 0; i--, distance++) {
            if (distance == k) {
                count++;
                break;
            }

            Node ancestor = path.get(i);
            Node childOnPath = path.get(i + 1);
            Node opposite = ancestor.left == childOnPath
                    ? ancestor.right : ancestor.left;

            count += countAtDistance(opposite, k - distance - 1);
        }

        return count;
    }

    private int countAtDistance(Node root, int k) {
        if (root == null || k < 0)
            return 0;
        if (k == 0)
            return 1;

        return countAtDistance(root.left, k - 1)
                + countAtDistance(root.right, k - 1);
    }

    // 17. Burn a tree from the node containing startValue.
    int burnTree(Node root, int startValue) {
        if (root == null)
            return 0;

        Map<Node, Node> parent = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.left != null) {
                parent.put(node.left, node);
                q.add(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                q.add(node.right);
            }
        }

        Node start = findNode(root, startValue);
        if (start == null)
            return 0;

        Set<Node> visited = new HashSet<>();
        q.add(start);
        visited.add(start);
        int time = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            boolean spread = false;

            for (int i = 0; i < size; i++) {
                Node node = q.poll();

                if (parent.containsKey(node) && visited.add(parent.get(node))) {
                    q.add(parent.get(node));
                    spread = true;
                }
                if (node.left != null && visited.add(node.left)) {
                    q.add(node.left);
                    spread = true;
                }
                if (node.right != null && visited.add(node.right)) {
                    q.add(node.right);
                    spread = true;
                }
            }

            if (spread)
                time++;
        }

        return time;
    }

    private Node findNode(Node root, int value) {
        if (root == null || root.val == value)
            return root;

        Node left = findNode(root.left, value);
        return left != null ? left : findNode(root.right, value);
    }
}
