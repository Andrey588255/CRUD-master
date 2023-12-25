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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("id: " + Id + " ; first name: " + firstName + " " +
                "; last name: " + lastName + " ; status: " + status + "; posts: [ ");
        if (posts != null) {
            for (int i = 0; i < posts.size(); i++) {
                sb.append(posts.get(i));
                if (i != posts.size() - 1) {
                    sb.append(" , ");
                }
            }
        }
        sb.append(" ]");
        return sb.toString();
    }
}