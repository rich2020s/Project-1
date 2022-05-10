package dataStructure;

// Generic List interface
public interface CustomList<T>{
    // define some methods that will be present in all implementations (ArrayList and LinkedList)
    // no matter what implementation we use, we should have the same functionality:
    public void add(T element);
    public T get(int i);
    public String print();

}