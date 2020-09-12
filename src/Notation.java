
public class Notation {

	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {

		NotationQueue<Character> queue = new NotationQueue<>(infix.length());
		NotationStack<Character> stack = new NotationStack<>(infix.length());
		char[] str = infix.toCharArray();

		int left = 0;
		int right = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] == '(') {
				left++;
				continue;
			}
			if (str[i] == ')') {
				right++;
				continue;
			}
		}
		if (left != right) {
			throw new InvalidNotationFormatException();
		}
		try {
			for (char current : str) {
				if (current == ' ') {
					continue;
				}
				if (Character.isDigit(current)) {
					queue.enqueue(current);
					continue;
				}
				if (current == '(') {
					stack.push(current);
				}
				if (current == '*' || current == '/' || current == '+' || current == '-') {
					if (!queue.isEmpty()) {
						char top = stack.top();
						if (top == '*' || top == '/' || current == '-' && top == '-' || current == '-' && top == '+'
								|| current == '+' && top == '-' || current == '+' && top == '+') {
							queue.enqueue(stack.pop());

						}
					}
					stack.push(current);
					continue;
				}
				if (current == ')') {
					while (stack.top() != '(') {
						queue.enqueue(stack.pop());
						if (stack.top() == null) {
							throw new InvalidNotationFormatException();
						}
					}
					stack.pop();
				}

			}
		} catch (QueueOverflowException | StackOverflowException | StackUnderflowException ignore) {

		}
		return queue.toString();

	}

	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {

		NotationStack<String> stack = new NotationStack<>(postfix.length());

		try {
			for (int i = 0; i < postfix.length(); i++) {
				char current = postfix.charAt(i);

				if (current == ' ') {
					continue;
				}
				if (Character.isDigit(current)) {
					stack.push(Character.toString(current));
					continue;
				}
				if (current == '*' || current == '/' || current == '+' || current == '-') {
					if (stack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					String first = stack.pop();
					String second = stack.pop();
					String s = "(" + second + current + first + ")";
					stack.push(s);

				}
			}

		} catch (StackUnderflowException | StackOverflowException ignore) {

		}
		if (stack.size() > 1) {
			throw new InvalidNotationFormatException();
		}
		return stack.toString();
	}

	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {

		NotationStack<Double> stack = new NotationStack<>(postfixExpr.length());
		char[] str = postfixExpr.toCharArray();

		try {
			for (char current : str) {
				if (current == ' ') {
					continue;
				}
				if (Character.isDigit(current) || current == '(') {
					stack.push(Double.parseDouble(Character.toString(current)));
					continue;
				}
				if (current == '*' || current == '/' || current == '+' || current == '-') {
					if (stack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					double right = stack.pop();
					double left = stack.pop();

					switch (current) {
					case '*':
						stack.push(left * right);
						break;
					case '/':
						stack.push(left / right);
						break;
					case '+':
						stack.push(left + right);
						break;
					case '-':
						stack.push(left - right);
					}

				}

			}

		} catch (StackOverflowException | StackUnderflowException ignore) {

		}

		if (stack.size() > 1) {
			throw new InvalidNotationFormatException();
		}

		return Double.parseDouble(stack.toString());

	}

}
