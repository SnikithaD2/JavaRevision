import java.util.*;

public class PriorityQueueProblems {

    // 1. Minimum Cost to Make Longest Chain
    // Repeatedly merge the two smallest chains.
    // Time: O(n log n), Space: O(n).
    public int minimumCost(int[] arr, int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int x : arr) {
            pq.add(x);
        }

        int totalCost = 0;

        while (pq.size() > 1) {
            int x = pq.poll();
            int y = pq.poll();

            int cost = x + y;
            totalCost += cost;
            pq.add(cost);
        }

        return totalCost;
    }

    // 2. Running Median
    // Max heap stores the smaller half; min heap stores the larger half.
    // Time: O(n log n), Space: O(n).
    public float[] runningMedian(int[] arr, int n) {
        float[] ans = new float[n];

        PriorityQueue<Integer> left =
                new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int x = arr[i];

            if (left.isEmpty() || x <= left.peek()) {
                left.add(x);
            } else {
                right.add(x);
            }

            // Keep heap sizes balanced.
            if (left.size() > right.size() + 1) {
                right.add(left.poll());
            } else if (right.size() > left.size() + 1) {
                left.add(right.poll());
            }

            if (left.size() == right.size()) {
                // Cast before addition to avoid integer overflow.
                ans[i] = ((float) left.peek() + right.peek()) / 2;
            } else if (left.size() > right.size()) {
                ans[i] = left.peek();
            } else {
                ans[i] = right.peek();
            }
        }

        return ans;
    }

    // 3. Meeting Rooms
    // Reuse a room when its meeting ends before or when the next starts.
    // Time: O(n log n), Space: O(n).
    public int meetingRooms(int[][] intervals, int n) {
        if (n == 0) return 0;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] meeting : intervals) {
            // Remove all meetings that have already ended.
            while (!pq.isEmpty() && pq.peek() <= meeting[0]) {
                pq.poll();
            }

            pq.add(meeting[1]);
        }

        // Each heap entry represents an ongoing meeting.
        return pq.size();
    }

    // 4. Fractional Knapsack
    // Choose items in descending order of value/weight ratio.
    // Time: O(n log n), Space: O(1) auxiliary space for sorting.
    static class Item {
        int value, weight;

        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    public double fractionalKnapsack(int capacity, Item[] arr, int n) {
        Arrays.sort(arr, (a, b) -> {
            double r1 = (double) a.value / a.weight;
            double r2 = (double) b.value / b.weight;
            return Double.compare(r2, r1);
        });

        int currentWeight = 0;
        double totalValue = 0;

        for (int i = 0; i < n; i++) {
            if (currentWeight + arr[i].weight <= capacity) {
                currentWeight += arr[i].weight;
                totalValue += arr[i].value;
            } else {
                int remaining = capacity - currentWeight;
                totalValue += ((double) arr[i].value / arr[i].weight)
                        * remaining;
                break;
            }
        }

        return totalValue;
    }
}
