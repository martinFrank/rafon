package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class MapArea extends AbstractEntity {

    @NotEmpty
    private String name = "";

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            name = "map_area_connection",
            joinColumns = @JoinColumn(name = "parent_map_area_id"),
            inverseJoinColumns = @JoinColumn(name = "sub_map_area_id"))
    private Set<MapArea> subMapAreas;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            name = "map_area_action_mapping",
            joinColumns = @JoinColumn(name = "map_area_id"),
            inverseJoinColumns = @JoinColumn(name = "action_id"))
    private Set<MapAreaAction> areaActions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<MapArea> getSubMapAreas() {
        return subMapAreas;
    }

    public void setSubMapAreas(Set<MapArea> subMapAreas) {
        this.subMapAreas = subMapAreas;
    }

    public Set<MapAreaAction> getAreaActions() {
        return areaActions;
    }

    public void setAreaActions(Set<MapAreaAction> areaActions) {
        this.areaActions = areaActions;
    }
}
