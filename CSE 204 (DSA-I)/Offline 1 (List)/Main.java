import ArrayList.Arr;
import LinkedList.LL;

import java.util.Scanner;

public class Main {
    public <E> void array_display(Arr<E> arr){
        int x = arr.currPos();
        arr.moveToStart();
        System.out.print("<");
        for(int i =0;i<arr.length();i++){
            E item = arr.getValue();
            if(i == x){
                System.out.print("| ");
            }
            if(i == arr.length()-1){
                System.out.print(item);
            }
            else{
                System.out.print(item + " ");
            }
            arr.next();
        }
        System.out.println(">");
        arr.moveToPos(x);
    }
    public <E> void LL_display(LL<E> linkedList){
        int position = linkedList.currPos();
        linkedList.moveToStart();
        System.out.print("<");
        for(int i =0;i< linkedList.length();i++){
            E item = linkedList.getValue();
            if(i == position){
                System.out.print("| ");
            }
            if(i == linkedList.length()-1){
                System.out.print(item);
            }
            else{
                System.out.print(item + " ");
            }
            linkedList.next();
        }
        System.out.println(">");
        linkedList.moveToPos(position);
    }
    public static void main(String[] args) {
        int k,x;
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        x = sc.nextInt();
        Integer[] array = new Integer[x];
        for(int i = 0;i<k;i++){
            array[i] = sc.nextInt();
        }
        Arr<Integer> arr = new Arr<>(array, x, k);
        Main main = new Main();
        //main.array_display(arr);
        /*while(true){
            int p, q;
            q = sc.nextInt();
            p = sc.nextInt();
            if(q==0){
                break;
            }
            if(q==1){
                arr.clear();
                main.array_display(arr);
                System.out.println("-1");
            }
            if(q==2){
                arr.insert(p);
                main.array_display(arr);
                System.out.println("-1");
            }
            if(q==3){
                arr.append(p);
                main.array_display(arr);
                System.out.println("-1");
            }
            if(q==4){
                Object obj = arr.remove();
                main.array_display(arr);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
            if(q==5){
                arr.moveToStart();
                main.array_display(arr);
                System.out.println("-1");
            }
            if(q==6){
                arr.moveToEnd();
                main.array_display(arr);
                System.out.println("-1");
            }
            if(q==7){
                arr.prev();
                main.array_display(arr);
                System.out.println("-1");
            }
            if(q==8){
                arr.next();
                main.array_display(arr);
                System.out.println("-1");
            }
            if(q==9){
                int len = arr.length();
                main.array_display(arr);
                System.out.println(len);
            }
            if(q==10){
                int pos = arr.currPos();
                main.array_display(arr);
                System.out.println(pos);
            }
            if(q==11){
                arr.moveToPos(p);
                main.array_display(arr);
                System.out.println("-1");
            }
            if(q==12){
                Object obj = arr.getValue();
                main.array_display(arr);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
            if(q==13){
                int pos = arr.Search(p);
                main.array_display(arr);
                System.out.println(pos);
            }
        }*/
        LL<Integer> LinkedList = new LL<>(array, k);
        main.LL_display(LinkedList);
        while(true){
            int p, q;
            q = sc.nextInt();
            p = sc.nextInt();
            if(q==0){
                break;
            }
            if(q==1){
                LinkedList.clear();
                main.LL_display(LinkedList);
                System.out.println("-1");
            }
            if(q==2){
                LinkedList.insert(p);
                main.LL_display(LinkedList);
                System.out.println("-1");
            }
            if(q==3){
                LinkedList.append(p);
                main.LL_display(LinkedList);
                System.out.println("-1");
            }
            if(q==4){
                Object obj = LinkedList.remove();
                main.LL_display(LinkedList);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
            if(q==5){
                LinkedList.moveToStart();
                main.LL_display(LinkedList);
                System.out.println("-1");
            }
            if(q==6){
                LinkedList.moveToEnd();
                main.LL_display(LinkedList);
                System.out.println("-1");
            }
            if(q==7){
                LinkedList.prev();
                main.LL_display(LinkedList);
                System.out.println("-1");
            }
            if(q==8){
                LinkedList.next();
                main.LL_display(LinkedList);
                System.out.println("-1");
            }
            if(q==9){
                int length = LinkedList.length();
                main.LL_display(LinkedList);
                System.out.println(length);
            }
            if(q==10){
                int pos = LinkedList.currPos();
                main.LL_display(LinkedList);
                System.out.println(pos);
            }
            if(q==11){
                LinkedList.moveToPos(p);
                main.LL_display(LinkedList);
                System.out.println("-1");
            }
            if(q==12){
                Object obj = LinkedList.getValue();
                main.LL_display(LinkedList);
                if(obj == null){
                    System.out.println("-1");
                }
                else{
                    System.out.println(obj);
                }
            }
            if(q==13){
                int pos = LinkedList.Search(p);
                main.LL_display(LinkedList);
                System.out.println(pos);
            }
        }
    }
}
