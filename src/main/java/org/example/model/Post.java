package org.example.model;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends Base {
    private String title;
    private String content;
    private List<Label> labels;

    public String toString( Long id ) {
        return null;
    }
}

