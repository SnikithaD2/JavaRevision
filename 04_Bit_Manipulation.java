import java.util.*;

public class BitManipulation {

    // 1. Finding AKELA: One number appears once; others appear twice.
    public int findAK(int[] arr) {
        int x = 0;

        for (int ele : arr) {
            x ^= ele;
        }

        return x;
    }

    // 2. Check if the kth bit is set (0-based indexing).
    public boolean checkSetBit(int n, int k) {
        return (n & (1 << k)) != 0;
        // Alternative: ((n >> k) & 1) != 0
    }

    // 3. Set the kth bit.
    public long setBit(long n, int k) {
        return n | (1L << k);
    }

    // 4. Clear the kth bit.
    public long clearBit(long n, int k) {
        return n & ~(1L << k);
    }

    // 5. Flip the kth bit.
    public long flipBit(long n, int k) {
        return n ^ (1L << k);
    }

    // 6. Check if n is a power of 2.
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // 7. Generate all subsets using bitmasks.
    public List<List<Integer>> subsets(int[] arr) {
        int n = arr.length;
        int total = 1 << n;

        List<List<Integer>> result = new ArrayList<>();

        for (int mask = 0; mask < total; mask++) {
            List<Integer> subset = new ArrayList<>();

            for (int bit = 0; bit < n; bit++) {
                if ((mask & (1 << bit)) != 0) {
                    subset.add(arr[bit]);
                }
            }

            result.add(subset);
        }

        return result;
    }

    // 8. Triplet Trouble: Every number appears thrice except one.
    public int triplet(int[] arr) {
        int ans = 0;

        // Reconstruct the answer using bits that occur non-multiples of 3 times.
        for (int bit = 0; bit < 32; bit++) {
            int count = 0;

            for (int ele : arr) {
                if (((ele >>> bit) & 1) != 0) {
                    count++;
                }
            }

            if (count % 3 != 0) {
                ans |= (1 << bit);
            }
        }

        return ans;
    }

    // 9. Find missing and repeated numbers in [1, n].
    // Assumption: One number is missing and another appears twice.
    public List<Integer> findMissingRepeated(int[] arr) {
        int n = arr.length;
        int xor = 0;

        // XOR array elements with numbers from 1 to n.
        for (int i = 0; i < n; i++) {
            xor ^= arr[i];
            xor ^= (i + 1);
        }

        // Find a set bit to separate the two numbers.
        int bit = xor & -xor;

        int a = 0, b = 0;

        // Divide values into two groups based on the selected bit.
        for (int i = 0; i < n; i++) {
            if ((arr[i] & bit) != 0) {
                a ^= arr[i];
            } else {
                b ^= arr[i];
            }

            int value = i + 1;

            if ((value & bit) != 0) {
                a ^= value;
            } else {
                b ^= value;
            }
        }

        // Determine which number is repeated and which is missing.
        List<Integer> result = new ArrayList<>();

        for (int ele : arr) {
            if (ele == a) {
                result.add(a); // Repeated
                result.add(b); // Missing
                return result;
            } else if (ele == b) {
                result.add(b); // Repeated
                result.add(a); // Missing
                return result;
            }
        }

        return result;
    }
}
