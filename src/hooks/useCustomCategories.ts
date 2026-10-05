import { useState, useEffect } from 'react';
import { supabase } from '../lib/supabase';
import { isKnownCategory } from '../lib/categories';

const ROW_LIMIT = 2000;

/** Eigene Kategorien des Users: distinct tags[0] aus receipt_items, die nicht zum festen Satz gehören. */
export function useCustomCategories(): string[] {
  const [categories, setCategories] = useState<string[]>([]);

  useEffect(() => {
    let cancelled = false;
    supabase.from('receipt_items').select('tags').limit(ROW_LIMIT).then(({ data }) => {
      if (cancelled || !data) return;
      const found = new Set<string>();
      for (const row of data as { tags: string[] | null }[]) {
        const first = row.tags?.[0]?.trim();
        if (first && !isKnownCategory(first)) found.add(first);
      }
      setCategories([...found].sort((a, b) => a.localeCompare(b)));
    });
    return () => { cancelled = true; };
  }, []);

  return categories;
}
