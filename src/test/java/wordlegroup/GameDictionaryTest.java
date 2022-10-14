package wordlegroup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameDictionaryTest
{

    @BeforeEach
    void setUp()
    {

    }

    /**
     * test getting list from words.txt file
     */
    @Test
    void getMasterFromFile()
    {
        //Confirm size of word list is zero before reading file
        GameDictionary gameDictionary = new GameDictionary("words.txt");
        assertEquals(gameDictionary.getWordSet().size(), 0);
        gameDictionary.getMasterFromFile();
        //confirm size of word list is greater than zero after the file
        assertTrue(gameDictionary.getWordSet().size() > 0);
    }

    /**
     * Test to ensure master dictionary is created
     */
    @Test
    void createMaster()
    {
        GameDictionary gameDictionary = new GameDictionary();
        assertEquals(gameDictionary.getWordSet().size(), 0);
        gameDictionary.createMaster();
        assertTrue(gameDictionary.getWordSet().size() > 0);

    }

}