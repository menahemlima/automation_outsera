import http from 'k6/http';

const BASE_URL = 'http://localhost:5000';

export function predict(inputText) {
    const url = `${BASE_URL}/predict`;
    const payload = JSON.stringify({
        input: inputText,
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    return http.post(url, payload, params);
}