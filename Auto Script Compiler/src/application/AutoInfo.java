package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class AutoInfo {
	
	private ObservableList<String> states = 
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
	
	private ObservableList<String> conditions = 
		    FXCollections.observableArrayList(
		        "distance",
		        "distanceAngle",
		        "time",
		        "gearout",
		        "springin",
		        "none"
		    );
	
	Button addState;
	Button deleteState;
	
	TextField addStateField;
	ComboBox deleteStateBox;
	
	Button addCondition;
	Button deleteCondition;
	
	TextField addConditionField;
	ComboBox deleteConditionBox;
	
	public AutoInfo (StackPane layout) {

		addState = new Button ();
		addState.setText ("add");
		addState.setTranslateX(-260);
		addState.setTranslateY(-340);
		
		deleteState = new Button ();
		deleteState.setText ("delete");
		deleteState.setTranslateX(-220);
		deleteState.setTranslateY(-310);
		
		addState.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					addState (addStateField.getText());
					addStateField.setText("");
				} catch (Exception e) {
					
				}
			}
		});
		
		deleteState.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					deleteState (deleteStateBox.getSelectionModel().getSelectedItem().toString());
				} catch (Exception e) {
					
				}
			}
		});
		
		addStateField = new TextField ();
		addStateField.setPromptText("State");
		addStateField.setMaxWidth(100);
		addStateField.setTranslateX(-330);
		addStateField.setTranslateY(-340);
		
		addCondition = new Button ();
		addCondition.setText("add");
		
		deleteCondition = new Button ();
		deleteCondition.setText("delete");
		
		addCondition.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					addCondition (addConditionField.getText());
				} catch (Exception e) {
					
				}
			}
		});
		
		deleteCondition.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					deleteCondition (deleteStateBox.getSelectionModel().getSelectedItem().toString());
				} catch (Exception e) {
					
				}
			}
		});
		
		addConditionField = new TextField ();
		addConditionField.setPromptText("Condition");
		addConditionField.setMaxWidth(100);
		
		instantiateComboBoxes();
		deleteStateBox.setPromptText("State");
		deleteConditionBox.setPromptText("Condition");
		
		addToLayout (layout);
	}
	
	private void addToLayout (StackPane x) {
		x.getChildren().add(addState);
		x.getChildren().add(deleteState);
		x.getChildren().add(addStateField);
		x.getChildren().add(deleteStateBox);
		x.getChildren().add(addCondition);
		x.getChildren().add(deleteCondition);
		x.getChildren().add(addConditionField);
		x.getChildren().add(deleteConditionBox);
	}
	
	public void deleteCondition (String deletion) {
		conditions.remove(deletion);
		instantiateComboBoxes ();
	}
	
	public void deleteState (String deletion) {
		states.remove(deletion);
		instantiateComboBoxes ();
	}
	
	public void addState (String addition) {
		states.add(addition);
		instantiateComboBoxes ();
	}
	
	public void addCondition (String addition) {
		conditions.add(addition);
		instantiateComboBoxes ();
	}
	
	public void instantiateComboBoxes () {
		deleteStateBox = new ComboBox (states);
		deleteStateBox.setTranslateX(-320);
		deleteStateBox.setTranslateY(-310);
		deleteConditionBox = new ComboBox (conditions);
	}
	
	public ObservableList<String> getStates () {
		return states;
	}
	
	public ObservableList<String> getConditions () {
		return conditions;
	}
	
}
