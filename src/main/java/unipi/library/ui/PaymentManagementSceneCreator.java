package unipi.library.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaymentManagementSceneCreator extends SceneCreator {

    private Runnable onReturnToMainMenu;
    private Stage stage;
    private LoanManager loanManager;

    public PaymentManagementSceneCreator(Stage stage, double width, double height, Runnable onReturnToMainMenu, LoanManager loanManager) { //instructor gia thn payment management 
        super(width, height);
        this.stage = stage;
        this.onReturnToMainMenu = onReturnToMainMenu;
        this.loanManager = loanManager;
    }

    @Override
    public Scene createScene() {
        Text PMText = new Text("Payment Management"); //epikefalida
        PMText.setFont(Font.font(30));
 
        //dhmiourgia koumpiwn gia tis 4 leitourgies ths skhnhs
        CustomButton viewPendingBtn = new CustomButton("View Pending Payments", 180, 50);
        CustomButton finePaymentBtn = new CustomButton("Fine Payment", 180, 50);
        CustomButton viewHistoryBtn = new CustomButton("View Payment History", 180, 50);
        CustomButton backBtn = new CustomButton("Return to Main Menu", 180, 50);
        
        
        
        viewPendingBtn.setOnAction(e -> { //Anathesi ths leitourgias tou viewPendingBtn
        	Scene pendingFinesListScene = new PendingFinesListSceneCreator(stage, width, height, () -> stage.setScene(this.createScene()), loanManager).createScene(); //klhsh skhnhs gia dhmiourgia neou bibliou
            stage.setScene(pendingFinesListScene);
        });
        
        finePaymentBtn.setOnAction(e -> { //Anathesi ths leitourgias tou finePaymentBtn
        	Scene finePaymentScene = new FinePaymentSceneCreator(stage, width, height, () -> stage.setScene(this.createScene()), loanManager).createScene(); //klhsh skhnhs gia dhmiourgia neou bibliou
            stage.setScene(finePaymentScene);
        });
        
        viewHistoryBtn.setOnAction(e -> { //Anathesi ths leitourgias tou viewPendingBtn
        	Scene viewHistoryScene = new PaymentHistorySceneCreator(stage, width, height, () -> stage.setScene(this.createScene()), loanManager).createScene(); //klhsh skhnhs gia dhmiourgia neou bibliou
            stage.setScene(viewHistoryScene);
        });
        
        

        backBtn.setOnAction(e -> onReturnToMainMenu.run()); //Anathesi ths leitourgias tou back button

        HBox buttons = new HBox(20, viewPendingBtn, finePaymentBtn, viewHistoryBtn); //topothetisi twn 3 basikwn leitourgiwn ths skhnhs se seira
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(100, PMText, buttons, backBtn); //topothetisi se stoiba epikefalidas, twn 4 leitourgiwn kai tou back button
        root.setAlignment(Pos.TOP_CENTER);

        return new Scene(root, width, height);
    }
}
