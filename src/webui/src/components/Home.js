import React, { Component,useState } from "react";
import { Row, Col,Button,Form } from 'react-bootstrap'
import { useEffect } from "react";
import { GetPlayerInfosModal } from "./modals/GetPlayerInfosModal";
import {useNavigate} from "react-router-dom";

export function Home(props){

    const [showNewGameModal, setShowNewGameModal] = useState(false);
    const navigate = useNavigate();

    function startGame(player1Name,player2Name){
        fetch("CreateGameApi")
        .then(response => {
           if(response){
            navigate("/Game/"+response.id);
           }
        })
        .catch(error => {
            alert("Catching error while creating game!! " +error);
        });
    }
    return(
        <div className="mainPage">
            <Row style={{marginTop:50}} >
                <Col sm={4}></Col>
                <Col sm={4}>
                    <Row>
                    <center>
                     <Form.Label>For starting a new game press the button!</Form.Label>
                     </center>
                  </Row>
                  <br></br>
                    <Row>
                    <center>
                     <Button variant="success" style={{width:120}} onClick={()=>setShowNewGameModal(true)}>
                    New Game
                </Button>
                </center>
                </Row> 
                <GetPlayerInfosModal onHide={()=>setShowNewGameModal(false)} 
                                     show={showNewGameModal}
                                     callback={(player1Name,player2Name)=>startGame(player1Name,player2Name)}/>  
                </Col>
            </Row>
        </div>
        )
}