import java.util.*;



public class BasicCalculator2 {

	Map<Character, Integer> map;



	public BasicCalculator2() {

		map = new HashMap<>();

		map.put('+', 1);

		map.put('-', 1);

		map.put('*', 2);

		map.put('/', 2);

		;

	}



	public int[] BasicCalculator(String s, int i) {

		if (s == null)

			return new int[] { 0, 0 };

		Stack<Character> ops = new Stack<>();

		Stack<Integer> nums = new Stack<>();

		while (i < s.length()) {

			char cur = s.charAt(i);

			if (cur == ' ') {

				++i;

			} else if (Character.isDigit(cur)) {

				int num = 0;

				while (i < s.length() && Character.isDigit(s.charAt(i)))

					num = num * 10 + (int) (s.charAt(i++) - '0');

				nums.push(num);

			} else if (cur == '(') {

				int[] xx = BasicCalculator(s, i + 1);

				i = xx[1] + 1;

				nums.push(xx[0]);

			} else if (cur == ')') {

				break;

			} else {

				calcu(nums, ops, cur);

				ops.push(cur);

				++i;

			}

		}

		calcu(nums, ops, '+');

		return new int[] { nums.isEmpty() ? 0 : nums.pop(), i };

	}



	private int helper(Stack<Integer> nums, char c) {

		int num1 = nums.pop(), num2 = nums.isEmpty() ? 0 : nums.pop();

		switch (c) {

		case '+':

			return num1 + num2;

		case '-':

			return num2 - num1;

		case '*':

			return num2 * num1;

		default:

			return num2 / num1;

		}

	}



	private void calcu(Stack<Integer> nums, Stack<Character> ops, char cur) {

		while (!ops.isEmpty() && map.get(cur) <= map.get(ops.peek())) {

			//System.out.println(nums.toString());

			//System.out.println(ops.toString());

			nums.push(helper(nums, ops.pop()));

		}

	}



	public static void main(String[] args) {

		// TODO Auto-generated method stub
		BasicCalculator2 bc = new BasicCalculator2();

		int res = bc.BasicCalculator("-5*(-3-3+2/1+5)", 0)[0];

		//System.out.println(res);

	}



}