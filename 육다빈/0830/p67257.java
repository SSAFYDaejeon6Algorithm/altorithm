import java.util.*;

class Solution {
    String[] pm = {"+", "-", "*"}; //43 45 42
    String[] exp, exp_copy;
    int[] key = new int[3];
    boolean[] visit = new boolean[3];
    long max = Long.MIN_VALUE;
    
    void perm(int n){
        if(n==3) {
            for(int i=0; i<exp.length; i++) exp_copy[i] = exp[i];
            max = Math.max(Math.abs(go()), max);
            return;
        }
        for(int i=0; i<3; i++){
            if(!visit[i]) {
                visit[i] = true;
                key[n] = i;
                perm(n+1);
                visit[i] = false;
            }
        }
    }
    
    long go(){
        for(int k : key){
            for(int i=0; i<exp_copy.length; i++){
                if(exp_copy[i].equals(pm[k])){
                    int start=i, end=i;
                    long left=-1, right=-1;
                    while(true){
                        --start;
                        if(exp_copy[start].length() == 0) continue;
                        left = Long.parseLong(exp_copy[start]);
                        exp_copy[start] = "";
                        break;
                    }
                    while(true){
                        ++end;
                        if(exp_copy[end].length() == 0) continue;
                        right = Long.parseLong(exp_copy[end]);
                        exp_copy[end] = "";
                        break;
                    }
                    exp_copy[i] = calc(left, right, exp_copy[i]) + "";
                }
            }
        }
        for(String s : exp_copy){
            if(s.length()!=0) {
                return Long.parseLong(s);
            }
        }
        return -1;
    }
    
    long calc(long a, long b, String s){
        switch(s){
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
        }
        return -1;
    }
    
    public long solution(String expression) {
        char[] tmp = expression.toCharArray();
        String str = "";
        List<String> strs = new ArrayList<String>();
        for(char c : tmp){
            if(c=='+' || c=='-' || c=='*') {
                strs.add(str);
                strs.add(c+"");
                str="";
            }else str += c;
        }
        strs.add(str);
        exp = strs.toArray(new String[strs.size()]);
        exp_copy = new String[exp.length];
        
        perm(0);
        
        long answer = max;
        return answer;
    }
}
