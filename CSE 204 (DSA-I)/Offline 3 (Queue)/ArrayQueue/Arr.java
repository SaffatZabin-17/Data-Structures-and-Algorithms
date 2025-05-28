package ArrayQueue;

public class Arr<E> implements Queue<E> {
    private int length, maxSize, frontPosition, rearPosition;
    private E[] queueArray;

    public Arr(){
        this.maxSize = 10;
        this.queueArray = (E[]) new Object[maxSize];
        this.rearPosition = 0;
        this.frontPosition = 1;
    }

    public Arr(int maxSize){
        this.maxSize = maxSize;
        this.queueArray = (E[]) new Object[maxSize+1];
        this.rearPosition = 0;
        this.frontPosition = 1;

    }

    public Arr(E[] array){
        this.maxSize = array.length+1;
        this.queueArray = (E[]) new Object[this.maxSize];
        int flag = 0;
        this.frontPosition = 0;
        for(int i =0; i< array.length; i++){
            this.queueArray[flag%maxSize] = array[i];
            flag++;
        }
        this.length = array.length;
        this.rearPosition = (flag-1)%maxSize;
    }

    public void clear(){
        rearPosition = 0;
        frontPosition = 1;
        length = 0;
    }

    public void enqueue(E item){
        if(rearPosition==maxSize-1){
            maxSize = (maxSize-1)*2;
            E[] tempArray = (E[]) new Object[maxSize+1];
            for(int i = 0, j = frontPosition; j<=rearPosition;i++, j++){
                tempArray[i] = this.queueArray[j];
            }
            this.queueArray = tempArray;
            this.frontPosition = 0;
            this.rearPosition = (length-1)%(maxSize+1);
        }
        rearPosition = (rearPosition+1)%maxSize;
        queueArray[rearPosition] = item;
        length++;
    }

    public E dequeue(){
        if(length>0){
            E item = queueArray[frontPosition];
            frontPosition = (frontPosition+1)%maxSize;
            length--;
            return item;
        }
        else{
            return null;
        }
    }

    public int length(){
        return this.length;
    }

    public E frontValue(){
        if(length > 0){
            return queueArray[frontPosition];
        }
        return null;
    }

    public E rearValue(){
        if(length > 0){
            return queueArray[rearPosition];
        }
        return null;
    }

    public E leaveQueue(){
        if(length > 0){
            E item = queueArray[rearPosition];
            rearPosition = (rearPosition-1)%maxSize;
            length--;
            return item;
        }
        return null;
    }
}