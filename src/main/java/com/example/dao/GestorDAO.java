package com.example.dao;

import com.example.model.Gestor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import org.springframework.stereotype.Repository;

@Repository
public class GestorDAO extends DAO<Gestor> {

    private static final String INSERT_SQL = "INSERT INTO crud_gestor.gestor (nome) VALUES (?)";
    private static final String SELECT_SQL = "SELECT * FROM crud_gestor.gestor";
    private static final String UPDATE_SQL = "UPDATE crud_gestor.gestor SET nome = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM crud_gestor.gestor WHERE id = ?";

    @Override
    public boolean create(Gestor t) {
        boolean success = false;

        try (Connection c = super.getConnection();
                PreparedStatement stmt = c.prepareStatement(INSERT_SQL)) {

            stmt.setString(1, t.getNome());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                success = true;
            }

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public ArrayList<Gestor> read() {
        ArrayList<Gestor> data = new ArrayList<Gestor>();

        try (Connection c = super.getConnection();
                PreparedStatement stmt = c.prepareStatement(SELECT_SQL)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                data.add(toObject(rs));

            }

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public boolean update(Gestor t) {
        boolean success = false;

        try (Connection c = super.getConnection();
                PreparedStatement stmt = c.prepareStatement(UPDATE_SQL)) {

            stmt.setString(1, t.getNome());
            stmt.setInt(2, t.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                success = true;
            }

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public boolean delete(Gestor t) {
        boolean success = false;

        try (Connection c = super.getConnection();
                PreparedStatement stmt = c.prepareStatement(DELETE_SQL)) {

            stmt.setInt(1, t.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                success = true;
            }

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    protected Gestor toObject(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");

        Gestor g = new Gestor();
        g.setId(id);
        g.setNome(nome);

        return g;
    }

}
