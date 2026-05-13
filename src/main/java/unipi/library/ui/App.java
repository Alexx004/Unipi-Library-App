package unipi.library.ui;

import javafx.application.Application;
import unipi.library.classes.StandardFeePolicy;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    //dhlwsh skhnwn
    private Stage stage;
    private BookManager bookManager;
    private LoanManager loanManager;
    private StudentManager studentManager;
    private Scene mainScene;
    private Scene bookManagementScene;
    private Scene studentManagementScene;
    private Scene loanManagementScene;
    private Scene paymentManagementScene;
    
    @Override
    public void start(Stage stage) {
    	this.bookManager = new BookManager(); //arxikopoihsh bookManager
    	this.loanManager = new LoanManager(new StandardFeePolicy()); //arxikopoihsh loan manager
    	this.studentManager = new StudentManager(); //arxikopoiisi student manager
    	this.stage = stage;
    	this.stage.setTitle("Unipi Library App"); //epikefalida tou main stage ths efarmoghs
    	createScenes(); // Klhsh ths methodou opou dhmiourgountai oles oi skhnes
    	this.stage.setScene(mainScene); //orismos basikhs skhnhs
    	this.stage.show(); //ekinhsh efarmoghs
    }
    private void createScenes() { //Methodos opou dhmiourgountai oles oi skhnes ths efarmoghs
	// Dhmiourgia main scene
    mainScene = new MainSceneCreator(850, 400, this::switchToBookManagement, this::switchToStudentManagement, this::switchToLoanManagement, this::switchToPaymentManagement).createScene();
   	// Dhmiourgia twn ypoloipwn scenes ths efarmoghs
    bookManagementScene = new BookManagementSceneCreator(stage, 850, 400, this::switchToMainMenu, bookManager).createScene();
    studentManagementScene = new StudentManagementSceneCreator(stage, 850, 400, this::switchToMainMenu, studentManager, loanManager).createScene();
    loanManagementScene = new LoanManagementSceneCreator(stage, 850, 400, this::switchToMainMenu, loanManager, bookManager, studentManager).createScene();
    paymentManagementScene = new PaymentManagementSceneCreator(stage, 850, 400, this::switchToMainMenu, loanManager).createScene();
    }
    
    private void switchToMainMenu() { //methodos allaghs skhnhs sthn main scene
    	stage.setScene(mainScene);
    }
    
    private void switchToBookManagement() { //methodos allaghs skhnhs sthn book management
    	stage.setScene(bookManagementScene);
    }
    
    private void switchToStudentManagement() { //methodos allaghs skhnhs sthn student management
    	stage.setScene(studentManagementScene);
    }
    
    private void switchToLoanManagement() { //methodos allaghs skhnhs sthn loan management
    	stage.setScene(loanManagementScene);
    }
    
    private void switchToPaymentManagement() { //methodos allaghs skhnhs sthn payment management
    	stage.setScene(paymentManagementScene);
    }
    
    public static void main(String[] args) { //main tou programmatos opou epla ekteleitai h arxh
    	launch(args);
    }
}




