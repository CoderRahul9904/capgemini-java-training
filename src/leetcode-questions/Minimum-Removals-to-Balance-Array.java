1class Solution {
2    public int minRemoval(int[] nums, int k) {
3        
4        Arrays.sort(nums);
5
6int start = 0;
7int maxLen = 0;
8
9for (int end = 0; end < nums.length; end++) {
10    while ((long) nums[end] > (long) nums[start] * k) {
11    start++;
12}
13    maxLen = Math.max(maxLen, end - start + 1);
14}
15
16return nums.length - maxLen;
17
18    }
19}