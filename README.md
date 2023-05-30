# 2023-DEV1-080-TicTacToe

This is a Tic-Tac-Toe game for the kata assesment.<br />
This kata solution developed with Java 17, Springboot framework and React.js.<br />
There are rest web services that provides game information and making a move in the game.<br />
Also there are webUI that consumes this rest services.<br />
Utilized JaCoCo for calculating code coverage reports.<br />
Also solution analyzed with SonarQube.<br />


<p><h3>Prerequisites</h3></p>
Before running the solution these are must be installed : <br />
Java and Node.Js<br />
OR<br />
Docker Desktop<br />


<p><h3>Running the application</h3></p>
<p><h5>With Docker</h5></p>
1.Download or clone the solution to your local computer<br /> 
2.Open Docker Desktop<br /> 
3.At the directory which include docker-compose.yml file, run command <br />
<code>docker-compose -f docker-compose.yml -f docker-compose.override.yml up -d</code><br />
4.Images will be downloaded for docker, it can be take some time<br />
There will be 2 container: <code>TicTacToe</code> <code>WebUi</code> <br />
5.After the installation finish , you can run <code>docker ps</code> command for control all containers is ok<br />
6.Navigate to <a href="http://localhost:8080/swagger-ui/index.html" target="_blank">http://localhost:8080/swagger-ui/index.html</a> , swagger will be opened<br />
7.For the game ui , Navigate to <a href="http://localhost:3000/" target="_blank">http://localhost:3000/</a> <br />
8.And play the game!<br />

<p><h5>Without Docker</h5></p>
1.Download or clone the solution to your local computer<br /> 
2.Open command promt in solution path and run <code>mvn package</code><br />
3.Cd to /target folder and Run <code>java -jar tictactoe-0.0.1-SNAPSHOT.jar</code><br />
4.Navigate to <a href="http://localhost:8080/swagger-ui/index.html" target="_blank">http://localhost:8080/swagger-ui/index.html</a> , swagger will be opened<br />
5.For the game ui , open command prompt in webui path (src/webui) and run <code>npm install</code><br />
6.After npm packages installation finish, run command <code>npm start</code><br />
7.Navigate to <a href="http://localhost:3000/" target="_blank">http://localhost:3000/</a> <br />
8.And play the game!<br />
  

<p><h3>Rules</h3></p>
-X always goes first.<br />
-Players cannot play on a played position.<br />
-Players alternate placing X’s and O’s on the board until either:<br />
-One player has three in a row, horizontally, vertically or diagonally<br />
-All nine squares are filled.<br />
-If a player is able to draw three X’s or three O’s in a row, that player wins.<br />
-If all nine squares are filled and neither player has three in a row, the game is a draw.<br />

<p><h3>InGame ScreenShots</h3></p>
Home<br/>

<img src="https://github.com/accyln/2023-DEV1-080-TicTacToe/blob/main/ApplicationInfo/homePage.png" width="800" height="150">

InGame<br/>

<img src="https://github.com/accyln/2023-DEV1-080-TicTacToe/blob/main/ApplicationInfo/inGame.png" width="800" height="300">

Game History<br/>

<img src="https://github.com/accyln/2023-DEV1-080-TicTacToe/blob/main/ApplicationInfo/gameHistory.png" width="800" height="200">

<p><h3>Sonarqube Result</h3></p>

<img src="https://github.com/accyln/2023-DEV1-080-TicTacToe/blob/main/ApplicationInfo/backendCodeCoverage.png" width="600" height="100">

<p><h3>Containers</h3></p>

<img src="https://github.com/accyln/2023-DEV1-080-TicTacToe/blob/main/ApplicationInfo/containers.png" width="600" height="300">





