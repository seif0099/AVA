package com.example.demo.Controllers;

import com.example.demo.DAOs.AvaFolderDAO;
import com.example.demo.Entities.AvaFolder;
import com.example.demo.Entities.SharedData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AvaFolderView implements Initializable {
    @FXML
    private ComboBox<?> avaType_cobx;

    @FXML
    private TextField baseCalc_field;

    @FXML
    private Button createAva_btn;

    @FXML
    private TextField dateDomiciliation_field;

    @FXML
    private TextField droitInit_field;

    @FXML
    private Button importDeclarartion_btn;

    @FXML
    private TextField soldeAva_field;

    @FXML
    private TextField titulaire_field;

    @FXML
    private AnchorPane parentForm;
    @FXML
    private Label importLabel;
    private String[] avaTypes = {"A","E"};

    public void addAvaTypesList(){
        SharedData.fiscalPath = "";
        titulaire_field.setText(SharedData.currentClient.getID().toString());
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        dateDomiciliation_field.setText(String.valueOf(sqlDate));

        List<String> listT = new ArrayList<String>(2);
        for (String data : avaTypes){
            listT.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listT);
        avaType_cobx.setItems(listData);
    }
    public void uploadFile(){
        FileChooser open = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        open.getExtensionFilters().add(imageFilter);
        File file = open.showOpenDialog(parentForm.getScene().getWindow());
        if(file != null){
            SharedData.fiscalPath=file.getAbsolutePath();
            importLabel.setText(file.getName());
        }
    }
    public void changeAvaSoldForBase(){
        baseCalc_field.textProperty().addListener((observable,oldValue,newValue)->{
            if (!newValue.matches("\\d*")) {
                baseCalc_field.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if(!newValue.isEmpty() && newValue.matches("\\d*") ){
                double afterCalc = (double)Math.round((Integer.parseInt(baseCalc_field.getText()) * 0.25) * 100)/100;
                droitInit_field.setText(Double.toString(afterCalc));
                soldeAva_field.setText(Double.toString(afterCalc));
            }
        });
    }
    public void createAvaFolder() throws IOException {
        Alert alert;
        if(avaType_cobx.getSelectionModel().getSelectedItem() == null
                || titulaire_field.getText().isEmpty() || baseCalc_field.getText().isEmpty() || droitInit_field.getText().isEmpty() || soldeAva_field.getText().isEmpty()
                || dateDomiciliation_field.getText().isEmpty() || SharedData.fiscalPath.isEmpty())
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tout les champs Pour créer le dossier AVA !");
            alert.showAndWait();
        }else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Confirmer la création de dossier AVA ?");
            Optional<ButtonType> option = alert.showAndWait();
            if(option.get().equals(ButtonType.OK)){
                String urf = SharedData.fiscalPath;
                urf = urf.replace("\\","\\\\");
                AvaFolder folderAva = new AvaFolder(
                        1,
                        (String)avaType_cobx.getSelectionModel().getSelectedItem(),
                        SharedData.currentClient.getID(),
                        Double.parseDouble(baseCalc_field.getText()),
                        Double.parseDouble(droitInit_field.getText()),
                        Double.parseDouble(soldeAva_field.getText()),
                        dateDomiciliation_field.getText(),
                        urf
                );

                AvaFolderDAO.ajouterDossierAva(folderAva);
                createAva_btn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/client_manager-view.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addAvaTypesList();
        changeAvaSoldForBase();

    }
}
