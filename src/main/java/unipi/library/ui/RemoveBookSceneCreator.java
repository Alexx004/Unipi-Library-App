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

public class RemoveBookSceneCreator extends SceneCreator {

	private Stage stage;
	private Runnable onCancel;
	private BookManager bookManager;

	public RemoveBookSceneCreator(Stage stage, double width, double height, Runnable onCancel, BookManager bookManager) { //constructor
		super(width, height);
		this.stage = stage;
		this.onCancel = onCancel;
		this.bookManager = bookManager;
	}
	
	
	@Override
	public Scene createScene() {
		Text RBText = new Text("Remove Book From Library"); //epikefalida
		RBText.setFont(Font.font(30));
		
		Label isbnLabel = new Label("Enter ISBN:"); 
        TextField isbnField = new TextField(); //pedio opou o xrhsths tha eisagei to isbn tou bibliou pou thelei na diagrapsei 
        CustomButton searchBtn = new CustomButton("Search", 120, 40); //koumpi pou jekinaei thn anazhthsh tou bibliou me bash to isbn
        CustomButton goBackBtn = new CustomButton("Go Back", 120, 40); //Koumpi gia thn epistrogh sto book management
        
        HBox searchBox = new HBox(10, isbnLabel, isbnField, searchBtn, goBackBtn); //mhnyma "Enter ISBN:", text field gia eisagwgh isbn kai koumpi anazhthshs sthn seira
        searchBox.setAlignment(Pos.CENTER);
        
        GridPane formBox = new GridPane(); //GridPane opou tha einai ta info tou bibliou pou thelei na diagrapsei o xrhsths efoson auto brethei
        formBox.setHgap(15);
        formBox.setVgap(10);
        formBox.setPadding(new Insets(10));
        formBox.setAlignment(Pos.CENTER);
        
        Label titleMsg = new Label("Title:");
        formBox.add(titleMsg, 0, 0);
        Label title = new Label();
        formBox.add(title, 1, 0);
        
        Label authorMsg = new Label("Author:");
        formBox.add(authorMsg, 0, 1);
        Label author = new Label();
        formBox.add(author, 1, 1);
        
        Label publisherMsg = new Label("Publisher:");
        formBox.add(publisherMsg, 0, 2);
        Label publisher = new Label();
        formBox.add(publisher, 1, 2);
        
        Label isbnMsg = new Label("ISBN:");
        formBox.add(isbnMsg, 0, 3);
        Label isbn = new Label();
        formBox.add(isbn, 1, 3);
        
        Label publicationYearMsg = new Label("PublicationYear:");
        formBox.add(publicationYearMsg, 0, 4);
        Label publicationYear = new Label();
        formBox.add(publicationYear, 1, 4);
        
        Label categoryMsg = new Label("Category");
        formBox.add(categoryMsg, 0, 5);
        Label category = new Label();
        formBox.add(category, 1, 5);
        
        Label availabilityMsg = new Label("Available:");
        formBox.add(availabilityMsg, 0, 6);
        CheckBox availabilityBox = new CheckBox();
        availabilityBox.setDisable(true);
        formBox.add(availabilityBox, 1, 6);
        
        formBox.setVisible(false); //to grid pane tha einai aorato mexri kai efoson brethei biblio me to isbn pou plhktrologei o xrhsths
        
        CustomButton removeBookBtn = new CustomButton("Remove Book", 120, 40); //Koumpi oloklirwshs diagrafhs bibliou
        removeBookBtn.setVisible(false); //arxika mh orato ston xrhsth
        
        
        searchBtn.setOnAction(e -> { //Anathesi ths leitourgias tou koumpiou anazhthshs
        	String isbnStr = isbnField.getText(); 
            Book book = bookManager.getBookByIsbn(isbnStr); //kalei thn methodo getBookByIsbn gia na brei to biblio pou antistoixei sto isbn 
            
            if (book == null) { //An epistrafei null, dhladh den yparxei to biblio sto database
            	showAlert("ISBN does not match any book in our database"); //Eidiko mhnyma
            }
            else { 
            	//arxikopoihsh olwn twn textfields me tis times pou antistoixoun sto biblio me to isbn pou eishgage o xrhsths
            	title.setText(book.getTitle());
            	author.setText(book.getAuthor());
            	publisher.setText(book.getPublisher());
            	isbn.setText(isbnField.getText());
            	publicationYear.setText(String.valueOf(book.getPublicationYear()));
            	category.setText(book.getCategory());
            	availabilityBox.setSelected(book.isAvailable());

            	//emfanish ths formas kai tou remove Book Button
            	formBox.setVisible(true);
            	removeBookBtn.setVisible(true);
            }
        });
        
        
        removeBookBtn.setOnAction(e -> { //Anathesi leitourgias tou koumpiou diagrafhs bibliou
        	boolean confirm = showConfirmation("Are you sure you wish to remove this book from our library?"); //mhnyma gia epibebaiwsh
        	if (confirm) { //an epibebaiwsei o xrhsths
        		String isbnStr = isbnField.getText(); 
                Book book = bookManager.getBookByIsbn(isbnStr); //kalei thn methodo getBookByIsbn gia na brei to biblio pou antistoixei sto isbn
        		bookManager.removeBook(book); //methodos ths bookManager.java gia diagrafh bibliou
        		showAlert("Book has been successfully removed!");
        		onCancel.run(); //epistrofh sto book management
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
        root.getChildren().addAll(RBText, searchBox, formBox, removeBookBtn); //stoibagmena to mhnyma "Remove Book", me to searchBox, ta dedomena tou bibliou kai removeBookBtn
        return new Scene(root, width, height);
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
