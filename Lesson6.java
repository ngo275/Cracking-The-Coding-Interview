import java.util.Random;

public class Lesson6 {
    // 6.7
    // 女の子が生まれるまで子供を産むときに男女比はどうなるのか
    public static void main(String[] args) {
        int boyCount = 0;
        int girlCount = 0;
        for (int i = 1; i < 10000000; i++) {
            int[] gender = doOneFamily();
            boyCount += gender[0];
            girlCount += gender[1];
        }
        int rate = boyCount / (boyCount + girlCount);
        System.out.println(rate);
    }

    public static int[] doOneFamily() {
        int boyCount = 0;
        int girlCount = 0;
        Random nextGender = new Random();
        while (girlCount == 0) {
            if (nextGender.nextBoolean()) {
                girlCount++;
            } else {
                boyCount++;
            }
        }
        int[] gender = {boyCount, girlCount};
        return gender;
    }
}
