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
        ).then(response => response.json())
        .then((data) => {
          if (data) {
            setGame(data);
          }
        });
      }

    function makeAmove(index,sign){
        let row=0;
        let col=0;
        switch (index) {
          case 0:
            row=0;
            col=0;
          break;
          case 1:
            row=0;
            col=1;
          break;
          case 2:
            row=0;
            col=2;
          break;
          case 3:
            row=1;
            col=0;
          break;
          case 4:
            row=1;
            col=1;
          break;
          case 5:
            row=1;
            col=2;
          break;
          case 6:
            row=2;
            col=0;
          break;
          case 7:
            row=2;
            col=1;
          break;
          case 8:
            row=2;
            col=2;
          break;
        
          default:
            break;
        }

        let requestBody={
            gameId: game.id,
             rowId: row,
             colId: col,
             sign: sign
        }

        var header = {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
           };

        fetch("http://localhost:8080/api/v1/game/makeAmove", {
          method: 'POST',
          headers:header,
          body: JSON.stringify(requestBody)
        }).then(response => response.json())
          .then((data) => {
            if (data) {
              setGame(data);
            }
          });
        
      }


    const handleClickSquare = (index) => {
            makeAmove(index,game?.lastPlayedSign == 'X' ? 'O' : 'X');   
    }
    

    const renderSquare = (index,sign) => {
        return (
          <Button className="square" style={{backgroundColor:"whitesmoke",height:70,width:70,borderColor:"gray"}}
          onClick={()=>handleClickSquare(index)}>
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
        {game?.board ? (<>
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