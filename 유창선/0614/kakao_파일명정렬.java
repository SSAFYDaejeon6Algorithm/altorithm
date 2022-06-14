import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        ArrayList<File> arrayList = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            arrayList.add(new File(files[i], i));
        }

        Collections.sort(arrayList); // comparator 대로 정렬

        for (int i = 0; i < answer.length; i++) {
            answer[i] = arrayList.get(i).file;
        }

        return answer;
    }

    public static class File implements Comparable<File>{
        int pos;
        String file;
        String header;
        String number;

        File(String file, int pos) {
            this.pos = pos;
            this.file = file;

            // header 추출
            int i = 0;
            while(file.charAt(i) < 48 || file.charAt(i) > 57) i++;
            this.header = file.substring(0, i);

            // number 추출
            int j = i;
            while (j < file.length() && file.charAt(j) >= 48 && file.charAt(j) <= 57) j++;
            this.number = file.substring(i, j);
        }

        @Override
        public int compareTo(File o) {
            String lh1 = this.header.toLowerCase();
            String lh2 = o.header.toLowerCase();

            if (lh1.equals(lh2)) {
                int num1 = Integer.parseInt(this.number);
                int num2 = Integer.parseInt(o.number);
                if (num1 == num2) {
                    return this.pos - o.pos;
                }else {
                    return num1 - num2;
                }
            } else {
                return lh1.compareTo(lh2);
            }

        }
    }
}