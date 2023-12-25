package org.example.repository.gson;

import org.example.model.Label;
import org.example.model.Post;
import org.example.model.Status;
import org.example.repository.LabelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.gson.reflect.TypeToken;
import org.example.util.FileUtil;

public class GsonLabelRepository implements LabelRepository {
    private final String filePath = "labels.json";
    private String id;
    private Label label;
    private Label label1;


    private List<Label> readLabelsFromFile() {
        return FileUtil.readFromFile(filePath, new TypeToken<List<Label>>() {
        }.getType());
    }

    private void writeLabelsToFile(List<Label> labels) {
        FileUtil.writeToFile(filePath, labels);
    }

    private Long generateIncrementedId(List<Label> labels) {
        boolean seen = false;
        long best = 0;
        for (Label label : labels) {
            long id = Long.parseLong(String.valueOf(label.getId()));
            if (!seen || id > best) {
                seen = true;
                best = id;
            }
        }
        return (seen ? best : 0) + 1;
    }


    public Optional<Label> getById( Long id) {
        return findAll().stream()
                .filter(label -> label.getId().equals(id))
                .findFirst();
    }

    @Override
    public Label save(Label label) {
        List<Label> labels = readLabelsFromFile();
        if (label.getId() == null) {
            label.setId(generateIncrementedId(labels));
            labels.add(label);
        } else {
            labels = labels.stream()
                    .map(l -> Objects.equals(l.getId(), label.getId()) ? label : l)
                    .collect(Collectors.toList());
        }
        writeLabelsToFile(labels);
        return label;
    }

    @Override
    public void deleteById(Long id) {
        List<Label> labels = new ArrayList<>();
        for (Label l : readLabelsFromFile()) {
            if (l.getId().equals(id)) {
                l.setStatus(Status.DELETED);
            }
            boolean add = labels.add(l);
        }
        writeLabelsToFile(labels);
    }

    @Override
    public java.awt.Label save( java.awt.Label entity ) {
        return null;
    }

    @Override
    public Post save( Post entity ) {
        return null;
    }

    @Override
    public List<Label> findAll() {
        return readLabelsFromFile();
    }

    @Override
    public boolean checkIfExists(Long id) {
        return readLabelsFromFile().stream()
                .anyMatch(l -> l.getId().equals(id));
    }
}

