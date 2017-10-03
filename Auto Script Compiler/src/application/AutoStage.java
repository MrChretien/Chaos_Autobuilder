package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class AutoStage{
	
	ObservableList<String> states = 
		    FXCollections.observableArrayList(
		        "forward",
		        "right",
		        "shoot",
		        "wait",
		        "retract",
		        "follow",
		        "none",
		        "passBall",
		        "intakeDrive",
		        "timeDrive",
		        "reverseTimeDrive"
		    );
	
	ObservableList<String> conditions = 
		    FXCollections.observableArrayList(
		        "distance",
		        "distanceAngle",
		        //"angle",
		        "time",
		        "gearout",
		        "springin",
		        "none"
		    );
	
	private ComboBox stateBox;
	private ComboBox conditionBox;
	
	private Button delete;
	private TextField conditionValue;
	private TextField stateValue;
	private Label stageNumber;
	
	private boolean toBeOff;
	
	public AutoStage (int stage, ArrayList <AutoStage> stages, StackPane layout) {
		
		
		
		// instantiation
		toBeOff = false;
		
		stageNumber = new Label ();
		stageNumber.setTranslateX(-250);
		stageNumber.setText("Auto Stage " + stage);
		
		delete = new Button ();
		delete.setText("delete");
		delete.setTranslateX(160);
		
		conditionBox = new ComboBox (conditions);
		conditionBox.setTranslateX(-40);
		conditionBox.setPromptText("Condition");
		
		stateBox = new ComboBox (states);
		stateBox.setTranslateX(-150);
		stateBox.setPromptText("State");
		
		conditionValue = new TextField ();
		conditionValue.setPromptText("value");
		conditionValue.setPrefWidth(1);
		conditionValue.setTranslateX(80);
		conditionValue.setMaxWidth(100);
		
		stateValue = new TextField ();
		stateValue.setPromptText("value");
		stateValue.setPrefWidth(1);
		stateValue.setTranslateX(80);
		stateValue.setMaxWidth(100);
		
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if (event.getSource() == delete) {
						toBeOff = true;
						
						
						int y = 1;
						for (AutoStage x : stages) {
							if (x.getOffOrNot() != true) {
								x.changeStageNumber(y);
								x.setYPostions((y * 30) - 200);
								y++;
							}
						}
					
						for (AutoStage x : stages) {
							if (x.getOffOrNot() == true) {
								layout.getChildren().remove(x.getConditionBox());
								layout.getChildren().remove(x.getStageNumber());
								layout.getChildren().remove(x.getStateBox());
								layout.getChildren().remove(x.getStateValue());
								layout.getChildren().remove(x.getConditionValue());
								layout.getChildren().remove(x.getDelete());
								stages.remove(x);
							}
						}
					}
				} catch (Exception E) {
					
				}
			}
			
		});
	}
	
	public void delete (StackPane layout) {
		layout.getChildren().remove(conditionValue);
		layout.getChildren().remove(stateValue);
		layout.getChildren().remove(stateBox);
		layout.getChildren().remove(conditionBox);
		layout.getChildren().remove(stageNumber);
		layout.getChildren().remove(delete);
	}
	
	public void setYPostions (double y) {
		delete.setTranslateY(y);
		conditionValue.setTranslateY(y);
		conditionBox.setTranslateY(y);
		stateBox.setTranslateY(y);
		stateValue.setTranslateY(y);
		stageNumber.setTranslateY(y);
	}
	
	public int getStageNumberActual () {
		return Integer.parseInt(stageNumber.getText().substring(stageNumber.getText().length() - 1));
	}
	
	public void setStageInfo (int autoNumber, String state, float stateValueNumber, String condition, float conditionValueNumber) {
		stageNumber.setText("Auto Stage " + autoNumber);
		stateBox.getSelectionModel().select(state);
		conditionBox.getSelectionModel().select(condition);
		conditionValue.setText(Double.toString(conditionValueNumber));
		stateValue.setText(Double.toString(stateValueNumber));
	}
	
	public ComboBox getStateBox () {
		return stateBox;
	}
	
	public ComboBox getConditionBox () {
		return conditionBox;
	}
	
	public TextField getConditionValue () {
		return conditionValue;
	}
	
	public TextField getStateValue () {
		return stateValue;
	}
	
	public Button getDelete () {
		return delete;
	}
	
	public Label getStageNumber () {
		return stageNumber;
	}
	
	public void addToLayout (StackPane x) {
		x.getChildren().add(stateBox);
		x.getChildren().add(conditionBox);
		x.getChildren().add(stateValue);
		x.getChildren().add(delete);
		x.getChildren().add(conditionValue);
		x.getChildren().add(stageNumber);
	}
	
	public boolean getOffOrNot () {
		return toBeOff;
	}
	
	public void changeStageNumber (int newNumber) {
		stageNumber.setText("Auto Stage " + newNumber);
	}
	
	public String getStageString () {
		return stageNumber.getText();
	}
	
}
