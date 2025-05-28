package ArrayStack;

interface Stack<E> {
    void clear();
    void push(E item);
    E pop();
    int length();
    E topValue();
    void setDirection(int direction);
}
