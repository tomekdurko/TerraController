package com.tomek.terra.service;

import com.tomek.terra.model.Terra;
import com.tomek.terra.repository.TerraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TerraService {
    @Autowired
    private TerraRepository terraRepository;

    public TerraService(TerraRepository terraRepository)
    {
        this.terraRepository=terraRepository;
    }
    public Terra addMeasurement(Terra input)
    {
        return terraRepository.save(input);
    }
    public List<Terra> getAllMeasurment() { return terraRepository.findAll(); }
    public Optional<Terra> getMeasurement(Long id)
    {
        return terraRepository.findById(id);
    }
}
