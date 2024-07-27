package RandomDeeds;

public class JavaRandom {

	public static void main(String[] args) {
	int nums[] = {-1, 3, 5, 6};
	
System.out.println(	searchInsert(nums, 0));
	}
	
	static public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int result = 0;

        while (l <= r) {
            int mid = (l + r) / 2;
            
            if (nums[mid] == target) 
            	return mid;
            else if (target < nums[mid]) 
                r = mid - 1;
            
            else if (target > nums[mid])
                l = mid + 1;
            

        }
        
        if (result == -1 && l > target)  result = l +1;
        else result = l;
        return result;
       
    }

}
