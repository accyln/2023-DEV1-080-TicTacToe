# 2023-DEV1-080-TicTacToe

This is a Tic-Tac-Toe game for the kata assesment.<br />
This kata solution developed with Java 17, Springboot framework and React.js.<br />
There are rest web services that provides game information and making a move in the game.<br />
Also there are webUI that consumes this rest services.<br />
Solution analyzed with SonarQube, code coverage calculated and issues solved.<br />


<p><h3>Prerequisites</h3></p>
Before running the solution these are must be installed : <br />
Java<br />
Node.Js<br />
Docker Desktop<br />

<br /> 
<p><h3>Running the application</h3></p>
1.Download or clone the solution to your local computer<br /> 
2.Open Docker Desktop<br /> 
3.At the directory which include docker-compose.yml file, run command <br />
<code>docker-compose -f docker-compose.yml -f docker-compose.override.yml up -d</code><br />
4.Images will be downloaded for docker, it can be take some time<br />
There will be 2 container: <code>TicTacToe</code> <code>WebUi</code> <br />
5.After the installation finish , you can run <code>docker ps</code> command for control all containers is ok<br />
6.Navigate to <a href="http://localhost:8080/swagger-ui/index.html" target="_blank">http://localhost:8080/swagger-ui/index.html</a> , swagger will be opened<br />
7.For the game ui , Navigate to <a href="http://localhost:3000/" target="_blank">http://localhost:3000/</a> <br />
8.And play the game!
<br />

<p><h3>Rules</h3></p>
<br />
-X always goes first.<br />
-Players cannot play on a played position.<br />
-Players alternate placing X’s and O’s on the board until either:<br />
-One player has three in a row, horizontally, vertically or diagonally<br />
-All nine squares are filled.<br />
-If a player is able to draw three X’s or three O’s in a row, that player wins.<br />
-If all nine squares are filled and neither player has three in a row, the game is a draw.<br />

<p><h3>Sonarqube Result</h3></p>
![alt text](https://github.com/accyln/2023-DEV1-080-TicTacToe/blob/feature/codeCoverage/ApplicationInfo/backendCodeCoverage.png)

<p><h3>InGame ScreenShots</h3></p>



