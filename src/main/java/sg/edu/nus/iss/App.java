package sg.edu.nus.iss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    @FunctionalInterface
    interface Operator<T> {
        T process(T a, T b);
    }

    public static void main(String[] args) {
        if (args[0].equalsIgnoreCase("kill")) {
            System.exit(0);
        }

        streamFilterSortExample();

        integersSortExample();

        functionalInterfaceExample();

        arraySortExample();

        mapSortExample();

        threadExecutorExample();

        threadExample01();

        threadExample02();
    }

    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<Employee>();

        employees.add(new Employee(1, "Hsien Loong", "Lee", 70));
        employees.add(new Employee(2, "Chee Hean", "Tan", 71));
        employees.add(new Employee(3, "Swee Kiat", "Ng", 65));
        employees.add(new Employee(4, "Pritam", "Singh", 55));
        employees.add(new Employee(5, "Eng Hen", "Ng", 70));
        employees.add(new Employee(6, "Lawrence", "Wong", 50));
        employees.add(new Employee(7, "Ye Kung", "Ong", 50));
        employees.add(new Employee(8, "Darryl", "Ng", 45));
        return employees;
    }

    public static void streamFilterSortExample() {
        List<Employee> employees = getEmployees();

        // stream and filter
        List<Employee> filteredEmployees = employees.stream().filter(e -> e.getLastName().equalsIgnoreCase("ng"))
                .collect(Collectors.toList());
        System.out.println(filteredEmployees);

        Employee emp = employees.stream().filter(e -> e.getLastName().equalsIgnoreCase("ng")).findFirst().get();
        System.out.println(emp);

        int idx = employees.indexOf(emp);
        Employee returnedEmp = employees.get(idx);
        System.out.println(idx + ">>>" + returnedEmp);

        employees.get(idx).setAge(1000);
        System.out.println(employees.get(idx));

        // sort by firstname in ascending order
        employees.sort(Comparator.comparing(e -> e.getFirstName()));
        System.out.println(employees);

        // sort by firstname in descending order
        Comparator<Employee> comparator = Comparator.comparing(e -> e.getFirstName());
        employees.sort(comparator.reversed());
        System.out.println(employees);

        // loop and print out employee records
        employees.forEach(emp1 -> System.out.println(emp1));
    }

    public static void integersSortExample() {
        List<Integer> integers = Arrays.asList(3, 6, 8, 2, 9, 0, 5, 4, 7, 1);
        integers.sort(Comparator.naturalOrder());
        System.out.println(integers);
    }

    public static void functionalInterfaceExample() {
        // Functional Interface
        Operator<Integer> addOperations = (a, b) -> {
            return a + b;
        };

        Operator<Integer> minusOperations = (a, b) -> {
            return a - b;
        };

        Operator<String> ConcatOperations = (a, b) -> {
            return a.concat(b);
        };


        System.out.println("Add Operation: " + addOperations.process(5, 2));
        System.out.println("Minus Operation: " + minusOperations.process(10, 2));
        System.out.println("Concat Operation: " + ConcatOperations.process("Let's go", " home"));
    }

    public static void arraySortExample() {
        String arr[] = { "practice", "workshop", "lecture", "revision" };

        Arrays.sort(arr);
        System.out.println("Ascending Sorted array: " + Arrays.toString(arr));

        Arrays.sort(arr, Collections.reverseOrder());
        System.out.println("Descending Sorted array: " + Arrays.toString(arr));

    }

    public static void mapSortExample() {
        Map<String, Integer> mapList = new HashMap<>();
        mapList.put("Sushi", 5);
        mapList.put("Chocolate", 15);
        mapList.put("Coffee", 8);
        mapList.put("Tea", 12);
        mapList.put("Sandwich", 18);
        mapList.put("Hashbrown", 10);
        mapList.forEach((k, v) -> System.out.println(k + " --> " + v));

        List<Entry<String, Integer>> list = new ArrayList<>(mapList.entrySet());
        list.sort(Entry.comparingByValue());
        list.forEach(System.out::println);
    }

    public static void threadExecutorExample() {
        MyRunnableImplementation ri1 = new MyRunnableImplementation("Task 1");
        MyRunnableImplementation ri2 = new MyRunnableImplementation("Task 2");
        MyRunnableImplementation ri3 = new MyRunnableImplementation("Task 3");
        MyRunnableImplementation ri4 = new MyRunnableImplementation("Task 4");
        MyRunnableImplementation ri5 = new MyRunnableImplementation("Task 5");

        ExecutorService es = Executors.newFixedThreadPool(4);
        es.execute(ri1);
        es.execute(ri2);
        es.execute(ri3);
        es.execute(ri4);
        es.execute(ri5);
        es.shutdown();
    }

    public static void threadExample01() {
        MyRunnableImplementation ri1 = new MyRunnableImplementation("Task 1");
        MyRunnableImplementation ri2 = new MyRunnableImplementation("Task 2");
        MyRunnableImplementation ri3 = new MyRunnableImplementation("Task 3");
        MyRunnableImplementation ri4 = new MyRunnableImplementation("Task 4");
        MyRunnableImplementation ri5 = new MyRunnableImplementation("Task 5");

        Thread t1 = new Thread(ri1);
        t1.start();
        Thread t2 = new Thread(ri2);
        t2.start();
        Thread t3 = new Thread(ri3);
        t3.start();
        Thread t4 = new Thread(ri4);
        t4.start();
        Thread t5 = new Thread(ri5);
        t5.start();
    }

    public static void threadExample02() {

        new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        System.out.println(i);
                    }
                }).start();
    }
 
    public static void linkedListExample() {
        LinkedList<String> ll = new LinkedList<String>();
        
        ll.add("A");
        ll.add("B");
        ll.add("C");
        ll.add("D");
        ll.add(2, "E");

        System.out.println(ll);

        ll.remove("C");
        System.out.println(ll);

        System.out.println(ll.peekFirst());

        ll.removeFirst();
        System.out.println(ll);
    }

    public static void stackExample() {
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < 10; i++) {
            stack.push(i);
        }

        for (int i = 0; i < 5; i++) {
            Integer item = stack.pop();
            System.out.println("Pop ->" + item);
        }

        Integer itm = stack.peek();
        System.out.println("Peek the last element in the stack ->" + itm);

        Integer firstElement = stack.firstElement();
        System.out.println("Peek the first element in the stack ->" + firstElement);

        Iterator<Integer> iterator = stack.iterator();
        while(iterator.hasNext()) {
            Integer stackItem = iterator.next();
            System.out.println("Iterate through stack ->" + stackItem);
        }
    }
}
