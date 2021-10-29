package com.github.martinfrank.data.entity;

import com.github.martinfrank.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Item extends AbstractEntity {

    @NotEmpty
    private String name = "";

//    @ManyToMany (fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "item_granted_map_area_access",
//            joinColumns = @JoinColumn(name = "quest_item_id"),
//            inverseJoinColumns = @JoinColumn(name = "map_area_id"))
//    private Set<MapArea> grantedAccess;


    @OneToMany(mappedBy = "item")
    Set<PlayerItem> inventory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<MapArea> getGrantedAccess() {
//        return grantedAccess;
//    }
//
//    public void setGrantedAccess(Set<MapArea> grantedAccess) {
//        this.grantedAccess = grantedAccess;
//    }

}
