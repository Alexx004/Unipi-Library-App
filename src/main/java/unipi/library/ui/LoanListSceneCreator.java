package unipi.library.ui;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import unipi.library.classes.Loan;
import java.time.format.DateTimeFormatter;

public class LoanListSceneCreator extends SceneCreator {
	
   private Stage stage;
   private Runnable onReturn;
   private LoanManager loanManager;
   
   public LoanListSceneCreator(Stage stage, double width, double height, Runnable onReturn, LoanManager loanManager) { //constructor
       super(width, height);
       this.stage = stage;
       this.onReturn = onReturn;
       this.loanManager = loanManager;
   }
   
   @Override
   public Scene createScene() {
	   Text loanTxt = new Text("Loans"); //epikefalida
	   loanTxt.setFont(Font.font(30));
	   
       TableView<Loan> tableView = new TableView<>();
       
       // --- Sthles TableView ---
       TableColumn<Loan, String> studentCol = new TableColumn<>("Student"); //Sthlh me to full name tou foititi pou daneisthke 
       studentCol.setCellValueFactory(data ->
           new SimpleStringProperty(data.getValue().getStudent().getFullName())
       );
       
       TableColumn<Loan, String> bookCol = new TableColumn<>("Book"); //Sthlh me to biblio pou daneisthke o foititis
       bookCol.setCellValueFactory(data ->
           new SimpleStringProperty(data.getValue().getBook().getTitle())
       );
       
       TableColumn<Loan, String> loanDateCol = new TableColumn<>("Loan Date"); //sthlh gia thn hmeromhnias pou pragmatopoiithike o daneismos
       loanDateCol.setCellValueFactory(data -> 
       new SimpleStringProperty(data.getValue().getLoanDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))); //elegxos tou format tou date gia apofygh crash

       
       TableColumn<Loan, String> returnDateCol = new TableColumn<>("Return Date"); //sthlh gia thn hmeromhnias pou oloklirwthike o daneismos
       returnDateCol.setCellValueFactory(data -> 
       new SimpleStringProperty(data.getValue().getReturnDate() == null ? "Not returned" : //elegxos tou format tou date gia apofygh crash
           data.getValue().getReturnDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
       
       TableColumn<Loan, String> statusCol = new TableColumn<>("Status"); //sthlh me to status tou daneismou
       statusCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus() == null ? " " : //an den exei kapoia timh to status emfanizetai keno
    	   data.getValue().getStatus()));
    		   
       
       tableView.getColumns().addAll(studentCol, bookCol, loanDateCol, returnDateCol, statusCol); //prosthiki twn sthlwn ston pinaka
       tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       
       // --- Fortosi pragmatikon daneiwn ---
       tableView.setItems(FXCollections.observableArrayList(loanManager.getAllLoans()));
       Button backBtn = new Button("Go Back");
       backBtn.setOnAction(e -> onReturn.run());
       VBox root = new VBox(20, loanTxt, tableView, backBtn);
       root.setStyle("-fx-padding: 20; -fx-alignment: center;");
       return new Scene(root, width, height);
   }
}

