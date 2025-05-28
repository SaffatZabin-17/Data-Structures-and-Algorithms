#include<iostream>
#include<cstring>
using namespace std;

int solutionMatrix[10][1 << 10]; //For  storing overlapping sequences
int costMatrix[10][10]; //For storing the costMatrix array

int solve(int i, int mask, int n){
    //Base Case,stop when all cities have been visited
    if(i == n){
        return 0;
    }
    //Recall previously stored value in solutionMatrix array
    if(solutionMatrix[i][mask] != -1){
        return solutionMatrix[i][mask];
    }
    //Calculation for all cities visited before & minimum costMatrix
    int minimumValue = INT_MAX;
    for(int j =0 ; j < n; j++){
        int temp = 0;
        //use mask to find out which cities have been visited already and add their costMatrix to temp
        for (int k = 0; k < n; k++){
            if(!(mask & (1<<k))){
                temp+= costMatrix[j][k];
            }
        }
        //basic recursive call
        if(mask & (1<<j)){
            minimumValue  = min(minimumValue, costMatrix[j][j] + temp + solve(i + 1, (mask ^ (1 << j)), n ));
        }
    }
    solutionMatrix[i][mask] = minimumValue;
    return solutionMatrix[i][mask];
}

int main(){
    int n;
    cin>>n;
    memset(solutionMatrix, -1 , sizeof(solutionMatrix));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> costMatrix[i][j];
        }
    }
    int ans = solve(0,(1<<n)-1 , n);
    cout<<ans;
}


/*
3
30 75 95
120 45 20
105 30 90
*/

/*
3
25 100 0
0 25 0
150 100 25
*/

/*
2
100 100
5000 100
*/