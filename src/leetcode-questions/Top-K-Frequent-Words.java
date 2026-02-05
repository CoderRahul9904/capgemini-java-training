1class Solution {
2    public List<String> topKFrequent(String[] words, int k) {
3        Map<String, Integer> map = new HashMap<>();
4
5        for (String word : words) {
6            map.put(word, map.getOrDefault(word, 0) + 1);
7        }
8
9        PriorityQueue<String> pq = new PriorityQueue<>(
10            (a, b) -> {
11                if (map.get(a).equals(map.get(b))) {
12                    return a.compareTo(b);
13                }
14                return map.get(b) - map.get(a);
15            }
16        );
17
18        pq.addAll(map.keySet());
19
20        List<String> result = new ArrayList<>();
21        for (int i = 0; i < k; i++) {
22            result.add(pq.poll());
23        }
24
25        return result;
26    }
27}
28