package com.haulmont.db;

import com.haulmont.model.Receipt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReceiptsData extends AbstractDBController<Receipt, Long> {

    public ReceiptsData(){
        super();
    }

    @Override
    public List<Receipt> getAll() {
        List<Receipt> receipts = new ArrayList<>();
        try {

            ResultSet rs = sendQuery("SELECT * FROM receipts");
            while (rs.next()){

                receipts.add( new Receipt(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getLong(4),
                        rs.getDate(5),
                        rs.getInt(6),
                        rs.getInt(7))
                );
            }
        }catch (SQLException e){
            e.getMessage();
        }

        return receipts;
    }

    @Override
    public Receipt getEntityById(Long id) {

        try {

            ResultSet rs = sendQuery("SELECT * FROM receipts WHERE id = " + id);
            return new Receipt(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getLong(3),
                    rs.getLong(4),
                    rs.getDate(5),
                    rs.getInt(6),
                    rs.getInt(7)
            );

        } catch (SQLException e){
            e.getMessage();
        }

        return null;
    }

    @Override
    public Receipt update(Receipt entity) {

        try {

            sendQuery("UPDATE receipts set "
                    + "description = " + entity.getDescription()
                    + ", docID = " + entity.getDoctorID()
                    + ", patientID = " + entity.getPatientID()
                    + ", creationDate = " + (new SimpleDateFormat("yyyy-MM-dd").format(entity.getCreationDate()))
                    + ", expiration = " + entity.getExpiration()
                    + "priority = " + entity.getPrior().ordinal()
            );

        } catch (SQLException e){
            e.getMessage();
        }

        return null;
    }

    @Override
    public boolean delete(Long id) {
        try {

            sendQuery("DELETE FROM receipts WHERE id = " + id);
            return true;

        } catch (SQLException e){
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean create(Receipt entity) {
        try {

            sendQuery("INSERT INTO receipts (description, docID, patientID, creationDate, expiration, priority) values ("
                    + entity.getDescription() + ", "
                    + entity.getDoctorID() + ", "
                    + entity.getPatientID() + ", DATE '"
                    + (new SimpleDateFormat("yyyy-MM-dd").format(entity.getCreationDate())) + "', "
                    + entity.getExpiration() + ", "
                    + entity.getPrior().ordinal() + ");"
            );
            return true;

        } catch (SQLException e) {
            e.getMessage();
            return false;

        }
    }

    public List<Receipt> chargeOf(long doctorID){

        List<Receipt> receipts = new ArrayList<>();

        try {
            ResultSet rs = sendQuery("SELECT * FROM receipts WHERE doctorID = " + doctorID);
            while (rs.next()){

                receipts.add( new Receipt(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getLong(4),
                        rs.getDate(5),
                        rs.getInt(6),
                        rs.getInt(7)
                ));
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        return receipts;
    }
}
