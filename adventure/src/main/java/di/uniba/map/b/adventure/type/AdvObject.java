/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Francesco
 */
public class AdvObject {

    private final int id;

    private String name;

    private String description;

    private String contents;

    private Set<String> alias;

    private boolean openable = false;

    private boolean pickupable = true;

    private boolean pushable = false;

    private boolean open = false;

    private boolean push = false;

    private boolean creature = false;

    private boolean living = false;

    private boolean readable = false;

    /**
     *
     * @param id
     */
    public AdvObject(int id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param name
     */
    public AdvObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     */
    public AdvObject(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param alias
     */
    public AdvObject(int id, String name, String description, Set<String> alias) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alias = alias;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public boolean isOpenable() {
        return openable;
    }

    /**
     *
     * @param openable
     */
    public void setOpenable(boolean openable) {
        this.openable = openable;
    }

    /**
     *
     * @return
     */
    public boolean isPickupable() {
        return pickupable;
    }

    /**
     *
     * @param pickupable
     */
    public void setPickupable(boolean pickupable) {
        this.pickupable = pickupable;
    }

    /**
     *
     * @return
     */
    public boolean isPushable() {
        return pushable;
    }

    /**
     *
     * @param pushable
     */
    public void setPushable(boolean pushable) {
        this.pushable = pushable;
    }

    /**
     *
     * @return
     */
    public boolean isOpen() {
        return open;
    }

    /**
     *
     * @param open
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     *
     * @return
     */
    public boolean isPush() {
        return push;
    }

    /**
     *
     * @param push
     */
    public void setPush(boolean push) {
        this.push = push;
    }

    /**
     *
     * @return
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     *
     * @param alias
     */
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    /**
     *
     * @param alias
     */
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public boolean isCreature() { return creature; }

    /**
     *
     * @param creature
     */
    public void setCreature(boolean creature) { this.creature = creature; this.living = true; }

    /**
     *
     * @return
     */
    public boolean isLiving() { return living; }

    /**
     *
     * @param living
     */
    public void setLiving(boolean living) { this.living = living; }

    /**
     *
     * @return readable
     */
    public boolean isReadable() { return this.readable; }

    /**
     *
     * @param readable
     */
    public void setReadable(boolean readable) { this.readable = readable; }

    /**
     *
     * @param contents
     */
    public void setContents(String contents) { this.contents = contents; }

    /**
     *
     * @return contents
     */
    public String getContents() { return this.contents; }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
    /*@Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }*/

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdvObject other = (AdvObject) obj;
        return id == other.id;
    }
}
