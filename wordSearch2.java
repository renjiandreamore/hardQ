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
