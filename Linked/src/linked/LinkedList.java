package linked;

public class LinkedList<T> {

    private Node<T> head;

    // Add a new item to end of list
    public void add(T item) {
        // create new node to hold item
        Node<T> node = new Node<T>();
        node.setData(item);

        // special case - first item in list
        if (head == null) {
            head = node;
        } else { // not first item
            // walk list until last item. Add new node at end
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(node);
        }
    }

    //Add item at first index where the data is null, if no indexes are null,
    //the data is added at the end of the list
    public void addAtNull(T item) {
        // special case - first item in list
        Node<T> node = new Node<T>();
        if (head == null) {
            node.setData(item);
            head = node;
        } else { // not first item
            Node temp = head;

            while (true) {
                if (temp.getNext() != null && temp.getData() == null) {
                    temp.setData(item);
                    break;
                } else if (temp.getNext() != null && temp.getData() != null) {
                    temp = temp.getNext();
                } else if (temp.getNext() == null && temp.getData() == null) {
                    temp.setData(item);
                    break;
                    //Need to tell it to add at the end if there is no null
                } else if (temp.getNext() == null && temp.getData() != null) {
                    node.setData(item);
                    temp.setNext(node);
                    break;
                }
            }
        }
    }

    // add item at a specific point in list
    public void add(T item, int index) {
        // create new node to hold item
        Node<T> newNode = new Node<T>();
        newNode.setData(item);

        // special case. New first item
        if (index == 0) {
            Node oldHead = head;
            head = newNode;
            newNode.setNext(oldHead);
        } else { // not first item. Need to walk items until correct spot and 
            // the add node. May need to create "empty" nodes along the way
            // if nodes dont exist all the way to the desired index

            // Make sure we have a head or create an empty one
            if (head == null) {
                head = new Node<T>();
            }
            // loop through nodes until desired index
            Node next = head;
            for (int i = 0; i < index - 1; i++) {
                Node temp = next.getNext();
                // create an empty node if needed
                if (temp == null) {
                    temp = new Node<T>();
                    next.setNext(temp);
                }
                next = temp;
            }
            // We got to desired index, now add new node
            Node oldNext = next.getNext();
            next.setNext(newNode);
            newNode.setNext(oldNext);
        }
    }

    public void clear() {
        //this will do the trick, although I imagine it is not the best way
        head = null;
    }

    //returns the data at the index specified.
    public T get(int index) {
        Node<T> temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.getNext();
        }
        return temp.getData();
    }

    //gets the last element in the list.
    public T getLast() {
        Node<T> temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        return temp.getData();
    }

    //Removes the first item and returns it's data.
    public T remove() {
        Node<T> temp = head;
        head = temp.getNext();
        T data = temp.getData();
        return data;
    }

    //checks list for specified data and returns true if it is found.
    public boolean contains(T data) {
        Node<T> temp = head;
        T compare;
        boolean check = false;
        while (temp.getNext() != null) {
            compare = temp.getData();
            if (compare == null) {
                temp = temp.getNext();
            } else if (compare.equals(data)) {
                check = true;
                break;
            } else {
                temp = temp.getNext();
            }
        }
        return check;
    }

    //Returns the size of the list.
    public int size() {
        int size = 1;
        Node temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
            size++;
        }
        return size;
    }

    //Returns the index of the first occurence of the element.
    public int indexOf(T data) {
        int index = 0;
        Node<T> temp;
        for (temp = head; temp.getNext() != null; temp = temp.getNext()) {
            if (temp.getData() == null) {
                continue;
            } else if (data.equals(temp.getData())) {
                break;
            }
            index++;
        }
        return index;
    }
}
