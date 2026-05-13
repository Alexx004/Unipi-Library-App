package unipi.library.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import unipi.library.classes.Loan;
import unipi.library.classes.Fine;
import unipi.library.classes.FeePolicy;
import unipi.library.classes.Student;

// Klasi pou diaxeirizetai ola ta dania  kai ta prostima
public class LoanManager {
	
   private ArrayList<Loan> loans = new ArrayList<>(); // lista me ola ta danei
   private ArrayList<Fine> fines = new ArrayList<>(); // lista me prostima pou exoun ypologistei
   private ArrayList<Fine> paidFines = new ArrayList<>(); //lista me prostima pou exoun plirwthei
   private FeePolicy feePolicy;     // politiki prostimou (e.g. 1€/mera)
   
   // --- Constructor ---
   public LoanManager(FeePolicy policy) {
       this.feePolicy = policy;
   }
  
   // --- Prosthetoume neo danio ---
   public void addLoan(Loan loan) {
       loans.add(loan);
       loan.getStudent().setCurrentActiveLoans(loan.getStudent().getCurrentActiveLoans() + 1); //aujhsh tou arithmou twn energwn daneismwn tou foititi
   }
   // --- Emfanizoume ola ta dania ---
   public List<Loan> getAllLoans() {
       return loans;
   }
   // --- Epistrofi mono ton energon daneiwn ---
   public List<Loan> getActiveLoans() {
       return loans.stream()
               .filter(l -> l.getStatus().equalsIgnoreCase("Active"))
               .collect(Collectors.toList());
   }
   // --- Epistrofi kathysterimenon daneiwn ---
   public List<Loan> getLateLoans() {
       return loans.stream()
               .filter(Loan::isLate)
               .collect(Collectors.toList());
   }
   // --- Epistrofi daneiwn me sugkekrimeno student ---
   public List<Loan> getLoansByStudent(Student student) {
       return loans.stream()
               .filter(l -> l.getStudent().equals(student))
               .collect(Collectors.toList());
   }
   // --- Katagrafi epistrofis daneiou ---
   public void returnBook(Loan loan, LocalDate returnDate) {
       loan.setReturnDate(returnDate);
       loan.setStatus(loan.isLate() ? "Delayed" : "Completed");
       loan.getBook().setAvailable(true);
       loan.getStudent().setCurrentActiveLoans(loan.getStudent().getCurrentActiveLoans() - 1); //meiwsh tou arithmou twn energwn daneismwn tou foititi
   }
   // --- Ypologismos prostimou gia dania kai kataxorisi Fine ---
   public Fine createFine(Loan loan) {
       Fine fine = new Fine(loan, feePolicy); 
       return fine;
   }
   // --- Epistrofi olon ton fines ---
   public List<Fine> getAllFines() {
       return fines;
   }

   public Loan getLoanById(int id) {
       return loans.stream()
               .filter(l -> l.getLoanId() == id)
               .findFirst()
               .orElse(null);
   }
   
   public void addFine(Fine fine) { //methodos gia thn prosthiki enos prostimou sto array list
   		fines.add(fine);
   }
   
   public ArrayList<Fine> getUnpaidFines() { //methodos h opoia epistrefei array list me ola ta ekkremh prostima
   	ArrayList<Fine> unpaidFines = new ArrayList<>();
   	for (Fine fine : fines) {
   		if (!fine.isPaid()) {
   			unpaidFines.add(fine);
   		}
   	}
   		return unpaidFines;
   }
   
   public void setFineAsPaid(Fine fine) { //methodos gia thn anathesi enos prostimou ws plhrwmeno
   	fine.setPaid(true);
   }

   public ArrayList<Fine> getPaidFines() { //methodos h opoia epistrefei array list me ola ta plhrwmena prostima
   	ArrayList<Fine> paidFines = new ArrayList<>();
   	for (Fine fine : fines) {
   		if (fine.isPaid()) {
   			paidFines.add(fine);
   		}
   	}
   	return paidFines;
   }
   
   public void addPaidFine(Fine fine) { //methodos gia thn prosthiki enos plirwmenou prostimou sto array list
	   paidFines.add(fine);
   }
}

