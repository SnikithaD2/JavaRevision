import java.util.ArrayList;

class TwoPointers {
    public void TwoSum() {
        int[] arr = {1, 2, 3, 4, 6};
        int target = 6;

        int left = 0, right = arr.length - 1;
        boolean found = false;

        // Find a pair in the sorted array
        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target) {
                System.out.println("Pair found: (" + arr[left] + ", " + arr[right] + ")");
                found = true;
                break;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        if (!found) {
            System.out.println("No pair found");
        }
    }

    public void UnionTwoArrays() {
        int[] arr1 = {1, 2, 4, 5, 6};
        int[] arr2 = {2, 3, 5, 7};

        int i = 0, j = 0;
        ArrayList<Integer> union = new ArrayList<>();

        // Merge both sorted arrays without duplicates
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                union.add(arr1[i++]);
            } else if (arr1[i] > arr2[j]) {
                union.add(arr2[j++]);
            } else {
                union.add(arr1[i]);
                i++;
                j++;
            }
        }

        // Add remaining elements
        while (i < arr1.length) {
            union.add(arr1[i++]);
        }

        while (j < arr2.length) {
            union.add(arr2[j++]);
        }

        System.out.println("Union: " + union);
    }
}
