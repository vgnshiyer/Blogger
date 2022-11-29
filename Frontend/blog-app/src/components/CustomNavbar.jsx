import { useContext } from "react";
import { useEffect } from "react";
import { useState } from "react";
import { Navigate, NavLink as ReactLink, useNavigate } from "react-router-dom";
import { Navbar, NavbarBrand, NavbarToggler, Collapse, Nav, NavItem, NavLink, UncontrolledDropdown, DropdownToggle, DropdownMenu, DropdownItem, NavbarText } from "reactstrap";
import { doLogout, getCurrentUser, isLoggedin } from "../auth";

const CustomNavbar = () => {
  const [isOpen, setIsOpen] = useState(false)
  const [login, setLogin] = useState(false)
  const [user, setUser] = useState(undefined)

  useEffect(() => {
    setLogin(isLoggedin())
    setUser(getCurrentUser())
  })

  const logout = () => {
    doLogout(() => {
      setLogin(false)
    })
  }

  return (
    <div >
      <Navbar
        color="dark"
        dark
        expand="md"
        fixed=""
        className="px-5"
      >
        <NavbarBrand tag={ReactLink} to="/">
          BlogSter
        </NavbarBrand>
        <NavbarToggler onClick={() => setIsOpen(!isOpen)} />
        <Collapse isOpen={isOpen} navbar>
          <Nav
            className="me-auto"
            navbar
          >
            <NavItem>
              <NavLink tag={ReactLink} to="/" >
                New Feed
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink tag={ReactLink} to="/about" >
                About
              </NavLink>
            </NavItem>
          </Nav>
          <Nav navbar>
            {
              login && (
                <>
                  <NavItem>
                    <NavLink tag={ReactLink} to="/user/profile-info" >
                      Profile Info
                    </NavLink>
                  </NavItem>
                  <NavItem>
                    <NavLink onClick={logout} >
                      Logout
                    </NavLink>
                  </NavItem>
                </>
              )
            }
            {
              !login && (
                <>
                  <NavItem>
                    <NavLink tag={ReactLink} to="/login" >
                      Login
                    </NavLink>
                  </NavItem>
                  <NavItem>
                    <NavLink tag={ReactLink} to="/signup" >
                      Signup
                    </NavLink>
                  </NavItem>
                </>
              )
            }
          </Nav>
        </Collapse>
      </Navbar>
    </div>
  )
}

export default CustomNavbar