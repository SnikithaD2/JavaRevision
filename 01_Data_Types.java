public class Datatypes {

    public Datatypes() {
        System.out.println("Range of byte: " + Byte.MIN_VALUE + " to " + Byte.MAX_VALUE);
        System.out.println("Range of short: " + Short.MIN_VALUE + " to " + Short.MAX_VALUE);
        System.out.println("Range of int: " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        System.out.println("Range of long: " + Long.MIN_VALUE + " to " + Long.MAX_VALUE);

        System.out.println("Range of float: " + Float.MIN_VALUE + " to " + Float.MAX_VALUE);
        System.out.println("Range of double: " + Double.MIN_VALUE + " to " + Double.MAX_VALUE);

        System.out.println("Range of char: " + (int) Character.MIN_VALUE
                + " to " + (int) Character.MAX_VALUE);

        System.out.println("Boolean values: " + Boolean.FALSE + " and " + Boolean.TRUE);
    }

    public static void main(String[] args) {
        new Datatypes();
    }
}
