import React, { useState, useEffect } from "react";
import { Container, Card, Row, Col, Button } from 'react-bootstrap'
import { useNavigate } from "react-router-dom";
import LoadingOverlay from 'react-loading-overlay-ts';
import { GetSecureBase } from "../common/basepages/RequestHandler";



export function GameHistory(props) {

    const [gameList, setGameList] = useState([]);
    const [loading, setLoading] = useState(true);

    const navigate = useNavigate();

    useEffect(() => {

        getGameList();

    }, []);


    function getGameList() {
        GetSecureBase(
            "api/v1/game/getAllGamesWithDetails"
        ).then((data) => {
                if (data) {
                    setGameList(data);
                    setLoading(false);
                }
            });
    }



    return (
        <LoadingOverlay
            active={loading}
            spinner
            text='Game list loading, please wait...'>
            <Container fluid>
                <Row>
                    <Card style={{ borderColor: "white" }}>
                        <Card.Header style={{ backgroundColor: "steelblue" }}><div class="col text-center"><span style={{ color: "white" }}>Games</span></div></Card.Header>
                        <Card.Body style={{ textAlign: 'center', marginTop: 5, marginLeft: 50, marginRight: 50 }}>
                            <Row>
                                <div class="card shadow border-1 mb-7 mb-2">
                                    <div class="card-header border-0">
                                        <Row>
                                            <Col style={{ fontWeight: "bold" }}>Game ID</Col>
                                            <Col style={{ fontWeight: "bold" }}>Player X</Col>
                                            <Col style={{ fontWeight: "bold" }}>Player O</Col>
                                            <Col style={{ fontWeight: "bold" }}>Status</Col>
                                            <Col style={{ fontWeight: "bold" }}>Winner</Col>
                                            <Col style={{ fontWeight: "bold" }}>
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
                                                    <Col >{item.gameStatus}</Col>
                                                    <Col >{item.gameStatus=="ENDED" && item.winner && (item.winner == "X" || item.winner=="O") ? item.winner 
                                                    : item.gameStatus=="ENDED" && (item.winner != "X" || item.winner !="O") ? "DRAW" : ""}</Col>
                                                    <Col ><Button size="sm" onClick={() => {
                                                        navigate("/Game/" + item.id);
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
        </LoadingOverlay>
    )

}