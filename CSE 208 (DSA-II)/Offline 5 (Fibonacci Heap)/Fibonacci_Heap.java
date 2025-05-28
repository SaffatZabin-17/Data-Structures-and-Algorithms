class node <E>{
    node <E> parentNode, childNode, leftSibling, rightSibling;

    E item;
    int degree, key;
    boolean marked;

    node(int key){
        this.parentNode = null;
        this.childNode = null;
        this.leftSibling = null;
        this.rightSibling = null;
        this.key = key;
        this.degree = 0;
        this.marked = false;
    }

    node(E vertex){
        this.parentNode = null;
        this.childNode = null;
        this.leftSibling = null;
        this.rightSibling = null;
        //this.key = vertex.getVertexDistance();
        this.degree = 0;
        this.marked = false;
        this.item = vertex;
    }

    public void setParentNode(node<E> parentNode){
        this.parentNode = parentNode;
    }

    public node<E> getParentNode(){
        return this.parentNode;
    }

    public void setChildNode(node<E> childNode){
        this.childNode = childNode;
    }

    public node<E> getChildNode(){
        return this.childNode;
    }

    public void setLeftSibling(node<E> leftSibling){
        this.leftSibling = leftSibling;
    }

    public node<E> getLeftSibling(){
        return this.leftSibling;
    }

    public void setRightSibling(node<E> rightSibling){
        this.rightSibling = rightSibling;
    }

    public node<E> getRightSibling(){
        return this.rightSibling;
    }

    public void setDegree(int degree){
        this.degree = degree;
    }

    public int getDegree(){
        return this.degree;
    }

    public E getItem(){
        return this.item;
    }

    public void setItem(E item){
        this.item = item;
    }

    public void setKey(int key){
        this.key = key;
    }

    public int getKey(){
        return this.key;
    }

    public void setMarked(boolean marked){
        this.marked = marked;
    }

    public boolean getMarked(){
        return this.marked;
    }

    @Override
    public String toString(){
        return String.valueOf(this.key);
    }
}

public class Fibonacci_Heap<E> {
    node<E> min;
    int nodeCount;

    public void add(node<E> vertex){
        if(min==null){
            min = vertex;
            vertex.setLeftSibling(min);
            vertex.setRightSibling(min);
        }
        else{
            vertex.setLeftSibling(min.getLeftSibling());
            min.getLeftSibling().setRightSibling(vertex);
            vertex.setRightSibling(min);
            min.setLeftSibling(vertex);
            if(vertex.getKey()<min.getKey()){
                min = vertex;
            }
        }
        nodeCount++;
    }

    public node<E> findMin(){
        return this.min;
    }

    public int size(){
        return this.nodeCount;
    }

    public E poll(){
        node<E> x = min;
        if(x!=null){
            node<E> child = x.getChildNode();
            node<E> k = child, temp;
            // Promote all the children of min to the root list
            if(child!=null){
                temp = child.getRightSibling();
                if(min==null){
                    min = child;
                    child.setLeftSibling(min);
                    child.setRightSibling(min);
                }
                else{
                    child.setLeftSibling(min.getLeftSibling());
                    min.getLeftSibling().setRightSibling(child);
                    child.setRightSibling(min);
                    min.setLeftSibling(child);
                    if(child.getKey()<min.getKey()){
                        min = child;
                    }
                }
                child.setParentNode(null);
                child = temp;
                while(child!=null && child!=k){
                    temp = child.getRightSibling();
                    if(min==null){
                        min = child;
                        child.setLeftSibling(min);
                        child.setRightSibling(min);
                    }
                    else{
                        child.setLeftSibling(min.getLeftSibling());
                        min.getLeftSibling().setRightSibling(child);
                        child.setRightSibling(min);
                        min.setLeftSibling(child);
                        if(child.getKey()<min.getKey()){
                            min = child;
                        }
                    }
                    child.setParentNode(null);
                    child = temp;
                }
            }
            x.getLeftSibling().setRightSibling(x.getRightSibling());
            x.getRightSibling().setLeftSibling(x.getLeftSibling());
            x.setChildNode(null);
            if(x == x.getRightSibling()){
                min = null;
            }
            else{
                min = x.getRightSibling();
                consolidate();                  //Create Binomial Tree like structure in the heap
            }
            nodeCount--;
            return x.item;
        }
        return null;
    }

    private void consolidate(){
        double goldenRatio = (1+Math.sqrt(5))/2;
        int arraySize = (int)(Math.log(nodeCount)/Math.log(goldenRatio))+1;
        node<E>[] array = new node[arraySize];
        for(int i=0;i<arraySize;i++){
            array[i] = null;
        }
        int temp1;
        node<E> start = min;
        if(start!=null){
            node<E> current = min;
            do{
                node<E> x = start;
                int degree = x.getDegree();
                while(array[degree]!=null){
                    node<E> y = array[degree];
                    if(x.getKey()>y.getKey()){
                        node<E> temp = x;
                        x = y;
                        y = temp;
                        start = x;
                    }
                    treeLink(y, x);
                    current = x;
                    array[degree] = null;
                    degree++;
                }
                array[degree] = x;
                start = start.getRightSibling();
            } while(start!=null && start!=current);
            min = null;
            for(int i=0;i<arraySize;i++){
                if(array[i]!=null){
                    if(min==null){
                        min = array[i];
                        array[i].setLeftSibling(min);
                        array[i].setRightSibling(min);
                    }
                    else{
                        array[i].setLeftSibling(min.getLeftSibling());
                        min.getLeftSibling().setRightSibling(array[i]);
                        array[i].setRightSibling(min);
                        min.setLeftSibling(array[i]);
                        if(array[i].getKey()<min.getKey()){
                            min = array[i];
                        }
                    }
                }
            }
        }
    }

    /*
        treeLink connects tree y to tree x, here always tree x will contain the min value. So, tree y will always be connected
     */
    private void treeLink(node<E> y, node<E> x){
        y.getLeftSibling().setRightSibling(y.getRightSibling());
        y.getRightSibling().setLeftSibling(y.getLeftSibling());

        node<E> child = x.getChildNode();

        if(child==null){
            y.setLeftSibling(y);
            y.setRightSibling(y);
        }
        else{
            y.setRightSibling(child);
            y.setLeftSibling(child.getLeftSibling());
            child.getLeftSibling().setRightSibling(y);
            child.setLeftSibling(y);
        }
        y.setParentNode(x);
        x.setChildNode(y);
        x.setDegree(x.getDegree()+1);
        y.setMarked(false);
    }

    public void output(){
        output(min);
        System.out.println();
    }

    private void output(node<E> c){
        System.out.print("(");
        if (c == null) {
            System.out.print(")");
        } else {
            node<E> temp = c;
            do {
                System.out.print(temp.getKey());
                node<E> k = temp.getChildNode();
                output(k);
                System.out.print("->");
                temp = temp.getRightSibling();
            } while (temp != c);
            System.out.print(")");
        }
    }
}

