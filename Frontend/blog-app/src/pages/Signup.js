import { useEffect, useState } from "react";
import { CardHeader, Container, Card, CardBody, Form, FormGroup, Label, Input, Button, Row, Col, FormFeedback } from "reactstrap";
import { signUp } from '../services/user-service'
import Base from "../components/Base";
import { toast } from "react-toastify";
import { Link } from "react-router-dom"

const Signup = () => {

    const [data, setData] = useState({
        name: '',
        email: '',
        password: '',
        about: ''
    })

    const [error, setError] = useState({
        errors: {},
        isError: false
    })

    useEffect(() => {
        console.log(data)
    }, [data])

    const handleChange = (event, property) => {
        setData({ ...data, [property]: event.target.value });   // dynamically setting field values
    }

    const submitForm = (event) => {
        event.preventDefault()

        // send data to backend server
        signUp(data).then((response) => {
            console.log(response)
            console.log("success")
            toast.success("Welcome to Blogster!")
            setData({
                name: '',
                email: '',
                password: '',
                about: ''
            })
        }).catch((error) => {
            console.log(error)
            console.log("error")
            toast.error("Invalid data!")

            setError({
                errors:error,
                isError: true
            })
        })
    }

    return (
        <Base>
            <Row className="mt-4">
                <Col sm={{ size: 6, offset: 3 }}>
                    <Container>
                        <Card color="dark" outline>
                            <CardHeader>
                                <h5>Already have an account? <Link to="/Login">sign in</Link></h5>
                            </CardHeader>
                            <CardBody>
                                <Form onSubmit={submitForm}>
                                    <FormGroup>
                                        <Label for="name">
                                            Name
                                        </Label>
                                        <Input
                                            type="text"
                                            id="name"
                                            onChange={(e) => handleChange(e, 'name')}
                                            value={data.name}
                                            invalid={error.errors?.response?.data?.name ? true: false}>
                                        </Input>
                                        <FormFeedback>
                                            { error.errors?.response?.data?.name }
                                        </FormFeedback>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="email">
                                            Email
                                        </Label>
                                        <Input
                                            type="email"
                                            id="email"
                                            onChange={(e) => handleChange(e, 'email')}
                                            value={data.email}
                                            invalid={error.errors?.response?.data?.email ? true: false}>
                                        </Input>
                                        <FormFeedback>
                                            { error.errors?.response?.data?.email }
                                        </FormFeedback>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="password">
                                            Password
                                        </Label>
                                        <Input
                                            type="password"
                                            id="password"
                                            onChange={(e) => handleChange(e, 'password')}
                                            value={data.password}
                                            invalid={error.errors?.response?.data?.password ? true: false}>
                                        </Input>
                                        <FormFeedback>
                                            { error.errors?.response?.data?.password }
                                        </FormFeedback>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for="about">
                                            About
                                        </Label>
                                        <Input
                                            type="textarea"
                                            id="about"
                                            onChange={(e) => handleChange(e, 'about')}
                                            value={data.about}
                                            invalid={error.errors?.response?.data?.about ? true: false}>
                                        </Input>
                                        <FormFeedback>
                                            { error.errors?.response?.data?.about }
                                        </FormFeedback>
                                    </FormGroup>
                                    <Container className="text-center">
                                        <Button color="dark">Register</Button>
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

export default Signup;