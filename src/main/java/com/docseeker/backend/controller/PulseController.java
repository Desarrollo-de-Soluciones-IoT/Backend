package com.docseeker.backend.controller;

import com.docseeker.backend.model.Pulse;
import com.docseeker.backend.repository.PulseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pulse")
@CrossOrigin()
public class PulseController {
    private final PulseRepository repository;

    public PulseController(PulseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Pulse> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Pulse findById(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Pulse pulse) {
        repository.save(pulse);
    }
}
