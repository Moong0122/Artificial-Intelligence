public class Pair<K, V> {
    /*
    DFS에서 <col, row>를 저장하기 위해 만든 class
     */

    private final K element0;
    private final V element1;

    public static <K, V> Pair<K, V> createPair(K key, V value) {
        return new Pair<K, V>(key, value);
    }

    public Pair(K element0, V element1) {
        this.element0 = element0;
        this.element1 = element1;
    }

    public K getElement0() {
        return element0;
    }

    public V getElement1() {
        return element1;
    }

}