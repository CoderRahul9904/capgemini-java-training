package mock.OOPS;

import java.util.*;

// Enum
enum ActionEnum {
    OPEN, CLOSE, READ, WRITE, DELETE;
}

// Analytics interface
interface IAnalytics {
    void registerAction(ActionEnum action);
    int getNumberOfActionRegisteredButNotSentToAnalyticsStore();
    int getTotalNumberOfLoggedActions();
    List<ActionEnum> getMostFrequentlyUsedActions();
}

// Store interface
interface IAnalyticsStore {
    void storeActions(Queue<ActionEnum> actions);
}

// Analytics implementation
public class Analytics implements IAnalytics {

    private final IAnalyticsStore analyticsStore;
    private final int k;

    private final Queue<ActionEnum> actions;
    private final Map<ActionEnum, Integer> frequentlyUsedActions;
    private int totalNumberOfLoggedActions;

    public Analytics(IAnalyticsStore analyticsStore, int k) {
        this.analyticsStore = analyticsStore;
        this.k = k;
        this.actions = new LinkedList<>();
        this.frequentlyUsedActions = new HashMap<>();
        this.totalNumberOfLoggedActions = 0;
    }

    @Override
    public void registerAction(ActionEnum action) {

        // Total count
        totalNumberOfLoggedActions++;

        // Frequency count
        frequentlyUsedActions.put(
                action,
                frequentlyUsedActions.getOrDefault(action, 0) + 1
        );

        // Add to buffer
        actions.add(action);

        // Send batch when size reaches k
        if (actions.size() == k) {
            analyticsStore.storeActions(new LinkedList<>(actions)); // IMPORTANT: send copy
            actions.clear();
        }
    }

    @Override
    public int getNumberOfActionRegisteredButNotSentToAnalyticsStore() {
        return actions.size();
    }

    @Override
    public int getTotalNumberOfLoggedActions() {
        return totalNumberOfLoggedActions;
    }

    @Override
    public List<ActionEnum> getMostFrequentlyUsedActions() {

        if (frequentlyUsedActions.isEmpty()) {
            return new ArrayList<>();
        }

        // Find max frequency
        int maxFreq = Collections.max(frequentlyUsedActions.values());

        // Collect all actions with max frequency
        List<ActionEnum> result = new ArrayList<>();
        for (Map.Entry<ActionEnum, Integer> entry : frequentlyUsedActions.entrySet()) {
            if (entry.getValue() == maxFreq) {
                result.add(entry.getKey());
            }
        }

        // Sort alphabetically (matches expected output behavior)
        result.sort(Comparator.comparing(Enum::name));

        return result;
    }
}
