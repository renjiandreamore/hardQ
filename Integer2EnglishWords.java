public class Solution {
    
    String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] tenPlus = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] position = {"", "Thousand", "Million", "Billion"};
    int index = 0;
    public String numberToWords(int num) {
        //2,123,123,123
        //billion级
        if(num == 0) return "Zero";
        String s = String.valueOf(num);
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        int x = len % 3;
        int p = (len-1) / 3;
        
        
        
        while(p >= 0) {
            boolean allZero = true;
            StringBuilder tmp = buildPath(x, p, s, allZero);
            sb.append(tmp);
            x = 0;
            p--;
        }
        
        
        return sb.toString().trim();
        
    }
    
    public StringBuilder buildPath(int x, int p, String s, boolean allZero) {
        StringBuilder sb = new StringBuilder();
        
        if(x == 0) {
            int hundred = s.charAt(index) - '0';
            sb.append(ones[hundred]);
            if(hundred != 0) {
                sb.append(" ").append("Hundred").append(" ");
                allZero = false;
            }
            index++;
            x = 2;
        }
        
        if(x == 2) {
            int ten = s.charAt(index) - '0';
            if(ten != 1) {
                sb.append(tens[ten]);
                if(ten != 0) {
                    sb.append(" ");
                    allZero = false;
                }
                x = 1;
            }
            index++;
        }
        
        if(x == 2 || x == 1) {
            int one = s.charAt(index) - '0';
            if(x == 2) {
                sb.append(tenPlus[one]).append(" ");
                allZero = false;
            }else{
                sb.append(ones[one]);
                if(one != 0) {
                    sb.append(" ");
                    allZero = false;
                }
            }
            if(!allZero) sb.append(position[p]).append(" ");
            index++;
        }
     
        return sb;
    }
}
