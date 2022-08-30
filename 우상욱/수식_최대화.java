package programmers;

import java.util.Arrays;
import java.util.List;


public class 수식_최대화 {
	
	static String prior[][] = {{"+","-","*"}, {"+","*","-"}, {"-","+","*"}, {"-","*","+"}, {"*","+","-"}, {"*","-","+"}};

	public static void main(String[] args) {
		String expression = "50*6-3*2";
		String[] nums = expression.split("\\D");		
		String[] operators = remove_space(expression.split("\\d"));
	
		long answer = 0;
		for(String[] p : prior) {
			String[] tmp_nums = Arrays.copyOf(nums, nums.length);
			String[] tmp_opers = Arrays.copyOf(operators, operators.length);
			
			for(String op : p) {
				calculate(tmp_nums, tmp_opers, op);
				tmp_nums = remove_space(tmp_nums);
				tmp_opers = remove_space(tmp_opers);
			}
			answer = (long) Math.max(answer, Math.abs(Double.parseDouble(tmp_nums[0])));
		}
		System.out.println(answer);
	}
	
	static void calculate(String[] nums, String[] operators, String operator_param) {
		for(int i = 0; i < operators.length; i++) {
			if(operators[i].equals(operator_param)) {
				nums[i + 1] = cal_num(nums[i], nums[i + 1], operator_param);
				nums[i] = "";
				operators[i] = "";
			}
		}
	}
	
	static String cal_num(String n1, String n2, String op) {
		long num1 = Long.parseLong(n1), num2 = Long.parseLong(n2);
		long res = 0;
		
		if(op.equals("*"))
			res = num1 * num2;
		else if (op.equals("-"))
			res = num1 - num2;
		else if (op.equals("+"))
			res = num1 + num2;
		
		return Long.toString(res);
	}
	
	static String[] remove_space(String[] arr) {
		return Arrays.stream(arr).filter(s -> !s.equals("")).toArray(String[]::new);
	}
}

