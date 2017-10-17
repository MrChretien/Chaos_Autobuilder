package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Main extends Application{
	
	AutoInfo info;
	
	FileChooser fileChooser;
	
	Button add;
	Button make;
	Button load;
	
	TextField fileName;
//	TextField loadFileName;
	
	StackPane layout;
	
	ArrayList <AutoStage> stages;
	
	PrintWriter output;
	

	@Override
	public void start(Stage primaryStage) throws Exception {

		
		fileChooser = new FileChooser();
		fileName = new TextField ();
		fileName.setMaxWidth(200);
		fileName.setPromptText("file name");
		stages = new ArrayList <AutoStage>();
		primaryStage.setTitle("Auto Script Compiler");
		layout = new StackPane ();
		info = new AutoInfo (layout, primaryStage);
		Scene scene = new Scene (layout, 800, 900);

		add = new Button ();
		make = new Button ();
		load = new Button ();
		
		
		make.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent event) {
				try {
					output = new PrintWriter(fileName.getText() + ".ini", "UTF-8");
				
					output.println("[NetworkTables Storage 3.0]");
					for (AutoStage x : stages) {
						output.println("string \"/Preferences/" + x.getStageString()
						+ "\"=\"" + x.getStateBox().getSelectionModel().getSelectedItem().toString()
						+ "\\x3D" + x.getStateValue().getText()
						+ ";" + x.getConditionBox().getSelectionModel().getSelectedItem().toString()
						+ "\\x3D" + x.getConditionValue().getText() + "\"");
					}
					output.close();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		add.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent arg0) {
				AutoStage stage = new AutoStage (0, stages, layout, info.getStates(), info.getConditions());
				stage.addToLayout(layout);
				stages.add(stage);
				int y = 1;
				for (AutoStage x : stages) {
					if (x.getOffOrNot() != true) {
						x.changeStageNumber(y);
						x.setYPostions((y * 30) - 200);
						y++;
					}
				}
			}
		});
		
		load.setOnAction(new EventHandler<ActionEvent> () {

			@Override
			public void handle(ActionEvent event) {
				ArrayList <String> lines = new ArrayList <String> ();
				
				
					try  {
						File file = fileChooser.showOpenDialog(primaryStage);
						FileReader fr = new FileReader (file);
						BufferedReader br = new BufferedReader (fr);
						String line;
						fileName.setText(file.getName().substring(0, file.getName().length() - 4));
						while ((line = br.readLine()) != null) {
							lines.add(line);
						}
						fr.close();
					} catch (IOException e) {
						
					}
					
					for (AutoStage x : stages) {
						x.delete(layout);
					}
					
					stages.clear(); 
					for (String line : lines) {
						if (line.equals("[NetworkTables Storage 3.0]") == false && line.equals("string \"/SmartDashboard/autoSelection\"=\"blah\"") == false) {
							
							String[] quoteSections = line.split("\"");
							String autoNumber = quoteSections[1].substring(quoteSections[1].length()-1);
							String autoState = quoteSections[3].split(";")[0].split("x3D")[0];
							String stateValue = quoteSections[3].split(";")[0].split("x3D")[1];
							String autoCondtion = quoteSections[3].split(";")[1].split("x3D")[0];
							String conditionValue = quoteSections[3].split("x3D")[2];
							
							
							//splitting on backslashes doesn't work (I don't think so)
							autoCondtion = autoCondtion.substring(0, autoCondtion.length()-1);
							autoState = autoState.substring(0, autoState.length()-1);
							
							AutoStage stage = new AutoStage (Integer.parseInt(autoNumber), stages, layout, info.getStates(), info.getConditions());
							stage.setStageInfo(Integer.parseInt(autoNumber), autoState, stateValue, autoCondtion, conditionValue);
							stage.addToLayout(layout);
							
							stages.add(stage);
							for (AutoStage x : stages) {
								x.setYPostions((x.getStageNumberActual() * 30) - 200);
							}
							
						}
						
					}
				
			}
			
		});
		
		add.setText("add");
		add.setTranslateX(-350);
		add.setTranslateY(0);
		
		load.setText("load");
		load.setTranslateX(75);
		load.setTranslateY(-300);
		
		make.setText("make");
		make.setTranslateX(-75);
		make.setTranslateY(-300);
		
		fileName.setTranslateX(0);
		fileName.setTranslateY(-250);
		
		layout.getChildren().add(add);
		layout.getChildren().add(make);
		layout.getChildren().add(fileName);
		layout.getChildren().add(load);
				
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	

	
	public static void main(String[] args) {
		launch(args);
	}
	
}
