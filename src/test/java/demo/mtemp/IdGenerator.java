package demo.mtemp;

public class IdGenerator {
    private static long id;

    public static long generateId() {
        return ++id;
    }
}
