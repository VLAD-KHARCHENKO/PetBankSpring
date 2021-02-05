package com.project.petbankspring.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
@Access(value = AccessType.FIELD)
public class BaseEntity implements EntityHasId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Access(value = AccessType.PROPERTY)
    private Long id;

    public BaseEntity() {
    }

    protected BaseEntity(Long id) {
        this.id = id;
    }



    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return getId() == null || getId() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
