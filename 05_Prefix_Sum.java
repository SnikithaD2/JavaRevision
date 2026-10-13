public class PrefixSum {

    // 1. Static Range Sum Query: Sum of elements from index L to R.
    // Time: O(n) preprocessing, O(1) per query.
    public static int sumRangeQuery(int[] arr, int L, int R) {
        int n = arr.length;
        int[] prefixSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        }

        return prefixSum[R + 1] - prefixSum[L];
    }

    // 2. Difference Array: Add x to every element in range [L, R].
    // After processing all queries, return the maximum element.
    // Time: O(n + q), where q is the number of queries.
    public static int rangeUpdatesMax(int n, int[][] queries) {
        int[] diff = new int[n + 1];

        // Each query contains [L, R, X].
        for (int[] query : queries) {
            int L = query[0];
            int R = query[1];
            int X = query[2];

            diff[L] += X;
            diff[R + 1] -= X;
        }

        int current = 0;
        int max = Integer.MIN_VALUE;

        // Prefix sum reconstructs the updated array.
        for (int i = 0; i < n; i++) {
            current += diff[i];
            max = Math.max(max, current);
        }

        return max;
    }
}
