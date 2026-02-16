1class Solution {
2    public List<List<Integer>> threeSum(int[] arr) {
3        List<List<Integer>> res = new ArrayList<>();
4        int n = arr.length;
5        if (n < 3) return res;
6
7        Arrays.sort(arr);
8
9        for (int i = 0; i < n - 2; i++) {
10
11            // Skip duplicate i values
12            if (i > 0 && arr[i] == arr[i - 1]) continue;
13
14            int j = i + 1;
15            int k = n - 1;
16
17            while (j < k) {
18                int sum = arr[i] + arr[j] + arr[k];
19
20                if (sum == 0) {
21                    res.add(Arrays.asList(arr[i], arr[j], arr[k]));
22                    j++;
23                    k--;
24
25                    // Skip duplicates for j
26                    while (j < k && arr[j] == arr[j - 1]) j++;
27
28                    // Skip duplicates for k
29                    while (j < k && arr[k] == arr[k + 1]) k--;
30
31                } else if (sum < 0) {
32                    j++;
33                } else {
34                    k--;
35                }
36            }
37        }
38        return res;
39    }
40}
41