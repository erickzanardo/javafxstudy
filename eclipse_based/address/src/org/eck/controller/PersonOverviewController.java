package org.eck.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import org.eck.model.Person;
import org.eck.view.MainApp;

public class PersonOverviewController {
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;

	private MainApp mainApp;

	public PersonOverviewController() {
	}

	@FXML
	private void initialize() {
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		personTable.setItems(mainApp.getPersonData());
	}
}
