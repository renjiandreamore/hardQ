public class Solution {
    /**
     * @param expression: A string array
     * @return: The Reverse Polish notation of this expression
     */
    public ArrayList<String> convertToRPN(String[] expression) {
        // write your code here
        ArrayList<String> res = new ArrayList<>();
        if(expression == null || expression.length == 0) return res;
        Stack<String> st = new Stack<>();
        StringBuffer sb = new StringBuffer();
        
        for(int i = 0; i < expression.length; i++){
            String s = expression[i];
            if(s == "+" || s == "-"){
                if(st.isEmpty()||st.peek() == "("){
                    st.push(s);
                }else{
                    while(!st.isEmpty() && (st.peek() == "+" || st.peek() == "-" || st.peek() == "*" || st.peek() == "/" )){
                        sb.append(st.pop());
                    }
                    st.push(s);
                }
            }else if(s == "*" || s == "/"){
                if(st.isEmpty() || st.peek() == "(" || st.peek() == "+" || st.peek() == "-") {
                    st.push(s);
                }else{
                    while(!st.isEmpty() && (st.peek() == "*" || st.peek() == "/")){
                        sb.append(st.pop());
                    }
                    st.push(s);
                }
            }else if(s == "("){
                st.push(s);
            }else if(s == ")"){
                String top = "";
                while((top = st.pop())!="("){
                    sb.append(top);
                }
            }else{ //为非运算符
                sb.append(s);
            }
        }
        
        while(!st.isEmpty()){
            sb.append(st.pop());
        }
        
        // for(int i = 0; i < sb.length(); i++){
        //     res.add(String.valueOf(sb.toString().charAt(i)));
        // }
        String ss = sb.toString();
        for(int i = 0; i < ss.length(); i++){
            res.add(String.valueOf(ss.charAt(i)));
        }
        
        return res;
    }
}


//
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//               佛祖保佑         永无BUG
//
//
/*1.若最高级运算符，入栈, 若( 入栈
  2.若)， 把栈中(之前的都加入表达式
  3.若非最高级：
    则从栈顶！往下，到第一个优先级小于此运算符的地方，这之间的运算符加入表达式
    然后次运算符再入栈
    
  其实就保持了是栈顶优先级最高

*/



