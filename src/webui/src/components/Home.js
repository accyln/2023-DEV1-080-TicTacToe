import React, { Component,useState } from "react";
import { Row, Col,Button,Form } from 'react-bootstrap'
import { useEffect } from "react";
import { GetPlayerInfosModal } from "./modals/GetPlayerInfosModal";

export function Home(props){

    const [showNewGameModal, setShowNewGameModal] = useState(false);
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
                <GetPlayerInfosModal onHide={()=>setShowNewGameModal(false)} show={showNewGameModal}/>  
                </Col>
            </Row>
        </div>
        )
}