package com.ll;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class Quote {

    @Getter
    @Setter
    private  int id;

    @Getter
    @Setter
    private String comment;

    @Getter
    @Setter
    private String author;

    @JsonCreator
    public Quote(@JsonProperty("id") int id, @JsonProperty("comment") String comment, @JsonProperty("author") String author) {
        this.id = id;
        this.comment = comment;
        this.author = author;
    }
}