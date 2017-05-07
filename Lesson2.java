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
        LinkedListNode hoge6 = hoge2.clone();
        System.out.println(addLists(hoge6, hoge2, 0).next.next.data);
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
            // このように並び変えるときは隣との鎖を切る
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

    // 2.4 別解
    // 冷静に4つもリスト持つのはナンセンスだよね、と
    static LinkedListNode partition2(LinkedListNode node, int x) {
        LinkedListNode head = node;
        LinkedListNode tail = node;

        while (node != null) {
            LinkedListNode next = node.next;

            if (node.data < x) {
                node.next = head;
                // headの更新
                head = node;
            } else {
                // node.nextは変える必要なし
                tail.next = node;
                // tailの更新
                tail = node;
            }
            node = next;
        }
        tail.next = null;

        // 配列を返すわけではなく、単方向なので先頭だけで十分
        return head;
    }

    // 2.5
    // 連結リストで足し算
    static LinkedListNode addLists(LinkedListNode node1, LinkedListNode node2, int carry) {
        if (node1 == null && node2 == null && carry == 0) return null;

        LinkedListNode result = new LinkedListNode();

        int value = carry;

        if (node1 != null) {
            value += node1.data;
        }
        if (node2 != null) {
            value += node2.data;
        }
        result.data = value % 10;
        int nextCarry = value > 9 ? 1 : 0;

        // ここの再帰のイメージは以下のように
        // result.setNext(addLists(node1.next, node2.next, nextCarry));

        if (node1 != null || node2 != null) {
            LinkedListNode more = addLists(node1 == null ? null : node1.next, node2 == null ? null : node2.next, nextCarry);
            result.setNext(more);
        }

        return result;
    }

    // 2.6
    // 回文リストか判別
    static boolean isPalidrome(LinkedListNode head) {
        LinkedListNode reversed = reverseAndClone(head);
        return isEqual(head, reversed);
    }

    static LinkedListNode reverseAndClone(LinkedListNode node) {
        LinkedListNode head = null;
        while (node != null) {
            LinkedListNode n = new LinkedListNode(node.data);
            n.next = head;
            head = n;
            node = node.next;
        }
        return head;
    }

    static boolean isEqual(LinkedListNode one, LinkedListNode two) {
        while (one != null && two != null) {
            if (one.data != two.data) {
                return false;
            }
            one = one.next;
            two = two.next;
        }
        return one == null && two == null;
    }
}



