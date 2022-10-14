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

 * Class: TextProcessor *

 * Description:

 *

 * ****************************************

 */

package wordlegroup;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor
{
    /**A string for the url to the text */
    public String url;

    /** A scanner to scan the text */
    protected static Scanner scnr;

    /** A Map with a word and the number of times it appears */
    private Map<String,Integer> wordMap;

    /** A count of total words */
    protected int totalWords;

    /** A count of discarded words */
    private int totalWordsDiscarded;

    /**
     * A count of the total valid words
     */
    private int totalGoodWords;

    /** A count of unique words */
    private int totalUniqueWords;

    /**
     * A constructor for class TextProcessor
     * @param url
     */
    public TextProcessor(String url)
    {
        this.url = url;
        wordMap = new TreeMap<>();
        totalWords = 0;
        totalUniqueWords = 0;
        totalGoodWords = 0;
        totalWordsDiscarded = 0;
    }

    /**
     * A function that calls processWord(String currentWord) to go through all of the words in the text
     */
    protected void processTextAtURL()
    {
        try( BufferedInputStream in = new BufferedInputStream(new URL(this.url).openStream());)
        {
            scnr = new Scanner(in);
            String currentWord = null;

            while (scnr.hasNext())
            {
                currentWord = scnr.next();
                processWord(currentWord);
            }

        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    /**
     * This function processes a given word, and adds to the total word count
     * If the word is not 5 letters and only contains lowercase letters discard the word
     * If the words does is five letter and only contains lower case letters add to totalGoodWords
     * If the word is not in already in the map add to unique words, and add word to map with Integer value of 1
     * If the word is in the map add 1 to the Integer value of the word in the map
     * @param word
     */
    protected void processWord(String word)
    {
        this.totalWords += 1;
        word = word.replaceAll("[”“]","");

        Pattern p = Pattern.compile(".+(\\p{Punct}+)");
        Matcher m = p.matcher(word);
        if (m.matches())
        {
            word = word.substring(0,m.start(1));
        }

        p = Pattern.compile("^[a-z]{5}$");
        m = p.matcher(word);

        if (m.matches())
        {
            totalGoodWords += 1;
            if (!wordMap.containsKey(word))
            {
                totalUniqueWords+=1;
                wordMap.put(word,1);

            }
            else
            {
                wordMap.put(word,wordMap.get(word)+1);
            }
        }
        else{this.totalWordsDiscarded += 1;}

    }


    /**
     * A function that prints all the results from the processed text
     */
    protected void printReport()
    {
        System.out.println("Total Number of Words Processed: "+ totalWords);
        System.out.println("Total words discarded "+ totalWordsDiscarded);
        System.out.println("Total number of words kept: " + totalGoodWords);
        System.out.println("Total unique words: " + totalUniqueWords);

        ArrayList<Map.Entry<String,Integer>> arrayOfWords = new ArrayList<>(wordMap.entrySet());
        Collections.sort(arrayOfWords,Comparator.comparing(Map.Entry::getValue));

        System.out.println("The twenty most used words are");
        for (int i = arrayOfWords.size()-1; i > (arrayOfWords.size()-20); i--)
        {
            System.out.println(arrayOfWords.get(i).getKey() + ":  " + arrayOfWords.get(i).getValue());
        }
    }

    /**
     * Initializes a Set<String> and adds all words that appear more than once to it
     * @return wordSet
     */
    public Set<String> getSetOfWords()
    {
        Set<String> wordSet = new TreeSet<>();
        for (String word:wordMap.keySet())
        {
            if ( wordMap.get(word) >= 2 )
            {
                wordSet.add(word);
            }
        }

        return wordSet;
    }



}
