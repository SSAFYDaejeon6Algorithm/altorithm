import java.util.Arrays;

class Solution {
    public String[] solution(String[] files) {
        
        Arrays.sort(files, (o1, o2) -> {
            String headA = o1.split("[0-9]")[0].toUpperCase(); // number앞까지 자르기, 문자열 비교 시 대소문자 구분을 하지 않음
            String headB = o2.split("[0-9]")[0].toUpperCase(); 
            
            if(headA.compareTo(headB) == 0){ // 파일명의 HEAD 부분이 대소문자 차이 외에는 같을 경우, NUMBER의 숫자 순으로 정렬
                String numberA = o1.substring(headA.length());
                String numberB = o2.substring(headB.length());
                
                // 숫자끼리 비교해야 함
                return Integer.parseInt(splitNum(numberA)) - Integer.parseInt(splitNum(numberB));
            }else{
                return headA.compareTo(headB);
            }
        });
        
        return files;
    }
    
    private static String splitNum(String num) { //최대 5자리인 number 계산
        StringBuilder sb = new StringBuilder(); // number+Tail 형태에서 number만을 담아주기 위해 선언
        for (char ch : num.toCharArray()) {
            if (Character.isDigit(ch) && sb.length() <= 5) {//숫자이고 다섯글자 이하
                sb.append(ch);
            } else {
                return sb.toString();
            }
        }
        return sb.toString();
    }
}