import { myAxios } from "./helper";

export const signUp = (user) => {
    return myAxios
        .post("/api/auth/register", user)
        .then((response) => response.data)
}

export const login = (user) => {
    return myAxios
        .post("/api/auth/login", user)
        .then((response) => response.data)
}