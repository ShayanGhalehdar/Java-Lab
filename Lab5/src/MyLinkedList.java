
public class MyLinkedList<E> {

    Node head=null;
    private int len=0;

    // insert at the end of the list
    public void insert(E e) {
        // this is the first item
        if (this.head==null) {
            this.head = new Node(e);
        }
        else {
            Node last = this.head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new Node(e);
        }
        this.len++;
    }

    // insert at a specified index
    public void insert(E e, int idx) throws CloneNotSupportedException {
        if (idx > this.len)
            throw new ArrayIndexOutOfBoundsException("index out of bounds!");
        else if (idx == this.len)
            this.insert(e);
        else if (idx==0) {
            Node middle = new Node(head.data);
            middle.next = (Node) this.head.next.clone();
            this.head.data = e;
            this.head.next = middle;
            this.len++;
        }
        else {
            Node temp = this.head;
            for (int k = 0; k < idx-1; k++) {
                temp = temp.next;
            }
            Node middle = new Node(e);
            middle.next = (Node) temp.next.clone();
            temp.next = middle;
            this.len++;
        }
    }

    // remove last element of list
    public void remove() {
        if (this.len == 0)
            throw new NegativeArraySizeException("array is already empty!");
        else {
            Node last = this.head;
            while (last.next.next != null) {
                last = last.next;
            }
            last.next = null;
        }
        this.len--;
    }

    public void remove(int idx) {
        if (idx >= this.len)
            throw new ArrayIndexOutOfBoundsException("index out of bounds!");
        else if (idx == 0) {
            this.head = this.head.next;
            this.len--;
        }
        else if (idx == this.len-1)
            this.remove();
        else {
            Node temp = this.head;
            for (int k=0; k<idx-1; k++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
            this.len--;
        }

    }

    public E get(int idx) throws ArrayIndexOutOfBoundsException{

        Node temp = this.head;
        for (int k=0; k<idx; k++) {
            if (temp.next == null)
                throw new ArrayIndexOutOfBoundsException("index out of bounds!");
            else
                temp = temp.next;
        }
        return temp.data;
    }

    public int len() {
        return this.len;
    }


    private class Node implements Cloneable{
        E data;
        Node next;

        Node(E d) {
            this.data = d;
            this.next = null;
        }

        protected Object clone() throws CloneNotSupportedException{
            return super.clone();
        }
    }

}


