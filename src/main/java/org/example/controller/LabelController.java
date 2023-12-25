package org.example.controller;

import org.example.model.Label;
import org.example.model.Status;
import lombok.RequiredArgsConstructor;
import org.example.repository.LabelRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RequiredArgsConstructor
public class LabelController {
    private final LabelRepository labelRepository;

    public Optional<Label> get( Long id) {
        return labelRepository.getById(id);
    }

    public void delete(Long id) {
        labelRepository.deleteById(id);
    }

    public Label save(Label l) { return labelRepository.save(l); }

    public List<Label> findAll() {
        return labelRepository.findAll().stream()
                .filter(l -> l.getStatus().equals(Status.ACTIVE))
                .collect(Collectors.toList());
    }
}