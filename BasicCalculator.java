import java.util.*;

public class BasicCalculator {
	public int BasicCalculator(String s) {
		Stack<Character> ops = new Stack<>();
		Stack<Integer> nums = new Stack<>();
		if (s == null)
			return 0;
		int i = 0;
		while (i < s.length()) {
			while (s.charAt(i) == ' ')
				i++;
			if (Character.isDigit(s.charAt(i))) {
				int num = (int) (s.charAt(i++) - '0');
				while (i < s.length() && Character.isDigit(s.charAt(i)))
					num = num * 10 + (int) (s.charAt(i++) - '0');
				nums.push(num);
			}else {
				char cur=s.charAt(i);
				if (!ops.isEmpty()&&cur!='(') {
					char c = ops.peek();
					while(!ops.isEmpty()&&(((cur=='+'||cur=='-'||cur==')')&&c!='(')||c=='*'||c=='/')){
						ops.pop();
						nums.push(helper(nums,c));
						if(!ops.isEmpty())
							c=ops.peek();
						else
							break;	
					}
					if(cur==')'&&c=='(')
						ops.pop();
				}
				if(cur!=')')
					ops.push(s.charAt(i));
				i++;
			}
		}
		while (!ops.isEmpty()) {
			char c = ops.pop();
			nums.push(helper(nums,c));
		}
		return nums.pop();
	}

	public int helper(Stack<Integer> nums, char c) {
		int num1 = nums.pop(), num2 = nums.isEmpty() ? 0 : nums.pop();
		if (c == '+')
			return num1 + num2;
		else if (c == '-')
			return num2 - num1;
		else if (c == '*')
			return num2 * num1;
		else
			return num2 / num1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicCalculator bc = new BasicCalculator();
		int res = bc.BasicCalculator("5*(3-3+2/1+5)");
		System.out.println(res);
	}

}
