package com.example.m03_javafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class HelloController {


    //AÑADIR CONTADOR DE CLICKS POR MINUTO

    @FXML
    private Button upgradeNormalClickButton;

    @FXML
    private Button upgradeSuperClickButton;

    @FXML
    private Button addNormalAutoclickButton;

    @FXML
    private Button addSuperAutoclickButton;

    @FXML
    private Label pointsText;

    @FXML
    private int points = 0;

    @FXML
    private int clickAmount = 1;

    @FXML
    private int autoclickers = 0;

    @FXML
    private final Timeline autoclickTimeline = new Timeline( new KeyFrame(Duration.seconds(1), event -> autoclick()));

    @FXML
    public void initialize() {
        upgradeNormalClickButton.setVisible(false);
        upgradeSuperClickButton.setVisible(false);
        addNormalAutoclickButton.setVisible(false);
        addSuperAutoclickButton.setVisible(false);

        autoclickTimeline.setCycleCount(Timeline.INDEFINITE);
        autoclickTimeline.play();

        checkButtonVisibility();
        pointsText.setText(String.valueOf(points));
    }

    @FXML
    protected void addPoint() {
        points += clickAmount;
        pointsText.setText(String.valueOf(points));
        checkButtonVisibility();
    }

    @FXML
    protected void upgradeNormalClick() {
        upgradeClick(1,5);
    }

    @FXML
    protected void upgradeSuperClick() {
        upgradeClick(100,500);
    }

    @FXML
    protected void addNormalAutoclick() {
        addAutoclick(1,50);
    }

    @FXML
    protected void addSuperAutoclick() {
        addAutoclick(50,1500);
    }

    @FXML
    protected void upgradeClick(int amount, int cost) {
        if (points >= cost) {
            points -= cost;
            clickAmount += amount;
        }
        pointsText.setText(String.valueOf(points));
    }

    @FXML
    protected void addAutoclick(int amount, int cost) {
        if (points >= cost) {
            points -= cost;
            autoclickers += amount;
        }
        pointsText.setText(String.valueOf(points));
    }

    @FXML
    protected void autoclick() {
        points += autoclickers;
        pointsText.setText(String.valueOf(points));
        checkButtonVisibility();
    }

    private void checkButtonVisibility() {
        if (points >= 5) {
            upgradeNormalClickButton.setVisible(true);
        }
        if (points >= 50) {
            addNormalAutoclickButton.setVisible(true);
        }
        if (points >= 500) {
            upgradeSuperClickButton.setVisible(true);
        }
        if (points >= 1500) {
            addSuperAutoclickButton.setVisible(true);
        }
    }
}