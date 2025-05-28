class Node<E>{
    E element;
    Node<E> leftChild;
    Node<E> rightChild;

    Node(E element, Node<E> leftChild, Node<E> rightChild){
        this.element = element;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}

public class LinkedBinaryTree<E extends Comparable<E>> implements BST<E> {

    private Node<E> root;

    public LinkedBinaryTree() {
        this.root = null;
    }

    public void insert(E element) {
        if (!contains(element)) {
            root = insertHelp(root, element);
        }
    }

    private Node<E> insertHelp(Node<E> node, E element) {
        if (node == null) {
            node = new Node<>(element, null, null);
        } else {
            if (element.compareTo(node.element) < 0) {
                node.leftChild = insertHelp(node.leftChild, element);
            } else {
                node.rightChild = insertHelp(node.rightChild, element);
            }
        }
        return node;
    }

    public E delete(E element) {
        E item = findHelp(root, element);
        if (item == null) {
            return null;
        } else {
            root = deleteHelp(root, item);
        }
        return item;
    }

    private Node<E> deleteHelp(Node<E> node, E element) {
        if (node == null) {
            return null;
        }
        int compare = element.compareTo(node.element);

        if (compare < 0) {
            node.leftChild = deleteHelp(node.leftChild, element);
        } else if (compare > 0) {
            node.rightChild = deleteHelp(node.rightChild, element);
        } else {
            if (node.leftChild == null) {
                Node<E> rightChild = node.rightChild;
                node.element = null;
                return rightChild;
            } else if (node.rightChild == null) {
                Node<E> leftChild = node.leftChild;
                node.element = null;
                return leftChild;
            } else {
                Node<E> temp = findMin(node.rightChild);
                node.element = temp.element;
                node.rightChild = deleteHelp(node.rightChild, temp.element);
            }
        }
        return node;
    }

    private E findHelp(Node<E> node, E element) {
        if (node == null) {
            return null;
        } else if (element.compareTo(node.element) < 0) {
            return findHelp(node.leftChild, element);
        } else if (element.compareTo(node.element) > 0) {
            return findHelp(node.rightChild, element);
        } else {
            return node.element;
        }
    }

    public boolean contains(E element) {
        return contains(root, element);
    }

    private boolean contains(Node<E> node, E element) {
        if (node == null) {
            return false;
        }
        int compare = element.compareTo(node.element);

        if (compare < 0) {
            return contains(node.leftChild, element);
        } else if (compare > 0) {
            return contains(node.rightChild, element);
        } else {
            return true;
        }
    }

    private Node<E> findMin(Node<E> node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    private Node<E> findMax(Node<E> node) {
        while (node.rightChild != null) {
            node = node.rightChild;
        }
        return node;
    }

    public void traversal(String traversalOrder) {
        switch (traversalOrder) {
            case "Pre":
                preOrderTraversal(root);
                System.out.println();
                break;
            case "In":
                inOrderTraversal(root);
                System.out.println();
                break;
            case "Post":
                postOrderTraversal(root);
                System.out.println();
                break;
        }
    }

    private void postOrderTraversal(Node<E> node) {
        if (node!=null) {
            postOrderTraversal(node.leftChild);
            postOrderTraversal(node.rightChild);
            System.out.print(node.element + " ");
        }
    }

    private void inOrderTraversal(Node<E> node) {
        if (node != null) {
            inOrderTraversal(node.leftChild);
            System.out.print(node.element + " ");
            inOrderTraversal(node.rightChild);
        }
    }

    private void preOrderTraversal(Node<E> node) {
        if (node != null) {
            System.out.print(node.element + " ");
            preOrderTraversal(node.leftChild);
            preOrderTraversal(node.rightChild);
        }
    }

    public void print(){
        printHelp(root);
        System.out.println();
    }

    private void printHelp(Node<E> node){
         if(node!=null){
            System.out.print("(");
            System.out.print(node.element);
            if(node.leftChild!=null){
                printHelp(node.leftChild);
            }
            else if(node.leftChild==null && node.rightChild!=null){
                System.out.print("()");
            }
            if(node.rightChild!=null){
                printHelp(node.rightChild);
            }
            else if(node.rightChild==null && node.leftChild!=null){
                System.out.print("()");
            }
            System.out.print(")");
        }
    }
}
