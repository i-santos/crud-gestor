//package com.example.dao;
//
//import com.example.model.Gestor;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import org.flywaydb.core.internal.jdbc.JdbcTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class GestorDAOTest {
//
//    private static final String INSERT_SQL = "INSERT INTO crud_gestor.gestor (nome, matricula, data_nascimento, id_setor) VALUES (?,?,?,?)";
//    private static final String SELECT_SQL = "SELECT * FROM crud_gestor.gestor";
//    private static final String UPDATE_SQL = "UPDATE crud_gestor.gestor SET nome = ? WHERE id = ?";
//    private static final String DELETE_SQL = "DELETE FROM crud_gestor.gestor WHERE id = ?";
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public GestorDAOTest(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public boolean insert(Gestor g) {
//        boolean success = false;
//        try {
//            int rowsAffected = jdbcTemplate.queryForInt(INSERT_SQL,
//                    g.getNome(),
//                    g.getMatricula(),
//                    g.getDataNascimento().toString(),
//                    Integer.toString(g.getSetor().getId())
//            );
//            
//            success = rowsAffected > 0;
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return success;
//    }
//
//    public List<Gestor> readAll() {
//        return new ArrayList();
//    }
//
//    public boolean update(Gestor g) {
//        return false;
//    }
//
//    public boolean delete(Gestor g) {
//        return false;
//    }
//
//}
