package unipi.library.ui;

import javafx.stage.Stage;
import unipi.library.classes.Book;
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

public class NewBookSceneCreator extends SceneCreator {

	private Stage stage;
	private Runnable onCancel;
	private BookManager bookManager;

	public NewBookSceneCreator(Stage stage, double width, double height, Runnable onCancel, BookManager bookManager) { //constructor
		super(width, height);
		this.stage = stage;
		this.onCancel = onCancel;
		this.bookManager = bookManager;
	}
	
	
	@Override
	public Scene createScene() {
		Text NBText = new Text("Add New Book");
        NBText.setFont(Font.font(24));

        TextField titleField = new TextField(); //pedio eisagwghs titlou bibliou
        TextField authorField = new TextField(); //pedio eisagwghs syggrafea bibiliou
        TextField isbnField = new TextField(); //pedio eisagwghs isbn bibliou
        TextField publisherField = new TextField(); //pedio eisagwghs ekdoth bibiliou
        TextField yearField = new TextField(); //pedio eisagwghs etous ekdoshs bibliou
        TextField categoryField = new TextField(); //pedio eisagwghs kathgorias bibliou
        CheckBox availabilityBox = new CheckBox(); //koutaki gia checkarisma an einai diathesimo h oxi to neo biblio
        
        GridPane infoBoard = new GridPane(); //neo gridpane layout gia ta labels kai ta fields
        infoBoard.setHgap(15);
        infoBoard.setVgap(10);
        infoBoard.setAlignment(Pos.CENTER);
        
        //dhmiourgia labels kai prosthiki labels kai fields ston gridpane pinaka
        infoBoard.add(new Label("Title:"), 0, 0);        
        infoBoard.add(titleField, 1, 0);
        infoBoard.add(new Label("Author:"), 0, 1);       
        infoBoard.add(authorField, 1, 1);
        infoBoard.add(new Label("ISBN:"), 0, 2);         
        infoBoard.add(isbnField, 1, 2);
        infoBoard.add(new Label("Publisher:"), 0, 3);    
        infoBoard.add(publisherField, 1, 3);
        infoBoard.add(new Label("Publishing Year:"), 0, 4); 
        infoBoard.add(yearField, 1, 4);
        infoBoard.add(new Label("Category:"), 0, 5);     
        infoBoard.add(categoryField, 1, 5);
        infoBoard.add(new Label("Available:"), 0, 6); 
        infoBoard.add(availabilityBox, 1, 6);
        
	    CustomButton saveBtn = new CustomButton("Save", 120, 40); //Koumpi gia apothikeush bibliou
	    CustomButton cancelBtn = new CustomButton("Cancel", 120, 40); //Koumpi gia akyrwsh diadikasias
        
        saveBtn.setOnAction(e -> { //anathesi leitourgias tou save button
            if (validInput(titleField, authorField, isbnField, publisherField, yearField, categoryField)) { //an den yparxei kapoio problhma egkyrothtas me ta dedomena pou eishgage o xrhsths
                boolean confirm = showConfirmation("Are you sure you want to save this book?"); //mhnyma epibebaiwshs apothikeushs
                if (confirm) { //an confirm == true
                	
                	Book newBook = bookManager.createBook(
                		    isbnField.getText(),
                		    titleField.getText(),
                		    authorField.getText(),
                		    publisherField.getText(),
                		    Integer.parseInt(yearField.getText()), 
                		    categoryField.getText(),
                		    availabilityBox.isSelected()
                		);
                	bookManager.addBook(newBook);
                    
                	showAlert("Book saved successfully!");
                    onCancel.run(); // epistrofh
                }
            }
        });

        cancelBtn.setOnAction(e -> { //Anathesi leitourgias tou cancel button
            boolean confirm = showConfirmation("Are you sure you want to cancel the process?");
            if (confirm) { //an confirm == true
                onCancel.run(); // epistrofh
            }
        });
	
	    
	    HBox buttons = new HBox(20, saveBtn, cancelBtn); //ta save kai cancel buttons se seira
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(20, NBText, infoBoard, buttons); 
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        return new Scene(root, width, height); //to teliko layout me thn epikefalida sthn koryfh, meta ton pinaka me ta dedomena kai katw ta save kai cancel
        
        
	}
	
	private boolean validInput(TextField titleField, TextField authorField, TextField isbnField, TextField publisherField, TextField yearField, TextField categoryField) { //methodos gia to elegxo egkyrothtas twn dedomenwn pou eisigage o xrhsths
        String title = titleField.getText();
        String author = authorField.getText();
		String isbn = isbnField.getText(); 
		String publisher = publisherField.getText();
        String yearStr = yearField.getText();
        String category = categoryField.getText();
        
        if (title.length() == 0 ||
        	author.length() == 0 ||
        	isbn.length() == 0 ||
        	publisher.length() == 0 ||
        	yearStr.length() == 0 ||
        	category.length() == 0) {
        	
        	showAlert("You can't leave empty fields!");
        	return false; //an dhladh estw ena pedio den exei symplhrwthei apo ton xrhsth emfanizetai proeidopoihtiko mhnyma kai den ginetai apothikeusi
        }

        if (isbn.length() != 13) { //an to isbn den einai akribws 13 pshfia
            showAlert("ISBN must be exactly 13 characters long.");
            return false; //my egkyro
        }
        
        for(Book book : bookManager.getBooks()) {
        	if (book.getIsbn().equals(isbn)) {
        		showAlert("A book with this ISBN already exists!"); //anazhtei sto array list me ta biblia ths bibliothikis kai an brethei kapoio 
        		return false; 										//me to idio isbn tote den to dexetai kai emfanizetai proeidopoihtiko mhnyma
        	}
        }
        
        if (!isbn.chars().allMatch(Character::isDigit)) { //elegxos gia to an ola ta pshfia tou isbn einai akeraioi. An den einai, ikanopoieitai h synthiki
            showAlert("ISBN must only contain digits.");
            return false; //my egkyro
        }

        try { //try gia periptwah sfalmatos
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


