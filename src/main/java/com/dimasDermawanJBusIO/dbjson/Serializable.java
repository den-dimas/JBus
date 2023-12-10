package com.dimasDermawanJBusIO.dbjson;

import java.util.HashMap;

/**
 * <p>
 * Class to generate unique IDs for its inheritors.
 * This class implements the Comparable interface.
 * </p>
 *
 * <br />
 *
 * <p>The ID is assigned automatically to each instance of the inheritor class,
 * ensuring uniqueness across all instances. The IDs are generated based on
 * the order of instantiation of the inheritor classes.</p>
 *
 * <br />
 *
 * <p>Additionally, it provides methods to retrieve and set the last assigned ID
 * for a specific inheritor class, allowing for manual control over ID assignment.</p>
 *
 * @author Dimas Dermawan
 */
public class Serializable implements Comparable<Serializable> {

    /**
     * The unique ID assigned to each instance.
     */
    public final int id;

    /**
     * A static HashMap to keep track of the last assigned ID for each inheritor class.
     */
    private static final HashMap<Class<?>, Integer> mapCounter = new HashMap<Class <?>, Integer>();

    /**
     * Default constructor that automatically assigns a unique ID to each instance.
     */
    protected Serializable() {
        Integer counter = mapCounter.get(getClass());
        counter = counter == null ? 0 : counter + 1;
        mapCounter.put(getClass(), counter);
        this.id = counter;
    }

    /**
     * Retrieves the last assigned ID for a specific inheritor class.
     *
     * @param <T> The type of the inheritor class.
     * @param getter The inheritor class for which to get the last assigned ID.
     * @return The last assigned ID for the specified inheritor class.
     */
    public static <T> Integer getLastAssignedId(Class<T> getter ){
        return mapCounter.get(getter);
    }

    /**
     * Sets the last assigned ID for a specific inheritor class.
     *
     * @param <T>    The type of the inheritor class.
     * @param setter The inheritor class for which to set the last assigned ID.
     * @param number The ID to be set.
     */
    public static <T> void setLastAssignedId(Class<T> setter, int number){
        mapCounter.put(setter, number);
    }

    /**
     * Compares two instances based on their IDs.
     *
     * @param temp The instance to compare to.
     * @return A negative integer, zero, or a positive integer as this ID
     * is less than, equal to, or greater than the specified ID.
     */
    public int compareTo(Serializable temp){
        return Integer.compare(this.id, temp.id);
    }

    /**
     * Checks if an object is an instance of Serializable and has the same ID.
     *
     * @param object The object to compare to.
     * @return True if the object is an instance of Serializable and has the same ID, false otherwise.
     */
    public boolean equals(Object object){
        return object instanceof Serializable && ((Serializable) object).id == this.id;
    }
}
