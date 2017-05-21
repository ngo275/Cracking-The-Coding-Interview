import java.util.*;
import java.lang.*;

public class Lesson5 {
    public static void main(String[] args) {
        int answer = setShortOne(100000000, 10011, 2, 6);
        System.out.println(answer);
    }

    // 5.1
    static int setShortOne(int longer, int shorter, int i, int j) {
        int mask1 = 1 << (j + 1) - 1;
        int mask2 = 1 << (i + 1) - 1;
        int mask = ~(mask1 - mask2);
        return (longer & mask) | (shorter << i);
    }

    // 5.2
    static String printBinary(double num) {
        if (num >= 1 | num <= 0) { return "ERROR"; }

        StringBuilder binary = new StringBuilder();
        binary.append(".");

        while(num > 0) {
            if (binary.length() >= 32) { return "ERROR"; }

            double r = num * 2;
            if (r >= 1) {
                binary.append(1);
                num = r - 1;
            } else {
                binary.append(0);
                num = r;
            }
        }
        return binary.toString();
    }

    // 5.3
    // 10011101101とかからひとつだけbitを入れ替えて連続する1の数が最大となるときの値を求める
    /*static int flipBit(int a) {
        if (~a == 0) return Integer.BYTES * 8;

        int currentLength = 0;
        int previousLength = 0;
        int maxLength = 1;

        while (a != 0) {
            if ((a & 1) == 1) {
                currentLength++;
            } else if ((a & 1) == 0) {
                previousLength = (a & 2) == 0 ? 0 : currentLength;
                currentLength = 0;
            }
            maxLength = Math.max(previousLength + currentLength + 1, maxLength);
            a >>>= 1;
        }
        return maxLength;
    }*/

    // 5.6
    // ビット変換
    // bit関連でforを使うときは、c != 0でc >> 1していく
    static int bitSwap(int a, int b) {
        int count = 0;
        for (int c = a ^ b; c != 0; c = c >> 1) {
            count += c & 1;
        }
        return count;
    }
}
