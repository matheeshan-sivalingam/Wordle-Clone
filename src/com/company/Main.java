//Name: Matheeshan Sivalingam (100703887)
//Course: SOFE3
//Program: Wordle
//Date: 25/02/2021

package com.company;
import java.util.*;
import java.io.*;

public class Main {
    //Declare variables that hold the ANSI characters to change text color
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String DEFAULT = "\u001B[0m";

    //Declare populateWordBank method - This method takes the words from words.txt and compiles them into a word bank
    public static ArrayList<String> populateWordBank() {
        //Declare string arrayList
        ArrayList<String> wordBank = new ArrayList<String>();
        //Use try/catch to ensure that the word.txt document is found
        try {
            //Declare file variable
            File words = new File("src/words.txt");
            //Declare file scanner
            Scanner fScan = new Scanner(words);
            //Loop while there is a line of text in the txt file
            while (fScan.hasNextLine()) {
                //Add the word to the word bank
                wordBank.add(fScan.nextLine());
            }
            //If the file is not found, print error
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Return wordBank
        return wordBank;
    }
    //Declare updateWordBank method - This method updated the word bank by removing a word from the wordbank
    public static ArrayList<String> updateWordBank(String word, ArrayList<String> wordBank) {
        //Remove the word from the wordbank
        wordBank.remove(word);
        //Return the updated wordbank
        return wordBank;
    }
    //Declare updateScore method - This function determines how much points does the player win from guessing the word
    public static int updateScore(int attempts, int score) {
        //Add score won from that round and save it into the total score. Score is calculated based on how many attempts that user had left
        return (score + attempts*100);
    }
    //Declare winCheck method - This method checks to see if the user has won the game. The win condition for the game is to complete all 20 wordles
    public static void winCheck (int size, int score) {
        //If the size of wordBank is zero
        if (size==0)
        {
            //Output to the user
            System.out.println("You have completed all 20 Wordles! You Win! Your final score is "+score);
            //Exit program
            System.exit(0);
        }
    }

    //Declare errorCheck method - This method is used to ensure that the user enters a valid input
    public static boolean errorCheck (String guess){
        //If the guess is not 5 letters
        if (guess.length()!=5)
        {
            //Output to the user
            System.out.println("Your guess is not 5 letters. Please try again");
            //Return true
            return true;
        }
        //If the users input does not match the regex (only lowercase and uppercase alphabet)
        else if (!guess.matches("[a-zA-Z]+"))
        {
            //Output to the user
            System.out.println("Your guess contains invalid characters. Please try again");
            //Return true
            return true;
        }
        //If there was no errors in the users input
        else{
            //Return false
            return false;
        }

    }
    //Declare printAnswer method - This function outputs the letters of the user guess. It informs the user what letters were
    // in the word and if they are in the correct position
    public static String printAnswer(int[] cLet, char[] gLet) {
        //Declare and initialize variable
        String answer = "";
        //Use for loop to print out all letters in the guess
        for (int i = 0; i < 5; i++) {
            //if the element of the correct letters array is zero (If the letter does not belong to the word)
            if (cLet[i] == 0) {
                //Concatenate the letter of the guess to the answer
                answer = answer + gLet[i];
                //If the element of the correct letters array is one (If the letter belongs to the word and is in the correct position)
            } else if (cLet[i] == 1) {
                //Concatenate the letter of the guess to the answer. Change the print to green and then change back to default
                answer = answer + GREEN+ gLet[i]+DEFAULT;
                //If the element of the correct letters are is two (If the letter belongs to the word but is not in the correct position)
            } else if (cLet[i] == 2) {
                //Concatenate the letter of the guess to the answer. Change the print to yellow and then change back to default
                answer = answer + YELLOW+ gLet[i]+DEFAULT;
            }
        }
        //Return answer
        return answer;
    }
    //Declare checkAnswer method - This function determines if what the user input is correct
    public static int[] checkAnswer(String word, char gLet[],char wLet[],int rLet[], int cLet[],char uniL[]) {
        //Declare and initialize num varaible
        int num=0;
        //Use for loop to go through all characters in the word and check to see if the letter belong to the word and is in the correct position
        for (int i = 0; i < word.length(); i++) {
            //If the letter from the word and the guess are the same and they are located in the same position
            if (wLet[i] == gLet[i]) {
                //Set element of the correct letters array to one
                cLet[i] = 1;
                //Use for loop to determine what letter to decrement from the repeated letter counter
                for (int j= 0;j<word.length();j++) {
                    //If the letter matches with the unit letter
                    if (wLet[i]==uniL[j]) {
                        //Decrement the counter
                        rLet[j]--;
                        //Break the loop
                        break;
                    }
                }
            }
        }
        //Use for loop to see which letters belong to the word but are not in the correct position
        for (int i = 0; i < word.length(); i++) {
            //If the element of the correctLetters array is equal to one (meaning that the letter is correct)
            if (cLet[i] == 1) {
                //Skip to the next element
                continue;
            }
            //Otherwise, check to see if the letter belong to the word but are not in the correct position
            else {
                //Use nested for loop to check if the letter is shared by the guess and the word
                for (int j = 0; j < word.length(); j++) {
                    //If the pointers are at the same element, skip (unnecessary as if this was true, it should have been a complete match
                    if (i == j) {
                        //Skip to the next element
                        continue;
                    }
                    //If the guess and the word share a letter
                    else if (gLet[i] == wLet[j]) {
                        //Use for loop to determine what letter it is and decrement it from the repeated letter counter
                        for (int k = 0;k<word.length();k++)
                        {
                            //If the letter is found, save the pointer value to num
                            if (uniL[k]==gLet[i]) {
                                num=k;
                                //Break the loop
                                break;
                            }
                        }
                        //If the repeated letter is greater than 0 (meaning that if the letter is still needed to guess the correct word
                        if (rLet[num]>0)
                        {
                            //Decrement the counter for the letter
                            rLet[num]--;
                            //Set the correct letter to 2 for this element
                            cLet[i] = 2;
                            //Brek the loop
                            break;
                        }

                    }
                }
            }
        }
        //Return correct letters array
    return cLet;
    }

    public static void main(String[] args) {
        //Output to the user
        System.out.println(DEFAULT + "Wordle - Guess the word in 6 tries!\n");
        //Declare scanner
        Scanner scan = new Scanner(System.in);
        long startTime = System.nanoTime();
        //Declare and initialize variables
        ArrayList<String> wordBank = populateWordBank();
        //Declare endTime variable for instrumentation and initialize with current time
        long endTime = System.nanoTime();
        //Calculate how much time has elapsed when running populateWordBank function
        long populateWordBankElapsedTime = (endTime-startTime);
        //Set the score to 0
        int score = 0;

        //Loop indefinitely
        while(true)
        {
            //Run the winCheck method to determine if the player has completed the game
            winCheck(wordBank.size(),score);
            //Declare random variable
            Random rand = new Random();
            //Get a random integer
            int n = rand.nextInt(wordBank.size());
            //Declare word variable and get random word from wordBank
            String word = wordBank.get(n);
            //Declare startTime variable for instrumentation and initialize with current time
            startTime = System.nanoTime();
            //Update the wordbank by removing the word from the wordBank
            wordBank = updateWordBank(word,wordBank);
            //Declare endTime variable for instrumentation and initialize with current time
            endTime = System.nanoTime();
            //Subtract the difference to get time taken to run updateWorldBank function
            long updateWordBankElapsedTime = (endTime-startTime);
            //Declare word letters array and initialize by taking storing the lowercase letters of the array
            char[] wLet = word.toLowerCase().toCharArray();
            //Set the number of attempts
            int attempts = 6;
            //Declare and initialize the frequency array. This array keeps track of the frequency of the letters
            int lFreq[] =  new int[word.length()];
            //Decalre and initalzie the unit letters array. This array works in combination with the frequency array by indicating what letter has what frequency
            char uniL[] = word.toLowerCase().toCharArray();
            //Use for loop to go through all letters in the word
            for (int i =0;i<word.length();i++)
            {
                //Set the frequency to one for the letter
                lFreq[i]=1;
                //Use for loop to check if each letter matches the current letter
                for (int j =i+1;j<word.length();j++)
                {
                    //If there are the same letter in the word
                    if(uniL[i]==uniL[j])
                    {
                        //Increment the frequency for the letter
                        lFreq[i]++;
                        //Replace with the letter to ensure that it doesn't count the letter twice
                        uniL[j] = '0';
                    }
                }
            }
            //Output to the user
            System.out.println("Guess the word     Current Score - "+score);

            //Loop while attempts are greater than zero
            while (attempts > 0) {
                //Declare and initialize guess variable
                String guess = "";
                //Declare and initialize repeated letters array. Copy of the value from the letter frequency array
                int[] rLet = Arrays.copyOf(lFreq,lFreq.length);
                //Declare and initialize the correct letters array
                int[] cLet = new int[5];
                //Declare and initialize correct flag variable. This variable checks to see if the player has gueesed the word
                int cflag=0;
                //Obtain users input
                guess = scan.next();

                //Set startTime variable with current nano time
                startTime = System.nanoTime();
                //Declare and initialize errorCheck boolean flag
                boolean eC = errorCheck(guess);
                endTime = System.nanoTime();
                //Subtract the difference to get time taken to run errorCheck function
                Long errorCheckElapsedTime = (endTime-startTime);
                //If there are no errors in the user input
                if (eC==false) {
                    //Declare and initialzie guess letters array by taking the users guess, making the letter lowerase, and storing into array
                    char[] gLet = guess.toLowerCase().toCharArray();
                    //Set startTime variable with current nano time
                    startTime = System.nanoTime();
                    //Call the checkAnswer function to determine what the user got correct
                    cLet = checkAnswer(word,gLet,wLet,rLet,cLet,uniL);
                    //Set endTime variable with current nano time
                    endTime = System.nanoTime();
                    //Subtract the difference to get time taken to run checkAnswer function
                    Long checkAnswerElapsedTime = (endTime-startTime);
                    //Set startTime variable with current nano time
                    startTime = System.nanoTime();
                    //Call printAnswer method to form the correct output
                    String answer = printAnswer(cLet,gLet);
                    //Set endTime variable with current nano time
                    endTime = System.nanoTime();
                    //Subtract the difference to get time taken to run checkAnswer function
                    Long printAnswerElapsedTime = (endTime-startTime);
                    //Output the answer of the guess to the user
                    System.out.println(answer);
                    //Use for loop to determine if the user has gotten all correct letters
                    for (int i = 0;i<word.length();i++)
                    {
                        //If the user was correct for this letter
                        if (cLet[i]==1)
                        {
                            //Increment the flag
                            cflag++;
                        }
                    }
                    //If the flag is equal to 5
                    if (cflag==5)
                    {
                        //Output to the user that they guessed the word
                        System.out.println("You guessed the word!");
                        //Call updateScore to update score
                        score = updateScore(attempts,score);
                        /////////////////////////PRINTING INSTRUMENTATION RESULTS////////////////////////////////////////////
                        System.out.println("errorCheck() time taken - " + errorCheckElapsedTime + " ns");
                        System.out.println("checkAnswer() time taken - " + checkAnswerElapsedTime+ " ns");
                        System.out.println("printAnswer() time taken - " + printAnswerElapsedTime+ " ns");
                        /////////////////////////////////////////////////////////////////////////////////////////
                        //Break the loop
                        break;
                    }
                    else
                    {
                        //Decrement the attempts
                        attempts = attempts - 1;
                    }

                    /////////////////////////PRINTING INSTRUMENTATION RESULTS////////////////////////////////////////////
                    if (wordBank.size()==19 && attempts>=5)
                    {
                        System.out.println("populateWordBank() time taken - " + populateWordBankElapsedTime + " ns");
                    }
                    if (attempts>=5)
                    {
                        System.out.println("updateWordBank() time taken - " + updateWordBankElapsedTime + " ns");
                    }
                    System.out.println("errorCheck() time taken - " + errorCheckElapsedTime + " ns");
                    System.out.println("checkAnswer() time taken - " + checkAnswerElapsedTime+ " ns");
                    System.out.println("printAnswer() time taken - " + printAnswerElapsedTime+ " ns");
                    /////////////////////////////////////////////////////////////////////////////////////////
                }
                //If the attempts equal to zero
                if (attempts==0)
                {
                    //Output to the user
                    System.out.println("You lose! The word was " + word + ". Your final score is "+score);
                    //Leave the system
                    System.exit(0);
                }


            }
        }
        }




}

