/* *****************************************

 * CSCI205 - Software Engineering and Design

 * Fall 2022

 * Instructor: Prof. Brian King

 *

 * Name: Patrick Quinlivan

 * Section: 11:00 AM

 * Date: 10/12/22

 * Time: 11:09 AM

 *

 * Project: csci205_hw

 * Package: wordlegroup

 * Class: Wordle *

 * Description:

 *

 * ****************************************

 */

package wordlegroup;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Enumertaion type for the current state of the game
 */
enum GameState
{
    NO_GAME,
    GAME_IN_PROGRESS,
    GAME_WINNER,
    GAME_LOSER
}
public class Wordle {

    /** Scanner for users guess */
    private static Scanner scnr;

    /** Counter for number of guesses */
    private int guessNumber;

    /** A string for the users current guess */
    private String currentGuess;

    /** The word that the user is trying to guess */
    private String correctWord;

    /** Boolean stating if the user's guess was correct */
    private boolean isCorrect;
    /** GuessEvaluator to evaluate the users guess */
    private GuessEvaluator guessEvaluator;

    /**
     * GameState enumeration type forThe current state of the game
     */
    private GameState state;

    /** GameDictionary class for the words that the user will attempt to guess */
    private GameDictionary gameDictionary;

    /** Alphabet class to list to the user the possible letters in the correct word */
    private Alphabet alphabet;

    /** Constructor for Wordle() class */
    public Wordle()
    {
        this.state = GameState.NO_GAME;
        this.scnr = new Scanner(System.in);
        this.alphabet = new Alphabet();
    }

    /**
     * This Function runs as the user plays the game
     */
    public void playGame()
    {
        if (this.state == GameState.GAME_IN_PROGRESS)
        {
            System.out.println("Invalid State");
            return;
        }
        this.guessNumber = 0;
        this.initGame();
        do
        {
            this.alphabet.displayAlphabet();
            this.getGuess();
            this.checkGuess();
            this.alphabet.compareWords(this.correctWord,this.currentGuess);
            this.guessNumber += 1;

        }while(!this.isCorrect && this.guessNumber <= 5);

        if ( this.isCorrect ){this.state = GameState.GAME_WINNER;}
        else{this.state = GameState.GAME_LOSER;}
        checkGameResults();
    }

    /**
     * Function that tells the users if they won or lost
     * If they lost tell the user the correct word
     */
    public void checkGameResults()
    {
        if (this.state == GameState.GAME_WINNER)
        {
            System.out.println("Congratulations you won!!!!!");
        }
        else
        {
            System.out.println("You lost Try again");
            System.out.println("Correct word is " + this.correctWord);
        }
        this.state = GameState.NO_GAME;
    }


    /**
     * Function to start the game
     */
    public void initGame()
    {
        if (this.state == GameState.GAME_IN_PROGRESS)
        {
            System.out.println("Invalid State");
            return;
        }

        File f = new File("words.txt");
        boolean generateNew = true;
        if (f.exists())
        {
            String input;
            Pattern p = Pattern.compile("^[YyNn]$");
            Matcher matcher;
            System.out.println("A word Dictionary already exists, would you like to generate a new one? [Y|N]");
            do
            {
                input = scnr.next().toLowerCase();
                matcher = p.matcher(input);
                if (!matcher.matches())
                {
                    System.out.println("Y|N");
                }
            } while (!matcher.matches());

            if (input.equals("n")){generateNew = false;}
        }
        initDictionary(generateNew);
        this.correctWord = this.gameDictionary.selectRandomWord();
        this.guessEvaluator = new GuessEvaluator(this.correctWord);
        this.state = GameState.GAME_IN_PROGRESS;
    }

    /**
     * Function to start the dictionary
     * if generateNew is true generate a dictionary, if not use the words from file words.txt
     * @param generateNew a boolean determingn whether r not a new GameDictionary is created or
     *                    using words from words.txt
     */
    public void initDictionary(boolean generateNew)
    {
        if (this.state == GameState.GAME_IN_PROGRESS)
        {
            System.out.println("Invalid Game state for creating Dictionary");
            return;
        }
        if (generateNew)
        {
            System.out.println("Creating Dictionary.......");
            this.gameDictionary = new GameDictionary();
            this.gameDictionary.createMaster();
        }
        else
        {
            this.gameDictionary = new GameDictionary("words.txt");
            this.gameDictionary.getMasterFromFile();
        }
    }

    /**
     * Get the users guess
     */
    public void getGuess()
    {
        if ( this.state != GameState.GAME_IN_PROGRESS)
        {
            System.out.println("Invalid State");
            return;
        }
        Pattern p = Pattern.compile("^[A-Za-z]{5}$");
        Matcher matcher;
        String currentGuess;
        do
        {
            System.out.println("Please Input a 5 letter word");
            currentGuess = scnr.next();
            matcher = p.matcher(currentGuess);
            if (!matcher.matches())
            {
                System.out.println("Not a valid 5 letter word");
            }
            else if ( !this.gameDictionary.getWordSet().contains(currentGuess) )
            {
                System.out.println("Word not in Dictionary");
            }
        }while(!matcher.matches() || !this.gameDictionary.getWordSet().contains(currentGuess));

        this.currentGuess = currentGuess.toLowerCase();
    }

    /**
     * Check the users gues
     */
    public void checkGuess()
    {
        if ( this.state != GameState.GAME_IN_PROGRESS)
        {
            System.out.println("Invalid State");
            return;
        }
        String results = this.guessEvaluator.analyzeGuess(this.currentGuess);

        System.out.println("Your Progress is: ");
        System.out.println(results);
        if ( results.equals("*****")){this.isCorrect = true;}
        else{this.isCorrect = false;}

    }


    /**
     * Main method for Wordle
     * @param args
     */
    public static void main(String[] args)
    {
        Pattern p = Pattern.compile("^[YyNn]$");
        Matcher matcher;
        boolean keepPlaying = false;
        String input;
        do
        {
            Wordle wordle = new Wordle();
            wordle.playGame();
            System.out.println("Would you like to play again [Y|N]");
            do
            {
                input = scnr.next().toLowerCase();
                matcher = p.matcher(input);
                if (!matcher.matches())
                {
                    System.out.println("Y|N");
                }
            }while(!matcher.matches());


            if (input.toLowerCase().equals("y")){keepPlaying = true;}
            else{keepPlaying = false;}

        }while(keepPlaying);



    }
}
