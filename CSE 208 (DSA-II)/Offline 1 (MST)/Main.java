import java.io.File;
import java.util.*;

public class Main {
    int vertexCount = 0, edgeCount = 0;
    ArrayList<Edge> edgesKruskal = new ArrayList<>();
    ArrayList<Edge> edgesPrim = new ArrayList<>();

    public void setVertexCount(int vertexCount){
        this.vertexCount = vertexCount;
    }
    public int getVertexCount(){
        return this.vertexCount;
    }
    public void setEdgeCount(int edgeCount){
        this.edgeCount = edgeCount;
    }
    public int getEdgeCount(){
        return this.edgeCount;
    }
    public void setEdges(ArrayList<Edge> edges){
        this.edgesKruskal = edges;
        this.edgesPrim = edges;
    }
    public ArrayList<Edge> getEdgesKruskal(){
        return this.edgesKruskal;
    }
    public ArrayList<Edge> getEdgesPrim(){
        return this.edgesPrim;
    }

    public static void main(String[] args){
        FileOperations fp = new FileOperations();
        Main main = fp.readFromFile();
        int vertexCount = 0, edgeCount = 0;
        vertexCount = main.getVertexCount();
        edgeCount = main.getEdgeCount();
        ArrayList<Edge> edgesKruskal = main.getEdgesKruskal();
        ArrayList<Edge> edgesPrim = main.getEdgesPrim();
        Set<Integer> initialVertexSet = new LinkedHashSet<>();
        for(Edge edge: edgesPrim){
            int start = edge.getStartVertex();
            int end = edge.getEndVertex();
            initialVertexSet.add(start);
            initialVertexSet.add(end);
        }
        ArrayList<Integer> initialVertexList = new ArrayList<>();
        initialVertexList.addAll(initialVertexSet);
        MST minimumSpanningTree = new MST(initialVertexSet, edgesKruskal);
        minimumSpanningTree.MST_Prim(initialVertexList.get(0));
        System.out.println("Cost of Minimum Spanning Tree: " + (double)minimumSpanningTree.getTotalWeight());
        System.out.print("List of Edges selected using Prim's: ");
        minimumSpanningTree.print();
        minimumSpanningTree.MST_Kruskal();
        System.out.print("List of Edges selected using Kruskal's: ");
        minimumSpanningTree.print();
    }
}
