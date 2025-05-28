import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        LinkedBinaryTree<Integer> binarySearchTree = new LinkedBinaryTree<>();
        String input_file_name = "Input_BST.txt";
        try{
            BufferedReader br = new BufferedReader(new FileReader(input_file_name));
            String line;

            while(true){
                line = br.readLine();
                if(line == null){
                    break;
                }
                String[] tokens = line.split(" ");
                if(tokens[0].equals("Q")){
                    break;
                }
                if(tokens[0].equalsIgnoreCase("I")){
                    Integer x = null;
                    try {
                        x = Integer.valueOf(tokens[1]);
                    } catch (Exception e){
                        System.out.println("Invalid input");
                    }
                    binarySearchTree.insert(x);
                    binarySearchTree.print();
                }
                if(tokens[0].equalsIgnoreCase("D")){
                    Integer x = null;
                    try{
                        x = Integer.valueOf(tokens[1]);
                    } catch (Exception e){
                        System.out.println("Invalid input");
                    }
                    Object obj = binarySearchTree.delete(x);
                    if(obj == null){
                        System.out.println("Invalid Operation");
                    }
                    else{
                        binarySearchTree.print();
                    }
                }
                if(tokens[0].equalsIgnoreCase("F")){
                    Integer x = null;
                    try{
                        x = Integer.valueOf(tokens[1]);
                    } catch (Exception e){
                        System.out.println("Invalid Input");
                    }
                    var isPresent = binarySearchTree.contains(x);
                    if(isPresent){
                        System.out.println("True");
                    }
                    else{
                        System.out.println("False");
                    }
                }
                if(tokens[0].equalsIgnoreCase("T")){
                    if(tokens[1].equalsIgnoreCase("In")){
                        binarySearchTree.traversal(tokens[1]);
                    }
                    if(tokens[1].equalsIgnoreCase("Pre")){
                        binarySearchTree.traversal(tokens[1]);
                    }
                    if(tokens[1].equalsIgnoreCase("Post")){
                        binarySearchTree.traversal(tokens[1]);
                    }
                }
            }
        } catch (Exception e){
            System.out.println("File Error");
        }
    }
}
