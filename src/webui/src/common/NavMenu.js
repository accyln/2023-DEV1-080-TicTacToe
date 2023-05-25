import React, { Component } from 'react';
import { Collapse, Container, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink } from 'reactstrap';
import { Link } from 'react-router-dom';
import './NavMenu.css';

export class NavMenu extends Component {
    static displayName = NavMenu.name;
    constructor (props) {
        super(props);

      }
    
    
      render () {
        return (
          <header>
            <Navbar className="navbar-expand-sm navbar-toggleable-sm ng-white border-bottom box-shadow mb-3" light>
              <Container>
                <NavbarBrand tag={Link} to="/">Tic-Tac-Toe Game</NavbarBrand>
                  <ul className="navbar-nav flex-grow">
                    <NavItem>
                      <NavLink tag={Link} className="text-dark" to="/">Home</NavLink>
                    </NavItem>   
                  </ul>
                  <div ><span style={{marginRight:10}}></span></div>
              </Container>
            </Navbar>
          </header>
        );
      }
    
    }
