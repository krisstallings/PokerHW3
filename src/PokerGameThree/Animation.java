package PokerGameThree;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animation {


	// Animation for removing the cards from the hand
	public static TranslateTransition dismissCard(Node node) {
		TranslateTransition move = new TranslateTransition();
		//move.setToY(value);
		move.setCycleCount(1);
		move.setNode(node);
		move.setDuration(Duration.seconds(1.5f));
		return move;
	}
}
