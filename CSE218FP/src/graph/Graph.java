package graph;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.FileHandler;

public class Graph {
	private LineChart<Number, Number> graph;
	private XYChart.Series<Number, Number> oneLoopData;
	private XYChart.Series<Number, Number> threeLoopsData;

	public Graph() {
		NumberAxis percentage = new NumberAxis();
		percentage.setLabel("Number of words- %");
		NumberAxis timer = new NumberAxis();
		timer.setLabel("Time- ms");

		graph = new LineChart<Number, Number>(percentage, timer);
		oneLoopData = new XYChart.Series<Number, Number>();
		oneLoopData.setName("One Loop");
		threeLoopsData = new XYChart.Series<Number, Number>();
		threeLoopsData.setName("Thre Loops");
	}

	public void addOneLoopData(Number percentage, Number time) {
		oneLoopData.getData().add(new XYChart.Data<Number, Number>(percentage, time));
	}

	public void addThreeLoopsData(Number percentage, Number time) {
		threeLoopsData.getData().add(new XYChart.Data<Number, Number>(percentage, time));
	}

	@SuppressWarnings("unchecked")
	public void showGraph() {
		Stage graphStage = new Stage();
		graphStage.initModality(Modality.APPLICATION_MODAL);
		graphStage.initOwner(new Stage());

		VBox graphBox = new VBox();
		graph.getData().addAll(oneLoopData, threeLoopsData);
		graphBox.getChildren().add(graph);

		Scene graphScene = new Scene(graphBox);
		graphStage.setScene(graphScene);
		graphStage.showAndWait();
		graph.getData().clear();
		oneLoopData.getData().clear();
		threeLoopsData.getData().clear();
	}
	
	public void saveData() {
		String data = "Data\n(x, y) -> (percentage, time)\nOne Loop Method = { ";
		for(int i = 0; i < oneLoopData.getData().size(); i++) {
			data += "(" + oneLoopData.getData().get(i).getXValue() + ", " + oneLoopData.getData().get(i).getYValue() + ")";
			if(i != oneLoopData.getData().size() - 1) {
				data += ", ";
			} else {				
				data += " }\nThree Loop Method = { ";
			}
		}
		for(int i = 0; i < threeLoopsData.getData().size(); i++) {
			data += "(" + threeLoopsData.getData().get(i).getXValue() + ", " + threeLoopsData.getData().get(i).getYValue() + ")";
			if(i != threeLoopsData.getData().size() - 1) {
				data += ", ";
			} else {
				data += " }";
			}
		}
		FileHandler.saveToNewFile(data, new File("outputData\\graphData.txt"));
	}

}
