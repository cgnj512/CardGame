import java.util.Scanner;

import static java.lang.Math.random;

public class Launcher {

    CardPile player1;
    CardPile player2;
    CardPile player3;
    CardPile extra;

    public static void main(String[] args)
    {
        Launcher lc = new Launcher();
        lc.startGame();
        CardPile[] ps = new CardPile[] {lc.player1, lc.player2, lc.player3};
        for (CardPile cp : ps) cp.sort();
        Scanner in = new Scanner(System.in);

        boolean has_extra = false;
        for (int i = 0; i < 3; ++i)
        {
            System.out.println("Player" + (i+1) + ":");
            ps[i].Print();
            if (!has_extra)
            {
                if (i != 2) System.out.println("Do you want extra cards? (Y/N)");
                String result = "";
                if (i != 2) result = in.next();
                if (result.equals("Y") || i == 2)
                {
                    has_extra = true;
                    for (int ii = 0; ii < 3; ii++)
                        ps[i].addCard(lc.extra.getCard(ii));
                    System.out.println("Now Your cards are:");
                    ps[i].Print();
                }
            }
        }

        boolean playing = true;
        String input;
        while (playing)
        {
            for (int i = 0; i < 3; i++)
            {
                System.out.println("\nPlayer" + (i+1) + ":");
                ps[i].Print();
                input = in.next();
                while (ps[i].check(input))
                {
                    System.out.println("Invalid request!");
                    input = in.next();
                }
                OrderedPile op = new OrderedPile();
                op.init(ps[i], input);
                op.Print();
            }
        }
    }

    public void startGame()
    {
        CardPile p = new CardPile();
        putCardsIn(p);
        CardPile p2 = new CardPile();
        while (p.getSize() != 0)
        {
            int i = (int) (random() * p.getSize());
            p2.addCard(p.getCard(i));
            p.deleteCard(i);
        }
        player1 = new CardPile();
        player2 = new CardPile();
        player3 = new CardPile();
        extra = new CardPile();
        for (int i = 0; i <= 16; i++) player1.addCard(p2.getCard(i));
        for (int i = 17; i <= 33; i++) player2.addCard(p2.getCard(i));
        for (int i = 34; i <= 50; i++) player3.addCard(p2.getCard(i));
        for (int i = 51; i <= 53; i++) extra.addCard(p2.getCard(i));
    }

    public void putCardsIn(CardPile p)
    {
        for (int i = 0; i < 15; ++i)
        {
            int j = (i >= 13 ? 1 : 4);
            while (j-- > 0)
            {
                Card c = new Card(i);
                p.addCard(c);
            }
        }
    }
}
