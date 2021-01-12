
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tester
{
    public static void main(String[] args)
    {
        String[] ranks = {"jack", "queen", "king", "ace"};
        String[] suits = {"spades", "hearts"};
        int[] values = {11, 12, 13, 14};
        
        Deck d1 = new Deck(ranks, suits, values);
        System.out.println(d1);
        
        d1.shuffle();
        System.out.println(d1);
    }
}
