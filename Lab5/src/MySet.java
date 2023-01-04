

public class MySet<E> {

    int hashSize = 10;
    MyArrayList<MyLinkedList<E>> hashTable = new MyArrayList<>();

    public MySet() {
        for (int i=0; i<this.hashSize; i++)
            this.hashTable.add(new MyLinkedList<E>());
    }

    public void add(E e) {
        int hashCode = this.calculateHashCode(e);
        if (!this.checkForDuplicate(e)) {
            MyLinkedList<E> temp = (MyLinkedList<E>) this.hashTable.get(hashCode);
            temp.insert(e);
        }
    }

    public void remove(E e) {
        int hashCode = this.calculateHashCode(e);
        int idx = this.checkForDuplicate(e, hashCode);
        if (idx == -1) {
            throw new ArrayIndexOutOfBoundsException("Element not found");
        }
        else {
            MyLinkedList<E> temp = (MyLinkedList<E>) this.hashTable.get(hashCode);
            temp.remove(idx);
        }

    }

    public boolean checkForDuplicate(E e) {
        int hashCode = this.calculateHashCode(e);
        MyLinkedList<E> temp = (MyLinkedList<E>) this.hashTable.get(hashCode);
        for (int i=0; i<temp.len(); i++) {
            if (temp.get(i).equals(e)) {
                return true;
            }
        }
        return false;
    }

    public void printAll() {
        for (int row=0; row<hashTable.len(); row++) {
            MyLinkedList<E> temp = (MyLinkedList<E>) this.hashTable.get(row);
            for (int elem = 0; elem< temp.len(); elem++)
                System.out.println(temp.get(0));
        }
    }

    private int checkForDuplicate(E e, int hashCode) {
        MyLinkedList<E> temp = (MyLinkedList<E>) this.hashTable.get(hashCode);
        for (int i=0; i<temp.len(); i++) {
            if (temp.get(i).equals(e)) {
                return i;
            }
        }
        return -1;
    }

    private int calculateHashCode(E e) {
        return System.identityHashCode(e)%this.hashSize;
    }




}
