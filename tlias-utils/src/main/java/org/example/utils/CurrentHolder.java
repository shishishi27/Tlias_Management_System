package org.example.utils;

public class CurrentHolder {
    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    public static void setCurrentLocal(Integer empId) {
        CURRENT_LOCAL.set(empId);
    }
    public static Integer getCurrentLocal() {
        return CURRENT_LOCAL.get();
    }
    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
