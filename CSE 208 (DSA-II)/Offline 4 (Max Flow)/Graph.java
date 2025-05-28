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

    private int[][] adjMatrix;

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
        adjMatrix = new int[vertexCount+1][vertexCount+1];
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
        adjMatrix = new int[vertexCount+1][vertexCount+1];
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

    public void setAdjMatrix(int[][] adjMatrix){
        this.adjMatrix = adjMatrix;
    }

    public boolean[] getVisited(){
        return this.visited;
    }

    public void setAdjMatrix(ArrayList<Edge>edgeArrayList){
        for(Edge edge: edgeArrayList){
            int start = edge.getStartVertex();
            int end = edge.getEndVertex();
            int weight = edge.getWeight();
            adjMatrix[start][end] = weight;
        }
    }

    public void addEdgeUndirected(Vertex u, Vertex v){
        adjList.get(u.getVertexIndex()).add(v);
        adjList.get(v.getVertexIndex()).add(u);
    }

    public void addEdgeDirected(Vertex u, Vertex v){
        adjList.get(u.getVertexIndex()).add(v);
    }

    public boolean BFS(ArrayList<ArrayList<Vertex>> adjList,int source, int destination, boolean print){
        LinkedList<Vertex> vertexQueue = new LinkedList<>();
        Arrays.fill(visited, false);
        Arrays.fill(distance, -1);
        Arrays.fill(predecessor, -1);
        visited[source] = true;
        distance[source] = 0;
        predecessor[source] = -2;
        vertexQueue.add(new Vertex(source, 0));
        while(!vertexQueue.isEmpty()){
            Vertex temp = vertexQueue.remove();
            for(int i =0;i<adjList.get(temp.getVertexIndex()).size();i++){
                if(!visited[adjList.get(temp.getVertexIndex()).get(i).getVertexIndex()]){
                    visited[adjList.get(temp.getVertexIndex()).get(i).getVertexIndex()] = true;
                    distance[adjList.get(temp.getVertexIndex()).get(i).getVertexIndex()] = distance[temp.getVertexIndex()]+1;
                    predecessor[adjList.get(temp.getVertexIndex()).get(i).getVertexIndex()] = temp.getVertexIndex();
                    vertexQueue.add(adjList.get(temp.getVertexIndex()).get(i));
                }
            }
        }
        //System.out.println();
//        for(int i =0;i<vertexCount;i++){
//            System.out.println("Vertex: " + (i));
//            System.out.println("Distance from source vertex: " + distance[i]);
//            System.out.println("Predecessor of the vertex: " + predecessor[i]);
//            System.out.println("Vertex has been visited: " + visited[i]);
//            System.out.println();
//        }
        if(print){
            ArrayList<Integer> singleSourceShortestPath = new ArrayList<>();
            int temp = destination;
            singleSourceShortestPath.add(temp);
            while(predecessor[temp]!=-2){
                temp = predecessor[temp];
                singleSourceShortestPath.add(temp);
            }
            for(int i = singleSourceShortestPath.size()-1;i>=0;i--){
                System.out.print(singleSourceShortestPath.get(i) + " ");
            }
            System.out.println();
            System.out.println("Length of the shortest path is: " + (singleSourceShortestPath.size()-1));
        }
        Arrays.fill(visited, false);
        Arrays.fill(distance, -1);
        Arrays.fill(predecessor, -1);
        return true;
    }

    public boolean BFS(int[][] graphMatrix, int source, int destination){
        Queue<Vertex> vertexQueue = new LinkedList<>();
        Arrays.fill(distance, -1);
        Arrays.fill(predecessor, -1);
        Arrays.fill(visited, false);
        visited[source] = true;
        distance[source] = 0;
        predecessor[source] = -2;
        vertexQueue.add(new Vertex(source, 0));
        while(vertexQueue.size()!=0){
            Vertex temp = vertexQueue.remove();
            for(int i = 0;i<vertexCount+1;i++){
                if(!visited[i] && graphMatrix[temp.getVertexIndex()][i] > 0){
                    if(i == destination){
                        predecessor[i] = temp.getVertexIndex();
                        return true;
                    }
                    visited[i] = true;
                    distance[i] = distance[temp.getVertexIndex()] + 1;
                    predecessor[i] = temp.getVertexIndex();
                    vertexQueue.add(new Vertex(i, distance[i]));
                }
            }
        }
        return false;
    }

    public void DFS(int source, boolean print){
        if(print){
            visited[source] = true;
            for(int i =0;i<adjList.get(source).size();i++){
                int temp = adjList.get(source).get(i).getVertexIndex();
                if(!visited[temp]){
                    DFS(temp, true);
                }
            }
        }
        else{
            visited[source] = true;
            for(int i =0;i<adjList.get(source).size();i++){
                int temp = adjList.get(source).get(i).getVertexIndex();
                if(!visited[temp]){
                    DFS(temp, false);
                }
            }
        }
    }

    public void singleSourceShortestPath(int source, int destination, boolean isDijkstra){
        Arrays.fill(visited, false);
        Arrays.fill(distance, -1);
        Arrays.fill(predecessor, -1);
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
        for(int i =0;i<=vertexCount;i++){
            for(int j =0;j<=vertexCount;j++){
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
            if(graphMatrix[start][end]!=Integer.MAX_VALUE || graphMatrix[start][end]!=0){
                graphMatrix[start][end] = Math.min(graphMatrix[start][end], weight);
            }
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

    public int maxFlow_Edmonds_Karp(int source, int destination){
        int[][] residueMatrix = adjMatrix;
        int maxFlow = 0;
        while(BFS(residueMatrix, source, destination)){
            int minimumPathFlow = Integer.MAX_VALUE;
            for(int i = destination; i!=source; i = predecessor[i]){
                int u = predecessor[i];
                minimumPathFlow = Math.min(minimumPathFlow, residueMatrix[u][i]);
            }
            for(int i = destination; i!=source; i = predecessor[i]){
                int u = predecessor[i];
                residueMatrix[u][i] -= minimumPathFlow;
                residueMatrix[i][u] += minimumPathFlow;
            }
            maxFlow += minimumPathFlow;
        }
        return maxFlow;
    }

    public void minCut_Edmonds_Karp(int source, int destination){
        int[][] residueMatrix = adjMatrix;
        while(BFS(residueMatrix, source, destination)){
            int minimumPathFlow = Integer.MAX_VALUE;
            for(int i = destination; i!=source; i = predecessor[i]){
                int u = predecessor[i];
                minimumPathFlow = Math.min(minimumPathFlow, residueMatrix[u][i]);
            }
            for(int i = destination; i!=source; i = predecessor[i]){
                int u = predecessor[i];
                residueMatrix[u][i] -= minimumPathFlow;
                residueMatrix[i][u] += minimumPathFlow;
            }
        }
        Arrays.fill(visited, false);
        BFS(residueMatrix, source, destination);
        System.out.println(Arrays.toString(visited));
        for(int i =0;i<adjMatrix.length;i++){
            for(int j =0;j< adjMatrix.length;j++){
                if(residueMatrix[i][j] > 0 && visited[i] && !visited[j]){
                    System.out.println(i + " - " + j);
                }
            }
        }
        System.out.println();
    }

    public void outputGraph(){
        for(int i =0;i<vertexCount;i++){
            System.out.println(i + "-> " + adjList.get(i));
        }
    }

    /*

    public int motherVertex(){
        Arrays.fill(visited, false);
        int motherVertex = -1;
        for(Integer integer: vertices){
            if(!visited[integer]){
                DFS(integer, false);
                motherVertex = integer;
            }
        }
        Arrays.fill(visited, false);
        DFS(motherVertex, false);
        for(Integer integer: vertices){
            if(!visited[integer]){
                return -1;
            }
        }
        return motherVertex;
    }
    public boolean isCyclic(int source) {
        int[] parent = new int[vertexCount + 1];
        Arrays.fill(visited, false);
        Arrays.fill(parent, -1);
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int i = 0; i < adjList.get(vertex).size(); i++) {
                int temp = adjList.get(vertex).get(i);
                if (!visited[temp]) {
                    visited[temp] = true;
                    queue.add(temp);
                    parent[temp] = vertex;
                } else if (parent[vertex] != temp) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean isBipartite(int source){
        int[] color = new int[vertexCount+1];
        Arrays.fill(color, -1);
        Queue<Vertex> pairQueue = new LinkedList<>();
        if(color[source] == -1){
            pairQueue.add(new Vertex(source, 0));
            color[source] = 0;
        }
        while(!pairQueue.isEmpty()){
            Vertex pair = pairQueue.poll();
            int currVertex = pair.getVertexIndex();
            int currColor = pair.getVertexColor();
            for(int i =0;i<adjList.get(currVertex).size();i++){
                if(color[i] == currColor){
                    return false;
                }
                else if(color[i] == -1){
                    if(currColor == 0){
                        color[i] = 1;
                    }
                    else{
                        color[i] = 0;
                    }
                    pairQueue.add(new Vertex(i, color[i]));
                }
            }
        }
        return true;
    }

    public void topologicalSorting(){
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(visited, false);
        for(Integer integer: vertices){
            if(!visited[integer]){
                topologicalSortHelper(integer, stack);
            }
        }
        if(stack.isEmpty()){
            System.out.println("Not possible");
        }
        else{
            while(!stack.isEmpty()){
                System.out.print(stack.pop() + " ");
            }
        }
    }

    private void topologicalSortHelper(int source, Stack<Integer> stack){
        visited[source] = true;
        for (Integer i : adjList.get(source)) {
            if (!visited[i]) {
                topologicalSortHelper(i, stack);
            }
        }
        stack.push(source);
    }

    *//*public void outputGraph(){
        for(int i =0;i< adjList.size();i++){
            System.out.println(i + "-> " + adjList.get(i));
        }
    }*/
}