package lab2;

import java.util.ArrayList;

public class Student
{
    public String firstname;
    public String lastname;
    public String id;
    public String studentid;
    public String field;
        
    ArrayList<String> courses = new ArrayList<>();
    ArrayList<Integer> grades = new ArrayList<>();
    
    public void printstats()
    {
        System.out.println("First Name : "+firstname);
        System.out.println("Last Name : "+lastname);
        System.out.println("ID : "+id);
        System.out.println("Student ID : "+studentid);
        System.out.println("Field : "+field);
        System.out.print("Courses : ");
        int i;
        for(i=0;i<courses.size();i++)
        {
            System.out.print(courses.get(i) + " , ");
            //System.out.println(grades.get(i));
        }
        System.out.println(" ");
    }
    
    public void addrem(String course)
    {
        int i;
        for(i=0;i<courses.size();i++) // search through all student courses
        {
            if(courses.get(i).equals(course)) // check if course name matches
            {
                courses.remove(i); //remove from arraylist
                return;
            }
        }
        courses.add(course);
    }
    
    public void mean()
    {
        int sum=0;
        for(int i=0;i<grades.size();i++)
        {
            sum+=grades.get(i);
        }
        double mean= (double)sum/grades.size();
        System.out.println(mean);
    }
}
