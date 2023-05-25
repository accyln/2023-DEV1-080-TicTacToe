import React, { useState, useEffect } from "react";
import { Container, Card, Row, Col, Button} from 'react-bootstrap'
import {useNavigate} from "react-router-dom";



export function GameHistory(props){

    const [gameList, setGameList] = useState([]);
    const [loading, setLoading] = useState(true);

    const navigate = useNavigate();

    useEffect(() => {

        getGameList();
    
      }, []);
    
    
      function getGameList() {
            fetch(
              "http://localhost:8080/api/v1/game/getAllGamesWithDetails"
            ).then(response => response.json())
            .then((data) => {
              if (data) {
                setGameList(data);
              }
            });
    }
      


return (
      <Container fluid>
        <Row>
          <Card style={{ borderColor: "white" }}>
            <Card.Header style={{ backgroundColor: "steelblue" }}><div class="col text-center"><span style={{ color: "white" }}>Games</span></div></Card.Header>
            <Card.Body style={{ textAlign: 'center', marginTop: 5,marginLeft:50,marginRight:50 }}>
              <Row>
              <div class="card shadow border-1 mb-7 mb-2">
                      <div class="card-header border-0">
                        <Row>
                          <Col  style={{fontWeight:"bold"}}>Game ID</Col>
                          <Col  style={{fontWeight:"bold"}}>Player 1</Col>
                          <Col  style={{fontWeight:"bold"}}>Player 2</Col>
                          <Col  style={{fontWeight:"bold"}}>Winner</Col>
                          <Col  style={{fontWeight:"bold"}}>
                          </Col></Row>
                      </div></div>
              </Row>
              <Row>
                {gameList?.map((item, index) => {
                  return (
                    <div class="card shadow border-1 mb-7 mb-2">
                      <div class="card-header border-0">
                        <Row>
                          <Col >{item.id}</Col>
                          <Col >{item.player1.playerName}</Col>
                          <Col >{item.player2.playerName}</Col>
                          <Col >{item.winner && item.winner!="\u0000" ? item.winner : "Ongoing"}</Col>
                          <Col ><Button size="sm" onClick={()=>{
                            navigate("/Game/"+item.id);
                          }}>Open Game</Button></Col>
                          </Row>
                      </div></div>
                      )
                })}
              </Row>
 
            </Card.Body>
          </Card>
        </Row>
      </Container>
  )

}