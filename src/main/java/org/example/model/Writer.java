package org.example.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class Writer extends Base {
    private String firstName;
    private String lastName;
    private List<Post> posts;


}
