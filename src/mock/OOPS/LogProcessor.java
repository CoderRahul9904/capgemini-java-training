package mock.OOPS;

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
    public long convertDate(String date, String time) {
        String dateFormatArr[]=date.split("-");
        String timeFormatArr[]=time.split(":");
        String dateAndTimeFormat=dateFormatArr[2]+dateFormatArr[1]+dateFormatArr[0]+timeFormatArr[0]+timeFormatArr[1];
        return Long.parseLong(dateAndTimeFormat);
    }
    List<LogEntry> entries;
    HashMap<String,Integer> hmp;
    public LogProcessor(List<LogEntry> entries) {
        // init
        this.entries = entries;
        this.hmp = new HashMap<>();
    }

    @Override
    public void ingest(LogEntry entry) {
        // TODO: implement
        entries.add(entry);
    }

    @Override
    public List<String> query(String dateFrom, String dateTo, String statusFilter, int M) {
        // TODO: implement
        List<String> mostFreqMessages=new ArrayList<>();
        PriorityQueue<Integer> pq=new PriorityQueue<>(Comparator.reverseOrder());
//        int maxFreqMessageCount=Collections.max(hmp.values());
        String morningDefault="00:00";
        String nightDefault="23:59";
        long rangeFrom=convertDate(dateFrom,morningDefault);
        long rangeTo=convertDate(dateTo,nightDefault);
        List<LogEntry> filteredEntries=entries.stream().filter(entry -> entry.status.equals(statusFilter)).filter(entry -> convertDate(entry.date,entry.time) >= rangeFrom && convertDate(entry.date,entry.time) <= rangeTo).toList();
        for(LogEntry entry:filteredEntries) {
            hmp.put(entry.message, hmp.getOrDefault(entry.message,0)+1);
        }
        
        for(int num: hmp.values()) {
            pq.add(num);
        }
        for(int i=1; i <= M; i++) {
            for(Map.Entry<String,Integer> entry: hmp.entrySet()) {
                if(hmp.get(entry.getKey()) == pq.poll()) {
                    mostFreqMessages.add(entry.getKey());
                }
            }
        }
        return mostFreqMessages;
    }

    public static void main(String[] args) {

        List<LogEntry> list = new ArrayList<>();
        LogProcessor lp = new LogProcessor(list);

        lp.ingest(new LogEntry("01-01-2024", "09:05", "ERROR", "db down"));
        lp.ingest(new LogEntry("01-01-2024", "09:10", "ERROR", "db down"));
        lp.ingest(new LogEntry("01-01-2024", "10:00", "ERROR", "timeout"));
        lp.ingest(new LogEntry("02-01-2024", "11:00", "INFO", "started"));
        lp.ingest(new LogEntry("02-01-2024", "11:05", "ERROR", "timeout"));
        lp.ingest(new LogEntry("03-01-2024", "12:00", "ERROR", "db down"));

        // TEST 1: Basic frequency
        System.out.println(
                lp.query("01-01-2024", "03-01-2024", "ERROR", 1)
        ); // Expected: ["db down"]

        // TEST 2: Top 2 messages
        System.out.println(
                lp.query("01-01-2024", "03-01-2024", "ERROR", 2)
        ); // Expected: ["db down", "timeout"]

        // TEST 3: Date range exclusion
        System.out.println(
                lp.query("02-01-2024", "02-01-2024", "ERROR", 2)
        ); // Expected: ["timeout"]

        // TEST 4: Status filter
        System.out.println(
                lp.query("01-01-2024", "03-01-2024", "INFO", 1)
        ); // Expected: ["started"]

        // TEST 5: Lexicographic tie
        lp.ingest(new LogEntry("04-01-2024", "10:00", "ERROR", "aaa"));
        lp.ingest(new LogEntry("04-01-2024", "10:01", "ERROR", "bbb"));

        System.out.println(
                lp.query("01-01-2024", "04-01-2024", "ERROR", 2)
        ); // Expected sorted lexicographically if same freq

        // TEST 6: Time boundary (same date)
        lp.ingest(new LogEntry("05-01-2024", "09:00", "ERROR", "early"));
        lp.ingest(new LogEntry("05-01-2024", "23:00", "ERROR", "late"));

        System.out.println(
                lp.query("05-01-2024", "05-01-2024", "ERROR", 2)
        ); // Should consider time too
    }

}