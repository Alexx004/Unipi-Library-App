package unipi.library.ui;

import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import unipi.library.classes.Student;
import unipi.library.classes.Book;
import unipi.library.classes.Loan;

public class NewLoanSceneCreator extends SceneCreator {
	
   private Stage stage;
   private Runnable onReturn;
   private LoanManager loanManager;  
   private BookManager bookManager;
   private StudentManager studentManager;
   
   public NewLoanSceneCreator(Stage stage, double width, double height, Runnable onReturn, LoanManager loanManager, BookManager bookManager, StudentManager studentManager) { //constructor
       super(width, height);
       this.stage = stage;
       this.onReturn = onReturn;
       this.loanManager = loanManager;
       this.bookManager = bookManager;
       this.studentManager = studentManager;
   }
   
   @Override
   public Scene createScene() {
       Label title = new Label("New Loan"); //epikefalida
       
       ComboBox<Student> studentComboBox = new ComboBox<>( //combobox me olous tous foitites gia thn epilogh autou pou tha daneistei kapoio biblio
    		    FXCollections.observableArrayList(studentManager.getAllStudents())
    		);
    	studentComboBox.setPromptText("Select Student");
    		
       ComboBox<Book> bookComboBox = new ComboBox<>(FXCollections.observableArrayList( //combobox me ola ta biblia tou systhmatos
               bookManager.getBooks().stream()
                       .filter(Book::isAvailable)
                       .collect(Collectors.toList())
       ));
       bookComboBox.setPromptText("Select Book");
       
       DatePicker loanDatePicker = new DatePicker(LocalDate.now()); //h wra pou pragmatopoieitai o daneismos
       Button submitBtn = new Button("Submit Loan"); //koumpi gia thn apothikeusi tou neou daneismou sto systhna
       
       submitBtn.setOnAction(e -> { //anathesi leitourgias tou submit button
           Student selectedStudent = studentComboBox.getValue(); //o foithths pou epilexthke
           Book selectedBook = bookComboBox.getValue(); //to biblio pou epilexthke
           LocalDate loanDate = loanDatePicker.getValue(); //h wra tou daneismou
           if (selectedStudent == null || selectedBook == null || loanDate == null) {
               showAlert("You must fill out all the fields.");
               return;
           }
           if (selectedStudent.getCurrentActiveLoans() >= 5) {
        	   showAlert("Loan can not be completed\nStudent has reached loan limit");
        	   return;
           }
           // Dimioyrgia daneiou kai prosthiki ston manager
           int nextId = loanManager.getAllLoans().size() + 1;
           Loan loan = new Loan(nextId, selectedStudent, selectedBook, loanDate, null, false, "Active");
           selectedBook.setAvailable(false); //anathesi bibliou os mh diathesimo afou daneisthke apo kapoion foithth
           loanManager.addLoan(loan); //prosthiki tou loan sto systhma
           showAlert("Loan has been saved");
           onReturn.run();
       });
       
       Button backBtn = new Button("Go Back");
       backBtn.setOnAction(e -> onReturn.run()); //anathesi leitourgias tou koumpiou pou akyrwnei thn diadikasia kai epistrefei ton xrhsth sto loan Management Scene
       
       VBox root = new VBox(15, title, studentComboBox, bookComboBox, loanDatePicker, submitBtn, backBtn); //VBox olwn twn combobox kai buttons ths skhnhs
       root.setStyle("-fx-padding: 20; -fx-alignment: center;");
       return new Scene(root, width, height);
   }
   
   private void showAlert(String message) { //methodos gia thn dhmiourgia pop up mhnymatos meta thn epibebaiwsh apothikeusis h akyrwshs
       Alert alert = new Alert(Alert.AlertType.INFORMATION, message);  
       alert.showAndWait();
   }
}

