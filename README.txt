________________________❄LANCER_LE_JEU❄_________________________

	 Pour lancer le jeu compiler le fichier "Play"

 >Modifier la ligne 31 : "final Game game = new ...;" 
  et y introduire le jeu correspondant :
           Demo1() pour l'étape 1.
           Demo2() pour l'étape 2.
           Enigme() pour l'étape 3 ,4 et 5.
 
 >Réorganiser les imports.


_______________________❄_CONTROLES_DU_JEU❄____________________________

	>TOUCHE GAUCHE :  Mouvement vers la gauche
	>TOUCHE DROITE :  Mouvement vers la droite
	>TOUCHE HAUT   :  Mouvement vers le haut
        >TOUCHE BAS    :  Mouvement vers le bas
	>ESPACE        :  Mettre le jeu en pause/poursuivre
	>TOUCHE L      :  Interaction à distance pour
                          collecter un objet ou demander
                          une interaction avec un personnage
                          non joueur.Notez que le personnage
                          doit s’orienter en direction de l’interactable.


________________________❄_NIVEAUX_DU_JEU❄__________________________________
    

  > LevelSelector (Comme demandé dans l'énoncé) :
     Choisir une porte : 
       -Porte 1 : Level 1
       -Porte 2 : Level 2
       -Porte 3 : Level 3
       -Porte 4 : Enigme 1
       -Porte 5 : Enigme 2
       -Portes 6-7-8 : Fermées-Inaccessibles

                 -----------------------------
  
  > Level 1 (Comme demandé dans l'énoncé)

    -La porte de sortie mène vers le LevelSelector.

                 -----------------------------
  
   > Level 2 (Comme demandé dans l'énoncé)

    -S'orienter vers la pomme et la collecter au moyen de la touche L.
    -Sortir par la porte. Celle-ci mène vers le levelSelector.
                 -----------------------------
  
   > Level 3 (Comme demandé dans l'énoncé)

    -Pour enlever la pierre à gauche de la porte de sortie soit allumer la torche 
     soit activer le 1er ainsi que le 3ème levier.
    -Pour enlever la pierre devant la porte de sortie marcher sur tous les
     boutons de pression
    -Pour enlever la pierre à droite de la porte de sortie , marcher sur la plaque
     de pression
    -Pour ouvrir la porte , récupérer la clé.
    -La porte de sortie mène vers le LevelSelector

                 -----------------------------
  > Enigme 1

      >Partie 1 de l'énigme 1 : 

    -Aller vers le haut dès que possible jusqu'au plus haut levier apparent
     et l'activer pour faire disparaitre une roche bloquante(qui bloque le passage).
    -Reprendre le même chemin en sens inverse et aller activer le levier le plus
     proche apparent.
    -Aller un peu plus haut et activer le 3ème levier juste à coté du lingot d'or,
     pour faire disparaitre une roche bloquante.
    -Récupérer ce lingot.
    -Descendre en bas à gauche vers le lingot au fond à gauche et le prendre.
    -La route est alors libérée.Vous pouvez maintenant aller vers le haut et prendre
     la sortie à gauche dès que possible.
    -Prendre le seul lingot qui se trouve là-bas,le gardien disparaît et le gel rouge
     devient vert.
    -Se diriger vers la cellule en gel vert qui répresente un portail qui permet de
     se téléporter vers la 2ème partie de l'énigme 1.
  
                 -----------------------------

      >Partie 2 de l'énigme 1 :

    -Récupérer la potion rouge et bleue pour faire apparaître le personnage
     donnant des indices en dialogue.
    -Aller vers le personnage qui apparait et cliquer sur L pour voir son dialogue.
    -Récupérer la hache ,l'épée et l'arc pour que le portail (cellule gel rouge) 
     devient fonctionnel (devient vert)
    -Se diriger vers cette cellule gel verte pour passer à l'énigme 2.

                 -----------------------------
 
  > Enigme 2

   -Aller vers le personnage non joueur et intéragir avec lui  au moyen 
    de la touche L pour voir son dialogue
   -Prendre la clé pour faire disparaître le safeBird(oiseau) et voir apparaître
    le trophée.
   -Si vous le souhaitez , vous pouvez récupérer ce trophée.
   -Marcher sur tous les boutons de pression formant IC pour les allumer afin d'enlever
    la roche bloquant la porte
   -Récupérer tous les gâteaux pour ouvrir la porte
   -Franchir la porte pour revenir au LevelSelector
    

=================================================================
                        MERCI ET BON JEU!                    
=================================================================

   