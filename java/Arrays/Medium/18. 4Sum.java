import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        
        Arrays.sort(nums);
        
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            // Early pruning
            if ((long)nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target) break;
            if ((long)nums[i] + nums[n-1] + nums[n-2] + nums[n-3] < target) continue;
            
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                
                // Early pruning
                if ((long)nums[i] + nums[j] + nums[j+1] + nums[j+2] > target) break;
                if ((long)nums[i] + nums[j] + nums[n-1] + nums[n-2] < target) continue;
                
                int left = j + 1, right = n - 1;
                
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    
                    if (sum == target) {
                        res.add(Arrays.asList(
                            nums[i], nums[j], nums[left], nums[right]
                        ));
                        
                        left++;
                        right--;
                        
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                        
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        
        return res;
    }
}