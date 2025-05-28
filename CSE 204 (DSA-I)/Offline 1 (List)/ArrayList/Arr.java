package ArrayList;

public class Arr<E> implements List<E> {
    private E[] array;                      //The array
    private int currPos = 0;               //Current position of the cursor
    private int memory_size = 0;           //Memory allocation
    private int listSize;                  //Array's size
    private int array_length;
    public Arr(){
        this.array = (E[]) new Object[array.length];
        this.currPos = 0;
        this.memory_size = 0;
        this.listSize = 0;
        this.array_length = 0;
    }
    public Arr(E[] array, int array_length, int listSize){
        this.listSize = listSize;
        this.array = array;
        this.currPos = 0;
        this.memory_size = array_length;
        this.array_length = array_length;
    }
    private void set(int i, E item){
        array[i] = item;
    }
    private void setMemory_size(int x){
        this.memory_size = x;
    }
    public void clear(){
        E[] temp_array = (E[]) new Object[array.length];
        Arr<E> arr_exp = new Arr<>(temp_array, array_length, listSize);//Create a new array with no elements and then pass the reference to your original array to avoid NPE
        this.array = arr_exp.array;
        this.listSize = 0;
        this.currPos = 0;
    }
    public void insert(E item){
        if(listSize>=array_length){
            array_length+= memory_size;
            Arr<E> arr1 = new Arr<>();
            arr1.array = (E[]) new Object[array_length];
            for(int i =0;i<array.length;i++){
                arr1.array[i] = this.array[i];                   //Copy array elements to the new array
            }
            this.array = arr1.array;                    //Pass the reference of the new array to the old one
        }
        for(int i = listSize; i>currPos;i--){
            array[i] = array[i-1];               //Shift the old array
        }
        array[currPos] = item;             //Insert the item
        listSize++;
    }
    public void append(E item){
        if(listSize>=array_length){
            array_length+= memory_size;
            Arr<E> arr1 = new Arr<>();
            arr1.array = (E[]) new Object[array_length];
            for(int i =0;i<array.length;i++){
                arr1.array[i] = this.array[i];
            }
            this.array = arr1.array;
        }
        array[listSize] = item;
        listSize++;
    }
    public E remove(){
        E x = array[currPos];
        for(int i = currPos;i< array.length-1;i++){
            array[i] = array[i+1];
        }
        listSize--;
        if(listSize != -1){
            return x;
        }
        else{
            return null;
        }
    }
    public void moveToStart(){
        this.currPos = 0;
    }
    public void moveToEnd(){
        this.currPos = listSize-1;
    }
    public void prev(){
        if(currPos == 0){
            return;
        }
        this.currPos = currPos-1;
    }
    public void next(){
        if(currPos == listSize){
            return;
        }
        this.currPos = currPos+1;
    }
    public int length(){
        return listSize;
    }
    public int currPos(){
        return currPos;
    }
    public void moveToPos(int pos){
        this.currPos = pos;
    }
    public E getValue(){
        return array[currPos];
    }
    public int Search(E item){
        for(int i =0;i< array.length;i++){
            if(array[i]== item){
                return i;
            }
        }
        return -1;
    }
}
