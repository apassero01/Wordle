package wordlegroup;/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall2022* Instructor: Prof. Brian King
 *
 * Name: Andrew Passero
 * Section: Section 2 11am
 * Date: 10/12/22* Time: 11:47 AM
 *
 * Project: csci205_hw
 * Package: PACKAGE_NAME
 * Class: wordlegroup.DictionaryProcessor
 *
 * Description:
 *
 * *****************************************/

import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Child Class of TextProcessor */
public class DictionaryProcessor extends TextProcessor
{
    /** Set for valid set of words */
    protected Set<String> setOfWords;

    /**
     * Constructor for DictionaryProcessor
     * @param url
     */
    public DictionaryProcessor(String url)
    {
        super(url);
        this.setOfWords = new TreeSet<>();
    }

    /**
     * If the word from the dictionary meets the criteria for the game add the word to setOfWords
     * @param word
     */
    @Override
    protected void processWord(String word)
    {
        Pattern p = Pattern.compile("^[A-Z]{4,5}$");
        Matcher matcher = p.matcher(word);

        if ( matcher.matches() )
        {
            this.setOfWords.add(word.toLowerCase());
        }
    }

    /**
     * This function prints the total number of valid words
     */
    @Override
    protected void printReport()
    {
        System.out.println("Total number of Words: " + this.setOfWords.size());
    }

    /**
     * A getter function for setOfWords
     * @return setOfWords
     */
    @Override
    public Set<String> getSetOfWords()
    {
        return this.setOfWords;
    }

}