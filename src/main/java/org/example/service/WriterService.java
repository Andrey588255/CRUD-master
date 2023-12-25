package org.example.service;

import org.example.model.Label;
import org.example.model.Writer;
import org.example.model.Post;
import org.example.repository.PostRepository;
import org.example.repository.WriterRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class WriterService {
    private final WriterRepository writerRepository;
    private final PostRepository postRepository;

    public Writer saveWithPosts( Writer writer, List<Long> postsId ) {
        List<Post> postsToSave = new ArrayList<>();
        for (Long id : postsId) {
            postRepository.getById(id).ifPresent((Consumer<Label>) postsToSave);
            writer.setPosts(postsToSave);
            return writerRepository.save(writer);
        }
        return writer;
    }
}