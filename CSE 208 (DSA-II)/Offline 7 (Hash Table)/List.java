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
    E Search(E item);
    int searchPosition(E item);
}
