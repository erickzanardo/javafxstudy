package org.eck.controller;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import org.eck.model.Person;

public class BirthdayStatisticsController {
	@FXML
	private BarChart<String, Integer> barChart;

	@FXML
	private CategoryAxis xAxis;

	private ObservableList<String> monthNames = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
		monthNames.addAll(Arrays.asList(months));

		xAxis.setCategories(monthNames);
	}

	/**
	 * Sets the persons to show the statistics for.
	 * 
	 * @param persons
	 */
	public void setPersonData(List<Person> persons) {
		int[] monthCounter = new int[12];
		for (Person p : persons) {
			int month = p.getBirthday().get(Calendar.MONTH);
			monthCounter[month]++;
		}

		XYChart.Series<String, Integer> series = createMonthDataSeries(monthCounter);
		barChart.getData().add(series);
	}

	private XYChart.Series<String, Integer> createMonthDataSeries(int[] monthCounter) {
		XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();

		for (int i = 0; i < monthCounter.length; i++) {
			XYChart.Data<String, Integer> monthData = new XYChart.Data<String, Integer>(monthNames.get(i), monthCounter[i]);
			series.getData().add(monthData);
		}

		return series;
	}
}
