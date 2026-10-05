import { supabase } from './supabase';

const PAGE_SIZE = 1000; // PostgREST liefert höchstens 1000 Zeilen pro Request

/** Lädt alle Zeilen seitenweise, damit lange Auszugs-Historien nicht abgeschnitten werden. */
export async function fetchAll<T>(table: string, columns: string): Promise<T[]> {
  const rows: T[] = [];
  for (let from = 0; ; from += PAGE_SIZE) {
    const { data, error } = await supabase.from(table).select(columns).range(from, from + PAGE_SIZE - 1);
    if (error || !data) return rows;
    rows.push(...(data as T[]));
    if (data.length < PAGE_SIZE) return rows;
  }
}
