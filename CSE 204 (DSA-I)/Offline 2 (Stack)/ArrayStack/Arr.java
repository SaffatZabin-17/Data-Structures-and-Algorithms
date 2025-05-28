package ArrayStack;

public class Arr<E> implements Stack<E> {
    private int direction, length, top, arraySize;
    private E[] stackArray;

    Arr(){
        this.top = -1;
        this.arraySize = 10;
        this.stackArray = (E[]) new Object[arraySize];
    }

    Arr(int size){
        this.top = -1;
        this.arraySize = size;
        this.stackArray = (E[]) new Object[size];
    }

    public Arr(E[] array, int direction){
        this.stackArray = (E[]) new Object[array.length];
        this.direction = direction;
        this.arraySize = this.length = array.length;
        if(this.direction == -1){
            for(int i =stackArray.length-1, j = 0;j<array.length;i--, j++){
                stackArray[i] = array[j];
            }
            this.top = 0;
        }
        else{
            for(int i =0;i<array.length;i++){
                stackArray[i] = array[i];
            }
            this.top = length-1;
        }
    }

    public void clear(){
        if(direction == -1){
            top = arraySize;
        }
        else{
            top = -1;
        }
        this.length = 0;
    }

    public void push(E item){
        if(direction == -1){
            if(length>=arraySize){
                int i = this.arraySize-1;
                int len = length;
                arraySize = arraySize*2;
                int j = arraySize-1;
                Arr<E> temp = new Arr<>(arraySize);
                while(len>0){
                    temp.stackArray[j] = this.stackArray[i];
                    len--;
                    i--;
                    j--;
                }
                this.stackArray = temp.stackArray;
                this.top = arraySize/2;
            }
            length++;
            stackArray[--top] = item;
        }
        else{
            if(length>=arraySize){
                arraySize*=2;
                Arr<E> temp = new Arr<>(arraySize);
                for(int i =0;i<length;i++){
                    temp.stackArray[i] = this.stackArray[i];
                }
                this.stackArray = temp.stackArray;
            }
            stackArray[++top] = item;
            length++;
        }
    }

    public E pop(){
        if(length>0){
            if(direction == -1){
                E item = stackArray[top++];
                length--;
                return item;
            }
            else{
                E item = stackArray[top--];
                length--;
                return item;
            }
        }
        else {
            return null;
        }
    }

    public int length(){
        return length;
    }

    public E topValue(){
        if(length>0){
            if(direction == -1){
                return stackArray[arraySize-length];
            }
            else{
                return stackArray[top];
            }
        }
        else{
            return null;
        }
    }

    public void setDirection(int direction){
        if(length == 0){
            this.direction = direction;
            if(direction == -1){
                top = arraySize-1;
            }
            else{
                top = 0;
            }
        }
    }
}