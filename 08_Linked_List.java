public class LinkedListProblems {

    // Basic node structure used by all methods.
    static class LLNode {
        int data;
        LLNode next;

        LLNode(int data) {
            this.data = data;
        }
    }

    // 1. Merge two sorted linked lists.
    // Time: O(n + m), Space: O(1).
    public LLNode merge(LLNode L1, LLNode L2) {
        if (L1 == null) return L2;
        if (L2 == null) return L1;

        LLNode head;

        if (L1.data <= L2.data) {
            head = L1;
            L1 = L1.next;
        } else {
            head = L2;
            L2 = L2.next;
        }

        LLNode temp = head;

        while (L1 != null && L2 != null) {
            if (L1.data <= L2.data) {
                temp.next = L1;
                L1 = L1.next;
            } else {
                temp.next = L2;
                L2 = L2.next;
            }

            temp = temp.next;
        }

        // Attach the remaining nodes.
        temp.next = (L1 != null) ? L1 : L2;

        return head;
    }

    // 2. Check whether a linked list is a palindrome.
    // Time: O(n), Space: O(1).
    public boolean isPalindrome(LLNode root) {
        if (root == null || root.next == null) return true;

        LLNode mid = middle(root);
        LLNode secondHalf = reverse(mid.next);

        // Disconnect the first half.
        mid.next = null;

        LLNode first = root;
        LLNode second = secondHalf;
        boolean palindrome = true;

        while (second != null) {
            if (first.data != second.data) {
                palindrome = false;
                break;
            }

            first = first.next;
            second = second.next;
        }

        // Restore the original list.
        mid.next = reverse(secondHalf);

        return palindrome;
    }

    // Find the middle node using slow and fast pointers.
    private LLNode middle(LLNode root) {
        LLNode slow = root;
        LLNode fast = root;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Reverse a linked list iteratively.
    public LLNode reverse(LLNode root) {
        LLNode prev = null;
        LLNode curr = root;

        while (curr != null) {
            LLNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    // 3. Detect a cycle in a linked list.
    // Returns true if a cycle exists.
    // Time: O(n), Space: O(1).
    public boolean hasCycle(LLNode root) {
        LLNode slow = root;
        LLNode fast = root;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }

        return false;
    }

    // 4. Find the starting node of a cycle.
    // Returns null if no cycle exists.
    public LLNode cycleStart(LLNode root) {
        LLNode slow = root;
        LLNode fast = root;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                slow = root;

                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                return slow;
            }
        }

        return null;
    }

    // 5. Find the length of a cycle.
    // Returns 0 if no cycle exists.
    public int cycleLength(LLNode root) {
        LLNode slow = root;
        LLNode fast = root;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                int count = 1;
                LLNode temp = slow.next;

                while (temp != slow) {
                    count++;
                    temp = temp.next;
                }

                return count;
            }
        }

        return 0;
    }

    // 6. Remove a cycle and make the linked list acyclic.
    public void removeCycle(LLNode root) {
        LLNode start = cycleStart(root);

        if (start == null) return;

        LLNode temp = start;

        // Find the last node in the cycle.
        while (temp.next != start) {
            temp = temp.next;
        }

        temp.next = null;
    }
}
