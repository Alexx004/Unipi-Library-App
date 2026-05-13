package unipi.library.ui;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import unipi.library.classes.Fine;
import unipi.library.classes.Loan;

public class ReturnBookSceneCreator extends SceneCreator {
	
   private Stage stage;
   private Runnable onReturn;
   private LoanManager loanManager;
   private Fine fine;

   public ReturnBookSceneCreator(Stage stage, double width, double height, Runnable onReturn, LoanManager loanManager) { //constructor
       super(width, height);
       this.stage = stage;
       this.onReturn = onReturn;
       this.loanManager = loanManager;
   }
   
   @Override
   public Scene createScene() {
	   
       Label title = new Label("Book Return"); //epikefalida
       
       ComboBox<Loan> loanComboBox = new ComboBox<>(FXCollections.observableArrayList( //combobox pou emfanizei olous tous energous daneismous bibliwn
               loanManager.getActiveLoans()
       ));
       
       loanComboBox.setPromptText("Select a loan");
       DatePicker returnDatePicker = new DatePicker(LocalDate.now()); //h akribhs hmeromhnia pou pragmatopoieitai h epistrofh
       Button returnBtn = new Button("Complete Return");
       
       returnBtn.setOnAction(e -> {
           Loan selectedLoan = loanComboBox.getValue(); //o daneismos pou epilexthke
           LocalDate returnDate = returnDatePicker.getValue();
           
           if (selectedLoan == null || returnDate == null) { //an den exei epilextei daneismos h den exei dwthei h akribhs hmeromhnia
               showAlert("You must first fill in all the fields"); //proeidopoihtiko mhnyma
               return;
           }

           loanManager.returnBook(selectedLoan, returnDate); //epistrofh bibliou mesw methodou tou loan manager
           if (selectedLoan.getStatus() == "Delayed") { //an kathisterise na epistrafei to biblio
        	   fine = loanManager.createFine(selectedLoan);
        	   loanManager.addFine(fine);
               showAlert("Book has been returned successfully"
               		             + "\nStatus: " + selectedLoan.getStatus() 
               		             + "\nFine: " +  fine.getAmount() + " euros");
               onReturn.run();
           }
           else {
               showAlert("Book has been returned successfully\nStatus: " + selectedLoan.getStatus());  
               onReturn.run();
           }

       });
       
       Button backBtn = new Button("Back");
       backBtn.setOnAction(e -> onReturn.run()); //Anathesi tou koumpiou BackBtn (epistrofh sthn loan Management Scene)
       
       VBox root = new VBox(15, title, loanComboBox, returnDatePicker, returnBtn, backBtn); //VBox me stoibagmena ta combobox kai buttons ths skhnhs
       root.setStyle("-fx-padding: 20; -fx-alignment: center;");
       return new Scene(root, width, height);
   }
   
   private void showAlert(String message) { //methodos gia thn dhmiourgia pop up mhnymatos meta thn epibebaiwsh apothikeusis h akyrwshs
       Alert alert = new Alert(Alert.AlertType.INFORMATION, message);  
       alert.showAndWait();
   }
}

