package group_project;



public class QueueAndStackTest {
	public static void main(String[] args)
	{
		Queue<Integer> queue = new Queue<Integer>();
		queue.add(1);
		System.out.println(queue.element());
		queue.add(2);
		System.out.println(queue.element());
		queue.remove();
		System.out.println(queue.element());
		
		
		
		Stack<String> stack = new Stack<String>();
		stack.push("World");
		stack.push("Hello");
		System.out.println(stack.peek());
		System.out.println(stack.pop() + " " + stack.pop());
	}
}
