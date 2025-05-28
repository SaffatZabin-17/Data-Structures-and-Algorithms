import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

class Vertex implements Comparable{
    int key, predecessor, vertexElement;

    Vertex(int vertexElement, int predecessor, int key){
        this.key = key;
        this.predecessor = predecessor;
        this.vertexElement = vertexElement;
    }

    Vertex(int vertexElement, int predecessor){
        this.vertexElement = vertexElement;
        this.predecessor = predecessor;
    }

    public void setKey(int key){
        this.key = key;
    }

    public int getKey(){
        return this.key;
    }

    public void setPredecessor(int predecessor){
        this.predecessor = predecessor;
    }

    public int getPredecessor(){
        return this.predecessor;
    }

    public void setVertexElement(int vertexElement){
        this.vertexElement = vertexElement;
    }

    public int getVertexElement(){
        return this.vertexElement;
    }

    @Override
    public String toString(){
        return "[" + this.vertexElement + " " + this.predecessor + " " + this.key + "]";
    }

    @Override
    public int compareTo(Object o) {
        Vertex vertex = (Vertex) o;
        int key = vertex.getKey();
        return this.getKey() - key;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Vertex)){
            return false;
        }
        else return ((Vertex) obj).getKey() == this.getKey();
    }
}

class vertexComparator implements Comparator<Vertex> {

    @Override
    public int compare(Vertex o1, Vertex o2) {
        if(o1.key > o2.key){
            return 1;
        }
        else if(o1.key < o2.key){
            return -1;
        }
        else{
            return 0;
        }
    }
}

class Edge implements Comparable{
    int startVertex, endVertex;
    double weight;

    Edge(){
        this.startVertex = 0;
        this.endVertex = 0;
        this.weight = 0;
    }
    Edge(int startVertex, int endVertex, double weight){
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    Edge(int startVertex, int endVertex){
        this.startVertex = startVertex;
        this.endVertex = endVertex;
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

    public void setWeight(double weight){
        this.weight = weight;
    }
    public double getWeight(){
        return this.weight;
    }

    @Override

    public int compareTo(Object o) {
        Edge edge = (Edge) o;
        double weight = ((Edge) o).getWeight();
        double val = this.getWeight()-weight;
        return (int) val;
    }

    @Override

    public String toString(){
        return this.getStartVertex() + " " + this.getEndVertex() + " " + this.getWeight();
    }
}
public class MST {

    private final ArrayList<Edge> MSTEdgePrim;
    private final ArrayList<Edge> MSTEdgeKruskal;
    Set<Integer> vertexSet;
    private int totalWeight = 0;

    int vertexCount;

    private ArrayList<Edge> edgeArrayList = new ArrayList<>();

    ArrayList<ArrayList<Vertex>> adjList;

    MST(Set<Integer> vertexSet, ArrayList<Edge> MSTEdgeList){
        this.vertexSet = vertexSet;
        this.MSTEdgePrim = MSTEdgeList;
        this.MSTEdgeKruskal = MSTEdgeList;
        this.vertexCount = vertexSet.size();
        this.adjList = new ArrayList<>(vertexCount+1);
        for(int i =0;i<vertexCount+1;i++){
            adjList.add(new ArrayList<>());
        }
    }

    public int getTotalWeight(){
        return this.totalWeight;
    }

    public void MST_Kruskal(){
        edgeArrayList.clear();
        Collections.sort(MSTEdgeKruskal);
        Disjoint_Set disjointSet = new Disjoint_Set(vertexSet.size());
        disjointSet.makeSet();
        for(Edge edge: MSTEdgeKruskal){
            if(disjointSet.findRoot(edge.getStartVertex())!= disjointSet.findRoot(edge.getEndVertex())){
                edgeArrayList.add(edge);
                disjointSet.union(edge.getStartVertex(), edge.getEndVertex());
            }
        }
        for(Edge edge: edgeArrayList){
            this.totalWeight += edge.getWeight();
        }
    }

    public void MST_Prim(int source){
        
        edgeArrayList.clear();
        Comparator<Vertex> vertexComparator = new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return (int) (o1.getKey()-o2.getKey());
            }
        };
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(vertexComparator);
        double[][] graphMatrix = new double[vertexCount+1][vertexCount+1];
        boolean[] visited = new boolean[vertexCount+1];
        adjList = new ArrayList<>();
        for(int i =0;i<vertexCount+1;i++){
            adjList.add(new ArrayList<>());
        }
        for(int i =0;i< MSTEdgePrim.size();i++){
            int startVertex = MSTEdgePrim.get(i).getStartVertex();
            int endVertex = MSTEdgePrim.get(i).getEndVertex();
            double weight = MSTEdgePrim.get(i).getWeight();
            graphMatrix[startVertex][endVertex] = weight;
            graphMatrix[endVertex][startVertex] = weight;
            Vertex start = new Vertex(startVertex, -1, -1);
            Vertex end = new Vertex(endVertex, -1, -1);
            addEdgeUndirected(start, end);
        }
        ArrayList<Vertex> outputSet = new ArrayList<>();
        priorityQueue.add(new Vertex(source, -1, 0));
        while(priorityQueue.size()!=0){
            Vertex u = priorityQueue.poll();
            if(!visited[u.getVertexElement()]){
                outputSet.add(u);
            }
            visited[u.getVertexElement()] = true;
            for(int i =0;i<adjList.get(u.getVertexElement()).size();i++){
                Vertex v = adjList.get(u.getVertexElement()).get(i);
                if(!visited[v.getVertexElement()]){
                    priorityQueue.add(new Vertex(v.getVertexElement(), u.getVertexElement(), (int) graphMatrix[u.getVertexElement()][v.getVertexElement()]));
                }
            }
        }
        totalWeight = 0;
        for(Vertex vertex:outputSet){
            totalWeight += vertex.getKey();
        }
        for(int i = 1;i<outputSet.size();i++){
            Edge edge = new Edge(outputSet.get(i).getPredecessor(), outputSet.get(i).getVertexElement());
            edgeArrayList.add(edge);
        }
    }

    private void addEdgeUndirected(Vertex u, Vertex v){
        adjList.get(u.getVertexElement()).add(v);
        adjList.get(v.getVertexElement()).add(u);
    }

    public void print(){
        System.out.print("{");
        for(int i = 0; i< edgeArrayList.size(); i++){
            if(i == edgeArrayList.size()-1){
                System.out.print("(" + edgeArrayList.get(i).getStartVertex() + "," + edgeArrayList.get(i).getEndVertex() + ")}");
            }
            else{
                System.out.print("(" + edgeArrayList.get(i).getStartVertex() + "," + edgeArrayList.get(i).getEndVertex() + "),");
            }
        }
        System.out.println();
        edgeArrayList = new ArrayList<>();
    }
}
