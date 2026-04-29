import java.util.*;
class CustomHashMap<K,V> {
    class Node {
        K key; V value;
        Node(K k, V v){ key=k; value=v; }
    }
    int SIZE = 10;
    LinkedList<Node>[] buckets;
    CustomHashMap(){
        buckets = new LinkedList[SIZE];
        for(int i=0;i<SIZE;i++) buckets[i]=new LinkedList<>();
    }
    int hash(K key){ return Math.abs(key.hashCode())%SIZE; }
    void put(K key, V value){
        int idx = hash(key);
        for(Node node: buckets[idx]){
            if(node.key.equals(key)){
                node.value=value; return;
            }
        }
        buckets[idx].add(new Node(key,value));
    }
    V get(K key){
        int idx = hash(key);
        for(Node node: buckets[idx]){
            if(node.key.equals(key)) return node.value;
        }
        return null;
    }
    public static void main(String[] args){
        CustomHashMap<Integer,String> map = new CustomHashMap<>();
        map.put(1,"A");
        System.out.println(map.get(1));
    }
}