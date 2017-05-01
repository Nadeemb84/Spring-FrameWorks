package com.elderstudios.service;

import com.elderstudios.domain.TechSEntry;
import com.elderstudios.domain.TechSEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechSService {

    @Autowired
    protected TechSEntryRepository techSEntryRepository;

    public List<TechSEntry> findAll() {
        return techSEntryRepository.findAll();
    }

    public TechSEntry save(TechSEntry entry) {
        return techSEntryRepository.save(entry);
    }

    public void delete(Long id) {
        techSEntryRepository.delete(id);
    }

    public TechSEntry findOne(Long id) {
        return techSEntryRepository.findOne(id);
    }
}
