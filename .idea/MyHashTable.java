import java.util.*;

public class MyHashTable<K, V> {
    private static class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<HashNode<K, V>>[] chainArray;
    private int M = 11; // размер массива (хеш-таблицы)
    private int size;

    public MyHashTable() {
        chainArray = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            chainArray[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    public void put(K key, V value) {
        int index = hash(key);
        for (HashNode<K, V> node : chainArray[index]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        chainArray[index].add(new HashNode<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        for (HashNode<K, V> node : chainArray[index]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        Iterator<HashNode<K, V>> iterator = chainArray[index].iterator();
        while (iterator.hasNext()) {
            HashNode<K, V> node = iterator.next();
            if (node.key.equals(key)) {
                iterator.remove();
                size--;
                return node.value;
            }
        }
        return null;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (LinkedList<HashNode<K, V>> bucket : chainArray) {
            for (HashNode<K, V> node : bucket) {
                keys.add(node.key);
            }
        }
        return keys;
    }

    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (LinkedList<HashNode<K, V>> bucket : chainArray) {
            for (HashNode<K, V> node : bucket) {
                values.add(node.value);
            }
        }
        return values;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entries = new HashSet<>();
        for (LinkedList<HashNode<K, V>> bucket : chainArray) {
            for (HashNode<K, V> node : bucket) {
                entries.add(new AbstractMap.SimpleEntry<>(node.key, node.value));
            }
        }
        return entries;
    }

    public void printTable() {
        for (int i = 0; i < M; i++) {
            System.out.print(i + ": ");
            for (HashNode<K, V> node : chainArray[i]) {
                System.out.print("[" + node.key + "=" + node.value + "] -> ");
            }
            System.out.println("null");
        }
    }

    public int[] calculateCollisions() {
        int[] collisions = new int[M];
        for (int i = 0; i < M; i++) {
            collisions[i] = Math.max(0, chainArray[i].size() - 1);
        }
        return collisions;
    }

    public double getAverageChainLength() {
        int totalLength = 0;
        int nonEmpty = 0;
        for (LinkedList<HashNode<K, V>> bucket : chainArray) {
            if (!bucket.isEmpty()) {
                totalLength += bucket.size();
                nonEmpty++;
            }
        }
        return nonEmpty == 0 ? 0 : (double) totalLength / nonEmpty;
    }
}
