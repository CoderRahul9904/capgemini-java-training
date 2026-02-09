1class Solution {
2    public List<Long> mergeAdjacent(int[] nums) {
3        List<Long> stack = new ArrayList<>();
4
5        for (int num : nums) {
6            long curr = num;
7
8            while (!stack.isEmpty() && stack.get(stack.size() - 1) == curr) {
9                stack.remove(stack.size() - 1);
10                curr *= 2;   // long math — no overflow
11            }
12
13            stack.add(curr);
14        }
15
16        return stack;
17    }
18}
19