# COMP 1502 - Assignment 1

The intention of this assignment is to demonstrate skills in designing and implementing classes that share an aggregation/composition relationship. Additionally, it will give you experience working with pre-existing code, testing that code, and creating/testing your own code as well.

The assignment consists of 2 phases:

- Phase 1: Due **Monday, March 2, 2020**
  - a UML diagram including all classes, instance variables, methods and relationships, plus any notes on tricky / complicated parts of the implementation.
- Phase 2: Due **Monday, March 16, 2020**
  - a fully functioning system, with tests and an updated UML document.

## The Project

In this assignment, you must use the provided `Deck`, `CardHand` and `Card` classes to help implement a mini casino program. You _must_ use these classes _as is_.

The Casino will allow players to choose from two different card games to play: _Punto Banco_ and _Hi-Lo_ (rules for each are included below). The Casino will provide players with a starting bank of coins and will allow them to earn more coins by winning the games. Each time a player starts a game, the Casino program should keep track of the number of coins the player has from previous games they have played that session.

The program must have the following features:

- When the program starts up, you must check to see if a file called `CasinoInfo.txt` exists in the current directory.
  - If it **does** exist, you must load the info from the file:
    - a player name on the first line of the file, and
    - the amount of money the payer has left to bet on the second line.
  - If the file does **not** exist, you must ask for the current player's name and assign them a starting money amount of 1000 coins that they can use to place bets when playing the games.
- After the startup, you must present a menu that allows them to choose from the following options:
  - play Punto Banco (Described later).
  - play Hi-Low (Described later).
  - exit the program.
- Before each game starts, a new deck(s) of cards should be created and shuffled before use.
- When a game is selected to play, the user will be able to keep playing that game until they chose to exit and return to the main menu.
- During the execution of a single game (even when repeating a play of a game) the same deck of cards should be used. Only when all cards run out should a new deck be created and shuffled.
- In both games, the player will be able to make bets (winning and losing money based upon the results).
  - When moving between games, the user's current amount of money on hand must be clearly displayed.
  - The player can never make a bet larger than the amount of money they have on hand.
  - If they have zero coins left, then they cannot play any more games and must be taken back to the main menu, where they can then select to exit the program.
  - When the program shuts down, you must save the player's information to the file `CasinoInfo.txt`. 
  - The first line once again must be the player's name and the second line must be the amount of money they have left when exiting the program.
  - The program does not shut down until they select quit from the main menu.

While specifics of the design are left up to you, we do expect to see both of the games implemented in their own classes and called via a method that returns their updated coin totals. You can (and should) create any other classes you need to organize your program following the principles of object oriented design (encapsulation and maximizing cohesion while minimizing coupling).

You have also been provided with a Menu system which you can use as the basis for your casino menu.

## The Games

You will have to implement the two games described below.

### Game 1: Punto Banco

The following description is for one round of the game. At the end of the round, the user must be asked if they want to play again or return to the main menu.

Upon launching the game, the user will be asked which outcome they would like to bet on. The three outcomes are:

- P: Player Wins - This bet pays out if the Player (Punto) wins.
- B: Banker Wins - This bet pays out if the Banker (Banco) wins.
- T: Tie Game - This bet pays out if there is a tie (Egalite).

The options above must be selectable with the given letters. Both upper and lower case characters should be acceptable. An invalid input should print an error message and re-prompt for an acceptable response.

Once a bet is made, the game must be played, the winner (Player or Banker) determined, and then the bet should be paid out if the user picked the correct outcome.

#### Punto Banco Basics:

Punto Banco is a casino game in which gamblers bet on the outcome of a game. What this means is that unlike in most casino games, the gambler is not technically _playing_ the game. Instead, the Player (Punto) and Banker (Banco) play according to a fixed set of rules, and the gambler may bet on the Player (Punto), Banker (Banco), or a tie (Egalite).

The value of the Player and Banker hands in the game are calculated by summing the value of the cards, modulo 10. Thus a sum of 10 results in a hand value of 0, and a sum of 18 results in a hand value of 8. In the game, cards with a rank of 2 through 9 are worth the value of their rank, face cards (Jack, Queen and King) and 10s are worth 0, and Aces are worth 1.

The sequence of play in the game is as follows:

1. The Player gets a card face up.
2. The Bank gets a card face up.
3. The Player gets a second card face up.
4. The Bank gets a second card face up.
   - Both hands should be printed to the screen ( clearly marking each hand )
5. If the Player or Banker has a total of 8 or 9, no more cards are dealt (skip to 8).
6. If the Player has a total of 0-5, the player gets a third card face up.
7. The bank may get a third card face up, depending on whether the Player received a third card, which card, if anything, the Player drew and the Banker's total:
   8. If the Player did not draw a card, the Banker draws if he (the Banker) has 0-5, and stands if he has 6-7.
   9. If the Player drew a 2 or 3, the Banker draws if he (the Banker) has 0-4, and stands if he has 5-7.
   10. If the Player drew a 4 or 5, the Banker draws if he has 0-5, and stands if he has 6-7.
   11. If the Player drew a 6 or 7, the Banker draws if he has 0-6, and stands if he has 7.
   12. If the Player drew an 8, the Banker draws if he has 0-2, and stands if he has 3-7.
   13. If the Player drew an Ace, 9, 10, or face card (Jack, Queen or King), the Banker draws if he has 0-3, and stands if he has 4-7.
8. At this point, the result of the game is determined: the Player wins if his total beats the Banker, and the Banker wins if his total beats the Player. Or, if the totals are the same, the result is a tie (Egalite).

A successful bet on the Player wins what was bet (100 in gets 200 back), a successful bet on the Banker wins 95% of what was bet (100 in gets 195 back), and a successful bet on a tie wins 8 to 1 (100 in gets 900 back).

### Game 2: High Low Same

At the start of the game, the user is asked for the bet they want to use for the entire game play. Two decks are created, one for the user and one for the banker. All cards are drawn from the respective decks until they are empty. At this point new decks should be created.

Game play goes as follows:

1. One card is delta(sic) from the players deck and displayed to the screen
1. The user is asked if the dealers card will be higher, lower or same rank (2 throuh Ace) than the current card they have
1. A card is dealt from the dealer's deck and compared to the user's card. Both cards and the result of the play are displayed to the user and they(sic) payout is calculated as follows:
   1. If the dealer's card is higher, and the user's guess was _Higher_, they win what was bet (100 in gets them 200 back).
   2. If the dealer's card is lower, and the user's gues was _Lower_, they also win what was bet (100 in gets them 200 back).
   3. If the cards match in **just** rank, and the user guessed _Same_, they win double what was bet (100 in gets 300 back).
   4. If the cards match in **both** rank and suit, then they tie and no one wins or loses.
   5. All other outcomes result in the player losing their bet.
1. Based upon the results, the user's balance is updated.
1. They then are asked if they want to play again or go back to the main menu.

## Testing

We expect to see reasonable JUnit tests written to ensure that the behaviour of the existing `Card`, `CardHand` and `Deck` classes is what you expect it to be.

At a minimum, we expect you to be able to:

- Ensure you can create a card and get its suit and rank.
- The numeric values of the suits map to the expected strings (Spade, Diamond, Clubs, Hearts), case-sensitive.
- The values of the ranks map to the expected strings (Jack, Queen, King, Ace), case-sensitive.
- Ensure you can create a hand of cards.
- Add cards to a hand and retrieve them.
- Create a new deck of cards.
- Shuffle a deck of cards (and get a different arrangement of cards each time).
- Deal a new card.=
- Deal a hand of cards.

Additionally, any public method you create which does not require user input to function should also have a JUnit test.

## Deliverables

Official due dates for the two phases will be posted on Blackboard.

### Phase 1 - UML Class Diagram

The first thing you need to hand in is a UML Class Diagram which includes all of the classes you need for your system, plus the existing classes you are provided. You may omit any private methods, but should include private instance variables.

Additionally, we would like to see a (short) set of notes on how you plan to build your program, focusing on the things you think will be challenging or tricky to build. We would also like to see if you plan to build your system "bottom up" starting with the low-level classes and then later adding the advanced classes that use them, or from the "top down" starting with the high level classes (like the menu) and then working down to the low-level classes.

### Phase 2 - System and Tests

The second thing to hand in will be the full system along with tests and an updated UML diagram.

Your system should be able to behave as discussed in The Project section above and include all of described functionality. All of the important behaviours of your system should be tested with JUnit tests, which cover all of the possible expected inputs to the system.

You will probably update some of your classes from your original UML Class Diagram submission. You should also hand in an updated version of the UML Class Diagram that reflects these changes.

To hand in, you will push your code back into your assignment repository, along with all tests and documents.

## Grading

Your overall grade will be based on the functionality of the system. This will be adjusted by the quality of the code you write, the design of your classes and their relationships, the quality and coverage of your tests, and the quality of your documentation (including JavaDoc and your UML Diagram). The adjustments can move your grade up or down by up to one whole letter grade.

_All work done in this course must be your own:_

- It is acceptable to discuss the problems and algorithms to solve the problems.
- It is also acceptable to look up potential solutions from the Internet.
- It is **not** acceptable to copy code from another student in the class, or from sources on the Internet. When you are incorperating elements that are not your original ideas, you must reconstruct them. Additionally, you should cite where the idea is from.

This means that, for example. if you look up an algorithm on how to shuffle a deck (not that you should need to), you need to rewrite the eniterty of the code yourself. This should include both your own stylistic choices and your own understanding of the algorithm. Copying and pasting the algorithm and changing the variable names does not count as reconstruction.

### Grading Guidelines

A system would receive a grade of **F** if:

- it cannot be compiled, or
- it compiles, but does not have enough functionality to give it a higher grade.

A system would receive a grade of **D** if:

- it has functioning, but incomplete, gameplay for one of the two games.

A system would receive a grade of **C** if:

- it has functioning, complete game play for one of the two games

A system would receive a grade of **B** if:

- it has functioning, complete game play for one of the two games, and
- it has a fully functioning betting system, which returns values to the main menu and saves to file between sessions

A system would receive a grade of **A** if:

- it has functioning, complete game play for both games, and
- it has a fully functioning betting system, which returns values to the main menu and saves between sessions

### Grading Adjustments

Each adjustment can move a grade up or down by a grade step. _For example, a B would become a B+, a B+ would become an A-._

#### For code quality:

- Good code quality will include:
  - Very few code style problems identified by our linter tools.
  - Well (and meaningfully) named classes, methods and variables.
  - Clear and to the point classes, methods and loops.
  - Line lengths limited to 80 chars.
- Poor code quality will include:
  - Many code style problems identified by our linter tools.
  - Poor or confusingly named classes, methods and variables.
  - Convoluted classes, methods and loops.
  - Long, difficult to read lines of code.

#### For class design:

- Good design will include:
  - High-cohesion classes and methods (each class and method should do 1 thing and 1 thing well).
  - Loose coupling between classes (classes should only share the minimum amount of information between themselves).
- Poor design will include:
  - Low-cohesion classes and methods (doing many different things).
  - Tight coupling between classes (classes sharing a lot of information and/or classes controlling other classes).

#### For testing:

- Good testing will include:
  - Tests covering important behaviours for important methods (the methods doing the actual work and calculations).
  - Clearly written tests, with expected output, set up, execution of actual output and comparison easy to see.
  - Testing must include all testable methods from your own code, but also from the `Card`, `CardHand` and `Deck` class.
    - Testing user input can be difficult, so consider that when structuring your classes.
- Poor testing will include:
  - Few tests covering important behaviours for important methods.
  - Poorly written tests.
  - Tests focused on unnecessary aspects of the system.

#### For documentation:

- Good documentation includes:
  - A complete and easy to read UML diagram.
  - Meaningful JavaDoc documentation for every class and method which includes:
    - An explanation of the purpose of the module.
    - An explanation of the parameters of each method.
    - An explanation of the return value of each method.
- Poor documentation includes:
  - An incomplete or unreadable UML diagram.
  - Missing or meaningless JavaDoc documentation.

#### Modifying `Deck`, `CardHand` or `Card`

- **You must leave the three data classes you are provided intact. A change to the classes will result in a one or two grade step reduction, depending on the extent of the changes.**
