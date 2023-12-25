package org.example.model;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class Label extends Base{
    private String name;

    public String toString( Long id ) {
        return "id: " + id + "; name: " + name + "; status: " + status + ";";
    }

    public void setFirstName(String updatedFirstName) {
    }

    public void setLastName(String updatedLastName) {
    }

    public void setTitle(String updatedTitle) {
    }

    public void setContent(String updatedContent) {
    }
}