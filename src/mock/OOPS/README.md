# Practice README — OOP & DSA (Analyst-level and slightly harder)

> This README provides 3 practice problems: **2 OOP / system-design style** problems (one similar to the Analytics system you solved and one slightly harder) and **1 DSA** problem that matches the M1 DSA level. Each problem includes: full statement, constraints, sample I/O, scoring hints, and a HackerRank-style Java starter template you can paste into the compiler.

---

## Problem A — Analytics System (Extended)

**Estimated target time:** 30–35 minutes (practice: 45 minutes)

### Problem statement

Design and implement an `Analytics` system that collects application actions and sends them to a store in batches. This is like the previous Analytics system, but extended with an **optional time-window flush** and **manual flush** API.

**Requirements**:

* Use an enum `ActionEnum` to represent actions.
* The `Analytics` class must:

  * Buffer incoming actions in-order.
  * Send buffered actions to the store when the buffer size reaches `K`.
  * Provide a `flush()` method that immediately sends all buffered actions (even if less than `K`).
  * Optionally flush automatically if the oldest action in the buffer is older than `T` seconds (simulate using timestamps provided with actions). The compiler test will call `registerAction(ActionEnum action, long timestampSeconds)` where `timestampSeconds` is increasing.
  * Track total number of logged actions.
  * Track frequency of actions (across all registered actions, whether sent or not) and return the most frequently used actions sorted by name.

**Important behaviors**:

* When sending actions to `IAnalyticsStore.storeActions(...)`, send a **copy** of the buffer (not the internal queue reference) and then clear the internal buffer.
* `flush()` should be callable any time and must send current buffer irrespective of size.

### Interfaces (do not modify)

```java
enum ActionEnum { FEATURE_A, FEATURE_B, FEATURE_C }

interface IAnalytics {
    void registerAction(ActionEnum action, long timestampSeconds);
    void flush();
    int getNumberOfActionRegisteredButNotSentToAnalyticsStore();
    int getTotalNumberOfLoggedActions();
    List<ActionEnum> getMostFrequentlyUsedActions();
}

interface IAnalyticsStore {
    void storeActions(Queue<ActionEnum> actions);
}
```

### Constraints

* `1 <= total actions <= 1e5`
* `1 <= K <= 50`
* `0 <= timestampSeconds <= 1e12` (timestamps increasing across calls in tests)
* Time-window `T` (if non-zero) will be small (<= 3600 seconds)

### Sample usage

* `registerAction(FEATURE_A, 1000)`
* `registerAction(FEATURE_B, 1010)`
* `registerAction(FEATURE_A, 1020)` // if `K == 3`, send these 3
* `registerAction(FEATURE_C, 20000)` // if T=3600 and oldest timestamp 1000, then an automatic flush may occur before this call

### Starter template (HackerRank style)

```java
import java.util.*;

enum ActionEnum { FEATURE_A, FEATURE_B, FEATURE_C }

interface IAnalytics {
    void registerAction(ActionEnum action, long timestampSeconds);
    void flush();
    int getNumberOfActionRegisteredButNotSentToAnalyticsStore();
    int getTotalNumberOfLoggedActions();
    List<ActionEnum> getMostFrequentlyUsedActions();
}

interface IAnalyticsStore {
    void storeActions(Queue<ActionEnum> actions);
}

public class Analytics implements IAnalytics {
    private final IAnalyticsStore analyticsStore;
    private final int k;
    private final long timeWindowSeconds; // 0 means disabled

    // Add your data structures here

    public Analytics(IAnalyticsStore analyticsStore, int k, long timeWindowSeconds) {
        this.analyticsStore = analyticsStore;
        this.k = k;
        this.timeWindowSeconds = timeWindowSeconds;
        // init structures
    }

    @Override
    public void registerAction(ActionEnum action, long timestampSeconds) {
        // TODO: implement
    }

    @Override
    public void flush() {
        // TODO: implement
    }

    @Override
    public int getNumberOfActionRegisteredButNotSentToAnalyticsStore() {
        // TODO
        return 0;
    }

    @Override
    public int getTotalNumberOfLoggedActions() {
        // TODO
        return 0;
    }

    @Override
    public List<ActionEnum> getMostFrequentlyUsedActions() {
        // TODO
        return null;
    }
}
```

---

## Problem B — Durable Log Processor (slightly harder)

**Estimated target time:** 40–55 minutes (practice: 60–80 minutes)

### Problem statement

You are given a stream of log entries that arrive as `(date, time, status, message)` with **date = DD-MM-YYYY** and **time = HH:MM** (may be non-zero-padded like `9:5`). The system must support two operations:

1. `ingest(logEntry)` — ingest a log entry into memory (you may assume entries for queries will fit in memory for the test).
2. `query(dateFrom, dateTo, statusFilter)` — return the top `M` most frequent messages (by exact message string) for log entries whose status matches `statusFilter` (one of `INFO`, `ERROR`, `CRITICAL`) and whose full timestamps lie between `dateFrom` and `dateTo` inclusive.

**Requirements**:

* Implement a `LogProcessor` class with methods for `ingest(...)` and `query(...)`.
* Parsing should be robust to non-zero-padded dates/times.
* Do not use any date parsing libraries — convert date+time into comparable integers/longs (e.g., `YYYYMMDDHHMM`).
* `query` must return messages sorted by frequency desc, then lexicographically ascending.

### Interfaces (don't modify)

```java
class LogEntry {
    String date; // "DD-MM-YYYY"
    String time; // "HH:MM"
    String status; // "INFO", "ERROR", "CRITICAL"
    String message;
    public LogEntry(String date, String time, String status, String message) { ... }
}

interface ILogProcessor {
    void ingest(LogEntry entry);
    List<String> query(String dateFrom, String dateTo, String statusFilter, int M);
}
```

### Constraints

* `1 <= number of ingested logs <= 2 * 10^5`
* `1 <= M <= 100`
* Dates between years 2000 and 3000

### Sample

* ingest: `(01-01-2024, 9:05, ERROR, "failed to connect")`
* query(`01-01-2024`, `31-01-2024`, `ERROR`, 5) → returns top 5 error messages and counts

### Starter template

```java
import java.util.*;

class LogEntry {
    String date;
    String time;
    String status;
    String message;
    public LogEntry(String date, String time, String status, String message) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.message = message;
    }
}

interface ILogProcessor {
    void ingest(LogEntry entry);
    List<String> query(String dateFrom, String dateTo, String statusFilter, int M);
}

public class LogProcessor implements ILogProcessor {

    // Add data structures here

    public LogProcessor() {
        // init
    }

    @Override
    public void ingest(LogEntry entry) {
        // TODO: implement
    }

    @Override
    public List<String> query(String dateFrom, String dateTo, String statusFilter, int M) {
        // TODO: implement
        return new ArrayList<>();
    }
}
```

---

## Problem C — DSA: Valid Words Count (string parsing) — M1 style

**Estimated target time:** 30–40 minutes (practice: 45 minutes)

### Problem statement

Given a sentence containing lowercase letters, digits, hyphens `-`, punctuation marks (`!`, `.`, `,`), and spaces, count the number of valid words. A token (split by spaces) is a valid word if:

1. It contains only lowercase letters, at most one hyphen (which must be surrounded by letters), and at most one punctuation mark which — if present — must be at the end of the token.
2. It contains no digits.

Return the number of valid words.

### Constraints

* Sentence length ≤ 10^5 characters
* Tokens count ≤ 10^4

### Starter template

```java
import java.util.*;

public class ValidWords {
    public int countValidWords(String sentence) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        ValidWords v = new ValidWords();
        System.out.println(v.countValidWords("cat and  dog")); // sample
    }
}
```

---

## Scoring and practice guidance

* Try Problem A (Analytics Extended) under **35 minutes**. Focus on correctness first. Handle copying the queue when storing and clearing the buffer.
* Try Problem B (Log Processor) under **45–55 minutes**. Pay careful attention to date/time parsing and lexicographic ordering.
* Try Problem C (DSA) under **30–40 minutes**.

### Mock-exam strategy (M1 style)

* **Total time**: assume 90–120 minutes for both problems in an exam. A recommended split: 40–60 minutes on DSA, 30–40 minutes on OOP, and reserve 10–20 minutes for testing & edge-cases.

---

Good luck — paste the code you write and I’ll debug with targeted hints. Happy practicing!

---

## Problem D — Rate Limited Notification System (Analyst+ Level)

**Estimated target time:** 40–50 minutes (practice: 60 minutes)

### Problem statement

Design and implement a **Rate Limited Notification System** that processes user notifications while preventing spam.

Each notification request contains:

```
(userId, messageType, timestampSeconds)
```

The system must:

1. Allow at most **N notifications per user per rolling window of W seconds**.
2. If a user exceeds the limit, the notification must be rejected.
3. Maintain statistics:

  * Total notifications received
  * Total notifications successfully sent
  * Total notifications rejected
4. Provide a method to return the **top K users by successful notification count** (sorted by count desc, then userId asc).

Timestamps are strictly increasing across calls.

---

### Interfaces (do not modify)

```java
class Notification {
    String userId;
    String messageType;
    long timestampSeconds;

    public Notification(String userId, String messageType, long timestampSeconds) {
        this.userId = userId;
        this.messageType = messageType;
        this.timestampSeconds = timestampSeconds;
    }
}

interface INotificationSystem {
    boolean send(Notification notification);
    int getTotalReceived();
    int getTotalSent();
    int getTotalRejected();
    List<String> getTopKActiveUsers(int K);
}
```

---

### Requirements

* Implement `NotificationSystem` class.
* Use efficient data structures (constraints go up to 10^5 notifications).
* Sliding window logic must be per user.
* Do NOT use external rate-limiter libraries.
* `getTopKActiveUsers(K)` must return users sorted:

  * Successful sends (desc)
  * userId (asc if tie)

---

### Constraints

* `1 <= total notifications <= 1e5`
* `1 <= N <= 100`
* `1 <= W <= 10^4`
* `1 <= K <= number of unique users`

---

### Starter template (HackerRank style)

```java
import java.util.*;

class Notification {
    String userId;
    String messageType;
    long timestampSeconds;

    public Notification(String userId, String messageType, long timestampSeconds) {
        this.userId = userId;
        this.messageType = messageType;
        this.timestampSeconds = timestampSeconds;
    }
}

interface INotificationSystem {
    boolean send(Notification notification);
    int getTotalReceived();
    int getTotalSent();
    int getTotalRejected();
    List<String> getTopKActiveUsers(int K);
}

public class NotificationSystem implements INotificationSystem {

    private final int maxPerWindow;
    private final long windowSeconds;

    // Add required data structures here

    public NotificationSystem(int maxPerWindow, long windowSeconds) {
        this.maxPerWindow = maxPerWindow;
        this.windowSeconds = windowSeconds;
    }

    @Override
    public boolean send(Notification notification) {
        // TODO: implement rate limiting logic
        return false;
    }

    @Override
    public int getTotalReceived() {
        // TODO
        return 0;
    }

    @Override
    public int getTotalSent() {
        // TODO
        return 0;
    }

    @Override
    public int getTotalRejected() {
        // TODO
        return 0;
    }

    @Override
    public List<String> getTopKActiveUsers(int K) {
        // TODO
        return new ArrayList<>();
    }
}
```

---

### Why this matches M1 / Analyst Level

* Tests OOPS design (per-user state management)
* Requires sliding window logic (DSA inside OOPS)
* Requires correct frequency tracking and sorting
* Has edge cases around boundary timestamps
* Time complexity matters (cannot do O(n^2))

---

💡 Suggested strategy:

* Use a `Map<String, Queue<Long>>` to track per-user timestamps
* Remove timestamps older than `(currentTime - windowSeconds)`
* Maintain a separate success counter map for ranking
* Use sorting or PriorityQueue for top-K users

---

You now have **4 solid Analyst-level problems** in this README. 🚀
