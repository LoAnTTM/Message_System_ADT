import java.util.Iterator;

class Queue<E> implements AbstractQueue<E>{
    private class Node<E> {
        private E element;
        public Node<E> next;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;

    private int size;
    public Queue( ) {
        head = null;
        tail = null;
        size = 0;
    }
    @Override
    public void offer( E data ) {
        Node<E> newNode = new Node<>( data );
        if ( isEmpty( ) ) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    @Override
    public E poll( ) {
        if ( isEmpty( ) ) {
            System.out.println("Error: Queue is empty. Cannot poll.");
            return null;
        }
        E element = head.element;
        if ( size == 1 ) {
            head = null;
            tail = null;
        } else {
            Node<E> next = head.next;
            head.next = null;
            head = next;
        }
        size--;
        return element;
    }
    @Override
    public E peek( ) {
        if ( isEmpty( ) ) {
            System.out.println("Error: Queue is empty. Cannot peek.");
            return null;
        }
        return head.element;
    }
    @Override
    public int size( ) {
        return size;
    }
    @Override
    public boolean isEmpty( ) {
        return ( head == null && tail == null ); // or size == 0
    }
    @Override
    public Iterator<E> iterator( ) {
        return new Iterator<>( ) {
            private Node<E> current = head;
            public boolean hasNext( ) {
                return current != null;
            }
            public E next( ) {
                if ( !hasNext( ) ) {
                    System.out.println("Error: Queue is empty. ");
                    return null;
                }
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    public void print() {
        Node<E> current = head;
        System.out.println( size + " message in queue.");
        while (current != null) {
            System.out.println(current.element + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void reset() {
        while (!isEmpty()) {
            this.poll();
        }
    }
}