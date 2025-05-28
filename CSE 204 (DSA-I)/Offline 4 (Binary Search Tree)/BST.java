import java.util.Iterator;

public interface BST<E> {
    void insert(E element);
    E delete(E element);
    boolean contains(E element);
    void traversal(String traversalOrder);
}
