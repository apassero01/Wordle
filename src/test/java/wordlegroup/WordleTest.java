package wordlegroup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest
{

    @Test
    /**
     * Test initGame not sure how to test for user input
     */
    void initGame()
    {
//        Wordle wordle = new Wordle();
//        assertEquals(wordle.getState(), GameState.NO_GAME);
//        wordle.initGame();
//        assertEquals(wordle.getState(), GameState.GAME_IN_PROGRESS);
    }

    @Test
    void initDictionary()
    {
        Wordle wordle = new Wordle();
        wordle.initDictionary(false);
        //I am not sure how to test this because it is making changes to a private member (gameDictionary has all the changes after this call)
    }

    @Test
    void checkGuess()
    {
        Wordle wordle = new Wordle();
        wordle.checkGuess();
        //Also not sure how to test this because it makes no changes and only outputs a string
        //I could change this to make it testable but the functionality is fine
    }
}