/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;
import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.Parser;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Room;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * The main window for the adventure game, providing the graphical user interface.
 * This class extends JFrame, incorporating various UI components to interact with the game.
 */
public class Window extends JFrame
{
    JPanel panel;
    JLabel currentBackground;
    JLabel labelScritta;
    JLabel timePlay;
    JTextArea leaderBoard;
    JTextArea roomDescriptionTextArea;
    JTextArea startDescriptionTextArea;
    JTextArea messageTextArea;
    JTextField testo;       // --------------------> dove inserire input
    JScrollPane scrollPane;
    Music sottofondo;
    RESTClient client;
    JButton pauseButton;
    JButton loadGameButton;
    JButton newGameButton;
    JButton exitAndSaveButton;
    JButton exitWithoutSaveButton;
    JButton showLeaderBoardButton;
    JLabel comandoLabel;
    JButton tutorialButton;


    Image image;
    Image resizedImage;
    private boolean isPaused = false;
    private int elapsedSeconds = 0;
    private String timeString;
    private String insertText;
    private final ImageIcon resizedImmagineGioco;
    private final ImageIcon resizedPonte;
    private final ImageIcon resizedSentiero;
    private final ImageIcon resizedLaboratorio;
    private final ImageIcon resizedPrigione;
    private final ImageIcon resizedArmeria;
    private final ImageIcon resizedCyborg;
    private final ImageIcon resizedArchivio;
    private final ImageIcon resizedCarro;
    private final ImageIcon resizedIngresso;
    private final ImageIcon resizedTorre;
    private final ImageIcon resizedPassaggio;

    /**
     * Constructs the main game window, initializing UI components and setting up event listeners.
     * @param game The game description, containing the state and logic of the adventure game.
     * @param parser The parser used to interpret player commands.
     */
    public Window(GameDescription game, Parser parser)
    {
        setSize(600,600);
        setResizable(false);
        setLayout(null);

        client = new RESTClient();
        sottofondo= new Music("resources/Audio/Barracks-Metal-Slug-X-Music.wav");

//Resize di tutte le immagini-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// scritta
        ImageIcon originalScritta = new ImageIcon("resources/Immagini/MetalSlug_scritta.png");
        image = originalScritta.getImage(); // Trasforma in un oggetto Image
        resizedImage = image.getScaledInstance(600, 600,  Image.SCALE_SMOOTH); // Ridimensiona l'immagine
        ImageIcon resizedScritta = new ImageIcon(resizedImage);
// base
        ImageIcon immagineBase = new ImageIcon("resources/Immagini/immagine_gioco.png");
        image = immagineBase.getImage();
        resizedImage = image.getScaledInstance(600, 600,  Image.SCALE_SMOOTH);
        resizedImmagineGioco = new ImageIcon(resizedImage);
//pontile
        ImageIcon pontile = new ImageIcon("resources/Immagini/MetalSlug_pontile.png");
        image = pontile.getImage();
        resizedImage = image.getScaledInstance(600, 600,  Image.SCALE_SMOOTH);
        resizedPonte = new ImageIcon(resizedImage);
//sentiero
        ImageIcon sentiero = new ImageIcon("resources/Immagini/MetalSlug_sentiero.png");
        image = sentiero.getImage();
        resizedImage = image.getScaledInstance(600, 600,  Image.SCALE_SMOOTH);
        resizedSentiero = new ImageIcon(resizedImage);
//ingresso base
        ImageIcon ingressobase = new ImageIcon("resources/Immagini/MetalSlug_ingresso.png");
        image = ingressobase.getImage();
        resizedImage = image.getScaledInstance(600, 600,  Image.SCALE_SMOOTH);
        resizedIngresso = new ImageIcon(resizedImage);
//reattpre nucleare
        ImageIcon reattore = new ImageIcon("resources/Immagini/MetalSlug_lab.png");
        image = reattore.getImage();
        resizedImage = image.getScaledInstance(600, 600,  Image.SCALE_SMOOTH);
        resizedLaboratorio = new ImageIcon(resizedImage);
//prigione
        ImageIcon prgione = new ImageIcon("resources/Immagini/MetalSlug_prigione.png");
        image = prgione.getImage();
        resizedImage = image.getScaledInstance(600, 600,  Image.SCALE_SMOOTH);
        resizedPrigione = new ImageIcon(resizedImage);
//armeria
        ImageIcon originalArmeria = new ImageIcon("resources/Immagini/MetalSlug_armeria.png");
        image = originalArmeria.getImage();
        resizedImage = image.getScaledInstance(600, 600,  Image.SCALE_SMOOTH);
        resizedArmeria = new ImageIcon(resizedImage);
//mostro
        ImageIcon originalCyborg = new ImageIcon("resources/Immagini/MetalSlug_robot.png");
        image = originalCyborg.getImage();
        resizedImage = image.getScaledInstance(600, 600,  Image.SCALE_SMOOTH);
        resizedCyborg = new ImageIcon(resizedImage);
//Metal Slug acceso
        ImageIcon attivaCarro = new ImageIcon("resources/Immagini/MetalSlug_carro.png");
        image = attivaCarro.getImage();
        resizedImage = image.getScaledInstance(600, 600,  Image.SCALE_SMOOTH);
        resizedCarro = new ImageIcon(resizedImage);
//torre di controolo
        ImageIcon torre = new ImageIcon("resources/Immagini/MetalSlug_torre.png");
        image = torre.getImage();
        resizedImage = image.getScaledInstance(600, 600,  Image.SCALE_SMOOTH);
        resizedTorre = new ImageIcon(resizedImage);
//passaggio segreto
        ImageIcon passaggiosegreto = new ImageIcon("resources/Immagini/MetalSlug_passaggio.png");
        image = passaggiosegreto.getImage();
        resizedImage = image.getScaledInstance(600, 600,  Image.SCALE_SMOOTH);
        resizedPassaggio = new ImageIcon(resizedImage);
//archivio segreto
        ImageIcon archivio = new ImageIcon("resources/Immagini/MetalSlug_archivio.png");
        image = archivio.getImage();
        resizedImage = image.getScaledInstance(600, 600,  Image.SCALE_SMOOTH);
        resizedArchivio = new ImageIcon(resizedImage);

//\Resize di tutte le immagini-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        sottofondo.startSound();

//JLabel panel-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(1,1);
        panel.setVisible(true);
        panel.setLayout(null);
        add(panel);
//\JLabel panel-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JTextArea message = ScrollPAne = clicca osserva-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        messageTextArea=new JTextArea();
        messageTextArea.setOpaque(true);
        messageTextArea.setEditable(false); // Rende la JTextArea non modificabile
        messageTextArea.setBorder(BorderFactory.createEmptyBorder()); // Rimuove i bordi
        messageTextArea.setForeground(Color.ORANGE);
        messageTextArea.setBackground(Color.BLACK);
        messageTextArea.setVisible(false);
        messageTextArea.setFont(new Font("Dialog", Font.BOLD, 12));

//\JTextArea message  = ScrollPane = clicca osserva -------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JLabel scrollpane = sezione osserva-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        scrollPane=new JScrollPane();
        scrollPane.setVisible(false);
        scrollPane.setSize(480, 51);
        scrollPane.setLocation(50, 415);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(messageTextArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.add(scrollPane);
//\JLabel scrollpanel = sezione osserva-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JLabel time-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        timePlay = new JLabel("00:00:00");
        timePlay.setForeground(Color.BLACK);
        timePlay.setBackground(Color.ORANGE);
        timePlay.setHorizontalAlignment(SwingConstants.CENTER);
        timePlay.setOpaque(true);
        timePlay.setSize(55, 17);
        timePlay.setLocation(20, 60);
        timePlay.setVisible(false);
        timePlay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));


        // Creare un contatore per i secondi
        // Sposta qui la definizione di elapsedSeconds

        // Modifica del timer per aggiornare elapsedSeconds
        Timer timer = new Timer(1000, (ActionEvent e) -> {
            elapsedSeconds++;
            int hours = elapsedSeconds / 3600;
            int minutes = (elapsedSeconds % 3600) / 60;
            int seconds = elapsedSeconds % 60;
            timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            timePlay.setText(timeString);
        });
//\JLabeltime-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------


//JButton newGameButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        newGameButton = new JButton("Nuova Partita");
        newGameButton.setSize(130, 50);
        newGameButton.setLocation(460, 450);
        newGameButton.setForeground(Color.BLACK);
        newGameButton.setContentAreaFilled(false);   // Rimuove sfondo
        newGameButton.setBorderPainted(false);       // Rimuove bordo
        newGameButton.setFocusPainted(false);        // Rimuove bordo al focus
        newGameButton.setOpaque(false);              // Rende completamente trasparente

        newGameButton.setVisible(false);
        panel.add(newGameButton);
        panel.setComponentZOrder(newGameButton, 0);
        // Gestione azione di nuova partita
        newGameButton.addActionListener((ActionEvent e) -> {
            labelScritta.setVisible(false);
            startDescriptionTextArea.setVisible(false);
            currentBackground.setVisible(true); // Visualizza solo la prima immagine
            roomDescriptionTextArea.setVisible(true);
            testo.setVisible(true);
            comandoLabel.setVisible(true);
            testo.setEditable(true);
            messageTextArea.setVisible(true);
            scrollPane.setVisible(false);
            pauseButton.setVisible(true);
            newGameButton.setVisible(false);
            loadGameButton.setVisible(false);
            showLeaderBoardButton.setVisible(false);
            leaderBoard.setVisible(false);
            testo.setFocusable(true);
            tutorialButton.setVisible(true);
            testo.requestFocusInWindow();
            timer.start();

        });
//\JButton newGameButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

//\JButton Tutorial -------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        tutorialButton = new JButton("?");
        tutorialButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        tutorialButton.setForeground(Color.BLACK);
        tutorialButton.setBackground(Color.ORANGE);
        tutorialButton.setFocusPainted(false);
        tutorialButton.setBorderPainted(true);
        tutorialButton.setContentAreaFilled(true);
        tutorialButton.setOpaque(true);
        tutorialButton.setSize(45, 45);
        tutorialButton.setLocation(40, 40); // In alto a sinistra
        tutorialButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        tutorialButton.setVisible(false);
        panel.add(tutorialButton);
        panel.setComponentZOrder(tutorialButton, 0);


        tutorialButton.addActionListener((ActionEvent e) -> {
            String comandi = "COMANDI DISPONIBILI:\n\n"
                    + "• NORD\n"
                    + "• SUD\n"
                    + "• OVEST\n"
                    + "• EST\n"
                    + "• OSSERVA\n"
                    + "• RACCOGLI\n"
                    + "• LEGGI\n"
                    + "• ACCENDI\n"
                    + "• ACCEDI\n"
                    +" • SPARA";

            JOptionPane.showMessageDialog(Window.this, comandi, "Tutorial", JOptionPane.INFORMATION_MESSAGE);
        });

//\JButton Tutorial -------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JButton loadGameButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        loadGameButton = new JButton("Carica Partita");
        loadGameButton.setSize(130, 50);
        loadGameButton.setLocation(10, 460);
        loadGameButton.setForeground(Color.BLACK);
        loadGameButton.setContentAreaFilled(false);   // Rimuove sfondo
        loadGameButton.setBorderPainted(false);       // Rimuove bordo
        loadGameButton.setFocusPainted(false);        // Rimuove bordo al focus
        loadGameButton.setOpaque(false);              // Rende completamente trasparente

        loadGameButton.setVisible(false);
        panel.add(loadGameButton);

        panel.setComponentZOrder(loadGameButton, 0);

        loadGameButton.addActionListener((ActionEvent e) -> {
            String gameName = JOptionPane.showInputDialog("Inserisci il nome della partita da caricare:");
            if (gameName != null && !gameName.trim().isEmpty()) {
                Map<String, Object> loadedGame = SaveGame.load(gameName.trim());
                if (!loadedGame.isEmpty()) {
                    // Recupera i dati della partita caricata
                    elapsedSeconds = (int) loadedGame.get("ElapsedSeconds");
                    int currentRoomId = (int) loadedGame.get("CurrentRoom");
                    List<Integer> inventoryIds = (List<Integer>) loadedGame.get("Inventory");
                    boolean monsterAlive=(boolean) loadedGame.get("MonsterAlive");
                    boolean isDoorOpen=(boolean) loadedGame.get("IsDoorOpen");

                    // Aggiorna l'oggetto game con i dati caricati
                    game.setGame(currentRoomId, inventoryIds, monsterAlive, isDoorOpen);

                    // Aggiorna l'interfaccia utente
                    String currentRoomName = game.getCurrentRoom().getName(); // Assumendo che tu abbia un metodo per ottenere il nome della stanza corrente
                    showRoomName(currentRoomName);

                    labelScritta.setVisible(false);
                    startDescriptionTextArea.setVisible(false);
                    currentBackground.setVisible(true); // Visualizza solo la prima immagine
                    roomDescriptionTextArea.setVisible(true);
                    showRoomDescription(game.getCurrentRoom().getDescription());
                    testo.setVisible(true);
                    comandoLabel.setVisible(true);
                    messageTextArea.setVisible(true);
                    scrollPane.setVisible(false);
                    pauseButton.setVisible(true);
                    newGameButton.setVisible(false);
                    loadGameButton.setVisible(false);
                    showLeaderBoardButton.setVisible(false);
                    leaderBoard.setVisible(false);
                    testo.setFocusable(true);
                    tutorialButton.setVisible(true);
                    testo.requestFocusInWindow();
                    timer.start();

                    JOptionPane.showMessageDialog(null, "Partita caricata con successo!");
                } else {
                    JOptionPane.showMessageDialog(null, "Partita non trovata!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nome della partita non valido!");
            }
        });
//\JButton loadGameButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------


//\JButton exitAndSaveButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        exitAndSaveButton = new JButton("Esci e salva");
        exitAndSaveButton.setSize(130, 30);
        exitAndSaveButton.setLocation(310, 250);
        exitAndSaveButton.setForeground(Color.BLACK);
        exitAndSaveButton.setBackground(Color.ORANGE);
        exitAndSaveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        exitAndSaveButton.setVisible(false);
        panel.add(exitAndSaveButton);
        panel.setComponentZOrder(exitAndSaveButton, 0);
        exitAndSaveButton.addActionListener((ActionEvent e) -> {
            while (true) {
                String gameName = JOptionPane.showInputDialog("Inserisci il nome della partita da salvare:");
                if (gameName == null) {
                    JOptionPane.showMessageDialog(null, "Operazione di salvataggio annullata.");
                    break; // Esci dal ciclo se l'utente preme "Cancel"
                } else if (!gameName.trim().isEmpty()) {
                    if (!SaveGame.gameExists(gameName.trim())) {
                        boolean monsterAlive=true, isDoorOpen;
                        List<Room> rooms;
                        rooms=game.getRooms();
                        for (Room room : rooms) {
                            if (room.getId()==8) {
                                monsterAlive=room.isMonsterAlive();
                            }
                        }
                        isDoorOpen = game.isKeyUsed();
                        SaveGame.save(game.getCurrentRoom(), game.getInventory(), gameName.trim(), elapsedSeconds, monsterAlive, isDoorOpen);
                        JOptionPane.showMessageDialog(null, "Partita salvata con successo!");
                        sottofondo.stopSound();
                        System.exit(0);
                        break; // Esci dal ciclo dopo un salvataggio riuscito
                    } else {
                        JOptionPane.showMessageDialog(null, "Una partita con questo nome esiste già. Inserisci un altro nome.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nome della partita non valido! Inserisci un nome valido.");
                }
            }
        });
//\JButton exitAndSaveButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//\JButton exitWithoutSaveButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        exitWithoutSaveButton = new JButton("Esci senza salvare");
        exitWithoutSaveButton.setSize(130, 30);
        exitWithoutSaveButton.setLocation(160, 250);
        exitWithoutSaveButton.setForeground(Color.BLACK);
        exitWithoutSaveButton.setBackground(Color.ORANGE);
        exitWithoutSaveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        exitWithoutSaveButton.setVisible(false);
        panel.add(exitWithoutSaveButton);
        panel.setComponentZOrder(exitWithoutSaveButton, 0);

        exitWithoutSaveButton.addActionListener((ActionEvent e) -> {
            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Sei sicuro di voler uscire senza salvare?",
                    "Conferma uscita",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (response == JOptionPane.OK_OPTION) {
                sottofondo.stopSound();
                System.exit(0);
            }
        });
//\JButton exitWithoutSaveButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------




//JButton Pausebutton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        pauseButton = new JButton("Pausa");
        pauseButton.setSize(100, 30);
        pauseButton.setLocation(450, 50);
        pauseButton.setForeground(Color.BLACK);
        pauseButton.setBackground(Color.ORANGE);
        pauseButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        pauseButton.setOpaque(true);
        pauseButton.setVisible(false);
        panel.add(pauseButton);
        panel.setComponentZOrder(pauseButton, 0);

        // Gestione azione di pausa/riprendi
        pauseButton.addActionListener((ActionEvent e) -> {
            if (isPaused) {
                // Se il gioco è in pausa, riprendi
                isPaused = false;
                pauseButton.setText("Pausa");
                timer.start(); // Riprendi il timer
                testo.setEditable(true);
                exitAndSaveButton.setVisible(false);
                exitWithoutSaveButton.setVisible(false);
            } else {
                // Se il gioco non è in pausa, metti in pausa
                isPaused = true;
                pauseButton.setText("Riprendi");
                timer.stop(); // Ferma il timer
                testo.setEditable(false);
                exitAndSaveButton.setVisible(true);
                exitWithoutSaveButton.setVisible(true);
            }
        });

//\JButton Pausebutton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JButton showClassificaButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        showLeaderBoardButton = new JButton("Mostra Classifica");
        showLeaderBoardButton.setSize(160, 50);
        showLeaderBoardButton.setLocation(420, 50);
        showLeaderBoardButton.setForeground(Color.BLACK);
        showLeaderBoardButton.setContentAreaFilled(false);   // Rimuove sfondo
        showLeaderBoardButton.setBorderPainted(false);       // Rimuove bordo
        showLeaderBoardButton.setFocusPainted(false);        // Rimuove bordo al focus
        showLeaderBoardButton.setOpaque(false);              // Rende completamente trasparente

        showLeaderBoardButton.setVisible(false);
        panel.add(showLeaderBoardButton);
        panel.setComponentZOrder(showLeaderBoardButton, 0);
        showLeaderBoardButton.addActionListener((ActionEvent e) -> {
            if (showLeaderBoardButton.getText().equals("Mostra Classifica")){
                showLeaderBoardButton.setText("Nascondi Classifica");
                leaderBoard.setVisible(true);
            }
            else if(showLeaderBoardButton.getText().equals("Nascondi Classifica")){
                showLeaderBoardButton.setText("Mostra Classifica");
                leaderBoard.setVisible(false);
            }
        });
//\JButton showClassificaButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------




//JLabel Scritta   -------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        labelScritta = new JLabel(resizedScritta);
        labelScritta.setSize(600, 600);
        labelScritta.setLocation(0, 0);
        labelScritta.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));

        panel.add(labelScritta);

        labelScritta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                startDescriptionTextArea.setVisible(false);
                newGameButton.setVisible(true);
                loadGameButton.setVisible(true);
                showLeaderBoardButton.setLocation(220, 480);
                showLeaderBoardButton.setVisible(true);
                showLeaderBoard();

            }
        });

        labelScritta.setFocusable(true);
        labelScritta.requestFocusInWindow();
        labelScritta.setVisible(true);
        currentBackground = new JLabel(resizedImmagineGioco); // Prima immagine di sfondo
        currentBackground.setSize(600, 600);
        currentBackground.setLocation(0, 0);
        panel.add(currentBackground);
        currentBackground.setVisible(false);
//\JLabel RoomImage-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JTextArea descrizione inizio gioco-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        startDescriptionTextArea = new JTextArea();
        startDescriptionTextArea.setEditable(false); // Rende la JTextArea non modificabile
        startDescriptionTextArea.setOpaque(true);
        startDescriptionTextArea.setForeground(Color.BLACK);
        startDescriptionTextArea.setBackground(Color.ORANGE);
        startDescriptionTextArea.setSize(600,140);
        startDescriptionTextArea.setLocation(0,420);
        startDescriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        startDescriptionTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));



        startDescriptionTextArea.setVisible(true);
        panel.add(startDescriptionTextArea);
        panel.setComponentZOrder(startDescriptionTextArea, 0);
//\JTextArea startDescription-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------




//JTextArea roomDescription-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        roomDescriptionTextArea = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(Color.BLACK);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                g2.dispose();
            }

            @Override
            public Insets getInsets() {
                return new Insets(10, 15, 10, 15);
            }
        };

        roomDescriptionTextArea.setOpaque(false);
        roomDescriptionTextArea.setSize(480, 51);
        roomDescriptionTextArea.setLocation(50, 360);
        roomDescriptionTextArea.setEditable(false);
        roomDescriptionTextArea.setForeground(Color.BLACK);
        roomDescriptionTextArea.setBackground(Color.ORANGE);
        roomDescriptionTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        roomDescriptionTextArea.setVisible(false);
        panel.add(roomDescriptionTextArea);
        panel.setComponentZOrder(roomDescriptionTextArea, 0);


//\JTextArea roomDescription-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JTextArea classificaBoard-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        leaderBoard=new JTextArea();
        leaderBoard.setOpaque(true);
        leaderBoard.setSize(320, 300);
        leaderBoard.setLocation(120, 50);
        leaderBoard.setEditable(false); // Rende la JTextArea non modificabile
        leaderBoard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        leaderBoard.setForeground(Color.BLACK);
        leaderBoard.setBackground(Color.ORANGE);
        leaderBoard.setVisible(false);
        panel.add(leaderBoard);
        panel.setComponentZOrder(leaderBoard, 0);

//\JTextArea leaderBoard-------------------------------------------------------------------------------------------------------------------------------------------------------------------


//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Etichetta sopra al campo testo (input)
        comandoLabel = new JLabel("Inserisci i comandi  qui sotto:");
        comandoLabel.setForeground(Color.ORANGE );
        comandoLabel.setBackground(Color.BLACK);
        comandoLabel.setOpaque(true);
        comandoLabel.setSize(200, 20);
        comandoLabel.setLocation(200, 120); // ← Posizionata a sinistra di testo
        comandoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        comandoLabel.setVisible(false);
        panel.add(comandoLabel);
        panel.setComponentZOrder(comandoLabel, 0);



//JTextField-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // testo comandi con bordo stondato
        testo = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(Color.BLACK);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                g2.dispose();
            }

            @Override
            public Insets getInsets() {
                return new Insets(5, 10, 5, 10);
            }
        };

        testo.setSize(200, 30);
        testo.setLocation(200, 140);
        testo.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        testo.setForeground(Color.BLACK);
        testo.setBackground(Color.ORANGE);
        testo.setOpaque(false);
        testo.setVisible(false);
        panel.add(testo);
        panel.setComponentZOrder(testo, 0);





        testo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    insertText = testo.getText().trim();
                    testo.setText("");
                    Window.this.showMessage("");

                    if (insertText.equalsIgnoreCase("1996")) {
                        Window.this.showMessage("Hai inserito correttamente il codice di avvio! Il Metal Slug si attiva...\nHai completato l’avventura, complimenti!");
                        testo.setVisible(false);
                        pauseButton.setVisible(false);
                        comandoLabel.setVisible(false);
                        roomDescriptionTextArea.setVisible(false);
                        showLeaderBoardButton.setLocation(450, 50);
                        showLeaderBoardButton.setVisible(true);
                        game.setCurrentRoom(null);
                        currentBackground.setIcon(resizedCarro);
                        timer.stop();

                        timePlay.setText("Tempo impiegato: " + timeString);
                        timePlay.setLocation(50, 470);
                        timePlay.setSize(180, 17);

                        String name;
                        do {
                            name = JOptionPane.showInputDialog(Window.this, "Inserisci il tuo nome (max 10 caratteri):", "Nome Giocatore", JOptionPane.PLAIN_MESSAGE);
                            if (name == null) {
                                Window.this.showMessage("Inserimento annullato.");
                                return;
                            }
                            if (name.length() > 10) {
                                Window.this.showMessage("Nome troppo lungo. Reinserisci un nome con massimo 10 caratteri.");
                            }
                        } while (name.length() > 10 || name.trim().isEmpty());

                        if (!name.trim().isEmpty()) {
                            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            String time = timeString;
                            PlayerData player = new PlayerData(name, date, time);
                            client.addPlayer(player);
                            showLeaderBoard();
                            Window.this.showMessage("Hai attivato il Metal Slug correttamente!\nSei tornato a casa sano e salvo.\nLa tua avventura termina qui!");
                        } else {
                            Window.this.showMessage("Nome non valido. Inserimento annullato.");
                        }
                        return;
                    }

                    // Normale parsing comando
                    ParserOutput p = parser.parse(insertText, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
                    if (p == null || p.getCommand() == null) {
                        Window.this.showMessage("Non capisco quello che mi vuoi dire.");
                    } else if (p.getCommand().getType() == CommandType.END) {
                        Window.this.showMessage("Sei un fifone, addio!");
                        testo.setVisible(false);
                        pauseButton.setVisible(false);
                        roomDescriptionTextArea.setVisible(false);
                        timer.stop();
                    } else {
                        game.nextMove(p, Window.this);
                        if (game.getCurrentRoom() == null) {
                            Window.this.showMessage("La tua avventura termina qui! Complimenti!");
                            testo.setVisible(false);
                            pauseButton.setVisible(false);
                            roomDescriptionTextArea.setVisible(false);
                            System.exit(0);
                        }
                    }
                }
            }
        });



//\JTextField-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



        // Aggiungi un WindowListener per gestire l'evento di chiusura della finestra
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sottofondo.stopSound();
                dispose(); // Chiudi la finestra in modo appropriato
                System.exit(0);
            }
        });

        setVisible(true);
    }

    /**
     * Displays the starting description of the game in a text area.
     * @param startDescription The starting description text.
     */
    public void showStartDescription(String startDescription){

        startDescriptionTextArea.setText(startDescription);
    }

    /**
     * Updates and displays the name of the current room in a text area.
     * @param roomName The name of the current room.
     */
    public void showRoomName(String roomName){



        if(roomName.equals("zona di sbarco"))    currentBackground.setIcon(resizedImmagineGioco);
        if(roomName.equals("Pontile"))    currentBackground.setIcon(resizedPonte);
        if(roomName.equals("Sentiero"))    currentBackground.setIcon(resizedSentiero);
        if(roomName.equals("Ingresso Base"))    currentBackground.setIcon(resizedIngresso);
        if(roomName.equals("Reattore Nucleare"))    currentBackground.setIcon(resizedLaboratorio);
        if(roomName.equals("Prigione"))    currentBackground.setIcon(resizedPrigione);
        if(roomName.equals("Armeria"))    currentBackground.setIcon(resizedArmeria);
        if(roomName.equals("Centrale operativa"))    currentBackground.setIcon(resizedCyborg);
        if(roomName.equals("Archivio"))    currentBackground.setIcon(resizedArchivio);
        if(roomName.equals("Torre di controllo"))    currentBackground.setIcon(resizedTorre);
        if(roomName.equals("Passaggio Segreto"))    currentBackground.setIcon(resizedPassaggio);
        if(roomName.equals("Sala Dossier"))    currentBackground.setIcon(resizedArchivio);

    }

    /**
     * Updates and displays the description of the current room in a text area.
     * @param roomDescription The description of the current room.
     */
    public void showRoomDescription(String roomDescription){
        roomDescriptionTextArea.setText(roomDescription);
    }

    /**
     * Displays a message in a text area, typically used for feedback or game instructions.
     * @param message The message to display.
     */
    public void showMessage(String message) {
        messageTextArea.setText(message);
        scrollPane.setVisible(!message.trim().isEmpty()); // mostra solo se non vuoto
    }



    /**
     * Fetches and displays the leaderboard from a RESTful service.
     */
    public void showLeaderBoard(){
        String leaderboard = client.getLeaderboard();
        leaderBoard.setText(leaderboard);
    }

    /**
     * Retrieves the text currently inserted by the player.
     * @return The text inserted by the player.
     */
    public String getInsertText(){
        return insertText;
    }
}