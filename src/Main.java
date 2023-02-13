import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MinHeap<Integer> numbers = new MinHeap<>();
//        for (int i = 0; i < 15; i++){
//            Random randI = new Random();
//            int myRandInt = randI.nextInt(100);
//            myRandInt = myRandInt + 1;
//            numbers.add(myRandInt);
//        }
        numbers.add(0);
        numbers.add(7);
        numbers.add(14);
        numbers.add(28);
        numbers.add(21);
        numbers.add(35);
        numbers.add(49);
        numbers.add(42);
        numbers.add(56);
        numbers.add(63);
        numbers.add(70);
        System.out.println("hey");
        System.out.println(numbers.remove());
        System.out.println("hey");
    }
}



