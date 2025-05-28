#include<iostream>
#include<fstream>
#include<cstdio>
#include<queue>
#include<ctime>
#include<vector>
#include<algorithm>
using namespace std;

void merge(vector<int>&array, int left, int mid, int right){
    int leftArraySize = mid-left+1;
    int rightArraySize = right-mid;
    vector<int> leftArray(leftArraySize);
    vector<int> rightArray(rightArraySize);
    for(int i = 0;i<leftArraySize;i++){
        leftArray[i] = array[left + i];
    }
    for(int i = 0;i<rightArraySize;i++){
        rightArray[i] = array[mid + 1 + i];
    }
    int leftArrayIndex = 0, rightArrayIndex = 0;
    int mergedArrayIndex = left;
    while(leftArrayIndex<leftArraySize && rightArrayIndex<rightArraySize){
        if(leftArray[leftArrayIndex] <= rightArray[rightArrayIndex]){
            array[mergedArrayIndex] = leftArray[leftArrayIndex];
            leftArrayIndex++;
        }
        else{
            array[mergedArrayIndex] = rightArray[rightArrayIndex];
            rightArrayIndex++;
        }
        mergedArrayIndex++;
    }
    while(leftArrayIndex<leftArraySize){
        array[mergedArrayIndex] = leftArray[leftArrayIndex];
        leftArrayIndex++;
        mergedArrayIndex++;
    }
    while(rightArrayIndex<rightArraySize){
        array[mergedArrayIndex] = rightArray[rightArrayIndex];
        rightArrayIndex++;
        mergedArrayIndex++;
    }
}
void mergeSort(vector<int>&array, int start, int finish){
    if(start>=finish){
        return;
    }
    int mid = (start + finish)/2;
    mergeSort(array, start, mid);
    mergeSort(array, mid+1, finish);
    merge(array, start, mid, finish);
}

void insertionSort(vector<int> &array){
    int temp;
    for(int i =1;i<array.size();i++){
        temp = array[i];
        int j = i-1;
        while(j>0 && array[j]>temp){
            array[j+1] = array[j];
            j--;
        }
        array[j] = temp;
    }
}

int partition(vector<int>&array, int low, int high){
    int pivot = array[high];
    int i = low-1;
    for(int j = low; j<=high; j++){
        if(array[j] <pivot){
            i++;
            swap(array[i], array[j]);
        }
    }
    swap(array[i+1], array[high]);
    return i+1;
}

void quickSort(vector<int> &array, int low, int high){
    if(low<high){
        int pi = partition(array, low, high);
        quickSort(array, low, pi-1);
        quickSort(array, pi+1, high);
    }
}

int randomizedPartition(vector<int>&array, int low, int high){
    srand(time(nullptr));
    int random = low + rand()%(high-low);
    swap(array[random], array[high]);
    return partition(array, low, high);
}

void randomizedQuickSort(vector<int> &array, int low, int high){
    if(low<high){
        int pi = randomizedPartition(array, low, high);
        quickSort(array, low, pi-1);
        quickSort(array, pi+1, high);
    }
}



int main()
{
    srand(1);
    vector<float>STLSortTime(6);
    vector<float> mergeSortTime(6);
    vector<float> insertionSortTime(6);
    vector<float> quickSortTime(6);
    vector<float> randomizedQuickSortTime(6);
    vector<float> quickSortSortedTime(6);
    vector<float> randomizedQuickSortSortedTime(6);
    float STLSortClock = 0, mergeSortClock = 0, insertionSortClock = 0, quickSortClock = 0, randomizedQuickSortClock = 0;
    float sortedQuickSortClock = 0, sortedRandomizedQuickSortClock = 0;
    vector<int> randomNumberSet1(5);
    vector<int> randomNumberSet2(10);
    vector<int> randomNumberSet3(100);
    vector<int> randomNumberSet4(1000);
    vector<int> randomNumberSet5(10000);
    vector<int> randomNumberSet6(100000);
    int index = 0;
    for(int i = 0;i<20;i++){
        vector<int> temp(5);
        for(int &k : temp){
            k = rand();
        }
        for(int j =0;j<5;j++){
            randomNumberSet1[j] = temp[j];
        }
        clock_t beforeSTLSort = clock();
        sort(randomNumberSet1.begin(), randomNumberSet1.end());
        STLSortClock += float(clock()-beforeSTLSort)/CLOCKS_PER_SEC;

        for(int j =0;j<5;j++){
            randomNumberSet1[j] = temp[j];
        }
        clock_t beforeMergeSort = clock();
        mergeSort(randomNumberSet1, 0, randomNumberSet1.size()-1);
        mergeSortClock += float(clock()-beforeMergeSort)/CLOCKS_PER_SEC;

        for(int j =0;j<5;j++){
            randomNumberSet1[j] = temp[j];
        }
        clock_t beforeQuickSort = clock();
        quickSort(randomNumberSet1, 0, randomNumberSet1.size()-1);
        quickSortClock+=float(clock()-beforeQuickSort)/CLOCKS_PER_SEC;

        for(int j =0;j<5;j++){
            randomNumberSet1[j] = temp[j];
        }
        clock_t beforeRandomizedQuickSort = clock();
        randomizedQuickSort(randomNumberSet1, 0, randomNumberSet1.size()-1);
        randomizedQuickSortClock += float(clock()-beforeRandomizedQuickSort)/CLOCKS_PER_SEC;

        for(int j =0;j<5;j++){
            randomNumberSet1[j] = temp[j];
        }
        clock_t beforeInsertionSort = clock();
        insertionSort(randomNumberSet1);
        insertionSortClock += float(clock()-beforeInsertionSort)/CLOCKS_PER_SEC;

        clock_t beforeSortedQuickSort = clock();
        quickSort(randomNumberSet1, 0, randomNumberSet1.size()-1);
        sortedQuickSortClock += float(clock()-beforeSortedQuickSort)/CLOCKS_PER_SEC;

        clock_t beforeSortedRandomizedQuickSort = clock();
        randomizedQuickSort(randomNumberSet1, 0, randomNumberSet1.size()-1);
        sortedRandomizedQuickSortClock += float(clock()-beforeSortedRandomizedQuickSort)/CLOCKS_PER_SEC;
    }
    STLSortClock = (STLSortClock*1000)/20;
    mergeSortClock = (mergeSortClock*1000)/20;
    quickSortClock = (quickSortClock*1000)/20;
    randomizedQuickSortClock = (randomizedQuickSortClock*1000)/20;
    insertionSortClock = (insertionSortClock*1000)/20;
    sortedQuickSortClock = (sortedQuickSortClock*1000)/20;
    sortedRandomizedQuickSortClock = (sortedRandomizedQuickSortClock*1000)/20;
    STLSortTime[index] = STLSortClock;
    mergeSortTime[index] = mergeSortClock;
    quickSortTime[index] = quickSortClock;
    randomizedQuickSortTime[index] = randomizedQuickSortClock;
    insertionSortTime[index] = insertionSortClock;
    quickSortSortedTime[index] = sortedQuickSortClock;
    randomizedQuickSortSortedTime[index] = sortedRandomizedQuickSortClock;
    index++;
    STLSortClock = 0, mergeSortClock = 0, insertionSortClock = 0, quickSortClock = 0, randomizedQuickSortClock = 0;
    sortedQuickSortClock = 0, sortedRandomizedQuickSortClock = 0;


    for(int i = 0;i<20;i++){
        vector<int> temp(10);
        for(int &k : temp){
            k = rand();
        }
        for(int j =0;j<10;j++){
            randomNumberSet2[j] = temp[j];
        }
        clock_t beforeSTLSort = clock();
        sort(randomNumberSet2.begin(), randomNumberSet2.end());
        STLSortClock += float(clock()-beforeSTLSort)/CLOCKS_PER_SEC;

        for(int j =0;j<10;j++){
            randomNumberSet2[j] = temp[j];
        }
        clock_t beforeMergeSort = clock();
        mergeSort(randomNumberSet2, 0, randomNumberSet2.size()-1);
        mergeSortClock += float(clock()-beforeMergeSort)/CLOCKS_PER_SEC;

        for(int j =0;j<10;j++){
            randomNumberSet2[j] = temp[j];
        }
        clock_t beforeQuickSort = clock();
        quickSort(randomNumberSet2, 0, randomNumberSet2.size()-1);
        quickSortClock+=float(clock()-beforeQuickSort)/CLOCKS_PER_SEC;

        for(int j =0;j<10;j++){
            randomNumberSet2[j] = temp[j];
        }
        clock_t beforeRandomizedQuickSort = clock();
        randomizedQuickSort(randomNumberSet2, 0, randomNumberSet2.size()-1);
        randomizedQuickSortClock += float(clock()-beforeRandomizedQuickSort)/CLOCKS_PER_SEC;

        for(int j =0;j<10;j++){
            randomNumberSet2[j] = temp[j];
        }
        clock_t beforeInsertionSort = clock();
        insertionSort(randomNumberSet2);
        insertionSortClock += float(clock()-beforeInsertionSort)/CLOCKS_PER_SEC;

        clock_t beforeSortedQuickSort = clock();
        quickSort(randomNumberSet2, 0, randomNumberSet2.size()-1);
        sortedQuickSortClock += float(clock()-beforeSortedQuickSort)/CLOCKS_PER_SEC;

        clock_t beforeSortedRandomizedQuickSort = clock();
        randomizedQuickSort(randomNumberSet2, 0, randomNumberSet2.size()-1);
        sortedRandomizedQuickSortClock += float(clock()-beforeSortedRandomizedQuickSort)/CLOCKS_PER_SEC;
    }
    STLSortClock = (STLSortClock*1000)/20;
    mergeSortClock = (mergeSortClock*1000)/20;
    quickSortClock = (quickSortClock*1000)/20;
    randomizedQuickSortClock = (randomizedQuickSortClock*1000)/20;
    insertionSortClock = (insertionSortClock*1000)/20;
    sortedQuickSortClock = (sortedQuickSortClock*1000)/20;
    sortedRandomizedQuickSortClock = (sortedRandomizedQuickSortClock*1000)/20;
    STLSortTime[index] = STLSortClock;
    mergeSortTime[index] = mergeSortClock;
    quickSortTime[index] = quickSortClock;
    randomizedQuickSortTime[index] = randomizedQuickSortClock;
    insertionSortTime[index] = insertionSortClock;
    quickSortSortedTime[index] = sortedQuickSortClock;
    randomizedQuickSortSortedTime[index] = sortedRandomizedQuickSortClock;
    index++;
    STLSortClock = 0, mergeSortClock = 0, insertionSortClock = 0, quickSortClock = 0, randomizedQuickSortClock = 0;
    sortedQuickSortClock = 0, sortedRandomizedQuickSortClock = 0;


    for(int i = 0;i<20;i++){
        vector<int> temp(100);
        for(int &k : temp){
            k = rand();
        }

        for(int j =0;j<100;j++){
            randomNumberSet3[j] = temp[j];
        }
        clock_t beforeSTLSort = clock();
        sort(randomNumberSet3.begin(), randomNumberSet3.end());
        STLSortClock += float(clock()-beforeSTLSort)/CLOCKS_PER_SEC;

        for(int j =0;j<100;j++){
            randomNumberSet3[j] = temp[j];
        }
        clock_t beforeMergeSort = clock();
        mergeSort(randomNumberSet3, 0, randomNumberSet3.size()-1);
        mergeSortClock += float(clock()-beforeMergeSort)/CLOCKS_PER_SEC;

        for(int j =0;j<100;j++){
            randomNumberSet3[j] = temp[j];
        }
        clock_t beforeQuickSort = clock();
        quickSort(randomNumberSet3, 0, randomNumberSet3.size()-1);
        quickSortClock+=float(clock()-beforeQuickSort)/CLOCKS_PER_SEC;

        for(int j =0;j<100;j++){
            randomNumberSet3[j] = temp[j];
        }
        clock_t beforeRandomizedQuickSort = clock();
        randomizedQuickSort(randomNumberSet3, 0, randomNumberSet3.size()-1);
        randomizedQuickSortClock += float(clock()-beforeRandomizedQuickSort)/CLOCKS_PER_SEC;

        for(int j =0;j<100;j++){
            randomNumberSet3[j] = temp[j];
        }
        clock_t beforeInsertionSort = clock();
        insertionSort(randomNumberSet3);
        insertionSortClock += float(clock()-beforeInsertionSort)/CLOCKS_PER_SEC;

        clock_t beforeSortedQuickSort = clock();
        quickSort(randomNumberSet3, 0, randomNumberSet3.size()-1);
        sortedQuickSortClock += float(clock()-beforeSortedQuickSort)/CLOCKS_PER_SEC;

        clock_t beforeSortedRandomizedQuickSort = clock();
        randomizedQuickSort(randomNumberSet3, 0, randomNumberSet3.size()-1);
        sortedRandomizedQuickSortClock += float(clock()-beforeSortedRandomizedQuickSort)/CLOCKS_PER_SEC;
    }
    STLSortClock = (STLSortClock*1000)/20;
    mergeSortClock = (mergeSortClock*1000)/20;
    quickSortClock = (quickSortClock*1000)/20;
    randomizedQuickSortClock = (randomizedQuickSortClock*1000)/20;
    insertionSortClock = (insertionSortClock*1000)/20;
    sortedQuickSortClock = (sortedQuickSortClock*1000)/20;
    sortedRandomizedQuickSortClock = (sortedRandomizedQuickSortClock*1000)/20;
    STLSortTime[index] = STLSortClock;
    mergeSortTime[index] = mergeSortClock;
    quickSortTime[index] = quickSortClock;
    randomizedQuickSortTime[index] = randomizedQuickSortClock;
    insertionSortTime[index] = insertionSortClock;
    quickSortSortedTime[index] = sortedQuickSortClock;
    randomizedQuickSortSortedTime[index] = sortedRandomizedQuickSortClock;
    index++;
    STLSortClock = 0, mergeSortClock = 0, insertionSortClock = 0, quickSortClock = 0, randomizedQuickSortClock = 0;
    sortedQuickSortClock = 0, sortedRandomizedQuickSortClock = 0;


    for(int i = 0;i<20;i++){
        vector<int> temp(1000);
        for(int &k : temp){
            k = rand();
        }

        for(int j =0;j<1000;j++){
            randomNumberSet4[j] = temp[j];
        }
        clock_t beforeSTLSort = clock();
        sort(randomNumberSet4.begin(), randomNumberSet4.end());
        STLSortClock += float(clock()-beforeSTLSort)/CLOCKS_PER_SEC;

        for(int j =0;j<1000;j++){
            randomNumberSet4[j] = temp[j];
        }
        clock_t beforeMergeSort = clock();
        mergeSort(randomNumberSet4, 0, randomNumberSet4.size()-1);
        mergeSortClock += float(clock()-beforeMergeSort)/CLOCKS_PER_SEC;

        for(int j =0;j<1000;j++){
            randomNumberSet4[j] = temp[j];
        }
        clock_t beforeQuickSort = clock();
        quickSort(randomNumberSet4, 0, randomNumberSet4.size()-1);
        quickSortClock+=float(clock()-beforeQuickSort)/CLOCKS_PER_SEC;

        for(int j =0;j<1000;j++){
            randomNumberSet4[j] = temp[j];
        }
        clock_t beforeRandomizedQuickSort = clock();
        randomizedQuickSort(randomNumberSet4, 0, randomNumberSet4.size()-1);
        randomizedQuickSortClock += float(clock()-beforeRandomizedQuickSort)/CLOCKS_PER_SEC;

        for(int j =0;j<1000;j++){
            randomNumberSet4[j] = temp[j];
        }
        clock_t beforeInsertionSort = clock();
        insertionSort(randomNumberSet4);
        insertionSortClock += float(clock()-beforeInsertionSort)/CLOCKS_PER_SEC;

        clock_t beforeSortedQuickSort = clock();
        quickSort(randomNumberSet4, 0, randomNumberSet4.size()-1);
        sortedQuickSortClock += float(clock()-beforeSortedQuickSort)/CLOCKS_PER_SEC;

        clock_t beforeSortedRandomizedQuickSort = clock();
        randomizedQuickSort(randomNumberSet4, 0, randomNumberSet4.size()-1);
        sortedRandomizedQuickSortClock += float(clock()-beforeSortedRandomizedQuickSort)/CLOCKS_PER_SEC;
    }
    STLSortClock = (STLSortClock*1000)/20;
    mergeSortClock = (mergeSortClock*1000)/20;
    quickSortClock = (quickSortClock*1000)/20;
    randomizedQuickSortClock = (randomizedQuickSortClock*1000)/20;
    insertionSortClock = (insertionSortClock*1000)/20;
    sortedQuickSortClock = (sortedQuickSortClock*1000)/20;
    sortedRandomizedQuickSortClock = (sortedRandomizedQuickSortClock*1000)/20;
    STLSortTime[index] = STLSortClock;
    mergeSortTime[index] = mergeSortClock;
    quickSortTime[index] = quickSortClock;
    randomizedQuickSortTime[index] = randomizedQuickSortClock;
    insertionSortTime[index] = insertionSortClock;
    quickSortSortedTime[index] = sortedQuickSortClock;
    randomizedQuickSortSortedTime[index] = sortedRandomizedQuickSortClock;
    index++;
    STLSortClock = 0, mergeSortClock = 0, insertionSortClock = 0, quickSortClock = 0, randomizedQuickSortClock = 0;
    sortedQuickSortClock = 0, sortedRandomizedQuickSortClock = 0;



    for(int i = 0;i<20;i++){
        vector<int> temp(10000);
        for(int &k : temp){
            k = rand();
        }

        for(int j =0;j<10000;j++){
            randomNumberSet5[j] = temp[j];
        }
        clock_t beforeSTLSort = clock();
        sort(randomNumberSet5.begin(), randomNumberSet5.end());
        STLSortClock += float(clock()-beforeSTLSort)/CLOCKS_PER_SEC;

        for(int j =0;j<10000;j++){
            randomNumberSet5[j] = temp[j];
        }
        clock_t beforeMergeSort = clock();
        mergeSort(randomNumberSet5, 0, randomNumberSet5.size()-1);
        mergeSortClock += float(clock()-beforeMergeSort)/CLOCKS_PER_SEC;

        for(int j =0;j<10000;j++){
            randomNumberSet5[j] = temp[j];
        }
        clock_t beforeQuickSort = clock();
        quickSort(randomNumberSet5, 0, randomNumberSet5.size()-1);
        quickSortClock+=float(clock()-beforeQuickSort)/CLOCKS_PER_SEC;

        for(int j =0;j<10000;j++){
            randomNumberSet5[j] = temp[j];
        }
        clock_t beforeRandomizedQuickSort = clock();
        randomizedQuickSort(randomNumberSet5, 0, randomNumberSet5.size()-1);
        randomizedQuickSortClock += float(clock()-beforeRandomizedQuickSort)/CLOCKS_PER_SEC;

        for(int j =0;j<10000;j++){
            randomNumberSet5[j] = temp[j];
        }
        clock_t beforeInsertionSort = clock();
        insertionSort(randomNumberSet5);
        insertionSortClock += float(clock()-beforeInsertionSort)/CLOCKS_PER_SEC;

        clock_t beforeSortedQuickSort = clock();
        quickSort(randomNumberSet5, 0, randomNumberSet5.size()-1);
        sortedQuickSortClock += float(clock()-beforeSortedQuickSort)/CLOCKS_PER_SEC;

        clock_t beforeSortedRandomizedQuickSort = clock();
        randomizedQuickSort(randomNumberSet5, 0, randomNumberSet5.size()-1);
        sortedRandomizedQuickSortClock += float(clock()-beforeSortedRandomizedQuickSort)/CLOCKS_PER_SEC;
    }
    STLSortClock = (STLSortClock*1000)/20;
    mergeSortClock = (mergeSortClock*1000)/20;
    quickSortClock = (quickSortClock*1000)/20;
    randomizedQuickSortClock = (randomizedQuickSortClock*1000)/20;
    insertionSortClock = (insertionSortClock*1000)/20;
    sortedQuickSortClock = (sortedQuickSortClock*1000)/20;
    sortedRandomizedQuickSortClock = (sortedRandomizedQuickSortClock*1000)/20;
    STLSortTime[index] = STLSortClock;
    mergeSortTime[index] = mergeSortClock;
    quickSortTime[index] = quickSortClock;
    randomizedQuickSortTime[index] = randomizedQuickSortClock;
    insertionSortTime[index] = insertionSortClock;
    quickSortSortedTime[index] = sortedQuickSortClock;
    randomizedQuickSortSortedTime[index] = sortedRandomizedQuickSortClock;
    index++;
    STLSortClock = 0, mergeSortClock = 0, insertionSortClock = 0, quickSortClock = 0, randomizedQuickSortClock = 0;
    sortedQuickSortClock = 0, sortedRandomizedQuickSortClock = 0;


    for(int i = 0;i<20;i++){
        vector<int> temp(100000);
        for(int &k : temp){
            k = rand();
        }

        for(int j =0;j<100000;j++){
            randomNumberSet6[j] = temp[j];
        }
        clock_t beforeSTLSort = clock();
        sort(randomNumberSet6.begin(), randomNumberSet6.end());
        STLSortClock += float(clock()-beforeSTLSort)/CLOCKS_PER_SEC;

        for(int j =0;j<100000;j++){
            randomNumberSet6[j] = temp[j];
        }
        clock_t beforeMergeSort = clock();
        mergeSort(randomNumberSet6, 0, randomNumberSet6.size()-1);
        mergeSortClock += float(clock()-beforeMergeSort)/CLOCKS_PER_SEC;

        for(int j =0;j<100000;j++){
            randomNumberSet6[j] = temp[j];
        }
        clock_t beforeQuickSort = clock();
        quickSort(randomNumberSet6, 0, randomNumberSet6.size()-1);
        quickSortClock+=float(clock()-beforeQuickSort)/CLOCKS_PER_SEC;

        for(int j =0;j<100000;j++){
            randomNumberSet6[j] = temp[j];
        }
        clock_t beforeRandomizedQuickSort = clock();
        randomizedQuickSort(randomNumberSet6, 0, randomNumberSet6.size()-1);
        randomizedQuickSortClock += float(clock()-beforeRandomizedQuickSort)/CLOCKS_PER_SEC;

        for(int j =0;j<100000;j++){
            randomNumberSet6[j] = temp[j];
        }
        clock_t beforeInsertionSort = clock();
        insertionSort(randomNumberSet6);
        insertionSortClock += float(clock()-beforeInsertionSort)/CLOCKS_PER_SEC;

        clock_t beforeSortedQuickSort = clock();
        quickSort(randomNumberSet6, 0, randomNumberSet6.size()-1);
        sortedQuickSortClock += float(clock()-beforeSortedQuickSort)/CLOCKS_PER_SEC;

        clock_t beforeSortedRandomizedQuickSort = clock();
        randomizedQuickSort(randomNumberSet6, 0, randomNumberSet6.size()-1);
        sortedRandomizedQuickSortClock += float(clock()-beforeSortedRandomizedQuickSort)/CLOCKS_PER_SEC;
    }
    STLSortClock = (STLSortClock*1000)/20;
    mergeSortClock = (mergeSortClock*1000)/20;
    quickSortClock = (quickSortClock*1000)/20;
    randomizedQuickSortClock = (randomizedQuickSortClock*1000)/20;
    insertionSortClock = (insertionSortClock*1000)/20;
    sortedQuickSortClock = (sortedQuickSortClock*1000)/20;
    sortedRandomizedQuickSortClock = (sortedRandomizedQuickSortClock*1000)/20;
    STLSortTime[index] = STLSortClock;
    mergeSortTime[index] = mergeSortClock;
    quickSortTime[index] = quickSortClock;
    randomizedQuickSortTime[index] = randomizedQuickSortClock;
    insertionSortTime[index] = insertionSortClock;
    quickSortSortedTime[index] = sortedQuickSortClock;
    randomizedQuickSortSortedTime[index] = sortedRandomizedQuickSortClock;
    index++;
    ofstream output;
    output.open("Output.csv");
    output << "Time required in ms \n";
    output << "n, Merge Sort, Quicksort, Randomized Quicksort, Insertion Sort, Quicksort with Sorted Input, Randomized Quicksort with Sorted Input, STL sort() function\n";
    for(int i =0;i<6;i++){
        if( i == 0){
            output << "5,";
        }
        if(i == 1){
            output << "10,";
        }
        if(i == 2){
            output << "100,";
        }
        if(i == 3){
            output << "1000,";
        }
        if(i == 4){
            output << "10000,";
        }
        if(i == 5){
            output << "100000,";
        }
        output << mergeSortTime[i] << "," << quickSortTime[i] << "," << randomizedQuickSortTime[i] << "," << insertionSortTime[i] << ",";
        output << quickSortSortedTime[i] << "," << randomizedQuickSortSortedTime[i] << "," << STLSortTime[i] << endl;
    }
}