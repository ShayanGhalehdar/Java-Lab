package lab2;

import java.util.ArrayList;

public class Course
{
    public String name;
    public String presenter;
    String department;
    String credits;
    String id;
    String password;
    ArrayList<String> students = new ArrayList<>();
    ArrayList<String> grades = new ArrayList<>();
    
    
    public void printstats()
    {
        System.out.println("Name : "+name);
        System.out.println("Presenter : "+presenter);
        System.out.println("Department : "+department);
        System.out.println("Credits : "+credits);
        System.out.println("ID : "+id);
        System.out.print("Students : ");
        int i;
        for(i=0;i<students.size();i++)
        {
            System.out.print(students.get(i) + ": " + grades.get(i) + " , ");
        }
        System.out.println(" ");
    }
    
    public void studentslist()
    {
        int i;
        for(i=0;i<students.size();i++)
        {
            System.out.println(students.get(i));
        }
    }
    
    public void addrem(String firstname, String lastname)
    {
        int i;
        for(i=0;i<students.size();i++) // search through all course students
        {
            if(students.get(i).equals(firstname + " " + lastname)) //check if name matches
            {
                students.remove(i);
                grades.remove(i);
                return;
            }
        }
        students.add(firstname + " " + lastname);
        grades.add("NA");
    }
}


