// Binary Search Tree revision methods.
// File name starts with a number, so this class is package-private.
class TreesB {

    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    // 1. Insert a value. Duplicate values are ignored.
    static Node insert(Node root, int val) {
        if (root == null)
            return new Node(val);

        if (val < root.val)
            root.left = insert(root.left, val);
        else if (val > root.val)
            root.right = insert(root.right, val);

        return root;
    }

    // 2. Search for a value.
    static boolean search(Node root, int val) {
        if (root == null)
            return false;
        if (root.val == val)
            return true;

        if (val < root.val)
            return search(root.left, val);

        return search(root.right, val);
    }

    // 3. Find the node with the minimum value.
    static Node findMin(Node root) {
        if (root == null)
            return null;

        while (root.left != null)
            root = root.left;

        return root;
    }

    // 4. Delete a value and return the updated root.
    static Node delete(Node root, int val) {
        if (root == null)
            return null;

        if (val < root.val) {
            root.left = delete(root.left, val);
        } else if (val > root.val) {
            root.right = delete(root.right, val);
        } else {
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;

            Node successor = findMin(root.right);
            root.val = successor.val;
            root.right = delete(root.right, successor.val);
        }

        return root;
    }

    // 5. Inorder traversal gives sorted order.
    static void inorder(Node root) {
        if (root == null)
            return;

        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }
}
