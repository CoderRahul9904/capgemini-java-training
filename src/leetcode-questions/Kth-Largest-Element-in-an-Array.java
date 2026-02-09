1class Solution {
2    public int findKthLargest(int[] nums, int k) {
3        PriorityQueue<Integer> pq=new PriorityQueue<Integer>(Comparator.reverseOrder());
4        for(int i=0; i < nums.length; i++){
5            pq.add(nums[i]);
6        }
7        int kthMax=Integer.MIN_VALUE;
8        for(int i=1; i < k; i++){
9            pq.poll();
10        }
11        return pq.poll();
12    }
13}