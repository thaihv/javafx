package com.uitgis.plugin.tilegenerator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.uitgis.plugin.tilegenerator.model.WizardData;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DrawingController {

    private Logger log = LoggerFactory.getLogger(DrawingController.class);

    @FXML
    TextField tfField5, tfField6, tfField7;

    @Inject
    WizardData model;

    @FXML
    public void initialize() {
//        tfField5.textProperty().bindBidirectional(model.field5Property());
//        tfField6.textProperty().bindBidirectional(model.field6Property());
//        tfField7.textProperty().bindBidirectional(model.field7Property());

    }

    @Validate
    public boolean validate() throws Exception {
//        if( tfField5.getText() == null || tfField5.getText().isEmpty() ) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Step 3");
//            alert.setHeaderText( "Missing Field" );
//            alert.setContentText( "Field 5 is required." );
//            alert.showAndWait();
//            return false;
//        }
//
//        if( tfField6.getText() == null || tfField6.getText().isEmpty() ) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Step 3");
//            alert.setHeaderText( "Missing Field" );
//            alert.setContentText( "Field 6 is required." );
//            alert.showAndWait();
//            return false;
//        }
//
//        if( tfField7.getText() == null || tfField7.getText().isEmpty() ) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Step 3");
//            alert.setHeaderText( "Missing Field" );
//            alert.setContentText( "Field 7 is required." );
//            alert.showAndWait();
//            return false;
//        }
        return true;
    }

    @Submit
    public void submit() throws Exception {
        if( log.isDebugEnabled() ) {
            log.debug("[SUBMIT] the user has completed step Drawing Configuration");
        }
    }
}


