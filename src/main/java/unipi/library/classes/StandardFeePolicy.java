package unipi.library.classes;

import java.time.temporal.ChronoUnit;

// Klasi pou ylopoiei tin FeePolicy kai efarmozei 1€/imera kathisterisis
public class StandardFeePolicy implements FeePolicy {
	
   private final double ratePerDay = 1.0; // pososto prostimou ana imera
   @Override
   public double calculateFine(Loan loan) {
       // An den yparxei returnDate, den mporei na ypologistei prostimo
       if (loan.getReturnDate() == null || loan.getDueDate() == null) {
           return 0.0;
       }
       // Ypologismos imeron kathisterisis
       long daysLate = ChronoUnit.DAYS.between(loan.getDueDate(), loan.getReturnDate());
       // An den yparxei kathisterisi, epistrefei 0
       if (daysLate <= 0) {
           return 0.0;
       }
       // Ypologismos prostimou
       return daysLate * ratePerDay;
   }
}
	

