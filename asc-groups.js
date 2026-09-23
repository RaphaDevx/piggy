#!/usr/bin/env node
'use strict';

const crypto = require('crypto');
const fs = require('fs');
const https = require('https');
const { URL } = require('url');

const KEY_ID    = '56XGD2G938';
const ISSUER_ID = '574f04c9-0353-4156-9ad6-28ffe3f42850';
const KEY_PATH  = '/home/raphael/K-Dev/private/AuthKey_56XGD2G938.p8';
const APP_ID    = '6802367826';
const GROUPS    = ['Internal Testers', 'Raphael Private Beta'];

function generateJWT() {
  const privateKey = fs.readFileSync(KEY_PATH, 'utf8');
  const header  = Buffer.from(JSON.stringify({ alg: 'ES256', kid: KEY_ID, typ: 'JWT' })).toString('base64url');
  const now     = Math.floor(Date.now() / 1000);
  const payload = Buffer.from(JSON.stringify({ iss: ISSUER_ID, iat: now, exp: now + 1200, aud: 'appstoreconnect-v1' })).toString('base64url');
  const sign    = crypto.createSign('SHA256');
  sign.update(`${header}.${payload}`);
  const sig = sign.sign({ key: privateKey, dsaEncoding: 'ieee-p1363' }).toString('base64url');
  return `${header}.${payload}.${sig}`;
}

function asc(method, path, body) {
  return new Promise((resolve, reject) => {
    const token = generateJWT();
    const bodyBuf = body ? Buffer.from(JSON.stringify(body)) : null;
    const headers = {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    };
    if (bodyBuf) headers['Content-Length'] = bodyBuf.length;
    const u = new URL(`https://api.appstoreconnect.apple.com${path}`);
    const req = https.request({ hostname: u.hostname, path: u.pathname + u.search, method, headers }, res => {
      const chunks = [];
      res.on('data', c => chunks.push(c));
      res.on('end', () => {
        const raw = Buffer.concat(chunks).toString();
        let json = null;
        try { json = JSON.parse(raw); } catch (_) {}
        resolve({ status: res.statusCode, body: json || raw });
      });
    });
    req.on('error', reject);
    if (bodyBuf) req.write(bodyBuf);
    req.end();
  });
}

async function main() {
  // 1. Find the latest build (build 12)
  const r1 = await asc('GET', `/v1/apps/${APP_ID}/builds?limit=50&fields[builds]=version,uploadedDate,processingState`);
  if (r1.status !== 200) { console.error('GET builds failed:', r1.status, JSON.stringify(r1.body)); process.exit(1); }
  const builds = r1.body.data || [];
  console.log('Recent builds:');
  builds.forEach(b => console.log(`  #${b.attributes.version} — ${b.attributes.processingState} — ${b.attributes.uploadedDate}`));

  const build = builds.find(b => b.attributes.version === '16');
  if (!build) {
    console.error('BUILD_NOT_FOUND. Current builds:', builds.map(b => `#${b.attributes.version}(${b.attributes.processingState})`).join(', '));
    process.exit(1);
  }
  if (build.attributes.processingState !== 'VALID') {
    console.error(`BUILD_NOT_VALID: state=${build.attributes.processingState} — still processing`);
    process.exit(1);
  }
  const buildId = build.id;
  console.log(`\nUsing build: #${build.attributes.version} (${buildId})`);

  // 2. Get all beta groups for the app
  const r2 = await asc('GET', `/v1/apps/${APP_ID}/betaGroups?fields[betaGroups]=name&limit=50`);
  if (r2.status !== 200) { console.error('GET betaGroups failed:', r2.status, JSON.stringify(r2.body)); process.exit(1); }
  const allGroups = r2.body.data || [];
  console.log('\nAll groups:', allGroups.map(g => `${g.attributes.name} (${g.id})`).join(', '));

  const targetGroups = allGroups.filter(g => GROUPS.includes(g.attributes.name));
  if (targetGroups.length === 0) { console.error('Target groups not found!'); process.exit(1); }

  // 3. Add build to each group
  let allOk = true;
  for (const group of targetGroups) {
    const r3 = await asc('POST', `/v1/betaGroups/${group.id}/relationships/builds`, {
      data: [{ type: 'builds', id: buildId }],
    });
    const ok = r3.status >= 200 && r3.status < 300;
    console.log(`\n[${ok ? 'OK' : 'ERR'}] Add to "${group.attributes.name}" → HTTP ${r3.status}`);
    if (!ok) { console.log('  Body:', JSON.stringify(r3.body).slice(0, 400)); allOk = false; }
  }

  if (!allOk) { console.error('ASSIGN_FAILED'); process.exit(1); }

  // 4. Beta App Review einreichen (für externe Gruppen nötig)
  console.log('\n── Step 4: Beta App Review Submit ──');
  const r4 = await asc('POST', '/v1/betaAppReviewSubmissions', {
    data: {
      type: 'betaAppReviewSubmissions',
      relationships: {
        build: { data: { type: 'builds', id: buildId } },
      },
    },
  });
  const reviewOk = r4.status >= 200 && r4.status < 300;
  if (reviewOk) {
    console.log('[OK] Beta Review submitted → Build geht zu Apple zur Prüfung');
  } else if (r4.status === 409) {
    // 409 = bereits eingereicht, ist OK
    console.log('[OK] Beta Review bereits eingereicht (409 = kein Problem)');
  } else {
    console.log(`[WARN] Beta Review submit → HTTP ${r4.status}:`, JSON.stringify(r4.body).slice(0, 300));
  }

  console.log('\nALL_GROUPS_ASSIGNED');
}

main().catch(e => { console.error('Fatal:', e); process.exit(1); });
