package org.eck.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import org.eck.model.Person;
import org.eck.utils.CalendarUtil;
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

		personTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		showPersonDetails(null);

		personTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {

			@Override
			public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
				showPersonDetails(newValue);
			}
		});
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		personTable.setItems(mainApp.getPersonData());
	}

	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();

		if (selectedIndex == -1) {
			Dialogs.showWarningDialog(mainApp.getPrimaryStage(), "Please select a person in the table.", "No Person Selected",
					"No Selection");
		} else {
			personTable.getItems().remove(selectedIndex);
		}
	}

	public void showPersonDetails(Person person) {
		if (person != null) {
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(String.valueOf(person.getPostalCode()));
			cityLabel.setText(person.getCity());
			birthdayLabel.setText(CalendarUtil.format(person.getBirthday()));
		} else {
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}

	@FXML
	private void handleNewPerson() {
		Person tempPerson = new Person();
		boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
		if (okClicked) {
			mainApp.getPersonData().add(tempPerson);
		}
	}

	@FXML
	private void handleEditPerson() {
		Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				refreshPersonTable();
				showPersonDetails(selectedPerson);
			}

		} else {
			Dialogs.showWarningDialog(mainApp.getPrimaryStage(), "Please select a person in the table.", "No Person Selected",
					"No Selection");
		}
	}

	private void refreshPersonTable() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		personTable.setItems(null);
		personTable.layout();
		personTable.setItems(mainApp.getPersonData());
		personTable.getSelectionModel().select(selectedIndex);
	}
}
