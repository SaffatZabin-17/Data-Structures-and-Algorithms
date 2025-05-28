import ArrayQueue.Arr;
import LinkedQueue.LL;

import java.util.Scanner;

class clientInfo{
    Integer enterTime, serviceTime;
    clientInfo(Integer enterTime, Integer serviceTime){
        this.enterTime = enterTime;
        this.serviceTime = serviceTime;
    }

    public void setEnterTime(Integer enterTime){
        this.enterTime = enterTime;
    }
    public Integer getEnterTime(){
        return this.enterTime;
    }

    public void setServiceTime(Integer serviceTime){
        this.serviceTime = serviceTime;
    }
    public Integer getServiceTime(){
        return this.serviceTime;
    }
    @Override
    public String toString(){
        return enterTime + " " + serviceTime;
    }
}

public class BankQueue {
    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        Arr<clientInfo> clientStack1 = new Arr<>();
        Arr<clientInfo> clientStack2 = new Arr<>();
        //LL<clientInfo> clientStack1= new LL<>();
        //LL<clientInfo>clientStack2 = new LL<>();
        Integer booth1FinishTime = 0, booth2FinishTime = 0;
        for(int i =0;i<n;i++){
            int enterTime, serviceTime;
            enterTime = sc.nextInt();
            serviceTime = sc.nextInt();
            clientInfo object = new clientInfo(enterTime, serviceTime);
            if(i == 0){
                clientStack1.enqueue(object);
                booth1FinishTime+=enterTime+serviceTime;
            }
            else if(i==1){
                clientStack2.enqueue(object);
                booth2FinishTime+=enterTime+serviceTime;
            }
            else{
                if(clientStack1.length()<=clientStack2.length() && booth2FinishTime> object.getEnterTime()){
                    clientStack1.enqueue(object);
                    while(object.getEnterTime() >= booth1FinishTime && clientStack1.length()>1){
                        clientStack1.dequeue();
                        booth1FinishTime+= clientStack1.frontValue().getServiceTime();
                        if(clientStack1.length()==1){
                            booth1FinishTime=clientStack1.frontValue().getServiceTime()+clientStack1.frontValue().getEnterTime();
                        }
                    }
                }
                else{
                    clientStack2.enqueue(object);
                    while(object.getEnterTime() >= booth2FinishTime && clientStack2.length()>1){
                        clientStack2.dequeue();
                        booth2FinishTime+=clientStack2.frontValue().getServiceTime();
                        if(clientStack2.length()==1){
                            booth2FinishTime=clientStack2.frontValue().getServiceTime()+clientStack2.frontValue().getEnterTime();
                        }
                    }
                }
            }
        }
        booth1FinishTime-=clientStack1.frontValue().getServiceTime();
        booth2FinishTime-=clientStack2.frontValue().getServiceTime();
        while(clientStack1.length()!= 0 || clientStack2.length()!=0){
            if(clientStack1.length()!=0){
                booth1FinishTime+=clientStack1.frontValue().getServiceTime();
            }
            if(clientStack2.length()!=0){
                booth2FinishTime+=clientStack2.frontValue().getServiceTime();
            }
            if(booth2FinishTime < booth1FinishTime){
                if(clientStack1.frontValue()!=null){
                    booth1FinishTime = booth1FinishTime-clientStack1.frontValue().getServiceTime();
                }
                clientStack2.dequeue();
                if(clientStack1.length() == 1 && clientStack2.length() == 0){
                    booth1FinishTime+=clientStack1.frontValue().getServiceTime();
                    clientStack1.dequeue();
                }
                else if(clientStack1.length()-clientStack2.length() >= 1){
                    clientStack2.enqueue(clientStack1.leaveQueue());
                }
            }
            else if(booth2FinishTime > booth1FinishTime){
                if(clientStack2.frontValue()!=null){
                    booth2FinishTime = booth2FinishTime- clientStack2.frontValue().getServiceTime();
                }
                clientStack1.dequeue();
                if(clientStack2.length() == 1 && clientStack1.length() == 0){
                    booth2FinishTime+=clientStack2.frontValue().getServiceTime();
                    clientStack2.dequeue();
                }
                else if(clientStack2.length()- clientStack1.length()>=1){
                    clientStack1.enqueue(clientStack2.leaveQueue());
                }
            }
            else {
                clientStack1.dequeue();
                clientStack2.dequeue();
            }
        }
        System.out.println("Booth 1 finishes at t = " + booth1FinishTime);
        System.out.println("Booth 2 finishes at t = " + booth2FinishTime);
    }
}
