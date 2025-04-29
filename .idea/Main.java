public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();

        for (int i = 0; i < 20; i++) {
            table.put(new MyTestingClass(i), new Student("Student" + i, 2.0 + (i % 5)));
        }

        table.printTable();

        System.out.println("\nCollision stats:");
        int[] collisions = table.calculateCollisions();
        for (int i = 0; i < collisions.length; i++) {
            System.out.println("Bucket " + i + ": " + collisions[i] + " collisions");
        }

        System.out.printf("Average chain length: %.2f\n", table.getAverageChainLength());
    }
}
