package unipi.library.classes;

public class Librarian extends User {
	   private String department, educationLevel;
	   // --- Constructor ---
	   public Librarian(int id, String firstName, String lastName, String birthDate, String phone, String email,
	                    String department, String educationLevel) {
	       super(id, firstName, lastName, birthDate, phone, email);
	       this.department = department;
	       this.educationLevel = educationLevel;
	   }
	   // --- Getters & Setters ---
	   public String getDepartment() {
	       return department;
	   }
	   public void setDepartment(String department) {
	       this.department = department;
	   }
	   public String getEducationLevel() {
	       return educationLevel;
	   }
	   public void setEducationLevel(String educationLevel) {
	       this.educationLevel = educationLevel;
	   }
	   @Override
	   public String toString() {
	       return super.toString() + "\nLibrarian{" +
	               "department='" + department + '\'' +
	               ", educationLevel='" + educationLevel + '\'' +
	               '}';
	   }
	}



