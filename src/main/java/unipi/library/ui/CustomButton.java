package unipi.library.ui;

import javafx.scene.control.Button;

public class CustomButton extends Button {
  
	//Ypoklash ths klashs Button oste sthn dhmiourgia neou button na kathorizetai to mhkos kai to ypsos tou
    public CustomButton(String text, double width, double height) {
        super(text);
        this.setPrefSize(width, height);
    }
}
