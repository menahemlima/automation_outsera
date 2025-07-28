import { htmlReport } from './bundle.js';

export function handleSummary(data) {
    return {
        "k6_report.html": htmlReport(data)
    };
}