/* *****************************************

 * CSCI205 - Software Engineering and Design

 * Fall 2022

 * Instructor: Prof. Brian King

 *

 * Name: Patrick Quinlivan

 * Section: 11:00 AM

 * Date: 10/12/22

 * Time: 11:09 AM

 *

 * Project: csci205_hw

 * Package: wordlegroup

 * Class: GuessEvaluator *

 * Description:

 *

 * ****************************************

 */

package wordlegroup;

public class GuessEvaluator {
    /** An instance of the word to be guessed */
    private String correctWord;

    /** The word currently guessed by the user */
    private String currentGuess;

    public GuessEvaluator(String correctWord){
        this.correctWord = correctWord;
    }


    /**
     * This is a function that compares the current guess to the correct word
     * @param currentGuess
     * @return String the result of the guess
     */
    public String analyzeGuess(String currentGuess)
    {
        this.currentGuess = currentGuess;
        String result = "";

        for(int i = 0; i < this.correctWord.length(); i++)
        {

            //Check if character int guess is correct and in the correct position
            if(this.currentGuess.charAt(i) == this.correctWord.charAt(i))
            {
                result += "*";
            }
            // check if the character in guess is in the word
            else if(this.checkIfCharInWord(this.currentGuess.charAt(i), this.correctWord)){
                result += "+";
            }
            // mark that the character is not in the correct word
            else
                result += "-";

        }

        return result;
    }


    /**
     * This is a helper cunction that checks if an input charachter is in an input word
     * @param ch
     * @param word
     * @return true if the given character is in the given word, otherwise return false
     */
    public boolean checkIfCharInWord(char ch, String word)
    {
        for(int i = 0; i < word.length(); i++)
        {
            if(ch == word.charAt(i))
                return true;
        }
        return false;
    }



}
