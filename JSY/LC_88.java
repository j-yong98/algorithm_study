import java.util.Arrays;

public class LC_88 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;

        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int size = m + n;
        int[] temp = nums1;
        int[] temp2 = Arrays.copyOf(nums1, m);
        int index = 0;
        int index1 = 0;
        int index2 = 0;

        while (index1 + index2 < size){
            if(index1 == m){
                temp[index++] = nums2[index2++];
            }else if(index2 == n){
                temp[index++] = temp2[index1++];
            }else{
                if(temp2[index1] <= nums2[index2] && temp2[index1] != 0){
                    temp[index++] = temp2[index1++];
                }else{
                    temp[index++] = nums2[index2++];
                }
            }
        }
    }
}
