package linked;

public class Stack<T> {

    LinkedList<T> stack = new LinkedList<T>();

    //push an item onto end of stack
    public void push(T item) {
        stack.add(item);
    }

    //remove the last item from the stack and return it
    public T pop() {
        Node<T> temp = stack.getLast();
        T data = temp.getData();
        if (temp.getPrev() != null) {
            temp = temp.getPrev();
            temp.setNext(null);
        } else {
            stack.clear();
        }
        return data;
    }

    //return the last item on the stack WITHOUT removing it
    public T peek() {
        Node<T> temp = stack.getLast();
        return temp.getData();
    }

    //return true if stack has no items in it, false if it does
    public boolean isEmpty() {
        return stack.isEmpty();
    }

}
