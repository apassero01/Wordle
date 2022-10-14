package wordlegroup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryProcessorTest {
    /**
     * Initilize a DictionaryProcessor
     */
    private DictionaryProcessor processor;

    @BeforeEach
    void setup(){
        //set up processor before test
        processor = new DictionaryProcessor("url");

    }

    @AfterEach
    void teardown(){

    }

    @Test
    void wordProcessorTest(){
        //Make sure setOfWords is empty to start
        assertEquals(0,processor.getSetOfWords().size());

        //Make sure it adds HELLO
        processor.processWord("HELLO");
        assertEquals(1,processor.getSetOfWords().size());

        //make sure it does not add words that are too short
        processor.processWord("HI");
        assertEquals(1,processor.getSetOfWords().size());

        //Make sure it does not add words that are too long
        processor.processWord("YELLOW");
        assertEquals(1,processor.getSetOfWords().size());

        //Make sure it only adds words that are upper case
        processor.processWord("hello");
        assertEquals(1,processor.getSetOfWords().size());



    }
}