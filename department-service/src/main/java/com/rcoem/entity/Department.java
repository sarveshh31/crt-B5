package com.rcoem.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deptName;
    private String head;

    public Department() {
        super();
    }

    public Department(Long id, String deptName, String location, String head) {
        super();
        this.id = id;
        this.deptName = deptName;
        this.head = head;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return "Department [id=" + id + ", deptName=" + deptName + ", head=" + head + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptName, head, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Department other = (Department) obj;

        return Objects.equals(deptName, other.deptName)
                && Objects.equals(head, other.head)
                && Objects.equals(id, other.id);
    }
}