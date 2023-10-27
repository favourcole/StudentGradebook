import java.util.Arrays;
import java.util.Scanner;

/** /
 * 
 * 
 * @author Favour Cole
 *
 */

public class XX007 {
    public static void main(String[] args) {
        // Open a scanner to get user input
        try (Scanner input = new Scanner(System.in)) { 
            // Ask the user to enter the number of students taking the module
            System.out.print("Enter the number of students: ");
            int numberofStudents = input.nextInt();

            // Print the number of students entered
            System.out.println("The number of students: " + numberofStudents);

            // We create an array to store the Student objects it depends on how many students are entered into the input
            Student[] students = new Student[numberofStudents];

            // We create a for loop so we can loop through the number of students entered and get their required information 
            for (int i = 0; i < numberofStudents; i++) { // i will keep increasing by 1; i < the number of students entered e.g i < 3
                System.out.println("Enter the information of student " + (i+1) + ":");
                System.out.print("First Name: ");
                String firstname = input.next();
                System.out.print("Surname: ");
                String surname = input.next();
                int[] hwmarks = new int[5];
                for (int j = 0; j < 5; j++) {
                    System.out.print("The Marks For The Homework " + (j+1) + ": ");
                    hwmarks[j] = input.nextInt();
                }
                System.out.print("The Marks For The Interim Test: ");
                int interimmarks = input.nextInt();
                System.out.print("The Marks For The Exam: ");
                double exammark = input.nextDouble();

                // Create a new Student object with the entered information
                students[i] = new Student(firstname, surname, hwmarks, interimmarks, exammark);
            }

            // Print a table of the student grades
            System.out.println("\nThe Number Of Students: " + numberofStudents);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("ID\tName\tSurname\tCoursework\tExam\tFinalMark");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            // We create a for loop through the students and calculate their grades 
            for (Student student : students) {
                // Get the best three homework marks
                int[] bestofthreeMarks = getBestThreeHomeworkMarks(student.gethwmarks());
                // Calculate the coursework mark (which is the average of the best three homework marks * 0.6 + interim test mark * 0.4)
                double cwmark = (Arrays.stream(bestofthreeMarks).average().orElse(0) * 0.6) + (student.getinterimmarks() * 0.4);
                // Calculate the final mark (coursework mark * 0.4 + exam mark * 0.6)
                double finalmark = cwmark * 0.4 + student.getexammark() * 0.6;

                // We print the student's information and grades 
                System.out.printf("%-8s %-8s %-8s %-8.2f%% %-8.2f%% %.2f%%%n", student.getId(), student.getfirstname(), student.getsurname(), cwmark, student.getexammark(), finalmark);
            }
        }
    }

    // Get the best three homework marks
    public static int[] getBestThreeHomeworkMarks(int[] hwmarks) {
        Arrays.sort(hwmarks);
        return new int[] {hwmarks[2], hwmarks[3], hwmarks[4]};
    }

    // Create a Student class to store information about each student e.g firstname surname etc
    public static class Student {
        private static int count = 1; // Static variable to keep track of the number of students
        private String id; // Unique ID
        private String firstname;
        private String surname;
        private int[] hwmarks;
        private int interimmarks;
        private double exammark;

        public Student(String firstname, String surname, int[] hwmarks, int interimmarks, double exammark) {
            this.id = String.valueOf(count);
            count++; // increasing the value of count by 1
            this.firstname = firstname;
            this.surname = surname;
            this.hwmarks = hwmarks;
            this.interimmarks = interimmarks;
            this.exammark = exammark;
        }
        
        // using getters so we can return the variables

        public String getId() {
            return id;
        }

        public String getfirstname() {
            return firstname;
        }

        public String getsurname() {
            return surname;
        }

        public int[] gethwmarks() {
            return hwmarks;
        }

        public int getinterimmarks() {
            return interimmarks;
        }

        public double getexammark() {
            return exammark;
        }
    }
    }
