import { render, screen, fireEvent, cleanup } from '@testing-library/react';
import App from '../App';
import userEvent from "@testing-library/user-event";


afterEach(cleanup)
// Checks to see if home page is rendered
test('Sign In Page Appears', () => {
    render(<App />);
    const SignIn = screen.getByText("Don't have an account?");
    expect(SignIn).toBeInTheDocument();
});

test('Forgot Password Appears', () => {
    render(<App />);
    const forgotPassword = screen.getByText("Forgot Password");
    fireEvent.click(forgotPassword)
    const requestButton = screen.getByText("Forgot Password")
    expect(requestButton).toBeInTheDocument();
});

test('Switch to Sign Up Page works', () => {
    render(<App />);
    const switchScreens = screen.getByText("Sign Up");
    fireEvent.click(switchScreens)

    expect(screen.getByText("Sign In")).toBeInTheDocument();
});

// test('Login works', () => {
//     render(<App />);
//     const emailButton = screen.getByTestId("loginEmail")
//     const passwordButton = screen.getByTestId("loginPassword")
//
//     userEvent.type(emailButton, "t2@gmail.com");
//     userEvent.type(passwordButton, "123456");
//     // emailButton.simulate('change', { target: {value: 't2@gmail.com'}})
//     // passwordButton.simulate('change', { target: {value: '123456'}})
//     const createAccountButton = screen.getByTestId("submitButton")
//     fireEvent.click(createAccountButton)
//
//     const createTask = screen.getByTestId("createTask")
//
//     expect(createTask).toBeInTheDocument();
// });




