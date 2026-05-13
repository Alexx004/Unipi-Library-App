package unipi.library.ui;

import unipi.library.classes.Student;
import java.util.ArrayList;


public class StudentManager {
	private ArrayList<Student> students = new ArrayList<>(); //array list me olous tous mathites tou systhmatos
   
    public StudentManager() { //5 prokathorismenoi foitites
	    students.add(new Student(0, "Kai", "Cenat", "2000-05-24", "6951234567", "kati@gmai.com", "E00134", "physics"));
	    students.add(new Student(1, "Adin", "Ross", "1992-11-04", "6958901234", "viscabarca@goatmail.com", "E92354", "computer science"));
   	    students.add(new Student(2, "Ye", "West", "1977-06-08", "9348356372", "cousinsNHH@gmail.com", "E01940", "history"));
		students.add(new Student(3, "Ishow", "Speed", "2006-06-01", "2342345676", "sui@gmail.com", "E06024", "mechanical engineering"));
	    students.add(new Student(4, "TwoJ", "Ioannou", "2005-08-20", "6999999999", "larnakagoated@gmail.com", "E05239", "music"));
    }
   
    public void addStudent(Student student) { //methodos gia thn prosthiki foithth
       students.add(student);
    }

   public Student findByStudentId(String studentId) {    // Anazhthsh foithth me bash to id tou
	   if (students.size() == 0) { //an h lista einai kenh epistrefei null
		   return null; 
	   }
       for (Student s : students) {
           if (s.getStudentId().equals(studentId)) {
               return s;
           }
       }
       return null;
   }
   
   public ArrayList<Student> getAllStudents() { //methodos gia thn epistrofh ths listas twn foititwm
       return students;
   }
  
   public boolean updateStudent(Student updatedStudent) {  // Enhmerwsh stoixeiwn foithth
       for (int i = 0; i < students.size(); i++) {
           if (students.get(i).getStudentId().equals(updatedStudent.getStudentId())) {
               students.set(i, updatedStudent);
               return true;
           }
       }
       return false;
   }
}

