import java.util.*;

class Stack<E> implements AbstractStack<E>{
    private class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E value) {
            this.element = value;
        }
    }

    private Node<E> top;
    private int size;

    @Override
    public void push(E element) {
        Node<E> newNode = new Node(element);
        newNode.next = top;
        top = newNode;
        size++;
    }

    @Override
    public E pop() {
        if(top == null){
            System.out.println("Error: Stack is empty. Cannot pop.");
            return null;
        }
        E element = top.element;
        top = top.next;
        size--;
        return element;
    }

    @Override
    public E peek() {
        if(top == null){
            System.out.println("Error: Stack is empty. Cannot peek.");
            return null;
        }
        return top.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0 && top == null){
            return true;
        } else { return false; }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = top;
            @Override
            public boolean hasNext() {
                return current != null;
            }
            @Override
            public E next() {
                if (current == null){
                    System.out.println("Error: Stack is empty.");
                    return null;
                }
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    public void print() {
        Node<E> current = top;
        System.out.println(size + " message in stack.");
        while (current != null) {
            System.out.println(current.element + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void reset() {
        while (!isEmpty()) {
            this.pop();
        }
    }
}
