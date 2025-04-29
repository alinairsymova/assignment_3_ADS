import java.util.*;

public class BST<K extends Comparable<K>, V> implements Iterable<Map.Entry<K, V>> {
    private Node root;
    private int size = 0;

    private class Node {
        K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Insert method
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;
        return x;
    }

    // Get size of tree
    public int size() {
        return size;
    }

    // In-order traversal using an iterator
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<Map.Entry<K, V>> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node x) {
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Map.Entry<K, V> next() {
            Node node = stack.pop();
            pushLeft(node.right);
            return new AbstractMap.SimpleEntry<>(node.key, node.value);
        }
    }
}
