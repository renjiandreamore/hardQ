/**
 * Definition of Tweet:
 * public class Tweet {
 *     public int id;
 *     public int user_id;
 *     public String text;
 *     public static Tweet create(int user_id, String tweet_text) {
 *         // This will create a new tweet object,
 *         // and auto fill id
 *     }
 * }
 */
public class MiniTwitter {
   
    class Node{
        int order;
        Tweet tw;
        public Node(int order, Tweet tw){
            this.order = order;
            this.tw = tw;
        }
    }
    
    class sortByOrder implements Comparator{
        public int compare(Object a1, Object b1){
            Node a = (Node) a1;
            Node b = (Node) b1;
            return(b.order - a.order);
        }
    }
    
    private HashMap<Integer, HashSet<Integer>> friends;
    private HashMap<Integer, List<Node>> user_twts;
    int order;
    
    public List<Node> getLastTen(List<Node> tmp){
        int last = 10;
        if(tmp.size() < 10){
            last = tmp.size();
        }
        return tmp.subList(tmp.size() - last, tmp.size());
    }
    
    public List<Node> getFirstTen(List<Node> tmp){
        int first = 10;
        if(tmp.size()<10){
            first = tmp.size();
        }
        return tmp.subList(0, first);
    }
    

    public MiniTwitter() {
        // initialize your data structure here.
        this.friends = new HashMap<>();
        this.user_twts = new HashMap<>();
        this.order = 0;
    }

    // @param user_id an integer
    // @param tweet a string
    // return a tweet
    public Tweet postTweet(int user_id, String tweet_text) {
        //  Write your code here
        Tweet atweet = Tweet.create(user_id, tweet_text);
        if(!user_twts.containsKey(atweet.user_id)){
            user_twts.put(atweet.user_id, new ArrayList<Node>());
        }
        order += 1;
        user_twts.get(atweet.user_id).add(new Node(order, atweet));
        return atweet;
    }

    // @param user_id an integer
    // return a list of 10 new feeds recently
    // and sort by timeline
    public List<Tweet> getNewsFeed(int user_id) {
        // Write your code here
        List<Node> tmp = new ArrayList<>();
        if(user_twts.containsKey(user_id)){
            tmp.addAll(getLastTen(user_twts.get(user_id)));
        }
        
        if(friends.containsKey(user_id)){
            for(int user: friends.get(user_id)){
                if(user_twts.containsKey(user)){
                    tmp.addAll(getLastTen(user_twts.get(user)));
                }
            }
        }
        
        Collections.sort(tmp, new sortByOrder());
        List<Tweet> list = new ArrayList<>();
        tmp = getFirstTen(tmp);
        // for(int i = 0; i < 10; i++){
        //     list.add(tmp.get(i).tw);
        // }
        for(Node n : tmp){
            list.add(n.tw);
        }
        
        return list;
    }
        
    // @param user_id an integer
    // return a list of 10 new posts recently
    // and sort by timeline
    public List<Tweet>  getTimeline(int user_id) {
        // Write your code here
        List<Node> tmp = new ArrayList<>();
        if(user_twts.containsKey(user_id)){
            tmp.addAll(getLastTen(user_twts.get(user_id)));
        }
        Collections.sort(tmp, new sortByOrder());
        List<Tweet> list = new ArrayList<>();
        for(Node n : tmp){
            list.add(n.tw);
        }
        
        return list;
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) {
        // Write your code here
        if(!friends.containsKey(from_user_id)){
            friends.put(from_user_id, new HashSet<Integer>());
        }
        
        friends.get(from_user_id).add(to_user_id);
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
        // Write your code here
        if(friends.containsKey(from_user_id)){
            friends.get(from_user_id).remove(to_user_id);
        }
    }
}
