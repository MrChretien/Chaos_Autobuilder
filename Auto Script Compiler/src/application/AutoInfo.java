package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AutoInfo {
	
	private ObservableList<String> states = 
		    FXCollections.observableArrayList(
//		        "forward",
//		        "right",
//		        "shoot",
//		        "wait",
//		        "retract",
//		        "follow",
//		        "none",
//		        "passBall",
//		        "intakeDrive",
//		        "timeDrive",
//		        "reverseTimeDrive"
		    );
	
	private ObservableList<String> conditions = 
		    FXCollections.observableArrayList(
		        //"distance",
		        //"distanceAngle",
		        //"time",
		        //"gearout",
		        //"springin",
		        //"none"
		    );
	
	Button loadConfig;
	FileChooser fileChooser;
	
	File configFile;
	
	final String separater = "~";
	
	public AutoInfo (StackPane layout, Stage primaryStage) {
		
		fileChooser = new FileChooser();
		
		configFile = fileChooser.showOpenDialog(primaryStage);
		updateList(primaryStage);
		
		loadConfig = new Button ();
		loadConfig.setText ("LoadConfig");
		loadConfig.setTranslateX(-260);
		loadConfig.setTranslateY(-340);

		loadConfig.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					
					configFile = fileChooser.showOpenDialog(primaryStage);
					updateList(primaryStage);
					
				} catch (Exception e) {
					
				}
			}
		});
		
		addToLayout (layout);
	}
	
	private void addToLayout (StackPane x) {
		x.getChildren().add(loadConfig);
	}
	private void updateList (Stage primaryStage) {

		//fileChooser = new FileChooser ();
		
		try  {
			states.clear();
			conditions.clear();
			File file = configFile;
			FileReader fr = new FileReader (file);
			BufferedReader br = new BufferedReader (fr);
			String line;
			boolean startConditions = false;
			while ((line = br.readLine()) != null) {
				
				
				if (startConditions) {
					conditions.add(line);
				} else if (!line.equals(separater)) {
					states.add(line);
				}
				
				if (line.equals(separater)) {
					startConditions = true;
				}
				
			}
			fr.close();
		} catch (IOException e) {
			
		}
	}
	
	public ObservableList<String> getStates () {
		return states;
	}
	
	public ObservableList<String> getConditions () {
		return conditions;
	}
}
