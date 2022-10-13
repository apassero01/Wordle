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
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class GameDictionary
{
    private final String MAST_DICT_URL = "https://www.gutenberg.org/cache/epub/29765/pg29765.txt";

    private final String[] LIST_OF_TEXT_URLS = {"https://www.gutenberg.org/files/1342/1342-0.txt",
                                                "https://www.gutenberg.org/cache/epub/5197/pg5197.txt",
                                                "https://www.gutenberg.org/cache/epub/100/pg100.txt",
                                                "https://www.gutenberg.org/cache/epub/215/pg215.txt",
                                                "https://www.gutenberg.org/files/98/98-0.txt"};

    private TextProcessor textProcessor;

    private DictionaryProcessor dictionaryProcessor;

    private Set<String> wordSet;

    private String fileName;

    public GameDictionary(String fileName)
    {
        this.wordSet = new TreeSet<>();
        this.fileName = fileName;
    }
    public GameDictionary()
    {
        this.wordSet = new TreeSet<>();
    }

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
                if( englishWords.contains(word))
                {
                    wordSet.add(word);
                }
            }
        }
        writeToFile();
    }

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
    public String selectRandonWord()
    {
        return " ";
    }




}
