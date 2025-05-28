import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Vertex {
    int vertexIndex, vertexColor, vertexDistance;
    Vertex(int vertexIndex, int vertexDistance, int vertexColor){
        this.vertexIndex = vertexIndex;
        this.vertexColor = vertexColor;
        this.vertexDistance = vertexDistance;
    }
    Vertex(int vertexIndex, int vertexDistance){
        this.vertexIndex = vertexIndex;
        this.vertexDistance = vertexDistance;
    }

    public void setVertexIndex(int vertexIndex){
        this.vertexIndex = vertexIndex;
    }

    public int getVertexIndex(){
        return this.vertexIndex;
    }

    public void setVertexColor(int vertexColor){
        this.vertexColor = vertexColor;
    }

    public int getVertexColor(){
        return this.vertexColor;
    }

    public void setVertexDistance(int vertexDistance){
        this.vertexDistance = vertexDistance;
    }
    public int getVertexDistance(){
        return this.vertexDistance;
    }

    @Override
    public String toString(){
        return "[" + this.vertexIndex + " " + this.vertexDistance + "]";
    }
}

class Edge{
    int startVertex, endVertex, weight;

    Edge(){
        this.startVertex = 0;
        this.endVertex = 0;
        this.weight = 0;
    }

    Edge(int startVertex, int endVertex, int weight){
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    public void setStartVertex(int startVertex){
        this.startVertex = startVertex;
    }

    public int getStartVertex(){
        return this.startVertex;
    }

    public void setEndVertex(int endVertex){
        this.endVertex = endVertex;
    }

    public int getEndVertex(){
        return this.endVertex;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public int getWeight(){
        return this.weight;
    }

    @Override
    public String toString(){
        return this.startVertex + " " + this.endVertex + " " + this.weight;
    }
}

public class Graph {
    private int vertexCount = 0;
    private ArrayList<ArrayList<Vertex>> adjList = new ArrayList<>(vertexCount);
    private final boolean[] visited;
    private final int[] distance;
    private final int[] predecessor;

    private int[][] adjMatrix;

    private Set<Integer> vertices = new LinkedHashSet<>();

    private ArrayList<Integer> vertexList = new ArrayList<>();

    private ArrayList<Edge> edgeArrayList = new ArrayList<>();

    Graph() {
        visited = new boolean[vertexCount + 1];
        distance = new int[vertexCount + 1];
        predecessor = new int[vertexCount + 1];
    }

    Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        for (int i = 0; i < vertexCount + 1; i++) {
            adjList.add(new ArrayList<>());
        }
        visited = new boolean[vertexCount + 1];
        distance = new int[vertexCount + 1];
        predecessor = new int[vertexCount + 1];
        adjMatrix = new int[vertexCount + 1][vertexCount + 1];
    }

    Graph(int vertexCount, Set<Integer> vertices) {
        this.vertexCount = vertexCount;
        for (int i = 0; i < vertexCount + 1; i++) {
            adjList.add(new ArrayList<>());
        }
        visited = new boolean[vertexCount + 1];
        distance = new int[vertexCount + 1];
        predecessor = new int[vertexCount + 1];
        this.vertices = vertices;
        vertexList.addAll(vertices);
        adjMatrix = new int[vertexCount + 1][vertexCount + 1];
    }

    public void setEdgeArrayList(ArrayList<Edge> edgeArrayList) {
        this.edgeArrayList = edgeArrayList;
    }

    public ArrayList<Edge> getEdgeArrayList() {
        return this.edgeArrayList;
    }

    public void setAdjList(ArrayList<ArrayList<Vertex>> adjList) {
        this.adjList = adjList;
    }

    public ArrayList<ArrayList<Vertex>> getAdjList() {
        return this.adjList;
    }

    public void setVertexList(ArrayList<Integer> vertexList) {
        this.vertexList = vertexList;
    }

    public void setAdjMatrix(int[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    public boolean[] getVisited() {
        return this.visited;
    }

    public void setAdjMatrix(ArrayList<Edge> edgeArrayList) {
        for (Edge edge : edgeArrayList) {
            int start = edge.getStartVertex();
            int end = edge.getEndVertex();
            int weight = edge.getWeight();
            adjMatrix[start][end] = weight;
        }
    }

    public void addEdgeUndirected(Vertex u, Vertex v) {
        adjList.get(u.getVertexIndex()).add(v);
        adjList.get(v.getVertexIndex()).add(u);
    }

    public void addEdgeUndirected(Vertex u, Vertex v, int weight) {
        adjList.get(u.getVertexIndex()).add(v);
        adjList.get(v.getVertexIndex()).add(u);
        adjMatrix[u.getVertexIndex()][v.getVertexIndex()] = weight;
        adjMatrix[v.getVertexIndex()][u.getVertexIndex()] = weight;
    }

    public void addEdgeDirected(Vertex u, Vertex v) {
        adjList.get(u.getVertexIndex()).add(v);
    }

    public void addEdgeDirected(Vertex u, Vertex v, int weight) {
        adjList.get(u.getVertexIndex()).add(v);
        adjMatrix[u.getVertexIndex()][v.getVertexIndex()] = weight;
    }

    public int[] SSSP_Dijkstra(int source, int destination) throws IOException {
        Arrays.fill(visited, false);
        Arrays.fill(distance, -1);
        Arrays.fill(predecessor, -1);
        Comparator<Vertex> vertexComparator = Comparator.comparingInt(Vertex::getVertexDistance);
        int[][] graphMatrix = new int[vertexCount + 1][vertexCount + 1];
        for (Edge value : edgeArrayList) {
            int start = value.getStartVertex();
            int end = value.getEndVertex();
            int weight = value.getWeight();
            graphMatrix[start][end] = weight;
        }
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        Arrays.fill(predecessor, -1);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(vertexComparator);
        distance[source] = 0;
        priorityQueue.add(new Vertex(source, 0));
        int count = 0;
        while (priorityQueue.size() != 0) {
            Vertex u = priorityQueue.poll();
            assert u != null;
            for (int i = 0; i < adjList.get(u.getVertexIndex()).size(); i++) {
                Vertex v = adjList.get(u.getVertexIndex()).get(i);
                if (distance[u.getVertexIndex()] + graphMatrix[u.getVertexIndex()][v.getVertexIndex()] < distance[v.getVertexIndex()]) {
                    distance[v.getVertexIndex()] = distance[u.getVertexIndex()] + graphMatrix[u.getVertexIndex()][v.getVertexIndex()];
                    predecessor[v.getVertexIndex()] = u.getVertexIndex();
                    int cnt = 5;
                    while(cnt>0){
                        priorityQueue.remove(v);
                        priorityQueue.add(v);
                        cnt--;
                    }
                }
            }
        }
        int[] values = new int[2];
        int shortestPathDistance = distance[destination];
        values[0] = shortestPathDistance;
        int i = destination;
        ArrayList<Integer> shortestPath = new ArrayList<>();
        while (predecessor[i] != -1) {
            shortestPath.add(i);
            i = predecessor[i];
        }
        values[1] = shortestPath.size();
        return values;
    }

    public int[] SSSP_Dijkstra_FibonacciHeap(int source, int destination) {
        Arrays.fill(visited, false);
        Arrays.fill(distance, -1);
        Arrays.fill(predecessor, -1);
        int[][] graphMatrix = new int[vertexCount + 1][vertexCount + 1];
        for (Edge value : edgeArrayList) {
            int start = value.getStartVertex();
            int end = value.getEndVertex();
            int weight = value.getWeight();
            graphMatrix[start][end] = weight;
        }
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        Arrays.fill(predecessor, -1);
        Fibonacci_Heap<Vertex> priorityQueue = new Fibonacci_Heap<>();
        distance[source] = 0;
        node<Vertex> sourceNode = new node<>(new Vertex(source, 0));
        sourceNode.setKey(0);
        priorityQueue.add(sourceNode);
        int count = 0;
        while (priorityQueue.size() != 0) {
            Vertex u = priorityQueue.poll();
            assert u!=null;
            for (int i = 0; i < adjList.get(u.getVertexIndex()).size(); i++) {
                Vertex v = adjList.get(u.getVertexIndex()).get(i);
                if (distance[u.getVertexIndex()] + graphMatrix[u.getVertexIndex()][v.getVertexIndex()] < distance[v.getVertexIndex()]) {
                    distance[v.getVertexIndex()] = distance[u.getVertexIndex()] + graphMatrix[u.getVertexIndex()][v.getVertexIndex()];
                    predecessor[v.getVertexIndex()] = u.getVertexIndex();
                    node<Vertex> temp = new node<>(v);
                    temp.setKey(distance[v.getVertexIndex()]);
                    priorityQueue.add(temp);
                }
            }
        }
        int[] values = new int[2];
        int shortestPathDistance = distance[destination];
        values[0] = shortestPathDistance;
        int i = destination;
        ArrayList<Integer> shortestPath = new ArrayList<>();
        while (predecessor[i] != -1) {
            shortestPath.add(i);
            i = predecessor[i];
        }
        values[1] = shortestPath.size();
        return values;
    }

    public void outputGraph() {
        for (int i = 0; i < vertexCount; i++) {
            System.out.println(i + "-> " + adjList.get(i));
        }
    }
}
