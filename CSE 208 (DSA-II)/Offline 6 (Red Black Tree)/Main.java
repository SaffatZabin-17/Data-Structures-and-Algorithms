//package Offline6_RedBlackTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception{
        RedBlackTree redBlackTree = new RedBlackTree();
        final String INPUT_FILE_NAME = "Input.txt";
        final String OUTPUT_FILE_NAME = "Output.txt";
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        String line;
        line = br.readLine();
        bw.write(line + "\n");
        int n = Integer.parseInt(line);
        int e, x, found;
        String[] tokens = new String[2];
        for(int i =0;i<n;i++){
            line = br.readLine();
            if(line == null){
                break;
            }
            tokens = line.split(" ");
            e = Integer.parseInt(tokens[0]);
            x = Integer.parseInt(tokens[1]);
            if(e == 0){
                found = redBlackTree.search(x);
                if(found == 0){
                    bw.write("" + e + " " + x + " 0");
                }
                else{
                    redBlackTree.deleteNode(x);
                    bw.write("" + e + " " + x + " 1");
                }
            }
            if(e == 1){
                found = redBlackTree.search(x);
                if(found == 1){
                    bw.write("" + e + " " + x + " 0");
                }
                else{
                    redBlackTree.insert(x);
                    bw.write("" + e + " " + x + " 1");
                }
            }
            if(e == 2){
                found = redBlackTree.search(x);
                if(found == 1){
                    bw.write("" + e + " " + x + " 1");
                }
                else{
                    bw.write("" + e + " " + x + " 0");
                }
            }
            if(e == 3){
                int count = redBlackTree.getCountLessThanVal(x);
                bw.write("" + e + " " + x + " " + count);
            }
            bw.write("\n");
        }
        bw.close();
    }
}

/*
11
1 1
1 2
1 3
1 1
0 1
0 4
2 3
2 5
1 1
3 3
3 6
 */