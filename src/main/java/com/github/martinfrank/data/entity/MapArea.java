package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class MapArea extends AbstractEntity {

    @NotEmpty
    private String mapAreaName = "";

    public String getMapAreaName() {
        return mapAreaName;
    }

    public void setMapAreaName(String mapAreaName) {
        this.mapAreaName = mapAreaName;
    }

    @ManyToMany
    private Set<QuestItem> accessGiver;

    public Set<QuestItem> getAccessGiver() {
        return accessGiver;
    }

    public void setAccessGiver(Set<QuestItem> accessGiver) {
        this.accessGiver = accessGiver;
    }

}
