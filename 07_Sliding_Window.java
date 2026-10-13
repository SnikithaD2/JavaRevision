import java.util.*;

class SlidingWindow {

    // 1. Count subarrays with sum <= k (non-negative elements)
    static int countSegmentsWithSmallSum(int[] arr, int k) {
        int left = 0, sum = 0, count = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            while (sum > k) {
                sum -= arr[left++];
            }

            count += right - left + 1;
        }

        return count;
    }

    // 2. Maximum length of subarray with sum <= k
    static int maxLengthWithSmallSum(int[] arr, int k) {
        int left = 0, sum = 0, maxLen = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            while (sum > k) {
                sum -= arr[left++];
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // 3. Count subarrays with sum >= k (non-negative elements)
    static int countSegmentsWithLargeSum(int[] arr, int k) {
        int left = 0, sum = 0, count = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            while (left <= right && sum >= k) {
                count += arr.length - right;
                sum -= arr[left++];
            }
        }

        return count;
    }

    // 4. Maximum length of subarray with sum >= k
    static int maxLengthWithLargeSum(int[] arr, int k) {
        int left = 0, sum = 0, maxLen = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            while (left <= right && sum >= k) {
                maxLen = Math.max(maxLen, right - left + 1);
                sum -= arr[left++];
            }
        }

        return maxLen;
    }

    // 5. Longest substring with at most k distinct characters
    static int longestSubstringAtMostKDistinct(String s, int k) {
        if (k <= 0)
            return 0;

        int left = 0, maxLen = 0;
        Map<Character, Integer> freq = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq.put(c, freq.getOrDefault(c, 0) + 1);

            while (freq.size() > k) {
                char l = s.charAt(left++);
                freq.put(l, freq.get(l) - 1);

                if (freq.get(l) == 0)
                    freq.remove(l);
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // 6. Count subarrays with sum exactly k (non-negative elements)
    static int countSubarraysWithSumK(int[] arr, int k) {
        if (k < 0)
            return 0;

        return countSegmentsWithSmallSum(arr, k)
             - countSegmentsWithSmallSum(arr, k - 1);
    }
}
