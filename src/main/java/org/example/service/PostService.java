package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Label;
import org.example.model.Post;
import org.example.repository.LabelRepository;
import org.example.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final LabelRepository labelRepository;

    public Post saveWithLabels(Post post, List<Long> labelsId) {
        List<Label> labelsToSave = new ArrayList<>();
        for (Long id: labelsId) {
            labelRepository.getById(id).ifPresent(labelsToSave::add);
        }
        post.setLables(labelsToSave);
        return postRepository.save(post);
    }
}