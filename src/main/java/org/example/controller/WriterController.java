package org.example.controller;

import org.example.model.Label;
import org.example.model.Status;
import org.example.model.Writer;
import org.example.repository.WriterRepository;
import org.example.repository.PostRepository;
import org.example.service.WriterService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class WriterController {
    private final WriterService writerService;
    private final WriterRepository writerRepository;
    private final PostRepository postRepository;

    public Optional<Label> get( Long id ) {
        return writerRepository.getById(id);
    }

    public boolean checkPostStatus( Long id ) {
        Optional<Label> post = postRepository.getById(id);
        return post.map(value -> value.getStatus().equals(Status.ACTIVE)).orElse(false);
    }

    public boolean checkIfWriterExists( Long id ) {
        return writerRepository.checkIfExists(id);
    }

    public void delete( Long id ) {
        writerRepository.deleteById(id);
    }

    public Writer saveWithPosts( Writer w, List<Long> postsId ) {
        return writerService.saveWithPosts(w, postsId);
    }

    public List<Label> findAll() {
        return postRepository.findAll().stream()
                .filter(p -> p.getStatus().equals(Status.ACTIVE))
                .collect(Collectors.toList());
    }

    public Writer saveWithPosts( Label label, List<Long> updatedPosts ) {
        return null;
    }
}

