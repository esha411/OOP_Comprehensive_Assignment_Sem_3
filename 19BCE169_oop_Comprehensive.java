//19BCE169
//Esha Patel
//OOP Comprehensive Assignment
import java.util.InputMismatchException;
import java.util.Scanner;

class Student
{
    int sem;
    String rollno;
    int[][] marks;
    static String[] courses = new String[20];

    public Student()
    {
        this.marks = new int[4][Student.courses.length];
    }
    public static void Displaystudentmarkarray(Student[] students)
    {
        int count = 0;
        int snum = 1;
        for (int i = 0; i < students.length; i++)
        {
            if (students[i].rollno != null)
            {
                System.out.print("\n[" + snum++ + "].ROLL NUMBER: " + students[i].rollno);
                System.out.print(" | SEMESTER: " + students[i].sem);
                System.out.print("\n\tCOURSES:\t");
                for (int j = 0; j < Student.courses.length; j++)
                {
                    System.out.printf("%-10s" , Student.courses[j]);
                }
                String[] components = {"CT", "SE" , "IA" , "Total"};
                for (int c = 0; c < students[i].marks.length; c++)
                {
                    System.out.printf("\n\t%7s: \t" , components[c]);
                    for (int j = 0; j < Student.courses.length; j++)
                    {
                        System.out.printf("%-10s" , students[i].marks[c][j]);
                    }
                }
                System.out.println("\n");
            }
            else
            {
                count++;
            }
        }

        if (count == students.length)
        {
            System.out.println("\nNO STUDENT DETAIL FOUND!");
        }
    }

    public static void printmarks(Student[] students, int sem, int min, int max)
    {
        for (int j = 0; j < Student.courses.length; j++)
        {
            System.out.println("\nCOURSE: " + Student.courses[j]);
            System.out.print("ROLL NUMBER: ");
            for (int i = 0; i < students.length; i++)
            {
                int m = students[i].marks[3][j];
                if (students[i].sem == sem && m >= min && m <= max)
                {
                    System.out.print(students[i].rollno + "\nTOTAL MARKS: " + m);
                }
            }
        }
    }
}


class MARKSHEET extends Student
{
    public static void main(String[] args)
    {   int MAX = 20;
        Scanner sc = new Scanner(System.in);
        Student[] students = new Student[MAX];
        Student s = new Student();
        System.out.println("\n************CONTINUOUS EVALUATION(CE)-MARK-SHEET*************");
        System.out.println("--------------------------------------------------------------");
        System.out.println("ENTER ALL THE COURSE CODE FOR THIS MARK-SHEET, in one line (e.g. 2CS301 2CS302)");
        System.out.print("COURSE CODE(separated by space): ");
        String code = sc.nextLine();
        String[] courses = code.split(" ");
        Student.courses = courses;
        for (int i = 0; i < students.length; i++)
        {
            students[i] = new Student();
        }
        while (true)
        {
            System.out.println("1:CREATE MARK-SHEET PORTAL");
            System.out.println("2:USE MARK-SHEET PORTAL");
            System.out.println("0:EXIT");
            int option;
            while (true)
            {
                try
                {
                    System.out.print("\n------>: ");
                    option = sc.nextInt();
                    if (option < 0 || option > 2)
                    {
                        throw new InputMismatchException();
                    }
                    break;
                } catch (InputMismatchException e)
                {
                    System.out.println("\nERROR!!! NO SUCH INPUT!.... ENTER VALID INPUT ...");
                    sc.nextLine();
                }
            }
            if (option == 1)
            {
                sc.nextLine();
                while (true)
                {
                    System.out.println(" 1: ADD STUDENT DATA");
                    System.out.println(" 2: UPDATE STUDENT DATA");
                    System.out.println(" 3: DELETE STUDENT DATA");
                    System.out.println(" 4: DISPLAY STUDENT DATA");
                    System.out.println(" 0: exit");
                    int Opt;
                    while (true)
                    {
                        try
                        {
                            System.out.print("\n---------->: ");
                            Opt = sc.nextInt();
                            if (Opt < 0 || Opt > 4)
                            {
                                throw new InputMismatchException();
                            }
                            break;
                        } catch (InputMismatchException e)
                        {
                            System.out.println("\nERROR!!! ENTER VALID CHOICE...");
                            sc.nextLine();
                        }
                    }
                    if (Opt == 1)
                    {
                        int index = -1;
                        for (int i = 0; i < students.length; i++)
                        {
                            if (students[i].rollno == null)
                            {
                                index = i;
                                break;
                            }
                        }

                        if (index == -1)
                        {
                            System.out.println("\nMARK-SHEET CAN'T STORE MORE THEN " + MAX + " STUDENT!");
                            System.out.println("PLEASE DELETE ONE STUDENT DATA TO ADD NEW STUDENT");
                        }
                        else
                        {
                            sc.nextLine();
                            System.out.print("\nENTER ROLL NUMBER OF THE STUDENT: ");
                            students[index].rollno = sc.nextLine();
                            System.out.print("SEMESTER: ");
                            students[index].sem = sc.nextInt();

                            String[] components = {"CLASS TEST", "SESSIONAL EXAM", "INNOVATIVE ASSIGNMENT"};
                            for (int i = 0; i < Student.courses.length; i++)
                            {
                                for (int j = 0; j < components.length; j++)
                                {
                                    System.out.print(components[j] + " MARKS IN COURSE " + Student.courses[i] + ": ");
                                    students[index].marks[j][i] = sc.nextInt();
                                }

                                students[index].marks[components.length][i] = (int) (0.3 * students[index].marks[0][i] + 0.4 * students[index].marks[1][i] + 0.3 * students[index].marks[2][i]) + 1;
                            }

                            System.out.println("\nNEW STUDENT DATA ADDED SUCCESSFULLY!");
                        }
                    } else if (Opt == 2)
                    {
                        sc.nextLine();
                        System.out.print("\nENTER ROLL NUMBER OF THE STUDENT TO BE UPDATED: ");
                        String roll = sc.nextLine();
                        int index = -1;
                        for (int i = 0; i < students.length; i++)
                        {
                            if (roll.equals(students[i].rollno))
                            {
                                index = i;
                            }
                        }

                        if (index == -1)
                        {
                            System.out.println("STUDENT RECORD NOT FOUND!");
                        } else
                        {
                            System.out.println("\nSELECT COURSE AND COMPONENT TO UPDATE: ");
                            System.out.println("ENTER YOUR CHOICE ");
                            for (int i = 0; i < Student.courses.length; i++)
                            {
                                System.out.println(i + " : " + Student.courses[i]);
                            }
                            int y = sc.nextInt();
                            String[] components = {"class test", "sessional exam", "innovative assignment"};
                            for (int i = 0; i < components.length; i++)
                            {
                                System.out.println(i + " : " + components[i]);
                            }
                            int x = sc.nextInt();

                            System.out.println("(" + x + "," + y + ")");

                            System.out.println("\nENTER NEW MARKS: ");
                            students[index].marks[x][y] = sc.nextInt();
                            students[index].marks[components.length][y] = (int) (0.3 * students[index].marks[0][y] + 0.4 * students[index].marks[1][y] + 0.3 * students[index].marks[2][y]) + 1;

                            System.out.println("MARKS UPDATED SUCCESSFULLY!");
                        }
                    }
                    else if (Opt == 3)
                    {
                        sc.nextLine();
                        System.out.print("\nENTER ROLL NUMBER OF THE STUDENT YOU WANT TO DELETE: ");
                        String roll = sc.nextLine();
                        int index = -1;
                        for (int i = 0; i < students.length; i++)
                        {
                            if (roll.equals(students[i].rollno))
                            {
                                index = i;
                            }
                        }

                        if (index == -1)
                        {
                            System.out.println("STUDENT DATA NOT FOUND!");
                        }
                        else
                        {
                            students[index] = new Student();
                            System.out.println("\nSTUDENT DATA DELETED SUCCESSFULLY!");
                        }
                    }
                    else if (Opt == 4)
                    {
                        s.Displaystudentmarkarray(students);
                    }
                    else
                    {
                        break;
                    }
                }
            }
            else if (option == 2)
            {
                while (true)
                {
                    System.out.println("\n 1: DISPLAY LIST OF PASSED CE STUDENT");
                    System.out.println(" 2: DISPLAY LIST OF FAILED CE STUDENT");
                    System.out.println(" 3: DISPLAY ALL STUDENT DATA");
                    System.out.println(" 0: EXIT");
                    int temp;
                    while (true)
                    {
                        try
                        {
                            System.out.print("\n------>: ");
                            temp = sc.nextInt();
                            if (temp < 0 || temp > 3)
                            {
                                throw new InputMismatchException();
                            }
                            break;
                        } catch (InputMismatchException e)
                        {
                            System.out.println("\nERROR! PLEASE ENTER VALID INPUT...");
                            sc.nextLine();
                        }
                    }
                    if (temp == 1)
                    {
                        System.out.println("\n:ENTER SEMESTER ");
                        int sem = sc.nextInt();

                        System.out.println("ALL THE PASS STUDENT DATA:");
                        s.printmarks(students, sem, 41, 100);
                        sc.nextLine();

                    } else if (temp == 2)
                    {

                        System.out.println("\nENTER SEMESTER: ");
                        int sem = sc.nextInt();

                        System.out.println("ALL THE FAIL STUDENT DATA:");
                        s.printmarks(students, sem, 0, 40);
                        sc.nextLine();

                    } else if (temp == 3)
                    {
                        s.Displaystudentmarkarray(students);
                    } else
                        {
                        break;
                    }
                }
            }
            else if (option == 0)
            {
                sc.close();
                return;
            }
        }
    }
}


