import Container    from 'react-bootstrap/Container';
import Nav          from 'react-bootstrap/Nav';
import Navbar       from 'react-bootstrap/Navbar';
import { Link }     from 'react-router-dom';

export function Header(){
    return (
        <div className="header">
            <Navbar bg="light" expand="lg">
                <Container>
                    <Navbar.Brand>Words Counter</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Navbar.Brand href="/">Text</Navbar.Brand>
                        <Navbar.Brand href="/count/url">Url</Navbar.Brand>
                    </Nav>
                    </Navbar.Collapse>
                </Container>
             </Navbar>
        </div>
    );
}