package com.docseeker.backend.controller;

import com.docseeker.backend.model.Temperature;
import com.docseeker.backend.repository.TemperatureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/temperature")
@CrossOrigin()
public class TemperatureController {
    private final TemperatureRepository repository;
    private LocalDateTime currentTime;

    public TemperatureController(TemperatureRepository repository) {
        this.repository = repository;
        this.currentTime = LocalDateTime.now();
    }

    @GetMapping("")
    public List<Temperature> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Temperature findById(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Temperature temperature) {
        temperature.setTimestamp(currentTime.now().toString());
        repository.save(temperature);
    }
}
