import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.security.cert.PolicyNode;
import java.util.Random;

public class Main extends Application {

    private String playerName;
    private int correctAnswers = 0;
    private int totalQuestions = 10;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Math Learning App");

        GridPane startGrid = new GridPane();
        startGrid.setPadding(new Insets(10, 10, 10, 10));
        startGrid.setVgap(8);
        startGrid.setHgap(10);

        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter your name");
        GridPane.setConstraints(nameInput, 0, 0);

        Button startButton = new Button("Start Game");
        GridPane.setConstraints(startButton, 1, 0);

        startButton.setOnAction(e -> {
            playerName = nameInput.getText();
            if (playerName.isEmpty()) {
                showAlert("Error", "Please enter your name.");
            } else {
                showGameWindow(primaryStage);
            }
        });

        startGrid.getChildren().addAll(nameInput, startButton);

        Scene startScene = new Scene(startGrid, 300, 100);

        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    private void showGameWindow(Stage primaryStage) {
        GridPane gameGrid = new GridPane();
        gameGrid.setPadding(new Insets(10, 10, 10, 10));
        gameGrid.setVgap(8);
        gameGrid.setHgap(10);

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int num1 = random.nextInt(100);
            int num2 = random.nextInt(100);
            boolean isSubtraction = random.nextBoolean();

            if (isSubtraction) {
                if (num1 < num2) {
                    int temp = num1;
                    num1 = num2;
                    num2 = temp;
                }
                Label problemLabel = new Label(num1 + " - " + num2 + " = ");
                GridPane.setConstraints(problemLabel, 0, i);
                TextField answerField = new TextField();
                GridPane.setConstraints(answerField, 1, i);
                gameGrid.getChildren().addAll(problemLabel, answerField);
            } else {
                int sum = num1 + num2;
                Label problemLabel = new Label(num1 + " + " + num2 + " = ");
                GridPane.setConstraints(problemLabel, 0, i);
                TextField answerField = new TextField();
                GridPane.setConstraints(answerField, 1, i);
                gameGrid.getChildren().addAll(problemLabel, answerField);
            }
        }

        for (int i = 0; i < 5; i++) {
            int num1 = random.nextInt(10);
            int num2 = random.nextInt(10);
            int product = num1 * num2;

            Label problemLabel = new Label(num1 + " * " + num2 + " = ");
            GridPane.setConstraints(problemLabel, 0, i + 5);
            TextField answerField = new TextField();
            GridPane.setConstraints(answerField, 1, i + 5);
            gameGrid.getChildren().addAll(problemLabel, answerField);
        }

        Label timerLabel = new Label("Time remaining: 60 seconds");
        GridPane.setConstraints(timerLabel, 0, 10);

        Button submitButton = new Button("Submit");
        GridPane.setConstraints(submitButton, 0, 11);
        submitButton.setOnAction(e -> calculateScore());

        Button returnButton = new Button("Return to Start");
        GridPane.setConstraints(returnButton, 1, 11);
        returnButton.setOnAction(e -> start(primaryStage));

        gameGrid.getChildren().addAll(timerLabel, submitButton, returnButton);

        Scene gameScene = new Scene(gameGrid, 400, 300);

        primaryStage.setScene(gameScene);

        Thread timerThread = new Thread(() -> {
            for (int i = 60; i >= 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                final int time = i;
                Platform.runLater(() -> timerLabel.setText("Time remaining: " + time + " seconds"));
            }
            Platform.runLater(this::calculateScore);
        });
        timerThread.start();
    }

    private void calculateScore() {
        int correctAnswers = 0;



        double percentage = (double) correctAnswers / totalQuestions * 100;
        showAlert("Results", "Congratulations, " + playerName + "!\nYour score: " + correctAnswers + "/" + totalQuestions +
                " (" + String.format("%.2f", percentage) + "%)");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
