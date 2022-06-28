package com.company;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest extends Main {
    //TC1:- All words are loaded into the word bank
    @Test
    void allWordsAreInWordBank(){
        //Declare wordBank and initialize by calling populateWordbank function
        ArrayList<String> wordBank = populateWordBank();
        //Check to see if the number of words in the wordbank is 20 (meaning that all words are located in the wordbank
        assertEquals(wordBank.size(),20);
    }
    //TC2:- Word is removed from the word bank when updateWordBank is called
    @Test
    void removeWordFromWordBank(){
        String word1 = "empty";
        String word2 = "pearl";
        String word3 = "vivid";

        ArrayList<String> wordBank = new ArrayList<String>();
        ArrayList<String> testWordBank = new ArrayList<String>();

        wordBank.add(word1);
        wordBank.add(word2);
        wordBank.add(word3);

        testWordBank.add(word2);
        testWordBank.add(word3);

        assertEquals(testWordBank,updateWordBank(word1,wordBank));
    }
    //TC3:- Guess entered from the player is less than five letters
    @Test
    void lessThanFiveLetters(){
        String guess = "flow";
        assertTrue(errorCheck(guess));
    }
    //TC4:- Guess entered from the player is more than five letters
    @Test
    void moreThanFiveLetters(){
        String guess = "Flower";
        assertTrue(errorCheck(guess));
    }
    //TC5:- Guess entered from the player does not contain numbers
    @Test
    void guessDoesNotContainNumbers(){
        String guess = "P0W3R";
        assertTrue(errorCheck(guess));
    }
    //TC6:- Guess entered from the player does not contain special characters
    @Test
    void otherCharacterGuess(){
        String guess = "@z)r!";
        assertTrue(errorCheck(guess));
    }
    //TC7:- User has entered a valid guess
    @Test
    void validGuess(){
        String guess = "azure";
        assertFalse(errorCheck(guess));
    }
    //TC8:- Correct output is given if the user enters a completely incorrect guess
    @Test
    void incorrectLettersIncorrectPositionGuess(){
        String word = "azure";
        String guess = "vivid";
        char gLet[] = guess.toLowerCase().toCharArray();
        char wLet[] = word.toLowerCase().toCharArray();
        int[] cLet = new int[5];
        int lFreq[] =  new int[word.length()];
        char uniL[] = word.toLowerCase().toCharArray();

        for (int i =0;i<word.length();i++)
        {
            lFreq[i]=1;
            for (int j =i+1;j<word.length();j++)
            {
                if(uniL[i]==uniL[j])
                {
                    lFreq[i]++;
                    uniL[j] = '0';
                }
            }
        }

        int[] rLet = Arrays.copyOf(lFreq,lFreq.length);
        int[] testCLet = {0,0,0,0,0};

        assertArrayEquals(checkAnswer(word, gLet,wLet,rLet,cLet,uniL),testCLet);
    }
    //TC9:- Correct output is given if the user enters the correct letters but incorrect position.
    @Test
    void correctLettersIncorrectPositionGuess(){
        String word = "azure";
        String guess = "erzau";
        char gLet[] = guess.toLowerCase().toCharArray();
        char wLet[] = word.toLowerCase().toCharArray();
        int[] cLet = new int[5];
        int lFreq[] =  new int[word.length()];
        char uniL[] = word.toLowerCase().toCharArray();

        for (int i =0;i<word.length();i++)
        {
            lFreq[i]=1;
            for (int j =i+1;j<word.length();j++)
            {
                if(uniL[i]==uniL[j])
                {
                    lFreq[i]++;
                    uniL[j] = '0';
                }
            }
        }

        int[] rLet = Arrays.copyOf(lFreq,lFreq.length);
        int[] testCLet = {2,2,2,2,2};

        assertArrayEquals(checkAnswer(word, gLet,wLet,rLet,cLet,uniL),testCLet);
    }
    //TC10:- Correct output is given if the user enters the correct letters in the correct position
    @Test
    void correctLettersCorrectPositionGuess(){
        String word = "azure";
        String guess = "azure";
        char gLet[] = guess.toLowerCase().toCharArray();
        char wLet[] = word.toLowerCase().toCharArray();
        int[] cLet = new int[5];
        int lFreq[] =  new int[word.length()];
        char uniL[] = word.toLowerCase().toCharArray();

        for (int i =0;i<word.length();i++)
        {
            lFreq[i]=1;
            for (int j =i+1;j<word.length();j++)
            {
                if(uniL[i]==uniL[j])
                {
                    lFreq[i]++;
                    uniL[j] = '0';
                }
            }
        }

        int[] rLet = Arrays.copyOf(lFreq,lFreq.length);
        int[] testCLet = {1,1,1,1,1};

        assertArrayEquals(checkAnswer(word, gLet,wLet,rLet,cLet,uniL),testCLet);
    }
    //TC11:- Correct output is given if the user enters a guess that has multiple correct letters
    @Test
    void correctNumberofLetters() {
        String word = "vixen";
        String guess = "vivid";
        char gLet[] = guess.toLowerCase().toCharArray();
        char wLet[] = word.toLowerCase().toCharArray();
        int[] cLet = new int[5];
        int lFreq[] =  new int[word.length()];
        char uniL[] = word.toLowerCase().toCharArray();

        for (int i =0;i<word.length();i++)
        {
            lFreq[i]=1;
            for (int j =i+1;j<word.length();j++)
            {
                if(uniL[i]==uniL[j])
                {
                    lFreq[i]++;
                    uniL[j] = '0';
                }
            }
        }

        int[] rLet = Arrays.copyOf(lFreq,lFreq.length);
        int[] testCLet = {1,1,0,0,0};

        assertArrayEquals(checkAnswer(word, gLet,wLet,rLet,cLet,uniL),testCLet);
    }
    //TC12:- Correct output is given as letters in the correct positions should be prioritized
    @Test
    void correctLetterIncorrectPositionPriority() {
        String word = "vivid";
        String guess = "ovovv";
        char gLet[] = guess.toLowerCase().toCharArray();
        char wLet[] = word.toLowerCase().toCharArray();
        int[] cLet = new int[5];
        int lFreq[] =  new int[word.length()];
        char uniL[] = word.toLowerCase().toCharArray();

        for (int i =0;i<word.length();i++)
        {
            lFreq[i]=1;
            for (int j =i+1;j<word.length();j++)
            {
                if(uniL[i]==uniL[j])
                {
                    lFreq[i]++;
                    uniL[j] = '0';
                }
            }
        }

        int[] rLet = Arrays.copyOf(lFreq,lFreq.length);
        int[] testCLet = {0,2,0,2,0};

        assertArrayEquals(checkAnswer(word, gLet,wLet,rLet,cLet,uniL),testCLet);
    }
    //TC13:- Correct output is given as letters in the correct positions should be prioritized
    @Test
    void correctLetterCorrectPositionPriority() {
        String word = "vivid";
        String guess = "vvvvv";
        char gLet[] = guess.toLowerCase().toCharArray();
        char wLet[] = word.toLowerCase().toCharArray();
        int[] cLet = new int[5];
        int lFreq[] =  new int[word.length()];
        char uniL[] = word.toLowerCase().toCharArray();

        for (int i =0;i<word.length();i++)
        {
            lFreq[i]=1;
            for (int j =i+1;j<word.length();j++)
            {
                if(uniL[i]==uniL[j])
                {
                    lFreq[i]++;
                    uniL[j] = '0';
                }
            }
        }

        int[] rLet = Arrays.copyOf(lFreq,lFreq.length);
        int[] testCLet = {1,0,1,0,0};

        assertArrayEquals(checkAnswer(word, gLet,wLet,rLet,cLet,uniL),testCLet);
    }
    //TC14:- Correct font color is used if the user enters letters that are not in the word
    @Test
    void incorrectLettersIncorrectPositionOutput(){
        int cLet[] = {0,0,0,0,0};
        char gLet[] = {'a','z','u','r','e'};

        assertEquals("azure", printAnswer(cLet,gLet));
    }
    //TC15:- Correct font color is used if the user enters letters that are in the word but in the incorrect position
    @Test
    void correctLettersIncorrectPositionOutput(){
        int cLet[] = {2,2,2,2,2};
        char gLet[] = {'a','z','u','r','e'};

        assertEquals(YELLOW+'a'+DEFAULT +YELLOW+'z'+DEFAULT +YELLOW+'u'+DEFAULT +YELLOW+'r'+DEFAULT +YELLOW+'e'+DEFAULT, printAnswer(cLet,gLet));
    }
    //TC16:- Correct font color guesses correctly
    @Test
    void correctLettersCorrectPositionOutput(){
        int cLet[] = {1,1,1,1,1};
        char gLet[] = {'a','z','u','r','e'};
        assertEquals(GREEN+'a'+DEFAULT +GREEN+'z'+DEFAULT +GREEN+'u'+DEFAULT +GREEN+'r'+DEFAULT +GREEN+'e'+DEFAULT, printAnswer(cLet,gLet));
    }

    //TC17:- Score is calculated correctly
    @Test
    void correctPointsAddedToScore() {
        int score = 600;
        int attempts = 6;
        assertEquals(1200,updateScore(attempts,score));
    }


}