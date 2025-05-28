//package Offline6_RedBlackTree;

class Node {
    int value, color, size;
    Node parent, leftChild, rightChild;

    Node(){
        this.value = 0;
        this.color = 0;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
        this.size = 0;
    }

    Node(int value){
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
        this.value = value;
        this.color = 1;
        this.size = 1;
    }

    Node(Node node){
        this.parent = node.parent;
        this.leftChild = node.leftChild;
        this.rightChild = node.rightChild;
        this.value = node.value;
        this.color = node.color;
        this.size = node.size;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public void setColor(int color){
        this.color = color;
    }

    public int getColor(){
        return this.color;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public Node getParent(){
        return this.parent;
    }

    public void setLeftChild(Node leftChild){
        this.leftChild = leftChild;
    }

    public Node getLeftChild(){
        return this.leftChild;
    }

    public void setRightChild(Node rightChild){
        this.rightChild = rightChild;
    }

    public Node getRightChild(){
        return this.rightChild;
    }

    public void setSize(int size){
        this.size = size;
    }

    public int getSize(){
        return this.size;
    }
}

public class RedBlackTree {
    private Node root;
    private final Node sentinelNode;                    //Sentinel node, to represent leaves as NULL nodes in the RB tree, rather than only null

    public RedBlackTree() {
        sentinelNode = new Node();
        sentinelNode.color = 0;
        sentinelNode.leftChild = null;
        sentinelNode.rightChild = null;
        sentinelNode.size = 0;
        root = sentinelNode;
    }

    /*
        Used when y is a right child of x.
        x's left child stays as it is
        y's right child stays as it is
        x becomes y's child and y becomes x's parent
        y's left child becomes the right child of x
    */
    public void leftRotate(Node x) {
        Node y = x.rightChild;
        x.rightChild = y.leftChild;
        if (y.leftChild != sentinelNode) {
            y.leftChild.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.leftChild) {
            x.parent.leftChild = y;
        } else {
            x.parent.rightChild = y;
        }
        y.leftChild = x;
        x.parent = y;
        x.size = x.leftChild.size + x.rightChild.size + 1;
        y.size = y.leftChild.size + y.rightChild.size + 1;
    }

    /*
        Same as left rotation, only left becomes right and right becomes left
    */
    public void rightRotate(Node x) {
        Node y = x.leftChild;
        x.leftChild = y.rightChild;
        if (y.rightChild != sentinelNode) {
            y.rightChild.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.rightChild) {
            x.parent.rightChild = y;
        } else {
            x.parent.leftChild = y;
        }
        y.rightChild = x;
        x.parent = y;
        x.size = x.leftChild.size + x.rightChild.size + 1;
        y.size = y.leftChild.size + y.rightChild.size + 1;
    }

    /*
        Insert a node z, color it as red initially.
        Do normal insertion as you would do in a regular BST
    */
    public void insert(int value) {
        Node z = new Node(value);
        z.leftChild = sentinelNode;
        z.rightChild = sentinelNode;
        Node y = null;
        Node x = this.root;
        while (x != sentinelNode) {
            y = x;
            if (z.value < x.value) {
                x = x.leftChild;
            } else {
                x = x.rightChild;
            }
        }
        z.parent = y;
        if (y == null) {
            root = z;
        }
        else if (z.value < y.value) {
            y.leftChild = z;
        }
        else {
            y.rightChild = z;
        }
        if (z.parent == null) {                                                 //To fix NPE problems
            z.color = 0;
            return;
        }
        if (z.parent.parent == null) {                                          //To fix NPE problems
            return;
        }
        Node temp = z;
        if(z != sentinelNode){
            while(temp != null){
                temp.size++;
                temp = temp.parent;
            }
        }
        insertFixUp(z);
    }

    /*
        Loop iterates as long as z's parents are red. If z's parent is black, we are done.

        Total 6 cases. 3 for when z is a rightChild, and 3 for when z is a leftChild. Below 3 cases are for when z is a rightChild.
        For the cases when z is a leftChild, just turn left into right and right into left
        Case 1: z's uncle y is red, and z is a rightChild
            1. z's grandfather must be black.
            2. z and z.parent are both red.
            3. So, color z.parent and z.uncle (y) black.
            4. Color z.grandparent red.
            5. z = z.parent.parent
            6. Push red violation up the tree

        Case 2: z's uncle y is black and z is a rightChild. So, z's grandfather must be black
            1. Set z as z.parent
            2. Left rotate on z.parent
            3. This was z becomes a left child, and both z and z.parent are red. This will initiate case 3

        Case 3: z's uncle y is black and z is a leftChild. z's grandfather is black
            1. Color z.parent black
            2. Color z's grandparent red.
            3. Now right rotate on z's grandparent.
            4. This way we get no longer two reds in a row. z's grandparent goes into the right subtree, and z's parent becomes the new root of that subtree.
            5. All properties are maintained. We are done.
    */
    private void insertFixUp(Node z) {
        Node y;
        while (z.parent.color == 1) {
            if (z.parent == z.parent.parent.leftChild) {
                y = z.parent.parent.rightChild;
                if (y.color == 1) {
                    y.color = 0;                                // Case 1
                    z.parent.color = 0;                         // Case 1
                    z.parent.parent.color = 1;                  // Case 1
                    z = z.parent.parent;                        // Case 1
                }
                else {
                    if (z == z.parent.rightChild) {
                        z = z.parent;                           // Case 2
                        leftRotate(z);                          // Case 2
                    }
                    z.parent.color = 0;                         // Case 3
                    z.parent.parent.color = 1;                  // Case 3
                    rightRotate(z.parent.parent);               // Case 3
                }
            }
            else {
                y = z.parent.parent.leftChild;
                if (y.color == 1) {
                    y.color = 0;
                    z.parent.color = 0;
                    z.parent.parent.color = 1;
                    z = z.parent.parent;
                }
                else {
                    if (z == z.parent.leftChild) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = 0;
                    z.parent.parent.color = 1;
                    leftRotate(z.parent.parent);
                }
            }
            if (z == root) {                                             //To fix the NPE problems
                break;
            }
        }
        root.color = 0;
    }

    //Transplants node v into node u's place.
    private void rbTransplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        }
        else if (u == u.parent.leftChild) {
            u.parent.leftChild = v;
        }
        else {
            u.parent.rightChild = v;
        }
        v.parent = u.parent;
    }

    /*
        Node to be removed is z. Two node pointers y and x. x points to the left child of y. Right child of y must be sentinel/null
        1. If z contains less than two non-empty children, then,
            1.1. y points to the same node as z
            1.2. Simply remove z, and x takes its place.

        2. If z contains more than two non-empty children, then,
            2.1. y will point to z's in-order successor, and will replace z.
            2.2. y will be deleted. z will not be physically deleted. Only z will be replaced by y.
            2.3. x will take up y's place and y's "blackness" if y was a black node. So, x becomes a doubly black node.

        If z was a black node, we will call deleteFixUp method.
        If z was a red node, we will do nothing.
    */
    public void deleteNode(int data) {
        deleteNodeHelper(this.root, data);
    }
    private void deleteNodeHelper(Node node, int key) {
        Node z = sentinelNode;
        Node x, y;
        while (node != sentinelNode) {
            if (node.value == key) {
                z = node;
            }
            if (node.value <= key) {
                node = node.rightChild;
            } else {
                node = node.leftChild;
            }
        }
        if (z == sentinelNode) {
            return;
        }
        y = z;
        int yOriginalColor = y.color;
        if (z.leftChild == sentinelNode) {
            x = z.rightChild;
            rbTransplant(z, z.rightChild);
        }
        else if (z.rightChild == sentinelNode) {
            x = z.leftChild;
            rbTransplant(z, z.leftChild);
        }
        else {
            y = findLeftMostNodeRightSubtree(z.rightChild);
            yOriginalColor = y.color;
            x = y.rightChild;
            if (y.parent == z) {
                x.parent = y;
            }
            else {
                rbTransplant(y, y.rightChild);
                y.rightChild = z.rightChild;
                y.rightChild.parent = y;
            }
            rbTransplant(z, y);
            y.leftChild = z.leftChild;
            y.leftChild.parent = y;
            y.color = z.color;
        }
        Node temp = z;
        while(temp != null){
            temp.size--;
            temp = temp.parent;
        }
        if (yOriginalColor == 0) {
            deleteFixUp(x);
        }
    }

    /*
        If while traversing, any node is red-black, we re-color the node black. We are done.
        If we don't find any red-black node, then in worst case, the iteration will go until root.
        Case 1: W is red
            1. w must have black siblings.
            2. Make w black and x.parent red.
            3. Then left rotate on x.parent, if w is a right child. Else, right rotate on x.parent
            4. New sibling of x was a child of w, hence this new sibling must be black. This new sibling is the new w.
            5. Go to case 2,3,4

        Case 2: W is black, both the children of w are black
            1. Parent of x can be black or red, doesn't matter.
            2. x was doubly black, so we remove one black from x. So, x remains black. w was single black. So removing one black from w turns w into red.
            3. Move the extracted black to x.parent. This "blacker" x.parent is the new x.
            4. If entered case 2 from case 1, x.parent was red. Now it received black. So, x.parent is now red-black.
            5. Set color of new x to red. Loop terminates. Else if x becomes double black, then loop continues.

        Case 3: w is black, left child of w is red, and right child of w is black.
            1. Make w red, and left child of w black.
            2. Right rotate on w
            3. New w is a new sibling of x. New w contains the previous w as right child, which is a red node.
            4. Go to case 4.

        Case 4: w is black, and the right child of w is red. Left child can be black or red, doesn't matter.
            1. x.parent can be any color, red or black. Doesn't matter. Set w.color = x.parent.color;
            2. set x.parent color as black and set w.rightChild color as black.
            3. Left rotate on x.parent.
            4. Remove the extra black from x, so, x was doubly black. Now it's singly black. This doesn't violate the black height property.
            5. All done. Setting x to root causes the program to terminate.
    */
    private void deleteFixUp(Node x) {
        Node w;
        while (x != root && x.color == 0) {
            if (x == x.parent.leftChild) {                              // x is a left child of it's parent
                w = x.parent.rightChild;
                if(w == sentinelNode){
                    continue;
                }
                if (w.color == 1) {
                    w.color = 0;                                        // Case 1
                    x.parent.color = 1;                                 // Case 1
                    leftRotate(x.parent);                               // Case 1
                    w = x.parent.rightChild;                            // Case 1
                }
                if ((w.leftChild.color == 0 || w.leftChild == sentinelNode) && (w.rightChild.color == 0 || w.rightChild == sentinelNode)) {         // To avoid some weird NPEs :3
                    w.color = 1;                                        // Case 2
                    x = x.parent;                                       // Case 2
                }
                else {
                    if (w.rightChild == sentinelNode || w.rightChild.color == 0) {                                  // To avoid some weird NPEs :3
                        w.leftChild.color = 0;                          // Case 3
                        w.color = 1;                                    // Case 3
                        rightRotate(w);                                 // Case 3
                        w = x.parent.rightChild;                        // Case 3
                    }
                    w.color = x.parent.color;                           // Case 4
                    x.parent.color = 0;                                 // Case 4
                    w.rightChild.color = 0;                             // Case 4
                    leftRotate(x.parent);                               // Case 4
                    x = root;                                           // Case 4
                }
            }
            else {                                                      // x is a right child of it's parent
                w = x.parent.leftChild;
                if(w == sentinelNode){
                    continue;
                }
                if (w.color == 1) {
                    w.color = 0;
                    x.parent.color = 1;
                    rightRotate(x.parent);
                    w = x.parent.leftChild;
                }
                if ((w.rightChild.color == 0 || w.rightChild == sentinelNode) && (w.leftChild.color == 0 || w.leftChild == sentinelNode)) {         // To avoid some weird NPEs :3
                    w.color = 1;
                    x = x.parent;
                }
                else {
                    if (w.leftChild.color == 0 || w.leftChild == sentinelNode) {                            // To avoid some weird NPEs :3
                        w.rightChild.color = 0;
                        w.color = 1;
                        leftRotate(w);
                        w = x.parent.leftChild;
                    }
                    w.color = x.parent.color;
                    x.parent.color = 0;
                    w.leftChild.color = 0;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = 0;
    }

    public int search(int key){
        if(searchHelp(root, key) == 0){
            return 0;
        }
        else{
            return 1;
        }
    }

    private int searchHelp(Node node, int key){
        if(node == sentinelNode){
            return 0;
        }
        else if(key < node.value){
            return searchHelp(node.leftChild, key);
        }
        else if(key > node.value){
            return searchHelp(node.rightChild, key);
        }
        else{
            return 1;
        }
    }

    public int getCountLessThanVal(int value){
        return getCountLessThanValHelper(root, value);
    }

    public int getCountLessThanValHelper(Node node, int value){
        if(node == sentinelNode){
            return 0;
        }
        if(node.value >= value){
            return getCountLessThanValHelper(node.leftChild, value);
        }
        else{
            return node.leftChild.size + 1 + getCountLessThanValHelper(node.rightChild, value);
        }
    }
    public Node findLeftMostNodeRightSubtree(Node node) {
        while (node.leftChild != sentinelNode) {
            node = node.leftChild;
        }
        return node;
    }

    public Node findRightMostNodeLeftSubtree(Node node) {
        while (node.rightChild != sentinelNode) {
            node = node.rightChild;
        }
        return node;
    }

}