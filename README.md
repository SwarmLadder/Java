# SwarmLadder Java — Practical Java + DSA

**Code → Run → Check output → Understand.** Eight Java 17 problems, editable starters, reference answers and 24 public test cases. No packages required beyond JDK 17 and Make.

```sh
make reference TASK=move-zeros # Working answer, expected and actual output
make check TASK=move-zeros     # Graded unfinished student starter
make reference                 # All reference checks
make check                     # All student checks
```

Edit `src/starter/Tasks.java` and re-run the test. Compare `PASS`/`FAIL` expected and actual outputs. Read `src/reference/Tasks.java` after attempting the challenge.

| ID | Skill | Time / additional space |
| --- | --- | --- |
| `two-sum` | HashMap | expected O(n) / O(n) |
| `move-zeros` | In-place two pointers | O(n) / O(1) |
| `lower-bound` | Binary search | O(log n) / O(1) |
| `valid-brackets` | Stack | O(n) / O(n) |
| `max-subarray` | Kadane's algorithm | O(n) / O(1) |
| `merge-sorted` | Merge two arrays | O(n+m) / O(n+m) output |
| `shortest-path` | BFS, unweighted graph | O(V+E) / O(V+E) |
| `first-unique` | ASCII character counting | O(n) / O(1) |

**Short example:** To move zeros, copy nonzero items forward using read and write indices, then fill the suffix with zeros. Order stays stable; O(n) time and O(1) extra space.

Java source executes through a real JDK locally or in CI. The existing SwarmLadder Lab browser has no Java compiler yet; this course does not claim to execute Java there. Tests are public, not hidden or proof of complexity.
