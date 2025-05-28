import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

class Pair{
    String key;
    int value;

    Pair(){
        this.key = "";
        this.value = 0;
    }
    Pair(String key, int value){
        this.key = key;
        this.value = value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public void setKey(String key){
        this.key = key;
    }

    public String getKey(){
        return this.key;
    }
}

public class HashTable {
    int tableSize, hashingMethodNo, collisionResolvingNo, probeCount;
    Pair[] integerArray;
    LinkedList<Pair>[] linkedListArray;

    HashTable(int tableSize){
        this.tableSize = tableSize;
    }

    HashTable(int tableSize, int collisionResolvingNo){
        this.tableSize = tableSize;
        integerArray = new Pair[tableSize];
        linkedListArray = new LinkedList[tableSize];
        this.collisionResolvingNo = collisionResolvingNo;
    }
    HashTable(int tableSize, int hashingMethodNo, int collisionResolvingNo){
        this.tableSize = tableSize;
        integerArray = new Pair[tableSize];
        linkedListArray = new LinkedList[tableSize];
        this.hashingMethodNo = hashingMethodNo;
        this.collisionResolvingNo = collisionResolvingNo;
    }

    public int getProbeCount(){
        return this.probeCount;
    }

    private int hashFunction1(String key){
        int g = 31;
        long hash = 0;
        for(int i=0;i<key.length();i++){
            hash = g*hash + key.charAt(i);
        }
        return (int) Math.abs(hash%150);
    }

    private int hashFunction2(String key){
        long sum = 0, multiplicand = 1;
        for (int i = 0; i < key.length(); i++) {
            if(i%4==0){
                multiplicand = 1;
            }
            else{
                multiplicand = multiplicand*256;
            }
            sum += key.charAt(i) * multiplicand;
        }
        return (int)(Math.abs(sum) % 150);
    }

    public void insert(String key, int value){
        int hashIndex;
        hashIndex = hashFunction1(key) % tableSize;
        //System.out.println(hashIndex);
        if(collisionResolvingNo == 1){
            //Separate Chaining
            if(linkedListArray[hashIndex] == null){
                linkedListArray[hashIndex] = new LinkedList<>();
            }
            linkedListArray[hashIndex].insert(new Pair(key, value));
        }
        else if(collisionResolvingNo == 2){
            //Linear Probing
            if(integerArray[hashIndex] == null){
                integerArray[hashIndex] = new Pair(key, value);
            }
            else{
                int newIndex = 0;
                for(int i=0;i<tableSize;i++){
                    probeCount++;
                    newIndex = (hashIndex + i) % tableSize;
                    if(integerArray[newIndex] == null){
                        integerArray[newIndex] = new Pair(key, value);
                        break;
                    }
                }
                probeCount--;
            }
        }
        else if(collisionResolvingNo == 3){
            //Quadratic Probing
            if(integerArray[hashIndex] == null){
                integerArray[hashIndex] = new Pair(key, value);
            }
            else{
                int newIndex = 0;
                probeCount = 0;
                int c1 = 12;
                int c2 = 20;
                for(int i=0;i<tableSize;i++){
                    probeCount++;
                    newIndex = (hashIndex + c1 * i + c2 * i * i) % tableSize;
                    if(integerArray[newIndex] == null){
                        integerArray[newIndex] = new Pair(key, value);
                        break;
                    }
                }
                probeCount--;
            }
        }
        else{
            //Double Hashing
            int newIndex = 0;
            int hashIndex2 = hashFunction2(key) % tableSize;
            probeCount = 0;
            for(int i=0;i<tableSize;i++){
                probeCount++;
                newIndex = (hashIndex + i* hashIndex2) % tableSize;
                if(integerArray[newIndex] == null){
                    integerArray[newIndex] = new Pair(key, value);
                    break;
                }
            }
            probeCount--;
        }
    }

    public int search(String key){
        int hashIndex;
        hashIndex = hashFunction1(key) % tableSize;
        if(collisionResolvingNo == 1){
            int returnValue = -1;
            LinkedList<Pair> list = linkedListArray[hashIndex];
            int length = list.length();
            Pair pair = new Pair();
            list.moveToPos(0);
            for(int i =0;i<length;i++){
                pair = list.getValue();
                if(Objects.equals(pair.key, key)){
                    returnValue = pair.value;
                    break;
                }
                list.next();
            }
            if(returnValue == -1){
                return 0;
            }
            else{
                return returnValue;
            }
        }
        else if(collisionResolvingNo == 2){
            if(integerArray[hashIndex] == null){
                return -1;
            }
            if(Objects.equals(integerArray[hashIndex].key, key)){
                return integerArray[hashIndex].value;
            }
            else{
                int newIndex = 0;
                probeCount = 0;
                int returnValue = 0;
                for(int i=0;i<tableSize;i++){
                    newIndex = (hashIndex+i) % tableSize;
                    if(integerArray[newIndex]!= null){
                        if(Objects.equals(integerArray[newIndex].key, key)){
                            returnValue = integerArray[newIndex].value;
                            break;
                        }
                        probeCount++;
                    }
                }
                return returnValue;
            }
        }
        else if(collisionResolvingNo == 3){
            if(integerArray[hashIndex] == null){
                return -1;
            }
            if(Objects.equals(integerArray[hashIndex].key, key)){
                return integerArray[hashIndex].value;
            }
            else{
                int returnValue = 0;
                int newIndex = 0;
                probeCount = 0;
                int c1 = 12;
                int c2 = 20;
                for(int i=0;i<tableSize;i++){
                    newIndex = (newIndex + c1 * i + c2 * i * i) % tableSize;
                    if(integerArray[newIndex]!=null){
                        if(Objects.equals(integerArray[newIndex].key, key)){
                            returnValue = integerArray[newIndex].value;
                            break;
                        }
                        probeCount++;
                    }
                }
                return returnValue;
            }
        }
        else{
            if(integerArray[hashIndex] == null){
                return -1;
            }
            if(Objects.equals(integerArray[hashIndex].key, key)){
                return integerArray[hashIndex].value;
            }
            else{
                int returnValue = 0;
                int newIndex = 0;
                probeCount = 0;
                int hashIndex2 = hashFunction2(key);
                for(int i=0;i<tableSize;i++){
                    newIndex = (hashIndex + i * hashIndex2) % tableSize;
                    if(integerArray[newIndex] !=null){
                        if(Objects.equals(integerArray[newIndex].key, key)){
                            returnValue = integerArray[newIndex].value;
                            break;
                        }
                        probeCount++;
                    }
                }
                return returnValue;
            }
        }
    }

    public void delete(String key){
        int hashIndex;
        hashIndex = hashFunction1(key) % tableSize;
        if(collisionResolvingNo == 1){
            LinkedList<Pair> list = linkedListArray[hashIndex];
            int length = list.length();
            Pair pair = new Pair();
            list.moveToPos(0);
            for(int i=0;i<length;i++){
                pair = list.getValue();
                if(Objects.equals(pair.key, key)){
                    list.remove();
                    break;
                }
                list.next();
            }
        }
        else if(collisionResolvingNo == 2){
            int newIndex = 0;
            if(integerArray[hashIndex] == null){
                return;
            }
            if(Objects.equals(integerArray[hashIndex].key, key)){
                integerArray[hashIndex] = null;
            }
            else{
                probeCount = 0;
                for(int i=0;i<tableSize;i++){
                    probeCount++;
                    newIndex = (hashIndex + i) % tableSize;
                    if(integerArray[newIndex] != null){
                        if(Objects.equals(integerArray[newIndex].key, key)){
                            integerArray[newIndex] = null;
                            break;
                        }
                    }
                }
                probeCount--;
            }
        }
        else if(collisionResolvingNo == 3){
            if(integerArray[hashIndex]==null){
                return;
            }
            if(Objects.equals(integerArray[hashIndex].key, key)){
                integerArray[hashIndex] = null;
            }
            else{
                probeCount = 0;
                int newIndex = 0;
                int c1= 12, c2 = 20;
                for(int i=0;i<tableSize;i++){
                    probeCount++;
                    newIndex = (hashIndex + c1*i + c2*i*i) % tableSize;
                    if(integerArray[newIndex]!=null){
                        if(Objects.equals(integerArray[newIndex].key, key)){
                            integerArray[newIndex] = null;
                            break;
                        }
                    }
                }
                probeCount--;
            }
        }
        else{
            if(integerArray[hashIndex] == null){
                return;
            }
            if(Objects.equals(integerArray[hashIndex].key, key)){
                integerArray[hashIndex] = null;
            }
            else{
                probeCount = 0;
                int newIndex = 0;
                int hashIndex2 = hashFunction2(key);
                for(int i=0;i<tableSize;i++){
                    probeCount++;
                    newIndex = (hashIndex + i*hashIndex2) % tableSize;
                    if(integerArray[newIndex]!=null){
                        if(Objects.equals(integerArray[newIndex].key, key)){
                            integerArray[newIndex] = null;
                            break;
                        }
                    }
                }
                probeCount--;
            }
        }
    }

    public void clear(){
        Arrays.fill(integerArray, null);
        Arrays.fill(linkedListArray, null);
    }

    public void output(){
        for(int i =0;i<tableSize;i++){
            System.out.println(Arrays.toString(linkedListArray));
        }
    }
}
