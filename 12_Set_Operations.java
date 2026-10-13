import java.util.HashSet;
import java.util.Set;

class SetOperations {
    SetOperations() {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        // Add elements
        set1.add(1);
        set1.add(2);
        set1.add(3);
        System.out.println("Set1: " + set1);

        set2.add(3);
        set2.add(4);
        set2.add(5);
        System.out.println("Set2: " + set2);

        // Union
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("Union: " + union);

        // Intersection
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);

        // Difference (set1 - set2)
        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference (set1 - set2): " + difference);

        // Symmetric Difference
        Set<Integer> symDiff = new HashSet<>(union);
        symDiff.removeAll(intersection);
        System.out.println("Symmetric Difference: " + symDiff);

        // Check if an element exists
        System.out.println("Set1 contains 2? " + set1.contains(2));

        // Remove an element
        set1.remove(2);
        System.out.println("Set1 after removing 2: " + set1);

        // Check if set is empty
        System.out.println("Set1 is empty? " + set1.isEmpty());

        // Size of set
        System.out.println("Size of Set2: " + set2.size());
    }
}
