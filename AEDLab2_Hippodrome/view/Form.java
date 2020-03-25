package view;

import java.util.LinkedList;
import java.util.Queue;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Bet;
import model.Hippodrome;
import model.Runner;


public class Form extends Application{
	
	
	private static Hippodrome<Runner, Bet> hippodrome;
	
	
	public static void main(String[] args) {
		hippodrome = new Hippodrome<>();
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ComboBox<String> optionsHorses = new ComboBox<>();
		BorderPane root = new BorderPane();
		GridPane formRunner = new GridPane();
		Label nRunner = new Label("Nombre del Corredor");
		Label nHorse = new Label("Nombre del Caballo");
		TextField tnRunner = new TextField();
		TextField tnHorse = new TextField();
		Button AddRunner = new Button("Agregar Corredor");
		
		AddRunner.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				String name = tnRunner.getText();
				String horse = tnHorse.getText();
				hippodrome.addRunner(new Runner(name, horse));
				optionsHorses.getItems().add(horse);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Se ha ingresado el Nuevo Corredor");
				alert.showAndWait();
			}
		});
		
		formRunner.add(nRunner, 0, 0);
		formRunner.add(tnRunner, 1, 0);
		formRunner.add(nHorse, 0, 1);
		formRunner.add(tnHorse, 1, 1);
		formRunner.add(AddRunner, 0, 2);
		
		
		BorderPane formManageBet = new BorderPane();
		
		GridPane formBet = new GridPane();
		Label lId = new Label("Cedula");
		Label lName = new Label("Nombre del Apostador");
		Label lHorse = new Label("Caballo a Seleccionar");
		Label lBudget = new Label("Monto de la Apuesta");
		TextField tlId = new TextField();
		TextField tlName = new TextField();
		TextField tlBudget = new TextField();
		
		Button AddBet = new Button("Agregar Apuesta");
		
		AddBet.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					String iD = tlId.getText();
					String name = tlName.getText();
					String horse = optionsHorses.getValue();
					int budget = Integer.parseInt(tlBudget.getText());
					if(budget<=0) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setContentText("El presupuesto debe ser positivo y mayor que cero, Por favor intente de nuevo");
						alert.showAndWait();
					}else {
						hippodrome.addBet(new Bet(iD, name, horse, budget));
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setContentText("Se ha creado la apuesta");
						alert.showAndWait();
					}
					
				}catch(NumberFormatException e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setContentText("Inserte un presupuesto para su apuesta");
					alert.showAndWait();
					
				}
			}
		});
		
		
		formBet.add(lId, 0, 0);
		formBet.add(tlId, 1, 0);
		formBet.add(lName, 0, 1);
		formBet.add(tlName, 1, 1);
		formBet.add(lHorse, 0, 2);
		formBet.add(optionsHorses, 1, 2);
		formBet.add(lBudget, 0, 3);
		formBet.add(tlBudget, 1, 3);
		formBet.add(AddBet, 0, 4);
		
		
		GridPane formSearch = new GridPane();
		Label lBet = new Label("Apuesta a buscar(Cedula)");
		TextField tlBet = new TextField();
		Button searchBet = new Button("Buscar Apuesta");
		Label lResult = new Label("Resultado de su apuesta");
		
		
		searchBet.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				String id = tlBet.getText();
				Bet bet = hippodrome.getBet(id);
				if(bet!=null) {
					if(hippodrome.getArrivalOrder().peek().getHorse().equals(bet.getHorse())) {
						lResult.setText("Resultado de su apuesta: El caballo ha ganado");
					}else {
						lResult.setText("Resultado de su apuesta: El caballo ha perdido");
					}
				}else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setContentText("No se ha realizado por parte de este apostador");
					alert.showAndWait();
				}
			}
		});
		
		formSearch.add(lBet, 0, 0);
		formSearch.add(tlBet, 1, 0);
		formSearch.add(searchBet, 0, 1);
		formSearch.add(lResult, 0, 2);
		
		formManageBet.setTop(formBet);
		formManageBet.setCenter(formSearch);
		
		
		BorderPane formManageRace = new BorderPane();
		
		ListView<String> resultRace = new ListView<>();
		
		
		
		ToolBar optionsRace = new ToolBar();
		Button startRace = new Button("Comenzar carrera");
		Button revenge = new Button("Revancha");
		Button createNewRace = new Button("Crear nueva carrera");
		
		startRace.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if(hippodrome.getArrivalOrder().size()<6) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setContentText("No hay suficientes competidores para comenzar la carrera");
					alert.showAndWait();
				}else {
					resultRace.getItems().clear();
					hippodrome.setSecondLap(false);
					hippodrome.startRace();
					Queue<Runner> arrival = new LinkedList<Runner>(hippodrome.getArrivalOrder());
					ObservableList<String> data = FXCollections.observableArrayList();
					int position = 1;
					while(arrival.peek()!=null) {
						data.add(position+". "+arrival.poll().getHorse());
						position++;
					}
					resultRace.setItems(data);
				}
				
			}
		});
		
		revenge.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if(hippodrome.getArrivalOrder().size()<6) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setContentText("No hay suficientes competidores para comenzar la carrera");
					alert.showAndWait();
				}else {
					resultRace.getItems().clear();
					hippodrome.setSecondLap(true);
					hippodrome.startRace();
					Queue<Runner> arrival = new LinkedList<Runner>(hippodrome.getArrivalOrder());
					ObservableList<String> data = FXCollections.observableArrayList();
					int position = 1;
					while(arrival.peek()!=null) {
						data.add(position+". "+arrival.poll().getHorse());
						position++;
					}
					resultRace.setItems(data);
				}
				
			}
		});
		
		createNewRace.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				hippodrome = new Hippodrome<Runner, Bet>();
				resultRace.getItems().clear();
				optionsHorses.getItems().clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Se ha iniciado un nuevo evento de carreras de caballos");
				alert.showAndWait();
			}
		});
		
		optionsRace.getItems().add(startRace);
		optionsRace.getItems().add(revenge);
		optionsRace.getItems().add(createNewRace);
		
		formManageRace.setTop(resultRace);
		formManageRace.setCenter(optionsRace);
		
		
		root.setTop(formRunner);
		root.setCenter(formManageBet);
		root.setBottom(formManageRace);
		
        Scene scene = new Scene(root, 600, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
}

