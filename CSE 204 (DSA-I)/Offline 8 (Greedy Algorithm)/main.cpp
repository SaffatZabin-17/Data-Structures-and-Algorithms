#include<bits/stdc++.h>
using namespace std;

int main(){
    int n, k, count = 0;
    cin>> n >> k;
    vector<int> vector(n);
    for(int & i : vector){
        cin >> i;
    }
    sort(vector.begin(),vector.end(), greater<>());
    long long value = 0;
    for(int i =0, j = 1;i<vector.size();i++, j++){
        value+=(count+1)*vector[i];
        if(j%k==0){
            count++;
        }
    }
    cout << value << endl;
}