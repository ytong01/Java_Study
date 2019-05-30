package cn.cloudwalk.learn02;

public class TestArray {

    public static void main(String[] args) {

        int[] num = {1, 2, 2, 3, 4, 5, 6, 7, 8, 9};
        int sum = 11;
        findSum2(num, sum);
    }

    private static void findSum(int[] num, int sum) {

        int left = 0, right = 0;
        int temp = 0;
        while (left < num.length) {
            for (int i = left; i < num.length; i++) {
                temp += num[i];
                if (temp == sum) {
                    right = i;
                    for (int j = left; j <= right; j++) {
                        System.out.print(num[j] + "  ");
                    }
                    System.out.println();
                }
            }
            temp = 0;
            left++;
        }
    }

    private static void findSum2(int[] num, int sum) {

        int left = 0;
        int right = 0;

        for (int i = 0; i < num.length; i++) {
            int curSum = 0;
            left = i;
            right = i;
            while (curSum < sum) {
                curSum += num[right++];
            }
            if (curSum == sum) {
                for (int j = left; j < right; j++) {
                    System.out.print(num[j] + " ");
                }
                System.out.println();
            }
        }
    }
}
