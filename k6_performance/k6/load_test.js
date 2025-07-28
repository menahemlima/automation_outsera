import { options } from './src/config/options.js';
import { loadDataSet } from './src/data/dataLoader.js';
import { predict } from './src/api/predictApiClient.js';
import { assertPredictResponse } from './src/utils/assertions.js';
import { handleSummary } from './src/reporters/htmlReporter.js';

import { sleep } from 'k6';

export { options };

const dataSet = loadDataSet;

export default function () {
    const textoAleatorio = dataSet[Math.floor(Math.random() * dataSet.length)];
    const maxTempoResposta = 300;
    const response = predict(textoAleatorio);

    assertPredictResponse(response, textoAleatorio, maxTempoResposta);
    sleep(1);
}

export { handleSummary };