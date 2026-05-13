package unipi.library.classes;

import java.time.LocalDate;

public class Loan {
	
   private int loanId;
   private Student student;             // pios to danizete
   private Book book;                   // pio book danizete
   private LocalDate loanDate;          // imerominia danismou
   private LocalDate dueDate;           // 14D na to epistrepsi
   private LocalDate returnDate;        // imerominia epistrofis
   private boolean isLate;              // an exi kathisterisi
   private String status;               // Delayed, Completed, Active
   
   // --- Constructor ---
   public Loan(int loanId, Student student, Book book, LocalDate loanDate, LocalDate returnDate, boolean isLate, String status) {
       this.loanId = loanId;
       this.student = student;
       this.book = book;
       this.loanDate = loanDate;
       this.dueDate = loanDate.plusDays(14);  //automati prothesmia
       this.returnDate = returnDate;
       this.isLate = isLate;
       this.status = status;
   }
   // --- Getters & Setters ---
   public int getLoanId() {
       return loanId;
   }
   public void setLoanId(int loanId) {
       this.loanId = loanId;
   }
   public Student getStudent() {
       return student;
   }
   public void setStudent(Student student) {
       this.student = student;
   }
   public Book getBook() {
       return book;
   }
   public void setBook(Book book) {
       this.book = book;
   }
   public LocalDate getLoanDate() {
       return loanDate;
   }
   public void setLoanDate(LocalDate loanDate) {
       this.loanDate = loanDate;
   }
   public LocalDate getDueDate() {
       return dueDate;
   }
   public void setDueDate(LocalDate dueDate) {
       this.dueDate = dueDate;
   }
   public LocalDate getReturnDate() {
       return returnDate;
   }
   // elegxos kathisterisis
   public void setReturnDate(LocalDate returnDate) {
       this.returnDate = returnDate;
       if (returnDate.isAfter(this.dueDate)) {
           this.isLate = true;
           this.status = "Delayed";
       } 
       else {
           this.isLate = false;
           this.status = "Completed";
       }
   }
   public boolean isLate() {
       return isLate;
   }
   public void setLate(boolean isLate) {
       this.isLate = isLate;
   }
   public String getStatus() {
       return status;
   }
   public void setStatus(String status) {
       this.status = status;
   }
   public String formatDate(LocalDate date) { //methodos gia thn metatroph hmeromhnias apo type LocalDate se String gia thn xrhsh tous se table column
	    if (date == null) {
	    	return "";
	    }
	    return date.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

   // --- toString ---
   @Override
   public String toString() {
       return "Loan [loanId=" + loanId +
              ", student=" + student +
              ", book=" + book +
              ", loanDate=" + loanDate +
              ", dueDate=" + dueDate +
              ", returnDate=" + returnDate +
              ", isLate=" + isLate +
              ", status=" + status + "]";
   }
}



