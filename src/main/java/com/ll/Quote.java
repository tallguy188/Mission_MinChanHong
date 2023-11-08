package com.ll;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Quote {

    @Getter
    private  int id;

    @Getter
    @Setter
    private String comment;

    @Getter
    @Setter
    private String author;
}