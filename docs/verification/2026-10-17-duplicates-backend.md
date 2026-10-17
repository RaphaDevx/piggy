# Duplicate review backend rollout — 2026-10-17

Production project: `dsucrlaonnmpwopgidcj`. Migration `20261017114452_receipt_duplicate_review.sql` applied after the TestFlight update request authorized release preparation.

## Validation before rollout

- Read-only catalog inspection retrieved actual production column types, defaults, nullability, numeric precision, identity columns, constraints, receipt-dependent foreign keys and triggers.
- Reconstructed these table definitions in disposable PGlite/PostgreSQL, with synthetic identities only in that local engine. The migration passed all 18 existing integration scenarios against that realistic schema, including bank-link preservation with a better image, split participant settlements and exclusions, source manual classifications, conflicting completed suggestions, safe deletion and atomic new scans.
- Every production receipt foreign key is handled by the merge implementation. There are no user-defined triggers on receipts/items that add hidden mutation behavior.
- Production contained no existing duplicate-review tables or RPCs before deployment.
- Security-advisor baseline captured before rollout.

## Rollout

Used the documented Supabase Management API `POST /v1/projects/{ref}/database/query` with an explicit transaction, advisory migration lock, 5-second lock timeout and 30-second statement timeout. The unchanged migration SQL and its original repository version were inserted into `supabase_migrations.schema_migrations` in the same transaction. No additional user records were inserted, updated, deleted or merged. PostgREST was notified to reload its schema.

SQL SHA-256: `967a6a75ed16c56fa39990fc8b1ec561f800f638f2b65d99681e8a4d38d1aaa7`.

## Production verification

- Before/after counts: **278 receipts, 1180 receipt items, zero bank receipt matches**, unchanged.
- Migration history contains the correct version and name; all five public RPCs and private implementations exist.
- Public wrappers are `SECURITY INVOKER`; private mutation implementations are `SECURITY DEFINER`, with fixed empty search paths and authenticated-only execution. Anonymous execution is revoked.
- Both new tables have RLS. Decision reads are restricted to their owner. Authenticated clients cannot read the private audit table. Both new tables remain empty after rollout.
- Live REST requests to all five RPCs using the public anonymous client were rejected with HTTP 401 / PostgreSQL `42501`, verifying API route registration and anonymous-denial behavior without creating fixture records.
- Security advisors introduced **no new WARN/ERROR**. One new INFO reports that the private archive has RLS enabled without a policy. This is intentional deny-all client access, also enforced by revoked grants. Existing unrelated advisor warnings were unchanged.

## Remaining practical limits

No production user records were exercised through mutation RPCs. Local PostgreSQL tests validate the actual production table shape; real simultaneous worker/merge operations have not been driven as a concurrency load test. The functions use ordered receipt/child row locks, preserve foreign-key identities for splits, and fail transactionally on conflicts. Full TestFlight device interaction remains part of release validation.
