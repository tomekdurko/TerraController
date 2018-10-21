package com.tomek.terra.controler;

import com.tomek.terra.model.Terra;
import com.tomek.terra.service.TerraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping(value = "measurement")
    public List<Terra> getMeasurementList()
    {
        return terraService.getAllMeasurment();
    }

    @PostMapping(value = "measurement")
    public void postMeasurement(@RequestBody Terra input)
    {
        terraService.addMeasurement(input);
    }


    @GetMapping("measurement/arduino")
    public Terra doMeasurement(@RequestParam double temperature, double humidity)
    {
        Terra measurement = new Terra(temperature, humidity);
        terraService.addMeasurement(measurement);
        return measurement;
    }
}
