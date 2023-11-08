package com.ll;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


public class Quote {

    @Getter
    @Setter
    private  int id;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private String author;

    @JsonCreator
    public Quote(@JsonProperty("id") int id, @JsonProperty("content") String content, @JsonProperty("author") String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
}