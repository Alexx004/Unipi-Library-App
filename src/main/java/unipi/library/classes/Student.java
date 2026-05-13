package unipi.library.classes;

public class Student extends User {
    // Pedia
    private String studentId, department;
    private int maxBooks = 5;
    private int currentActiveLoans;

    // Constructor
    public Student(int id, String firstName, String lastName, String birthDate, String phone, String email,
                   String studentId, String department) {
        // klisi tou constructor tis User
        super(id, firstName, lastName, birthDate, phone, email);

        // arxikopiisi diko tis pedio
        this.studentId = studentId;
        this.department = department;
        currentActiveLoans = 0;
    }

    // --- Getters & Setters ---
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
   
    public int getMaxBooks() {
        return maxBooks;
    }

    public void setMaxBooks(int maxBooks) {
        this.maxBooks = maxBooks;
    }
    
    public String getFullName() { //methodos gia thn dhmiourgia string me to plhres onomateponymo tou foithth
        return getFirstName() + " " + getLastName();
    }
    public int getCurrentActiveLoans() {
    	return currentActiveLoans;
    }
    public void setCurrentActiveLoans(int currentActiveLoans) {
    	this.currentActiveLoans = currentActiveLoans;
    }
    @Override
    public String toString() {
        return super.toString() + "\nStudent{" +
                "studentId='" + studentId + '\'' +
                ", department='" + department + '\'' +
                ", maxBooks=" + maxBooks +
                '}';
    }
   
}



