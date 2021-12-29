# Utaipei-BlackJack

University of Taipei 2021-1 Java programming blackjack final project

## MVC pattern

### Model

Face.java
Suit.java

> Use `enum` to define faces and suits

Card.java
Deck.java

> Use the `for` loop to build decks of cards on the gaming table

Hand.java
Player.java
Dealer.java

> In order to increase the readability and unify the repetitive code, the `Hand` class is created to achieve this purpose

Utilities.java

> Integrate common functions, including `Scanner` and `Thread.sleep`

### View

GamingTable.java

> Show play information and announce win or loss

### Controller

BlackJack.java

> Game rules and process

PlayGame.java

> Main method

## TODO

+ Add accounts and provide betting function

+ Add more advanced blackjack rules

## Bugs

+ An error occurs when the decks are dealt out
