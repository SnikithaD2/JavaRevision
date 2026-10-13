class StringBuilderOperations {
    StringBuilderOperations() {
        StringBuilder sb = new StringBuilder("Hello");

        // Append
        sb.append(" World");
        System.out.println("After append: " + sb);

        // Insert
        sb.insert(5, ",");
        System.out.println("After insert: " + sb);

        // Replace characters from index 0 to 4
        sb.replace(0, 5, "Hi");
        System.out.println("After replace: " + sb);

        // Delete characters from index 2 to 2
        sb.delete(2, 3);
        System.out.println("After delete: " + sb);

        // Delete character at index 2
        sb.deleteCharAt(2);
        System.out.println("After deleteCharAt: " + sb);

        // Reverse the string
        sb.reverse();
        System.out.println("After reverse: " + sb);

        // Length and capacity
        System.out.println("Length: " + sb.length());
        System.out.println("Capacity: " + sb.capacity());

        // Get character at index 1
        System.out.println("Character at index 1: " + sb.charAt(1));

        // Update character at index 1
        sb.setCharAt(1, 'X');
        System.out.println("After setCharAt: " + sb);

        // Extract substring from index 1 to 3
        System.out.println("Substring (1 to 4): " + sb.substring(1, 4));

        // Ensure minimum capacity of 50
        sb.ensureCapacity(50);
        System.out.println("Capacity after ensureCapacity: " + sb.capacity());

        // Convert StringBuilder to String
        String str = sb.toString();
        System.out.println("Converted to String: " + str);
    }
}
