import React,{ useEffect, useState } from "react";
import { Row, Col,Button} from 'react-bootstrap';
import {useParams} from "react-router-dom";


export function Game(props){

    const [game,setGame]=useState([[]]);

    const propsVariables=useParams();

    useEffect(()=>{
        getgameInfo();
    },[]);

    function getgameInfo() {
        fetch(
          "http://localhost:8080/api/v1/game/getGameById?gameId="+propsVariables.gameId
        ).then((data) => {
          if (data) {
            setGame(data);
          }
        });
      }

    const renderSquare = (index,sign) => {
        return (
          <Button className="square" style={{backgroundColor:"whitesmoke",height:70,width:70,borderColor:"gray"}}>
           <span style={{color:"black"}}>{sign}</span>
          </Button>
        );
      }

    return(
    <div className="game">
        <Row>
      <div style={{height:100,marginTop:30}}></div>
      </Row>
      <Row>
        <Col sm={4}></Col>
      <Col sm={4}><center>
      <div className="gameBoard">
        {game ? (<>
      <div className="board-row">
        {renderSquare(0,game?.board?.[0]?.[0])}
        {renderSquare(1,game?.board?.[0]?.[1])}
        {renderSquare(2,game?.board?.[0]?.[2])}
      </div>
      <div className="board-row">
        {renderSquare(3,game?.board?.[1]?.[0])}
        {renderSquare(4,game?.board?.[1]?.[1])}
        {renderSquare(5,game?.board?.[1]?.[2])}
      </div>
      <div className="board-row">
        {renderSquare(6,game?.board?.[2]?.[0])}
        {renderSquare(7,game?.board?.[2]?.[1])}
        {renderSquare(8,game?.board?.[2]?.[2])}
      </div>
       </>):(null)}
      </div>
      </center>
      </Col>
     </Row>
    </div>
    )
}