package unipi.library.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import unipi.library.classes.Student;


public class EditStudentSceneCreator extends SceneCreator {
	
   private Stage stage;
   private Runnable onCancel;
   private StudentManager studentManager;
   
   public EditStudentSceneCreator(Stage stage, double width, double height, Runnable onCancel, StudentManager studentManager) { //constructor
       super(width, height);
       this.stage = stage;
       this.onCancel = onCancel;
       this.studentManager = studentManager;
   }
   
   @Override
   public Scene createScene() {
	   Text editStudentText = new Text("Edit Student Info"); //epikefalida
	   editStudentText.setFont(Font.font(30));
		
       Label lblSearch = new Label("Insert student ID:"); 
       TextField txtSearch = new TextField(); //text field gia thn eisagwgh tou student id tou foititi tou opoiou ta dedomena epithymoume na allajoume
       Button btnSearch = new Button("Search");
       Button btnBack = new Button("Go back");
       
       HBox searchBox = new HBox(10, lblSearch, txtSearch, btnSearch, btnBack); //mhnyma "Enter student ID:", text field gia eisagwgh id, koumpi anazhthshs kai koumpi epistrofhs pisw sthn seira
       searchBox.setAlignment(Pos.CENTER);
       
       GridPane studentInfoBox = new GridPane(); //GridPane opou tha einai ta info tou foititi pou tairiazei sto student id efoson yparxei
       studentInfoBox.setHgap(15);
       studentInfoBox.setVgap(10);
       studentInfoBox.setPadding(new Insets(10));
       studentInfoBox.setAlignment(Pos.CENTER);
       
       //ola ta dedomena tou xrhsth se morfh gridpane
       Label lbl1 = new Label("First Name:");
       studentInfoBox.add(lbl1, 0, 0);
       TextField txt1 = new TextField();
       studentInfoBox.add(txt1, 1, 0);
       
       Label lbl2 = new Label("Last Name:");
       studentInfoBox.add(lbl2, 0, 1);
       TextField txt2 = new TextField();
       studentInfoBox.add(txt2, 1, 1);
       
       Label lbl3 = new Label("Email:");
       studentInfoBox.add(lbl3, 0, 2);
       TextField txt3 = new TextField();
       studentInfoBox.add(txt3, 1, 2);
       
       Label lbl4 = new Label("Department:");
       studentInfoBox.add(lbl4, 0, 3);
       TextField txt4 = new TextField();
       studentInfoBox.add(txt4, 1, 3);
       
       Label lbl5 = new Label("Phone Number:");
       studentInfoBox.add(lbl5, 0, 4);
       TextField txt5 = new TextField();
       studentInfoBox.add(txt5, 1, 4);
       
       studentInfoBox.setVisible(false); //to grid pane tha einai aorato mexri kai efoson brethei foititis me to id pou eisagetai

       
       Button btnSave = new Button("Save Changes"); //Koumpi apothikeusis newn dedomenwn
       btnSave.setVisible(false); //arxika mh orato ston xrhsth
       
       
       btnSearch.setOnAction(e -> { //Anathesi leitourgias tou koumpiou search
           String studentID = txtSearch.getText();
           Student found = studentManager.findByStudentId(studentID); //klhsh methodou gia thn euresh tou foititi pou tairiazei to student id (null an den yparxei)
           
           if (found != null) {
        	   //an brethike foititis me auto to id ta emfanizontai ta dedomena tou
               txt1.setText(found.getFirstName());
               txt2.setText(found.getLastName());
               txt3.setText(found.getEmail());
               txt4.setText(found.getDepartment());
               txt5.setText(found.getPhone());
               
             //emfanish ths formas kai tou save Button
               studentInfoBox.setVisible(true);
               btnSave.setVisible(true);

           } else { //an den brethike tetoios foititis
               showAlert("There is no student with this student ID in our database");
           }
       });
      
       btnSave.setOnAction(e -> { //Anathesi leitourgias tou koumpiou apothikeusis twn allagwn
    	   if (validInput(txt1, txt2, txt3, txt5, txt4)) { //klisi ths valid input gia elegxo twn newn dedomenwn
    	       boolean confirm = showConfirmation("Confirm changes to this book?");
       		   if (confirm) { //an epibebaiwthei h apothikeusi mesw tou pop up
       			   String studentID = txtSearch.getText();
       			   Student found = studentManager.findByStudentId(studentID); //klhsh methodou gia thn euresh tou foititi pou tairiazei to student id, ayth th fora exontas epibebaiwsei oti yparxei
           
       			   //efarmogh olwn twn allagwn, efoson eginan
       			   found.setFirstName(txt1.getText());
       			   found.setLastName(txt2.getText());
       			   found.setEmail(txt3.getText());
       			   found.setDepartment(txt4.getText());
       			   found.setPhone(txt5.getText());
            
               onCancel.run(); //epistrofh sto student management
       		   }
    	   }
       });
       
       btnBack.setOnAction(e -> { //anathesi leitourgias tou koumpiou epistrofhs pisw
    	   boolean confirm = showConfirmation("Are you sure you want to cancel the process?");
           if (confirm) { //an confirm == true
               onCancel.run(); // epistrofh
           }
       });
       
       VBox root = new VBox(20);
       root.setAlignment(Pos.TOP_CENTER);
       root.setPadding(new Insets(20));
       root.getChildren().addAll(editStudentText, searchBox, studentInfoBox, btnSave); //stoibagmena to mhnyma "Edit Book", me to searchBox, ta dedomena tou bibliou kai saveBtn
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
   
   private boolean validInput(TextField txt1, TextField txt2, TextField txt3, TextField txt4, TextField txt5) { //methodos gia thn elegxo egkyrothtas twn dedomenwn tou foititi
	   
	   String firstName = txt1.getText();
       String lastName = txt2.getText(); 
	   String email = txt3.getText();
       String phoneNumber = txt4.getText();
       String department = txt5.getText();
       
       
	   if (firstName.length() == 0 ||
		   lastName.length() == 0 ||
	       email.length() == 0 ||
	       phoneNumber.length() == 0 ||
	       department.length() == 0) {

		    showAlert("You can't leave empty fields!");
       	    return false; //an dhladh estw ena pedio den exei symplhrwthei apo ton xrhsth emfanizetai proeidopoihtiko mhnyma kai den ginetai apothikeusi
	   }
	   
	   if (!onlychars(firstName)) { //an first name den einai mono xarakthres -> false
		   showAlert("Invalid first or last name form");
		   return false;
	   }
	   if (!onlychars(lastName)) { //an last name den einai mono xarakthres -> false
		   showAlert("Invalid first or last name form");
		   return false;
	   }
	   if (!onlychars(department)) { //an to category den einai mono xarakthres -> false
		   showAlert("Invalid department");
		   return false;
	   }
	   if (!onlydigits(phoneNumber) || phoneNumber.length() != 10) { //an thlefwno den einai mono noumera h den einai 1- psifia akribws -> false
		   showAlert("Invalid phone number form");
		   return false;
	   }
	   if (!validEmail(email)) {
		   showAlert("Invalid email form");
		   return false;
	   }
	   return true;
   }
   
   public boolean onlychars(String a) { //methodos gia elegxo an ena string den apoteleitai mono apo xarakthres
	   for (char c : a.toCharArray()) {
	       if (!Character.isLetter(c)) {
	          return false;
	       }
	   }
	   return true;
   }
   
   public boolean onlydigits(String number) { //methodos gia ton elegxo an to thlefwno tou neou foititi den einai mono noumera
	   for (char c : number.toCharArray()) {
		    if (!Character.isDigit(c)) {
		        return false;	        
		    }
		}
	   return true;
   }
   public boolean validEmail(String email) { //methodos gia ton elegxo egkyrothtas tou email tou foititi
	    return email.matches(".+@.+\\..+");
   }
}

