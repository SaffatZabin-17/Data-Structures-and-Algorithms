import ArrayList.Arr;
import LinkedList.LL;
import java.util.LinkedList;
import java.util.Scanner;

public class TNL {
    public static void main(String[] args) {
        int k, l, m;
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        l = sc.nextInt();
        Integer[] busStop = new Integer[l];
        for(int i =0;i<l;i++){
            busStop[i] = sc.nextInt();
        }
        m = sc.nextInt();
        Integer[] trainStop = new Integer[m];
        for(int i =0;i<m;i++){
            trainStop[i] = sc.nextInt();
        }
        int task = sc.nextInt();
        if(task ==1){
            /*Arr<Integer> busStopArray = new Arr<>(busStop, k, l);
            Arr<Integer> trainStopArray = new Arr<>(trainStop, k, m);*/
            LL<Integer> busStopArray = new LL<>(busStop, l);
            LL<Integer> trainStopArray = new LL<>(trainStop, m);
            for(int i =0;i<k;i++){
                if(i == k-1){
                    System.out.print(i);
                }
                else{
                    System.out.print(i + ",");
                }
            }
            System.out.println();
            for(int i =0;i<k;i++){
                if(busStopArray.getValue()==i){
                    if(busStopArray.getValue() == k-1){
                        System.out.print(busStopArray.getValue());
                    }
                    else{
                        System.out.print(busStopArray.getValue() + ",");
                    }
                    busStopArray.next();
                }
                else{
                    System.out.print(",");
                }
            }
            System.out.println();
            for(int i =0;i<k;i++){
                if(trainStopArray.getValue()==i){
                    if(trainStopArray.getValue() == k-1){
                        System.out.print(trainStopArray.getValue());
                    }
                    else{
                        System.out.print(trainStopArray.getValue() + ",");
                    }
                    trainStopArray.next();
                }
                else{
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }
}
