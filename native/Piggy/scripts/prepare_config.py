#!/usr/bin/env python3
"""Generate a git-ignored xcconfig containing only explicit public client values.
Never accepts service-role, vendor private API, signing, or webhook keys.
"""
import argparse, base64, json, os, re
from pathlib import Path
p = argparse.ArgumentParser()
p.add_argument('--env-file', type=Path)
a = p.parse_args()
values = {}
if a.env_file:
    for line in a.env_file.read_text().splitlines():
        match = re.match(r'^(?:export\s+)?([A-Z][A-Z0-9_]*)\s*=\s*(.*?)\s*$', line)
        if match:
            key, value = match.groups()
            values[key] = value.strip('\"\'')
values.update(os.environ)
allow = {
    'SUPABASE_URL': 'EXPO_PUBLIC_SUPABASE_URL',
    'SUPABASE_ANON_KEY': 'EXPO_PUBLIC_SUPABASE_ANON_KEY',
    'REVENUECAT_IOS_KEY': 'EXPO_PUBLIC_REVENUECAT_IOS_KEY',
    'PRIVACY_URL': 'EXPO_PUBLIC_PRIVACY_URL',
    'TERMS_URL': 'EXPO_PUBLIC_TERMS_URL',
    'SUPPORT_URL': 'EXPO_PUBLIC_SUPPORT_URL',
}
lines = ['// Generated public client configuration. Do not commit.']
written = []
for key, source in allow.items():
    value = values.get(source, '')
    if not value: continue
    if any(c in value for c in '\n\r$;{}') or '/*' in value or '*/' in value: raise SystemExit('Invalid configuration syntax: '+key)
    if key.endswith('_URL') and not re.fullmatch(r'https://[A-Za-z0-9.-]+(?:/[^\s]*)?', value): raise SystemExit('HTTPS URL required: '+key)
    if key == 'SUPABASE_ANON_KEY':
        if value.startswith('eyJ'):
            try:
                body=value.split('.')[1]; claims=json.loads(base64.urlsafe_b64decode(body+'='*(-len(body)%4)))
                assert claims.get('role') == 'anon'
            except Exception: raise SystemExit('Only a public anon JWT is allowed')
        elif not value.startswith('sb_publishable_'): raise SystemExit('Only a public publishable Supabase key is allowed')
    if key == 'REVENUECAT_IOS_KEY' and not value.startswith('appl_'): raise SystemExit('Only a public Apple RevenueCat SDK key is allowed')
    # Xcode treats // as a comment; an empty expansion preserves URL separators.
    value = value.replace('//', '/$(EMPTY)/')
    lines.append(key+' = '+value); written.append(key)
output = Path(__file__).resolve().parents[1] / 'Config/Local.xcconfig'
output.write_text('\n'.join(lines)+'\n'); output.chmod(0o600)
print('Prepared public configuration fields: '+', '.join(written)+'. Values omitted.')
