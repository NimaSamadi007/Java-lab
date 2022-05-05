import java.util.HashMap;

public class Main {

    /*
    List :
    In Java, a list interface is an ordered collection of objects in which duplicate values can be stored.
    Since a List preserves the insertion order, it allows positional access and insertion of elements.
    List interface is implemented by the following classes:
        1. ArrayList :
            An ArrayList class is a resizable array, which is present in the java.util package.
            While built-in arrays have a fixed size, ArrayLists can change their size dynamically.
            Elements can be added and removed from an ArrayList whenever there is a need, helping
            the user with memory management.

        2. LinkedList :
            Since a LinkedList acts as a dynamic array and we do not have to specify the size while creating it,
            the size of the list automatically increases when we dynamically add and remove items. And also,
            the elements are not stored in a continuous fashion. Therefore, there is no need to increase the size.
            Internally, the LinkedList is implemented using the doubly linked list data structure. The main difference
            between a normal linked list and a doubly LinkedList is that a doubly linked list contains an extra pointer,
            typically called the previous pointer, together with the next pointer and data which are there in the singly
            linked list.

        3. Vector
        4. Stack

    Set : It is an interface that implements the mathematical set. This interface contains the methods inherited from
    the Collection interface and adds a feature that restricts the insertion of the duplicate elements.
    There are two interfaces that extend the set implementation namely SortedSet and NavigableSet.

    Queue : A queue is a data structure which follows the principle of FIFO (First-In-First-Out) i.e. the elements are
    inserted at the end of the list, and are deleted from the beginning of the list. This interface is available in the
    java.util.package and extends the Collection Interface. Queue supports multiple methods, including insertion and
    deletion. The queues available in java.util.package are known as Unbounded Queues , while the queues present in the
    java.util.concurrent package are known are Bounded Queues. All queues, except the Deques, support insertion at the
    end and deletion from the front. Deques support insertion and deletion of elements at both the ends.

    Map : Maps are perfect to use for key-value association mapping such as dictionaries. The maps are used to perform
    lookups by keys or when someone wants to retrieve and update elements by keys. Some common scenarios are as follows:

        1. A map of error codes and their descriptions.
        2. A map of zip codes and cities.
        3. A map of managers and employees. Each manager (key) is associated with a list of employees (value) he manages.
        4. A map of classes and students. Each class (key) is associated with a list of students (value).

    A Map cannot contain duplicate keys and each key can map to at most one value. Some implementations allow null key
    and null values like the HashMap and LinkedHashMap, but some do not like the TreeMap. The order of a map depends on
    the specific implementations. For example, TreeMap and LinkedHashMap have predictable orders, while HashMap does
    not. There are two interfaces for implementing Map in java. They are Map and SortedMap, and three classes: HashMap,
    TreeMap, and LinkedHashMap.
     */
    public static void main(String[] args){

        // -- -- -- Type 1 Test Cases -- -- -- //
        System.out.print(" -- -- -- Type 1 Test Case -- -- -- \n");
        NumericalArrayList A = new NumericalArrayList();

        A.Add(2);
        A.Add(3);
        A.Add(7);
        A.Add(4);
        A.Add(10);

        // A.Remove(1);
        // A.Clear();
        // A.Add(50);
        System.out.print("Size of List : ");
        System.out.print(A.Size());
        System.out.print("\n");

        for(int i = 0; i < A.Size(); i++) {
            System.out.printf("List Element %d : ", i);
            System.out.print(A.Get(i));
            System.out.print("\n");
        }

        // -- -- -- Type 2 Test Cases -- -- -- //
        System.out.print(" -- -- -- Type 2 Test Case -- -- -- \n");
        NumericalLinkedList B = new NumericalLinkedList();

        B.Add(2);
        B.Add(3);
        B.Add(7);
        B.Add(4);
        B.Add(10);

        B.Remove(1);
        System.out.print("Size of List : ");
        System.out.print(B.Size());
        System.out.print("\n");

        for(int i = 0; i < B.Size(); i++) {
            System.out.printf("List Element %d : ", i);
            System.out.print(B.Get(i));
            System.out.print("\n");
        }

        // -- -- -- Type 3 Test Cases -- -- -- //
        System.out.print(" -- -- -- Type 3 Test Case -- -- -- \n");
        GenericNAL C = new GenericNAL<String>();

        C.Add("Milad");
        C.Add("Nima");
        C.Add("Akbar");
        C.Add("Kobra");
        C.Add("Max");

        C.Remove(1);
        System.out.print("Size of List : ");
        System.out.print(C.Size());
        System.out.print("\n");

        for(int i = 0; i < C.Size(); i++) {
            System.out.printf("List Element %d : ", i);
            System.out.print(C.Get(i));
            System.out.print("\n");
        }

        // -- -- -- Type 4 Test Cases -- -- -- //
        System.out.print(" -- -- -- Type 4 Test Case -- -- -- \n");
        GenericNLL D = new GenericNLL<Float>();

        D.Add(3.6235);
        D.Add(97.101);
        D.Add(50);
        D.Add(19.0002);
        D.Add(0.02);

        D.Remove(1);
        System.out.print("Size of List : ");
        System.out.print(D.Size());
        System.out.print("\n");

        for(int i = 0; i < D.Size(); i++) {
            System.out.printf("List Element %d : ", i);
            System.out.print(D.Get(i));
            System.out.print("\n");
        }

        // -- -- -- Type 5 Test Cases -- -- -- //
        System.out.print(" -- -- -- Type 5 Test Case -- -- -- \n");
        Map<String, Integer> Set = new Map<>();
        Set.Add("this", 1);
        Set.Add("coder", 2);
        Set.Add("this", 4);
        Set.Add("hi", 5);
        System.out.println(Set.Size());
        Set.Exist("this");
        System.out.println(Set.Remove("this"));
        Set.Exist("this");
        Set.Exist("coder");
        System.out.println(Set.Remove("this"));
        System.out.println(Set.Size());
        System.out.println(Set.isEmpty());
        System.out.print(Set.Get("hi"));
    }
}
