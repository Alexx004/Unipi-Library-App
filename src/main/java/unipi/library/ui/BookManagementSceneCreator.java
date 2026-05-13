package unipi.library.ui;

import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BookManagementSceneCreator extends SceneCreator {

	private Stage stage;
    private Runnable onReturnToMainMenu;
	private BookManager bookManager;

    public BookManagementSceneCreator(Stage stage, double width, double height, Runnable onReturnToMainMenu, BookManager bookManager) { //instructor ths book management scene
        super(width, height);
        this.stage = stage;
        this.onReturnToMainMenu = onReturnToMainMenu;
        this.bookManager = bookManager;
    }

    @Override
    public Scene createScene() {
        Text BMText = new Text("Book Management"); //epikefalida
        BMText.setFont(Font.font(30));

        //Dhmiourgia twn 4 buttons pou odhgoun stis diaforetikes energeies ths book management. Ola me width=150 kai height=50
        CustomButton newBookBtn = new CustomButton("New Book", 150, 50); 
        CustomButton editBookBtn = new CustomButton("Edit Book Info", 150, 50);
        CustomButton removeBookBtn = new CustomButton("Remove Book", 150, 50);
        CustomButton backBtn = new CustomButton("Return to Main Menu", 150, 50);
        backBtn.setOnAction(e -> onReturnToMainMenu.run()); //Anathesi ths leitourgias tou back button
        
        newBookBtn.setOnAction(e -> { //anathesi ths leitourgias tou new book button
            Scene newBookScene = new NewBookSceneCreator(stage, 850, 400, () -> stage.setScene(this.createScene()), bookManager).createScene(); //klhsh skhnhs gia dhmiourgia neou bibliou
            stage.setScene(newBookScene);
        });
        
        editBookBtn.setOnAction(e -> { //anathesi ths leitourgias tou edit book button
            Scene editBookScene = new EditBookSceneCreator(stage, 850, 400, () -> stage.setScene(this.createScene()), bookManager).createScene(); //klhsh skhnhs gia epejergasia dedomenwn bibliou
            stage.setScene(editBookScene);
        });
        
        removeBookBtn.setOnAction(e -> { //anathesi ths leitourgias tou remove book button
            Scene removeBookScene = new RemoveBookSceneCreator(stage, 850, 400, () -> stage.setScene(this.createScene()), bookManager).createScene(); //klhsh skhnhs gia diagrafh bibliou
            stage.setScene(removeBookScene);
        });
        

        HBox buttons = new HBox(20, newBookBtn, editBookBtn, removeBookBtn); //dhmiourgias mias seiras twn koumpiwn-leitourgiwn
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(100, BMText, buttons, backBtn); //dhmiourgias mias stoivas me epikefalida, leitourgies kai back button
        root.setAlignment(Pos.TOP_CENTER);

        return new Scene(root, width, height);
    }
}
