// 2017を虚数を使って素因数分解する
public class Excercise {
    public static void main(String[] args) {
        int n = 2017;
        for (int i = 1; i < Math.sqrt(n); i++) {
            for (int j = i; j < Math.sqrt(n); j++) {
                if (i * i + j * j == n) {
                    System.out.println(i);
                    System.out.println(j);
                    return;
                }
            }
        }
    }
}
