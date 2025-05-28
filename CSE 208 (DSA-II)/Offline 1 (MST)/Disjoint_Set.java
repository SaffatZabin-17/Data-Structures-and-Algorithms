import java.util.Arrays;

public class Disjoint_Set {

    int[] parent, rank;
    int vertexCount;

    Disjoint_Set(int vertexCount){
        this.vertexCount = vertexCount;
        parent = new int[vertexCount+1];
        rank = new int[vertexCount+1];
    }

    public void makeSet(){
        Arrays.fill(parent, -1);
        Arrays.fill(rank, 0);
    }

    public int findRoot(int element){

        if(parent[element] == -1){
            return element;
        }
        else{
            return parent[element] = findRoot(parent[element]);  //Path Compression
        }
    }

    public void union(int startVertex, int endVertex){

        if(rank[startVertex] > rank[endVertex]){
            parent[findRoot(endVertex)] = startVertex;
        }
        else if(rank[startVertex] < rank[endVertex]){
            parent[findRoot(startVertex)] = endVertex;
        }
        else{
            parent[findRoot(endVertex)] = startVertex;
            rank[endVertex] += 1;
        }
    }
}
