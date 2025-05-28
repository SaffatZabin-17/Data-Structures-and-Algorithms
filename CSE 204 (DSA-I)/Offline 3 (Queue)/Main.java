import ArrayQueue.Arr;
import LinkedQueue.LL;

import java.util.Scanner;

public class Main{
    public <E> void array_display(Arr<E> arrayQueue){
        System.out.print("<");
        for(int i =0;i< arrayQueue.length();i++){
            E item = arrayQueue.dequeue();
            System.out.print(item + " ");
            arrayQueue.enqueue(item);
        }
        System.out.println(">");
    }

    public <E> void LL_display(LL<E> linkedQueue){
        System.out.print("<");
        for(int i =0;i< linkedQueue.length();i++){
            E item = linkedQueue.dequeue();
            if(i == linkedQueue.length()){
                System.out.print(item);
            }
            else{
                System.out.print(item + " ");
            }
            linkedQueue.enqueue(item);
        }
        System.out.println(">");
    }

    public static void main(String[] args) {
        int k;
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        Integer[] array = new Integer[k];
        for(int i =0;i<k;i++){
            array[i] = sc.nextInt();
        }
        Main main = new Main();
        Arr<Integer> arrayQueue = new Arr<>(array);
        main.array_display(arrayQueue);
        while(true){
            int q,p;
            q = sc.nextInt();
            p = sc.nextInt();
            if(q==0){
                break;
            }
            if(q==1){
                arrayQueue.clear();
                main.array_display(arrayQueue);
                System.out.println("-1");
            }
            if(q==2){
                arrayQueue.enqueue(p);
                main.array_display(arrayQueue);
                System.out.println("-1");
            }
            if(q==3){
                Object obj = arrayQueue.dequeue();
                main.array_display(arrayQueue);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
            if(q==4){
                int len = arrayQueue.length();
                main.array_display(arrayQueue);
                System.out.println(len);
            }
            if(q==5){
                Object obj = arrayQueue.frontValue();
                main.array_display(arrayQueue);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
            if(q==6){
                Object obj = arrayQueue.rearValue();
                main.array_display(arrayQueue);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
            if(q == 7){
                Object obj = arrayQueue.leaveQueue();
                main.array_display(arrayQueue);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
        }
        /*LL<Integer> linkedQueue = new LL<>(array);
        main.LL_display(linkedQueue);
        while(true){
            int q,p;
            q = sc.nextInt();
            p = sc.nextInt();
            if(q==0){
                break;
            }
            if(q==1){
                linkedQueue.clear();
                main.LL_display(linkedQueue);
                System.out.println("-1");
            }
            if(q==2){
                linkedQueue.enqueue(p);
                main.LL_display(linkedQueue);
                System.out.println("-1");
            }
            if(q==3){
                Object obj = linkedQueue.dequeue();
                main.LL_display(linkedQueue);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
            if(q==4){
                int len = linkedQueue.length();
                main.LL_display(linkedQueue);
                System.out.println(len);
            }
            if(q==5){
                Object obj = linkedQueue.frontValue();
                main.LL_display(linkedQueue);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
            if(q==6){
                Object obj = linkedQueue.rearValue();
                main.LL_display(linkedQueue);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
            if(q==7){
                Object obj = linkedQueue.leaveQueue();
                main.LL_display(linkedQueue);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
        }*/
    }
}