package org.example.repository;

import org.example.model.Post;
import org.example.model.Writer;
import org.example.repository.GenericRepository;

import java.awt.*;

public interface WriterRepository extends GenericRepository<Label, Long> {
    Post save( Post entity );

    Writer save( Writer writer);
}
