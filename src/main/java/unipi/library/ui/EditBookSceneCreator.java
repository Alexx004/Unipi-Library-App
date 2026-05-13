package unipi.library.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import unipi.library.classes.Book;

public class EditBookSceneCreator extends SceneCreator {

	private Stage stage;
	private Runnable onCancel;
	private BookManager bookManager;

	public EditBookSceneCreator(Stage stage, double width, double height, Runnable onCancel, BookManager bookManager) { //constructor
		super(width, height);
		this.stage = stage;
		this.onCancel = onCancel;
		this.bookManager = bookManager;
	}

	@Override
	public Scene createScene() {
		Text EBText = new Text("Edit Book Info"); //epikefalida
		EBText.setFont(Font.font(30));

        Label isbnLabel = new Label("Enter ISBN:"); 
        TextField isbnField = new TextField(); //pedio opou o xrhsths tha eisagei to isbn tou bibliou tou opoiou ta stoixeia epithymei na allajei 
        CustomButton searchBtn = new CustomButton("Search", 120, 40); //koumpi pou jekinaei thn anazhthsh tou bibliou me bash to isbn
        CustomButton goBackBtn = new CustomButton("Go Back", 120, 40); //Koumpi gia thn epistrogh sto book management
        
        HBox searchBox = new HBox(10, isbnLabel, isbnField, searchBtn, goBackBtn); //mhnyma "Enter ISBN:", text field gia eisagwgh isbn kai koumpi anazhthshs sthn seira
        searchBox.setAlignment(Pos.CENTER);

        GridPane formBox = new GridPane(); //GridPane opou tha einai ta info tou bibliou pou anazhtei o xrhsths
        formBox.setHgap(15);
        formBox.setVgap(10);
        formBox.setPadding(new Insets(10));
        formBox.setAlignment(Pos.CENTER);
        
        
        //dhmiourgia tou gridpane me ola ta dedomena tou bibliou
        Label title = new Label("Title: ");
        formBox.add(title, 0, 0);
        TextField titleField = new TextField();
        formBox.add(titleField, 1, 0);
        
        Label author = new Label("Author: ");
        formBox.add(author, 0, 1);
        TextField authorField = new TextField();
        formBox.add(authorField, 1, 1);
        
        Label publisher = new Label("Publisher: ");
        formBox.add(publisher, 0, 2);
        TextField publisherField = new TextField();
        formBox.add(publisherField, 1, 2);
        
        Label publicationYear = new Label("Publication Year: ");
        formBox.add(publicationYear, 0, 3);
        TextField publicationYearField = new TextField();
        formBox.add(publicationYearField, 1, 3);
        
        Label category = new Label("Category: ");
        formBox.add(category, 0, 4);
        TextField categoryField = new TextField();
        formBox.add(categoryField, 1, 4);
        
        Label availability = new Label("Available: ");
        formBox.add(availability, 0, 5);
        CheckBox availabilityBox = new CheckBox();
        formBox.add(availabilityBox, 1, 5);
        
        formBox.setVisible(false); //to grid pane tha einai aorato mexri kai efoson brethei biblio me to isbn pou plhktrologei o xrhsths
        
        
        CustomButton saveBtn = new CustomButton("Save Changes", 120, 40); //Koumpi apothikeusis newn dedomenwn
        saveBtn.setVisible(false); //arxika mh orato ston xrhsth
        
        
        
        searchBtn.setOnAction(e -> { //Anathesi ths leitourgias tou koumpiou anazhthshs
        	String isbn = isbnField.getText(); 
            Book book = bookManager.getBookByIsbn(isbn); //kalei thn methodo getBookByIsbn gia na brei to biblio pou antistoixei sto isbn 
            
            if (book == null) { //An epistrafei null, dhladh den yparxei to biblio sto database
            	showAlert("ISBN does not match any book in our database"); //Eidiko mhnyma
            }
            else { 
            	//arxikopoihsh olwn twn textfields me tis times pou antistoixoun sto biblio me to isbn pou eishgage o xrhsths
            	titleField.setText(book.getTitle());
            	authorField.setText(book.getAuthor());
            	publisherField.setText(book.getPublisher());
            	publicationYearField.setText(String.valueOf(book.getPublicationYear()));
            	categoryField.setText(book.getCategory());
            	availabilityBox.setSelected(book.isAvailable());

            	//emfanish ths formas kai tou save Button
            	formBox.setVisible(true);
            	saveBtn.setVisible(true);
            }
        });
        
        
        saveBtn.setOnAction(e -> { //Anathesi ths leitourgias tou koumpiou apothikeusis
        	if (validInput(titleField, authorField, publisherField, publicationYearField, categoryField)) {
        		boolean confirm = showConfirmation("Confirm changes to this book?");
        		if (confirm) {
        			String isbn = isbnField.getText();
        			Book book = bookManager.getBookByIsbn(isbn); //kalei thn methodo getBookByIsbn gia na brei to biblio pou antistoixei sto isbn 
        			
        			//efarmogh olwn twn allagwn, efoson eginan 
        			book.setTitle(titleField.getText());
        			book.setAuthor(authorField.getText());
        			book.setPublisher(publisherField.getText());
        			book.setPublicationYear(Integer.parseInt(publicationYearField.getText()));
        			book.setCategory(categoryField.getText());
        			book.setAvailable(availabilityBox.isSelected());
        			
        			onCancel.run(); //epistrofh sto book management
            	}
        	}
        	
        });
        
        goBackBtn.setOnAction(e -> { //Anathesi ths leitourgias tou koumpiou cancel
        	boolean confirm = showConfirmation("Are you sure you want to return?");
        	if (confirm) { //an confirm == true
                onCancel.run(); // epistrofh sto book management scene
            }
        });
        
        
        VBox root = new VBox(20);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(EBText, searchBox, formBox, saveBtn); //stoibagmena to mhnyma "Edit Book", me to searchBox, ta dedomena tou bibliou kai saveBtn
        return new Scene(root, width, height);
                
	}
	
	private boolean validInput(TextField titleField, TextField authorField, TextField publisherField, TextField publicationYearField, TextField categoryField) { //methodos gia to elegxo egkyrothtas twn dedomenwn pou eisigage o xrhsths
        String title = titleField.getText();
        String author = authorField.getText();
		String publisher = publisherField.getText();
        String yearStr = publicationYearField.getText();
        String category = categoryField.getText();
        
        if (title.length() == 0 ||
        	author.length() == 0 ||
        	publisher.length() == 0 ||
        	yearStr.length() == 0 ||
        	category.length() == 0) {
        	
        	showAlert("You can't leave empty fields!");
        	return false; //an dhladh estw ena pedio den exei symplhrwthei apo ton xrhsth emfanizetai proeidopoihtiko mhnyma kai den ginetai apothikeusi
        }

       
        try { //try gia periptwsh sfalmatos
            int year = Integer.parseInt(yearStr); //metatroph text field se akeraio gia swsto elegxo egkyrothtas
            if (year < 1 || year > 2025) { //an to publishing year pou edwse o xrhsths einai prin to 1 h meta to 2025
                showAlert("Invalid publishing year."); //tote einai my egkyro
                return false; //my egkyro
            }
        } catch (NumberFormatException e) { //sfalma pou emfanizetai an o xrhsths eisagagei akyro etos, pou den apoteleitai apo mono akeraious
            showAlert("Publishing year must be a valid number.");
            return false; //my egkyro
        }

        return true; //an den brethei kapoio problhma me to isbn h me to etos ekdoshs tote epistrefetai true
    }
	
	private boolean showConfirmation(String message) { //methodos gia thn epibebaiwsh mias energeias
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO); //dhmiourgia pop-up window ebibebaiwtikou typou me sxetiko mhnyma kai koumpia nai kai oxi
        alert.showAndWait(); //emfanish tou pop-up kai anamonh epiloghs Yes/No apo ton xrhsth
        if (alert.getResult() == ButtonType.YES) { 
        	return true; //an epilejei nai tote epistrefetai true
        }
        else {
        	return false; //alliws me epilogh oxi epistrefetai false
        }
    }
	
	private void showAlert(String message) { //methodos gia thn dhmiourgia pop up mhnymatos
	    Alert alert = new Alert(Alert.AlertType.INFORMATION, message); 
	    alert.showAndWait();
	}
}




