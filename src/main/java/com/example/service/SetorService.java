package com.example.service;

import com.example.dao.SetorDAO;
import com.example.model.Setor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetorService {
    
    private final SetorDAO setorDao;

    @Autowired
    public SetorService(SetorDAO setorDao) {
        this.setorDao = setorDao;
    }
    
    public Setor addSetor(Setor s) {
        return setorDao.create(s);
    }
    
    public List<Setor> getAllSetor() {
        return setorDao.read();
    }
    
    
}
