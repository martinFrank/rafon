package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class QuestItem extends AbstractEntity {

    @NotEmpty
    private String name = "";


    private Set<MapArea> grantedAccess;

}
