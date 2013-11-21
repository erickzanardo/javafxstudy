package org.eck.controller;

import javafx.fxml.FXML;

import org.eck.view.MainApp;

public class RootLayoutController {

	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleShowBirthdayStatistics() {
		mainApp.showBirthdayStatistics();
	}
}
