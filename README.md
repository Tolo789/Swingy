# Swingy
42 project: RPG game using Swingy (Java) library.

# Objective
Create an RPG game that can be played either on a GUI or a CLI or even the two of them at the same time.
Moreover, every user input must be validated through an annotation validation (Hibernate) and data must be persisted when exiting in order to resume game later.

### Hero Selection
![Alt text](/screenshots/HeroSelection.png?raw=true "Main Game")

### Begin of Adventure
![Alt text](/screenshots/GameStart.png?raw=true "Main Game")

### Main Game
![Alt text](/screenshots/MainGame.png?raw=true "Main Game")

# Game Requirements
In order to play the game, check the following requirements first:
- Java8 installed (and java and javac commands available on your terminal).
- Maven installed (http://maven.apache.org/install.html).
- Install JDBC driver in project repository (http://www.mkyong.com/maven/how-to-add-oracle-jdbc-driver-in-your-maven-local-repository/).

# How to Launch
Once all the requirements are met just type the following commands to start play:
- "$> cd swingy"
- "$> mvn clean package"
- "$> java -jar target/swingy-1.0-SNAPSHOT-jar-with-dependencies.jar [ui_option]"

If you only want to play on the CLI, the ui_option is "console".
If you only want to play on the GUI, the ui_option is "gui".

# Gameplay Rules
After a new hero is created or an already saved one is loaded the main game starts, here are the main gameplay rules:
- A square map is generated following this formula: mapSize = (heroLevel - 1) * 5 + 10 - (heroLevel % 2).
- An hero gains xp either by winning fights (the amount varies accordingly to the defeated monster) or by reaching the edge of the map (xpGain = heroLevelAtStartOfMap * 1000).
- The needed XP for the hero to reach the next level always follow this formula: level * 1000 + (level - 1) ^ 2 * 450.
- Each turn the player can move in one of the given directions, or to save and exit.
- If the hero bumps into a monster a choice is displayed between Fight and Flee, fleeing only has a 50% chance of succes.
- The result of a fight is automatically calculated, hero and monster alternatively attack the other and, depending on their relative Agility, thay have a chance to either do a critical (x2 damage) or dodging the attack. The damage formula is the following: (2 * attackerLevel / 5 + 2) * attackerAttack / defenderDefense + 2.
- If hero wins a fight that he has started then an artifact may be dropped by the monster (depending on the monster rarity) and the user will be able to choose if equip it or not.
- An artifact will either be an Helm, which gives bonus HP, an Armor, which gives bonus Defense, or a Weapon, which gives bonus Attack. Only an artifact of each kind can be equipped at the same time.
- Note that if the hero didn't succed to fly from monster or has been attacked by one of them, then no drop of artifact will occur.

# Game features
Here is a list of additional game features:
- Player can switch between GUI and CLI at any time during the game (as long as no "ui_option" is given).
- Datas are saved in a relational database (Hibernate/H2).
- Passive Skills: each hero class has a different passive which triggers on different conditions.
- Monster Moods: a monster may either roam, attack the hero, flee from the hero or don't move at all.
- Map in the GUI: scrollable map with both hero and monsters sprites facing direction they last moved towards.
- Obstacles: different types of obstacles, and some can even be destroyed !

# Disclaimer
I do not own any of the sprite used for this game, all ownership and credit goes to the respective spritesheet creator at https://www.spriters-resource.com/ds_dsi/pokemonblackwhite/
