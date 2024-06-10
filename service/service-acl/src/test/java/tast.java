/**
 * ClassName: tast
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author liuux
 * @Create 2024/3/15 18:53
 * @Version 1.0
 */
public class tast {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,4,5,5,6,7,9};
        int num = 5;
        int[] ans = new int[2];
        ans = searchRange(nums, num);
        for (int it:ans) {
            System.out.println(it);
        }
    }

    public static int[] searchRange(int[] nums, int target) {
        int r = nums.length - 1;
        return new int[]{left(nums, 0, r, target), right(nums, 0, r, target)};
    }

    public static int left(int[] nums, int l, int r, int target) {
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        if (nums[l] == target) return l;
        return -1;
    }

    public static int right(int[] nums, int l, int r, int target) {
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= target) l = mid;
            else r = mid - 1;
        }
        if (nums[l] == target) return l;
        return -1;
    }
}


//class Solution {
//    public int[] searchRange(int[] nums, int target) {
//        int r = nums.length - 1;
//        return new int[]{left(nums, 0, r, target), right(nums, 0, r, target)};
//    }
//
//    public static int left(int[] nums, int l, int r, int target) {
//        while (l < r) {
//            int mid = l + r >> 1;
//            if (nums[mid] >= target) r = mid;
//            else l = mid + 1;
//        }
//
//        return -1;
//    }
//
//    public static int right(int[] nums, int l, int r, int target) {
//        while (l < r) {
//            int mid = l + r + 1 >> 1;
//            if (nums[mid] <= target) l = mid;
//            else r = mid - 1;
//        }
//        if (nums[l] == target) return l;
//        return -1;
//    }
//}
