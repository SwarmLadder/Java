import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/** Public, dependency-free checks with expected and actual values. */
public final class LabCheck {
    private LabCheck() {}
    private static String filter;
    private static int total, passed;

    private static String format(Object value) {
        return value instanceof int[] ? Arrays.toString((int[]) value) : String.valueOf(value);
    }
    private static void check(String task, String name, Object actual, Object expected) {
        if (filter != null && !filter.equals(task)) return;
        total++;
        boolean good = actual instanceof int[] && expected instanceof int[]
            ? Arrays.equals((int[]) actual, (int[]) expected)
            : Objects.equals(actual, expected);
        if (good) passed++;
        System.out.printf("%s %s / %s: expected %s; actual %s%n",
            good ? "PASS" : "FAIL", task, name, format(expected), format(actual));
    }
    private static void zeros(String name, int[] input, int[] expected) {
        Tasks.moveZeros(input);
        check("move-zeros", name, input, expected);
    }
    public static void main(String[] args) {
        filter = args.length == 0 ? null : args[0];
        if (args.length > 1 || filter != null && !Set.of(
            "two-sum", "move-zeros", "lower-bound", "valid-brackets",
            "max-subarray", "merge-sorted", "shortest-path", "first-unique").contains(filter)) {
            System.err.println("Unknown task"); System.exit(2);
        }

        check("two-sum", "basic", Tasks.twoSum(new int[]{2,7,11,15}, 9), new int[]{0,1});
        check("two-sum", "duplicate", Tasks.twoSum(new int[]{3,3}, 6), new int[]{0,1});
        check("two-sum", "negative", Tasks.twoSum(new int[]{-4,2,9}, 5), new int[]{0,2});
        zeros("mixed", new int[]{0,5,0,3,12,0,7}, new int[]{5,3,12,7,0,0,0});
        zeros("negative", new int[]{-1,0,-2}, new int[]{-1,-2,0});
        zeros("empty", new int[]{}, new int[]{});
        check("lower-bound", "duplicates", Tasks.lowerBound(new int[]{1,2,2,2,5}, 2), 1);
        check("lower-bound", "missing", Tasks.lowerBound(new int[]{1,3,5}, 2), 1);
        check("lower-bound", "empty", Tasks.lowerBound(new int[]{}, 5), 0);
        check("valid-brackets", "nested", Tasks.validBrackets("([]){}"), true);
        check("valid-brackets", "crossed", Tasks.validBrackets("([)]"), false);
        check("valid-brackets", "empty", Tasks.validBrackets(""), true);
        check("max-subarray", "mixed", Tasks.maxSubarray(new int[]{-2,1,-3,4,-1,2,1,-5,4}), 6);
        check("max-subarray", "negative", Tasks.maxSubarray(new int[]{-7,-2,-9}), -2);
        check("max-subarray", "single", Tasks.maxSubarray(new int[]{4}), 4);
        check("merge-sorted", "basic", Tasks.mergeSorted(new int[]{1,2,4}, new int[]{1,3,4}), new int[]{1,1,2,3,4,4});
        check("merge-sorted", "empty", Tasks.mergeSorted(new int[]{}, new int[]{2,5}), new int[]{2,5});
        check("merge-sorted", "negatives", Tasks.mergeSorted(new int[]{-2,0}, new int[]{-1,3}), new int[]{-2,-1,0,3});
        check("shortest-path", "basic", Tasks.shortestPath(4, new int[][]{{0,1},{1,2},{2,3}}, 0, 3), 3);
        check("shortest-path", "unreachable", Tasks.shortestPath(4, new int[][]{{0,1}}, 0, 3), -1);
        check("shortest-path", "same", Tasks.shortestPath(2, new int[][]{}, 1, 1), 0);
        check("first-unique", "basic", Tasks.firstUnique("leetcode"), 0);
        check("first-unique", "none", Tasks.firstUnique("aabb"), -1);
        check("first-unique", "case-sensitive", Tasks.firstUnique("aAbA"), 0);

        System.out.printf("RESULT: %d/%d passed%n", passed, total);
        if (passed != total || total == 0) System.exit(1);
    }
}
