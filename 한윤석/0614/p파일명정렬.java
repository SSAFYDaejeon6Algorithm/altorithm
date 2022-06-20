
public class P파일명정렬 {

	static public String[] solution(String[] files) {
		int len = files.length;
    String[] answer = new String [len];
    File [] objArr = new File[len];

    for(int i=0; i<len; i++) {
      String file = files[i];
      String head = "";
      String num = "";
      String tail = "";

      for(int j=0; j<file.length(); j++) {
        char c = file.charAt(j);
        if (c - '0' >= 0 && c - '0' <= 9 && tail=="") num += c;
      else {
        if (num == "") head += c;
        else tail += c;
      }
      }
      objArr[i] = new File(head, num, tail);
    }

    Arrays.sort(objArr);

    for(int i=0; i<len; i++) {
      File f = objArr[i];
      answer[i] = f.head+f.num+f.tail;
    }

    return answer;
  }
	
	static class File implements Comparable<File>{
		String head, num, tail;
		public File(String head, String num, String tail) {
			this.head = head;
			this.tail = tail;
			this.num = num;
		}
		@Override
		public int compareTo(File o) {
			int headCmp = this.head.toLowerCase().compareTo(o.head.toLowerCase());
			if(headCmp == 0) {
				int numCmp = Integer.parseInt(this.num) - Integer.parseInt(o.num);
				return numCmp;
			}
			return headCmp;
		}
	}
}
