/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Room;
import java.util.ArrayList;
import java.util.List;
import di.uniba.map.b.adventure.GameObservable;
import di.uniba.map.b.adventure.GameObserver;

/**
 * Main class for the MetalSlug game, extending GameDescription and implementing GameObservable.
 * This class initializes the game environment, including rooms, objects, and commands, and handles player moves.
 */
public class MetalSlug extends GameDescription implements GameObservable {

    private final List<GameObserver> observer = new ArrayList<>();
    private ParserOutput parserOutput;
    private final List<String> messages = new ArrayList<>();

    /**
     * Initializes the game environment using data from the provided database.
     * This includes setting up rooms, commands, objects, and observers.
     *
     * @throws Exception If there is an error accessing the database.
     */
    @Override
    public void init() throws Exception {

        messages.clear();
        //Commands
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getCommands().add(nord);
        Command iventory = new Command(CommandType.INVENTORY, "inventario");
        iventory.setAlias(new String[]{"inv"});
        getCommands().add(iventory);
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getCommands().add(sud);
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getCommands().add(est);
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getCommands().add(ovest);
        Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[]{"end", "fine", "abbandona", "muori", "ammazzati", "ucciditi", "suicidati", "exit", "basta"});
        getCommands().add(end);
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getCommands().add(look);
        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi"});
        getCommands().add(pickup);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{});
        getCommands().add(open);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi", "attiva" , "accendi" , "accedi"});
        getCommands().add(push);
        Command use = new Command(CommandType.USE, "usa");
        use.setAlias(new String[]{"utilizza", "combina"});
        getCommands().add(use);
        Command read = new Command(CommandType.READ, "leggi");
        read.setAlias(new String[]{"sfoglia"});
        getCommands().add(read);
        Command killMonster = new Command(CommandType.KILL, "uccidi");
        killMonster.setAlias(new String[]{"uccidi", "elimina", "distruggi" , "ammazza", "spara" ,"colpisci" ,"attacca"});
        getCommands().add(killMonster);
        //Rooms
        Room startRoom = new Room(0,"zona di sbarco", "sei hai appena atterato  nella base nemica", this);
        startRoom.setLook("Sembra esserci una spece di Carro Armato ...Aspetta ma è un METALSLUG!\n" +"Purtropppo sembre non essere attivo ,ha bisogno di qualcosa per funzionare ");
        Room bridgeRoom = new Room(1, "Pontile", "Ti Trovi sul un ponte di legno pericolante ", this);
        bridgeRoom.setLook("Sembra che questo ponte porti verso un senitiero ad SUD\n"+"Aspetta !! Sento delle voci provenire verso OVEST");
        Room trackRoom = new Room(2, "Sentiero", "Stai attento ci sono mine anti-uomo ovunque ", this);
        trackRoom.setLook("In lontananza verso SUD si intravede una base abbandonata...");
        Room baseRoom = new Room(3, "Ingresso Base","Sei  davanti all'ingresso della  base nemica  ", this);
        baseRoom.setLook("Sembra che sia abbandonata ... Ad OVEST si sente odore di polvere da sparo , invece \n"+ "a EST si intravede una enorme torre di cntrollo\n"+"A SUD invece noti una stanza nucleare...");
        Room towerRoom = new Room(4,"Torre di controllo","Ecco sei davanti alla torre di controllo . All'interno  possono esserci \n"+" veicoli militari e trappole ...stai attento ", this);
        towerRoom.setLook("Noti una luce rossa a EST che indica una porta nascota...");
        Room secretRoom = new Room(5, "Passaggio Segreto", "Hai trovato una porta segreta !\n"+"Bravo forse all'interno troverari qualcosa di utile per tornare a casa", this);
        secretRoom.setLook("Per fortuna la porta sembra essere rotta ... Chissà cosa nasconde ");
        Room prisonRoom = new Room(6, "Prigione", "Hai trovato una prigione e senti qualcuno al suo interno ", this);
        prisonRoom.setLook("Sembra che ci sia un  Hyakutaro (un tuo alleato di guerra) vuoi aprire la prigione ?\n" + "ha qualcosa che forse potrebbe esserti utile...");

        Room labRoom = new Room(7, "Reattore Nucleare", "Ti trovi all'interno di un Reattore Nucleare abbandomato... chissà cosa alimentava", this);
        labRoom.setLook("Sembra che qui  facevano esperimenti per creature Cyborg... \n" +"Ormai è dismesso da tempo non sembra molto utile");
        Room centralRoom = new Room(8, "Centrale operativa", "Sei riuscito ad entrare all'interno della porta segreta e ti trovi davanti\n"+" a un Robot .Ecco a cosa serviva il Reattore !Ad alimentare il Robot !", this);
        centralRoom.setMonsterAlive(true);  // Imposta il mostro come vivo all'inizio
        centralRoom.setLook("Attento il Cyborg sembra essersi accorto della tua presenza ...\n"+ "Ti sta per attaccare attento!!\n"+"Aspetta sembra che stia proteggendo  una ultima porta ...");
        Room archivieRoom = new Room(9,"Sala Dossier", "Ecco cosa stava proteggendo il mostro!! FILE TOP SECRET !! \n"+"Aspetta! C'è un file con dei codici... ", this);
        archivieRoom.setLook( "Forse questo file contiene il codice di accesso del MetalSlug  così da poterlo avviare");
        Room armoryRoom = new Room(10, "Armeria", "ti trovi all'interno di una vecchia armeria", this);
        armoryRoom.setLook("Sembra una vecchia armeria abbandonta ... \n" + "Forse potrai raccogliere qualche arma utile");


        //map
        startRoom.setSouth(bridgeRoom);
        bridgeRoom.setWest(prisonRoom);
        prisonRoom.setEast(bridgeRoom);
        bridgeRoom.setNorth(startRoom);
        bridgeRoom.setSouth(trackRoom);
        trackRoom.setSouth(baseRoom);
        trackRoom.setNorth(bridgeRoom);
        baseRoom.setEast(towerRoom);
        baseRoom.setNorth(trackRoom);
        baseRoom.setWest(armoryRoom);
        armoryRoom.setEast(baseRoom);
        baseRoom.setSouth(labRoom);
        labRoom.setNorth(baseRoom);
        towerRoom.setSouth(secretRoom);
        towerRoom.setWest(baseRoom);
        towerRoom.setEast(secretRoom);
        secretRoom.setWest(towerRoom);
        secretRoom.setSouth(centralRoom);
        centralRoom.setNorth(secretRoom);
        centralRoom.setEast(archivieRoom);
        archivieRoom.setWest(centralRoom);

        //obejcts
        AdvObject metalslug = new AdvObject(1,"MetalSlug", "Carro Armato Speciale");
        metalslug.setAlias(new String[]{ "metal","slug", "metalslug", "carro armato", "carro" });
        metalslug.setPushable(true);
        startRoom.addObject(metalslug);

        AdvObject controlPanel = new AdvObject(2," pannello", "descrizione");
        controlPanel.setAlias(new String[]{"pannello","console", "schermi", "console di controllo" ," pannello comandi","comandi" });
        controlPanel.setPushable(true);
        startRoom.addObject(controlPanel);

        AdvObjectContainer alien = new AdvObjectContainer(3, "Cyborg", " Mostro mutaforme con tentatacoli robotici ");
        alien.setAlias(new String[]{"gigante", "mostro", "essere", "alieno " , "mostro robotico" ,"robot"});
        alien.setCreature(true);
        centralRoom.addObject(alien);

        AdvObjectContainer prison = new AdvObjectContainer(4, " prigione", "Prigione con al suo interno un caduto di guerra ");
        prison.setAlias(new String[]{"prigione", "gabbia"});
        prison.setOpenable(true);
        prisonRoom.addObject(prison);

        AdvObject tanica = new AdvObject(6, "Tanica di benzina", "Una tanica di benzina che fortuna!\n"+ "Sembra che per ringraziarti il prigioniero di guerra ti abbia fatto un regalo." );
        tanica.setAlias(new String[]{"tanica", "benzina", "tanica di benzina"});
        tanica.setPickupable(true);
        prison.add(tanica); //tanica inserita all'interno della prigione
        prisonRoom.addObject(tanica);

        AdvObject sword = new AdvObject(8, " Heavy Machine", "Heavy Machine ... Sembra essere  una ottima pistola!");
        sword.setAlias(new String[]{"arma", "razzo", "pistola" , "lanziarazzi" , " Heavy Machine Gun" , "mitragliatrice" });
        sword.setPickupable(true);
        armoryRoom.addObject(sword);

        AdvObject door = new AdvObjectContainer(9, "oggetto porta ", "descrizione");
        door.setAlias(new String[]{"uscita", "portone", "porta rinforzata, porta"});
        door.setOpen(false); // Imposta lo stato iniziale della porta come chiusa
        towerRoom.addObject(door);

        AdvObject map = new AdvObject(11,"Codici", "Codice di accesso Metal Slug");
        map.setAlias(new String[]{"pin", "password", "segreti", "file" , "codice", "codici"});
        map.setPickupable(true);
        map.setReadable(true);
        map.setContents(
                "Inseriscilo nel pannello dei comandi: 1996 \n" );
        archivieRoom.addObject(map);

        //Observer
        GameObserver moveObserver = new MoveObserver();
        this.attach(moveObserver);
        GameObserver invObserver = new InventoryObserver();
        this.attach(invObserver);
        GameObserver pushObserver = new PushObserver();
        this.attach(pushObserver);
        GameObserver lookatObserver = new LookAtObserver();
        this.attach(lookatObserver);
        GameObserver pickupObserver = new PickUpObserver();
        this.attach(pickupObserver);
        GameObserver openObserver = new OpenObserver();
        this.attach(openObserver);
        GameObserver useObserver = new UseObserver();
        this.attach(useObserver);
        GameObserver readObserver = new ReadObserver();
        this.attach(readObserver);
        GameObserver killObserver = new KillObserver();
        this.attach(killObserver);
        //set starting room
        getRooms().add(startRoom);
        getRooms().add(bridgeRoom);
        getRooms().add(trackRoom);
        getRooms().add(baseRoom);
        getRooms().add(towerRoom);
        getRooms().add(secretRoom);
        getRooms().add(prisonRoom);
        getRooms().add(labRoom);
        getRooms().add(centralRoom);
        getRooms().add(archivieRoom);
        getRooms().add(armoryRoom);
        setCurrentRoom(startRoom);

    }



    /**
     * Processes the next move based on the player's command.
     * This method updates the game state, notifies observers, and displays messages to the player.
     *
     * @param p The parsed output of the player's command.
     * @param window The game window where messages are displayed.
     */
    @Override
    public void nextMove(ParserOutput p, Window window) {
        parserOutput = p;
        messages.clear();

        if (p.getCommand() == null) {
            window.showMessage("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            Room cr = getCurrentRoom();
            notifyObservers(window);
            boolean move = !cr.equals(getCurrentRoom()) && getCurrentRoom() != null;       // ---------> indica se il giocatore non si è mosso dalla stanza

            // Controllo per il comando LOOK_AT
            if (p.getCommand().getType() == CommandType.LOOK_AT) {
                AdvObject object = p.getObject();
                if (object != null) {
                    window.showMessage("Osservi " + object.getName() + ": " + object.getDescription());
                } else {
                    window.showMessage(cr.getLook());
                    if (cr.getName().equalsIgnoreCase("Centrale operativa")) {
                        if (cr.isMonsterAlive()) {
                            cr.setDynamicLook("Sei nell'anticamera, l'alieno Robotico sembra che stia sorvegliano qualcosa!\n"+"Stai attento potrebbe attacarti da un momento all'altro");
                        }
                        window.showMessage(cr.getDynamicLook());
                    }
                }
            } else {
                if (!messages.isEmpty()) {
                    for (String m : messages) {
                        if (m.length() > 0) {
                            window.showMessage(m);
                        }
                    }
                }
                    // Se non c'è stato un movimento, mostra la stanza corrente
                window.showRoomName(getCurrentRoom().getName());
                window.showRoomDescription(getCurrentRoom().getDescription());

            }
        }
    }






    /**
     * Attaches an observer to the game.
     * Observers are notified of game events and can update the game state or display messages.
     *
     * @param o The observer to attach.
     */
    @Override
    public void attach(GameObserver o) {
        if (!observer.contains(o)) {
            observer.add(o);
        }
    }

    /**
     * Detaches an observer from the game.
     *
     * @param o The observer to detach.
     */
    @Override
    public void detach(GameObserver o) {
        observer.remove(o);
    }

    /**
     * Notifies all attached observers of a game event.
     * Observers can update the game state or generate messages based on the event.
     *
     * @param window The game window where messages may be displayed.
     */
    @Override
    public void notifyObservers(Window window) {
        for (GameObserver o : observer) {
            messages.add(o.update(this, parserOutput, window));
        }
    }

    /**
     * Returns the welcome message to be displayed at the start of the game.
     *
     * @return The welcome message string.
     */
    @Override
    public String getWelcomeMsg() {

        return "  Ti trovi in una base nemica fantasma , sembri solo ma potrebbe esserci qualcuno...\n"
                + "Il tuo obbiettivo è riuscire a tornare a casa sano e salvo . Per fortuna noti qualcosa.\n "
                + "     Un \"Metal Slug\", l'arma segreta della resistenza, è parcheggiato vicino a te \n "
                + "      ma sembra che per farlo funzionare devi recuperare degli oggetti ... \n"
                + "      Una volta ritrovati potrai avviare il Carro Armato  e usarlo per tornare a casa.\n"
                + "          PREMI UN QUALSIASI TASTO SULLA TASTIERA PER INIZIARE      ";
    }

}



