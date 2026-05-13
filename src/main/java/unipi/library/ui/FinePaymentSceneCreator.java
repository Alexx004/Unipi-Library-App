package unipi.library.ui;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import unipi.library.classes.Fine;

public class FinePaymentSceneCreator extends SceneCreator{

	private Stage stage;
	private Runnable onCancel;
	private LoanManager loanManager;
	
	public FinePaymentSceneCreator(Stage stage, double width, double height, Runnable onCancel, LoanManager loanManager) {
		super(width, height);
		this.stage = stage;
		this.onCancel = onCancel;
		this.loanManager = loanManager;
	}
	
	@Override
	public Scene createScene() {
		Text FPTxt = new Text("Fine Payment");
		FPTxt.setFont(Font.font(30));
		
		ComboBox<Fine> unpaidFineComboBox = new ComboBox<>(FXCollections.observableArrayList( //combobox pou emfanizei olous tous energous daneismous bibliwn
				loanManager.getUnpaidFines()
	              
	       ));
		unpaidFineComboBox.setPromptText("Select an Unpaid Fine");
		
		CustomButton confirmBtn = new CustomButton("Confirm Loan Payment", 250, 50);
		CustomButton backBtn = new CustomButton("Go Back", 250, 50);
		
		confirmBtn.setOnAction(e -> {
			Fine selectedFine = unpaidFineComboBox.getValue(); //to prostimo pou epilexthke
			
			if (selectedFine == null) {
				showAlert("Please select a fine to pay");
				return;
			}
			else {
				selectedFine.setPaid(true);
				selectedFine.getLoan().setStatus("Completed");
				showAlert("Fine has been paid successfully");
				onCancel.run();
			}
		});

		backBtn.setOnAction(e -> onCancel.run()); //Anathesi tou koumpiou BackBtn (epistrofh sthn paymentManagementScene)
		
		VBox root = new VBox(50, FPTxt, unpaidFineComboBox, confirmBtn, backBtn);
		root.setAlignment(Pos.TOP_CENTER);
	    return new Scene(root, width, height);
	}
	
	private void showAlert(String message) { //methodos gia thn dhmiourgia pop up mhnymatos meta thn epibebaiwsh apothikeusis h akyrwshs
	       Alert alert = new Alert(Alert.AlertType.INFORMATION, message);  
	       alert.showAndWait();
	   }
}
