package ch.bbcag.gui.shop;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;


public abstract class Shop extends VBox {
    public final ImageView ivItem = new ImageView();
    private final VBox vbxItemStats = new VBox();
    private final Button btnBuy = new Button();

    public Shop(){
        this.setSpacing(10);
        HBox hbxItem = new HBox();
        vbxItemStats.setMinWidth(150);
        hbxItem.getChildren().addAll(ivItem, vbxItemStats);

        HBox hbxButtons = getButtons();

        this.getChildren().addAll(hbxItem, hbxButtons);
    }
    protected HBox getButtons() {
        HBox hbxChooser = new HBox();
        Button btnLeft = new Button();
        btnLeft.setText("<-");
        //btnLeft.setStyle(
        //        "-fx-background-image: url('/graphics/small_buttons.png'); " +
        //        "-fx-background-color: transparent;" +
        //        "-fx-text-fill: #9dd3cf"
        //);
        btnLeft.setAlignment(Pos.BASELINE_LEFT);
        btnLeft.setOnAction(e -> {
            btnPrevious();
            update();
        });

        btnBuy.setMaxWidth(Double.MAX_VALUE);


        Button btnShipRight = new Button();
        btnShipRight.setText("->");
        btnShipRight.setAlignment(Pos.BASELINE_RIGHT);
        btnShipRight.setOnAction(e -> {
            btnNext();
            update();
        });
        //btnShipRight.setStyle(
        //        "-fx-background-image: url('/graphics/small_buttons.png'); " +
        //                "-fx-background-color: transparent;" +
        //                "-fx-text-fill: #9dd3cf"
        //);
        //btnBuy.setStyle(
        //        "-fx-background-image: url('/graphics/buttons.png'); " +
        //                "-fx-background-color: transparent;" +
        //                "-fx-text-fill: #9dd3cf"
        //);

        hbxChooser.getChildren().addAll(btnLeft, btnBuy, btnShipRight);
        hbxChooser.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(btnBuy, Priority.ALWAYS);

        return hbxChooser;
    }
    protected abstract void btnNext();
    protected abstract void btnPrevious();
    protected void update() {
        ivItem.setImage(getImage());

        vbxItemStats.getChildren().clear();
        for (String txt : getStats()) {
            vbxItemStats.getChildren().add(new Label(txt));
        }



        if (isBought()) {
            btnBuy.setText("Already Bought");
            btnBuy.setDisable(true);
        } else {
            btnBuy.setText(getCost() + " Credits");
            btnBuy.setDisable(false);
            btnBuy.setOnMouseClicked(event -> {
                try {
                    buyItem();
                    btnBuy.setText("Already Bought");
                    btnBuy.setDisable(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        }
    }
    protected abstract Image getImage();
    protected abstract List<String> getStats();
    protected abstract int getCost();
    protected abstract boolean isBought();
    protected abstract void buyItem() throws Exception;

}
