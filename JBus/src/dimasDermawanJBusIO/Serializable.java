package dimasDermawanJBusIO;

import java.util.HashMap;
import java.util.Map;

public class Serializable {
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<Class<?>, Integer>();
    
    protected Serializable() {
        id = mapCounter.get(getClass()) == null ? 0 : mapCounter.get(getClass());

        mapCounter.put(getClass(), id + 1);
    }

    public static Integer getLastAssignedId(Class<?> c) {
        return mapCounter.get(c);
    }

    public static Integer setLastAssignedId(Class<?> c, int newId) {
        mapCounter.put(c, newId);

        return newId;
    }

    public int compareTo(Serializable s) {
        return Integer.compare(this.id, s.id);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Serializable && ((Serializable) o).id == this.id;
    }

    public boolean equals(Serializable o) {
        return o.id == this.id;
    }
}
