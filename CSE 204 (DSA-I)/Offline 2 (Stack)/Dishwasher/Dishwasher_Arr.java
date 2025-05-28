package Dishwasher;

import ArrayStack.Arr;
import java.util.Scanner;

public class Dishwasher_Arr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int direction = -1;
        int n,x;                                                //n = number of friends, x = number of meals
        n = sc.nextInt();
        x = sc.nextInt();
        Integer[] friendCount = new Integer[n];
        for(int i =0;i<n;i++){
            friendCount[i] = 0;
        }
        Integer[] timeToWash = new Integer[x];                  //Info of the time required to wash a dish of a meal
        for(int i =0;i<x;i++){
            timeToWash[i] = sc.nextInt();
        }
        Integer[] friend = new Integer[n*x];                    //Info of the friend who pushed the dish
        Integer[] pushTime = new Integer[n*x];                  //Info of the time when the dish has been pushed in the dirty stack
        Integer[] courseNum = new Integer[n*x];                 //Info of the meal course
        Integer[] endTime = new Integer[n*x];                    //Info for when the dish has been pushed in the clean stack

        Arr<Integer> friendStack = new Arr<>(friend, direction);
        Arr<Integer> pushTimeStack = new Arr<>(pushTime, direction);           //Dirty Stack
        Arr<Integer> courseNumStack = new Arr<>(courseNum, direction);
        Arr<Integer> endTimeStack = new Arr<>(endTime, direction);             //Clean Stack
        int len = friendStack.length();
        int cleanStackPushTime = 0;
        Integer[] tempTime = new Integer[n*x];
        Integer[] tempCourse = new Integer[n*x];
        Arr<Integer> tempTimeStack = new Arr<>(tempTime, direction);
        Arr<Integer> tempCourseStack = new Arr<>(tempCourse, direction);
        int w =0;
        while(true){
            int k, t, s;
            k = sc.nextInt();
            t = sc.nextInt();
            s = sc.nextInt();
            if(k!=0 || t!=0 || s!=0){
                friendStack.push(k);
                pushTimeStack.push(t);
                courseNumStack.push(s);
            }
            if(k == 0){
                break;
            }
            else{
                Integer m = friendStack.pop();
                friendCount[m-1]++;
                Integer course = courseNumStack.topValue();
                Integer time = timeToWash[course-1];
                Integer topTime = pushTimeStack.topValue();
                if(topTime == 0){
                    cleanStackPushTime = time-1;
                    endTimeStack.push(cleanStackPushTime);
                    pushTimeStack.pop();
                    courseNumStack.pop();
                }
                else if(topTime == cleanStackPushTime){
                    cleanStackPushTime += time;
                    endTimeStack.push(cleanStackPushTime);
                    pushTimeStack.pop();
                    courseNumStack.pop();
                    if(tempTimeStack.topValue()!=null){
                        Integer topValue = tempCourseStack.pop();
                        time = timeToWash[topValue-1];
                        endTimeStack.push(cleanStackPushTime+time);
                        tempTimeStack.pop();
                    }
                }
                else if(topTime > cleanStackPushTime){
                    cleanStackPushTime = topTime+time-1;
                    endTimeStack.push(cleanStackPushTime);
                    pushTimeStack.pop();
                    courseNumStack.pop();
                    if(tempTimeStack.topValue()!=null){
                        Integer topValue = tempCourseStack.pop();
                        time = timeToWash[topValue-1];
                        endTimeStack.push(cleanStackPushTime+time);
                        tempTimeStack.pop();
                    }
                }
                else {
                    tempTimeStack.push(pushTimeStack.pop());
                    tempCourseStack.push(courseNumStack.pop());
                }
            }
        }
        System.out.println(endTimeStack.topValue());
        Integer[] outputArray = new Integer[n*x];
        for(int i =(n*x)-1;i>=0;i--){
            if(endTimeStack.topValue()!=null){
                outputArray[i] = endTimeStack.pop();
            }
        }
        for (Integer value : outputArray) {
            if (value != null) {
                System.out.print(value + " ");
            }
        }
        System.out.println();
        boolean friendFlag = true;
        for (Integer integer : friendCount) {
            if (integer != x) {
                friendFlag = false;
                break;
            }
        }
        if(friendFlag){
            System.out.println("Y");
        }
        else{
            System.out.println("N");
        }
        for(int i =0;i<friendCount.length;i++){
            if(friendCount[i] == x && i == friendCount.length-1){
                System.out.print(i+1);
            }
            else if(friendCount[i] == x){
                System.out.print(i+1 + ",");
            }
        }
    }
}
