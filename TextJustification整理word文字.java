public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        /*
        1.刚填满这一行
        2.填上这个单词还有空余
        3.此行填不下这个单词(需要rebuild)
        
        rebuild:
            str只有一个词
            str>1个词
        */
        
        
        List<String> res = new ArrayList<>();
        if(words == null || words.length == 0) return res;
        
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while(index < words.length) {
            int remain = maxWidth - sb.length();
            if(remain == words[index].length()) {
                sb.append(words[index]);
                res.add(sb.toString());
                sb = new StringBuilder();
                index++;
                // if(index == words.length-1) {
                //     res.add(words[index]);
                //     break;
                // }
            }
            else if(remain > words[index].length()) {
                sb.append(words[index]).append(" ");
                if(index == words.length-1) {
                    int l = maxWidth - sb.length();
                    for(int j = 0; j < l; j++) sb.append(" ");
                    res.add(sb.toString());
                    break;
                }
                index++;
            }
            else{
                String s = sb.toString();
                s = reBuild(s, maxWidth);
                res.add(s);
                sb = new StringBuilder();
            }
        }
        
        return res;
    }
    
    public String reBuild(String s, int maxWidth) {
        String[] str = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        if(str.length == 1) {
            sb.append(str[0]);
            for(int i = 0; i < maxWidth - str[0].length(); i++) {
                sb.append(" ");
            }
            return sb.toString();
        }
        
        sb.append(str[0]);
        int len = maxWidth - str[0].length() - str[str.length - 1].length();
        int ss = 0;
        int count = 0;
        for(int i = 1; i < str.length - 1; i++) {
            count++;
            ss += str[i].length();
        }
        
        int remain = len - ss;
        int index = 1;
        while(count >= 0 && index < str.length) {
            int spaces = (remain - 1)/(count+1) + 1;
            for(int j = 0; j < spaces; j++) {
                sb.append(" ");
            }
            count --;
            sb.append(str[index++]);
            remain = remain - spaces;
        }
        
        return sb.toString();
    }
}
