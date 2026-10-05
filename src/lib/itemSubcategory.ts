import { categoryOfSubcategory, classifyItemName } from './taxonomy';

/**
 * Unterkategorie, die zur gewählten Hauptkategorie passt: die vorhandene, falls sie dazu gehört,
 * sonst die Regel-Einordnung des Artikelnamens (nur bei gleicher Hauptkategorie), sonst null.
 */
export function resolveSubcategory(name: string, category: string, subcategory?: string | null): string | null {
  if (subcategory && categoryOfSubcategory(subcategory) === category) return subcategory;
  const guess = classifyItemName(name);
  return guess && guess.category === category ? guess.subcategory : null;
}
