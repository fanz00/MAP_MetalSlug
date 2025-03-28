/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.type;

import di.uniba.map.b.adventure.GameDescription;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room within the adventure game. A room can have various properties such as a name, description,
 * visibility, connections to other rooms (north, south, east, west), and objects within it. Rooms can also contain
 * a monster, and have dynamic descriptions based on game state.
 */
public class Room {

    private final int id;
    private String name;
    private String description;
    private String look;
    private boolean visible = true;
    private Room south = null;
    private Room north = null;
    private Room east = null;
    private Room west = null;
    private final List<AdvObject> objects = new ArrayList<>();
    private boolean monsterAlive = true;
    private String dynamicLook;
    private final GameDescription game;  // Riferimento alla GameDescription

    /**
     * Constructs a Room with a specified ID and a reference to the game description.
     * This constructor initializes a room without a name or a static description.
     *
     * @param id The unique identifier for the room.
     * @param game The game description, providing access to global game state.
     */
    public Room(int id, GameDescription game) {
        this.id = id;
        this.game = game;
    }

    /**
     * Constructs a Room with a specified ID, name, description, and a reference to the game description.
     *
     * @param id The unique identifier for the room.
     * @param name The name of the room.
     * @param description The static description of the room.
     * @param game The game description, providing access to global game state.
     */
    public Room(int id, String name, String description, GameDescription game) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public Room getWest() {
        return west;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public List<AdvObject> getObjects() {
        return objects;
    }

    /**
     * Adds an object to the room. If the object's ID is not 6, it is added to the room's object list
     * and the global game object list.
     *
     * @param obj The object to add to the room.
     */
    public void addObject(AdvObject obj) {
        if(obj.getId()!=6)
            objects.add(obj);
        game.addObject(obj);  // Aggiungi l'oggetto alla lista allObjects
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        return this.id == other.id;
    }

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }

    public AdvObject getObject(int id) {
        for (AdvObject o : objects) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }

    /**
     * Generates a dynamic description of the room, including details about objects and their states.
     * For example, if a reinforced door object (ID 9) is open, this is reflected in the description.
     *
     * @return A string representing the dynamic look of the room.
     */
    public String getDynamicLook() {
        StringBuilder dynamiclook = new StringBuilder(look);

        for (AdvObject obj : objects) {
            if (obj instanceof AdvObjectContainer && obj.getId() == 9) { // porta rinforzata
                if (((AdvObjectContainer) obj).isOpen()) {
                    dynamiclook.append("\nLa porta rinforzata è aperta.");
                } else {
                    dynamiclook.append("\nLa porta rinforzata è chiusa.");
                }
            }
        }

        return dynamiclook.toString();
    }

    public AdvObject getObjectByName(String name) {
        for (AdvObject obj : objects) {
            if (obj.getName().equalsIgnoreCase(name)) {
                return obj;
            }
        }
        return null;
    }

    public boolean isMonsterAlive() {
        return monsterAlive;
    }

    public void setMonsterAlive(boolean alive) {
        this.monsterAlive = alive;
    }

    public void removeObject(AdvObject object) {
        objects.remove(object);
    }

    public String getDynamicLookmonster() {
        return dynamicLook != null ? dynamicLook : description;
    }

    public void setDynamicLook(String dynamicLook) {
        this.dynamicLook = dynamicLook;
    }
}
