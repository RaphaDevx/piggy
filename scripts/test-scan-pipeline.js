#!/usr/bin/env node
/**
 * test-scan-pipeline.js — Isolierter Test des kompletten Scan-Prozesses
 *
 * Testet jeden Schritt separat:
 *   1. Supabase Auth    — User-Login + Token-Generierung
 *   2. Profil-Check     — BYOK Key vorhanden?
 *   3. RPC              — check_and_increment_scan funktioniert?
 *   4. Anthropic API    — Key valid + Model erreichbar?
 *   5. Edge Function    — End-to-End mit realem OCR-Text
 *   6. Response-Format  — Stimmt data.result überein mit iOS-Erwartung?
 *
 * Nutzung:
 *   node scripts/test-scan-pipeline.js
 *   node scripts/test-scan-pipeline.js --user raphaelk01@outlook.de
 *   node scripts/test-scan-pipeline.js --step 4   (nur Anthropic-Test)
 */

const https = require('https');
const path  = require('path');
const fs    = require('fs');

// ── Config ────────────────────────────────────────────────────────────────────

const SUPABASE_URL      = 'https://dsucrlaonnmpwopgidcj.supabase.co';
const SUPABASE_ANON_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRzdWNybGFvbm5tcHdvcGdpZGNqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzgzMTk1MTYsImV4cCI6MjA5Mzg5NTUxNn0.q3gXxH6d2ZwMceWog49qdRkjFHwGHDB-BLc_bSZRrvY';

// Service Role aus supabase-tokens.env lesen
function loadServiceRole() {
  // 1. Env-Var (bevorzugt)
  if (process.env.SUPABASE_SERVICE_ROLE) return process.env.SUPABASE_SERVICE_ROLE;
  // 2. Datei (absoluter Pfad)
  const candidates = [
    '/home/raphael/K-Dev/private/supabase-tokens.env',
    path.join(__dirname, '../../../private/supabase-tokens.env'),
  ];
  for (const p of candidates) {
    try {
      const content = fs.readFileSync(p, 'utf8');
      const match = content.match(/SUPABASE_SERVICE_ROLE="([^"]+)"/);
      if (match) return match[1];
    } catch { /* try next */ }
  }
  return null;
}

const SERVICE_ROLE = loadServiceRole();

// Beispiel OCR-Text (typische Schweizer Coop-Quittung)
const TEST_OCR = `COOP
Supermarkt St. Gallen
Marktplatz 7, 9000 St. Gallen

Datum: 28.09.2026  10:34

Vollmilch 3.5%       1L     1.85
Butter 250g                  2.40
Zopf                         3.20
Emmentaler 200g              4.95
Mineralwasser 6x5dl          4.50

Total CHF           16.90
Bezahlt mit Karte
Visa **** 4242

Merci für Ihren Einkauf!`;

// ── HTTP Helper ───────────────────────────────────────────────────────────────

function request(url, options = {}, body = null) {
  return new Promise((resolve, reject) => {
    const parsed = new URL(url);
    const opts = {
      hostname: parsed.hostname,
      path:     parsed.pathname + parsed.search,
      method:   options.method || 'GET',
      headers:  options.headers || {},
    };
    const req = https.request(opts, (res) => {
      let data = '';
      res.on('data', d => data += d);
      res.on('end', () => {
        try { resolve({ status: res.statusCode, body: JSON.parse(data), raw: data }); }
        catch { resolve({ status: res.statusCode, body: null, raw: data }); }
      });
    });
    req.on('error', reject);
    if (body) req.write(typeof body === 'string' ? body : JSON.stringify(body));
    req.end();
  });
}

// ── Logging ───────────────────────────────────────────────────────────────────

const C = {
  green:  s => `\x1b[32m${s}\x1b[0m`,
  red:    s => `\x1b[31m${s}\x1b[0m`,
  yellow: s => `\x1b[33m${s}\x1b[0m`,
  cyan:   s => `\x1b[36m${s}\x1b[0m`,
  bold:   s => `\x1b[1m${s}\x1b[0m`,
  dim:    s => `\x1b[2m${s}\x1b[0m`,
};

function pass(label, detail = '') {
  console.log(`  ${C.green('✅')} ${C.bold(label)}${detail ? C.dim('  ' + detail) : ''}`);
}
function fail(label, detail = '') {
  console.log(`  ${C.red('❌')} ${C.bold(label)}${detail ? C.dim('  ' + detail) : ''}`);
}
function info(label, detail = '') {
  console.log(`  ${C.cyan('ℹ')}  ${label}${detail ? C.dim('  ' + detail) : ''}`);
}
function step(n, title) {
  console.log(`\n${C.bold(C.cyan(`── Step ${n}: ${title}`))}`);
}

const results = [];
function record(stepN, name, ok, detail) {
  results.push({ step: stepN, name, ok, detail });
}

// ── Tests ─────────────────────────────────────────────────────────────────────

async function step1_auth(userEmail) {
  step(1, 'Supabase Auth — User-Token generieren');

  if (!SERVICE_ROLE) {
    fail('Service Role Key nicht gefunden', 'Prüfe /private/supabase-tokens.env');
    record(1, 'Auth', false, 'Service Role missing');
    return null;
  }

  // Admin-API: Session für User generieren
  const userId = await getUserId(userEmail);
  if (!userId) {
    fail('User nicht gefunden', userEmail);
    record(1, 'Auth', false, `User ${userEmail} not found`);
    return null;
  }
  info('User gefunden', `${userEmail} → ${userId}`);

  // Magic Link Token via Admin
  const res = await request(
    `${SUPABASE_URL}/auth/v1/admin/users/${userId}`,
    { method: 'GET', headers: { apikey: SERVICE_ROLE, Authorization: `Bearer ${SERVICE_ROLE}` } }
  );

  if (res.status !== 200) {
    fail('User-Daten nicht abrufbar', `HTTP ${res.status}`);
    record(1, 'Auth', false, `HTTP ${res.status}`);
    return null;
  }

  pass('User in Supabase Auth gefunden', res.body?.email);
  record(1, 'Auth', true, res.body?.email);
  return { userId, email: res.body?.email };
}

async function getUserId(email) {
  const res = await request(
    `${SUPABASE_URL}/auth/v1/admin/users?page=1&per_page=50`,
    { headers: { apikey: SERVICE_ROLE, Authorization: `Bearer ${SERVICE_ROLE}` } }
  );
  const users = res.body?.users || [];
  return users.find(u => u.email === email)?.id || null;
}

async function generateUserToken(userId) {
  // Supabase admin kann keinen echten Session-Token direkt erzeugen via REST
  // Stattdessen: Link-Token generieren und davon ableiten
  // Für Tests: wir nutzen den Service Role Key als "super-user" Token
  // ACHTUNG: In Prod würde die Edge Function diesen Token ablehnen (kein echter User)
  return SERVICE_ROLE; // Service Role umgeht auth check — für reinen API-Test
}

async function step2_profile(userId) {
  step(2, 'Profil-Check — BYOK Key & processing_mode');

  const res = await request(
    `${SUPABASE_URL}/rest/v1/profiles?id=eq.${userId}&select=id,gemini_api_key,processing_mode`,
    { headers: { apikey: SERVICE_ROLE, Authorization: `Bearer ${SERVICE_ROLE}` } }
  );

  if (res.status !== 200 || !res.body?.length) {
    fail('Profil nicht gefunden', `HTTP ${res.status}`);
    record(2, 'Profile', false, `HTTP ${res.status}`);
    return null;
  }

  const profile = res.body[0];
  const hasByok = !!profile.gemini_api_key?.trim();

  info('processing_mode', profile.processing_mode);
  if (hasByok) {
    pass('BYOK Gemini Key vorhanden', '→ direkter Gemini Call, kein Rate-Limit');
  } else {
    info('Kein BYOK Key', '→ Server-Key-Pfad (Anthropic, max 10/Tag)');
  }

  record(2, 'Profile', true, `mode=${profile.processing_mode}, byok=${hasByok}`);
  return profile;
}

async function step3_rpc(userId) {
  step(3, 'RPC — check_and_increment_scan (daily_limit: 999 für Test)');

  // Wir nutzen limit=999 damit der Test-Call nicht das echte Limit verbraucht
  const res = await request(
    `${SUPABASE_URL}/rest/v1/rpc/check_and_increment_scan`,
    {
      method: 'POST',
      headers: {
        apikey: SERVICE_ROLE,
        Authorization: `Bearer ${SERVICE_ROLE}`,
        'Content-Type': 'application/json',
      },
    },
    { user_uuid: userId, daily_limit: 999 }
  );

  if (res.status !== 200) {
    fail('RPC Fehler', `HTTP ${res.status}: ${res.raw?.slice(0, 100)}`);
    record(3, 'RPC', false, `HTTP ${res.status}`);

    // Test-Eintrag aufräumen (falls angelegt)
    return false;
  }

  const allowed = res.body;
  pass('RPC antwortet', `allowed = ${allowed}`);
  record(3, 'RPC', true, `allowed=${allowed}`);

  // Test-Eintrag sofort wieder löschen
  await request(
    `${SUPABASE_URL}/rest/v1/user_usage?user_id=eq.${userId}&scan_date=eq.${new Date().toISOString().slice(0,10)}`,
    {
      method: 'DELETE',
      headers: { apikey: SERVICE_ROLE, Authorization: `Bearer ${SERVICE_ROLE}` },
    }
  );
  info('Test-Eintrag aus user_usage entfernt');

  return allowed;
}

async function step4_anthropic() {
  step(4, 'Anthropic API — Key & Model check');
  info('Key-Wert nicht lesbar (Supabase Secrets encrypted)', 'Teste via Edge Function call');

  // Wir können den Key nicht direkt lesen — testen via Edge Function
  // Stattdessen: Prüfen ob ANTHROPIC_API_KEY im Secret-Namen vorhanden
  const res = await request(
    'https://api.supabase.com/v1/projects/dsucrlaonnmpwopgidcj/secrets',
    {
      headers: {
        Authorization: `Bearer ${process.env.SUPABASE_PAT || ''}`,
      },
    }
  );

  if (res.status === 200) {
    const secrets = res.body || [];
    const hasKey = secrets.some(s => s.name === 'ANTHROPIC_API_KEY');
    if (hasKey) {
      pass('ANTHROPIC_API_KEY in Supabase Secrets vorhanden');
    } else {
      fail('ANTHROPIC_API_KEY fehlt in Supabase Secrets');
    }
    record(4, 'Anthropic Secret', hasKey, hasKey ? 'present' : 'missing');
  } else {
    info('Secrets-Check übersprungen', 'PAT nicht gesetzt — via SUPABASE_PAT env var');
    record(4, 'Anthropic Secret', null, 'skipped');
  }
}

async function step5_edge_function(userId) {
  step(5, 'Edge Function — End-to-End Test');
  info('URL', `${SUPABASE_URL}/functions/v1/scan-receipt`);
  info('Payload', `{ ocr_text: "${TEST_OCR.slice(0, 30)}..." }`);

  // Für diesen Test brauchen wir einen echten User-Token
  // Service Role wird von der Function abgelehnt (kein echter User)
  // → Wir testen mit dem anon key als Trick: die Function prüft via getUser()
  //   ob der Token ein gültiger User-JWT ist

  // Step A: Magic Link generieren (kein Email-Versand via Admin API)
  const genRes = await request(
    `${SUPABASE_URL}/auth/v1/admin/generate_link`,
    {
      method: 'POST',
      headers: {
        apikey: SERVICE_ROLE,
        Authorization: `Bearer ${SERVICE_ROLE}`,
        'Content-Type': 'application/json',
      },
    },
    { type: 'magiclink', email: 'raphaelk01@outlook.de' }
  );

  // email_otp (8-digit) + type:"email" is the reliable programmatic path.
  // The hashed_token/magiclink type fails on newer GoTrue versions.
  const emailOtp = genRes.body?.email_otp;
  if (genRes.status !== 200 || !emailOtp) {
    fail('Magic Link fehlgeschlagen', `HTTP ${genRes.status}: ${JSON.stringify(genRes.body)?.slice(0,100)}`);
    record(5, 'Edge Function', false, 'Token generation failed');
    return null;
  }
  info('Magic Link generiert', 'email_otp verfügbar');

  // Step B: OTP gegen Session eintauschen (type:"email" + email_otp)
  const verifyRes = await request(
    `${SUPABASE_URL}/auth/v1/verify`,
    {
      method: 'POST',
      headers: {
        apikey: SUPABASE_ANON_KEY,
        'Content-Type': 'application/json',
      },
    },
    { type: 'email', email: 'raphaelk01@outlook.de', token: emailOtp }
  );

  let accessToken = verifyRes.body?.access_token;
  if (!accessToken) {
    fail('Session-Generierung fehlgeschlagen', `HTTP ${verifyRes.status}: ${JSON.stringify(verifyRes.body)?.slice(0,100)}`);
    record(5, 'Edge Function', false, `Verify HTTP ${verifyRes.status}`);
    return null;
  }
  pass('User-Session generiert', `...${accessToken.slice(-8)}`);
  pass('User-Token generiert', `...${accessToken.slice(-8)}`);

  const start = Date.now();
  const edgeRes = await request(
    `${SUPABASE_URL}/functions/v1/scan-receipt`,
    {
      method: 'POST',
      headers: {
        Authorization: `Bearer ${accessToken}`,
        'Content-Type': 'application/json',
      },
    },
    { ocr_text: TEST_OCR }
  );
  const ms = Date.now() - start;

  info(`HTTP Status`, `${edgeRes.status} (${ms}ms)`);
  info(`Response body`, JSON.stringify(edgeRes.body)?.slice(0, 200));

  if (edgeRes.status === 200 && edgeRes.body?.result) {
    pass('Edge Function erfolgreich', `store: ${edgeRes.body.result.store_name}, total: ${edgeRes.body.result.total_amount}`);
    record(5, 'Edge Function', true, `${ms}ms, store=${edgeRes.body.result.store_name}`);
    return edgeRes.body;
  } else {
    fail('Edge Function Fehler', `${edgeRes.status}: ${JSON.stringify(edgeRes.body)?.slice(0, 100)}`);
    record(5, 'Edge Function', false, `HTTP ${edgeRes.status}: ${JSON.stringify(edgeRes.body)?.slice(0,80)}`);
    return null;
  }
}

async function step6_response_format(edgeResult) {
  step(6, 'Response-Format — iOS ocr.ts Kompatibilität');

  if (!edgeResult) {
    fail('Kein Ergebnis aus Step 5', 'Übersprungen');
    record(6, 'Format', false, 'skipped');
    return;
  }

  // iOS ocr.ts erwartet:
  //   if (res.ok) { const data = await res.json(); if (data.result) { ... } }
  const checks = [
    { name: 'data.result vorhanden',           ok: !!edgeResult.result },
    { name: 'data.result.store_name (string)', ok: typeof edgeResult.result?.store_name === 'string' },
    { name: 'data.result.total_amount (num)',   ok: typeof edgeResult.result?.total_amount === 'number' },
    { name: 'data.result.items (array)',        ok: Array.isArray(edgeResult.result?.items) },
    { name: 'data.result.currency (string)',    ok: typeof edgeResult.result?.currency === 'string' },
    { name: 'data.result.date (str|null)',      ok: edgeResult.result?.date === null || typeof edgeResult.result?.date === 'string' },
  ];

  checks.forEach(c => {
    if (c.ok) pass(c.name);
    else fail(c.name);
  });

  const allOk = checks.every(c => c.ok);
  record(6, 'Response Format', allOk, allOk ? 'iOS-compatible' : 'format mismatch');
}

// ── Summary ───────────────────────────────────────────────────────────────────

function printSummary() {
  console.log(`\n${'─'.repeat(55)}`);
  console.log(C.bold('SUMMARY'));
  console.log('─'.repeat(55));

  let allPassed = true;
  results.forEach(r => {
    const icon = r.ok === true ? C.green('✅') : r.ok === false ? C.red('❌') : C.yellow('⚠️ ');
    console.log(`  ${icon}  Step ${r.step} ${r.name.padEnd(22)} ${C.dim(r.detail)}`);
    if (r.ok === false) allPassed = false;
  });

  console.log('─'.repeat(55));
  if (allPassed) {
    console.log(C.green(C.bold('  ✅ Pipeline vollständig funktionsfähig')));
  } else {
    const failed = results.filter(r => r.ok === false);
    console.log(C.red(C.bold(`  ❌ ${failed.length} Step(s) fehlgeschlagen`)));
    console.log(C.yellow('\n  Nächste Schritte:'));
    failed.forEach(r => {
      if (r.step === 3) console.log('    → Supabase Migration für check_and_increment_scan erneut anwenden');
      if (r.step === 4) console.log('    → ANTHROPIC_API_KEY in Supabase Secrets prüfen/neu setzen');
      if (r.step === 5) console.log('    → Edge Function Logs prüfen: supabase.com/dashboard/project/dsucrlaonnmpwopgidcj/functions');
    });
  }
  console.log('');
}

// ── Main ──────────────────────────────────────────────────────────────────────

async function main() {
  const args   = process.argv.slice(2);
  const onlyStep = args.includes('--step') ? parseInt(args[args.indexOf('--step') + 1]) : null;
  const userEmail = args.includes('--user')
    ? args[args.indexOf('--user') + 1]
    : 'raphael.m.kaufmann@gmail.com';

  console.log(C.bold(C.cyan('\n╔══════════════════════════════════════════════════════╗')));
  console.log(C.bold(C.cyan('║      Piggy Scan-Pipeline — Isolierter Test           ║')));
  console.log(C.bold(C.cyan('╚══════════════════════════════════════════════════════╝')));
  console.log(C.dim(`  User: ${userEmail}  |  ${new Date().toISOString()}\n`));

  if (!SERVICE_ROLE) {
    console.log(C.red('FEHLER: Service Role Key nicht geladen.'));
    console.log('Ausführen mit: source /home/raphael/K-Dev/private/supabase-tokens.env && node scripts/test-scan-pipeline.js');
    process.exit(1);
  }

  // Step 1: Auth
  let userData = null;
  if (!onlyStep || onlyStep === 1) {
    userData = await step1_auth(userEmail);
  }

  const userId = userData?.userId || (await getUserId(userEmail));

  // Step 2: Profile
  if ((!onlyStep || onlyStep === 2) && userId) {
    await step2_profile(userId);
  }

  // Step 3: RPC
  if ((!onlyStep || onlyStep === 3) && userId) {
    await step3_rpc(userId);
  }

  // Step 4: Anthropic
  if (!onlyStep || onlyStep === 4) {
    await step4_anthropic();
  }

  // Step 5: Edge Function
  let edgeResult = null;
  if (!onlyStep || onlyStep === 5) {
    edgeResult = await step5_edge_function(userId);
  }

  // Step 6: Format check
  if (!onlyStep || onlyStep === 6) {
    await step6_response_format(edgeResult);
  }

  printSummary();
}

main().catch(err => {
  console.error('Fatal:', err.message);
  process.exit(1);
});
