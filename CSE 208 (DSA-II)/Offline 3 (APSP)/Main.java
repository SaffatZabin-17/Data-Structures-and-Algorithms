import java.util.*;

public class Main {
    public static void main(String[] args) {
        int vertexCount = 0, edgeCount  = 0;
        Scanner sc = new Scanner(System.in);
        vertexCount = sc.nextInt();
        edgeCount = sc.nextInt();
        Graph graph = new Graph(vertexCount);
        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        Set<Integer> vertexSet = new LinkedHashSet<>();
        int startVertex, endVertex, weight;
        for(int i =0;i<edgeCount;i++){
            startVertex = sc.nextInt();
            endVertex = sc.nextInt();
            graph.addEdgeDirected(new Vertex(startVertex, Integer.MAX_VALUE), new Vertex(endVertex, Integer.MAX_VALUE));
            weight = sc.nextInt();
            edgeArrayList.add(new Edge(startVertex, endVertex, weight));
            vertexSet.add(startVertex);
            vertexSet.add(endVertex);
        }

        ArrayList<Integer> vertexList = new ArrayList<>(vertexSet);
        graph.setEdgeArrayList(edgeArrayList);
        graph.setVertexList(vertexList);
        graph.APSP_FloydWarshall();
        System.out.println();
        graph.APSP_MatrixChainMultiplication();
    }
}