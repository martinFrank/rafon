package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class MapArea extends AbstractEntity {

    @NotEmpty
    private String mapAreaName = "";

    @ManyToMany // (fetch = FetchType.EAGER)
    @JoinTable(
            name = "map_area_connection",
            joinColumns = @JoinColumn(name = "parent_map_area_id"),
            inverseJoinColumns = @JoinColumn(name = "sub_map_area_id"))
    private Set<MapArea> subMapAreas;

    public String getMapAreaName() {
        return mapAreaName;
    }

    public void setMapAreaName(String mapAreaName) {
        this.mapAreaName = mapAreaName;
    }


    public Set<MapArea> getSubMapAreas() {
        return subMapAreas;
    }

    public void setSubMapAreas(Set<MapArea> subMapAreas) {
        this.subMapAreas = subMapAreas;
    }
}
