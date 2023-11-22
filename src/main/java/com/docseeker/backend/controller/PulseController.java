package com.docseeker.backend.controller;

import com.docseeker.backend.model.Pulse;
import com.docseeker.backend.repository.PulseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/pulse")
@CrossOrigin()
public class PulseController {
    private final PulseRepository repository;
    private LocalDateTime currentTime;

    public PulseController(PulseRepository repository) {
        this.repository = repository;
        this.currentTime = LocalDateTime.now();
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
        pulse.setTimestamp(currentTime.now().toString());;
        repository.save(pulse);
    }
}
