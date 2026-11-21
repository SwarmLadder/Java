import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Reference algorithms: try starter first. */
public final class Tasks {
    private Tasks() {}
    public static int[] twoSum(int[] a, int target) {
        Map<Integer,Integer> seen = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            int needed = target - a[i];
            if (seen.containsKey(needed)) return new int[] {seen.get(needed), i};
            seen.put(a[i], i);
        }
        throw new IllegalArgumentException("No pair");
    }
    public static void moveZeros(int[] a) {
        int write = 0;
        for (int value : a) if (value != 0) a[write++] = value;
        Arrays.fill(a, write, a.length, 0);
    }
    public static int lowerBound(int[] a, int target) {
        int lo = 0, hi = a.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
    public static boolean validBrackets(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') stack.push(ch);
            else if (stack.isEmpty()) return false;
            else {
                char open = stack.pop();
                if (!(open == '(' && ch == ')' || open == '[' && ch == ']' || open == '{' && ch == '}')) return false;
            }
        }
        return stack.isEmpty();
    }
    public static int maxSubarray(int[] a) {
        if (a.length == 0) throw new IllegalArgumentException("Empty input");
        int best = a[0], current = a[0];
        for (int i = 1; i < a.length; i++) {
            current = Math.max(a[i], current + a[i]);
            best = Math.max(best, current);
        }
        return best;
    }
    public static int[] mergeSorted(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length)
            result[k++] = a[i] <= b[j] ? a[i++] : b[j++];
        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];
        return result;
    }
    public static int shortestPath(int n, int[][] edges, int start, int goal) {
        if (n <= 0 || start < 0 || start >= n || goal < 0 || goal >= n)
            throw new IllegalArgumentException("Invalid vertex");
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            if (edge.length != 2 || edge[0] < 0 || edge[1] < 0 || edge[0] >= n || edge[1] >= n)
                throw new IllegalArgumentException("Invalid edge");
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int[] distance = new int[n];
        Arrays.fill(distance, -1);
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        distance[start] = 0;
        queue.add(start);
        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            if (vertex == goal) return distance[vertex];
            for (int neighbor : graph.get(vertex)) if (distance[neighbor] < 0) {
                distance[neighbor] = distance[vertex] + 1;
                queue.add(neighbor);
            }
        }
        return -1;
    }
    public static int firstUnique(String s) {
        int[] counts = new int[128];
        for (int i = 0; i < s.length(); i++) counts[s.charAt(i)]++;
        for (int i = 0; i < s.length(); i++) if (counts[s.charAt(i)] == 1) return i;
        return -1;
    }
}
