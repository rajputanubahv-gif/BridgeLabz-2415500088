import java.util.*;
class PairWithGivenSum {
    static boolean hasPair(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (set.contains(target - num)) return true;
            set.add(num);
        }
        return false;
    }
    public static void main(String[] args) {
        int[] arr = {1,4,45,6,10};
        System.out.println(hasPair(arr,16));
    }
}