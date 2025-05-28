import java.util.*;

public class Graph {
    private final int boardSize;
    private int rollCount;
    private final List<Integer> ladderStart;
    private final List<Integer> ladderEnd;
    private final List<Integer> snakeStart;
    private final List<Integer> snakeEnd;
    private final LinkedList<Integer>[] adjacencyList;
    private final StringBuilder filePrinter = new StringBuilder();

    Graph(int die_face, int boardSize, List<Integer> ladderStart, List<Integer> ladderEnd, List<Integer> snakeStart, List<Integer> snakeEnd) {
        this.boardSize = boardSize;
        this.ladderStart = ladderStart;
        this.ladderEnd = ladderEnd;
        this.snakeStart = snakeStart;
        this.snakeEnd = snakeEnd;
        //Creating adjacency List
        adjacencyList = new LinkedList[boardSize + 1];
        for (int i = 0; i <= boardSize; i++) {
            adjacencyList[i] = new LinkedList<>();
            for (int j = 1; j <= die_face; j++) {
                if (i + j > boardSize) {
                    break;
                }
                adjacencyList[i].add(i + j);
            }
        }
        //Inputs ladders
        int i, j = 0;
        while (j < ladderStart.size()) {
            i = 1;
            if (ladderStart.get(j) - die_face >= 1) {
                i = ladderStart.get(j) - die_face;
            }
            while (i < ladderStart.get(j)) {
                if (adjacencyList[i] != null) {
                    Iterator<Integer> iterator = adjacencyList[i].listIterator();
                    int k = 0;
                    while (iterator.hasNext()) {
                        int temp = iterator.next();
                        if (temp == ladderStart.get(j)) {
                            adjacencyList[i].set(k, ladderEnd.get(j));
                        }
                        k++;
                    }
                }
                i++;
            }
            adjacencyList[i] = null;
            j++;
        }
        //Inputs Snake
        j = 0;
        while (j < snakeStart.size()) {
            i = 1;
            if (snakeStart.get(j) - die_face >= 1) {
                i = snakeStart.get(j) - die_face;
            }
            while (i < snakeStart.get(j)) {
                if (adjacencyList[i] != null) {
                    Iterator<Integer> iterator = adjacencyList[i].listIterator();
                    int k = 0;
                    while (iterator.hasNext()) {
                        int temp = iterator.next();
                        if (temp == snakeStart.get(j)) {
                            adjacencyList[i].set(k, snakeEnd.get(j));
                        }
                        k++;
                    }
                }
                i++;
            }
            adjacencyList[i] = null;
            j++;
        }
    }

    public int getRollCount(){
        return rollCount;
    }

    public StringBuilder getFilePrinter(){
        return this.filePrinter;
    }

    public void BFS(int start) {
        boolean[] check = new boolean[boardSize + 1];
        int[] parent = new int[boardSize + 1];
        int[] distance = new int[boardSize + 1];
        Arrays.fill(check, false);                 //Check whether a node has been visited or not
        Arrays.fill(parent, -2);                   //Used to find the shortest path recursively
        Arrays.fill(distance, -1);                 //Used for finding the shortest distance to the end node or to determine whether the end node is isReachable
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        parent[start] = -1;
        distance[start] = 0;
        check[start] = true;
        while (!queue.isEmpty()) {
            int top = queue.poll();
            Iterator<Integer> it = adjacencyList[top].listIterator();
            if (adjacencyList[top] != null) {
                while (it.hasNext()) {
                    int neighbour = it.next();
                    if (neighbour <= top) {
                        for (int i = 0; i < snakeEnd.size(); i++) {
                            if (snakeEnd.get(i) == neighbour) {
                                parent[snakeStart.get(i)] = 0;
                                distance[snakeStart.get(i)] = distance[top] + 1;
                            }
                        }
                    }
                    if (!check[neighbour]) {
                        queue.add(neighbour);
                        check[neighbour] = true;
                        distance[neighbour] = distance[top] + 1;
                        parent[neighbour] = top;
                    }
                }
            }
        }
        if(distance[boardSize]==-1){
            rollCount = -1;
            filePrinter.append("No Solution").append("\n");
        } else{
            rollCount = distance[boardSize];
            print(parent, filePrinter, 1);
        }
        for(int i =0;i<ladderEnd.size();i++){
            int tail = ladderEnd.get(i);
            if(distance[tail]!=-1){
                int head = ladderStart.get(i);
                parent[head] = 0;
                distance[head] = distance[tail];
            }
        }
        print(parent, filePrinter, 0);
    }

    private void print(int[] parent, StringBuilder stringBuilder, int check){
        if(check == 1){
            printHelp(parent, boardSize, stringBuilder);
            stringBuilder.append(boardSize).append("\n");
        }
        else{
            boolean flag = true;
            for(int i =1;i<parent.length;i++){
                if(parent[i] == -2){
                    stringBuilder.append(i).append(" ");
                    if(flag){
                        flag = false;
                    }
                }
            }
            if(flag){
                stringBuilder.append("All Reachable").append("\n");
            }
        }
    }

    private void printHelp(int[] parent, int index, StringBuilder stringBuilder) {
        if (parent[index] != -1){
            printHelp(parent, parent[index], stringBuilder);
            for (int i = 0; i < ladderEnd.size(); i++) {
                if (parent[index] == ladderEnd.get(i)) {
                    stringBuilder.append(ladderStart.get(i)).append(" -> ");
                }
            }
            for (int i = 0; i < snakeEnd.size(); i++) {
                if (parent[index] == snakeEnd.get(i)) {
                    stringBuilder.append(snakeStart.get(i)).append(" -> ");
                }
            }
            stringBuilder.append(parent[index]).append(" -> ");
        }
    }
}
