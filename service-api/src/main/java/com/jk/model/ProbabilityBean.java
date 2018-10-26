package com.jk.model;

public class ProbabilityBean {
    private String id;

    private String probability;

    private String name;

    //临时字段
    private String linshi;

    public void setId(String id) {
        this.id = id;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLinshi(String linshi) {
        this.linshi = linshi;
    }

    public String getId() {
        return id;
    }

    public String getProbability() {
        return probability;
    }

    public String getName() {
        return name;
    }

    public String getLinshi() {
        return linshi;
    }

    @Override
    public String toString() {
        return "ProbabilityBean{" +
                "id='" + id + '\'' +
                ", probability='" + probability + '\'' +
                ", name='" + name + '\'' +
                ", linshi='" + linshi + '\'' +
                '}';
    }
}
