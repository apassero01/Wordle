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

 * Class: Wordle *

 * Description:

 *

 * ****************************************

 */

package wordlegroup;

public class Wordle {

    private GameDictionary gameDictionary;
    public static void main(String[] args)
    {
        Wordle wordle = new Wordle();
//        wordle.gameDictionary = new GameDictionary();
//        wordle.gameDictionary.createMaster();
        wordle.gameDictionary = new GameDictionary("words.txt");
        wordle.gameDictionary.getMasterFromFile();

    }
}
