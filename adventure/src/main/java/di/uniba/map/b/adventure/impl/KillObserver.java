/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;


import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.GameObserver;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Room;
/**
 * Observer implementation for handling "kill" commands within the game.
 * This observer checks if the player can kill a monster in the current room,
 * based on the presence of a weapon in the player's inventory and the monster's existence.
 */

public class KillObserver implements GameObserver {

    /**
     * Processes a "kill" command, determining if the player successfully kills a monster in the current room.
     * The method checks if the player is in the correct room, if the monster is alive, and if the player has the required weapon.
     * If the conditions are met, the monster is killed, and a success message is returned.
     * Otherwise, appropriate messages are returned based on the failure condition (e.g., no monster, no weapon).
     *
     * @param description The current state of the game, including rooms, inventory, and game objects.
     * @param parserOutput The parsed output of the player's command, including the command type and arguments.
     * @param window The game window where output messages are displayed.
     * @return A string message indicating the outcome of the "kill" command.
     */
    @Override
    public String update(GameDescription description, ParserOutput parserOutput, Window window) {
        StringBuilder msg = new StringBuilder();
        if (parserOutput.getCommand().getType() == CommandType.KILL) {
            Room currentRoom = description.getCurrentRoom();
            if (currentRoom.getName().equalsIgnoreCase("Centrale operativa")) {
                if (currentRoom.isMonsterAlive()) {
                    boolean hasWeapon = description.getInventory().stream().anyMatch(obj -> obj.getId() == 8); // ID del arma
                    if (hasWeapon) {
                        AdvObject alieno = currentRoom.getObjectByName("Cyborg");
                        if (alieno != null) {
                            msg.append("Hai ucciso il Cyborg con la tua arma! OSSERVI qualcosa  al suo interno...");
                            currentRoom.removeObject(alieno);
                            currentRoom.setMonsterAlive(false); // Imposta il mostro come morto
                            currentRoom.setLook("Ti trovi ancora nella Centrale operativa il cuore della base segreta \nNoti che il Robot stava proteggendo una ad EST che ora puoi aprire  \n" );
                        } else {
                            msg.append("NON c'è nessun alieno qui da attaccare.");
                        }
                    } else {
                        msg.append("Non hai nessuna arma per uccidere il Cyboorg! Devi trovare una pistola .");
                        // Puoi decidere cosa fare in questo caso, per esempio mostrare un messaggio diverso
                    }
                } else {
                    msg.append("Hai già ucciso il Cyborg qui.");
                }
            } else {
                msg.append("Non c'è nessun Cyborg qui da uccidere.");
            }
        }
        return msg.toString();
    }
}

