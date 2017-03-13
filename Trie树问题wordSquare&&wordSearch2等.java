public class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<String>();
        if(board == null || board.length == 0 || words == null || words.length == 0 ){
            return res;
        }
        
        Trie trie = new Trie();
        for(String s : words){
            trie.insert(s);
        }
        
        String word = "";
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                dfs(board, trie, word, i, j, res);
            }
        }
        
        return res;
    }
    
    public void dfs(char[][]board, Trie trie, String word, int i, int j, List<String> res ){
        if(i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1){
            return;
        }
        if(board[i][j] == '$') return;
        word += board[i][j];
        
        if(!trie.startsWith(word)) return;
        if(trie.search(word) && !res.contains(word)){
            res.add(word);
        }
        
        char tmp = board[i][j];
        board[i][j] = '$';
        dfs(board, trie, word, i, j+1, res);
        dfs(board, trie, word, i+1, j, res);
        dfs(board, trie, word, i, j-1, res);
        dfs(board, trie, word, i-1, j, res);
        board[i][j] = tmp;
        
    }
}

class Trie{
    TrieNode root;
    public Trie(){
        this.root = new TrieNode();
    }
    
    public void insert(String word){
        TrieNode node = root;
        for(char c : word.toCharArray()){
            int num = c - 'a';
            if(node.child[num] == null){
                node.child[num] = new TrieNode();
            }
            node = node.child[num];
        }
        
        node.item = word;
    }
    
    public boolean search(String word){
        TrieNode node = root;
        for(char c : word.toCharArray()){
            int num = c - 'a';
            if(node.child[num] == null) return false;
            node = node.child[num];
        }
        return node.item.equals(word);
    }
    
    public boolean startsWith(String prefix){
        TrieNode node = root;
        for(char c : prefix.toCharArray()){
            int num = c - 'a';
            if(node.child[num] == null) return false;
            node = node.child[num];
        }
        return true;
    }
}

class TrieNode{
    String item = "";
    TrieNode[] child = new TrieNode[26];
}




///word square
/*
Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

*/
public class Solution {
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        if(words == null || words.length == 0) return res;
        Trie trie = new Trie();
        for(String word : words) trie.insert(word);
        
        List<String> path = new ArrayList<>();
        for(String word : words) {
            dfs(res, path, word, 0, trie);
        }
        return res;
    }
    
    public void dfs(List<List<String>> res, List<String> path, String word, int index, Trie trie) {
        if(index == word.length()-1) {
            path.add(word);
            res.add(new ArrayList<>(path));
            path.remove(path.size()-1);
            return;
        }
        
        path.add(word);
        StringBuilder sb = new StringBuilder();
        for(String s : path) {
            sb.append(s.charAt(path.size()));
        }
        
        if(!trie.startsWith(sb.toString())) {
            path.remove(path.size() - 1);   //这句删掉可否？
            return;
        }
        for(String ss : trie.getAllPrefix(sb.toString())) {
            dfs(res, path, ss, index+1, trie);
        }
        
        path.remove(path.size()-1);
    }
}

class Trie{
    TrieNode root;
    public Trie() {
        this.root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()) {
            if(node.children[c-'a'] == null) {
                node.children[c-'a'] = new TrieNode();
            }
            node.prefixs.add(word);
            node = node.children[c-'a'];
        }
        node.word = word;
    }
    
    public boolean search(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()) {
            if(node.children[c-'a'] == null) return false;
            node = node.children[c-'a'];
        }
        return node.word.equals(word);
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(char c : prefix.toCharArray()) {
            if(node.children[c-'a'] == null) return false;
            node = node.children[c-'a'];
        }
        return true;
    }
    
    public ArrayList<String> getAllPrefix(String prefix) {
        ArrayList<String> res = new ArrayList<>();
        TrieNode node = root;
        for(char c : prefix.toCharArray()) {
            if(node.children[c-'a'] == null) return null;
            node = node.children[c-'a'];
        }
        res.addAll(node.prefixs);
        return res;
    }
    
}
//这里的trie不单单判断是否startwith prefix，而是要得到所有以prefix开头的单词，然后再dfs。所以要稍稍改进trie
//而单纯通过每个递归搜所有以prefix开头的单词太慢，于是加一个list<prefix>， 在insert的时候直接加进去，找的时候直接get就是了。

class TrieNode{
    List<String> prefixs;
    String word;
    TrieNode[] children;
    public TrieNode(){
        this.prefixs = new ArrayList<String>();
        this.word = "";
        this.children = new TrieNode[26];
    }
}
