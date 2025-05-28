package Dishwasher;

import java.util.Scanner;
import ArrayStack.Arr;

class Info{
    Integer friend = 0;
    Integer pushTime = 0;
    Integer courseNum = 0;
    Integer cleanStackPushTime = 0;

    public Info(Integer friend, Integer pushTime, Integer courseNum){
        this.friend = friend;
        this.pushTime = pushTime;
        this.courseNum = courseNum;
    }
    public Info(Integer cleanStackPushTime){
        this.cleanStackPushTime = cleanStackPushTime;
    }

    public void setFriend(Integer friend){
        this.friend = friend;
    }
    public Integer getFriend(){
        return this.friend;
    }

    public void setPushTime(Integer pushTime){
        this.pushTime = pushTime;
    }
    public Integer getPushTime(){
        return this.pushTime;
    }

    public void setCourseNum(Integer courseNum){
        this.courseNum = courseNum;
    }
    public Integer getCourseNum(){
        return this.courseNum;
    }

    public void setCleanStackPushTime(Integer cleanStackPushTime){
        this.cleanStackPushTime = cleanStackPushTime;
    }
    public Integer getCleanStackPushTime(){
        return this.cleanStackPushTime;
    }

    @Override
    public String toString(){
        return cleanStackPushTime + " ";
    }
}
public class OneArrayTwoStack {
    public static void main(String[] args) {
        int n,x;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        x = sc.nextInt();
        Integer[] timeToWash = new Integer[x];
        Integer[] friendCount = new Integer[n];
        for(int i =0;i<n;i++){
            friendCount[i] = 0;
        }
        for(int i =0;i<x;i++){
            timeToWash[i] = sc.nextInt();
        }
        int cleanStackPushTime = 0;
        int size = (n*x)+1;
        Info[] info = new Info[size];
        Arr<Info> dirtyStackInfo = new Arr<>(info, 1);
        Arr<Info> cleanStackInfo = new Arr<>(info, -1);
        int iterator =0;
        Integer[] tempTime = new Integer[n*x];
        Integer[] tempCourse = new Integer[n*x];
        Arr<Integer> tempTimeStack = new Arr<>(tempTime, 1);
        Arr<Integer> tempCourseStack = new Arr<>(tempCourse, 1);
        int count = 0;
        while(true){
            int k,t,s;
            k = sc.nextInt();
            t = sc.nextInt();
            s = sc.nextInt();
            if(k==0){
                break;
            }
            info[iterator] = new Info(k,t,s);
            dirtyStackInfo.push(info[iterator]);
            iterator++;
            count++;

            Info temp = dirtyStackInfo.pop();
            Info cleanStack;
            Integer m = temp.getFriend();
            friendCount[m-1]++;
            Integer course = temp.getCourseNum();
            Integer time = timeToWash[course-1];
            Integer topTime = temp.getPushTime();
            if(topTime==0){
                cleanStackPushTime = time-1;
                cleanStack = new Info(cleanStackPushTime);
                cleanStackInfo.push(cleanStack);
            }
            else if(topTime == cleanStackPushTime){
                cleanStackPushTime+=time;
                cleanStack = new Info(cleanStackPushTime);
                cleanStackInfo.push(cleanStack);
                if(tempTimeStack.topValue()!=null){
                    Integer topValue = tempCourseStack.pop();
                    time = timeToWash[topValue-1];
                    cleanStack = new Info(cleanStackPushTime+time);
                    cleanStackInfo.push(cleanStack);
                    tempTimeStack.pop();
                }
            }
            else if(topTime> cleanStackPushTime){
                cleanStackPushTime = topTime+time-1;
                cleanStack = new Info(cleanStackPushTime);
                cleanStackInfo.push(cleanStack);
                if(tempTimeStack.topValue()!=null){
                    Integer topValue = tempCourseStack.pop();
                    time = timeToWash[topValue-1];
                    cleanStack = new Info(cleanStackPushTime+time);
                    cleanStackInfo.push(cleanStack);
                    tempTimeStack.pop();
                }
            }
            else{
                tempTimeStack.push(temp.getPushTime());
                tempCourseStack.push(temp.getCourseNum());
            }
        }
        System.out.println(cleanStackInfo.topValue().getCleanStackPushTime());
        Integer[] outputArray = new Integer[count];
        for(int i = count-1;i>=0;i--){
            outputArray[i] = cleanStackInfo.pop().cleanStackPushTime;
        }
        for(Integer value : outputArray){
            if(value!=null){
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
