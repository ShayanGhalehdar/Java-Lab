import java.util.Arrays;

public class MyArrayList<E> {

    private int minSize = 10;
    private int size = minSize;
    private int len = 0;
    private Object[] elementData = new Object[size];

    // add an element to the end of the arrayList
    public void add(E e) {
        this.manageCapacity();
        this.elementData[len] = e;
        this.len ++;
    }

    // add an element to a specified location in the arrayList
    public void add(E e, int idx) {

        this.manageCapacity();
        System.arraycopy(this.elementData, idx, this.elementData, idx + 1, this.len - idx);
        this.elementData[idx] = e;
        this.len ++;
    }

    // remove the element in the specified location
    public void remove(int idx) {
        this.manageCapacity();
        System.arraycopy(this.elementData, idx + 1, this.elementData, idx, this.len - idx - 1);
        this.len --;
    }

    // get an element of the arrayList
    public Object get(int idx) {
        return this.elementData[idx];
    }

    // get the length of the arrayList
    public int len() {
        return this.len;
    }

    // get the total memory allocation of the elementData
    public int memory() {
        return this.size;
    }


    private void manageCapacity() {
        // add space to the array, when it is full
        if (this.size == this.len) {
            this.size = (this.size < 1000)? this.size + this.size/2 : this.size + 100;
            this.reallocateArray();
        }
        // free up some space when not needed
        else if ((this.size - this.len > this.size/2) && (this.size > 50)) {
            this.size = this.size*3/4;
            this.reallocateArray();
        }

    }

    private void reallocateArray() {
        this.elementData = Arrays.copyOf(this.elementData, this.size);
    }
}
