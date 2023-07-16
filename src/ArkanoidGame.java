import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Label;

public class ArkanoidGame extends Application {
    private int brickRows;
    private int brickColumns;
    private int currentScore;
    private int highestScore;
    private int currentLevel;
    private int ballX;
    private int paddleX;

    private static final int BRICK_WIDTH = 50;
    private static final int BRICK_HEIGHT = 20;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 10;
    private static final int BALL_RADIUS = 10;
    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Arkanoid Game");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the number of brick rows:");
        brickRows = Integer.parseInt(dialog.showAndWait().orElse("0"));

        dialog.setContentText("Enter the number of brick columns:");
        brickColumns = Integer.parseInt(dialog.showAndWait().orElse("0"));

        dialog.setContentText("Enter the current score:");
        currentScore = Integer.parseInt(dialog.showAndWait().orElse("0"));

        dialog.setContentText("Enter the highest score:");
        highestScore = Integer.parseInt(dialog.showAndWait().orElse("0"));
        if (currentScore > highestScore) {
            highestScore = currentScore;
        }

        dialog.setContentText("Enter the current level:");
        currentLevel = Integer.parseInt(dialog.showAndWait().orElse("0"));

        dialog.setContentText("Enter the ball's x position:");
        ballX = Integer.parseInt(dialog.showAndWait().orElse("0"));

        dialog.setContentText("Enter the paddle's x position:");
        paddleX = Integer.parseInt(dialog.showAndWait().orElse("0"));

        Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawGame(gc);

        VBox scoreBox = new VBox();
        scoreBox.setStyle("-fx-background-color: #000000; -fx-padding: 10;");
        scoreBox.getChildren().addAll(
                createScoreLabel("Score: " + currentScore),
                createScoreLabel("Highest Score: " + highestScore),
                createScoreLabel("Level: " + currentLevel)
        );

        BorderPane root = new BorderPane();
        root.setLeft(canvas);
        root.setRight(scoreBox);

        // Load and set the background image
        Image backgroundImage = new Image("file:/C:/Volkan/ArkanoidGame/src/resources/images/background.jpg");
        root.setStyle("-fx-background-image: url('" + backgroundImage.getUrl() + "'); -fx-background-size: cover;");

        Scene scene = new Scene(root);

        primaryStage.setTitle("Arkanoid Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawGame(GraphicsContext gc) {
        // Clear the canvas
        gc.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        // Draw bricks
        double brickY = 50;
        for (int row = 0; row < brickRows; row++) {
            double brickX = 50;
            for (int col = 0; col < brickColumns; col++) {
                gc.setFill(Color.BLUE);
                gc.fillRect(brickX, brickY, BRICK_WIDTH, BRICK_HEIGHT);
                brickX += BRICK_WIDTH + 10;
            }
            brickY += BRICK_HEIGHT + 10;
        }

        // Draw ball
        gc.setFill(Color.RED);
        gc.fillOval(ballX, GAME_HEIGHT - 50 - BALL_RADIUS, BALL_RADIUS, BALL_RADIUS);

        // Draw paddle
        gc.setFill(Color.YELLOW);
        gc.fillRect(paddleX, GAME_HEIGHT - 20, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    private Label createScoreLabel(String text) {
        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        label.setStyle("-fx-font-size: 14;");
        return label;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
