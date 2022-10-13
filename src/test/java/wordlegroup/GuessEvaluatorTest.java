package wordlegroup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuessEvaluatorTest {

    private GuessEvaluator guessEvaluator;

    @BeforeEach
    void setUp() {
        guessEvaluator = new GuessEvaluator("hello");
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void analyzeGuessTest(){

        //Check to see that if guess is correct the result will be correct
        String correctAnswerResult = "*****";
        assertEquals(correctAnswerResult,this.guessEvaluator.analyzeGuess("hello"));

        //Check that the first letter from the guess comes back incorrect
        String firstIncorrectResult = "-****";
        assertEquals(firstIncorrectResult,this.guessEvaluator.analyzeGuess("yello"));

        //Check that the first letter is in the wrong place and the rest are incorrect
        // This also checks that the function checkIfCharInWord also works
        String InWrongSpotResult = "+----";
        assertEquals(InWrongSpotResult,this.guessEvaluator.analyzeGuess("lzzzz"));

    }




}