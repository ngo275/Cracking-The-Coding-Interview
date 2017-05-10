import java.util.*;

public class Lesson3 {

    public static void main(String[] args) {
        
    }

    // 3.6
    // dogとcatのLinkedListを作ってそれを管理するラッパークラス作成。LinkedListをつかってキューのように扱う。
    // dogとcatはAnimalという抽象クラスから派生させる。
    // addLastやpoll、peekはLinkedListのメソッド。
    // 抽象クラスから具体化するときは、コンストラクタを書く必要あり。super();で良いので。
    static abstract class Animal {
        private int order;
        protected String name;
        Animal(String n) { name = n; }
        void setOrder(int ord) { order = ord; }
        int getOrder() { return order; }

        boolean isOlderThan(Animal a) {
            return this.order < a.order;
        }
    }

    static class AnimalQueue {
        LinkedList<Dog> dogs = new LinkedList<Dog>();
        LinkedList<Cat> cats = new LinkedList<Cat>();
        private int order = 0;

        void enqueue(Animal a) {
            a.setOrder(order);
            order++;

            if (a instanceof Dog) {
                dogs.addLast((Dog)a);
            } else {
                cats.addLast((Cat)a);
            }
        }

        Animal dequeueAny() {
            if (dogs.size() == 0) {
                return dequeueCats();
            } else if (cats.size() == 0) {
                return dequeueDogs();
            }

            Dog dog = dogs.peek();
            Cat cat = cats.peek();
            if (dog.isOlderThan(cat)) {
                return dequeueDogs();
            } else {
                return dequeueCats();
            }
        }

        Dog dequeueDogs() {
            return dogs.poll();
        }

        Cat dequeueCats() {
            return cats.poll();
        }
    }

    static class Dog extends Animal {
        Dog(String n) {
            super(n);
        }
    }

    static class Cat extends Animal {
        Cat(String n) {
            super(n);
        }
    }

}
