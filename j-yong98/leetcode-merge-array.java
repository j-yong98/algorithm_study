class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;

        int idx = 0;
        int[] arr = new int[m + n];
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                arr[idx++] = nums1[i++];
            } else {
                arr[idx++] = nums2[j++];
            }
        }

        while (i < m) {
            arr[idx++] = nums1[i++];
        }

        while (j < n) {
            arr[idx++] = nums2[j++];
        }

        for (int k = 0; k < idx; k++) {
            nums1[k] = arr[k];
        }
    }

}
