import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise16_01 extends Application {
    private double messagePosition = 70; // Initial x-position of the message

    @Override
    public void start(Stage primaryStage) {
        // Create the Text object
        Text message = new Text("Programming is fun!");
        message.setFont(Font.font(20));
        message.setFill(Color.ORANGE); // Default color
        message.setX(messagePosition); // Set initial X position of the text
        message.setY(75); // Set initial Y position 

        // Create RadioButtons for color selection
        RadioButton redButton = new RadioButton("Red");
        RadioButton yellowButton = new RadioButton("Yellow");
        RadioButton blackButton = new RadioButton("Black");
        RadioButton orangeButton = new RadioButton("Orange");
        RadioButton greenButton = new RadioButton("Green");

        ToggleGroup colorGroup = new ToggleGroup();
        redButton.setToggleGroup(colorGroup);
        yellowButton.setToggleGroup(colorGroup);
        blackButton.setToggleGroup(colorGroup);
        orangeButton.setToggleGroup(colorGroup);
        greenButton.setToggleGroup(colorGroup);

        // Add functionality to change the color of the message
        redButton.setOnAction(e -> message.setFill(Color.RED));
        yellowButton.setOnAction(e -> message.setFill(Color.YELLOW));
        blackButton.setOnAction(e -> message.setFill(Color.BLACK));
        orangeButton.setOnAction(e -> message.setFill(Color.ORANGE));
        greenButton.setOnAction(e -> message.setFill(Color.GREEN));

        // Create Buttons to move the text
        Button leftButton = new Button("<");
        Button rightButton = new Button(">");        
        

        // Add functionality to move the message left and right
        leftButton.setOnAction(e -> {
            if (messagePosition > 0) { // Preent going off-screen to left
                messagePosition -= 10; // Move left
                message.setX(messagePosition);             
            }
        });

        rightButton.setOnAction(e -> {
            if (messagePosition < 140) { // Prevent going off-screen to right
                messagePosition += 10; // Move right
                message.setX(messagePosition);
            }
        });

        // Layouts for RadioButtons and Movement Buttons
        HBox radioBox = new HBox(10, redButton, yellowButton, blackButton, orangeButton, greenButton);
        HBox buttonBox = new HBox(10, leftButton, rightButton);        
        Pane centerPane = new Pane(message);
        
        BorderPane pane = new BorderPane();
        pane.setTop(radioBox);
        pane.setCenter(centerPane);
        pane.setBottom(buttonBox);

        // Adjust initial position of the message
        BorderPane.setAlignment(message, javafx.geometry.Pos.CENTER);

        // Create the Scene and display it
        Scene scene = new Scene(pane, 325, 200);      
       
        primaryStage.setTitle("Exercise16_01");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
