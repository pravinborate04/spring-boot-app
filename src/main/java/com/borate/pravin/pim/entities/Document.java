package com.borate.pravin.pim.entities;

import com.borate.pravin.pim.helper.Constants;

import javax.persistence.*;

/**
 * @author Pravin Borate
 * 11/03/21
 */
@Entity(name = Constants.DOCUMENT)
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "document_url")
    private String documentUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }
}
