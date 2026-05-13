package unipi.library.ui;

import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StudentManagementSceneCreator extends SceneCreator {

    private Runnable onReturnToMainMenu;
    private Stage stage;
    private StudentManager studentManager;
    private LoanManager loanManager;

    public StudentManagementSceneCreator(Stage stage, double width, double height, Runnable onReturnToMainMenu, StudentManager studentManager, LoanManager loanManager) { //constructor
        super(width, height);
        this.stage = stage;
        this.onReturnToMainMenu = onReturnToMainMenu;
        this.studentManager = studentManager;
        this.loanManager = loanManager;
    }

    @Override
    public Scene createScene() {
        Text title = new Text("Student Management"); //epikefalida skhnhs
        title.setFont(Font.font(30));

        //Dhmiourgia twn 4 buttons pou odhgoun stis diaforetikes energeies ths book management. Ola me width=150 kai height=50
        CustomButton newStudentBtn = new CustomButton("New Student", 150, 50); 
        CustomButton editStudentBtn = new CustomButton("Edit Student Info", 150, 50);
        CustomButton viewHistoryBtn = new CustomButton("View Student's Loan History", 150, 50);
        CustomButton backBtn = new CustomButton("Return to Main Menu", 150, 50);
        
        newStudentBtn.setOnAction(e -> {
        	Scene newStudentScene = new AddStudentSceneCreator(stage, width, height, () -> stage.setScene(this.createScene()), studentManager).createScene(); //klhsh skhnhs gia dhmiourgia neou bibliou
            stage.setScene(newStudentScene);
        });
        
        editStudentBtn.setOnAction(e -> {
        	Scene editStudentScene = new EditStudentSceneCreator(stage, width, height, () -> stage.setScene(this.createScene()), studentManager).createScene();
            stage.setScene(editStudentScene);
        });
        
        viewHistoryBtn.setOnAction(e -> {
            Scene viewLoanHistoryScene = new ViewLoanHistorySceneCreator(stage, width, height, () -> stage.setScene(this.createScene()), studentManager, loanManager).createScene();
            stage.setScene(viewLoanHistoryScene);
        });

        backBtn.setOnAction(e -> onReturnToMainMenu.run()); //Anathesi ths leitourgias tou back button

        HBox buttonRow = new HBox(20, newStudentBtn, editStudentBtn, viewHistoryBtn); //topothetisi twn 3 leitourgiwn se seira
        buttonRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(100, title, buttonRow, backBtn); //stoiba me thn epikefalida, tis leitourgies kai to back button
        root.setAlignment(Pos.TOP_CENTER);

        return new Scene(root, width, height);
    }
}
