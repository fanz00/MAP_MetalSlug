package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.GameObserver;
import di.uniba.map.b.adventure.type.Room;

/**
 * Observer implementation for handling movement commands within the game.
 * Blocks eastward movement from "Centrale operativa" if the monster is still alive.
 */
public class MoveObserver implements GameObserver {

    @Override
    public String update(GameDescription description, ParserOutput parserOutput, Window window) {
        Room currentRoom = description.getCurrentRoom();

        switch (parserOutput.getCommand().getType()) {
            case NORD:
                if (currentRoom.getNorth() != null) {
                    description.setCurrentRoom(currentRoom.getNorth());
                } else {
                    return "Da quella parte non si può andare c'è un campo minato!\nNon hai ancora acquisito i poteri per poter volare...";
                }
                break;

            case SOUTH:
                if (currentRoom.getSouth() != null) {
                    description.setCurrentRoom(currentRoom.getSouth());
                } else {
                    return "Da quella parte non si può andare c'è un muro di filo spinato!...";
                }
                break;

            case EAST:
                // BLOCCO SOLO IL MOVIMENTO VERSO EST SE IL CYBORG È ANCORA VIVO
                if (currentRoom.getName().equalsIgnoreCase("Centrale operativa") && currentRoom.isMonsterAlive()) {
                    return " Il Cyborg ti blocca la strada verso EST! Devi ucciderlo prima di procedere.";
                }
                if (currentRoom.getEast() != null) {
                    description.setCurrentRoom(currentRoom.getEast());
                } else {
                    return "Da quella parte non si può andare c'è un campo minato!\nNon hai ancora acquisito i poteri per poter volare...";
                }
                break;

            case WEST:
                if (currentRoom.getWest() != null) {
                    description.setCurrentRoom(currentRoom.getWest());
                } else {
                    return "Da quella parte non si può andare c'è un muro di filo spinato!...";
                }
                break;

            default:
                break;
        }

        return "";
    }
}
