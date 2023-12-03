package com.example.demo.Controllers;

import com.example.demo.DAOs.AvaFolderDAO;
import com.example.demo.Entities.AvaFolder;
import com.example.demo.Entities.SharedData;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AvaManagerView implements Initializable {

    @FXML
    private TableColumn<AvaFolder, Integer> IDAva;
    @FXML
    private TableColumn<AvaFolder, String> type;
    @FXML
    private TableColumn<AvaFolder, Integer> titulaire;
    @FXML
    private TableColumn<AvaFolder, Double> MontantCalcul;
    @FXML
    private TableColumn<AvaFolder, Double> DroitInitial;
    @FXML
    private TableColumn<AvaFolder, Double> SoldeAva;

    @FXML
    private TableColumn<AvaFolder, String> DeclarationFiscal;



    @FXML
    private TableView<AvaFolder> ava_tableView;

    @FXML
    private TextField searchAva_field;
    @FXML
    private Button openFolder_bnt;
    private ObservableList<AvaFolder> dataList;







    public void showAvaListData()
    {
        dataList = AvaFolderDAO.AvaList();
        IDAva.setCellValueFactory(new PropertyValueFactory<>("ID"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        titulaire.setCellValueFactory(new PropertyValueFactory<>("titulaire"));
        MontantCalcul.setCellValueFactory(new PropertyValueFactory<>("MontantCalcul"));
        DroitInitial.setCellValueFactory(new PropertyValueFactory<>("DroitInitial"));
        SoldeAva.setCellValueFactory(new PropertyValueFactory<>("SoldeAva"));
        DeclarationFiscal.setCellValueFactory(new PropertyValueFactory<>("DeclarationFiscal"));
        ava_tableView.setItems(dataList);

    }

    public void avaSearch(){
        FilteredList<AvaFolder> filteredList = new FilteredList<>(dataList, e ->true);
        ava_tableView.setItems(filteredList);
        searchAva_field.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredList.setPredicate(predicateAvaData ->{
                if(newValue ==null || newValue.isEmpty()){
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if(predicateAvaData.getID().toString().contains(searchKey)){
                    return true;
                } else if (predicateAvaData.getType().toLowerCase().contains(searchKey)) {
                    return true;
                }
                else if (predicateAvaData.getDeclarationFiscal().toLowerCase().contains(searchKey)) {
                    return true;
                }else if (predicateAvaData.getSoldeAva().toString().contains(searchKey)) {
                    return true;
                }else if (predicateAvaData.getTitulaire().toString().contains(searchKey)) {
                    return true;
                }else if (predicateAvaData.getMontantCalcul().toString().contains(searchKey)) {
                    return true;
                }else if (predicateAvaData.getDroitInitial().toString().contains(searchKey)) {
                    return true;
                }
                return false;
            });
        });

    }
    public void getSelectedItem()
    {
        int num = ava_tableView.getSelectionModel().getSelectedIndex();
        if(num-1<-1)
            return;
        SharedData.currentAva = ava_tableView.getSelectionModel().getSelectedItem();


    }
    public void consulter() throws IOException {
       if(SharedData.currentAva == null)
            return;
        openFolder_bnt.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/consulter-ava.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAvaListData();
        avaSearch();

    }

}
