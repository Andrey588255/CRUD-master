package org.example.repository.gson;

import com.google.gson.reflect.TypeToken;
import org.example.model.Label;
import org.example.model.Post;
import org.example.model.Status;
import org.example.repository.PostRepository;
import org.example.util.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class GsonPostRepository implements PostRepository {
    private final String filePath = "posts.json";

    private List<Post> readPostsFromFile() {
        return FileUtil.readFromFile(filePath, new TypeToken<List<Post>>(){}.getType());
    }

    private void writePostsToFile(List<Post> posts) { FileUtil.writeToFile(filePath, posts);}

    private Long generateIncrementedId(List<Post> posts) {
        return posts.stream().mapToLong(Post::getId).max().orElse(0) + 1;
    }

    public Optional<Post> getById( Long id) {
        return readPostsFromFile().stream()
                .filter(label -> label.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(Long id) {
        List<Post> posts = new ArrayList<>();
        for (Post l : readPostsFromFile()) {
            if (l.getId().equals(id)) {
                l.setStatus(Status.DELETED);
            }
            boolean add = posts.add(l);
        }
        writeLabelsToFile(posts);
    }

    private void writeLabelsToFile( List<Post> labels ) {
    }

    @Override
    public List<Post> findAll() {
        return readPostsFromFile();
    }

    @Override
    public boolean checkIfExists(Long id) {
        return readPostsFromFile().stream()
                .anyMatch(l -> Objects.equals(l.getId(), id));
    }

    @Override
    public Post save(Post post) {
        List<Post> posts = readPostsFromFile();
        if (Objects.isNull(post.getId())) {
            post.setId(generateIncrementedId(posts));
            posts.add(post);
        } else {
            posts = posts.stream()
                    .map(p -> Objects.equals(p.getId(), post.getId()) ? post : p)
                    .collect(Collectors.toList());
        }
        writePostsToFile(posts);
        return post;
    }

    protected abstract Label readLabelsFromFile();
}
