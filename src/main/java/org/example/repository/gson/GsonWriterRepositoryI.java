package org.example.repository.gson;

import org.example.model.Status;
import org.example.model.Writer;
import org.example.repository.WriterRepository;
import com.google.gson.reflect.TypeToken;
import org.example.util.FileUtil;

import java.util.*;
import java.util.stream.Collectors;

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

    public Optional<Writer> getById( Long id ) {
        return findAll().stream()
                .filter(label -> label.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById( Long id ) {
        List<Writer> writers = readWritersFromFile();
        for (Writer wr : writers) {
            if (wr.getId().equals(id)) {
                wr.setStatus(Status.DELETED);
            }
        }
        writeWritersToFile(writers);
    }

    @Override
    public List<Writer> findAll() {
        return readWritersFromFile();
    }

    @Override
    public boolean checkIfExists(Long id) {
        return readWritersFromFile().stream()
                .anyMatch(l -> l.getId().equals(id));
    }

    @Override
    public Writer save( Writer writerToSave ) {
        List<Writer> writers = readWritersFromFile();
        if (Objects.isNull(writerToSave.getId())) {
            writerToSave.setId(generateIncrementedId(writers));
            writers.add(writerToSave);
        } else {
            writers = writers.stream()
                    .map(exisitingWriter -> {
                        if(Objects.equals(exisitingWriter.getId(), writerToSave.getId())) {
                            return writerToSave;
                        }
                        return exisitingWriter;
                    })
                    .collect(Collectors.toList());
        }
        writeWritersToFile(writers);
        return writerToSave;
    }
}

