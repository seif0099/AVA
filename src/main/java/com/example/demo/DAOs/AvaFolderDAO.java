package com.example.demo.DAOs;

import com.example.demo.Entities.AvaFolder;
import com.example.demo.Entities.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AvaFolderDAO {
    public static void ajouterDossierAva(AvaFolder avaFolder){
        String sql = "INSERT INTO avafolder (type,montantCalcul,droitInitial,soldeAva,DateDomiciliation,declarationFiscal,titulaire) VALUES (?,?,?,?,?,?,?)";
        Connection connect = Database.connectDb();
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1,avaFolder.getType());
            prepare.setDouble(2,avaFolder.getMontantCalcul());
            prepare.setDouble(3,avaFolder.getDroitInitial());
            prepare.setDouble(4,avaFolder.getSoldeAva());
            prepare.setString(5,avaFolder.getDateDomiciliation());
            prepare.setString(6,avaFolder.getDeclarationFiscal());
            prepare.setInt(7,avaFolder.getTitulaire());
            prepare.executeUpdate();
        }catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }
}
