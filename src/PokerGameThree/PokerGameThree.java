package PokerGameThree;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PokerGameThree extends Application {

    // UI
    private Stage window;
    private Scene gameScreen, betScreen;

    // Game screen elements
    private BorderPane gamePane = new BorderPane();
    private ToggleButton card1 = new ToggleButton("");
    private ToggleButton card2 = new ToggleButton("");
    private ToggleButton card3 = new ToggleButton("");
    private ToggleButton card4 = new ToggleButton("");
    private ToggleButton card5 = new ToggleButton("");
    private ImageView card1Image = new ImageView();
    private ImageView card2Image = new ImageView();
    private ImageView card3Image = new ImageView();
    private ImageView card4Image = new ImageView();
    private ImageView card5Image = new ImageView();
    private Label walletAmt = new Label("");
    private Button drawBtn = new Button("DRAW");
    private Button dealBtn = new Button("DEAL");
    private RadioButton oneBox = new RadioButton("$1");
    private RadioButton tenBox = new RadioButton("$10");
    private RadioButton hundredBox = new RadioButton("$100");
    private Button hold1Btn = new Button("HOLD");
    private Button hold2Btn = new Button("HOLD");
    private Button hold3Btn = new Button("HOLD");
    private Button hold4Btn = new Button("HOLD");
    private Button hold5Btn = new Button("HOLD");


    // Game preparations
    private Deck deck;							// Holds the deck for the game
    private int deckParam = 1;					// Holds the numOfDecks selected by the user
    private Hand hand;							// Holds the current hand
    private Wallet wallet = new Wallet(200);	// Holds the user's current wallet amount

    @Override
    public void start(Stage stage) throws Exception {
        try {
            // Set window to the main stage
            window = stage;

            // Create the gameScreen scene
            gamePane.setBottom(getGameButtons());
            gamePane.setCenter(getCards());
            gameScreen = new Scene(gamePane, 900, 500);

            // Set the JavaFX window title, icon, and scene. Then show the window
            window.setTitle("Poker Five Card Game");
            window.setResizable(false);
            window.setScene(gameScreen);
            window.show();
        } catch (Exception e)	{
            e.printStackTrace();
        }
    }

    // Returns a GridPane with the cards
    private GridPane getCards() throws FileNotFoundException {

        // Generate the deck
        deck = new Deck(deckParam);

        // Generate the first hand
        hand = new Hand(deck);

        // Create the game GridPane container
        GridPane game = new GridPane();
        game.setHgap(40);
        game.setVgap(40);

        // Set the cards to images
        final Image selected = new Image(new FileInputStream("image/card/b1fv.png"));
        final Image card1Img = new Image(new FileInputStream("image/card/" + hand.getCard(0).getNumber() + ".png"));
        final Image card2Img = new Image(new FileInputStream("image/card/" + hand.getCard(1).getNumber() + ".png"));
        final Image card3Img = new Image(new FileInputStream("image/card/" + hand.getCard(2).getNumber() + ".png"));
        final Image card4Img = new Image(new FileInputStream("image/card/" + hand.getCard(3).getNumber() + ".png"));
        final Image card5Img = new Image(new FileInputStream("image/card/" + hand.getCard(4).getNumber() + ".png"));

        card1Image.setImage(card1Img);
        card2Image.setImage(card2Img);
        card3Image.setImage(card3Img);
        card4Image.setImage(card4Img);
        card5Image.setImage(card5Img);

        card1.setGraphic(card1Image);
        card2.setGraphic(card2Image);
        card3.setGraphic(card3Image);
        card4.setGraphic(card4Image);
        card5.setGraphic(card5Image);

        //card is selected or not selected need to update for buttons
        card1Image.imageProperty().bind(Bindings.when(card1.selectedProperty()).then(selected).otherwise(card1Img));
        card2Image.imageProperty().bind(Bindings.when(card2.selectedProperty()).then(selected).otherwise(card2Img));
        card3Image.imageProperty().bind(Bindings.when(card3.selectedProperty()).then(selected).otherwise(card3Img));
        card4Image.imageProperty().bind(Bindings.when(card4.selectedProperty()).then(selected).otherwise(card4Img));
        card5Image.imageProperty().bind(Bindings.when(card5.selectedProperty()).then(selected).otherwise(card5Img));

        // Add the cards to the container
        // column, row
        game.add(card1, 0, 0);
        game.add(card2, 1, 0);
        game.add(card3, 2, 0);
        game.add(card4, 3, 0);
        game.add(card5, 4, 0);


        // Set container content to center and return it
        game.setAlignment(Pos.CENTER);
        return game;
    }

    // Return a HBox with the draw, bet, & draw buttons
    private Region getGameButtons() {
        // Create the buttons HBox container
        HBox container = new HBox();

        // Disable dealBtn
        dealBtn.setDisable(false);

        // Set draw button behaviors
        dealBtn.setOnAction(e -> {
            wallet.setAmount(wallet.getAmount() - wallet.getBetAmount());
            if (hand.isRoyalFlush()) {
                wallet.setAmount(wallet.getAmount() + (wallet.getBetAmount() * 3));
            } else if (hand.isStraightFlush()) {
                wallet.setAmount(wallet.getAmount() + (wallet.getBetAmount() * 2));
            } else if (hand.isFour()) {
                wallet.setAmount(wallet.getAmount() + (wallet.getBetAmount() * 2));
            } else if (hand.allSameSuit()) {
                wallet.setAmount(wallet.getAmount() + (wallet.getBetAmount() * 2));
            }

            hand = new Hand(deck);

            // Set the cards to images
            try {
                final Image selected = new Image(new FileInputStream("image/card/b1fv.png"));
                final Image newCard1Img = new Image(new FileInputStream("image/card/" + hand.getCard(0).getNumber() + ".png"));
                final Image newCard2Img = new Image(new FileInputStream("image/card/" + hand.getCard(1).getNumber() + ".png"));
                final Image newCard3Img = new Image(new FileInputStream("image/card/" + hand.getCard(2).getNumber() + ".png"));
                final Image newCard4Img = new Image(new FileInputStream("image/card/" + hand.getCard(3).getNumber() + ".png"));
                final Image newCard5Img = new Image(new FileInputStream("image/card/" + hand.getCard(4).getNumber() + ".png"));

                final ImageView one = new ImageView(newCard1Img);
                final ImageView two = new ImageView(newCard2Img);
                final ImageView three = new ImageView(newCard3Img);
                final ImageView four = new ImageView(newCard4Img);
                final ImageView five = new ImageView(newCard5Img);

                card1.setGraphic(one);
                card2.setGraphic(two);
                card3.setGraphic(three);
                card4.setGraphic(four);
                card5.setGraphic(five);

                one.imageProperty().bind(Bindings.when(card1.selectedProperty()).then(selected).otherwise(newCard1Img));
                two.imageProperty().bind(Bindings.when(card2.selectedProperty()).then(selected).otherwise(newCard2Img));
                three.imageProperty().bind(Bindings.when(card3.selectedProperty()).then(selected).otherwise(newCard3Img));
                four.imageProperty().bind(Bindings.when(card4.selectedProperty()).then(selected).otherwise(newCard4Img));
                five.imageProperty().bind(Bindings.when(card5.selectedProperty()).then(selected).otherwise(newCard5Img));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

            // Flip cards again
            card1.setSelected(false);
            card2.setSelected(false);
            card3.setSelected(false);
            card4.setSelected(false);
            card5.setSelected(false);

            // Add money
            walletAmt.setText("Wallet: $" + Integer.toString((int) wallet.getAmount()));

            // Enable draw
            drawBtn.setDisable(false);
        });

        oneBox.setOnAction(e -> {
            // Init betScreen scene
            getBetButtons();
            window.setScene(betScreen);
        });

        // Set draw button
        drawBtn.setOnAction(e -> {
            ArrayList<Integer> toRemove = new ArrayList<Integer>();    // Holds card indexes to remove

            if (card1.isSelected()) {
                toRemove.add(0);
            }

            if (card2.isSelected()) {
                toRemove.add(1);
            }

            if (card3.isSelected()) {
                toRemove.add(2);
            }

            if (card4.isSelected()) {
                toRemove.add(3);
            }

            if (card5.isSelected()) {
                toRemove.add(4);
            }

            // Remove selected cards from hand
            for (int i = toRemove.size(); i > 0; i--) {
                hand.removeCard(i);
            }

            // Provide new cards
            for (int i : toRemove) {
                hand.addCardAtIndex(i, deck.getRandomCard());
            }

            // Set the cards to their respective images
            try {
                final Image selected = new Image(new FileInputStream("image/card/b1fv.png"));
                final Image newCard1Img = new Image(new FileInputStream("image/card/" + hand.getCard(0).getNumber() + ".png"));
                final Image newCard2Img = new Image(new FileInputStream("image/card/" + hand.getCard(1).getNumber() + ".png"));
                final Image newCard3Img = new Image(new FileInputStream("image/card/" + hand.getCard(2).getNumber() + ".png"));
                final Image newCard4Img = new Image(new FileInputStream("image/card/" + hand.getCard(3).getNumber() + ".png"));
                final Image newCard5Img = new Image(new FileInputStream("image/card/" + hand.getCard(4).getNumber() + ".png"));

                final ImageView one = new ImageView(newCard1Img);
                final ImageView two = new ImageView(newCard2Img);
                final ImageView three = new ImageView(newCard3Img);
                final ImageView four = new ImageView(newCard4Img);
                final ImageView five = new ImageView(newCard5Img);

                card1.setGraphic(one);
                card2.setGraphic(two);
                card3.setGraphic(three);
                card4.setGraphic(four);
                card5.setGraphic(five);

                one.imageProperty().bind(Bindings.when(card1.selectedProperty()).then(selected).otherwise(newCard1Img));
                two.imageProperty().bind(Bindings.when(card2.selectedProperty()).then(selected).otherwise(newCard2Img));
                three.imageProperty().bind(Bindings.when(card3.selectedProperty()).then(selected).otherwise(newCard3Img));
                four.imageProperty().bind(Bindings.when(card4.selectedProperty()).then(selected).otherwise(newCard4Img));
                five.imageProperty().bind(Bindings.when(card5.selectedProperty()).then(selected).otherwise(newCard5Img));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

            // Flip cards again
            card1.setSelected(false);
            card2.setSelected(false);
            card3.setSelected(false);
            card4.setSelected(false);
            card5.setSelected(false);

        });

        // Add the buttons
        container.getChildren().addAll(dealBtn,hold1Btn, drawBtn, hold2Btn, oneBox, hold3Btn, tenBox, hold4Btn, hundredBox, hold5Btn);

        // Set spacing
        container.setSpacing(10);
        HBox.setMargin(dealBtn, new Insets(1, 1, 30, 1));
        HBox.setMargin(hold1Btn, new Insets(0, 20, 250, 10));
        HBox.setMargin(drawBtn, new Insets(1, 1, 30, 1));
        HBox.setMargin(hold2Btn, new Insets(0, 20, 250, 10));
        HBox.setMargin(oneBox, new Insets(1, 1, 30, 1));
        HBox.setMargin(hold3Btn, new Insets(0, 20, 250, 10));
        HBox.setMargin(tenBox, new Insets(1, 1, 30, 1));
        HBox.setMargin(hold4Btn, new Insets(0, 20, 250, 10));
        HBox.setMargin(hundredBox, new Insets(1, 1, 30, 1));
        HBox.setMargin(hold5Btn, new Insets(0, 20, 250, 10));

        // Set container content to center and return it
        container.setAlignment(Pos.BOTTOM_CENTER);
        return container;
    }

    private void getBetButtons() {
    }

    // Launches the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}