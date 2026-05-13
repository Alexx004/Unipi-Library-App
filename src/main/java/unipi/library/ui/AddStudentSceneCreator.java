package unipi.library.ui;

import java.time.LocalDate;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import unipi.library.classes.Student;

public class AddStudentSceneCreator extends SceneCreator {
	
	private Stage stage;
	private Runnable onCancel;
	private StudentManager studentManager;
   
   public AddStudentSceneCreator(Stage stage, double width, double height, Runnable onCancel, StudentManager studentManager) {
       super(width, height);
       this.stage = stage;
       this.onCancel = onCancel;
       this.studentManager = studentManager;
   }
   
   public Scene createScene() {
	   Text addStudentText = new Text("Add Student");
	   addStudentText.setFont(Font.font(24));


	   
	   GridPane studentInfo = new GridPane(); //neo gridpane layout gia ta labels kai ta fields
	   studentInfo.setHgap(15);
	   studentInfo.setVgap(10);
	   studentInfo.setAlignment(Pos.CENTER);
       
       Label lbl1 = new Label("First Name:");
       studentInfo.add(lbl1, 0, 0);
       TextField txt1 = new TextField();
       studentInfo.add(txt1, 1, 0);
       Label lbl2 = new Label("Last Name");
       studentInfo.add(lbl2, 0, 1);
       TextField txt2 = new TextField();
       studentInfo.add(txt2, 1, 1);
       Label lbl3 = new Label("Student ID:");
       studentInfo.add(lbl3, 0, 2);
       TextField txt3 = new TextField();
       studentInfo.add(txt3, 1, 2);
       Label lbl4 = new Label("Email:");
       studentInfo.add(lbl4, 0, 3);
       TextField txt4 = new TextField();
       studentInfo.add(txt4, 1, 3);
       Label lbl5 = new Label("Phone Number:");
       studentInfo.add(lbl5, 0, 4);
       TextField txt5 = new TextField();
       studentInfo.add(txt5, 1, 4);
       Label lbl6 = new Label("Department");
       studentInfo.add(lbl6, 0, 5);
       TextField txt6 = new TextField();
       studentInfo.add(txt6, 1, 5);
       Label lbl7 = new Label("Date of Birth");
       studentInfo.add(lbl7, 0, 6);
       DatePicker datePicker = new DatePicker();
       studentInfo.add(datePicker, 1, 6);
       
       Button submit = new Button("Submit");
       Button back = new Button("Go Back");
       
       CustomButton saveBtn = new CustomButton("Submit", 120, 40); //Koumpi gia apothikeush foititi
	   CustomButton cancelBtn = new CustomButton("Go Back", 120, 40); //Koumpi gia akyrwsh diadikasias
	   HBox buttons = new HBox(20, saveBtn, cancelBtn); //ta submit kai cancel buttons se seira
       buttons.setAlignment(Pos.CENTER);
       
       

       saveBtn.setOnAction(e -> { //anathesi leitourgias tou submit button

    	   LocalDate date = datePicker.getValue(); 
    	   if (date == null) { //elegxos gia thn apofygh crash
        	   showAlert("You must select a date of birth");
           }
    	   else {
    		   String dateOfBirth = datePicker.getValue().toString();
    		   if (validInput(txt1, txt2, txt3, txt4, txt5, txt6, dateOfBirth)) {
    			   
        	       int id = studentManager.getAllStudents().size() + 1;
                   Student student = new Student(id, //dhmiourgia neou foititi
            		                             txt1.getText(), 
            		                             txt2.getText(), 
            		                             dateOfBirth, 
            		                             txt5.getText(), 
            		                             txt4.getText(), 
            		                             txt3.getText(), 
            		                             txt6.getText());
              
                   studentManager.addStudent(student); //prosthiki tou foititi sto systhma
                   showAlert("Student has been successfully added");
                   onCancel.run(); // epistrofh
    		   }
    	   }
           
       });
       
       cancelBtn.setOnAction(e -> { //anathesi leitourgias tou koumpiou cancel
    	   boolean confirm = showConfirmation("Are you sure you want to cancel the process?");
           if (confirm) { //an confirm == true
               onCancel.run(); // epistrofh
           }
       });
       
       VBox root = new VBox(20, addStudentText, studentInfo, buttons); //VBox me epikefalida, gridpane gia ta student info kai koumpia gia apothikeusi kai akyrwsh, ola stoibagmena
       root.setAlignment(Pos.CENTER);
       return new Scene(root, width, height);
   }
   
   
   
   
   private boolean validInput(TextField txt1, TextField txt2, TextField txt3, TextField txt4, TextField txt5, TextField txt6, String dateOfBirth) {
	   
	   String firstName = txt1.getText();
       String lastName = txt2.getText();
	   String studentID = txt3.getText(); 
	   String email = txt4.getText();
       String phoneNumber = txt5.getText();
       String department = txt6.getText();
       
       
	   if (firstName.length() == 0 ||
		   lastName.length() == 0 ||
	       studentID.length() == 0 ||
	       email.length() == 0 ||
	       phoneNumber.length() == 0 ||
	       department.length() == 0 ||
	       dateOfBirth.length() == 0) {

		    showAlert("You can't leave empty fields!");
       	    return false; //an dhladh estw ena pedio den exei symplhrwthei apo ton xrhsth emfanizetai proeidopoihtiko mhnyma kai den ginetai apothikeusi
	   }
	   
	   if (studentManager.findByStudentId(studentID) != null) {
		   showAlert("This ID already belongs to another student");
		   return false;
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
	   if (!validStudentID(studentID)) {
		   showAlert("Invalid student ID form");
		   return false;
	   }
	   if (!validEmail(email)) {
		   showAlert("Invalid email form");
		   return false;
	   }
	   return true;
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
   
   public boolean validStudentID(String id) { //methodos gia thn egkyrothta tou student id to opoio prepei na exei thn morfh A12345
	   if (id.length() != 6) { //an den exei 6 xarakthres -> false
		   return false;
	   }
	   if (!Character.isLetter(id.charAt(0))) { //an o prwtos xarakthras den einai char -> false
	        return false;
	    }
	    for (int i = 1; i < 6; i++) {
	        if (!Character.isDigit(id.charAt(i))) { //an oi ypoloipoi 5 xarakthres den einai psifia -> false
	            return false;
	        }
	    }
	    return true;
   }
   public boolean validEmail(String email) { //methodos gia ton elegxo egkyrothtas tou email tou foititi
	    return email.matches(".+@.+\\..+");
  }
  
}

