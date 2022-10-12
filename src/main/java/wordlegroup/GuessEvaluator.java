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

    public GuessEvaluator(String  correctWord){
        this.correctWord = correctWord;
    }

    public String analyzeGuess(String currentGuess)
    {
        this.currentGuess = currentGuess;
        String result = "";

        for(int i = 0; i < this.correctWord.length(); i++)
        {
            if(this.currentGuess.charAt(i) == this.correctWord.charAt(i))
            {
                result += "";
            }
        }




        return result;
    }



}
