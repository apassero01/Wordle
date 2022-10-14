package wordlegroup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlphabetTest {
    private Alphabet alphabet;

    @BeforeEach
    void setup(){
        //Set up an instance of alphabet class before each test
        alphabet = new Alphabet();
    }

    @AfterEach
    void tearDown(){

    }


    @Test
    void compareWordsTest(){
        //Assert that the alphabet is full
        assertEquals(26,alphabet.getAlphabetSize());

        //Check that one charachter is incorrect and remove it from alphabet
        alphabet.compareWords("hello","yello");
        assertEquals(25,alphabet.getAlphabetSize());

    }


}

