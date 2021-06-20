package com.centime.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParentIdColorResponse {

    String name;
    List<Subclasses> subclassesList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subclasses> getSubclassesList() {
        return subclassesList;
    }

    public void setSubclassesList(List<Subclasses> subclassesList) {
        this.subclassesList = subclassesList;
    }
}
