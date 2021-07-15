import java.util.StringJoiner;

public class Main {

    private static final int count = 100000;
    private static Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) {
        checkMethod("builderRepeat", Main::builderRepeat);
        checkMethod("builder", Main::builder);
        checkMethod("concat", Main::concat);
        checkMethod("join", Main::join);
        checkMethod("joiner", Main::joiner);
    }

    public static void checkMethod(final String name, final Func func) {
        long memoryBefore = checkMemory();
        long timeBefore = System.currentTimeMillis();
        try {
            func.execute();
        } finally {
            long timeAfter = System.currentTimeMillis();
            long memoryAfter = checkMemory();
            
            System.out.println("Before memory is bytes: " + memoryBefore);
            System.out.println("After memory is bytes: " + memoryAfter);
            System.out.println("\n" + name + " elapsed " + (timeAfter - timeBefore) + " ms");
            System.out.println("Used memory is bytes: " + (memoryAfter - memoryBefore));
            System.out.println("================================================");
        }
    }

    private static long checkMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }

    private interface Func {
        String execute();
    }

    private static String builderRepeat() {
        StringBuilder s = new StringBuilder();
        s.append("*".repeat(count));
        return s.toString();
    }

    private static String builder() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < count; i++) {
            s.append("*");
        }
        return s.toString();
    }

    private static String concat() {
        String s = "";
        for (int i = 0; i < count; i++) {
            s = s + "*";
        }
        return s;
    }

    private static String join() {
        String s = "";
        for (int i = 0; i < count; i++) {
            String.join("", s, "*");
        }
        return s;
    }

    private static String joiner() {
        StringJoiner joiner = new StringJoiner("");
        for (int i = 0; i < count; i++) {
            joiner.add("*");
        }
        return joiner.toString();
    }
}