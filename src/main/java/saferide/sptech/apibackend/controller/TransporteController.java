package saferide.sptech.apibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saferide.sptech.apibackend.model.Transporte;
import saferide.sptech.apibackend.repositories.TransporteRepository;

@RestController
@RequestMapping("/transportes")
public class TransporteController {
    @Autowired
    private TransporteRepository transporteRepository;

    @PostMapping
    public ResponseEntity<Transporte> criar(@RequestBody Transporte transporte) {
        Transporte saveTransporte = transporteRepository.save(transporte);
        return ResponseEntity.status(201).body(saveTransporte);
    }

}
