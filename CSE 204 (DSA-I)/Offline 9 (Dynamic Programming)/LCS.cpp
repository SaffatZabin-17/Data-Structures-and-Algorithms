//#include<iostream>
//#include<string>
//#include<vector>
//using namespace std;
//
//void solve(const string& X, const string& Y){
//    int m = (int)X.length()+1;
//    int n = (int)Y.length()+1;
//    int LCSCountMatrix[m][n], LCSStringMatrix[m][n];
//    for(int i =0;i<=m;i++){
//        LCSCountMatrix[i][0] = 0;
//        LCSStringMatrix[i][0] = 0;
//    }
//    for(int i = 0;i<=n;i++){
//        LCSCountMatrix[0][i] = 0;
//        LCSStringMatrix[0][i] = 0;
//    }
//    for(int i=1; i<m;i++){
//        for(int j =1;j<n;j++){
//            if(X[i-1] == Y[j-1]){
//                LCSCountMatrix[i][j] = LCSCountMatrix[i - 1][j - 1] + 1;
//                LCSStringMatrix[i][j] = 1;
//            }
//            else{
//                if(LCSCountMatrix[i-1][j]>=LCSCountMatrix[i][j-1]){
//                    LCSCountMatrix[i][j] = LCSCountMatrix[i-1][j];
//                    LCSStringMatrix[i][j] = 2;
//                }
//                else{
//                    LCSCountMatrix[i][j] = LCSCountMatrix[i][j-1];
//                    LCSStringMatrix[i][j] = 3;
//                }
//            }
//        }
//    }
//    cout << LCSCountMatrix[m-1][n-1] << endl;
//    vector<char>LCS(LCSCountMatrix[m-1][n-1]);
//    int i = LCSCountMatrix[m-1][n-1];
//    while(i>0){
//        if(LCSStringMatrix[m-1][n-1]==1){
//            LCS[i-1] = X[m-2];
//            m--;
//            n--;
//            i--;
//        }
//        else if(LCSStringMatrix[m-1][n-1] == 2){
//            m--;
//        }
//        else{
//            n--;
//        }
//    }
//    for(char c:LCS){
//        cout << c;
//    }
//}
//
//int main(){
//    string X, Y;
//    cin>> X >> Y;
//    string LCSString;
//    solve(X, Y);
//}
