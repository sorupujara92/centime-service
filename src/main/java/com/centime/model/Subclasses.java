package com.centime.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subclasses {
   String name;
   List<Subclasses> subclasses;

    public List<Subclasses> getSubclasses() {
        return subclasses;
    }

    public void setSubclasses(List<Subclasses> subclasses) {
        this.subclasses = subclasses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
