package io.github.mattn.sbblog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="customer")
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
    
    public Entry(String title, String body) {
    	this.title = title;
    	this.body = body;
    }
}
