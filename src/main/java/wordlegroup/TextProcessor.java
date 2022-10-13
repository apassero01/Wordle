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
    public String url;

    protected static Scanner scnr;

    private Map<String,Integer> wordMap;

    protected int totalWords;

    private int totalWordsDiscarded;

    private int totalGoodWords;

    private int totalUniqueWords;
    public TextProcessor(String url)
    {
        this.url = url;
        wordMap = new TreeMap<>();
        totalWords = 0;
        totalUniqueWords = 0;
        totalGoodWords = 0;
        totalWordsDiscarded = 0;
    }

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
