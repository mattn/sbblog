package io.github.mattn.sbblog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="entry")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entry {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable=false)
    private String title;

    @Column(nullable=false)
    private String body;

    @Column(nullable=false, name = "created_at")
    public Date createdAt;

    public Entry(String title, String body) {
    	this.title = title;
    	this.body = body;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        createdAt = new Date();
    }
}
