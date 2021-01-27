import java.util.Scanner;
import java.util.Random;

public class Game {
    public static Scanner input = new Scanner(System.in);
    public static Random random = new Random();
    public static String answer1, answer2, answer3, answer4, answer6, answer7, answer8 = "MM", highcard;
    public static String[] usernames = new String[3];
    public static int players, randmain, rand1, rand2, rand3, p1sum, dsum, answer5, p1min, p1mid, p1max, dmin, dmid, dmax, p1hand, dhand, p1pair = p1max, dpair = dmax, fbet;
    public static boolean[] playing = {false, false, false, false};
    public static String[] cards = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    public static int[] bjcardvalue = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
    public static int[] tcpcardvalue = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    public static int[] points = {100, 100, 100, 100};
    public static int[] bet = {0, 0, 0, 0};
    public static String[] suit = {"♦", "♣", "♥", "♠"};
    public static String[] p1card1 = new String[2];
    public static String[] p1card2 = new String[2];
    public static String[] p1card3 = new String[2];
    public static String[] dcard1 = new String[2];
    public static String[] dcard2 = new String[2];
    public static String[] dcard3 = new String[2];

    public static void main(String[] arg) {
        while (!answer8.equals("Q")) { //If user wants to quit, they type "Q" to stop the program
            if (answer8.equals("MM")) { //Allows user to pick different game or play different gamemode
                introanswer();
                SPorMP();
                bet();
            }
            // This part checks which game the user chose and checks how many players are playing
            if (answer1.equals("1")) {
                for (int i = 0; i < 4; i++) {
                    if (playing[i] == true) {
                        blackjack(i);
                    }
                }
            } else if (answer1.equals("2")) {
                for (int i = 0; i < 4; i++) {
                    if (playing[i] == true) {
                        threecardpoker(i);
                    }
                }
            }
            playagain();
        }
    }

    public static void introanswer() { //This method is the introduction to the code and determines what game the user wants to play
        System.out.println("Welcome to the casino! We hope you enjoy your stay!");
        System.out.println("We have two games right now, Blackjack or Three Card Poker.");
        System.out.println("Which of the two games would you like to play? Type 1 for Blackjack or 2 for Three Card Poker.");
        answer1 = input.nextLine();
        while (!answer1.equals("1") && !answer1.equals("2")) {
            System.out.println("Please type 1 for Blackjack or 2 for Three Card Poker.");
            answer1 = input.nextLine();
        }
        //Chooses which game rule method to play
        if (answer1.equals("1")) {
            bjrules();
        } else if (answer1.equals("2")) {
            tcprules();
        }
    }

    public static void bjrules() { //The blackjack rule method which asks if users wants to see it and if so it explains how to play
        System.out.println("Would you like to hear the rules for Blackjack? (Y/N)");
        System.out.println("The rules in our casino may be different from the rules that you know.");
        answer2 = input.nextLine();
        answer2 = answer2.toUpperCase();
        while (!answer2.equals("Y") && !answer2.equals("N")) {
            System.out.println("Please type Y if you would like to listen to the rules or N if not.");
            answer2 = input.nextLine();
            answer2 = answer2.toUpperCase();
        }
        if (answer2.equals("Y")) {
            System.out.println("The main point in Blackjack is to get your cards to add up to as close to 21 as possible.");
            System.out.println("You will get dealt 2 cards which you can look at and when it is your turn, you may asked for more cards or pass.");
            System.out.println("If you get Blackjack meaning you get a sum of 21, you will automatically win the game.");
            System.out.println("If you go over 21 though, you bust and automatically lose the game.");
            System.out.println("All number cards have the same value, all face cards are equal to 10 and the ace can be 1 or 11 (You must pick which before drawing cards and the value of ace cannot change after).");
            System.out.println("Bet up to 5 of your points and if you win, they get doubled but if you lose, you lose all bet points.");
            System.out.println("Whether you play against friends or the dealer, you will be trying to beat the dealer's hand.");
            System.out.println("If you go bankrupt, you will have to restart the game. Have fun!\n");
        }
        if (answer2.equals("N")) {
            System.out.println("Thank you.");
        }
    }

    public static void tcprules() { //The three card poker rule method which asks if users wants to see it and if so it explains how to play
        System.out.println("Would you like to hear the rules for Three Card Poker? (Y/N)");
        System.out.println("The rules in our casino may be different from the rules that you know.");
        answer3 = input.nextLine();
        answer3 = answer3.toUpperCase();
        while (!answer3.equals("Y") && !answer3.equals("N")) {
            System.out.println("Please type Y if you would like to listen to the rules or N if not.");
            answer3 = input.nextLine();
            answer3 = answer3.toUpperCase();
        }
        if (answer3.equals("Y")) {
            System.out.println("The main point in Three Card Poker is to get the best hand possible.");
            System.out.println("There are a variety of different combinations which will be explained here.");
            System.out.println("High Card: You have no combos so you go with your highest card.");
            System.out.println("Pair: Two of the same number.");
            System.out.println("Flush: All three cards in your hand have the same suit.");
            System.out.println("Straight: All three cards go consecutively like, 5,6,7. (Suit doesn't matter)");
            System.out.println("Three of a kind: All three cards have the same number.");
            System.out.println("Royal Flush: Your three cards have the same suit and go consecutively. (Basically a straight and flush combined)");
            System.out.println("Bet up to 5 of your points and if you win, they get doubled but if you lose, you lose all bet points.");
            System.out.println("You can also fold if your hand isn't good which will make you only lose half of your bet points.");
            System.out.println("Whether you play against friends or the dealer, you will be trying to beat the dealer's hand.");
            System.out.println("If you go bankrupt, you will have to restart the game. Have fun!\n");
        }
        if (answer3.equals("N")) {
            System.out.println("Thank you.");
        }
    }

    public static void SPorMP() { //Method asks the user if they want to play against the dealer only or play with other players as well
        System.out.println("Would you like to play singleplayer or multiplayer?");
        System.out.println("Type either SP for singleplayer or MP for multiplayer.");
        answer4 = input.nextLine();
        answer4 = answer4.toUpperCase();
        while (!answer4.equals("SP") && !answer4.equals("MP")) {
            System.out.println("Please type SP for singleplayer or MP for multiplayer.");
            answer4 = input.nextLine();
            answer4 = answer4.toUpperCase();
        }
        if (answer4.equals("SP")) {
            playing[0] = true;
            System.out.println("Please enter your name.");
            usernames[0] = input.nextLine();
        }
        if (answer4.equals("MP")) {
            System.out.println("How many players will be playing? (Max of 4)");
            players = input.nextInt();
            usernames = new String[players];
            while (players > 4 && players == 1) {
                System.out.println("There is a maximum of 4 players and you cannot have 1 player in multiplayer.");
                players = input.nextInt();
                usernames = new String[players];
            }
            //Makes the playing boolean true for the amount of players playing so it can repeat the method as much as needed
            if (players == 2) {
                playing[0] = true;
                playing[1] = true;
            }
            if (players == 3) {
                playing[0] = true;
                playing[1] = true;
                playing[2] = true;
            }
            if (players == 4) {
                playing[0] = true;
                playing[1] = true;
                playing[2] = true;
                playing[3] = true;
            }
            input.nextLine();
            for (int i = 0; i < players; i++) {
                System.out.println("Please enter player " + (i + 1) + "'s name.");
                usernames[i] = input.nextLine();
            }
        }
    }

    public static void bet() { //Allows the user to choose how many points they want to bet and different players can bet different amounts
        System.out.println("Everytime, you will start with 100 points. Bet up to 10 points each time and see if you can profit or if you lose.");
        if (playing[0] == true) {
            System.out.println("How many points would you like to bet, " + usernames[0] + "?");
            bet[0] = input.nextInt();
            while (bet[0] > 10) {
                System.out.println("The maximum bet is 10 points.");
                bet[0] = input.nextInt();
            }
        }
        if (playing[1] == true) {
            System.out.println("How many points would you like to bet, " + usernames[1] + "?");
            bet[1] = input.nextInt();
            while (bet[1] > 10) {
                System.out.println("The maximum bet is 10 points.");
                bet[1] = input.nextInt();
            }
        }
        if (playing[2] == true) {
            System.out.println("How many points would you like to bet, " + usernames[2] + "?");
            bet[2] = input.nextInt();
            while (bet[2] > 10) {
                System.out.println("The maximum bet is 10 points.");
                bet[2] = input.nextInt();
            }
        }
        if (playing[3] == true) {
            System.out.println("How many points would you like to bet, " + usernames[3] + "?");
            bet[3] = input.nextInt();
            while (bet[3] > 10) {
                System.out.println("The maximum bet is 10 points.");
                bet[3] = input.nextInt();
            }
        }
    }

    public static void blackjack(int i) { //The method for the blackjack game which uses parameters to get each user's name and bet
        System.out.println("Dealing 2 cards to " + usernames[i] + " and the dealer.");
        // Generating random cards for user (Number and suit)
        rand1 = random.nextInt(13);
        p1card1[0] = cards[rand1];
        randmain = random.nextInt(4);
        p1card1[1] = suit[randmain];
        rand2 = random.nextInt(13);
        p1card2[0] = cards[rand2];
        randmain = random.nextInt(4);
        p1card2[1] = suit[randmain];
        //Makes sure there is no two cards that are the same for the user
        if (p1card1[0].equals(p1card2[0]) && p1card1[1].equals(p1card2[1])) {
            rand2 = random.nextInt(13);
            p1card2[0] = cards[rand2];
            randmain = random.nextInt(4);
            p1card2[1] = suit[randmain];
        }
        System.out.println(usernames[i] + ", your cards are " + p1card1[0] + p1card1[1] + " and " + p1card2[0] + p1card2[1] + ".");
        //Ace can be equal to 1 or 11 so this gives the user the choice to pick which one they would like
        if (p1card1[0].equals("A")) {
            System.out.println("Would you like your ace to be equal to 1 or 11?");
            answer5 = input.nextInt();
            p1sum += answer5;
            while (answer5 != 1 && answer5 != 11) {
                System.out.println("Ace can only be equal to 1 or 11. Which would you like to pick?");
                answer5 = input.nextInt();
                p1sum += answer5;
            }
        }
        if (p1card2[0].equals("A")) {
            System.out.println("Would you like your ace to be equal to 1 or 11?");
            answer5 = input.nextInt();
            p1sum += answer5;
            while (answer5 != 1 && answer5 != 11) {
                System.out.println("Ace can only be equal to 1 or 11. Which would you like to pick?");
                answer5 = input.nextInt();
                p1sum += answer5;
            }
        }
        p1sum = p1sum + bjcardvalue[rand1] + bjcardvalue[rand2];
        if (bjcardvalue[rand1] == 1) {
            p1sum -= 1;
        }
        if (bjcardvalue[rand2] == 1) {
            p1sum -= 1;
        }
        //Shows total sum of your two cards
        System.out.println(usernames[i] + ", you have a value of " + p1sum + " from your cards.");
        if (p1sum == 21) {
            points[i] += bet[i];
            System.out.println(usernames[i] + ", you got blackjack! You earned " + bet[i] + " with a total balance of " + points[i] + ".\n");
        } else {
            //Allows user to draw another card if the sum they got was too low
            System.out.println("Would you like to draw another card? (Y/N)");
            answer6 = input.nextLine();
            answer6 = answer6.toUpperCase();
            while (!answer6.equals("Y") && !answer6.equals("N")) {
                System.out.println("Please type Y if you want to draw a card or N if not.");
                answer6 = input.nextLine();
                answer6 = answer6.toUpperCase();
            }
            while (answer6.equals("Y")) {
                rand1 = random.nextInt(13);
                p1card1[0] = cards[rand1];
                randmain = random.nextInt(4);
                p1card1[1] = suit[randmain];
                System.out.println("The card you drew was a " + p1card1[0] + p1card1[1] + ".");
                if (p1card1[0].equals("A")) {
                    System.out.println("Would you like your ace to be equal to 1 or 11?");
                    answer5 = input.nextInt();
                    p1sum += answer5;
                    while (answer5 != 1 && answer5 != 11) {
                        System.out.println("Ace can only be equal to 1 or 11. Which would you like to pick?");
                        answer5 = input.nextInt();
                        p1sum += answer5;
                    }
                }
                p1sum = p1sum + bjcardvalue[rand1];
                if (bjcardvalue[rand1] == 1) {
                    p1sum -= 1;
                }
                if (bjcardvalue[rand2] == 1) {
                    p1sum -= 1;
                }
                System.out.println(usernames[i] + ", you have a value of " + p1sum + " from your cards.");
                if (p1sum > 21) {
                    points[i] -= bet[i];
                    System.out.println(usernames[i] + ", you busted! You lost " + bet[i] + " with a total balance of " + points[i] + ".\n");
                    answer6 = "E";
                } else if (p1sum == 21) {
                    points[i] += bet[i];
                    System.out.println(usernames[i] + ", you got blackjack! You earned " + bet[i] + " with a total balance of " + points[i] + ".\n");
                    answer6 = "E";
                } else if (p1sum < 21) {
                    System.out.println("Would you like to draw again?");
                    answer6 = input.nextLine();
                    answer6 = answer6.toUpperCase();
                    while (!answer6.equals("Y") && !answer6.equals("N")) {
                        System.out.println("Please type Y if you want to draw a card or N if not.");
                        answer6 = input.nextLine();
                        answer6 = answer6.toUpperCase();
                    }
                }
            }
            //Goes on to dealer once the user is done with their turn
            if (answer6.equals("N")) {
                rand1 = random.nextInt(13);
                dcard1[0] = cards[rand1];
                randmain = random.nextInt(4);
                dcard1[1] = suit[randmain];
                rand2 = random.nextInt(13);
                dcard2[0] = cards[rand2];
                randmain = random.nextInt(4);
                dcard2[1] = suit[randmain];
                if (dcard1[0].equals(dcard2[0]) && dcard1[1].equals(dcard2[1])) {
                    rand2 = random.nextInt(13);
                    dcard2[0] = cards[rand2];
                    randmain = random.nextInt(4);
                    dcard2[1] = suit[randmain];
                }
                System.out.println("Dealer drew " + dcard1[0] + dcard1[1] + " and " + dcard2[0] + dcard2[1] + ".");
                if (dcard1[0].equals("A")) {
                    dsum += 11;
                }
                if (dcard2[0].equals("A")) {
                    if (dsum == 11) {
                        dsum += 1;
                    } else {
                        dsum += 11;
                    }
                }
                dsum = dsum + bjcardvalue[rand1] + bjcardvalue[rand2];
                if (bjcardvalue[rand1] == 1) {
                    dsum -= 1;
                }
                if (bjcardvalue[rand2] == 1) {
                    dsum -= 1;
                }
                System.out.println("Dealer has a value of " + dsum + " from his cards.");
                if (dsum == 21) {
                    points[i] -= bet[i];
                    System.out.println("Dealer got blackjack! " + usernames[i] + ", you lost " + bet[i] + " with a total balance of " + points[i] + ".\n");
                } else {
                    while (dsum <= 17) {
                        rand1 = random.nextInt(13);
                        dcard1[0] = cards[rand1];
                        randmain = random.nextInt(4);
                        dcard1[1] = suit[randmain];
                        System.out.println("Dealer drew " + dcard1[0] + dcard1[1] + ".");
                        if (dcard1[0].equals("A")) {
                            dsum += 1;
                        }
                        dsum = dsum + bjcardvalue[rand1];
                        if (bjcardvalue[rand1] == 1) {
                            dsum -= 1;
                        }
                        System.out.println("Dealer has a value of " + dsum + " from his cards.");
                    }
                    //Checking who won, dealer or the user and giving or deduction points based on results
                    if (dsum > 21) {
                        points[i] += bet[i];
                        System.out.println("Dealer busted! " + usernames[i] + ", you earned " + bet[i] + " with a total balance of " + points[i] + ".\n");
                    } else if (dsum == 21) {
                        points[i] -= bet[i];
                        System.out.println("Dealer got blackjack! " + usernames[i] + ", you lost " + bet[i] + " with a total balance of " + points[i] + ".\n");
                    } else if (p1sum > dsum) {
                        points[i] += bet[i];
                        System.out.println(usernames[i] + ", you beat the dealer! You earned " + bet[i] + " with a total balance of " + points[i] + ".\n");
                    } else if (dsum > p1sum) {
                        points[i] -= bet[i];
                        System.out.println("Dealer beat you! " + usernames[i] + ", you lost " + bet[i] + " with a total balance of " + points[i] + ".\n");
                    } else if (dsum == p1sum) {
                        System.out.println(usernames[i] + ", you and the dealer tied! You got back " + bet[i] + " with a total balance of " + points[i] + ".\n");
                    }
                }
            }
        }
        //Resetting sum for the next player
        p1sum = 0;
        dsum = 0;
    }

    public static void threecardpoker(int i) { //The method for the blackjack game which uses parameters to get each user's name and bet
        System.out.println("Dealing three cards to " + usernames[i] + " and the dealer.");
        //Generates random cards for user
        rand1 = random.nextInt(13);
        p1card1[0] = cards[rand1];
        randmain = random.nextInt(4);
        p1card1[1] = suit[randmain];
        rand2 = random.nextInt(13);
        p1card2[0] = cards[rand2];
        randmain = random.nextInt(4);
        p1card2[1] = suit[randmain];
        rand3 = random.nextInt(13);
        p1card3[0] = cards[rand3];
        randmain = random.nextInt(4);
        p1card3[1] = suit[randmain];
        //Makes sure there is no two cards that are the same for the user
        if (p1card1[0].equals(p1card2[0]) && p1card1[1].equals(p1card2[1])) {
            rand2 = random.nextInt(13);
            p1card2[0] = cards[rand2];
            randmain = random.nextInt(4);
            p1card2[1] = suit[randmain];
        }
        if (p1card1[0].equals(p1card3[0]) && p1card1[1].equals(p1card3[1])) {
            rand3 = random.nextInt(13);
            p1card3[0] = cards[rand3];
            randmain = random.nextInt(4);
            p1card3[1] = suit[randmain];
        }
        if (p1card2[0].equals(p1card3[0]) && p1card2[1].equals(p1card3[1])) {
            rand3 = random.nextInt(13);
            p1card3[0] = cards[rand3];
            randmain = random.nextInt(4);
            p1card3[1] = suit[randmain];
        }
        System.out.println(usernames[i] + ", your cards are " + p1card1[0] + p1card1[1] + ", " + p1card2[0] + p1card2[1] + " and " + p1card3[0] + p1card3[1] + ".");
        System.out.println("You now have a choice to fold, meaning you don't play or to play.");
        System.out.println("If you fold, you get back half of your bet points and if you play, you and the dealer's cards get compared.");
        //Lets the user fold to lose less points or play if they want to go against the dealer
        System.out.println("Would you like to fold or play? (F/P)");
        answer7 = input.nextLine();
        answer7 = answer7.toUpperCase();
        while (!answer7.equals("F") && !answer7.equals("P")) {
            System.out.println("Please enter F for fold or P for play.");
            answer7 = input.nextLine();
            answer7 = answer7.toUpperCase();
        }
        if (answer7.equals("F")) {
            //Lose less points for folding
            fbet = bet[i] - (bet[i] / 2);
            points[i] -= fbet;
            System.out.println(usernames[i] + ", you fold! You lost " + fbet + " and have a total balance of " + points[i] + ".\n");
        }
        if (answer7.equals("P")) {
            System.out.println("Calculating what hand you have.");
            //This part calculates the maximum, medium and minimum of the three cards which allows program to determine the user's hand
            if (tcpcardvalue[rand1] > tcpcardvalue[rand2]) {
                if (tcpcardvalue[rand1] > tcpcardvalue[rand3]) {
                    p1max = tcpcardvalue[rand1];
                    if (tcpcardvalue[rand2] > tcpcardvalue[rand3]) {
                        p1mid = tcpcardvalue[rand2];
                        p1min = tcpcardvalue[rand3];
                    } else {
                        p1mid = tcpcardvalue[rand3];
                        p1min = tcpcardvalue[rand2];
                    }
                } else {
                    p1mid = tcpcardvalue[rand1];
                    p1max = tcpcardvalue[rand3];
                    p1min = tcpcardvalue[rand2];
                }
            } else {
                if (tcpcardvalue[rand2] > tcpcardvalue[rand3]) {
                    p1max = tcpcardvalue[rand2];
                    if (tcpcardvalue[rand1] > tcpcardvalue[rand3]) {
                        p1mid = tcpcardvalue[rand1];
                        p1min = tcpcardvalue[rand3];
                    } else {
                        p1mid = tcpcardvalue[rand3];
                        p1min = tcpcardvalue[rand1];
                    }
                } else {
                    p1mid = tcpcardvalue[rand2];
                    p1max = tcpcardvalue[rand3];
                    p1min = tcpcardvalue[rand1];
                }
            }
            //Sets variable for highcard so the user knows which card is their highcard
            if (p1max == tcpcardvalue[rand1]) {
                highcard = cards[rand1];
            }
            if (p1max == tcpcardvalue[rand2]) {
                highcard = cards[rand2];
            }
            if (p1max == tcpcardvalue[rand3]) {
                highcard = cards[rand3];
            }
            //Displays which hand the user has
            if (p1min + 1 == p1mid && p1min + 2 == p1max && p1card1[1].equals(p1card2[1]) && p1card2[1].equals(p1card3[1])) {
                System.out.println(usernames[i] + ", you got a royal flush! Let's see what the dealer has.");
                p1hand = 6;
            } else if (p1min + 1 == p1mid && p1min + 2 == p1max) {
                System.out.println(usernames[i] + ", you got a straight! Let's see what the dealer has.");
                p1hand = 4;
            } else if (p1card1[1].equals(p1card2[1]) && p1card2[1].equals(p1card3[1])) {
                System.out.println(usernames[i] + ", you got a flush! Let's see what the dealer has.");
                p1hand = 3;
            } else if (p1card1[0].equals(p1card2[0]) && p1card1.equals(p1card3[0])) {
                System.out.println(usernames[i] + ", you got a three of a kind. Let's see what the dealer has.");
                p1hand = 5;
            } else if (p1card1[0].equals(p1card2[0]) || p1card1[0].equals(p1card3[0]) || p1card2[0].equals(p1card3[0])) {
                System.out.println(usernames[i] + ", you got a pair. Let's see what the dealer has.");
                p1hand = 2;
            } else {
                System.out.println(usernames[i] + ", you have a high card of " + highcard + ". Let's see what the dealer has.");
                p1hand = 1;
            }
            //Generates random dealer cards
            rand1 = random.nextInt(13);
            dcard1[0] = cards[rand1];
            randmain = random.nextInt(4);
            dcard1[1] = suit[randmain];
            rand2 = random.nextInt(13);
            dcard2[0] = cards[rand2];
            randmain = random.nextInt(4);
            dcard2[1] = suit[randmain];
            rand3 = random.nextInt(13);
            dcard3[0] = cards[rand3];
            randmain = random.nextInt(4);
            dcard3[1] = suit[randmain];
            //Makes sure the dealer doesn't have two cards which are the same
            if (dcard1[0].equals(dcard2[0]) && dcard1[1].equals(dcard2[1])) {
                rand2 = random.nextInt(13);
                dcard2[0] = cards[rand2];
                randmain = random.nextInt(4);
                dcard2[1] = suit[randmain];
            }
            if (dcard1[0].equals(dcard3[0]) && dcard3[1].equals(dcard3[1])) {
                rand3 = random.nextInt(13);
                dcard3[0] = cards[rand3];
                randmain = random.nextInt(4);
                dcard3[1] = suit[randmain];
            }
            if (dcard2[0].equals(dcard3[0]) && dcard2[1].equals(dcard3[1])) {
                rand3 = random.nextInt(13);
                dcard3[0] = cards[rand3];
                randmain = random.nextInt(4);
                dcard3[1] = suit[randmain];
            }
            System.out.println("The dealers cards are " + dcard1[0] + dcard1[1] + ", " + dcard2[0] + dcard2[1] + " and " + dcard3[0] + dcard3[1] + ".");
            System.out.println("Calculating what hand the dealer has.");
            //Calculates the dealer's max, mid and min cards
            if (tcpcardvalue[rand1] > tcpcardvalue[rand2]) {
                if (tcpcardvalue[rand1] > tcpcardvalue[rand3]) {
                    dmax = tcpcardvalue[rand1];
                    if (tcpcardvalue[rand2] > tcpcardvalue[rand3]) {
                        dmid = tcpcardvalue[rand2];
                        dmin = tcpcardvalue[rand3];
                    } else {
                        dmid = tcpcardvalue[rand3];
                        dmin = tcpcardvalue[rand2];
                    }
                } else {
                    dmid = tcpcardvalue[rand1];
                    dmax = tcpcardvalue[rand3];
                    dmin = tcpcardvalue[rand2];
                }
            } else {
                if (tcpcardvalue[rand2] > tcpcardvalue[rand3]) {
                    dmax = tcpcardvalue[rand2];
                    if (tcpcardvalue[rand1] > tcpcardvalue[rand3]) {
                        dmid = tcpcardvalue[rand1];
                        dmin = tcpcardvalue[rand3];
                    } else {
                        dmid = tcpcardvalue[rand3];
                        dmin = tcpcardvalue[rand1];
                    }
                } else {
                    dmid = tcpcardvalue[rand2];
                    dmax = tcpcardvalue[rand3];
                    dmin = tcpcardvalue[rand1];
                }
            }
            //Figures out with hand the dealer has
            if (dmin + 1 == dmid && dmin + 2 == dmax && dcard1[1].equals(dcard2[1]) && dcard2[1].equals(dcard3[1])) {
                dhand = 6;
            } else if (dmin + 1 == dmid && dmin + 2 == dmax) {
                dhand = 4;
            } else if (dcard1[1].equals(dcard2[1]) && dcard2[1].equals(dcard3[1])) {
                dhand = 3;
            } else if (dcard1[0].equals(dcard2[0]) && dcard1.equals(dcard3[0])) {
                dhand = 5;
            } else if (dcard1[0].equals(dcard2[0]) || dcard1[0].equals(dcard3[0]) || dcard2[0].equals(dcard3[0])) {
                dhand = 2;
            } else {
                dhand = 1;
            }
            //Compares the user's hand to the dealers hand
            if (p1hand > dhand) {
                points[i] += bet[i];
                System.out.println(usernames[i] + ", you had the better hand! You gained " + bet[i] + " and have a total balance of " + points[i] + ".\n");
            } else if (dhand > p1hand) {
                points[i] -= bet[i];
                System.out.println("Dealer had the better hand! " + usernames[i] + ", you lost " + bet[i] + " and have a total balance of " + points[i] + ".\n");
                //Find the highest pair if both dealer and user has pair
            } else if (dhand == p1hand) {
                if (p1hand == 2) {
                    if (p1mid == p1min) {
                        p1pair = p1mid;
                    }
                    if (dmid == dmin) {
                        dpair = dmid;
                    }
                    if (p1pair > dpair) {
                        points[i] += bet[i];
                        System.out.println(usernames[i] + ", you had the better hand! You gained " + bet[i] + " and have a total balance of " + points[i] + ".\n");
                    }
                    if (dpair > p1pair) {
                        points[i] -= bet[i];
                        System.out.println("Dealer had the better hand! " + usernames[i] + ", you lost " + bet[i] + " and have a total balance of " + points[i] + ".\n");
                    }
                    if (dpair == p1pair) {
                        System.out.println(usernames[i] + ", you tied! You got back " + bet[i] + " and have a total balance of " + points[i] + ".\n");

                    }
                } else {
                    //If the user has the same type of hand as the dealer, they find out who has the largest max card or if max is same then it's a tie
                    if (p1max > dmax) {
                        points[i] += bet[i];
                        System.out.println(usernames[i] + ", you had the better hand! You gained " + bet[i] + " and have a total balance of " + points[i] + ".\n");
                    }
                    if (dmax > p1max) {
                        points[i] -= bet[i];
                        System.out.println("Dealer had the better hand! " + usernames[i] + ", you lost " + bet[i] + " and have a total balance of " + points[i] + ".\n");
                    }
                    if (dmax == p1max) {
                        System.out.println(usernames[i] + ", you tied! You got back " + bet[i] + " and have a total balance of " + points[i] + ".\n");
                    }
                }
            }
        }
    }

    public static void playagain() {
        //Allows the user 3 choices when they finish a round, play again (same game, same mode), main menu to switch game or mode, or quit
        System.out.println("Would you like to play again?");
        System.out.println("Type MM to go back to the main menu (switch games), type RP to replay the game or type Q to quit.");
        answer8 = input.nextLine();
        answer8 = answer8.toUpperCase();
        while (!answer8.equals("MM") && !answer8.equals("RP") && !answer8.equals("Q")) {
            System.out.println("Type MM to go back to the main menu (switch games), type RP to replay the game or type Q to quit.");
            answer8 = input.nextLine();
            answer8 = answer8.toUpperCase();
        }
        //If the user goes to main menu, it will display points and then reset because
        if (answer8.equals("MM")) {
            for (int i = 0; i < 4; i++) {
                if (playing[i] == true) {
                    System.out.println(usernames[i] + " finished the game with a total balance of " + points[i] + ".");
                    points[i] = 100;
                    playing[i] = false;
                }
            }
            System.out.println();
        }
        //If the user wants to quit, it displays their final balance to determine if they profited or lost points and then thanks them for playing
        if (answer8.equals("Q")) {
            for (int i = 0; i < 4; i++) {
                if (playing[i] == true) {
                    System.out.println(usernames[i] + " finished the game with a total balance of " + points[i] + ".");
                }
            }
            System.out.println("Thank you for playing!");
        }
    }
}