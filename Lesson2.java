import java.util.*;

public class Lesson2 {
    public static void main(String[] args) {

        LinkedListNode hoge1 = new LinkedListNode(1);
        LinkedListNode hoge2 = new LinkedListNode(2);
        LinkedListNode hoge3 = new LinkedListNode(3, hoge1, hoge2);
        LinkedListNode hoge4 = new LinkedListNode(4);
        LinkedListNode hoge5 = new LinkedListNode(5);
        hoge3.next.setNext(hoge4);
        hoge4.setNext(hoge5);

        System.out.println(hoge3.data);
        //deleteDups(hoge3);
        //deleteNode(hoge4);
        System.out.println(kthToLast(hoge2, 2).data);

        return;
    }

    // 2.1
    // 重複要素の削除
    static void deleteDups(LinkedListNode n) {
        HashSet<Integer> set = new HashSet<Integer>();
        LinkedListNode previous = null;
        while (n != null) {
            if (set.contains(n.next)) {
                previous.next = n.next;
            } else {
                set.add(n.data);
                previous = n;
            }
            n = n.next;
        }
    }

    // 2.2
    // 最後からk番目にアクセス
    // 最後からk番目には、ふたつのポインターを使えば良い
    static LinkedListNode kthToLast(LinkedListNode head, int k) {
        LinkedListNode p1 = head;
        LinkedListNode p2 = head;

        for (int i = 0; i < k; i++) {
            if (p1 == null) return null;
            p1 = p1.next;
        }
        while(p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    // 2.3
    // 単方向連結リストにおいて間の要素の削除
    // 単方向連結リストはdataとnextを持つので、それらを該当の次のnode情報で上書きする
    // 要素の個数が、とかは配列ではないので全く関係ない話。
    // 連結リストでは要素数という概念がない
    // 削除 = 上書き
    static boolean deleteNode(LinkedListNode n) {
        if (n == null || n.next == null) {
            return false;
        }
        LinkedListNode next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;
    }

    // 2.4
    // 連結リストの要素の並び替え
    static LinkedListNode partition(LinkedListNode node, int x) {
        LinkedListNode beforeStart = null;
        LinkedListNode beforeEnd = null;
        LinkedListNode afterStart = null;
        LinkedListNode afterEnd = null;

        while (node != null) {
            LinkedListNode next = node.next;
            // 並び変えるときは隣との鎖を切る
            node.next = null;
            if (node.data < x) {
                if (beforeStart == null) {
                    beforeStart = node;
                    beforeEnd = beforeStart;
                } else {
                    beforeEnd.next = node;
                    beforeEnd = node;
                }
            } else {
                if (afterStart == null) {
                    afterStart = node;
                    afterEnd = afterStart;
                } else {
                    // 連結リストに重複という感覚はないらしい
                    afterEnd.next = node;
                    afterEnd = node;
                }
            }
            node = next;
        }

        if (beforeStart == null) {
            return afterStart;
        }

        // マージはこれだけ
        beforeEnd.next = afterStart;
        return beforeStart;
    }

}



