package unipi.library.classes;

// Klasi pou anaparista ena prostimo gia enan danei
// Den ipologizei i idia to prostimo – xrisimopoiei FeePolicy gia na to ypologisei

public class Fine {
	
   private Loan loan;         // To danei pou sindeetai me to prostimo
   private double amount;     // To poso tou prostimou (ypologizetai apo politikí)
   private boolean isPaid;    // Katastasi pliromis (true/false)
   
   // --- Constructor ---
   public Fine(Loan loan, FeePolicy policy) {
       this.loan = loan;                                  // Apothikevoume to daneio
       this.amount = policy.calculateFine(loan);          // Ypologizoume to prostimo me vasi tin politiki
       this.isPaid = false;                               // Default katastasi, oti dhladh den exei plirothei
   }
   // --- Getters & Setters ---
   public Loan getLoan() {
       return loan;
   }
   public void setLoan(Loan loan) {
       this.loan = loan;
   }
   public double getAmount() {
       return amount;
   }
   public void setAmount(double amount) {
       this.amount = amount;
   }
   public boolean isPaid() {
       return isPaid;
   }
   public void setPaid(boolean isPaid) {
       this.isPaid = isPaid;
   }
   public String getAmountInString() {
       String s = amount + "";
       return s;
   }
   // --- toString() gia emfanish pliroforiwn ---
   @Override
   public String toString() {
       return "Fine {" +
              "loanId = " + loan.getLoanId() +
              ", amount = " + amount +
              "€, isPaid = " + isPaid +
              '}';
   }
}



