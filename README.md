# ObstACL Tower

#### Iopeti Hugo, MATHIEU STEINBACH Hugo, YVOZ Ludovic & ZIMOL Guillaume

## Sommaire

1. <a href="#vision-du-jeu-final">Vision du jeu final</a>
2. <a href="#comment-compiler-et-exécuter-le-projet">Compilation & exécution du projet</a>
3. <a href="comment-jouer-au-jeu">Comment jouer au jeu</a>

Pour tout ce qui concerne notre organisation des sprints, tout est détaillé dans le Sprints.md.

<img src="./assets/obstACL_tower.png" alt="illustration"/>

-----

## Vision du jeu final

Notre jeu sera un rogue-lite basé sur un système de tour à gravir. Un héros pourra monter d’étages en étages, chaque
étage est un labyrinthe contenant des monstres et éventuellement des pièges. Certains étages auront des défis plus rares
ou compliqués sous forme d’événements spéciaux ou bien de boss. Au cours de son aventure, le héros va accumuler des
ressources qu’il pourra utiliser pour faciliter son exploration. Une fois mort, certaines ressources sont gardées même
après la mort et peuvent être utilisées pour obtenir des améliorations permanentes du héros.

-----

## Comment compiler et exécuter le projet

Premièrement, il faut avoir installer java : <br>
https://www.java.com/fr/download/ <br>
Pour compiler le projet il vous faut un jdk java disponible ici : <br>
https://www.oracle.com/java/technologies/downloads/#jdk17 <br>
Il faut ensuite exécuter cette commande à la racine du projet :
> ./gradlew desktop:dist

Puis pour lancer le jeu il faut exécuter la commande suivante toujours à la racine du projet : <br><br>
-Sur Windows/Linux :
> java -jar "./desktop/build/libs/desktop-1.0.jar"

-Sur MacOS
> java -XstartOnFirstThread -jar "./desktop/build/libs/desktop-1.0.jar"

-----

## Comment jouer au jeu

### L'objectif du jeu

Vous incarnez un chevalier dans une tour de plusieurs étages,
le but est de progresser d'étage en étage en empruntant les escaliers.
Pour ce faire il faut tuer tous les monstres de l'étage, ce qui aura pour effet de retirer la trappe présente au-dessus des escaliers.
Attention cependant le contact avec les monstres vous inflige des dégâts.
Des éléments comme des tonneaux et des trésors sont également dispersés à travers les étages et permettent d'augmenter le score, 
lors de leur destruction par le joueur.

### Les monstres

Il existe plusieurs types de monstres, les slimes qui se déplacent de manière aléatoire, les gardiens qui font
aléatoirement des mouvements verticaux ou horizontaux jusqu'à rencontrer des murs. Et la liche qui fait le tour de l'étage.

### Les touches

Echap permet d'accéder au menu.<br>
Espace d'utiliser l'arme.<br>
WASD/ZQSD ou les flèches directionnelles pour se déplacer.<br>
Shift_Left pour le mode de Debug.<br>
P Pour le passage en plein écran.<br>



