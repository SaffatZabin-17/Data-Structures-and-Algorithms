import ArrayStack.Arr;
import LinkedStack.LL;

import java.util.Scanner;

public class Main{
    public <E> void array_display(Arr<E> arr){
        int length = arr.length();
        E[] items = (E[]) new Object[arr.length()];
        System.out.print("<");
        for(int i =0;i<length;i++){
            E item = arr.pop();
            items[i] = item;
        }
        for(int i = items.length-1;i>=0;i--){
            if(i==0){
                System.out.print(items[i]);
            }
            else{
                System.out.print(items[i] + " ");
            }
        }
        System.out.println(">");
        for(int i =length-1;i>=0;i--){
            arr.push(items[i]);
        }
    }
    public <E> void LL_display(LL<E> linkedList){
        int length = linkedList.length();
        E[] items = (E[]) new Object[linkedList.length()];
        System.out.print("<");
        for(int i = 0;i< length;i++){
            E item = linkedList.pop();
            items[i] = item;
        }
        for(int i = items.length-1; i>=0;i--){
            if(i==0){
                System.out.print(items[i]);
            }
            else{
                System.out.print(items[i] + " ");
            }
        }
        System.out.println(">");
        for(int i =length-1;i>=0;i--){
            linkedList.push(items[i]);
        }
    }
    public static void main(String[] args) {
        int k,x;
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        Integer[] array = new Integer[k];
        for(int i =0;i<k;i++){
            array[i] = sc.nextInt();
        }
        Main main = new Main();
        /*Arr<Integer> arrayStack = new Arr<>(array, -1);
        main.array_display(arrayStack);
        while(true){
            int q,p;
            q = sc.nextInt();
            p = sc.nextInt();
            if(q==0){
                break;
            }
            if(q==1){
                arrayStack.clear();
                main.array_display(arrayStack);
                System.out.println("-1");
            }
            if(q==2){
                arrayStack.push(p);
                main.array_display(arrayStack);
                System.out.println("-1");
            }
            if(q==3){
                Object obj = arrayStack.pop();
                main.array_display(arrayStack);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
            if(q==4){
                main.array_display(arrayStack);
                System.out.println(arrayStack.length());
            }
            if(q==5){
                Object obj = arrayStack.topValue();
                main.array_display(arrayStack);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
            if(q==6){
                arrayStack.setDirection(p);
                main.array_display(arrayStack);
                System.out.println("-1");
            }
        }*/
        LL<Integer> linkedListStack = new LL<>(array);
        main.LL_display(linkedListStack);
        while(true){
            int q,p;
            q = sc.nextInt();
            p = sc.nextInt();
            if(q==0){
                break;
            }
            if(q==1){
                linkedListStack.clear();
                main.LL_display(linkedListStack);
                System.out.println("-1");
            }
            if(q==2){
                linkedListStack.push(p);
                main.LL_display(linkedListStack);
                System.out.println("-1");
            }
            if(q==3){
                Object obj = linkedListStack.pop();
                main.LL_display(linkedListStack);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
            if(q==4){
                int len = linkedListStack.length();
                main.LL_display(linkedListStack);
                System.out.println(len);
            }
            if(q==5){
                Object obj = linkedListStack.topValue();
                main.LL_display(linkedListStack);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
        }
    }
}