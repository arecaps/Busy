package linked;

public class Linked {

    public static void main(String[] args) {

        LinkedList<String> list = new LinkedList<String>();
        list.add("Hello");
        list.add("Goodbye");
        list.add("Hello Again");
        list.add("In middle", 1);
        list.add("New Begining", 0);

        System.out.println(list.getLast().getData());
        System.out.println(list.get(4));
        System.out.println(list.remove());
        //list.add("New end", 7);
        list.addAtNull("First null data spot");
        list.add("Hello");
        if (list.contains("Goodbye")) {
            System.out.println("Goodbye is in there");
        } else {
            System.out.println("Goodbye is NOT in there");
        }
        if (list.contains("Geshmak")) {
            System.out.println("Geshmak is in there");
        } else {
            System.out.println("Geshmak is NOT in there");
        }
        System.out.println(list.size());
        System.out.println(list.indexOf("Hello Again"));
        list.sort();
        list.clear();

        Stack<String> stack = new Stack<String>();
        if (stack.isEmpty()) {
            System.out.println("Stack is Empty");
        }
        stack.push("Hello");
        stack.push("How are you");
        stack.push("It's been a pleasure");
        stack.push("Goodbye");
        if (stack.isEmpty()) {
            System.out.println("Stack is Empty");
        }
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        if (stack.isEmpty()) {
            System.out.println("Stack is Empty");
        }

    }
}
