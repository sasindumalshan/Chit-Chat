package controller;

import javafx.scene.input.MouseEvent;

public class EmojiController {

    public void img1Clicked(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(0x1F600));
        DashboardController.getInstance().  txtMessage.setText(emoji);
        //emojiPane.setVisible(false);
    }

    public void img2Clicked(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(0x1F60D));
        DashboardController.getInstance(). txtMessage.setText(emoji);
      //  emojiPane.setVisible(false);
    }

    public void img3Clicked(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(0x1F602));
        DashboardController.getInstance(). txtMessage.setText(emoji);
     //   emojiPane.setVisible(false);
    }

    public void img4Clicked(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(0x1F44D));
        DashboardController.getInstance().  txtMessage.setText(emoji);
       // emojiPane.setVisible(false);
    }

    public void img5Clicked(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(0x2764));
        DashboardController.getInstance().  txtMessage.setText(emoji);
      //  emojiPane.setVisible(false);
    }

    public void img6Clicked(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(0x1F609));
        DashboardController.getInstance().  txtMessage.setText(emoji);
     //   emojiPane.setVisible(false);
    }

    public void img7Clicked(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(0x1F622));
        DashboardController.getInstance().  txtMessage.setText(emoji);
      //  emojiPane.setVisible(false);
    }

    public void img8Clicked(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(0x1F644));
        DashboardController.getInstance().  txtMessage.setText(emoji);
      //  emojiPane.setVisible(false);
    }

    public void img9Clicked(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(0x1F64F));
        DashboardController.getInstance().  txtMessage.setText(emoji);
    //    emojiPane.setVisible(false);
    }

    public void img12Clicked(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(0x1F60E));
        DashboardController.getInstance().txtMessage.setText(emoji);
    //    emojiPane.setVisible(false);
}


    public void img10Clicked(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(0x1F60E));
        DashboardController.getInstance().txtMessage.setText(emoji);
    }

    public void img11Clicked(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(0x1F60E));
        DashboardController.getInstance().txtMessage.setText(emoji);
    }
}
