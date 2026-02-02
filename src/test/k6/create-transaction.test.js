import { check } from 'k6';
import http from 'k6/http';
import {
  randomString,
  randomIntBetween
} from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';

const host = 'localhost';
const port = '8080';
const endpoint = '/v1/transacao/async'

function randomDoubleFixed(min, max, decimals) {
  const random = Math.random() * (max - min) + min;
  return parseFloat(random.toFixed(decimals));
}

const utcOffset = 180*60;
const oneMinute = 60*1000;
const now = Date.now()

function randomDate() {
const randomMinutes = randomIntBetween(1, 5);
const last = new Date(now - (randomMinutes * oneMinute) );
const isoDateString = last.toISOString().split('T')[0];
const timeString = last.toLocaleTimeString();
return [isoDateString, timeString].join('T');

}

export default function () {
    let payload = JSON.stringify({
        valor:  `${randomDoubleFixed(1, 5000, 2)}`,
        dataHora: `${randomDate()}`
    });

    let params = { headers: { "Content-Type": "application/json" } };
    let res = http.post(`http://${host}:${port}${endpoint}`, payload, params);

    check(res, { 'is status 202': (r) => r.status === 202 });
}