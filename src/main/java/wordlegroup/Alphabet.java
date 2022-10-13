/* *****************************************

 * CSCI205 - Software Engineering and Design

 * Fall 2022

 * Instructor: Prof. Brian King

 *

 * Name: Patrick Quinlivan

 * Section: 11:00 AM

 * Date: 10/12/22

 * Time: 11:31 PM

 *

 * Project: csci205_hw

 * Package: wordlegroup

 * Class: Alphabet *

 * Description:

 *

 * ****************************************

 */

package wordlegroup;

import java.util.ArrayList;

public class Alphabet {

    /** Initialize an arrayList that will contain the charachters of the alphabet*/
    public ArrayList<Character> alphabet;

    /** Constructer class for Alphabet */
    public Alphabet(){
        alphabet = new ArrayList<Character>();
        addAlphabetValues();
    }

    /** Adds all letters to alphabet */
    private void addAlphabetValues() {
        alphabet.add('a');
        alphabet.add('b');
        alphabet.add('c');
        alphabet.add('d');
        alphabet.add('e');
        alphabet.add('f');
        alphabet.add('g');
        alphabet.add('h');
        alphabet.add('i');
        alphabet.add('j');
        alphabet.add('k');
        alphabet.add('l');
        alphabet.add('m');
        alphabet.add('n');
        alphabet.add('o');
        alphabet.add('p');
        alphabet.add('q');
        alphabet.add('r');
        alphabet.add('s');
        alphabet.add('t');
        alphabet.add('u');
        alphabet.add('v');
        alphabet.add('w');
        alphabet.add('x');
        alphabet.add('y');
        alphabet.add('z');
    }


    /**
     * Removes letters in the guess word that are not in the correct word
     * @param correctWord word to be guessed
     * @param guess the users guess
     */
    public void compareWords(String correctWord, String guess)
    {
        GuessEvaluator evaluator = new GuessEvaluator(correctWord);
        for(int i = 0; i < guess.length(); i+= 1)
        {
            if(!evaluator.checkIfCharInWord(guess.charAt(i), correctWord))
            {
              int index =  alphabet.indexOf(guess.charAt(i));
               alphabet.remove(index);
            }

        }

    }

    /**
     * A simple function that prints the valid characters in the alphabet
     */
    public void displayAlphabet(){
        for(int i = 0; i < alphabet.size(); i++) {
            System.out.println(alphabet.get(i));

        }
    }


    /**
     *  Getter method for alphabet size
     * @return size of the valid alphabet
     */
    public int getAlphabetSize() {
        return alphabet.size();
    }
}
