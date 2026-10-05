import { supabase } from './supabase';

const PAGE_SIZE = 1000; // PostgREST liefert höchstens 1000 Zeilen pro Request

const selectRows = (table: string, columns: string) => supabase.from(table).select(columns);
type SelectQuery = ReturnType<typeof selectRows>;

/**
 * Lädt alle Zeilen seitenweise, damit lange Auszugs-Historien nicht abgeschnitten werden.
 * Mit `applyFilter` lassen sich Filter ergänzen — dann zusätzlich `.order('id')` setzen, damit die Seiten nicht überlappen.
 */
export async function fetchAll<T>(
  table: string,
  columns: string,
  applyFilter?: (query: SelectQuery) => SelectQuery,
): Promise<T[]> {
  const rows: T[] = [];
  for (let from = 0; ; from += PAGE_SIZE) {
    const base = selectRows(table, columns);
    const query = applyFilter ? applyFilter(base) : base;
    const { data, error } = await query.range(from, from + PAGE_SIZE - 1);
    if (error || !data) return rows;
    rows.push(...(data as T[]));
    if (data.length < PAGE_SIZE) return rows;
  }
}
