import React from 'react';
import { Routes ,Route } from "react-router-dom";
import './App.css';
import Layout from './common/Layout';
import { Game } from './components/Game';
import { Home } from './components/Home';
import { GameHistory } from './components/GameHistory';

export function App() {
  return (
    <div className="gameApp">
      <Layout>
      <div>

        <Routes>
        <Route path='/' element={<Home/>} />
        <Route path='/Game/:gameId' element={<Game/>} />
        <Route path='/GameHistory' element={<GameHistory/>} />
        </Routes>

       
      </div>
      </Layout>
    </div>
  );
} 
