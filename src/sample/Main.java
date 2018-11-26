package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application implements EventHandler<ActionEvent> {
    private GridPane gridPane = new GridPane();

    private void addButtons(int squareSize, int buttonSize){
        for(int i = 0; i < squareSize; i++){
            for(int j = 0; j < squareSize; j++){
                Button button = new Button("");
                this.gridPane.add(button, j, i);
                button.setPrefSize(buttonSize, buttonSize);
                button.setId("" + j + i);
                button.setOnAction(this);
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //

        addButtons(3, 100);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(new Scene(this.gridPane, 300, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private int counter = 0;
    private ArrayList<String> oPositions = new ArrayList<String>();
    private ArrayList<String> xPositions = new ArrayList<String>();

    @Override
    public void handle(ActionEvent event){
        Button button = (Button) event.getSource();

        if (counter % 2 == 0) {
            button.setText("O");
            this.oPositions.add(button.getId());
            if(this.oPositions.size() >= 3 && didWin(oPositions) != null){
                colorButtons(didWin(oPositions));
                this.gridPane.setDisable(true);
            }
        }
        else{
            button.setText("X");
            this.xPositions.add(button.getId());
            if(this.xPositions.size() >= 3 && didWin(xPositions) != null){
                colorButtons(didWin(xPositions));
                this.gridPane.setDisable(true);
            }
        }
        button.setDisable(true);

        counter++;
    }

    private String[] didWin(ArrayList<String> arr){
        //definition of winning combinations - in String "01" 0 is number of column, 1 is number of row
        String[] won1 = {"00", "10", "20"};
        String[] won2 = {"01", "11", "21"};
        String[] won3 = {"02", "12", "22"};
        String[] won4 = {"00", "01", "02"};
        String[] won5 = {"10", "11", "12"};
        String[] won6 = {"20", "21", "22"};
        String[] won7 = {"00", "11", "22"};
        String[] won8 = {"20", "11", "02"};

        if(checkIfArrayContainsCombination(arr, won1)){
            return won1;
        }
        if(checkIfArrayContainsCombination(arr, won2)){
            return won2;
        }
        if(checkIfArrayContainsCombination(arr, won3)){
            return won3;
        }
        if(checkIfArrayContainsCombination(arr, won4)){
            return won4;
        }
        if(checkIfArrayContainsCombination(arr, won5)){
            return won5;
        }
        if(checkIfArrayContainsCombination(arr, won6)){
            return won6;
        }
        if(checkIfArrayContainsCombination(arr, won7)){
            return won7;
        }
        if(checkIfArrayContainsCombination(arr, won8)){
            return won8;
        }
        return null;
    }

    private boolean checkIfArrayContainsCombination(ArrayList<String> arr, String[] combination){
        if(arr.contains(combination[0]) && arr.contains(combination[1]) && arr.contains(combination[2])){
            return true;
        }
        else{
            return false;
        }
    }

    private void colorButtons(String[] combination){
        int size = this.gridPane.getChildren().size();

        for(int i = 0; i < size; i++){
            if(this.gridPane.getChildren().get(i).getId().equals(combination[0]) ||
                    this.gridPane.getChildren().get(i).getId().equals(combination[1]) ||
                    this.gridPane.getChildren().get(i).getId().equals(combination[2])){
                this.gridPane.getChildren().get(i).setStyle("-fx-font-weight: bold");
                this.gridPane.getChildren().get(i).setStyle("-fx-base: green;");
            }
        }
    }
}
