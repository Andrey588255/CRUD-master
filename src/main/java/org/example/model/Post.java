package org.example.model;

import java.util.List;

public abstract class Post extends Base {
    private String title;
    private String content;
    private String id;
    private List<Label> labels;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("id: " + id + "; title: " + title + ";" +
                " content: " + content + "; status: " + status + "; labels id: [ ");
        if (labels != null) {
            for (int i = 0; i < labels.size(); i++) {
                sb.append("[ ");
                sb.append(labels.get(i));
                if (i != labels.size() - 1) {
                    sb.append(" ] , ");
                }
            }
            sb.append(" ]");
        }
        sb.append(" ]");
        return sb.toString();
    }

    public void setTitle( String updatedTitle ) {
    }

    public void setContent( String updatedContent ) {
    }

    public void setLables( List<Label> labelsToSave ) {
    }
}

