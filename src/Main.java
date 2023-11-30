import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        MyList<String> list1 = new MyArrayList<>();
        System.out.println("MyArrayList:");
        testList(list1);

        System.out.println();

        MyList<String> list2 = new MyLinkedList<>();
        System.out.println("MyLinkedList:");
        testList(list2);
    }

    public static void testList(MyList<String> list) {
        list.add("CCC");
        list.add("FFF");
        list.add("AAA");
        list.add("DDD");
        list.add("BBB");
        list.add("EEE");

        System.out.println(list);
        list.sort(Comparator.naturalOrder());
        System.out.println(list);
    }
}
