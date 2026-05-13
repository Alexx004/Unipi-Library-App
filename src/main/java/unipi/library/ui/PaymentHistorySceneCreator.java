package unipi.library.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import unipi.library.classes.Fine;

public class PaymentHistorySceneCreator extends SceneCreator{

	private Stage stage;
	private Runnable onCancel;
	private LoanManager loanManager;

	public PaymentHistorySceneCreator(Stage stage, double width, double height, Runnable onCancel, LoanManager loanManager) {
		super(width, height);
		this.stage = stage;
		this.onCancel = onCancel;
		this.loanManager = loanManager;
	}
	
	@Override
	public Scene createScene() {
		Text unpaidFinesTxt = new Text("Paid Fines"); //epikefalida
		unpaidFinesTxt.setFont(Font.font(30));

		TableView<Fine> tableView = new TableView<>();
		
		TableColumn<Fine, String> studentCol = new TableColumn<>("Student"); //Sthlh me to full name tou foititi pou dexetai to prostimo 
	    	studentCol.setCellValueFactory(data ->
	           new SimpleStringProperty(data.getValue().getLoan().getStudent().getFullName())
	     );
	    	
	    TableColumn<Fine, String> bookCol = new TableColumn<>("Book"); //Sthlh me to onoma tou bibliou tou daneismou tou prostimou
	    	bookCol.setCellValueFactory(data ->
	           new SimpleStringProperty(data.getValue().getLoan().getBook().getTitle())
	     );
	    	
	    TableColumn<Fine, String> amountCol = new TableColumn<>("Amount"); //Sthlh me to poso tou prostimou 
	    	amountCol.setCellValueFactory(data ->
	           new SimpleStringProperty(data.getValue().getAmountInString())
	     );
	       
	     tableView.getColumns().addAll(studentCol, bookCol, amountCol); //prosthiki twn sthlwn ston pinaka
		 tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		 tableView.setItems(FXCollections.observableArrayList(loanManager.getPaidFines()));
		 
		 CustomButton backBtn = new CustomButton("Go Back", 120, 40); //Koumpi gia na paei o librarian pisw
         backBtn.setOnAction(e -> {
        	 onCancel.run();
         });
         
         VBox root = new VBox(20, unpaidFinesTxt, tableView, backBtn);
         root.setStyle("-fx-padding: 20; -fx-alignment: center;");
         return new Scene(root, width, height);
	}

}
