# Utaipei-BlackJack

University of Taipei 2021-2 Java programming blackjack final project

## What's New

+ Add accounts and provide betting function

+ Add more advanced blackjack rules, including bet, insurance, double, and surrender

+ Fix a bug: an error occurs when the decks are dealt out

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

Account.java

> Offers optional gambling function

Utilities.java

> Integrate common functions, including `Scanner`, `Thread.sleep`, ect

### View

GamingTable.java

> Show play information and announce win or loss

### Controller

BlackJack.java

> Game rules and process

PlayGame.java

> Main method

## TODO

+ Add split function

## Bugs

+ No bugs found so far
