/*
 * Author: cgnj512
 * Time: 09/06/2020
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Card implements Comparable<Card>
{
    private final int ind;
    private final String str;
    public static final String[] trans = new String[]
            {"3", "4", "5", "6", "7", "8", "9", "0",
             "J", "Q", "K", "A", "2", "S", "B"};

    public Card(int v)
    {
        ind = v;
        str = trans[v];
    }

    public int getIndex()
    {
        return ind;
    }

    public String getStr() {
        return str;
    }

    @Override
    public int compareTo(Card other)
    {
        return Integer.compare(ind, other.ind);
    }
}


class CardPile
{
    protected final ArrayList<Card> pile;
    protected final int val;

    public CardPile()
    {
        pile = new ArrayList<>();
        val = -1;
    }

    public void sort()
    {
        pile.sort(Card::compareTo);
    }

    public int getSize()
    {
        return pile.size();
    }

    public Card getCard(int i)
    {
        return pile.get(i);
    }

    public void addCard(Card c)
    {
        pile.add(c);
    }

    public void deleteCard(int i)
    {
        pile.remove(i);
    }

    public int getVal()
    {
        return val;
    }

    public void Print()
    {
        if (getSize() == 0) return;
        sort();
        int len = pile.size();
        for (int i = 0; i < 2 * (len + 1); i++)
            System.out.print("-");
        System.out.println();
        for (Card card : pile)
        {
            System.out.print("|");
            System.out.print(card.getStr());
        }
        System.out.println(" |");
        for (Card ignored : pile) {System.out.print("| ");}
        System.out.println(" |");
        for (int i = 0; i < 2 * (len + 1); i++)
            System.out.print("-");
        System.out.println();
    }

    public boolean check(String str) // true -> false :-)
    {
        for (int i = 0; i < str.length(); ++i)
            if ((str.charAt(i) < '0' || str.charAt(i) > '9') && str.charAt(i) != 'A'
                 && str.charAt(i) != 'J' && str.charAt(i) != 'Q' && str.charAt(i) != 'K'
                 && str.charAt(i) != 'S' && str.charAt(i) != 'B') return true;
        int j = 0;
        Set<Integer> st = new HashSet<>();
        while (j < str.length())
        {
            boolean can = false;
            for (int i = 0; i < getSize(); i++)
                if (!st.contains(i) && getCard(i).getStr().equals(str.substring(j, j+1)))
                {
                    st.add(i);
                    j++;
                    can = true;
                    break;
                }
            if (!can) return true;
        }
        return false;
    }
}


class OrderedPile extends CardPile
{
    public OrderedPile()
    {
        super();
    }

    public void init(CardPile p, String str)
    {
        if (p.check(str)) return;
        int j = 0;
        System.out.println("here3!");
        while (j < str.length())
        {
            for (int i = 0; i < p.getSize(); i++)
                if (p.getCard(i).getStr().equals(str.substring(j, j+1)))
                {
                    j++;
                    pile.add(p.getCard(i));
                    p.deleteCard(i);
                    break;
                }
        }
    }

    @Override
    public void Print()
    {
        if (getSize() == 0) return;
        int len = pile.size();
        for (int i = 0; i < 2 * (len + 1); i++)
            System.out.print("-");
        System.out.println();
        for (Card card : pile)
        {
            System.out.print("|");
            System.out.print(card.getStr());
        }
        System.out.println(" |");
        for (Card ignored : pile) {System.out.print("| ");}
        System.out.println(" |");
        for (int i = 0; i < 2 * (len + 1); i++)
            System.out.print("-");
        System.out.println();
    }
}
