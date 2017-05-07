public class Lesson1 {
    public static void main(String[] args) {
        //System.out.println(powersOf2(50));        
        //powersOf2(50);

        //System.out.println(isUniqueChar(args[0]));
        //System.out.println(isPermutation("hoge", "ehog"));
        //System.out.println(encodeSpace("ho eg hoge ", 11));
        //System.out.println(hasPlindrome2("hogeaoge"));
        //System.out.println(isOneEdittable("apple", "appde"));
        System.out.println(compress("aaaabbbbddddd"));
        return;

    }

    static int powersOf2(int n) {
        if (n < 1) {
            return 0;
        } else if (n == 1) {
            System.out.println(1);
            return 1;
        } else {
            // ここで次のループに入るのを繰り返して、n==1の分岐に入ったらあとはcurrをprintしまくる
            int prev = powersOf2(n / 2);
            int curr = prev * 2;
            System.out.println(curr);
            return curr;
        }

    }

    // 1.1
    // ASCIIだと文字の種類はたかだか128種類になる
    static boolean isUniqueChar(String str) {
        if (str.length() > 256) { return false; }
        boolean[] char_set = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val] == true) {
                return false;
            } else {
                char_set[val] = true;
            }
        }
        return true;
    }

    // 1.2
    //
    static boolean isPermutation(String str1, String str2) {
        if (str1.length() != str2.length()) { return false; }
        char[] char_set1 = str1.toCharArray();
        char[] char_set2 = str2.toCharArray();
        //char[] sorted_char_set1 = java.util.Arrays.sort(char_set1);
        //char[] sorted_char_set2 = java.util.Arrays.sort(char_set2);
        java.util.Arrays.sort(char_set1);
        java.util.Arrays.sort(char_set2);
        String sorted_str1 = new String(char_set1);
        String sorted_str2 = new String(char_set2);
        return sorted_str1.equals(sorted_str2);
    }

    // 1.3
    // %20にする問題
    static String encodeSpace(String str, int num) {
        int spaceCount = 0;
        for (int i = 0; i < num; i++) {
            if (str.charAt(i) == ' ') {
                System.out.println("found a space!!");
                spaceCount++;
            }
        }
        int trueNum = num + spaceCount * 2;

        char[] charSet = new char[trueNum];
        int tmpSpaceCount = 0;
        for (int i = 0; i < num; i++) {
            if (str.charAt(i) == ' ') {
                charSet[i + tmpSpaceCount] = '%';
                charSet[i + tmpSpaceCount + 1] = '2';
                charSet[i + tmpSpaceCount + 2] = '0';
                tmpSpaceCount += 2;
            } else {
                charSet[i + tmpSpaceCount] = str.charAt(i);
            }
        }

        return String.valueOf(charSet);
    }

    // 1.4
    // 回文を含むかどうか
    static boolean hasPlindrome(String str) {
        if (str.length() % 2 == 0) {
            for (int i = 0; i < str.length(); i++) {
                int iCount = 0;
                for (int j = 0; j < str.length(); j++) {
                    if (str.charAt(i) == str.charAt(j)) { iCount++; }
                }
                if (iCount % 2 == 1) { return false; }
            }
            return true;
        } else {
            boolean isFirst = true;
            for (int i = 0; i < str.length(); i++) {
                int iCount = 0;
                for (int j = 0; j < str.length(); j++) {
                    if (str.charAt(i) == str.charAt(j)) { iCount++; }
                }
                if (iCount % 2 == 1) {
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    // 1.4 別解
    // アルファベットの表をつくる
    static int[] getAlphabetTable(String str) {
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (char c : str.toCharArray()) {
            int index = getIndex(c);
            if (index != -1) { table[index]++; }
        }
        return table;
    }

    static int getIndex(char ch) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int c = Character.getNumericValue(ch);
        int num = c - a;
        if (a <= c && c <= z) { return num; }
        return -1;
    }

    // 1.4 別解
    // ビット列を使ってライトのオン・オフと同様のことをする
    static int createBitVector(String str) {
        int bitVector = 0;
        for (char c : str.toCharArray()) {
            int index = getIndex(c);
            bitVector = toggle(bitVector, index);
        }
        return bitVector;
    }

    static int toggle(int bitVector, int index) {
        if (index < 0) { return bitVector; }
        int mask = 1 << index;
        if ((bitVector & mask) == 0) {
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }
        return bitVector;
    }

    static boolean checkBitVectorSingleOne(int bitVector) {
        return ((bitVector - 1) & bitVector) == 0;
    }

    static boolean hasPlindrome2(String str) {
        int bitVector = createBitVector(str);
        return bitVector == 0 || checkBitVectorSingleOne(bitVector);
    }

    // 1.5
    // 2つのstringの比較
    static boolean isOneEdittable(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        String shortOne = first.length() < second.length() ? first : second;
        String longOne = first.length() < second.length() ? second : first;

        int index1 = 0;
        int index2 = 0;

        boolean isFirst = true;
        while (index1 < shortOne.length() && index2 < longOne.length()) {
            if (shortOne.charAt(index1) != longOne.charAt(index2)) {
                if (isFirst) {
                    isFirst = false;
                    if (shortOne.length() != longOne.length()) {
                        index2 += 1;
                    }
                } else {
                    return false;
                }
            }
            index1++;
            index2++;
        }
        return true;
    }

    // 1.6
    // 文字列圧縮 うまくいかない
    // => StringBuilderとか使うと簡単よ
    static String badCompress(String str) {
        char[] charSet = str.toCharArray();
        char[] answer = new char[str.length()];
        char prevChar = ' ';
        char typeCount = 0;
        char charCount = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = charSet[i];
            if (i != str.length() - 1) {
                // 同じ文字が出てきた時
                if (c == prevChar) {
                    charCount++;
                // 新しい文字が出てきた時
                } else {
                    answer[typeCount + 1] = c;
                    if (i != 0) {
                        answer[typeCount] = charCount;
                    }
                    prevChar = c;
                    typeCount++;
                    charCount = 1;
                }
            } else if (i == str.length() - 1) {
                // 上の処理を繰り返す
                if (c == prevChar) {
                    charCount++;
                    answer[typeCount + 1] = charCount;
                // 新しい文字が出てきた時
                } else {
                    answer[typeCount] = c;
                    answer[typeCount + 1] = 1;
                }
            }
            System.out.println(answer);
        }
        return String.valueOf(answer);
    }

    // 1.6 別解 
    // StringBuilderを使う
    static String compress(String str) {
        StringBuilder compressed = new StringBuilder();
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressed.length() < str.length() ? compressed.toString() : str;
    }

    // 1.7
    // rotate
    static boolean rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) {
            return false;
        }
        int n = matrix.length;
        for (int layer = 0; layer < n /2; layer++) {
            int first = layer;
            int last = n - layer - 1;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i];
                matrix[first][i] = matrix[last - offset][first];
                matrix[last - offset][first] = matrix[last][last - offset];
                matrix[last][last - offset] = matrix[i][last];
                matrix[i][last] = top;
            }
        }
        return true;
    }

    // 1.8
    // MN行列の一部を0クリアする
    static void setZeros(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (row[i]) nullifyRow(matrix, i);
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (column[i]) nullifyColumn(matrix, i);
        }
    }

    static void nullifyRow(int[][] matrix, int row) {
        for (int i = 0; i < row; i++) {
            matrix[row][i] = 0;
        }
    }

    static void nullifyColumn(int[][] matrix, int column) {
        for (int i = 0; i < column; i++) {
            matrix[i][column] = 0;
        }
    }

    // 1.9
    // 文字列の回転を判別
    /*static boolean isRotate(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        String fullWord = s1 + s1;
        return s2.subString(fullWord);
    }*/
}


