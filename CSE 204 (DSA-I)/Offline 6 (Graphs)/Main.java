import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("Input_Graph.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter("Output_Graph.txt"));
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        String[] line1;
        int testCase = Integer.parseInt(line);
        while(testCase>0){
            line = sc.nextLine();
            line1 = line.split(" ");
            int die_face = Integer.parseInt(line1[0]);
            int boardSize = Integer.parseInt(line1[1]);
            line = sc.nextLine();
            int ladderCount = Integer.parseInt(line);
            List<Integer> ladderStart = new ArrayList<>();
            List<Integer> ladderEnd = new ArrayList<>();
            int count = 0;
            while(count<ladderCount){
                line = sc.nextLine();
                line1 = line.split(" ");
                ladderStart.add(Integer.valueOf(line1[0]));
                ladderEnd.add(Integer.valueOf(line1[1]));
                count++;
            }
            line = sc.nextLine();
            int snakeCount = Integer.parseInt(line);
            count = 0;
            List<Integer> snakeStart = new ArrayList<>();
            List<Integer> snakeEnd = new ArrayList<>();
            while(count < snakeCount){
                line = sc.nextLine();
                line1 = line.split(" ");
                snakeStart.add(Integer.valueOf(line1[0]));
                snakeEnd.add(Integer.valueOf(line1[1]));
                count++;
            }
            Graph graph = new Graph(die_face, boardSize, ladderStart, ladderEnd, snakeStart, snakeEnd);
            graph.BFS(1);
            String rollCount = String.valueOf(graph.getRollCount());
            bw.write(rollCount + "\n");
            StringBuilder s1 = graph.getFilePrinter();
            bw.write(new String(s1));
            testCase--;
        }
        bw.close();
    }
}
