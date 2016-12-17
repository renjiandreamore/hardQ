public class Solution {
    public List<String> addOperators(String num, int target) {
        //"123" 6
        String path = "";
        List<String> res = new ArrayList<>();
        helper(res, num, target, path, 0, 0);
        return res;
    }
    
    public void helper(List<String> res, String num, int target, String path, long curPathSum, long prevNum){
        if(curPathSum == target && num.length() == 0){
            res.add(path);
            return;
        }
        //(res, "123", 6, "", 0, 0);
        for(int i = 1; i <= num.length(); i++){
            String curString = num.substring(0, i);//"1"
            if(curString.length()>1 && curString.charAt(0)=='0')return;
            long curNum = Long.parseLong(curString); //1
            String nextString = num.substring(i);//"23"
            if(path.length() == 0){//第一次添加，只加字母
                helper(res, nextString, target, curString, curNum, curNum);
            }else{
                //*
                helper(res, nextString, target, path+"*"+curString, curPathSum - prevNum + prevNum * curNum, curNum * prevNum);
                //+
                helper(res, nextString, target, path+"+"+curString, curPathSum + curNum, curNum);
                //-
                helper(res, nextString, target, path+"-"+curString, curPathSum - curNum, -curNum);
            }
            
        }
    }
} 
