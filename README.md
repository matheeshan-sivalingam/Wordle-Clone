# SOFE 3980U: Software Quality Assignment One - Wordle Clone

## Background
Wordle is a web-based game that has gained critical acclaim at the beginning of the year. The objective of the game is to guess the 5 letter word of the day. The player is given six attempts to try to guess this word. For each attempt, the player receives feedback on the word that they have entered. If the letter of the word that they have entered does not belong in the word of the day, then the tile of the letter is coloured in gray. If a letter from the entered word does belong in the word of the day but the position of the letter is wrong then the tile of the letter is coloured in yellow. If the letter and the position match the letter from the word of the day, then the tile of the letter is coloured in green. The game ends if the player guesses the correct word or when the player runs out of attempts

## Testcases
<b>TC1:-</b> All words are loaded into the word bank <br>
<b>TC2:-</b> Word is removed from the word bank when updateWordBank is called <br>
<b>TC3:-</b> Guess entered from the player is less than five letters <br>
<b>TC4:-</b> Guess entered from the player is more than five letters <br>
<b>TC5:-</b> Guess entered from the player does not contain numbers <br>
<b>TC6:-</b> Guess entered from the player does not contain special characters <br>
<b>TC7:-</b> User has entered a valid guess <br>
<b>TC8:-</b> Correct output is given if the user enters a completely incorrect guess <br>
<b>TC9:-</b> Correct output is given if the user enters the correct letters but incorrect position <br>
<b>TC10:-</b> Correct output is given if the user enters the correct letters in the correct position <br>
<b>TC11:-</b> Correct output is given if the user enters a guess that has multiple correct letters <br>
<b>TC12:-</b> Correct output is given if the user enters a guess that has multiple characters that are the same but are in the incorrect position <br>
<b>TC13:-</b> Correct font color is used if the user enters letters that are not in the word <br>
<b>TC14:-</b> Correct font color is used if the user enters letters that are in the word but in the incorrect position <br>
<b>TC15:-</b> Correct font color guesses correctly <br>
<b>TC16:-</b> Score is calculated correctly <br>
