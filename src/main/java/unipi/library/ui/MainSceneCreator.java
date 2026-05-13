package unipi.library.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainSceneCreator {

    private final double width;
    private final double height;

    private final Runnable onBook;
    private final Runnable onStudent;
    private final Runnable onLoan;
    private final Runnable onPayment;

    public MainSceneCreator(double width, double height, //constructor 
                            Runnable onBook,
                            Runnable onStudent,
                            Runnable onLoan,
                            Runnable onPayment) {
        this.width = width;
        this.height = height;
        this.onBook = onBook;
        this.onStudent = onStudent;
        this.onLoan = onLoan;
        this.onPayment = onPayment;
    }

    public Scene createScene() {
        Text welcomeText = new Text("Welcome to the official\n   Unipi Library App!"); //epikefalida
        welcomeText.setFont(Font.font(30));
 
        //dhmiourgia twn 4 leitourgiwn tou main scene
        CustomButton bookBtn = new CustomButton("Book Management", 150, 50);
        CustomButton studentBtn = new CustomButton("Student Management", 150, 50);
        CustomButton loanBtn = new CustomButton("Loan Management", 150, 50);
        CustomButton paymentBtn = new CustomButton("Payment Management", 150, 50);

        // Anathesi leitourgiwn gia ta 4 koumpia
        bookBtn.setOnAction(e -> onBook.run());
        studentBtn.setOnAction(e -> onStudent.run());
        loanBtn.setOnAction(e -> onLoan.run());
        paymentBtn.setOnAction(e -> onPayment.run());


        HBox buttons1 = new HBox(100, bookBtn, studentBtn);
        buttons1.setAlignment(Pos.CENTER);
        HBox buttons2 = new HBox(100, loanBtn, paymentBtn);
        buttons2.setAlignment(Pos.CENTER);
        VBox root = new VBox(60, welcomeText, buttons1, buttons2);
        root.setAlignment(Pos.TOP_CENTER);
        
        return new Scene(root, width, height);
    }
}
