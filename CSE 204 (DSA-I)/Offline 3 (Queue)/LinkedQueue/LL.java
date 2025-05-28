package LinkedQueue;

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
    Node<E> getNext(){
        return this.next;
    }
    void setNext(Node<E> nextVal){
        this.next = nextVal;
    }
    E getElement(){
        return element;
    }
    void setElement(E item){
        this.element = item;
    }
}

public class LL<E> implements Queue<E> {
    Node<E> head, tail, curr;
    int length;

    public LL(){
        Node<E> temp = new Node<>(null, null);
        head = new Node<>(null, temp);
        curr = head;
        tail = head;
        length = 0;
    }
    public LL(E[] array){
        this.length = array.length;
        for(int i =0;i< array.length;i++){
            Node<E> temp = new Node<>();
            temp.setElement(array[i]);
            temp.setNext(temp.getNext());
            if(head == null){
                head = new Node<>(null, temp);
                curr = head;
            }
            else{
                curr = curr.getNext();
                curr.setNext(temp);
            }
            if(i == array.length-1){
                tail = curr.getNext();
                tail.setNext(null);
                curr = head;
            }
        }
    }

    public void clear(){
        head.setNext(null);
        curr = head;
        tail = head;
        length = 0;
    }

    public void enqueue(E item){
        Node<E> temp = new Node<>();
        if(length == 0){
            head.setNext(temp);
        }
        tail.setNext(temp);
        temp.setElement(item);
        temp.setNext(null);
        tail = temp;
        length++;
    }

    public E dequeue(){
        if(length>0){
            E item = head.getNext().getElement();
            head.setNext(head.getNext().getNext());
            length--;
            return item;
        }
        return null;
    }

    public int length(){
        return length;
    }

    public E frontValue(){
        return head.getNext().getElement();
    }

    public E rearValue(){
        return tail.getElement();
    }

    public E leaveQueue(){
        if(length > 0){
            E item = tail.getElement();
            curr = head;
            while(curr.getNext()!=tail){
                curr = curr.getNext();
            }
            tail = curr;
            int x = length;
            Node<E> temp = head;
            while(x!=length-2){
                temp = temp.getNext();
                x--;
            }
            curr = temp;
            tail.setNext(null);
            length--;
            return item;
        }
        return null;
    }
}