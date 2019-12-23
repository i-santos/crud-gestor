package com.example.dao;

import com.example.model.Gestor;
import com.example.model.Setor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.naming.NamingException;
import org.springframework.stereotype.Repository;

@Repository
public class GestorDAO extends DAO<Gestor> {

    private static final String INSERT_SQL = "INSERT INTO gestor (nome, matricula, data_nascimento, id_setor) VALUES (?,?,?,?)";
    private static final String SELECT_SQL = "SELECT g.id as g_id, "
            + "g.nome as g_nome, "
            + "g.matricula as g_matricula, "
            + "g.data_nascimento as g_data_nascimento, "
            + "g.id_setor as g_id_setor, "
            + "s.id as s_id, "
            + "s.nome as s_nome, "
            + "s.sigla as s_sigla "
            + "FROM gestor g INNER JOIN setor s ON g.id_setor = s.id";
    private static final String UPDATE_SQL = "UPDATE gestor SET nome = ?, matricula = ?, data_nascimento = ?, id_setor = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM gestor WHERE id = ?";

    @Override
    public Gestor create(Gestor t) {
        Gestor g = null;

        try (Connection c = super.getConnection();
                PreparedStatement stmt = c.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, t.getNome());
            stmt.setString(2, t.getMatricula());
            stmt.setDate(3, java.sql.Date.valueOf(t.getDataNascimento()));
            stmt.setInt(4, t.getSetor().getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    g = t;
                    int id = rs.getInt("id");
                    g.setId(id);
                }
            }

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        return g;
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
            stmt.setString(2, t.getMatricula());
            stmt.setDate(3, java.sql.Date.valueOf(t.getDataNascimento()));
            stmt.setInt(4, t.getSetor().getId());
            stmt.setInt(5, t.getId());

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
        int id = rs.getInt("g_id");
        String nome = rs.getString("g_nome");
        String matricula = rs.getString("g_matricula");
        LocalDate dataNascimento = rs.getDate("g_data_nascimento").toLocalDate();
        int idSetor = rs.getInt("g_id_setor");
        String nomeSetor = rs.getString("s_nome");
        String sigla = rs.getString("s_sigla");

        Gestor g = new Gestor();
        g.setId(id);
        g.setNome(nome);
        g.setMatricula(matricula);
        g.setDataNascimento(dataNascimento);
        g.setSetor(new Setor());
        g.getSetor().setId(idSetor);
        g.getSetor().setNome(nomeSetor);
        g.getSetor().setSigla(sigla);

        return g;
    }

}
