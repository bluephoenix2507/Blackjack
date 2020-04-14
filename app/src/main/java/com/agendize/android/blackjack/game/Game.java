package com.agendize.android.blackjack.game;

import android.content.Context;
import android.content.res.Resources;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agendize.android.blackjack.R;

import java.util.List;

/**
 * Created by David Tant on 14/04/2020 09:04.
 */
public class Game {
    private static final float density = Resources.getSystem().getDisplayMetrics().density;

    private Context context;
    private LinearLayout dealerHandLayout;
    private LinearLayout playerHandLayout;
    private TextView dealerScore;
    private TextView playerScore;

    private Player dealer;
    private Player player;
    private boolean revealDealerCards;

    public Game(Context context, LinearLayout dealerHandLayout, LinearLayout playerHandLayout, TextView dealerScore, TextView playerScore) {
        this.context = context;
        this.dealerHandLayout = dealerHandLayout;
        this.playerHandLayout = playerHandLayout;
        this.dealerScore = dealerScore;
        this.playerScore = playerScore;
    }

    private void drawDeck() {
        dealerHandLayout.removeAllViews();
        playerHandLayout.removeAllViews();

        List<Card> dealerCards = dealer.getCards();
        boolean firstCard = true;
        for (Card card : dealerCards) {
            ImageView cardView = new ImageView(context);
            cardView.setAdjustViewBounds(true);
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (!firstCard) {
                if (revealDealerCards) {
                    cardView.setImageDrawable(context.getDrawable(card.getImageID()));
                } else {
                    cardView.setImageDrawable(context.getDrawable(R.drawable.red_back));
                }
                layout.setMarginStart((int)(-55 * density));
            } else {
                cardView.setImageDrawable(context.getDrawable(card.getImageID()));
                firstCard = false;
            }
            cardView.setLayoutParams(layout);
            dealerHandLayout.addView(cardView);
        }

        List<Card> playerCards = player.getCards();
        firstCard = true;
        for (Card card : playerCards) {
            ImageView cardView = new ImageView(context);
            cardView.setAdjustViewBounds(true);
            cardView.setImageDrawable(context.getDrawable(card.getImageID()));
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (!firstCard) {
                layout.setMarginStart((int)(-55 * density));
            } else {
                firstCard = false;
            }
            cardView.setLayoutParams(layout);
            playerHandLayout.addView(cardView);
        }

        if (revealDealerCards) {
            dealerScore.setText(Integer.toString(dealer.getScore()));
        } else {
            dealerScore.setText(Integer.toString(dealer.getCards().get(0).value()));
        }
        playerScore.setText(Integer.toString(player.getScore()));
    }
}
