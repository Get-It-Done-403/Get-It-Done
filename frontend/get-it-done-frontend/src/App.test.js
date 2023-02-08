import { render, screen, fireEvent } from '@testing-library/react';
import App from './App';
// Checks to see if home page is rendered
test('renders create new task button', () => {
  render(<App />);
  const linkElement = screen.getByText("Create new Task");
  expect(linkElement).toBeInTheDocument();
});

// Checks to see if Calendar Page router works
test('renders create new task button', () => {
  render(<App />);

  const calendarButton = screen.getByText("Calendar")
  fireEvent.click(calendarButton)
  const month = screen.getByText("February 2023")
  expect(month).toBeInTheDocument();
});


