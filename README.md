<h1>Technologies</h1>
<ul>
    <li>OpenJDK 22.0.2</li>
    <li>maven 3.9.9</li>
</ul>
<br>
    <h1>Dépendances</h1>
    <li><b>JUnit 5.8.1:</b> utilisé pour les tests</li>
    <li><b>argon2 2.11:</b> Utilisé pour le hashage des mots de passes</li>
    <li></li>
    <li><b>JavaFX Controls:</b> Utilisé pour les contrôler les visuels</li>
    <li><b>JavaFX FXML:</b> Utilisé pour décrire les vues</li>
    <li><b>Gluon HQ</b> Utilisé pour ajouter d'autres composant pour les visuels</li>
</ul>
<br>
<h1>Installation</h1>
<p>Installer <b>OpenJDK 22.0.2</b>, <b>maven 3.9.9</b> et <b>Git</b</p>
<p><b>Recommandation:</b> Sur windows utiliser choco</p>
<h3>Installer choco</h3>
dans powershell<br>
<code>Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))</code>

<h3>Installer les technologies</h3>
dans powershell<br>
<code>
choco install openjdk --version=22.0.2<br>
choco install maven --version=3.9.9<br>
choco install git
</code>

<h3>Télécharger le repos</h3>
<code>git clone https://github.com/Clement-soulier/L3-Fiche_personnage.git</code>
<br>
<br>
<h1>Compilation et exécution</h1>
<p>Ouvrir un terminal à la racine du projet et lancer la commande</p>
<code>mvn exec:java</code>
<br>
<br>
<h1>Run les test</h1>
<p>Ouvrir un terminal à la racine du projet et lancer la commande:</p>
<code>mvn -Dtest="fr.clement_tristan_olivier.liste_personnage.model.CompteTest" test -X</code>
<br>
<br>
<h1>Build</h1>
<p>Pour créer un exécutable Jar, ouvrir un terminal à la racine du projet et lancer la commande:</p>
<code>mvn package</code>
<br>
<br>
<p>Pour exécuter l'exécutable jar généré lancer la commande:</p>
<code>java -jar target/App.jar</code>