class GenericNLL<T> {

    private static int counter;
    private Node head;

    // appends the specified element to the end of this list.
    public void Add(T data) {

        // Initialize Node` only incase of 1st element
        if (head == null) {
            head = new Node(data);
        }

        Node Temp = new Node(data);
        Node CurrentNode = head;

        // Let's check for NPE before iterate over CurrentNode
        if (CurrentNode != null) {

            // starting at the head node, crawl to the end of the list and then add element after last node
            while (CurrentNode.getNext() != null) {
                CurrentNode = CurrentNode.getNext();
            }

            // the last node's "next" reference set to our new node
            CurrentNode.setNext(Temp);
        }

        // increment the number of elements variable
        incrementCounter();
    }

    private static int getCounter() {
        return counter;
    }

    private static void incrementCounter() {
        counter++;
    }

    private void decrementCounter() {
        counter--;
    }

    // inserts the specified element at the specified position in this list
    public void Add(T data, int index) {
        Node Temp = new Node(data);
        Node CurrentNode = head;

        // Let's check for NPE before iterate over CurrentNode
        if (CurrentNode != null) {
            // crawl to the requested index or the last element in the list, whichever comes first
            for (int i = 0; i < index && CurrentNode.getNext() != null; i++) {
                CurrentNode = CurrentNode.getNext();
            }
        }

        // set the new node's next-node reference to this node's next-node reference
        Temp.setNext(CurrentNode.getNext());

        // now set this node's next-node reference to the new node
        CurrentNode.setNext(Temp);

        // increment the number of elements variable
        incrementCounter();
    }

    public Object Get(int index)
    // returns the element at the specified position in this list.
    {
        // index must be 1 or higher
        if (index < 0)
            return null;
        Node CurrentNode = null;
        if (head != null) {
            CurrentNode = head.getNext();
            for (int i = 0; i < index; i++) {
                if (CurrentNode.getNext() == null)
                    return null;

                CurrentNode = CurrentNode.getNext();
            }
            return CurrentNode.getData();
        }
        return CurrentNode;

    }

    // removes the element at the specified position in this list.
    public boolean Remove(int index) {

        // if the index is out of range, exit
        if (index < 1 || index > Size())
            return false;

        Node CurrentNode = head;
        if (head != null) {
            for (int i = 0; i < index; i++) {
                if (CurrentNode.getNext() == null)
                    return false;

                CurrentNode = CurrentNode.getNext();
            }
            CurrentNode.setNext(CurrentNode.getNext().getNext());

            // decrement the number of elements variable
            decrementCounter();
            return true;

        }
        return false;
    }

    // returns the number of elements in this list.
    public int Size() {
        return getCounter();
    }

    public String toString() {
        String output = "";

        if (head != null) {
            Node CurrentNode = head.getNext();
            while (CurrentNode != null) {
                output += "[" + CurrentNode.getData().toString() + "]";
                CurrentNode = CurrentNode.getNext();
            }

        }
        return output;
    }

    private class Node {
        // reference to the next node in the chain, or null if there isn't one.
        Node next;

        // data carried by this node. could be of any type you need.
        T data;

        // Node constructor
        public Node(T dataValue) {
            next = null;
            data = dataValue;
        }

        // another Node constructor if we want to specify the node to point to.
        public Node(T dataValue, Node nextValue) {
            next = nextValue;
            data = dataValue;
        }

        // these methods should be self-explanatory
        public T getData() {
            return data;
        }

        public void setData(T dataValue) {
            data = dataValue;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node nextValue) {
            next = nextValue;
        }

    }
}