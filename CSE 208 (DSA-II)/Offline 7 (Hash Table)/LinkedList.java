class Node<E>{
    private E element;                      //Value for the current node
    private Node<E> next;                   //Reference to the next node on the LinkedList.List
    Node(){
        this.element = null;
        this.next = null;
    }
    Node(E item, Node<E> nextVal){
        this.element = item;
        this.next = nextVal;
    }
    Node(Node<E> nextVal){
        this.next = nextVal;
    }
    Node<E> getNext(){
        return this.next;
    }
    Node<E> setNext(Node<E> nextVal){
        return this.next = nextVal;
    }
    E getElement(){
        return element;
    }
    E setElement(E item){
        return this.element = item;
    }
}

public class LinkedList<E> implements List<E> {
    public Node<E> head;                          //Reference to the first node
    private Node<E> tail;                         //Reference to the last node
    private Node<E> curr;                         //Reference to the current node
    private int count;                            //Keeps track of the list size
    public LinkedList(){
        curr = head = tail = new Node<E>();
        count = 0;
    }
    public LinkedList(E[] array, int k){
        for(int i =0;i< k;i++){
            Node<E> temp = new Node<>();
            temp.setElement(array[i]);
            temp.setNext(temp.getNext());
            if(head == null){
                this.head = temp;
                this.curr = temp;
            }
            else{
                curr.setNext(temp);
                curr = curr.getNext();
            }
            count++;
        }
        tail = curr;
        curr = head;
    }
    public void clear() {
        curr = head = tail = new Node<E>(null);
        count = 0;
    }
    public void insert(E item) {
        Node<E> first = new Node<E>(head);
        Node<E> second = new Node<>(head.getNext());
        int position = currPos();
        if(position == 0){
            Node<E> temp = new Node<>();
            temp.setElement(item);
            temp.setNext(head);
            head = temp;
            curr = head;
        }
        else{
            while(second!=curr){
                second = second.getNext();
                first = first.getNext();
            }
            Node<E> temp = new Node<E>();
            temp.setElement(item);
            first.setNext(temp);
            temp.setNext(second);
            curr = temp;
        }
        count++;
    }
    public void append(E item) {
        Node<E> temp = new Node<>();
        if(tail.getElement() == null){
            tail.setElement(item);
        }
        else{
            tail.setNext(temp);
            temp.setElement(item);
            temp.setNext(null);
            tail = temp;
        }
        count++;
    }
    public E remove() {
        E item;
        if(curr == tail){
            item = curr.getElement();
            prev();
            curr.setNext(null);
            tail = curr;
            count--;
        }
        else if(curr == head){
            item = curr.getElement();
            head = curr.getNext();
            curr = head;
            count--;
        }
        else{
            int x = currPos();
            item = curr.getElement();
            prev();
            curr.setNext(curr.getNext().getNext());
            moveToPos(x);
            count--;
        }
        if(count < 0){
            return null;
        }
        else{
            return item;
        }
    }
    public void moveToStart() {
        curr = head;
    }
    public void moveToEnd() {
        moveToStart();
        int x = count-1;
        while(x!=0){
            curr = curr.getNext();
            x--;
        }
    }
    public void prev() {
        Node<E> second = new Node<>(head.getNext());
        Node<E> first = new Node<>(head);
        if(curr == head){
        }
        else{
            while(second!=curr){
                second = second.getNext();
                first = first.getNext();
            }
            curr = first;
        }
    }
    public void next() {
        if(curr!=tail){
            curr = curr.getNext();
        }
    }
    public int length() {
        return count;
    }
    public int currPos() {
        int cnt =-1;
        Node<E> temp = new Node<>(head);
        while(temp!=curr){
            temp = temp.getNext();
            cnt++;
        }
        return cnt;
    }
    public void moveToPos(int pos) {
        moveToStart();
        int cnt = 0;
        while(cnt<pos){
            curr = curr.getNext();
            cnt++;
        }
    }

    public E getValue() {
        return curr.getElement();
    }
    public E Search(E item) {
        Node<E> temp = new Node<>(head);
        int cnt = -1;
        while(temp!=tail){
            temp = temp.getNext();
            cnt++;
            if(temp.getElement() == item){
                return item;
            }
        }
        return null;
    }

    public int searchPosition(E item) {
        Node<E> temp = new Node<>(head);
        int cnt = -1;
        while(temp!=tail){
            temp = temp.getNext();
            cnt++;
            if(temp.getElement() == item){
                return cnt;
            }
        }
        return cnt;
    }

}

