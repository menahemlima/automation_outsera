import { check } from 'k6';

export function assertPredictResponse(response, expectedInput, maxDurationMs) {
    check(response, {
        'Status Code should be 200': (r) => r.status === 200,
        [`Response time must be less than ${maxDurationMs}ms`]: (r) =>
            r.timings.duration < maxDurationMs,
        'JSON response must not be null': (r) => r.json() !== null

    });
}