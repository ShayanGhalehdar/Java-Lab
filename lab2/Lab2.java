package lab2;

// IMPORTANT
// a total number of 5 students and 5 courses has been added for ease
// you can comment them and uncomment the upcoming block of code to enter as many as courses or students you want

import java.util.Scanner;

public class Lab2
{
    public static void main(String[] args)
    {
        Scanner scanObj= new Scanner(System.in); // create a scanner object
        int numstudents = 5;
        int numcourses = 5;
        int i,j;
        Student student[]=new Student[numstudents]; // assign memory of an array of Student class instances with length of numstudents
        student[0] = new Student(); // create an element of array of objects
        student[1] = new Student(); 
        student[2] = new Student(); 
        student[3] = new Student(); 
        student[4] = new Student(); 
        student[0].firstname = "kobra";
        student[1].firstname = "abdol";
        student[2].firstname = "moshtaba";
        student[3].firstname = "askhar";
        student[4].firstname = "kambiz";
        student[0].lastname = "golabdareh";
        student[1].lastname = "chatrabgoon";
        student[2].lastname = "saki";
        student[3].lastname = "borazjani";
        student[4].lastname = "ghanbari";
        student[0].id = "1";
        student[1].id = "2";
        student[2].id = "3";
        student[3].id = "4";
        student[4].id = "5";
        student[0].studentid = "1";
        student[1].studentid = "2";
        student[2].studentid = "3";
        student[3].studentid = "4";
        student[4].studentid = "5";
        student[0].field = "ee";
        student[1].field = "ee";
        student[2].field = "ee";
        student[3].field = "ee";
        student[4].field = "ee";
    
        Course course[]=new Course[5]; // assign memory of an array of Course class instances with length of numcourses
        course[0] = new Course(); // create an instance of Course class
        course[1] = new Course();
        course[2] = new Course();
        course[3] = new Course();
        course[4] = new Course();
        course[0].id = "1";
        course[1].id = "2";
        course[2].id = "3";
        course[3].id = "4";
        course[4].id = "5";
        course[0].password = "1";
        course[1].password = "2";
        course[2].password = "3";
        course[3].password = "4";
        course[4].password = "5";
        course[0].name = "math";
        course[1].name = "physics";
        course[2].name = "chem";
        course[3].name = "algebra";
        course[4].name = "literature";
        course[0].presenter = "abas";
        course[1].presenter = "naghi";
        course[2].presenter = "taghi";
        course[3].presenter = "abdol";
        course[4].presenter = "mozafar";
        course[0].credits = "3";
        course[1].credits = "3";
        course[2].credits = "3";
        course[3].credits = "2";
        course[4].credits = "2";
        course[0].department = "ee";
        course[1].department = "ee";
        course[2].department = "ee";
        course[3].department = "ee";
        course[4].department = "ee";
        
        
        
        /*
        System.out.print("Enter number of students: ");
        Scanner scanObj= new Scanner(System.in);
        Integer numstudents = scanObj.nextInt();
        
        Student student[]=new Student[numstudents];
        
        for(i=0;i<numstudents;i++)
        {
            student[i] = new Student();
                    
            
            System.out.print("First Name: ");
            String firstname = scanObj.next();
            student[i].firstname = firstname;
            
            System.out.print("Last Name: ");
            String lastname = scanObj.next();
            student[i].lastname = lastname;
            
            System.out.print("ID: ");
            String id = scanObj.next();
            student[i].id = id;
            
            System.out.print("Student ID: ");
            String studentid = scanObj.next();
            student[i].studentid = studentid;
            
            System.out.print("Field: ");
            String field = scanObj.next();
            student[i].field = field;
            
            System.out.println("________________________");
            System.out.println("Done!");
            System.out.println("________________________");

        }
        System.out.print("Enter number of courses: ");
        Integer numcourses = scanObj.nextInt();
        
        Course course[]=new Course[numcourses];
        
        for(i=0;i<numcourses;i++)
        {
            course[i] = new Course();
                    
            
            System.out.print("Name: ");
            String name = scanObj.next();
            course[i].name = name;
        
            System.out.print("Password: ");
            String password = scanObj.next();
            course[i].password = password;
            
            System.out.print("Presenter: ");
            String presenter = scanObj.next();
            course[i].presenter = presenter;
            
            System.out.print("Department: ");
            String department = scanObj.next();
            course[i].department = department;
            
            System.out.print("Credits: ");
            String credits = scanObj.next();
            course[i].credits = credits;
            
            System.out.print("ID: ");
            String id = scanObj.next();
            course[i].id = id;
            
            System.out.println("________________________");
            System.out.println("Done!");
            System.out.println("________________________");
        }
        */
        
        double rand;
        for(i=0;i<numcourses;i++)
        {
            for(j=0;j<numstudents;j++)
            {
                rand = Math.random();
                if(rand<0.5) //assigning or not assigning students to courses randomly
                {
                    course[i].addrem(student[j].firstname, student[j].lastname);
                    student[j].addrem(course[i].name);
                }
            }
        }
        
        for(i=0;i<numstudents;i++)
        {
            student[i].printstats(); // printing stats of all students created
            System.out.println("________________________");
        }
        for(i=0;i<numstudents;i++)
        {
            course[i].printstats(); // printing stats of all courses created
            System.out.println("________________________");
        }
        
        while(true) // platform is always open :)
        {
            System.out.println("Are you a Student or Teacher? (Enter choice number)"); // check if you are a student or course teacher
            System.out.println("1.Student   2.Teacher");
            String studorteach = scanObj.next();
            if(studorteach.equals("1"))
            {
                int found=0; // to check if user and pass didn't match, print a "try again" message
                System.out.println("Enter Username");
                String username = scanObj.next();
                System.out.println("Enter Password");
                String password = scanObj.next();
                System.out.println("________________________");
                for(i=0;i<numstudents;i++)
                {
                    if(student[i].studentid.equals(username) && student[i].id.equals(password))
                    {
                        found=1; 
                        Scanner input = new Scanner(System.in);
                        System.out.println("* Enter Operation Number *");
                        System.out.println("1.Add Course   2.Remove Course   3.Calculate Mean   4.Stats and List of Courses   5.Exit");
                        String op = input.nextLine(); // get the operation

                        if(op.equals("1"))
                        {
                            System.out.println("Enter Course Name: ");
                            String coursename = scanObj.next();
                            int exists=0; // to check if the course exists, not to add it again
                            for(j=0;j<student[i].courses.size();j++) // search through all student courses
                            {
                                if(student[i].courses.get(j).equals(coursename)) // search to find if the course is already exists
                                {
                                    System.out.println("This course has already exists!");
                                    exists=1;
                                }
                            }
                            if(exists==0)
                            {
                                student[i].addrem(coursename); // if the course is new and able to add, we call addrem method
                                System.out.println("Course added!");
                            }
                            System.out.println("________________________");
                        }
                        else if(op.equals("2"))
                        {
                            System.out.println("Enter Course Name: ");
                            String coursename = scanObj.next();
                            int exists=0; // to check if the course doesn't exist,
                            for(j=0;j<student[i].courses.size();j++) // search through all student courses
                            {
                                if(student[i].courses.get(j).equals(coursename)) // search to find if the course exists
                                {
                                    exists=1;
                                    student[i].addrem(coursename);
                                    System.out.println("Course Removed!");
                                }
                            }
                            if(exists==0)
                            {
                                System.out.println("You don't have this course!");
                            }
                            System.out.println("________________________");
                        }
                        
                        else if(op.equals("3"))
                        {
                            int sum=0; //sum of grades
                            int num=0; //number of courses which grades are assigned
                            for(j=0;j<numcourses;j++) //search through all courses
                            {
                                for(int k=0;k<course[j].students.size();k++) //search through course students
                                {
                                    if(course[j].students.get(k).equals(student[i].firstname + " " +student[i].lastname))
                                    {//search to find if the student has the course
                                        if(course[j].grades.get(k).equals("NA")) // check if the course grade has been set
                                        {
                                            continue;
                                        }
                                        else
                                        {
                                            sum+=Integer.valueOf(course[j].grades.get(k));
                                            num++;
                                        }
                                    }
                                }
                            }
                            if(num==0) // when no teacher has set this student grades
                            {
                                System.out.println("No Grade has been assigned yet!");
                            }
                            else
                            {
                                double mean = (double)sum/num;
                                System.out.println("Student Mean is: " + mean);
                            }
                            System.out.println("________________________");
                        }
                        
                        else if(op.equals("4"))
                        {
                            student[i].printstats();
                            System.out.println("________________________");
                        }
                        else if(op.equals("5"))
                        {
                            System.out.println("________________________");
                            break;
                        }
                        i--;// student can stay and do other things
                    }
                }
                if(found==0) // when user and pass doesn't match with database, gives another try to user
                {
                    System.out.println("Username or Password doesn't match! Try Again");
                    System.out.println("________________________");
                }
            }
            else if(studorteach.equals("2"))
            {
                int found=0;
                System.out.println("Enter Course ID");
                String username = scanObj.next();
                System.out.println("Enter Password");
                String password = scanObj.next();
                System.out.println("________________________");
                for(i=0;i<numcourses;i++)
                {
                    if(course[i].id.equals(username) && course[i].password.equals(password))
                    {
                        found=1; // to check if user and pass didn't match, print a "try again" message
                        Scanner input = new Scanner(System.in);
                        System.out.println("* Enter Operation Number *");
                        System.out.println("1.Add Student   2.Remove Student   3.Add Grades   4.Stats and List of Students   5.Edit Course Stats   6.Exit");
                        String op = input.nextLine(); // get the operation

                        if(op.equals("1"))
                        {
                            System.out.println("Enter Student First Name: ");
                            String firstname = scanObj.next();
                            System.out.println("Enter Student Last Name: ");
                            String lastname = scanObj.next();
                            int exists=0; // to check if the student exists, and if it is, not to add it again
                            for(j=0;j<course[i].students.size();j++)
                            {
                                if(course[i].students.get(j).equals(firstname + " " + lastname))
                                {
                                    System.out.println("This student has already been added!");
                                    exists=1;
                                }
                            }
                            if(exists==0)
                            {
                                course[i].addrem(firstname,lastname);
                                System.out.println("Student added!");
                            }
                            System.out.println("________________________");
                        }
                        else if(op.equals("2"))
                        {
                            System.out.println("Enter Student First Name: ");
                            String firstname = scanObj.next();
                            System.out.println("Enter Student Last Name: ");
                            String lastname = scanObj.next();
                            int exists=0; // to check if the student doesn't exist,
                            for(j=0;j<course[i].students.size();j++)
                            {
                                if(course[i].students.get(j).equals(firstname + " " + lastname))
                                {
                                    exists=1;
                                    course[i].addrem(firstname,lastname);
                                    System.out.println("Student Removed!");
                                }
                            }
                            if(exists==0)
                            {
                                System.out.println("This student doesn't have this course!");
                            }
                            System.out.println("________________________");
                        }
                        
                        else if(op.equals("3"))
                        {
                            System.out.println("Enter Student First Name: ");
                            String firstname = scanObj.next();
                            System.out.println("Enter Student Last Name: ");
                            String lastname = scanObj.next();
                            int exists=0; // to check if the student doesn't exist,
                            for(j=0;j<course[i].students.size();j++) //search through all course students
                            {
                                if(course[i].students.get(j).equals(firstname + " " + lastname)) //check if name matches
                                {
                                    exists=1;
                                    System.out.println("Enter Student Grade: ");
                                    String grade = scanObj.next();
                                    course[i].grades.set(j, grade); // change the student grade with input
                                    System.out.println("Student Grade set!");
                                }
                            }
                            if(exists==0) // when teacher typed the student's name wrong
                            {
                                System.out.println("This student doesn't have this course!");
                            }
                            System.out.println("________________________");
                            
                        }
                            
                        
                        else if(op.equals("4"))
                        {
                            course[i].printstats();
                            System.out.println("________________________");
                        }
                        
                        else if(op.equals("5")) // edit each of the course stats
                        {
                            System.out.println("* What stats do you want to edit? (Enter Number) *");
                            System.out.println("1.Course Name   2.Department   3.ID   4.Credits");
                            String edit = input.nextLine();
                            if(edit.equals("1"))
                            {
                                System.out.println("Enter New Course Name");
                                String editname = input.nextLine();
                                course[i].name = editname;
                                System.out.println("Course Name has been changed!");
                            }
                            else if(edit.equals("2"))
                            {
                                System.out.println("Enter New Department Name");
                                String editname = input.nextLine();
                                course[i].department = editname;
                                System.out.println("Department Name has been changed!");
                            }
                            else if(edit.equals("3"))
                            {
                                System.out.println("Enter New ID");
                                String editname = input.nextLine();
                                course[i].id = editname;
                                System.out.println("ID has been changed!");
                            }
                            else if(edit.equals("4"))
                            {
                                System.out.println("Enter New Credits number");
                                String editname = input.nextLine();
                                course[i].credits = editname;
                                System.out.println("Credits has been changed!");
                            }
                            System.out.println("________________________");
                            
                        }
                        
                        else if(op.equals("6"))
                        {
                            System.out.println("________________________");
                            break;
                        }

                        i--; // teacher can stay and do other things
                    }
                }
                if(found==0)
                {
                    System.out.println("Course ID or Password doesn't match! Try Again");
                    System.out.println("________________________");
                }
            }
        }
    }    
}
