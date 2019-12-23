package com.example.dao;

import com.example.model.Setor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.NamingException;
import org.springframework.stereotype.Repository;

@Repository
public class SetorDAO extends DAO<Setor> {

    private static final String INSERT_SQL = "INSERT INTO setor (nome, sigla) VALUES (?,?)";
    private static final String SELECT_SQL = "SELECT * FROM setor";
    private static final String UPDATE_SQL = "UPDATE setor SET nome = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM setor WHERE id = ?";

    @Override
    public Setor create(Setor s) {
        Setor setor = null;

        try (Connection c = super.getConnection();
                PreparedStatement stmt = c.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, s.getNome());
            stmt.setString(2, s.getSigla());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {

                    setor = s;
                    int id = rs.getInt("id");
                    setor.setId(id);
                }
            }

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        return setor;
    }

    @Override
    public ArrayList<Setor> read() {
        ArrayList<Setor> data = new ArrayList();

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
    public boolean update(Setor t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Setor t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Setor toObject(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String sigla = rs.getString("sigla");

        Setor s = new Setor();
        s.setId(id);
        s.setNome(nome);
        s.setSigla(sigla);

        return s;
    }

}
