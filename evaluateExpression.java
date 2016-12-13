
import java.util.*;
public class Solution {
    /**
     * @param expression: an array of strings;
     * @return: an integer
     */
    public int evaluateExpression(String[] expression) {
        // write your code here
        if(expression == null || expression.length == 0) return 0;
        ArrayList<String> s = convert2RPN(expression);
        int i = evaluateRPN(s);
        return i;
    }
    
    public ArrayList<String> convert2RPN(String[] expression){
        ArrayList<String> res = new ArrayList<>();
        if(expression == null || expression.length == 0) return res;
        Stack<String> oprs = new Stack<String>();
        
        StringBuffer sb = new StringBuffer();
        
        for(int i = 0; i < expression.length; i++){
            String s = expression[i];
            if(s.equals("+") || s.equals("-")){
                if(oprs.isEmpty() || oprs.peek() == "("){
                    oprs.push(s);
                }else{
                    while(!oprs.isEmpty() && (oprs.peek()=="+" || oprs.peek() == "-" || oprs.peek() == "*" || oprs.peek() == "/")){
                        res.add(oprs.pop());
                    }
                    oprs.push(s);
                }
            }else if(s.equals("*") || s.equals("/")){
                if(oprs.isEmpty() || oprs.peek() == "(" || oprs.peek() == "+" || oprs.peek() == "-"){
                    oprs.push(s);
                }else{
                    while(!oprs.isEmpty() && (oprs.peek() == "*" || oprs.peek() == "/") ){
                        res.add(oprs.pop());
                    }
                    oprs.push(s);
                }
            }else if(s.equals("(")){
                oprs.push(s);
            }else if(s.equals(")")){
                String top = oprs.pop();
                while( top != "("){
                    res.add(top);
                    top = oprs.pop();
                }
            }else{
                res.add(s);
            }
        }
        
        while(!oprs.isEmpty()){
            res.add(oprs.pop());
        }
        
        return res;
    }
    
    public int evaluateRPN(ArrayList<String> res){
        if(res == null) return 0;
        String t = "+-*/";
        int result = 0;
        Stack<Integer> st = new Stack<Integer>();
        
        for(int i = 0; i < res.size(); i++){
            String s = res.get(i);
            if(!t.contains(s)){
                st.push(Integer.valueOf(s));
                continue;
            }else if(s.equals("+")){
                int a = st.pop();
                int b = st.pop();
                result = a + b;
            }else if(s.equals("-")){
                int a = st.pop();
                int b = st.pop();
                result = b - a;
            }else if(s.equals("*")){
                int a = st.pop();
                int b = st.pop();
                result = a * b;
            }else if(s.equals("/")){
                int a = st.pop();
                int b = st.pop();
                result = b / a;
            }
            st.push(result);
        }
        
        return st.pop();
    }

    public static void main(String[] args){
    	Solution ss = new Solution();
    	String[] expression = {"2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"};
    	System.out.println(ss.evaluateExpression(expression));
    }
};


