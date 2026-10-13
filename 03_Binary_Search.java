/*
 * Binary Search - Revision Notes
 *
 * Works on sorted arrays.
 * Time Complexity: O(log n) for each method.
 * Space Complexity: O(1) for each method.
 */

public class BinarySearch {

    // 1. Floor: Greatest element <= x. Returns -1 if none exists.
    public int floor(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] <= x) {
                ans = arr[mid];
                low = mid + 1; // Search for a larger valid element.
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // 2. Ceil: Smallest element >= x. Returns -1 if none exists.
    public int ceil(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= x) {
                ans = arr[mid];
                high = mid - 1; // Search for a smaller valid element.
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // 3. Integer square root: Largest value whose square <= n.
    public int squareRoot(int n) {
        int low = 0, high = n;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if ((long) mid * mid <= n) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // 4. First corrupted file: 0s followed by 1s. Returns its index.
    public int firstCorrupted(int[] files) {
        int low = 0, high = files.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (files[mid] == 1) {
                ans = mid;
                high = mid - 1; // Check for an earlier corrupted file.
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // 5. CNOZ: Count zeros in a sorted binary array (0s followed by 1s).
    public int countZeros(int[] arr) {
        int low = 0, high = arr.length - 1;
        int firstOne = arr.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == 1) {
                firstOne = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return firstOne; // Index of first 1 = number of zeros.
    }

    // 6. Search in a rotated sorted array (distinct elements).
    public int searchRotated(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            // At least one half is sorted.
            if (arr[low] <= arr[mid]) {
                if (target >= arr[low] && target < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
