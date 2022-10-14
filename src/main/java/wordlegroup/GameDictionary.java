/* *****************************************

 * CSCI205 - Software Engineering and Design

 * Fall 2022

 * Instructor: Prof. Brian King

 *

 * Name: Patrick Quinlivan

 * Section: 11:00 AM

 * Date: 10/12/22

 * Time: 11:10 AM

 *

 * Project: csci205_hw

 * Package: wordlegroup

 * Class: GameDictionary *

 * Description:

 *

 * ****************************************

 */

package wordlegroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class GameDictionary
{
    /** A URL to Dictionary */
    private final String MAST_DICT_URL = "https://www.gutenberg.org/cache/epub/29765/pg29765.txt";

    /** List of URLs to text */
    private final String[] LIST_OF_TEXT_URLS = {"https://www.gutenberg.org/files/1342/1342-0.txt",
                                                "https://www.gutenberg.org/cache/epub/5197/pg5197.txt",
                                                "https://www.gutenberg.org/cache/epub/100/pg100.txt",
                                                "https://www.gutenberg.org/cache/epub/215/pg215.txt",
                                                "https://www.gutenberg.org/files/98/98-0.txt"};

    /** Class TextProcessor used for getting valid words from text */
    private TextProcessor textProcessor;

    /** Dictionary Processor used for getting words from dictionary */
    private DictionaryProcessor dictionaryProcessor;

    /** Set of valid words */
    private Set<String> wordSet;

    /** Name of file used */
    private String fileName;

    /**
     * Constructor for Game dictionary
     * @param fileName
     */
    public GameDictionary(String fileName)
    {
        this.wordSet = new TreeSet<>();
        this.fileName = fileName;
    }

    /**
     * Another constructor for game dictionary
     */
    public GameDictionary()
    {
        this.wordSet = new TreeSet<>();
    }

    /**
     * If a file is given add words from the file to the word set
     */
    public void getMasterFromFile()
    {
        File wordFile = new File(this.fileName);
        try( Scanner in = new Scanner(wordFile) )
        {
            while( in.hasNext() )
            {
                this.wordSet.add(in.next());
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Using the dictionary and links to text this function proccesses both dictionary and text
     * and adds words from both sets to the word set
     */
    public void createMaster()
    {
        Set<String> englishWords;
        Set<String> wordsInTexts = new TreeSet<>();

        dictionaryProcessor = new DictionaryProcessor(MAST_DICT_URL);
        dictionaryProcessor.processTextAtURL();
        englishWords = dictionaryProcessor.getSetOfWords();


        for (int i = 0; i < LIST_OF_TEXT_URLS.length; i++)
        {
            textProcessor = new TextProcessor(LIST_OF_TEXT_URLS[i]);
            textProcessor.processTextAtURL();
            wordsInTexts = textProcessor.getSetOfWords();

            for (String word:wordsInTexts)
            {
                //Check if the word is in dictionary or plural if non plural word is in dictionary.
                if( englishWords.contains(word) ||
                        (word.charAt(4) == 's' && englishWords.contains(word.substring(0,4))))
                {
                    wordSet.add(word);
                }
            }
        }
        writeToFile();
    }

    /**
     * This function adds all words from wordSet to file words.txt
     */
    public void writeToFile()
    {
        try(PrintWriter out = new PrintWriter("words.txt");)
        {
            for (String word:wordSet)
            {
                out.println(word);
            }
        }
        catch (FileNotFoundException e )
        {
            System.out.println(e);
        }

    }

    /**
     * This function returns a random word from word set
     * @return a random word
     */
    public String selectRandomWord()
    {
        String[] arrayOfWords = wordSet.toArray(new String[wordSet.size()]);
        Random random = new Random();
        int randNumber = random.nextInt(arrayOfWords.length);

        return arrayOfWords[randNumber];
    }

    /**
     * A getter function for Set<String> wordSet
     * @return
     */
    public Set<String> getWordSet()
    {
        return wordSet;
    }
}
