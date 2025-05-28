package LinkedStack;

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
    void setNext(Node<E> nextVal){
        this.next = nextVal;
    }
    E getElement(){
        return element;
    }
    E setElement(E item){
        return this.element = item;
    }
}

public class LL<E> implements List<E>{
    private Node<E> top;
    private Node<E> curr;
    private int length;

    public LL(){
        this.top = null;
        this.length = 0;
    }
    public LL(E[] array){
        for(int i = array.length-1;i>=0;i--){
            Node<E> temp = new Node<>();
            temp.setElement(array[i]);
            temp.setNext(temp.getNext());
            if(top == null){
                top = temp;
                curr = temp;
            }
            else{
                curr.setNext(temp);
                curr = curr.getNext();
            }
            length++;
        }
        curr = top;
    }

    public void clear(){
        this.top = null;
        this.curr = null;
        this.length = 0;
    }

    public void push(E item){
        Node<E> temp = new Node<>();
        temp.setElement(item);
        temp.setNext(top);
        top = temp;
        curr = temp;
        length++;
    }

    public E pop(){
        E item = null;
        if(length > 0){
            item = top.getElement();
            top = top.getNext();
            curr = top;
            length--;
        }
        return item;
    }

    public int length(){
        return this.length;
    }

    public E topValue(){
        if(length > 0){
            return top.getElement();
        }
        return null;
    }
}