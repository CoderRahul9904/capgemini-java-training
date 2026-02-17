package mock.OOPS;

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


//Help - A user can send at most X maxPerWindow in any rolling Y-windowSeconds
public class NotificationSystem implements INotificationSystem {

    private final int maxPerWindow;
    private final long windowSeconds;
    private HashMap<String, Integer> notificationCountPerUser;
    List<Notification> notifications;
    private int totalReceived;
    private int totalSent;
    private int totalRejected;

    // Add required data structures here

    public NotificationSystem(int maxPerWindow, long windowSeconds) {
        this.maxPerWindow = maxPerWindow;
        this.windowSeconds = windowSeconds;
        this.notificationCountPerUser = new HashMap<>();
        this.notifications = new ArrayList<>();
        this.totalReceived = 0;
        this.totalSent = 0;
        this.totalRejected = 0;
    }

    @Override
    public boolean send(Notification notification) {
        // TODO: implement rate limiting logic
        if(notificationCountPerUser.containsKey(notification.userId)) {
            if(notificationCountPerUser.get(notification.userId) < maxPerWindow && notification.timestampSeconds < windowSeconds){
                notificationCountPerUser.put(notification.userId,notificationCountPerUser.get(notification.userId)+1);
                totalSent++;
                return true;
            }else{
                totalRejected++;
            }
        }
        else if(!notificationCountPerUser.containsKey(notification.userId)) {
            notificationCountPerUser.put(notification.userId, 1);
            totalSent++;
            return true;
        }else {
            totalRejected++;
            return false;
        }

        return false;
    }

    @Override
    public int getTotalReceived() {
        // TODO
        return totalSent;
    }

    @Override
    public int getTotalSent() {
        // TODO
        return totalSent;
    }

    @Override
    public int getTotalRejected() {
        // TODO
        return totalRejected;
    }

    @Override
    public List<String> getTopKActiveUsers(int K) {
        // TODO
        List<String> topActiveUser=new ArrayList<>();
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        pq.addAll(notificationCountPerUser.values());
        int prevMax=-1;
        for(int i=0; i < K; i++){
            int currMax=pq.poll();
            for(Map.Entry<String,Integer> hm: notificationCountPerUser.entrySet()){
                if(hm.getValue() == currMax && currMax!=prevMax) topActiveUser.add(hm.getKey());
                else if(currMax == prevMax){
                    if(topActiveUser.getLast().compareTo(hm.getKey()) == 1){
                        String changeUser=topActiveUser.getLast();
                        topActiveUser.remove(topActiveUser.getLast());
                        topActiveUser.add(changeUser);
                    }else if(topActiveUser.getLast().compareTo(hm.getKey()) == -1){
                        topActiveUser.add(hm.getKey());
                    }
                }
            }
        }
        return topActiveUser;
    }

    public static void main(String[] args) {

        NotificationSystem ns = new NotificationSystem(2, 10);

        System.out.println("===== TEST 1: Basic allowed =====");
        System.out.println(ns.send(new Notification("U1", "EMAIL", 1)));  // true
        System.out.println(ns.send(new Notification("U1", "EMAIL", 2)));  // true
        System.out.println("TotalSent = " + ns.getTotalSent());           // 2
        System.out.println("TotalRejected = " + ns.getTotalRejected());   // 0


        System.out.println("\n===== TEST 2: Exceed rate limit =====");
        System.out.println(ns.send(new Notification("U1", "EMAIL", 3)));  // false (3rd within 10s)
        System.out.println("TotalSent = " + ns.getTotalSent());           // 2
        System.out.println("TotalRejected = " + ns.getTotalRejected());   // 1


        System.out.println("\n===== TEST 3: Sliding window works =====");
        // timestamp = 15, now 1 & 2 are outside 10-sec window
        System.out.println(ns.send(new Notification("U1", "EMAIL", 15))); // true
        System.out.println("TotalSent = " + ns.getTotalSent());           // 3


        System.out.println("\n===== TEST 4: Multiple users independent =====");
        System.out.println(ns.send(new Notification("U2", "SMS", 16)));   // true
        System.out.println(ns.send(new Notification("U2", "SMS", 17)));   // true
        System.out.println(ns.send(new Notification("U2", "SMS", 18)));   // false
        System.out.println("TotalRejected = " + ns.getTotalRejected());   // should increase


        System.out.println("\n===== TEST 5: Top K active users =====");
        System.out.println(ns.send(new Notification("U3", "PUSH", 20)));  // true
        System.out.println(ns.send(new Notification("U3", "PUSH", 21)));  // true

        // Now U1=3, U2=2, U3=2 (successful counts)
        System.out.println(ns.getTopKActiveUsers(2));
        // Expected: ["U1", "U2"]
        // (U2 and U3 both have 2 → lexicographically smaller first)


        System.out.println("\n===== TEST 6: Edge boundary (exact window) =====");
        NotificationSystem ns2 = new NotificationSystem(2, 5);

        System.out.println(ns2.send(new Notification("A", "SMS", 1))); // true
        System.out.println(ns2.send(new Notification("A", "SMS", 6))); // true (1 is now outside window)
        System.out.println(ns2.send(new Notification("A", "SMS", 7))); // false (6 & 7 inside window)
    }

}