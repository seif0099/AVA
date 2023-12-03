package com.example.demo.Controllers;

import com.example.demo.Entities.SharedData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ConsulterAva implements Initializable {



        @FXML
        private TextField idField;

        @FXML
        private TextField titulaireField;

        @FXML
        private TextField montantField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField soldeAva;
    @FXML
    private TextField dateField;


    @FXML
        private Label montantCalculeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load data into the form when the FXML is loaded
        loadFormData();
    }

    private void loadFormData() {
        SharedData.fiscalPath = "";
        titulaireField.setText(SharedData.currentAva.getTitulaire().toString());
        montantField.setText(SharedData.currentAva.getMontantCalcul().toString());
        idField.setText(SharedData.currentAva.getID().toString());
        typeField.setText(SharedData.currentAva.getType().toString());
        soldeAva.setText(SharedData.currentAva.getSoldeAvgit remote add origin https://github.com/your-username/your-repo.gita().toString());
        dateField.setText(SharedData.currentAva.getDateDomiciliation().toString());


    }


        @FXML
        private void valideAction() {

            montantCalculeLabel.setText("Valide action performed");
        }

        @FXML
        private void notValideAction() {
            // Implement your logic for the 'Not Valide' button action
            // You can access the values from idField, titulaireField, and montantField
            // Perform your calculations and update montantCalculeLabel accordingly
            montantCalculeLabel.setText("Not Valide action performed");
        }

    }

