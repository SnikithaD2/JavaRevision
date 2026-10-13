import java.util.*;

class StackProblems {

    // 1. Next Greater Element
    // First greater element on the right.
    public int[] nextGreater(int[] arr, int n) {
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans, -1);

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[i] > arr[st.peek()]) {
                ans[st.pop()] = arr[i];
            }
            st.push(i);
        }

        return ans;
    }

    // 2. Next Smaller Element
    // First smaller element on the right.
    public int[] nextSmaller(int[] arr, int n) {
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans, -1);

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[i] < arr[st.peek()]) {
                ans[st.pop()] = arr[i];
            }
            st.push(i);
        }

        return ans;
    }

    // 3. Previous Greater Element
    // First greater element on the left.
    public int[] previousGreater(int[] arr, int n) {
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans, -1);

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[i] > arr[st.peek()]) {
                ans[st.pop()] = arr[i];
            }
            st.push(i);
        }

        return ans;
    }

    // 4. Previous Smaller Element
    // First smaller element on the left.
    public int[] previousSmaller(int[] arr, int n) {
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans, -1);

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[i] < arr[st.peek()]) {
                ans[st.pop()] = arr[i];
            }
            st.push(i);
        }

        return ans;
    }
}
