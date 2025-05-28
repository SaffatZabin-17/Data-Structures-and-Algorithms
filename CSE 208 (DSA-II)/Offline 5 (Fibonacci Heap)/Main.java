import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int vertexCount = 0, edgeCount = 0;
        int startVertex = 0, endVertex, weight;
        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        Set<Integer> vertexSet = new LinkedHashSet<>();
        final String FIRST_INPUT_FILE_NAME = "Input1.txt";
        final String SECOND_INPUT_FILE_NAME = "Input2.txt";
        final String OUTPUT_FILE_NAME = "Output.txt";
        BufferedReader br = new BufferedReader(new FileReader(FIRST_INPUT_FILE_NAME));
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        String line;
        int count = 0;
        while(true){
            line = br.readLine();
            if(line == null){
                break;
            }
            String[] tokens = line.split(" ");
            if(count == 0){
                vertexCount = Integer.parseInt(tokens[0]);
                edgeCount = Integer.parseInt(tokens[1]);
                count++;
            }
            else{
                startVertex = Integer.parseInt(tokens[0]);
                endVertex = Integer.parseInt(tokens[1]);
                weight = Integer.parseInt(tokens[2]);
                edgeArrayList.add(new Edge(startVertex, endVertex, weight));
                vertexSet.add(startVertex);
                vertexSet.add(endVertex);
            }
        }
        Graph graph = new Graph(vertexCount);
        for (Edge edge : edgeArrayList) {
            graph.addEdgeDirected(new Vertex(edge.getStartVertex(), Integer.MAX_VALUE), new Vertex(edge.getEndVertex(), Integer.MAX_VALUE));
        }
        ArrayList<Integer> vertexList = new ArrayList<>(vertexSet);
        graph.setEdgeArrayList(edgeArrayList);
        graph.setVertexList(vertexList);
        bw.write("Path Length       Path Cost       Binary Heap Implementation      Fibonacci Heap Implementation");
        bw.write("\n");
        int source = 0, destination = 0;
        br = new BufferedReader(new FileReader(SECOND_INPUT_FILE_NAME));
        int[] values;
        count = 0;
        int testCount = 0;
        while(true){
            line = br.readLine();
            if(line == null){
                break;
            }
            String[] tokens = line.split(" ");
            if(count == 0){
                testCount = Integer.parseInt(tokens[0]);
                count++;
            }
            else{
                source = Integer.parseInt(tokens[0]);
                destination = Integer.parseInt(tokens[1]);
                double startTime = System.nanoTime();
                values = graph.SSSP_Dijkstra(source, destination);
                double endTime = System.nanoTime();
                if(values[1] == 0){
                    bw.write("      " + values[1] + "              " + "Inf" + "                    " + (endTime - startTime));
                }
                else{
                    bw.write("      " + values[1] + "              " + values[0] + "                    " + (endTime - startTime));
                }
                startTime = System.nanoTime();
                values = graph.SSSP_Dijkstra_FibonacciHeap(source, destination);
                endTime = System.nanoTime();
                bw.write("                             " + (endTime-startTime));
                bw.write("\n");
            }
        }
        bw.close();
    }
}

/*
9 14
0 1 4
0 7 8
1 7 11
1 2 8
2 8 2
2 5 4
2 3 7
3 4 9
3 5 14
4 5 10
5 6 2
6 7 1
6 8 6
7 8 7
 */
/*
2
0 4
4 8
 */