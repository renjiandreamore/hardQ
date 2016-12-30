class Pair{
    String word;
    int freq;
    public Pair(String word, int freq){
        this.word=word;
        this.freq=freq;
    }
}
class PqComparator implements Comparator<Pair>{
    public int compare(Pair a, Pair b){
        if(a.freq != b.freq){
            return a.freq - b.freq;
        }else{
            return b.word.compareTo(a.word);
        }
    }
}
public class TopK {

    HashMap<String, Integer> dict;
    PriorityQueue<Pair> pq;
    int k;
    
    public TopK(int k) {
        // initialize your data structure here
        this.k = k;
        dict = new HashMap<String, Integer>();
        pq = new PriorityQueue<Pair>(k, new PqComparator());
    }

    public void add(String word) {
        // Write your code here
        if(dict.containsKey(word)){
            dict.put(word, dict.get(word)+1);
        }else{
            dict.put(word, 1);
        }
        
    }

    public List<String> topk() {
        // Write your code here
        for(String word : dict.keySet()){
            Pair node = new Pair(word, dict.get(word));
            if(pq.size() < k){
                pq.offer(node);
            }else if(node.freq > pq.peek().freq){
                pq.poll();
                pq.offer(node);
            }
        }
        
        List<String> list = new ArrayList<>();
        for(Pair p : pq){
            list.add(pq.poll().word);
        }
        
        Collections.reverse(list);
        return list;
    }
}
