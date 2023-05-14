package ch.bbcag.gui;

import ch.bbcag.common.Global;
import ch.bbcag.common.Navigator;
import ch.bbcag.common.SceneType;

import ch.bbcag.game.objects.upgrades.armors.ArmorType;
import ch.bbcag.game.objects.upgrades.motors.MotorType;
import ch.bbcag.game.objects.upgrades.ships.ShipType;
import ch.bbcag.game.objects.upgrades.weapons.WeaponType;
import ch.bbcag.game.scenes.NavigatableScene;
import ch.bbcag.gui.shop.ArmorShop;
import ch.bbcag.gui.shop.MotorShop;
import ch.bbcag.gui.shop.ShipShop;
import ch.bbcag.gui.shop.WeaponShop;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Pair;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeScreen extends NavigatableScene {
    private static final VBox rootNode = new VBox();

    public HomeScreen(Navigator navigator) {
        super(rootNode);
        //Stats
        Label lblCredits = new Label("Credits: " + Global.playerCredits);
        lblCredits.setFont(new Font("Arial", 20));
        lblCredits.setId("credits");

        //Shop
        List<WeaponType> weaponTypes = new ArrayList<>(Arrays.asList(WeaponType.HANDHELD_BLASTER, WeaponType.BLASTER,
                WeaponType.AUTO_BLASTER, WeaponType.CANNON, WeaponType.ION_CANNON, WeaponType.MISSILE_GUN));

        WeaponShop weaponShop = new WeaponShop(weaponTypes);

        List<ArmorType> armorTypes = new ArrayList<>(Arrays.asList(ArmorType.RECYCLED_MATERIAL, ArmorType.STEEL,
                ArmorType.HARDENED_STEEL, ArmorType.CHROME, ArmorType.BESKAR));
        ArmorShop armorShop = new ArmorShop(armorTypes);

        List<ShipType> shipTypes = new ArrayList<>(Arrays.asList(ShipType.NABOO_CRUISER, ShipType.NABOO_JTYPE,
                ShipType.NABOO_FIGHTER, ShipType.JEDI_INTERCEPTOR, ShipType.JEDI_STARFIGHTER, ShipType.VENATOR));
        ShipShop shipShop = new ShipShop(shipTypes);

        List<MotorType> motorTypes = new ArrayList<>(Arrays.asList(MotorType.SPEEDER, MotorType.TIE,
                MotorType.INTERCEPTOR,MotorType.NABOO, MotorType.VENATOR));
        MotorShop motorShop = new MotorShop(motorTypes);

        VBox vbxInventory = new VBox();
        vbxInventory.getChildren().addAll(weaponShop , armorShop, motorShop, shipShop);

        ScrollPane spInventory = new ScrollPane();
        spInventory.setContent(vbxInventory);
        spInventory.setMinWidth(300);
        spInventory.setMinHeight(400);
        spInventory.setPadding(new Insets(10));


        VBox vbxLevelChooser = new VBox();
        vbxLevelChooser.setSpacing(10);

        //Levels
        List<Pair<String, SceneType>> levels = new ArrayList<>();
        levels.add(new Pair<>("BATTLE OF CORUSCANT", SceneType.BATTLE_OF_CORUSCANT));
        levels.add(new Pair<>("BATTLE OF YAVIN", SceneType.BATTLE_OF_YAVIN));

        for (Pair<String, SceneType> level : levels) {
            Button button = new Button(level.getKey());
            button.setOnAction((e) -> {
                Global.selectedWeapon = weaponShop.getCurrentWeapon();
                Global.selectedArmor = armorShop.getCurrentArmor();
                Global.selectedMotor = motorShop.getCurrentMotor();
                Global.selectedShip = shipShop.getCurrentShip();
                navigator.navigateTo(level.getValue());
                navigator.getCurentScene().reStart();
            });
            button.setMinWidth(150);
            button.setMinHeight(30);
            //button.setStyle("-fx-background-color: transparent");
            //button.setStyle(
            //        "-fx-background-image: url('/graphics/buttons.png'); " +
            //        "-fx-background-color: transparent;" +
            //        "-fx-text-fill: #9dd3cf"
            //);
            vbxLevelChooser.getChildren().add(button);
        }


        HBox body = new HBox();
        body.getChildren().addAll(vbxLevelChooser, spInventory);
        body.setSpacing(20);
        rootNode.getChildren().addAll(lblCredits, body);
        rootNode.setSpacing(20);
        rootNode.setPadding(new Insets(20));

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                lblCredits.setText("Credits: " + Global.playerCredits);
            }
        }.start();

    }

    @Override
    public void reStart() {

    }
}