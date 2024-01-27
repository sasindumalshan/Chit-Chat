package sample;

import javafx.scene.text.Text;

public class BarController {
    public Text message;

    public void setData(String message) {
        this.message.setText(message);
    }
}
