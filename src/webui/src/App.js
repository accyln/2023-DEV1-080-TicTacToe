import React from 'react';
import { Routes ,Route } from "react-router-dom";
import './App.css';
import Layout from './common/Layout';
import { Game } from './components/Game';

export function App() {
  return (
    <div className="gameApp">
      <Layout>
      <div>

        <Routes>
        <Route path='/Game' element={<Game/>} />
        </Routes>

       
      </div>
      </Layout>
    </div>
  );
} 
