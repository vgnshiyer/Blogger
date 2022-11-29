import { Navigate } from "react-router-dom"

export const isLoggedin = () => {
    let data = localStorage.getItem("data")
    return (data === null ? false : true)
}

export const doLogin = (data, next) => {
    localStorage.setItem("data", JSON.stringify(data))
    next()
}

export const doLogout = (next) => {
    localStorage.removeItem("data")
    next()
}

export const getCurrentUser = () => {
    if(isLoggedin){
        return JSON.parse(localStorage.getItem("data")).user;
    } 
    return false
}