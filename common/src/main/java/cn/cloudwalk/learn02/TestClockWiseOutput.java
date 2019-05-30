package cn.cloudwalk.learn02;

public class TestClockWiseOutput {

    public static void main(String[] args) {

        int[][] num = new int[6][6];
        int n = 6;
        int count = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                num[i][j] = count++;
            }
        }
        output(num, 0, n - 1);
    }

    private static void output(int[][] num, int start, int end) {

        if (start > end || end <= 0) {
            return;
        }

        for (int i = start; i < end; i++) {
            System.out.print(num[start][i] + "  ");
        }

        for (int i = start + 1; i < end; i++) {
            System.out.print(num[i][end] + "  ");
        }

        for (int i = end - 1; i > 0; i--) {
            System.out.print(num[end][i] + "  ");
        }

        for (int i = end - 1; i >= 0; i--) {
            System.out.print(num[i][start] + "  ");
        }
        output(num, start + 1, end - 1);
    }
}
