import React from 'react';
import styled from 'styled-components';
import { NavMenu } from './NavMenu';

// component styles
const Wrapper = styled.div`
    @media (min-width: 700px) {
        display: flex;
        position: fixed;
        height: calc(100% - 100px);
        width: 100%;
        flex: auto;
        flex-direction: column;
    }
`;
const Main = styled.main`
    position: fixed;
    top: 80px;
    width: 100%;
`;
const Layout = ({ children }) => {
    return (
    <React.Fragment>
        <Wrapper>
            <NavMenu/>
            <Main>{children}</Main>
        </Wrapper>
    </React.Fragment>
    );
};
export default Layout;