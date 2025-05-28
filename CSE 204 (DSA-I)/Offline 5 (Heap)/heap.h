//
// Created by 1905060 on 1/9/2022.
//
#include<vector>
#include<string>
using namespace std;
#ifndef UNTITLED_HEAP_H
#define UNTITLED_HEAP_H

class Heap{
private:
    int* array;
    int length;
public:

    Heap(int x){
        array = new int[x+1];
        this->length = 0;
    }

    Heap(vector<int> vector){
        array = new int[vector.size()+1];
        this->length = 0;
        for(int i : vector){
            insert(i);
        }
    }

    Heap(const Heap &h){
        this->length = h.length;
        this->array = new int[this->length];
        for(int i =0;i<length;i++){
            this->array[i] = h.array[i];
        }
    }

    ~Heap(){
        delete[] array;
        this->length = 0;
    }

    void insert(int x){
        array[length+1] = x;
        int pos = length+1;
        while(pos/2!=0 && array[pos/2]<array[pos]){
            int temp = array[pos/2];
            array[pos/2] = array[pos];
            array[pos] = temp;
            pos = pos/2;
        }
        length++;
    }

    int size() const{
        return length;
    }

    int getMax(){
        return array[1];
    }

    void deleteKey(){
        array[1] = array[length];
        length--;
        int pos = 1, leftChild = 2, rightChild = 3;
        while(rightChild<=length+1){
            if(array[pos]>=array[leftChild] && array[length]>=array[rightChild]){
                break;
            }
            else if(array[leftChild]>=array[rightChild]){
                int temp = array[pos];
                array[pos] = array[leftChild];
                array[leftChild] = temp;
                pos = 2*pos;
            }
            else{
                int temp = array[pos];
                array[pos] = array[rightChild];
                array[rightChild] = temp;
                pos = (2*pos)+1;
            }
            leftChild = 2*pos;
            rightChild = (2*pos)+1;
        }
    }
};

void heapsort(vector<int>&v){
    Heap heap(v);
    vector<int> temp(v.size());
    int i =0;
    while(heap.size()!=0){
        temp[i] = heap.getMax();
        heap.deleteKey();
        i++;
    }
    for(int j =0;j<temp.size();j++){
        v[j] = temp[j];
    }
}
#endif //UNTITLED_HEAP_H
