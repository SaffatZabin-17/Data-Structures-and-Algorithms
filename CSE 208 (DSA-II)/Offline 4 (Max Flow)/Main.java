import java.util.*;

public class Main {

    static int factorial(int parameter){
        int answer = 1;
        for(int i =parameter; i>=1;i--){
            answer *= i;
        }
        return answer;
    }
    static int combination(int x, int y){
        int numerator = factorial(x);
        int denominator = factorial(y) * factorial(x-y);
        return numerator/denominator;
    }

    static int[][] createAdjMatrix(int[][] gameMatrix, int matrixSize, int discardedIndex, int[] winArray, int[] leftArray, int vertexCount){
        int[][] adjMatrix = new int[matrixSize][matrixSize];
        int m =1;
        for(int i =0;i< gameMatrix.length;i++){
            if(i!= discardedIndex){
                for(int j =i+1;j< gameMatrix.length;j++){
                    if(j!= discardedIndex){
                        adjMatrix[0][m] = gameMatrix[i][j];
                        m++;
                    }
                }
            }
        }
        int increment = 0, index = 1;
        int count = vertexCount-1;
        while(true){
            count--;
            if(count == 0){
                break;
            }
            int tempCount = count;
            int start = combination(vertexCount-1,2)+2 + increment;
            while(tempCount > 0){
                adjMatrix[index][start] = Integer.MAX_VALUE;
                start++;
                index++;
                tempCount--;
            }
            increment++;
        }
        count = vertexCount-1;
        increment = 0;
        index = 1;
        while(true){
            count--;
            if(count == 0){
                break;
            }
            int tempCount = count;
            int start = combination(vertexCount-1,2)+1+increment;
            while(tempCount>0){
                adjMatrix[index][start] = Integer.MAX_VALUE;
                index++;
                tempCount--;
            }
            increment++;
        }
        index = combination(vertexCount-1, 2)+1;
        for(int i =0;i<vertexCount;i++){
            if(i!=discardedIndex){
                adjMatrix[index][matrixSize-1] = winArray[discardedIndex] + leftArray[discardedIndex] - winArray[i];
                index++;
            }
        }
        return adjMatrix;
    }

    public static void main(String[] args) {
        int vertexCount = 0, edgeCount = 0;
        Scanner sc = new Scanner(System.in);
        vertexCount = sc.nextInt();
        /*edgeCount = sc.nextInt();
        Graph graph = new Graph(vertexCount);
        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        Set<Integer> vertexSet = new LinkedHashSet<>();
        int startVertex, endVertex, weight;
        *//*for(int i =0;i<edgeCount;i++){
            startVertex = sc.nextInt();
            endVertex = sc.nextInt();
            graph.addEdgeDirected(new Vertex(startVertex, Integer.MAX_VALUE), new Vertex(endVertex, Integer.MAX_VALUE));
            weight = sc.nextInt();
            edgeArrayList.add(new Edge(startVertex, endVertex, weight));
            vertexSet.add(startVertex);
            vertexSet.add(endVertex);
        }
        int source, destination;
        source = sc.nextInt();
        destination = sc.nextInt();
        ArrayList<Integer> vertexList = new ArrayList<>(vertexSet);
        graph.setEdgeArrayList(edgeArrayList);
        graph.setVertexList(vertexList);
        graph.setAdjMatrix(edgeArrayList);
        System.out.println(graph.maxFlow_Edmonds_Karp(source, destination));*/
        Map <Integer, String> teamMap1 = new LinkedHashMap<>();
        Map<String, Integer> teamMap2 = new LinkedHashMap<>();
        String teamName;
        int[] winArray = new int[vertexCount];
        int[] lossArray = new int[vertexCount];
        int[] leftArray = new int[vertexCount];
        String[] teams = new String[vertexCount];
        int[][] gameMatrix = new int[vertexCount][vertexCount];
        for(int i =0;i<vertexCount;i++){
            teamName = sc.next();
            teams[i] = teamName;
            teamMap1.put(i, teamName);
            teamMap2.put(teamName, i);
            int wins = sc.nextInt();
            int losses = sc.nextInt();
            int left = sc.nextInt();
            winArray[i] = wins;
            lossArray[i] = losses;
            leftArray[i] = left;
            for(int j =0;j<vertexCount;j++){
                int games = sc.nextInt();
                gameMatrix[i][j] = games;
            }
        }
        edgeCount = combination(vertexCount-1,2) + 2*combination(vertexCount-1,2) + 3;
        int matrixSize = combination(vertexCount-1, 2) + (vertexCount-1) + 2;
        int[][] adjMatrix;
        ArrayList<Edge> edgeArrayList = new ArrayList<>(edgeCount);
        Graph graph = new Graph(matrixSize);
        for(int i =0;i<teams.length;i++){
            edgeArrayList.clear();
            adjMatrix = createAdjMatrix(gameMatrix, matrixSize, i, winArray, leftArray, vertexCount);
            for(int j =0;j<matrixSize;j++){
                for(int k =0;k<matrixSize;k++){
                    edgeArrayList.add(new Edge(j, k, adjMatrix[j][k]));
                }
            }
            graph.setAdjMatrix(edgeArrayList);
            int maxFlow = graph.maxFlow_Edmonds_Karp(0, matrixSize-1);
            int totalCapacity = 0;
            for(int j =0;j<matrixSize;j++){
                totalCapacity+= adjMatrix[0][j];
            }
            boolean[] visited;
            ArrayList<Integer> visitedTeams = new ArrayList<>();
            ArrayList<String> visitedTeamsName = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();
            if(maxFlow!=totalCapacity){
                System.out.println(teamMap1.get(i) + " is eliminated");
                System.out.println("They can win at most " + winArray[i] + " + " + leftArray[i] + " = " + (winArray[i] + leftArray[i]) + " games");
                if(winArray[i] + leftArray[i] < winArray[0]){
                    System.out.println(teams[0] + " has already won a total of " + winArray[0] + " games");
                    System.out.println("They play each other 0 times");
                    System.out.println("So, on average each of the teams in this group wins " + winArray[0] + "/1" + " = " + winArray[0] + " games");
                }
                else{
                    visited = graph.getVisited();
                    for(int j =0;j< teams.length;j++){
                        if(j!=i){
                            visitedTeamsName.add(teamMap1.get(j));
                        }
                    }
                    int index = combination(vertexCount-1,2)+1;
                    for(int j = index;j<= index+vertexCount-2; j++){
                        if(visited[j]){
                            visitedTeams.add(j);
                        }
                    }
                    int size = visitedTeams.size();

                    for(Integer integer: visitedTeams){
                        if(size == 1){
                            if(integer-vertexCount>=i){
                                System.out.print(teamMap1.get(integer-vertexCount+1));
                                names.add(teamMap1.get(integer-vertexCount+1));
                            }
                        }
                        else{
                            System.out.print(teamMap1.get(integer-vertexCount) + " and ");
                            names.add(teamMap1.get(integer-vertexCount));
                            size--;
                        }
                    }
                    System.out.print(" have won a total of ");
                    int total = 0, timesPlayed = 0;
                    for(Integer integer: visitedTeams){
                        if(integer-vertexCount>=i){
                            total+=winArray[integer-vertexCount+1];
                        }
                        else{
                            total+=winArray[integer-vertexCount];
                        }
                    }
                    System.out.println(total + " games");
                    int m = 0;
                    System.out.println("They play each other " + gameMatrix[teamMap2.get(names.get(m))][teamMap2.get(names.get(m+1))] + " times");
                    System.out.println("So on average each of the team wins " + (total+gameMatrix[teamMap2.get(names.get(m))][teamMap2.get(names.get(m+1))]) + "/2 = " + (total+gameMatrix[teamMap2.get(names.get(m))][teamMap2.get(names.get(m+1))])/2 + ".5 games");
                }
                System.out.println();
            }
        }
    }
}

/*
4 5
1 2 2
1 3 1
2 3 3
2 4 1
3 4 2
1 4
 */

/*
6 9
1 2 16
1 3 13
2 4 12
3 2 4
3 5 14
4 3 9
4 6 20
5 4 7
5 6 4
1 6
 */
/*
4
Atlanta 83 71 8 0 1 6 1
Philadelphia 80 79 3 1 0 0 2
NewYork 78 78 6 6 0 0 0
Montreal 77 82 3 1 2 0 0
 */
/*
Montreal:
7 12
0 1 1
0 2 6
0 3 0
1 4 1000000
1 5 1000000
2 4 1000000
2 6 1000000
3 5 1000000
3 6 1000000
4 7 -3
5 7 0
6 7 2
0 7
 */

/*
Atlanta:
8 12
0 1 0
0 2 2
0 3 0
1 4 1000000
1 5 1000000
2 4 1000000
2 6 1000000
3 5 1000000
3 6 1000000
4 7 11
5 7 13
6 7 14
0 7
 */
/*
Philly:
8 12
0 1 6
0 2 1
0 3 0
1 4 1000000
1 5 1000000
2 4 1000000
2 6 1000000
3 5 1000000
3 6 1000000
4 7 0
5 7 5
6 7 6
0 7
 */
/*
NewYork
8 12
0 1 1
0 2 1
0 3 2
1 4 1000000
1 5 1000000
2 4 1000000
2 6 1000000
3 5 1000000
3 6 1000000
4 7 1
5 7 4
6 7 7
0 7
 */