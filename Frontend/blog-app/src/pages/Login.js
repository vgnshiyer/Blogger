import { useState, useEffect } from "react";
import { CardHeader, Container, Card, CardBody, Form, FormGroup, Label, Input, Button, Row, Col } from "reactstrap";
import { toast } from "react-toastify";
import Base from "../components/Base";
import { login } from "../services/user-service";
import { Link } from "react-router-dom"
import { doLogin } from "../auth";

const Login=()=>{

    const [data, setData] = useState({
        username : '',
        password : ''
    })

    const [error, setError] = useState({
        errors: {},
        isError: false
    })

    useEffect(() => {
        // console.log(data)
    }, [data])

    const handleChange = (event, property) => {
        setData({ ...data, [property]: event.target.value });   // dynamically setting field values
    }

    const submitForm = (event) => {
        event.preventDefault()

        if(data.username.trim() === '' || data.password.trim() === ''){
            toast.error("Username or Password cannot be empty!")
            return;
        }

        login(data).then((response) => {
            console.log(response)
            console.log("success")

            // save token to local
            doLogin(response, ()=>{
                console.log("token has been saved to local storage.")
            })

            toast.success("Welcome Back!")
            setData({
                username : '',
                password : ''
            })
            setError({
                errors : {},
                isError : false
            })
        }).catch((error) => {
            console.log(error)
            console.log("error")
            setError({
                error : error,
                isError : true
            })

            if(error.response.status === 400 || error.response.status === 404){
                toast.error(error.response.data.message)
            } else {
                toast.error("Something went wrong...")
            }

        })
    }

    return (
        <Base>
            <Row className="mt-4">
                <Col sm={{ size: 6, offset: 3 }}>
                    <Container>
                        <Card color="dark" outline>
                            <CardHeader>
                                <h5>Don't have an account? <Link to="/Signup">sign up</Link></h5>
                            </CardHeader>
                            <CardBody>
                                <Form onSubmit={submitForm}>
                                    <FormGroup>
                                        <Label for="username">
                                            Email
                                        </Label>
                                        <Input
                                            type="email"
                                            id="username"
                                            onChange={(e) => handleChange(e, 'username')}
                                            value={data.username}>
                                        </Input>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="password">
                                            Password
                                        </Label>
                                        <Input
                                            type="password"
                                            id="password"
                                            onChange={(e) => handleChange(e, 'password')}
                                            value={data.password}>
                                        </Input>
                                    </FormGroup>
                                    <Container className="text-center">
                                        <Button color="dark">Login</Button>
                                    </Container>
                                </Form>
                            </CardBody>
                        </Card>
                    </Container>
                </Col>
            </Row>
        </Base>
    )
};

export default Login;