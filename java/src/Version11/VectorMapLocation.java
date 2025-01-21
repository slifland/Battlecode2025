package Version11;
import battlecode.common.*;

import java.util.Arrays;

public class VectorMapLocation {
    private MapLocation[] array;

    private int size;
    private int capacity;

    private static final int default_capacity = 250;

    public VectorMapLocation(int initialSize) {
        array = new MapLocation[initialSize];
        size = 0;
        capacity = initialSize;
    }

    public VectorMapLocation() {
        array = new MapLocation[default_capacity];
        size = 0;
        capacity = default_capacity;
    }

    public boolean contains(MapLocation other) {
        if(other == null) return false;
        for(int i = 0; i < array.length; i++) {
            if(array[i].equals(other)) return true;
        }
        return false;
    }

    public void add(MapLocation toAdd) {
        if(size >= capacity) {
            doubleSize();
        }
        array[size] = toAdd;
        size++;
    }

    public void remove(MapLocation toRemove) {
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
