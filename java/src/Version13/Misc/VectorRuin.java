package Version13.Misc;


import java.util.Arrays;

public class VectorRuin {

    private Ruin[] array;

    private int size;
    private int capacity;

    private static final int default_capacity = 250;

    public VectorRuin(int initialSize) {
        array = new Ruin[initialSize];
        size = 0;
        capacity = initialSize;
    }

    public VectorRuin() {
        array = new Ruin[default_capacity];
        size = 0;
        capacity = default_capacity;
    }

    public boolean contains(Ruin other) {
        if(other == null) return false;
        for(int i = 0; i < array.length; i++) {
            if(array[i].equals(other)) return true;
        }
        return false;
    }

    public void add(Ruin toAdd) {
        if(size >= capacity) {
            doubleSize();
        }
        array[size] = toAdd;
        size++;
    }

    public void remove(Ruin toRemove) {
        for(int i = 0; i < array.length; i++) {
            if(array[i].equals(toRemove)) {
                array[i] = null;
                return;
            }
        }
    }

    private void doubleSize() {
        capacity = capacity * 2;
        array = Arrays.copyOf(array, capacity);
    }
    

}
