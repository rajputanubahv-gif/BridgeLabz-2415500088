import java.util.*;
class LongestConsecutiveSequence {
    static int longest(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int longest = 0;
        for (int num : set) {
            if (!set.contains(num-1)) {
                int curr = num, len = 1;
                while (set.contains(curr+1)) {
                    curr++; len++;
                }
                longest = Math.max(longest, len);
            }
        }
        return longest;
    }
    public static void main(String[] args) {
        int[] arr = {100,4,200,1,3,2};
        System.out.println(longest(arr));
    }
}