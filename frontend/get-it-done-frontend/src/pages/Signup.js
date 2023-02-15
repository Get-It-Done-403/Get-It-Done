import React, {useRef, useState} from 'react'
import  { Form, Button, Card } from 'react-bootstrap'
import {useAuth} from "./Contexts/AuthContext";

export default function Signup() {
    const [error, setError] = useState('')
    const [loading, setLoading] = useState('')
    const emailRef = useRef()
    const passwordRef = useRef()
    const passwordConfirmRef = useRef()
    const { signup } = useAuth()

    async function handleSubmit(e) {
        e.preventDefault()
        if (passwordRef.current.valueOf() !== passwordConfirmRef.current.valueOf()) {
            return setError('Passwords do not match')
        }

        try {
            setError('')
            setLoading(true)
            await signup(emailRef.current.valueOf(), passwordRef.current.valueOf())
        } catch {
            setError('Failed to create an account')
        }
        setLoading(false)

        signup(emailRef.current.valueOf(), passwordRef.current.valueOf(), )
    }

    return (
        <>
            <Card>
                <Card.Body>
                    <h2 className="text-center mb-4">Sign Up</h2>
                    <Form onSubmit={handleSubmit}>
                        <Form.Group id="email">
                            <Form.Label>Email</Form.Label>
                            <Form.Control type="email" ref={emailRef} required />
                        </Form.Group>
                        <Form.Group id="password">
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" ref={passwordRef} required />
                        </Form.Group>
                        <Form.Group id="password-confirm">
                            <Form.Label>Password Confirmation</Form.Label>
                            <Form.Control type="passwordConfirmation" ref={passwordConfirmRef} required />
                        </Form.Group>
                        <Button disabled={loading} className="w-100" type="submit">Sign Up</Button>
                    </Form>
                </Card.Body>
            </Card>
            <div className="w-100 text-center mt-2">
                Already have an account? Login
            </div>
        </>
    )
}