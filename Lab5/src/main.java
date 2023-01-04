import java.util.ArrayList;

public class main {

    public static void main(String[] args) throws CloneNotSupportedException {

    //    test_MyArrayList();
    //    test_MyLinkedList();
        test_MySet();
    }

    public static void test_MyArrayList() {
        MyArrayList<Integer> arrayList1 = new MyArrayList<>();

        System.out.println("\n\n*** arrayList ***\n");
        for (int i=0; i<99; i++) {
            arrayList1.add(i);
            System.out.println("len: " + arrayList1.len() + ", size: " + arrayList1.memory());
        }
        for (int i=80; i>=0; i--) {
            arrayList1.remove(i);
            System.out.println("len: " + arrayList1.len() + ", size: " + arrayList1.memory());
        }

        System.out.println("MyArrayList test completed!");
    }

    public static void test_MyLinkedList() throws CloneNotSupportedException{
        MyLinkedList<Double> linkedList1 = new MyLinkedList<>();

        linkedList1.insert(1.0, 0);
        linkedList1.insert(2.0);
        linkedList1.insert(3.0,0);
        linkedList1.insert(4.0,1);
        linkedList1.insert(5.0,4);

        System.out.println("\n\n*** linkedList ***\n");

        for (int i=0; i< linkedList1.len(); i++)
            System.out.println("item at index=" + i + ": "+ linkedList1.get(i));
        System.out.println("len:" + linkedList1.len() + "\n");

        linkedList1.remove();
        linkedList1.remove(2);
        linkedList1.remove(0);
        linkedList1.remove(1);

        for (int i=0; i< linkedList1.len(); i++)
            System.out.println("item at index=" + i + ": "+ linkedList1.get(i));
        System.out.println("len:" + linkedList1.len());
    }

    public static void test_MySet(){
        MySet<Integer> set1 = new MySet<>();

        set1.add(2);
        set1.add(4);
        set1.add(3);
        set1.add(2);
        set1.add(4);
        set1.printAll();
        System.out.println();

        set1.remove(2);
        set1.printAll();
        System.out.println();

        System.out.println(set1.checkForDuplicate(2));
        System.out.println(set1.checkForDuplicate(3));
    }

}
