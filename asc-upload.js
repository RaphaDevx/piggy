#!/usr/bin/env node
'use strict';

const crypto = require('crypto');
const fs = require('fs');
const https = require('https');
const http = require('http');
const path = require('path');
const { URL } = require('url');

// ── Config ────────────────────────────────────────────────────────────────────
const KEY_ID     = '56XGD2G938';
const ISSUER_ID  = '574f04c9-0353-4156-9ad6-28ffe3f42850';
const KEY_PATH   = '/home/raphael/K-Dev/private/AuthKey_56XGD2G938.p8';
const APP_ID     = '6802367826';
const VERSION_ID = 'adc71d9b-b1fc-4bd3-a705-54c1042b8e85';
const DE_LOC_ID  = 'd70c2e5e-682c-4b8b-9efe-4b10b891d8d7';
const SS_DIR     = '/home/raphael/Piggy/screenshots/appstore';

const SCREENSHOTS = {
  APP_IPHONE_69: ['6.9-1-home.png','6.9-2-quittungen.png','6.9-3-profil.png','6.9-4-projekte.png'],
  APP_IPHONE_67: ['6.7-1-home.png','6.7-2-quittungen.png','6.7-3-profil.png','6.7-4-projekte.png'],
  APP_IPHONE_61: ['6.1-1-home.png','6.1-2-quittungen.png','6.1-3-profil.png','6.1-4-projekte.png'],
};

// ── JWT ───────────────────────────────────────────────────────────────────────
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

// ── HTTP helpers ──────────────────────────────────────────────────────────────
function request(method, urlStr, body, extraHeaders = {}) {
  return new Promise((resolve, reject) => {
    const u = new URL(urlStr);
    const isHttps = u.protocol === 'https:';
    const lib = isHttps ? https : http;
    const token = generateJWT();
    const headers = {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
      ...extraHeaders,
    };
    const bodyBuf = body ? Buffer.from(JSON.stringify(body)) : null;
    if (bodyBuf) headers['Content-Length'] = bodyBuf.length;
    const opts = {
      hostname: u.hostname,
      port: u.port || (isHttps ? 443 : 80),
      path: u.pathname + u.search,
      method,
      headers,
    };
    const req = lib.request(opts, res => {
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

function asc(method, path, body) {
  return request(method, `https://api.appstoreconnect.apple.com${path}`, body);
}

// Upload via PUT (no auth header, raw bytes)
function putFile(urlStr, filePath, contentType) {
  return new Promise((resolve, reject) => {
    const u = new URL(urlStr);
    const data = fs.readFileSync(filePath);
    const opts = {
      hostname: u.hostname,
      port: u.port || 443,
      path: u.pathname + u.search,
      method: 'PUT',
      headers: {
        'Content-Type': contentType,
        'Content-Length': data.length,
      },
    };
    const lib = u.protocol === 'https:' ? https : http;
    const req = lib.request(opts, res => {
      const chunks = [];
      res.on('data', c => chunks.push(c));
      res.on('end', () => resolve({ status: res.statusCode, body: Buffer.concat(chunks).toString() }));
    });
    req.on('error', reject);
    req.write(data);
    req.end();
  });
}

// ── Logging ───────────────────────────────────────────────────────────────────
function log(label, status, body) {
  const ok = status >= 200 && status < 300;
  console.log(`\n[${ok ? 'OK' : 'ERR'}] ${label} → HTTP ${status}`);
  if (!ok) console.log('  Body:', JSON.stringify(body, null, 2).slice(0, 800));
}

// ── Main ──────────────────────────────────────────────────────────────────────
async function main() {
  console.log('=== ASC Upload Script ===\n');

  // ── 1. PATCH DE Localization ──────────────────────────────────────────────
  console.log('\n── Step 1: PATCH DE Localization ──');
  const deBody = {
    data: {
      type: 'appStoreVersionLocalizations',
      id: DE_LOC_ID,
      attributes: {
        name: 'Piggy',
        subtitle: 'Quittungen scannen & Ausgaben tracken',
        description: 'Piggy macht Schluss mit dem Papierberg.\n\nScanne deine Quittungen in Sekunden – Piggy erkennt Betrag, Händler und Datum automatisch via OCR. Alle Ausgaben auf einen Blick, jederzeit abrufbar.\n\n• Quittungen fotografieren und automatisch erfassen\n• Übersicht über Ausgaben nach Händler und Kategorie\n• Projekte anlegen und Kosten zuordnen\n• Profil mit QR-Code zum einfachen Teilen\n• Sicher mit Apple ID einloggen\n• Deine Daten gehören dir – kein Verkauf an Dritte\n\nPerfekt für den Alltag in der Schweiz: CHF, lokale Händler, Swiss German ready.',
        keywords: 'ausgaben,quittungen,budget,finanzen,scanner,haushalt,belege,kosten',
        marketingUrl: null,
        promotionalText: 'Neu: Automatische Texterkennung direkt beim Fotografieren',
        supportUrl: 'https://piggy-38h.pages.dev',
      },
    },
  };
  const r1 = await asc('PATCH', `/v1/appStoreVersionLocalizations/${DE_LOC_ID}`, deBody);
  log('PATCH DE Localization', r1.status, r1.body);

  // ── 2. EN Localization — check then create/update ──────────────────────────
  console.log('\n── Step 2: EN Localization ──');
  const r2 = await asc('GET', `/v1/appStoreVersions/${VERSION_ID}/appStoreVersionLocalizations`);
  log('GET Localizations', r2.status, r2.body);

  let enLocId = null;
  if (r2.status === 200 && r2.body && r2.body.data) {
    const en = r2.body.data.find(l => l.attributes && l.attributes.locale === 'en-US');
    if (en) {
      enLocId = en.id;
      console.log(`  → en-US exists: ${enLocId}`);
    } else {
      console.log('  → en-US not found, will POST');
    }
  }

  const enAttrs = {
    name: 'Piggy',
    subtitle: 'Scan receipts & track expenses',
    description: 'Piggy puts your spending on autopilot.\n\nSnap a photo of any receipt and Piggy instantly reads the amount, merchant, and date using built-in OCR. No more manual entry, no more guessing where the money went.\n\n• Scan receipts with your camera in seconds\n• Automatic text recognition – amount, merchant, date\n• Track spending by merchant and category\n• Organize expenses into projects\n• Share your profile via QR code\n• Sign in securely with Apple ID\n• Your data stays yours – never sold\n\nBuilt for everyday Swiss life: CHF, local merchants, clean Swiss design.',
    keywords: 'expenses,receipts,budget,finance,scanner,spending,tracker,bills',
    marketingUrl: null,
    promotionalText: 'New: Automatic text recognition when you take a photo',
    supportUrl: 'https://piggy-38h.pages.dev',
  };

  if (enLocId) {
    // PATCH existing
    const r2p = await asc('PATCH', `/v1/appStoreVersionLocalizations/${enLocId}`, {
      data: { type: 'appStoreVersionLocalizations', id: enLocId, attributes: enAttrs },
    });
    log('PATCH EN Localization', r2p.status, r2p.body);
  } else {
    // POST new
    const r2c = await asc('POST', '/v1/appStoreVersionLocalizations', {
      data: {
        type: 'appStoreVersionLocalizations',
        attributes: { locale: 'en-US', ...enAttrs },
        relationships: {
          appStoreVersion: { data: { type: 'appStoreVersions', id: VERSION_ID } },
        },
      },
    });
    log('POST EN Localization', r2c.status, r2c.body);
    if (r2c.status >= 200 && r2c.status < 300 && r2c.body && r2c.body.data) {
      enLocId = r2c.body.data.id;
      console.log(`  → Created en-US: ${enLocId}`);
    }
  }

  // ── 3. Age Rating ─────────────────────────────────────────────────────────
  console.log('\n── Step 3: Age Rating ──');
  const r3 = await asc('GET', `/v1/appStoreVersions/${VERSION_ID}/ageRatingDeclaration`);
  log('GET AgeRating', r3.status, r3.body);

  if (r3.status === 200 && r3.body && r3.body.data) {
    const ageId = r3.body.data.id;
    const current = r3.body.data.attributes || {};
    console.log(`  → AgeRating ID: ${ageId}`);
    console.log('  → Current attrs:', JSON.stringify(current));

    // Patch with safe values
    const r3p = await asc('PATCH', `/v1/ageRatingDeclarations/${ageId}`, {
      data: {
        type: 'ageRatingDeclarations',
        id: ageId,
        attributes: {
          alcoholTobaccoOrDrugUseOrReferences: 'NONE',
          gamblingSimulated: 'NONE',
          medicalOrTreatmentInformation: 'NONE',
          profanityOrCrudeHumor: 'NONE',
          sexualContentGraphicAndNudity: 'NONE',
          sexualContentOrNudity: 'NONE',
          violenceCartoonOrFantasy: 'NONE',
          violenceRealisticProlonged: 'NONE',
          violenceRealistic: 'NONE',
          contests: false,
          gambling: false,
          kidsAgeBand: null,
          matureOrSuggestiveThemes: 'NONE',
          unrestrictedWebAccess: false,
          horrorOrFearThemes: 'NONE',
        },
      },
    });
    log('PATCH AgeRating', r3p.status, r3p.body);
  }

  // ── 4. Screenshots ────────────────────────────────────────────────────────
  // We'll upload screenshots for both DE and EN localization IDs we have
  const locIds = { 'de-DE': DE_LOC_ID };
  if (enLocId) locIds['en-US'] = enLocId;

  for (const [locale, locId] of Object.entries(locIds)) {
    console.log(`\n── Step 4: Screenshots for ${locale} (${locId}) ──`);

    // Get existing screenshot sets
    const r4 = await asc('GET', `/v1/appStoreVersionLocalizations/${locId}/appScreenshotSets`);
    log(`GET ScreenshotSets (${locale})`, r4.status, r4.body);
    const existingSets = (r4.status === 200 && r4.body && r4.body.data) ? r4.body.data : [];
    const existingByType = {};
    for (const s of existingSets) {
      existingByType[s.attributes.screenshotDisplayType] = s.id;
    }
    console.log('  → Existing sets:', Object.keys(existingByType).join(', ') || 'none');

    for (const [displayType, files] of Object.entries(SCREENSHOTS)) {
      console.log(`\n  [${displayType}]`);

      // Get or create the set
      let setId = existingByType[displayType];
      if (!setId) {
        const r4c = await asc('POST', '/v1/appScreenshotSets', {
          data: {
            type: 'appScreenshotSets',
            attributes: { screenshotDisplayType: displayType },
            relationships: {
              appStoreVersionLocalization: { data: { type: 'appStoreVersionLocalizations', id: locId } },
            },
          },
        });
        log(`POST ScreenshotSet ${displayType}`, r4c.status, r4c.body);
        if (r4c.status >= 200 && r4c.status < 300 && r4c.body && r4c.body.data) {
          setId = r4c.body.data.id;
          console.log(`    → Created set: ${setId}`);
        } else {
          console.log(`    → Failed to create set, skipping`);
          continue;
        }
      } else {
        console.log(`    → Reusing existing set: ${setId}`);
      }

      // Upload each file
      for (let i = 0; i < files.length; i++) {
        const filename = files[i];
        const filePath = path.join(SS_DIR, filename);
        if (!fs.existsSync(filePath)) {
          console.log(`    → SKIP (not found): ${filename}`);
          continue;
        }
        const fileSize = fs.statSync(filePath).size;
        console.log(`    → Uploading ${filename} (${fileSize} bytes) ...`);

        // Step 4a: POST appScreenshots — reserve slot
        const r4s = await asc('POST', '/v1/appScreenshots', {
          data: {
            type: 'appScreenshots',
            attributes: {
              fileSize,
              fileName: filename,
            },
            relationships: {
              appScreenshotSet: { data: { type: 'appScreenshotSets', id: setId } },
            },
          },
        });
        log(`  POST appScreenshot ${filename}`, r4s.status, r4s.body);
        if (r4s.status < 200 || r4s.status >= 300) continue;

        const ssId = r4s.body.data.id;
        const ops  = r4s.body.data.attributes.uploadOperations || [];
        console.log(`      → Screenshot ID: ${ssId}, ops: ${ops.length}`);

        // Step 4b: PUT file bytes to each upload operation
        let putOk = true;
        for (const op of ops) {
          const { url, method, requestHeaders, length, offset } = op;
          // Read the slice
          const buf = Buffer.alloc(length);
          const fd  = fs.openSync(filePath, 'r');
          fs.readSync(fd, buf, 0, length, offset);
          fs.closeSync(fd);

          // Build headers from op
          const hdrs = {};
          if (requestHeaders) for (const h of requestHeaders) hdrs[h.name] = h.value;

          await new Promise((resolve, reject) => {
            const u = new URL(url);
            const lib = u.protocol === 'https:' ? https : http;
            const reqOpts = {
              hostname: u.hostname,
              port: u.port || 443,
              path: u.pathname + u.search,
              method: method || 'PUT',
              headers: { ...hdrs, 'Content-Length': buf.length },
            };
            const req = lib.request(reqOpts, res => {
              const chunks = [];
              res.on('data', c => chunks.push(c));
              res.on('end', () => {
                const body = Buffer.concat(chunks).toString();
                const ok = res.statusCode >= 200 && res.statusCode < 300;
                console.log(`      → PUT op: HTTP ${res.statusCode}${ok ? '' : ' BODY: ' + body.slice(0,200)}`);
                if (!ok) putOk = false;
                resolve();
              });
            });
            req.on('error', e => { console.log(`      → PUT error: ${e.message}`); putOk = false; resolve(); });
            req.write(buf);
            req.end();
          });
        }

        // Step 4c: PATCH uploaded=true
        if (putOk) {
          const r4u = await asc('PATCH', `/v1/appScreenshots/${ssId}`, {
            data: { type: 'appScreenshots', id: ssId, attributes: { uploaded: true } },
          });
          log(`  PATCH uploaded ${filename}`, r4u.status, r4u.body);
        } else {
          console.log(`      → Skipping PATCH uploaded (PUT had errors)`);
        }
      }
    }
  }

  // ── 5. Pricing check ────────────────────────────────────────────────────────
  console.log('\n── Step 5: Pricing check ──');
  const r5 = await asc('GET', `/v1/apps/${APP_ID}/appPriceSchedule`);
  log('GET AppPriceSchedule', r5.status, r5.body);

  console.log('\n=== Done ===');
}

main().catch(e => { console.error('Fatal:', e); process.exit(1); });
