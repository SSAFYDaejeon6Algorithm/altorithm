class Solution {
	    String answer = "";
	    int max_gap = -1;
	    
	    public int[] solution(int n, int[] info) {
	        select(n, info, 0, 0, "");
	        
	        if(answer.equals(""))
	            // can't win
	            return new int[]{-1};
	        else {
	            // covert string to int
	            int[] ret = new int[11];
	            for(int i = 0; i < 11; i++)
	                ret[i] = answer.charAt(i) - '0';
	            return ret;
	        }
	    }
	    
	    void select(int n, int[] info, int idx, int cnt, String shoot) {
	        if(idx == 11 && cnt <= n) {
	            // cal score
	            int lion = 0, peach = 0;
	            for(int i = 0; i < 11; i++) {
	                if(shoot.charAt(i) != '0')
	                    lion += 10 - i;
	                else if(info[i] != 0)
	                    peach += 10 - i;
	            }
	            
	            int gap = lion - peach;    
	            if(gap <= 0)
	                return;
	            else if(gap > max_gap) {
	                answer = shoot;
	                max_gap = gap;
	            }
	            // pick case that shot more low shoot
	            else if(gap == max_gap) {
	                for(int i = 10; i >= 0; i--) {
	                    if(answer.charAt(i) < shoot.charAt(i))
	                        answer = shoot;
	                    else if(answer.charAt(i) > shoot.charAt(i))
	                        break;
	                }
	            }
	            return;
	        }
	        
	        // shoot
	        int next_shoot = info[idx] + 1;
	        if(n >= cnt + next_shoot)
	            select(n, info, idx + 1, cnt + next_shoot, shoot + next_shoot);
	        // not shoot
	        if(idx == 10)
				      select(n, info, idx + 1, cnt, shoot + (n - cnt));
	        else
	            select(n, info, idx + 1, cnt, shoot + "0");
	    }
	}
