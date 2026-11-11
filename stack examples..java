import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;
public class AllStackExamples 

    public static void main(String[] args) {
        example1();
        example2();
        example3();
        example4();
        example5();
        example6();
        example7And8();
        example9();
        example10();
        example11();
        example13();
        example14();
        example15();
    }
    // EXAMPLE 1: Push and print a stack
    static void example1() 
        System.out.println("\n--- Example 1: Push and print a stack ---");
        Stack<Integer> s1 = new Stack<Integer>();
        s1.push(10);
        s1.push(20);
        s1.push(30);
        System.out.println(s1.toString());
        // EXAMPLE 2: peek() looks; pop() takes
        static void example2() {
        System.out.println("\n--- Example 2: peek() and pop() ---");
        Stack<Integer> s1 = new Stack<Integer>();
        s1.push(10);
        s1.push(20);
        s1.push(30);
        System.out.println("Original Stack: " + s1);
        int top = s1.peek();
        System.out.println("top element: " + top);
        System.out.println("Stack after accessing top element: " + s1);
        int out = s1.pop();
        System.out.println("Removed element: " + out);
        System.out.println("Stack after Pop Operation: " + s1);
    }
     static class Box<T> {
        private T item;
        public void set(T item) { this.item = item; }
        public T get() { return item; }
    }
// EXAMPLE 3: Generics — one class, many types
    static void example3() {
        System.out.println("\n--- Example 3: Generics ---");
        Box<Integer> box1 = new Box<Integer>();
        box1.set(25);
        int age = box1.get();

        Box<String> box2 = new Box<String>();
        box2.set("Ali");
        String name = box2.get();

        System.out.println("box1 holds " + age);
        System.out.println("box2 holds " + name);
    }
    static class BankAccount {
        private double balance;
        private Stack<Double> history = new Stack<Double>();

        public BankAccount(double balance) { this.balance = balance; }

        public void deposit(double amount) {
            balance = balance + amount;
            history.push(amount);
            System.out.println("Deposit " + amount + " -> balance " + balance);
        }

        public void undoLastDeposit() {
            if (history.isEmpty()) {
                System.out.println("Nothing to undo");
                return;
            }
            double last = history.pop();
            balance = balance - last;
            System.out.println("Undo " + last + " -> balance " + balance);
        }
    }

    static void example4() {
        System.out.println("\n--- Example 4: Bank undo deposit ---");
        BankAccount acc = new BankAccount(100);
        acc.deposit(50);
        acc.deposit(20);
        acc.deposit(30);
        acc.undoLastDeposit();
        acc.undoLastDeposit();
    }
// EXAMPLE 5: Student — undo the last course
    static class Registration {
        private String studentName;
        private Stack<String> courses = new Stack<String>();

        public Registration(String studentName) {
            this.studentName = studentName;
        }

        public void addCourse(String code) {
            courses.push(code);
            System.out.println("Added " + code + " -> " + courses);
        }

        public void undoLastCourse() {
            if (courses.isEmpty()) {
                System.out.println("Nothing to undo");
                return;
            }
            String removed = courses.pop();
            System.out.println("Removed " + removed + " -> " + courses);
        }
    }

    static void example5() {
        System.out.println("\n--- Example 5: Student undo course ---");
        Registration reg = new Registration("Hodan");
        reg.addCourse("CA314");
        reg.addCourse("CA301");
        reg.addCourse("MA210");
        reg.undoLastCourse();
    }
    // EXAMPLE 6: Reversing a string
    static class Reverser {
        public String reverse(String input) {
            Stack<Character> stack = new Stack<Character>();
            for (int i = 0; i < input.length(); i++) {
                stack.push(input.charAt(i));
            }
            String reversed = "";
            while (!stack.isEmpty()) {
                reversed += stack.pop();
            }
            return reversed;
        }
    }

    static void example6() {
        System.out.println("\n--- Example 6: Reversing a string ---");
        String input = "JAVA";
        Reverser test = new Reverser();
        String result = test.reverse(input);
        System.out.println("Enter String to Reverse: " + input);
        System.out.println("Reversed String: " + result);
    }
 // EXAMPLE 7: ArrayStack — full implementation
    static class ArrayStack<T> {
        private static final int DEFAULT_CAPACITY = 2;
        private int top;
        private T[] stack;

        public ArrayStack() {
            this(DEFAULT_CAPACITY);
        }

        SuppressWarnings("unchecked")
        public ArrayStack(int initialCapacity) {
            top = 0;
            stack = (T[]) new Object[initialCapacity];
        }

        public void push(T element) {
            if (size() == stack.length) {
                expandCapacity();
            }
            stack[top] = element;
            top++;
        }

        private void expandCapacity() {
            stack = Arrays.copyOf(stack, stack.length * 2);
        }

        public T pop() throws EmptyStackException {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            top--;
            T result = stack[top];
            stack[top] = null;
            return result;
        }

        public T peek() throws EmptyStackException {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return stack[top - 1];
        }

        public int size() { return top; }
        public boolean isEmpty() { return top == 0; }
        public int capacity() { return stack.length; }
    }
    // EXAMPLE 8: ArrayStack trace
    static void example7And8() {
        System.out.println("\n--- Example 7: ArrayStack implementation / Example 8: trace ---");
        ArrayStack<String> s = new ArrayStack<String>();
        s.push("A");
        s.push("B");
        System.out.println("size " + s.size() + ", capacity " + s.capacity());
        s.push("C");
        System.out.println("size " + s.size() + ", capacity " + s.capacity());
        System.out.println("peek: " + s.peek());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        try {
            System.out.println("pop: " + s.pop());
        } catch (EmptyStackException e) {
            System.out.println("Exception: EmptyStackException");
        }
    }
    // EXAMPLE 9: Defining our own exception
    static class EmptyCollectionException extends RuntimeException {
        public EmptyCollectionException(String collection) {
            super("The " + collection + " is empty.");
        }
    }

    static void example9() {
        System.out.println("\n--- Example 9: Custom exception ---");
        try {
            throw new EmptyCollectionException("stack");
        } catch (EmptyCollectionException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }

    // Node class used by Examples 10, 11, 12, 13, 14 and 15
    static class Node<T> {
        private T element;
        private Node<T> next;

        public Node() {
            element = null;
            next = null;
        }

        public Node(T element) {
            this.element = element;
            next = null;
        }

        public Node<T> getNext() { return next; }
        public void setNext(Node<T> next) { this.next = next; }
        public T getElement() { return element; }
        public void setElement(T element) { this.element = element; }
    }
 // EXAMPLE 10: Linked list — traverse the chain
    static void example10() {
        System.out.println("\n--- Example 10: Linked list traversal ---");
        Node<Integer> head = new Node<Integer>(17);
        Node<Integer> n2 = new Node<Integer>(92);
        Node<Integer> n3 = new Node<Integer>(63);
        Node<Integer> n4 = new Node<Integer>(45);
        head.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);

        Node<Integer> current = head;
        while (current != null) {
            System.out.println(current.getElement());
            current = current.getNext();
        }
    }
 // EXAMPLE 11: Insert at the front — order matters
    static void printNodes(String label, Node<String> head) {
        System.out.print(label);
        Node<String> cur = head;
        for (int i = 0; i < 5 && cur != null; i++) {
            System.out.print(cur.getElement() + " -> ");
            cur = cur.getNext();
        }
        System.out.println(cur == null ? "null" : "...");
    }

    static void example11() {
        System.out.println("\n--- Example 11: Insert at the front ---");
        Node<String> head = new Node<String>("B");
        Node<String> newNode = new Node<String>("A");
        newNode.setNext(head);
        head = newNode;
        printNodes("Correct: ", head);

        head = new Node<String>("B");
        newNode = new Node<String>("A");
        head = newNode;
        newNode.setNext(head);
        printNodes("Swapped: ", head);
    }
// EXAMPLE 12: LinkedStack — full implementation
    static class LinkedStack<T> {
        private int count;
        private Node<T> top;

        public LinkedStack() {
            count = 0;
            top = null;
        }

        public void push(T element) {
            Node<T> temp = new Node<T>(element);
            temp.setNext(top);
            top = temp;
            count++;
        }

        public T pop() throws EmptyStackException {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            T result = top.getElement();
            top = top.getNext();
            count--;
            return result;
        }

        public T peek() throws EmptyStackException {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return top.getElement();
        }

        public boolean isEmpty() { return count == 0; }
        public int size() { return count; }
    }
// EXAMPLE 13: LinkedStack trace
    static void example13() {
        System.out.println("\n--- Example 13: LinkedStack trace ---");
        LinkedStack<String> s = new LinkedStack<String>();
        s.push("A");
        s.push("B");
        s.push("C");
        System.out.println("size: " + s.size());
        System.out.println("peek: " + s.peek());
        System.out.println("pop: " + s.pop());
        System.out.println("size: " + s.size());
        System.out.println("peek: " + s.peek());
    }
// EXAMPLE 14: Bank example with LinkedStack
    static class LinkedBankAccount {
        private double balance;
        private LinkedStack<Double> history = new LinkedStack<Double>();

        public LinkedBankAccount(double balance) { this.balance = balance; }

        public void deposit(double amount) {
            balance = balance + amount;
            history.push(amount);
            System.out.println("Deposit " + amount + " -> balance " + balance);
        }

        public void undoLastDeposit() {
            if (history.isEmpty()) {
                System.out.println("Nothing to undo");
                return;
            }
            double last = history.pop();
            balance = balance - last;
            System.out.println("Undo " + last + " -> balance " + balance);
        }
    }

    static void example14() {
        System.out.println("\n--- Example 14: Bank with LinkedStack ---");
        LinkedBankAccount acc = new LinkedBankAccount(100);
        acc.deposit(50);
        acc.deposit(20);
        acc.deposit(30);
        acc.undoLastDeposit();
        acc.undoLastDeposit();
    }

    // EXAMPLE 15: Student example with LinkedStack
    static class LinkedRegistration {
        private LinkedStack<String> courses = new LinkedStack<String>();

        public void addCourse(String code) {
            courses.push(code);
            System.out.println("Added " + code + " | top: " + courses.peek()
                    + " | size: " + courses.size());
        }

        public void undoLastCourse() {
            if (courses.isEmpty()) {
                System.out.println("Nothing to undo");
                return;
            }
            String removed = courses.pop();
            System.out.println("Removed " + removed + " | size: " + courses.size());
        }
    }

    static void example15() {
        System.out.println("\n--- Example 15: Student with LinkedStack ---");
        LinkedRegistration reg = new LinkedRegistration();
        reg.addCourse("CA314");
        reg.addCourse("CA301");
        reg.addCourse("MA210");
        reg.undoLastCourse();
        reg.undoLastCourse();
    }
