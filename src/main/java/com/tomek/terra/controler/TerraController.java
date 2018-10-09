package com.tomek.terra.controler;

import com.tomek.terra.model.Terra;
import com.tomek.terra.service.TerraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TerraController {
    @Autowired
    TerraService terraService;

    public TerraController(TerraService terraService)
    {
        this.terraService=terraService;
    }

    @GetMapping(value = "measurement/{measurementId}")
    public Optional<Terra> getMeasurement(@PathVariable Long measurementId) {
        return terraService.getMeasurement(measurementId);
    }

    @PostMapping(value = "measurement")
    public void postMeasurement(@RequestBody Terra input)
    {
        terraService.addMeasurement(input);
    }
}
