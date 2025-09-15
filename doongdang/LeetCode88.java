class Solution {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int stdPointer = 0;
    int pointer1 = 0;
    int pointer2 = 0;
    int[] copyOfNums1 = Arrays.copyOf(nums1, m);

    while (stdPointer < m + n) {
      if (pointer1 == m) {
        while (pointer2 < n) {
          nums1[stdPointer++] = nums2[pointer2++];
        }
        break;
      }

      if (pointer2 == n) {
        while (pointer1 < m) {
          nums1[stdPointer++] = copyOfNums1[pointer1++];
        }
        break;
      }

      if (copyOfNums1[pointer1] <= nums2[pointer2]) {
        nums1[stdPointer++] = copyOfNums1[pointer1++];
      } else {
        nums1[stdPointer++] = nums2[pointer2++];
      }
    }
  }
}