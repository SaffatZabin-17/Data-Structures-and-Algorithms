import java.util.*;

public class Main {
    public static void main(String[] args) {
        int vertexCount = 0, edgeCount  = 0;
        Scanner sc = new Scanner(System.in);
        vertexCount = sc.nextInt();
        edgeCount = sc.nextInt();
        Graph graph = new Graph(vertexCount);
        int[] weightArray = new int[edgeCount];
        boolean dijkstra = true;
        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        Set<Integer> vertexSet = new LinkedHashSet<>();
        int startVertex, endVertex, weight;
        for(int i =0;i<edgeCount;i++){
            startVertex = sc.nextInt();
            endVertex = sc.nextInt();
            graph.addEdgeDirected(new Vertex(startVertex, Integer.MAX_VALUE), new Vertex(endVertex, Integer.MAX_VALUE));
            weight = sc.nextInt();
            weightArray[i] = weight;
            edgeArrayList.add(new Edge(startVertex, endVertex, weight));
            vertexSet.add(startVertex);
            vertexSet.add(endVertex);
        }
        int source = sc.nextInt();
        int destination = sc.nextInt();
        graph.setEdgeArrayList(edgeArrayList);
        for(Integer integer: weightArray){
            if(integer < 0){
                dijkstra = false;
                break;
            }
        }
        graph.shortestPath(source, destination, dijkstra);
    }
}

/*
8 14
0 1 4
0 4 8
1 2 8
2 3 7
2 6 4
3 7 9
4 8 7
4 1 11
4 5 1
5 6 2
6 3 14
6 7 10
8 2 2
8 5 6
0 7
 */
/*
5 7
0 1 10
0 2 5
1 3 1
2 1 3
2 4 2
2 3 9
4 3 6
0 3
 */
/*
5 8
0 1 6
0 2 7
1 4 5
1 3 -4
2 3 9
3 4 7
3 0 2
4 1 -2
0 4
 */
/*
9 17
0 7 60
7 1 -150
4 8 -70
6 4 80
5 1 4000
8 0 100000
2 3 -200
8 2 1000
0 3 300
3 8 50000
3 7 -200
2 5 120
6 3 1500
4 0 90
5 7 -50
1 6 100
4 1 -90
0 5
 */
/*
4 4
0 1 1
1 2 -1
2 3 -1
3 0 -1
0 3
 */