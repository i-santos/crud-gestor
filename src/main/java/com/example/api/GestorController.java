package com.example.api;

import com.example.model.Gestor;
import com.example.service.GestorService;
import com.example.wrapper.GestorWrapper;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/gestor")
public class GestorController {

    private final GestorService gestorService;

    @Autowired
    public GestorController(GestorService gestorService) {
        this.gestorService = gestorService;
    }

    @GetMapping()
    public List<Gestor> list() {
        return gestorService.getAllGestor();
    }

    @GetMapping("/{id}")
    public Gestor get(@PathVariable String id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Gestor input) {
        return ResponseEntity.ok(gestorService.updateGestor(input));
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Gestor input) {
        return ResponseEntity.ok(gestorService.addGestor(input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Gestor g = new Gestor();
        g.setId(Integer.parseInt(id));
        return ResponseEntity.ok(gestorService.deleteGestor(g));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody List<Gestor> input) {
        return ResponseEntity.ok(gestorService.deleteGestores(input));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }

}
