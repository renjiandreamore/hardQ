public class LRUCache {
    public Node head = new Node(-1, -1);
    public Node tail = new Node(-1, -1);
    public int capacity;
    Map<Integer, Node> map = new HashMap<>();
    
    public LRUCache(int capacity) {
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        
        Node get_node = map.get(key);
        get_node.prev.next = get_node.next;
        get_node.next.prev = get_node.prev;
        insert_before_tail(get_node);
        
        return map.get(key).value;
    }
    
    public void set(int key, int value) {
        if(get(key) != -1){
            map.get(key).value = value;
            return;
        }
        
        if(map.size() == capacity){    //remove head.next node
            map.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }
        
        Node new_node = new Node(key, value);
        insert_before_tail(new_node);
        map.put(key, new_node);
    }
    
    public void insert_before_tail(Node node){
        node.next = tail;
        tail.prev.next = node;
        node.prev = tail.prev;
        tail.prev = node;
    }
}

class Node{
    Node prev;
    Node next;
    int key;
    int value;
    public Node(int key, int value){
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}
