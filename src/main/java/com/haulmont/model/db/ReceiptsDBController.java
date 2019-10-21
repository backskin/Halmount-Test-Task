package com.haulmont.model.db;

import com.haulmont.model.entities.Receipt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReceiptsDBController extends AbstractDBController<Receipt, Long> {

    public ReceiptsDBController(){
        super();
    }

    @Override
    protected void addResultsToList(ResultSet rs, List<Receipt> out) throws SQLException {

        while (rs.next()){
            out.add( new Receipt(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getLong(3),
                    rs.getLong(4),
                    rs.getDate(5),
                    rs.getInt(6),
                    Receipt.Prior.get(rs.getInt(7)))
            );
        }
    }

    @Override
    public List<Receipt> getAll() {

        List<Receipt> out = new ArrayList<>();
        try {

            ResultSet rs = sendQuery("SELECT * FROM receipts");
            addResultsToList(rs, out);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return out;
    }

    @Override
    public Receipt getEntityById(Long id) {

        try {
            ResultSet rs = sendQuery("SELECT * FROM receipts WHERE id = " + id);

            if (rs.next())
                return new Receipt(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getLong(3),
                    rs.getLong(4),
                    rs.getDate(5),
                    rs.getInt(6),
                    Receipt.Prior.get(rs.getInt(7))
                );

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }



    @Override
    public void update(Receipt entity) {

        try {

            sendQuery("UPDATE receipts SET "
                    + "description = '" + entity.getDescription()
                    + "', doctorID = " + entity.getDoctorID()
                    + ", patientID = " + entity.getPatientID()
                    + ", creationDate = DATE '" + (new SimpleDateFormat("yyyy-MM-dd").format(entity.getCreationDate()))
                    + "', validity = " + entity.getValidity()
                    + ", priority = " + entity.getPrior().ordinal()
                    + " WHERE id = " + entity.getId()
            );

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public boolean delete(Long id) {
        try {

            sendQuery("DELETE FROM receipts WHERE id = " + id);
            return true;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean create(Receipt entity) {

        try {
            sendQuery("INSERT INTO receipts (description, doctorID, patientID, creationDate, validity, priority) values ('"
                    + entity.getDescription() + "', "
                    + entity.getDoctorID() + ", "
                    + entity.getPatientID() + ", DATE '"
                    + (new SimpleDateFormat("yyyy-MM-dd").format(entity.getCreationDate())) + "', "
                    + entity.getValidity() + ", "
                    + entity.getPrior().ordinal() + ")"
            );
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;

        }
    }


    public List<Receipt> filterByDescription(String excerpt){

        List<Receipt> out = new ArrayList<>();

        try {

            ResultSet rs = sendQuery("SELECT * FROM receipts WHERE " +
                    "description LIKE '" + excerpt + "'");

            addResultsToList(rs,out);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return out;
    }

    public List<Receipt> filterByDoctor(long doctorID){

        List<Receipt> out = new ArrayList<>();

        try {
            ResultSet rs = sendQuery("SELECT * FROM receipts WHERE doctorID = " + doctorID);

            addResultsToList(rs, out);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return out;
    }

    public List<Receipt> filterByPatient(long patientID) {

        List<Receipt> out = new ArrayList<>();

        try {

            ResultSet rs = sendQuery("SELECT * FROM receipts WHERE " +
                    "patientID = " + patientID);

            addResultsToList(rs,out);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return out;
    }

    public List<Receipt> filterByPrior(Receipt.Prior prior){

        List<Receipt> out = new ArrayList<>();

        try {

            ResultSet rs = sendQuery("SELECT * FROM receipts WHERE " +
                    "priority = " + prior.ordinal());

            addResultsToList(rs, out);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return out;
    }
}
