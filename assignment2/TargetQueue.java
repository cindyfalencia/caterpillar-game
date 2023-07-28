package assignment2;

public class TargetQueue extends MyQueue<Position> {
    private MyStack<String> stack;

    public TargetQueue() {
        super();
        stack = new MyStack<>();
    }

    public void clear() {
        super.clear();
        stack.clear();
    }

    public void addTargets(String input) throws IllegalArgumentException {
        String singleTargetRegex = "\\(\\d+,\\d+\\)";
        if (!(input.matches(String.format("^(|\\.?%1$s(\\.%1$s)*)\\.?$", singleTargetRegex))))
            // Note: Passing this check guaranteed that string matches the required format exactly
            throw new IllegalArgumentException();
        String num = "";
        for (char c : input.toString().toCharArray()) {
            switch (c) {
                case ',':
                    stack.push(num);
                    num = "";
                    break;
                case ')':
                    String x = stack.pop();
                    enqueue(new Position(Integer.valueOf(x), Integer.valueOf(num)));
                    num = "";
                    break;
                default:
                    if (Character.isDigit(c))
                        num += c;
            }
        }
    }
}
