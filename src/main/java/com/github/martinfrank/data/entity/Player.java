package com.github.martinfrank.data.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Player {

    @NotEmpty
    private String displayName = "";

    @NotNull
    @OneToOne
    private User user;


}
