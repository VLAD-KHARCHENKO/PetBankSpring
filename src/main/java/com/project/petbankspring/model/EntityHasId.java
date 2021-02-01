package com.project.petbankspring.model;

public interface EntityHasId {
    Long getId();

    void setId(Long id);

    boolean isNew();
}
