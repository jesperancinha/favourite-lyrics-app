import React from 'react';
import {render, screen} from '@testing-library/react';
import App from './App';

test('renders learn react link', () => {
    render(<App />);
    const linkElement = screen.getByText(/Favourite Lyrics - Hexagonal Architecture Java 17 Example/i);
    expect(linkElement).toBeInTheDocument();
});
