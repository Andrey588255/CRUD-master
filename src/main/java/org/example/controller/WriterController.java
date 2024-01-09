package org.example.controller;

import org.example.model.Label;
import org.example.model.Post;
import org.example.model.Status;
import org.example.model.Writer;
import org.example.repository.WriterRepository;
import org.example.repository.PostRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class WriterController {
    private final WriterRepository writerRepository;
    private final PostRepository postRepository;

    public Optional<Writer> get( Long id ) {
        return writerRepository.getById(id);
    }

    public boolean checkPostStatus( Long id ) {
        Optional<Post> post = postRepository.getById(id);
        return post.map(value -> value.getStatus().equals(Status.ACTIVE)).orElse(false);
    }

    public boolean checkIfWriterExists( Long id ) {
        return writerRepository.checkIfExists(id);
    }

    public void delete( Long id ) {
        writerRepository.deleteById(id);
    }

    public Writer saveWithPosts( Writer w, List<Long> postsId ) {
        return saveWithPosts(w, postsId);
    }

    public List<Writer> findAll() {
        return writerRepository.findAll().stream()
                .filter(p -> p.getStatus().equals(Status.ACTIVE))
                .collect(Collectors.toList());
    }

    public Writer saveWithPosts( Label label, List<Long> updatedPosts ) {
        return null;
    }
}

