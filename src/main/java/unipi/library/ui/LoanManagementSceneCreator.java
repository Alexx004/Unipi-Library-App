package unipi.library.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoanManagementSceneCreator extends SceneCreator {
   private Stage stage;
   private Runnable onReturnToMainMenu;
   private LoanManager loanManager;
   private BookManager bookManager;
   private StudentManager studentManager;
   
   public LoanManagementSceneCreator(Stage stage, double width, double height, Runnable onReturnToMainMenu, LoanManager loanManager, BookManager bookManager, StudentManager studentManager) {
       super(width, height);
       this.stage = stage;
       this.onReturnToMainMenu = onReturnToMainMenu;
       this.loanManager = loanManager; // Apothikevoume ton LoanManager
       this.bookManager = bookManager;
       this.studentManager = studentManager;
   }
   @Override
   public Scene createScene() {
       Text LMText = new Text("Loan Management");
       LMText.setFont(Font.font(30));  
       
       //koumpia me oles tis epiloges tou xrhsth
       CustomButton newLoanBtn = new CustomButton("New Loan", 150, 50);
       CustomButton returnBookBtn = new CustomButton("Book Return", 150, 50);
       CustomButton viewLoanListBtn = new CustomButton("View Loan List", 150, 50);
       CustomButton backBtn = new CustomButton("Return to Main Menu", 150, 50);

       newLoanBtn.setOnAction(e -> { //Anathesi leitourgias tou newLoanBtn (allagh skhnhs)
           Scene scene = new NewLoanSceneCreator(stage, width, height, () -> stage.setScene(this.createScene()),loanManager, bookManager, studentManager).createScene();
           stage.setScene(scene);
       });
       returnBookBtn.setOnAction(e -> { //Anathesi leitourgias tou returnBookBtn (allagh skhnhs)
           Scene scene = new ReturnBookSceneCreator(stage, width, height, () -> stage.setScene(this.createScene()),loanManager).createScene();
           stage.setScene(scene);
       });
       viewLoanListBtn.setOnAction(e -> { //Anathesi leitourgias tou viewLoanListBtn (allagh skhnhs)
           Scene scene = new LoanListSceneCreator(stage, width, height, () -> stage.setScene(this.createScene()),loanManager).createScene();
           stage.setScene(scene);
       });
       
       backBtn.setOnAction(e -> onReturnToMainMenu.run()); //Anathesi leitourgias tou newLoanBtn (epistrogh sto main menu)
       HBox buttons = new HBox(20, newLoanBtn, returnBookBtn, viewLoanListBtn); //Sthn seira ta 4 koumpia twn basikwn leitourgiwn
       buttons.setAlignment(Pos.CENTER);
       VBox root = new VBox(100, LMText, buttons, backBtn); //VBox me epikefalida, leitourgies kai backBtn stoibagmena
       root.setAlignment(Pos.TOP_CENTER);
       return new Scene(root, width, height);
   }
}



