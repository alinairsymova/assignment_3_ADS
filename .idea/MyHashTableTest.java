import java.util.Random;

public class MyHashTableTest {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();

        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(100000);
            String name = "Name" + random.nextInt(100000);
            MyTestingClass key = new MyTestingClass(id, name);
            String value = "Student" + i;
            table.put(key, value);
        }

        System.out.println("Bucket distribution:");
        for (int i = 0; i < 11; i++) {
            System.out.println("Bucket " + i + ": " + table.getBucketSize(i) + " elements");
        }
    }
}
