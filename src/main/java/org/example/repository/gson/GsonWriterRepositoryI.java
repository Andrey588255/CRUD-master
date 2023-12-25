package org.example.repository.gson;

import org.example.model.Label;
import org.example.model.Post;
import org.example.model.Status;
import org.example.model.Writer;
import org.example.repository.WriterRepository;
import com.google.gson.reflect.TypeToken;
import org.example.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
;
import java.util.Optional;


public class GsonWriterRepositoryI implements WriterRepository {
    private final String filePath = "writers.json";

    private List<Writer> readWritersFromFile() {
        return FileUtil.readFromFile(filePath, new TypeToken<List<Writer>>(){}.getType());
    }


    private void writeWritersToFile(List<Writer> writers) {
        FileUtil.writeToFile(filePath, writers);
    }

    private Long generateIncrementedId(List<Writer> writers) {
        boolean seen = false;
        long best = 0;
        for (Writer writer : writers) {
            long id = Long.parseLong(String.valueOf(writer.getId()));
            if (!seen || id > best) {
                seen = true;
                best = id;
            }
        }
        return (seen ? best : 0) + 1;
    }


    public Optional<Label> getById( Long id ) {
        return findAll().stream()
                .filter(label -> label.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById( Long id ) {
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

    private Label[] readLabelsFromFile() {
        return new Label[0];
    }

    private void writeLabelsToFile( List<Label> labels ) {
    }

    @Override
    public Post save( Post entity ) {
        return null;
    }

    @Override
    public List<Label> findAll() {
        return List.of(readLabelsFromFile());
    }

    @Override
    public boolean checkIfExists(Long id) {
        return Arrays.stream(readLabelsFromFile())
                .anyMatch(l -> l.getId().equals(id));
    }

    @Override
    public Writer save( Writer writer ) {
        return null;
    }
}

