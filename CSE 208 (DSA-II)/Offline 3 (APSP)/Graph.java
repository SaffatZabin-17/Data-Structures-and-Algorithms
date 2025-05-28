import java.util.*;

class Vertex {
    int vertexIndex, vertexColor, vertexDistance;
    /*Vertex(int vertexIndex, int vertexColor){
        this.vertexIndex = vertexIndex;
        this.vertexColor = vertexColor;
    }*/

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
    private ArrayList<ArrayList<Vertex>>adjList = new ArrayList<>(vertexCount);
    private final boolean[] visited;
    private final int[] distance;
    private final int[] predecessor;

    private Set<Integer> vertices = new LinkedHashSet<>();

    private ArrayList<Integer> vertexList = new ArrayList<>();

    private ArrayList<Edge> edgeArrayList = new ArrayList<>();

    Graph(){
        visited = new boolean[vertexCount+1];
        distance = new int[vertexCount+1];
        predecessor = new int[vertexCount+1];
    }

    Graph(int vertexCount){
        this.vertexCount = vertexCount;
        for(int i =0;i<vertexCount+1;i++){
            adjList.add(new ArrayList<>());
        }
        visited = new boolean[vertexCount+1];
        distance = new int[vertexCount+1];
        predecessor = new int[vertexCount+1];
    }

    Graph(int vertexCount, Set<Integer> vertices){
        this.vertexCount = vertexCount;
        for(int i =0;i<vertexCount+1;i++){
            adjList.add(new ArrayList<>());
        }
        visited = new boolean[vertexCount+1];
        distance = new int[vertexCount+1];
        predecessor = new int[vertexCount+1];
        this.vertices = vertices;
        vertexList.addAll(vertices);
    }

    public void setEdgeArrayList(ArrayList<Edge> edgeArrayList){
        this.edgeArrayList = edgeArrayList;
    }

    public ArrayList<Edge> getEdgeArrayList(){
        return this.edgeArrayList;
    }

    public void setAdjList(ArrayList<ArrayList<Vertex>> adjList){
        this.adjList = adjList;
    }

    public ArrayList<ArrayList<Vertex>> getAdjList(){
        return this.adjList;
    }

    public void setVertexList(ArrayList<Integer> vertexList){
        this.vertexList = vertexList;
    }

    public void addEdgeUndirected(Vertex u, Vertex v){
        adjList.get(u.getVertexIndex()).add(v);
        adjList.get(v.getVertexIndex()).add(u);
    }

    public void addEdgeDirected(Vertex u, Vertex v){
        adjList.get(u.getVertexIndex()).add(v);
    }

    public void singleSourceShortestPath(int source, int destination, boolean isDijkstra){
        Comparator<Vertex> vertexComparator = Comparator.comparingInt(Vertex::getVertexDistance);
        int[][] graphMatrix = new int[vertexCount+1][vertexCount+1];
        for (Edge value : edgeArrayList) {
            int start = value.getStartVertex();
            int end = value.getEndVertex();
            int weight = value.getWeight();
            graphMatrix[start][end] = weight;
        }
        ArrayList<Edge> outputEdges = new ArrayList<>();
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        Arrays.fill(predecessor, -1);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(vertexComparator);
        int currentVertex = source;
        if(isDijkstra){
            distance[source] = 0;
            priorityQueue.add(new Vertex(currentVertex, 0));
            while(priorityQueue.size()!=0){
                Vertex u = priorityQueue.poll();
                assert u != null;
                for(int i =0;i< adjList.get(u.getVertexIndex()).size();i++){
                    Vertex v = adjList.get(u.getVertexIndex()).get(i);
                    if(distance[u.getVertexIndex()] + graphMatrix[u.getVertexIndex()][v.getVertexIndex()] < distance[v.getVertexIndex()]){
                        distance[v.getVertexIndex()] = distance[u.getVertexIndex()] + graphMatrix[u.getVertexIndex()][v.getVertexIndex()];
                        predecessor[v.getVertexIndex()] = u.getVertexIndex();
                        priorityQueue.remove(v);
                        priorityQueue.add(v);
                    }
                }
            }
            int shortestPathDistance = distance[destination];
            System.out.println("Shortest path cost: " + shortestPathDistance);
            int i =destination;
            ArrayList<Integer> shortestPath = new ArrayList<>();
            while(predecessor[i]!=-1){
                shortestPath.add(i);
                i = predecessor[i];
            }
            System.out.print(source);
            for(i =shortestPath.size()-1;i>=0;i--){
                System.out.print(" -> " + shortestPath.get(i));
            }
        }
        else{
            distance[source] = 0;
            boolean change;
            boolean negativeCycle = false;
            int[] count = new int[vertexCount+1];
            for(int i =0;i<vertexCount-1;i++){
                change = false;
                Queue<Vertex> vertexQueue = new LinkedList<>();
                vertexQueue.add(new Vertex(source, 0));
                while(vertexQueue.size()!=0){
                    Vertex u = vertexQueue.remove();
                    assert u!= null;
                    for(int j = 0;j<adjList.get(u.getVertexIndex()).size();j++){
                        Vertex v = adjList.get(u.getVertexIndex()).get(j);
                        if(distance[u.getVertexIndex()] + graphMatrix[u.getVertexIndex()][v.getVertexIndex()] < distance[v.getVertexIndex()]){
                            distance[v.getVertexIndex()] = distance[u.getVertexIndex()] + graphMatrix[u.getVertexIndex()][v.getVertexIndex()];
                            predecessor[v.getVertexIndex()] = u.getVertexIndex();
                            vertexQueue.remove(v);
                            vertexQueue.add(v);
                            if(!change){
                                change = true;
                            }
                        }
                    }
                    count[u.getVertexIndex()]++;
                    if(count[u.getVertexIndex()] > (vertexCount*(vertexCount-1))/2){
                        negativeCycle = true;
                        break;
                    }
                }
                if(!change){
                    break;
                }
            }
            if(negativeCycle){
                System.out.println("The graph contains a negative cycle");
            }
            else{
                System.out.println("The graph does not contain a negative cycle");
                int shortestPathDistance = distance[destination];
                System.out.println("Shortest path cost: " + shortestPathDistance);
                ArrayList<Integer> shortestPath = new ArrayList<>();
                int i = destination;
                while(predecessor[i]!=-1){
                    shortestPath.add(i);
                    i = predecessor[i];
                }
                System.out.print(source);
                for(int k=shortestPath.size()-1;k>=0;k--){
                    System.out.print(" -> " + shortestPath.get(k));
                }
            }
        }
    }

    private long[][] APSP_setGraphMatrix(){
        long[][] graphMatrix = new long[vertexCount+1][vertexCount+1];
        for(int i =0;i<vertexCount+1;i++){
            for(int j =0;j<vertexCount+1;j++){
                if(i==j){
                    graphMatrix[i][j] = 0;
                }
                else{
                    graphMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for(Edge edge: edgeArrayList){
            int start = edge.getStartVertex();
            int end = edge.getEndVertex();
            int weight = edge.getWeight();
            graphMatrix[start][end] = weight;
        }
        Collections.sort(vertexList);
        return graphMatrix;
    }
    public void APSP_FloydWarshall(){
        long[][] graphMatrix = APSP_setGraphMatrix();
        for(Integer integer: vertexList){
            int vertex = integer;
            for(int i = vertexList.get(0); i<=vertexCount; i++){
                for(int j = vertexList.get(0); j<=vertexCount; j++){
                    if(i != j){
                        graphMatrix[i][j] = Math.min(graphMatrix[i][j], graphMatrix[i][vertex] + graphMatrix[vertex][j]);
                    }
                }
            }
        }
        APSP_Output(graphMatrix);
    }

    public void APSP_MatrixChainMultiplication(){
        long[][] graphMatrix = APSP_setGraphMatrix();
        for(Integer integer: vertexList){
            for(int i =0;i<vertexCount+1;i++){
                for(int j =0;j<vertexCount+1;j++){
                    for(int k =0;k<vertexCount+1;k++){
                        graphMatrix[i][j] = Math.min(graphMatrix[i][j], graphMatrix[i][k] + graphMatrix[k][j]);
                    }
                }
            }
        }
        APSP_Output(graphMatrix);
    }

    private void APSP_Output(long[][] graphMatrix){
        for(int i =1;i<=vertexCount;i++){
            for(int j =1;j<=vertexCount;j++){
                if(graphMatrix[i][j] == Integer.MAX_VALUE){
                    System.out.print("INF ");
                }
                else{
                    System.out.print(graphMatrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void outputGraph(){
        for(int i =0;i<vertexCount;i++){
            System.out.println(i + "-> " + adjList.get(i));
        }
    }
}