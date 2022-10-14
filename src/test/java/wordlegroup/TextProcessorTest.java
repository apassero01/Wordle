package wordlegroup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextProcessorTest {
    private TextProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new TextProcessor("url");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void processWordTest(){
        //Assert that set of words is empty
        assertEquals(0,processor.totalWords);

        //Process brown twice so it gets added to set
        processor.processWord("brown");
        processor.processWord("brown");

        assertEquals(1,processor.getSetOfWords().size());



        // make sure it does not add six letter words
        processor.processWord("yellow");
        processor.processWord("yellow");
        assertEquals(2,processor.getTotalWordsDiscarded());
        assertEquals(1,processor.getSetOfWords().size());

        //make sure it removes punctuation
        processor.processWord("hello!");
        processor.processWord("hello!");
        assertEquals(2,processor.getSetOfWords().size());

        //Make sure it does not add capitalized words
        processor.processWord("Happy");
        processor.processWord("Happy");
        assertEquals(2,processor.getSetOfWords().size());


    }




}