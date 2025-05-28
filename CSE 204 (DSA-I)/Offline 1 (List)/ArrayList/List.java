package ArrayList;

interface List<E> {
    void clear();
    void insert(E item);
    void append(E item);
    E remove();
    void moveToStart();
    void moveToEnd();
    void prev();
    void next();
    int length();
    int currPos();
    void moveToPos(int pos);
    E getValue();
    int Search(E item);
}
