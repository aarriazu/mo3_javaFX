package com.example.m03_javafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class Controller {

    @FXML
    private Button upgradeNormalClickButton;

    @FXML
    private Button upgradeSuperClickButton;

    @FXML
    private Button addNormalAutoclickButton;

    @FXML
    private Button addSuperAutoclickButton;

    @FXML
    private Label pointsLabel;

    @FXML
    private Label clickLabel;

    @FXML
    private Label autoclickLabel;

    @FXML
    private Label clicksPerSecondLabel;

    private int points = 0;
    private int clickAmount = 1;
    private int autoclickers = 0;
    private int clickCount = 0;
    private int clicksPerSecond = 0;

    private final Timeline autoclickTimeline = new Timeline( new KeyFrame(Duration.seconds(1), event -> autoclick()));
    private final Timeline clicksPerSecondTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClicksPerSecond()));

    @FXML
    public void initialize() {
        upgradeNormalClickButton.setVisible(false);
        upgradeSuperClickButton.setVisible(false);
        addNormalAutoclickButton.setVisible(false);
        addSuperAutoclickButton.setVisible(false);

        autoclickTimeline.setCycleCount(Timeline.INDEFINITE);
        autoclickTimeline.play();

        clicksPerSecondTimeline.setCycleCount(Timeline.INDEFINITE);
        clicksPerSecondTimeline.play();

        checkButtonVisibility();
        pointsLabel.setText(String.valueOf(points));
        clickLabel.setText(String.valueOf(clickAmount));
        autoclickLabel.setText(String.valueOf(autoclickers));
    }

    @FXML
    protected void addPoint() {
        points += clickAmount;
        clickCount++;
        pointsLabel.setText(String.valueOf(points));
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
        pointsLabel.setText(String.valueOf(points));
        clickLabel.setText(String.valueOf(clickAmount));
    }

    @FXML
    protected void addAutoclick(int amount, int cost) {
        if (points >= cost) {
            points -= cost;
            autoclickers += amount;
        }
        pointsLabel.setText(String.valueOf(points));
        autoclickLabel.setText(String.valueOf(autoclickers));
    }

    @FXML
    protected void autoclick() {
        points += autoclickers;
        pointsLabel.setText(String.valueOf(points));
        checkButtonVisibility();
    }

    private void updateClicksPerSecond() {
        clicksPerSecond = clickCount;
        clickCount = 0;
        clicksPerSecondLabel.setText(String.valueOf(clicksPerSecond));
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