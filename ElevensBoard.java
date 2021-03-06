import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard extends Board {

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 9;

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen",
            "king" };

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS = { "spades", "hearts", "diamonds", "clubs" };

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0 };

    /**
     * Flag used to control debugging print statements.
     */
    private static final boolean I_AM_DEBUGGING = true;

    /**
     * Creates a new <code>ElevensBoard</code> instance.
     */
    public ElevensBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
    }

    /**
     * Determines if the selected cards form a valid group for removal. In Elevens,
     * the legal groups are (1) a pair of non-face cards whose values add to 11, and
     * (2) a group of three cards consisting of a jack, a queen, and a king in some
     * order.
     * 
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid group for removal; false
     *         otherwise.
     */
    @Override
    public boolean isLegal(List<Integer> selectedCards) {
        return containsPairSum11(selectedCards) || containsJQK(selectedCards);
    }

    /**
     * Determine if there are any legal plays left on the board. In Elevens, there
     * is a legal play if the board contains (1) a pair of non-face cards whose
     * values add to 11, or (2) a group of three cards consisting of a jack, a
     * queen, and a king in some order.
     * 
     * @return true if there is a legal play left on the board; false otherwise.
     */
    @Override
    public boolean anotherPlayIsPossible() {
        // selectedCards is a list of the indexes of
        // all cards on the board
        List<Integer> selectedCards = cardIndexes();
        return containsPairSum11(selectedCards) || containsJQK(selectedCards);
    }

    /**
     * Check for an 11-pair in the selected cards.
     * 
     * @param selectedCards selects a subset of this board. It is list of indexes
     *                      into this board that are searched to find an 11-pair.
     * @return true if the board entries in selectedCards contain an 11-pair; false
     *         otherwise.
     */
    private boolean containsPairSum11(List<Integer> selectedCards) {
      boolean hasPair = false;
      for (int i = 0; i < selectedCards.size(); i++) {
        for (int j = 0; j < selectedCards.size(); j++) {
          if (j != i && cardAt(selectedCards.get(j)).getPointValue() + cardAt(selectedCards.get(i)).getPointValue() == 11) {
            hasPair = true;
          }
        }
      }
      return hasPair;
    }

    /**
     * Check for a JQK in the selected cards.
     * 
     * @param selectedCards selects a subset of this board. It is list of indexes
     *                      into this board that are searched to find a JQK group.
     * @return true if the board entries in selectedCards include a jack, a queen,
     *         and a king; false otherwise.
     */
    private boolean containsJQK(List<Integer> selectedCards) {
        // return selectedCards.contains(10) && selectedCards.contains(11) && selectedCards.contains(12);
        boolean containsJack = false;
        boolean containsQueen = false;
        boolean containsKing = false;

        for (int i = 0; i < selectedCards.size(); i++) {
          String currentCard = cardAt(selectedCards.get(i)).getRank();
          System.out.println(currentCard);
          if (currentCard.equals("jack")) {
            containsJack = true;
          } else if (currentCard.equals("queen")) {
            containsQueen = true;
          } else if (currentCard.equals("king")) {
            containsKing = true;
          }
        }
        return containsJack && containsQueen && containsKing;
    }
}
