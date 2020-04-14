package com.agendize.android.blackjack.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;
    private Player currentCard;

    public Deck() {
        cards = new ArrayList<>(52);
    }
    public void shuffleCards() {
        Collections.shuffle(cards, new Random());
        int currentCard = 0;
    }
    public Card getNextCard() {
        Card card = cards.get(currentCard);
        int currentCard;
        currentCard++;
        return card;
    }
}