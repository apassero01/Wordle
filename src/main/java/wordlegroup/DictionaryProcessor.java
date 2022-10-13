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

public class DictionaryProcessor extends TextProcessor
{
    protected Set<String> setOfWords;
    protected int number;

    public DictionaryProcessor(String url)
    {
        super(url);
        this.setOfWords = new TreeSet<>();
    }

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

    @Override
    protected void printReport()
    {
        System.out.println("Total number of Words: " + this.setOfWords.size());
    }
    @Override
    public Set<String> getSetOfWords()
    {
        return this.setOfWords;
    }





}