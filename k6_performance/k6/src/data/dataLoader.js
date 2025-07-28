import { SharedArray } from 'k6/data';

export const loadDataSet = new SharedArray('Dataset', () => {
    const csv = open('../../../dataset/dataset_random.csv');
    return csv
        .split('\n')
        .slice(1)
        .map((line) => line.trim())
        .filter(line => line.length > 0);
});