package unipi.library.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import unipi.library.classes.Loan;
import unipi.library.classes.Student;

import java.util.List;

public class ViewLoanHistorySceneCreator extends SceneCreator {
    	
	private Stage stage;
	private Runnable onCancel;
	private StudentManager studentManager;
	private LoanManager loanManager;
	   
	public ViewLoanHistorySceneCreator(Stage stage, double width, double height, Runnable onCancel, StudentManager studentManager, LoanManager loanManager) { //constructor
		super(width, height);
	    this.stage = stage;
	    this.onCancel = onCancel;
	    this.studentManager = studentManager;
	    this.loanManager = loanManager;
	}
	   
   @Override
   public Scene createScene() {
	   Text loanHistoryText = new Text("View Student's Loan History"); //epikefalida
	   loanHistoryText.setFont(Font.font(30));
	   
       Label textLbl = new Label("Insert student ID:");
       TextField txtSearch = new TextField();
       Button btnSearch = new Button("Search");
       HBox searchRow = new HBox(20, textLbl, txtSearch, btnSearch); //seira me keno gia eisagwgh id kai search button
       //searchRow.setAlignment(Pos.CENTER);

       Label nameLbl = new Label(""); //label pou tha brisketai to onoma tou foititi pou tairiazei sto id pou eisaxthike efoson yparxei      
       Button btnBack = new Button("Go Back");

       ListView<String> loanListView = new ListView<>(); //keno opou tha brisketai mia lista me olous tous daneismous pou exei pragmatopoihsei o foititis
       
       btnSearch.setOnAction(e -> { //anathesi leitourgias tou koumpiou anazitisis
           String studentID = txtSearch.getText();
           Student found = studentManager.findByStudentId(studentID); //klhsh methodou gia thn euresh tou foititi pou tairiazei to student id (null an den yparxei)

           if (studentID.length() == 0) { // an to pedio eisagwghs id einai keno
        	   showAlert("You can't leave this field empty");
               return;
           }
           else if (found == null) { //alliws an den brethei foititis me to student ID pou eisaxthike
               showAlert("There is no student with this student ID in our database");
               return;
           }
           else { //alliws an brethei o foititis me auto to student id
        	   List<Loan> loans = loanManager.getLoansByStudent(found); //anaktisi olwn twn daneismwn tou
        	   nameLbl.setText( found.getFullName() + "'s Loan History:");
               
               if (loans.isEmpty()) { //an den exei kanenan daneismo emfanizetai analogo mhnyma
                   loanListView.getItems().add("Student's loan history is empty");
               } else { //alliws emfanizontai oloi me thn morfh pou fainetai parakatw
                   for (Loan loan : loans) {
                       loanListView.getItems().add(
                               "Book: " + loan.getBook().getTitle() +
                               " | Loan Date: " + loan.getLoanDate() +
                               " | Return Date: " + (loan.getReturnDate() != null ? loan.getReturnDate() : "pending") +
                               " | Status: " + loan.getStatus()
                       );
                   }
               }
           }
       });
       
       btnBack.setOnAction(e -> { //anathesi leitourgias tou koumpiou epistrofhs pisw
           onCancel.run(); // epistrofh
       });
       
       VBox root = new VBox(20);
       //root.setAlignment(Pos.TOP_CENTER);
       root.setPadding(new Insets(20));
       root.getChildren().addAll(loanHistoryText, searchRow, nameLbl, loanListView, btnBack); //stoibagmena to mhnyma "Edit Book", me to searchBox, ta dedomena tou bibliou kai saveBtn
       return new Scene(root, width, height);
   }
   
   private boolean showConfirmation(String message) { //methodos gia thn epibebaiwsh apothikeushs bibliou h akyrwshs diadikasias
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO); //dhmiourgia pop-up window ebibebaiwtikou typou me sxetiko mhnyma kai koumpia nai kai oxi
       alert.showAndWait(); //emfanish tou pop-up kai anamonh epiloghs Yes/No apo ton xrhsth
       if (alert.getResult() == ButtonType.YES) { 
       	return true; //an epilejei nai tote epistrefetai true
       }
       else {
       	return false; //alliws me epilogh oxi epistrefetai false
       }
   }

   private void showAlert(String message) { //methodos gia thn dhmiourgia pop up mhnymatos meta thn epibebaiwsh apothikeusis h akyrwshs
       Alert alert = new Alert(Alert.AlertType.INFORMATION, message); 
       alert.showAndWait();
   }
}


