import java.util.Comparator;
import java.util.PriorityQueue;

class matrixComparator implements Comparator<Branch_And_Bound>{

    @Override
    public int compare(Branch_And_Bound o1, Branch_And_Bound o2) {
        return Integer.compare(o1.getFinalBound(), o2.getFinalBound());
    }
}
public class Branch_And_Bound {

    int fixedColumnNo, fixedRowNo;

    int[][] matrix;

    public Branch_And_Bound(){
        this.matrix = null;
        this.fixedRowNo = 0;
        this.fixedColumnNo = 0;
    }

    public Branch_And_Bound(int[][] matrix, int fixedColumnNo, int fixedRowNo){
        this.matrix = matrix;
        this.fixedColumnNo = fixedColumnNo;
        this.fixedRowNo = fixedRowNo;
    }
    public void setFixedColumnNo(int fixedColumnNo){
        this.fixedColumnNo = fixedColumnNo;
    }

    public void setFixedRowNo(int fixedRowNo){
        this.fixedRowNo = fixedRowNo;
    }

    public void setMatrix(int[][] matrix){
        this.matrix = matrix;
    }

    public int getUnfixedRegionBound(){
        int startCol = this.fixedColumnNo;
        int startRow = this.fixedRowNo;
        int[][] intermediate_matrix = this.matrix;

        //Calculating bound of rows
        //Inner loop -> Cols
        //Outer loop -> Rows
        int rowBound = 0, rowTemp = 0;
        for(int i=startRow; i < matrix.length;i++){
            for(int j=startCol;j< matrix.length;j++){
                rowTemp += matrix[i][j];
            }
            rowTemp = (int) Math.floor((rowTemp+1)/2);
            rowBound = Math.max(rowBound, rowTemp);
            rowTemp = 0;
        }

        //Calculating bound of columns
        //Inner loop -> Rows
        //Outer loop -> Cols
        int colBound = 0, colTemp = 0;
        for(int i = startCol ; i< matrix.length;i++){
            for(int j = startRow; j< matrix.length; j++){
                colTemp += matrix[j][i];
            }
            colTemp = (int) Math.floor((colTemp + 1)/2);
            colBound = Math.max(colBound, colTemp);
            colTemp = 0;
        }

        return Math.max(rowBound, colBound);

    }

    public int getFixedRegionBound(){
        int endCol = this.fixedColumnNo;
        int endRow = this.fixedRowNo;

        int diagonal = 0, rowBound = 0, rowTemp = 0;
        int range = Math.min(endRow, endCol);
        //System.out.println(range);
        //Calculation bound for rows
        for(int i=0;i<Math.min(endRow, endCol);i++){
            //Calculating before diagonal
            diagonal = i;
            //System.out.println(diagonal);
            //System.out.println("Calculate Bound Before Diagonal: " + calculateBoundBeforeDiagonal(diagonal));
            //System.out.println("Calculate Bound After Diagonal: " + calculateBoundAfterDiagonal(diagonal));
            rowTemp = Math.max(calculateBoundBeforeDiagonal(diagonal) , calculateBoundAfterDiagonal(diagonal));

            //System.out.println("Bound for row" + i + ": " + rowTemp);

            rowBound = Math.max(rowBound, rowTemp);
        }

        //System.out.println("Final Row Bound: " + rowBound);

        //Calculating bound for cols
        int colBound = 0, colTemp = 0;
        for(int i=0;i<Math.max(endRow, endCol);i++){
            diagonal = i;
            colTemp = Math.max(calculateBoundOverDiagonal(diagonal), calculateBoundUnderDiagonal(diagonal));
            //System.out.println("Bound for column" + i + ": " + colTemp);

            colBound = Math.max(colBound, colTemp);
        }
        //System.out.println("Final Column Bound: " + colBound);

        return Math.max(colBound, rowBound);
    }

    private int calculateBoundBeforeDiagonal(int diagonal){
        if(diagonal == 0){
            return 0;
        }
        int temp = 0;
        for(int i= diagonal-1; i>=0;i--){
            temp += matrix[diagonal][i];
        }
        if(matrix[diagonal][diagonal] == 0){
            return temp;
        }
        return temp+1;
    }

    private int calculateBoundAfterDiagonal(int diagonal){
        int tempFixed = 0, tempUnfixed = 0;

        for(int i=diagonal+1;i<this.fixedColumnNo;i++){
            if(matrix[diagonal][i] == 1){
                tempFixed = i;
            }
        }

        //tempFixed = this.fixedColumnNo - (diagonal + 1);


        int[][] tempMatrix = this.matrix;
        for(int i= this.fixedColumnNo; i< matrix.length;i++){
            tempUnfixed += matrix[diagonal][i];
        }
        if(matrix[diagonal][diagonal] == 0 && tempFixed == 0 && tempUnfixed == 0){
            return 0;
        }
        if(tempFixed == 0){
            if(tempUnfixed == 0){
                return 1;
            }
            else{
                return fixedColumnNo + tempUnfixed - diagonal;
            }
        }
        else{
            if(tempUnfixed == 0){
                return tempFixed - diagonal + 1;
            }
            else{
                return tempFixed + tempUnfixed - diagonal;
            }
        }
    }

    private int calculateBoundOverDiagonal(int diagonal){
        if(diagonal == 0){
            return 0;
        }
        int temp = 0;
        for(int i=diagonal-1; i>=0;i--){
            temp += matrix[i][diagonal];
        }
        if(matrix[diagonal][diagonal] == 0){
            return temp;
        }
        return temp+1;
    }

    private int calculateBoundUnderDiagonal(int diagonal){
        int tempFixed = 0, tempUnfixed = 0;

        for(int i = diagonal+1; i<this.fixedRowNo;i++){
            if(matrix[i][diagonal] == 1){
                tempFixed = i;
            }
        }

        //tempFixed = this.fixedRowNo - (diagonal + 1);

        for(int i = this.fixedRowNo; i< matrix.length;i++){
            tempUnfixed += matrix[i][diagonal];
        }

        //System.out.println("" + tempFixed + " " + tempUnfixed + " ");

        if(matrix[diagonal][diagonal] == 0 && tempFixed == 0 && tempUnfixed == 0){
            return 0;
        }

        if(tempFixed == 0){
            if(tempUnfixed == 0){
                return 1;
            }
            else{
                return fixedRowNo + tempUnfixed - diagonal;
            }
        }
        else{
            if(tempUnfixed == 0){
                return tempFixed + 1 - diagonal;
            }
            else{
                return tempFixed + tempUnfixed - diagonal;
            }
        }
    }

    public int getFinalBound(){
        return Math.max(getUnfixedRegionBound(), getFixedRegionBound());
    }

    public int[][] shiftCol(int firstCol, int secondCol){
        //System.out.println("Shifting Columns " + firstCol + " and " + secondCol);

        int[] tempCol = new int[this.matrix.length];

        int[][] tempMatrix = new int[matrix.length][matrix.length];

        for(int i=0;i< matrix.length;i++){
            System.arraycopy(matrix[i], 0, tempMatrix[i], 0, matrix.length);
        }

        for(int i=0;i< matrix.length;i++){
            tempCol[i] = tempMatrix[i][secondCol];
        }

        for(int i=0;i< matrix.length;i++){
            for(int j = secondCol; j> firstCol; j--){
                tempMatrix[i][j] = tempMatrix[i][j-1];
            }
        }

        for(int i=0;i< matrix.length;i++){
            tempMatrix[i][firstCol] = tempCol[i];
        }

        /*System.out.println("======================================================");
        System.out.println("After Shifting: ");

        for (int[] ints : tempMatrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }

        System.out.println("========================================================");*/

        return tempMatrix;
    }

    public PriorityQueue<Branch_And_Bound> fixColumn(int fixedRowNo, int fixedColumnNo){
        this.fixedRowNo = fixedRowNo;
        this.fixedColumnNo = fixedColumnNo;

        PriorityQueue<Branch_And_Bound> matrixPriorityQueue = new PriorityQueue<>(new matrixComparator());

        for(int i = 0; i<matrix.length-fixedColumnNo; i++){
            int[][] tempMatrix = shiftCol(fixedColumnNo, fixedColumnNo+i);
            /*for (int[] ints : tempMatrix) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.print(ints[j] + " ");
                }
                System.out.println();
            }*/
            matrixPriorityQueue.add(new Branch_And_Bound(tempMatrix, fixedColumnNo+1, fixedRowNo));
        }

        return matrixPriorityQueue;
    }

    private int[][] shiftRow(int firstRow, int secondRow){
        int[] tempRow = new int[this.matrix.length];
        int[][] tempMatrix = new int[matrix.length][matrix.length];

        for(int i=0;i< matrix.length;i++){
            System.arraycopy(matrix[i], 0, tempMatrix[i], 0, matrix.length);
        }

        for(int i=0;i< matrix.length;i++){
            tempRow[i] = tempMatrix[secondRow][i];
        }

        for(int i=secondRow; i> firstRow; i--){
            for(int j=0; j< matrix.length; j++){
                tempMatrix[i][j] = tempMatrix[i-1][j];
            }
        }

        for(int i=0;i< matrix.length;i++){
            tempMatrix[firstRow][i] = tempRow[i];
        }

        //System.out.println("================================================");
        //System.out.println("After Shifting: ");
        /*for(int i =0;i< matrix.length;i++){
            for(int j=0;j< matrix.length;j++){
                //System.out.print(tempMatrix[i][j] + " ");
            }
            //System.out.println();
        }
        //System.out.println("=================================================");*/

        return tempMatrix;
    }

    public PriorityQueue<Branch_And_Bound> fixRow(int fixedRowNo, int fixedColumnNo){
        this.fixedRowNo = fixedRowNo;
        this.fixedColumnNo = fixedColumnNo;

        PriorityQueue<Branch_And_Bound> matrixPriorityQueue = new PriorityQueue<>(new matrixComparator());

        for(int i=0;i< matrix.length-fixedRowNo; i++){
            int[][] tempMatrix = shiftRow(fixedRowNo, fixedRowNo+i);
            matrixPriorityQueue.add(new Branch_And_Bound(tempMatrix, fixedColumnNo, fixedRowNo+1));
        }
        return matrixPriorityQueue;
    }
}
