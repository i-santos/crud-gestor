package com.example.service;

import com.example.dao.GestorDAO;
import com.example.model.Gestor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestorService {

    private final GestorDAO gestorDao;

    @Autowired
    public GestorService(GestorDAO gestorDao) {
        this.gestorDao = gestorDao;
    }

    public Gestor addGestor(Gestor g) {
        return gestorDao.create(g);
    }

    public List<Gestor> getAllGestor() {
        return gestorDao.read();
    }

    public boolean updateGestor(Gestor g) {
        return gestorDao.update(g);
    }

    public boolean deleteGestor(Gestor g) {
        return gestorDao.delete(g);
    }

    public boolean deleteGestores(List<Gestor> gs) {
        boolean error = false;
        for (Gestor g : gs) {
            if (!gestorDao.delete(g)) {
                error = true;
            }
        }
        return !error;
    }

}
