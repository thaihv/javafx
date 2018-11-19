package com.uitgis.plugin.tilegenerator.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.uitgis.maple.common.util.Noti;
import com.uitgis.plugin.tilegenerator.model.WizardData;

import framework.i18n.I18N;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;

public class OutputController {

	private Logger log = LoggerFactory.getLogger(OutputController.class);

	@FXML
	TextField tfLocation, tfMapName, tfExpression;
	
	@FXML
	Label lblOutputTitle;
	
	@FXML
	ToggleGroup tglGroupOutput;

	@FXML
	RadioButton rbTileFile, rbTileGSS;

	@FXML
	Button btnBuildAsFile;

	@FXML
	ComboBox<String> cmbTileFormat;
	
	@FXML
	CheckBox cbGoogleXYZ;

	@FXML
	HBox hbxTileGSS, hbxLocation, hbxExpression;

	@Inject
	WizardData model;

	public ObservableList<String> choiceTileFormats = FXCollections.observableArrayList("PNG", "JPEG");

	@FXML
	public void initialize() {
		
		
		lblOutputTitle.setFont(Font.font("System", FontWeight.BOLD, 14));
		
		hbxExpression.disableProperty().bind(rbTileGSS.selectedProperty());
		hbxLocation.disableProperty().bind(rbTileGSS.selectedProperty());
		hbxTileGSS.disableProperty().bind(rbTileFile.selectedProperty());

		cmbTileFormat.getItems().addAll(choiceTileFormats);
		cmbTileFormat.getSelectionModel().selectFirst();

		tfMapName.textProperty().bindBidirectional(model.tileNameProperty());
		tfLocation.textProperty().bindBidirectional(model.destinationFolderProperty());
		tfExpression.textProperty().bindBidirectional(model.pathExpressionProperty());
		cbGoogleXYZ.selectedProperty().bindBidirectional(model.googleXYZProperty());

		btnBuildAsFile.setOnAction(event -> {
			DirectoryChooser dirChooser = new DirectoryChooser();
			dirChooser.setTitle(I18N.getText("SearchFolder"));

			File file = dirChooser.showDialog(btnBuildAsFile.getScene().getWindow());
			if (file != null) {
				tfLocation.setText(file.getAbsolutePath());
			}
		});

		tglGroupOutput.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle oldVal, Toggle newVal) {

				int selected = tglGroupOutput.getToggles().indexOf(tglGroupOutput.getSelectedToggle());
				model.setTileMapType(selected);

			}
		});

		cmbTileFormat.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal.equals("PNG"))
				model.setTileFormat(0);
			else
				model.setTileFormat(1);
		});
	}

	@Validate
	public boolean validate() throws Exception {

		if (tfMapName.getText() == null || tfMapName.getText().isEmpty()) {
			Noti.showAlert("Missing Field", "Tile Map Name field is required.");
			return false;
		}

		if (tfLocation.getText() == null || tfLocation.getText().isEmpty()) {
			Noti.showAlert("Missing Field", "Tile File Location field is required.");
			return false;
		}

		if (tfExpression.getText() == null || tfExpression.getText().isEmpty()) {
			Noti.showAlert("Missing Field", "Subpath Expression field is required.");
			return false;
		}
		return true;
	}

	@Submit
	public void submit() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("[SUBMIT] the user has completed step Output Configuration");
		}
	}
}
