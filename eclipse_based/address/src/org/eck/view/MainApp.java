package org.eck.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.eck.controller.BirthdayStatisticsController;
import org.eck.controller.PersonEditDialogController;
import org.eck.controller.PersonOverviewController;
import org.eck.controller.RootLayoutController;
import org.eck.model.Person;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Person> personData = FXCollections.observableArrayList();

	public MainApp() {
		// Add some sample data
		personData.add(new Person("Hans", "Muster"));
		personData.add(new Person("Ruth", "Mueller"));
		personData.add(new Person("Heinz", "Kurz"));
		personData.add(new Person("Cornelia", "Meier"));
		personData.add(new Person("Werner", "Meyer"));
		personData.add(new Person("Lydia", "Kunz"));
		personData.add(new Person("Anna", "Best"));
		personData.add(new Person("Stefan", "Meier"));
		personData.add(new Person("Martin", "Mueller"));
	}

	public ObservableList<Person> getPersonData() {
		return personData;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");

		FXMLLoader loader = getLoader("org/eck/view/RootLayout.fxml");
		rootLayout = loadBorderPane(loader);
		Scene scene = new Scene(rootLayout);

		RootLayoutController rootLayoutController = loader.getController();
		rootLayoutController.setMainApp(this);
		
		primaryStage.setScene(scene);
		primaryStage.show();

		showPersonOverview();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void showPersonOverview() {
		FXMLLoader loader = getLoader("org/eck/view/PersonOverview.fxml");
		AnchorPane overviewPage = loadAnchorPane(loader);
		rootLayout.setCenter(overviewPage);

		PersonOverviewController controller = loader.getController();
		controller.setMainApp(this);
	}

	public boolean showPersonEditDialog(Person person) {
		FXMLLoader loader = getLoader("org/eck/view/PersonEditDialog.fxml");
		AnchorPane page = loadAnchorPane(loader);
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Edit Person");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);

		PersonEditDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setPerson(person);

		dialogStage.showAndWait();

		return controller.isOkClicked();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showBirthdayStatistics() {
		FXMLLoader loader = getLoader("org/eck/view/BirthdayStatistics.fxml");
		AnchorPane page = loadAnchorPane(loader);
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Birthday Statistics");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);

		BirthdayStatisticsController controller = loader.getController();
		controller.setPersonData(personData);

		dialogStage.show();
	}

	private FXMLLoader getLoader(String path) {
		FXMLLoader loader = new FXMLLoader(MainApp.class.getClassLoader().getResource(path));
		return loader;
	}

	private AnchorPane loadAnchorPane(FXMLLoader loader) {
		try {
			return (AnchorPane) loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private BorderPane loadBorderPane(FXMLLoader loader) {
		try {
			return (BorderPane) loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
