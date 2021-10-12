package com.aizhong;

public class Main {
    public static void main(String[] args) {

    }

    public int method(int nums) {
        int res = 0;
        int cnt = 0;
        for (int i = nums; cnt < 10 && i >= 0; i--) {
            if (i % 13 == 0 || i % 17 == 0) {
                cnt++;
                res += i;
            }
        }
        return res;
    }
}
