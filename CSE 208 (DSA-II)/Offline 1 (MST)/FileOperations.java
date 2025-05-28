import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileOperations {
    public final String INPUT_FILE_NAME = "MST.txt";

    public Main readFromFile(){
        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        Main main = new Main();
        try{
            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            String line;
            int i = 0;
            while(true){
                line = br.readLine();
                if(line==null){
                    break;
                }
                else{
                    String[] tokens = line.split(" ");
                    if(i == 0){
                        main.setVertexCount(Integer.parseInt(tokens[0]));
                        main.setEdgeCount(Integer.parseInt(tokens[1]));
                        i++;
                    }
                    else{
                        Edge edge = new Edge();
                        edge.setStartVertex(Integer.parseInt(tokens[0]));
                        edge.setEndVertex(Integer.parseInt(tokens[1]));
                        edge.setWeight(Double.parseDouble(tokens[2]));
                        edgeArrayList.add(edge);
                    }
                }
            }
            main.setEdges(edgeArrayList);
        } catch (Exception e){
            e.printStackTrace();
        }
        return main;
    }
}
