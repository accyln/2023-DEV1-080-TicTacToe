import React, { useState } from 'react'
import { Modal, Button, Row, Col, Form } from 'react-bootstrap';

export function GetPlayerInfosModal(props){

    const [player1Name, setPlayer1Name] = useState(null);
    const [player2Name, setPlayer2Name] = useState(null);

    function startGame(){
        if(player1Name && player2Name){
          props.callback(player1Name,player2Name);
        } else alert("Please enter all player names.")
    }

    return (
        <Modal
            show={props.show}
            size="sm"
            aria-labelledby="contained-modal-title-vcenter"
            centered>
            <Modal.Header clooseButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    New Game
                            </Modal.Title>
                            <button type="button" class="btn-close" aria-label="Close" style={{float:"right"}} onClick={props.onHide}></button>
            </Modal.Header>
            <Modal.Body>
                <Row style={{ marginLeft: 3 }}>
                    <Col sm={11}>
                        <Row>
                    <Form.Label>Player X Name <span style={{ color: "red" }}>*</span></Form.Label>
                        <div>
                            <textarea className="form-control" id="player1Name" rows="1" value={player1Name} onChange={(e)=>setPlayer1Name(e.target.value)}></textarea>
                        </div>
                        </Row>
                        <br></br>
                        <Row>
                        <Form.Label>Player O Name <span style={{ color: "red" }}>*</span></Form.Label>
                        <div>
                            <textarea className="form-control" id="player2Name" rows="1" value={player2Name} onChange={(e)=>setPlayer2Name(e.target.value)}></textarea>
                        </div>
                        </Row>
                    </Col>
                    </Row>
                    </Modal.Body>
                    <Modal.Footer>
                <Button variant="success" onClick={()=>startGame()}>Start Game</Button>
            </Modal.Footer>
                    </Modal>

        )
}