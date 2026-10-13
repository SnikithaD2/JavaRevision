import java.util.HashMap;
import java.util.Map;

class MapOperations {
    MapOperations() {
        Map<Integer, String> map = new HashMap<>();

        // Add elements
        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Cherry");
        System.out.println("Map: " + map);

        // Get value by key
        System.out.println("Value for key 2: " + map.get(2));

        // Update value
        map.put(2, "Blueberry");
        System.out.println("Updated Map: " + map);

        // Remove element
        map.remove(3);
        System.out.println("After removing key 3: " + map);

        // Check key and value
        System.out.println("Contains key 1? " + map.containsKey(1));
        System.out.println("Contains value Apple? " + map.containsValue("Apple"));

        // Iterate over keys
        System.out.print("Keys: ");
        for (Integer key : map.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();

        // Iterate over values
        System.out.print("Values: ");
        for (String value : map.values()) {
            System.out.print(value + " ");
        }
        System.out.println();

        // Iterate over key-value pairs
        System.out.println("Entries:");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Size and empty check
        System.out.println("Size: " + map.size());
        System.out.println("Is empty? " + map.isEmpty());

        // Clear map
        map.clear();
        System.out.println("After clear: " + map);
    }
}

class SubarraysProblems {

    // Count subarrays with sum K
    public void CountSubarraySumK() {
        int[] arr = {1, 2, 3, 2, 1};
        int k = 3;
        int sum = 0, count = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int num : arr) {
            sum += num;

            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        System.out.println("Number of subarrays with sum " + k + ": " + count);
    }

    // Maximum length of a subarray with sum K
    public void MaxLengthSubarraySumK() {
        int[] arr = {1, 2, 3, 2, 1};
        int k = 3;
        int sum = 0, maxLength = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum - k)) {
                maxLength = Math.max(
                    maxLength, i - map.get(sum - k)
                );
            }

            // Store only the first occurrence of each prefix sum
            map.putIfAbsent(sum, i);
        }

        System.out.println("Maximum length of subarray with sum " + k + ": " + maxLength);
    }
}
