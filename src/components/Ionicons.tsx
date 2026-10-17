import ExpoIonicons from '@expo/vector-icons/Ionicons';
import type { ComponentProps } from 'react';

export type IoniconName = ComponentProps<typeof ExpoIonicons>['name'];
type Props = Omit<ComponentProps<typeof ExpoIonicons>, 'name'> & { name: string };

/** One complete, maintained symbol family for every screen, including dynamic names. */
export function Ionicons({ name, size = 24, color = '#1A1A1A', ...props }: Props) {
  const resolved = Object.prototype.hasOwnProperty.call(ExpoIonicons.glyphMap, name)
    ? name as IoniconName
    : 'help-circle-outline';
  return <ExpoIonicons name={resolved} size={size} color={color} accessible={false} {...props} />;
}
