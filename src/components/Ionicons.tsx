import Svg, { Path, Circle, G } from 'react-native-svg';

type IoniconName =
  | 'add-circle-outline' | 'alert-circle' | 'alert-circle-outline'
  | 'bar-chart' | 'bar-chart-outline'
  | 'checkmark' | 'checkmark-circle'
  | 'chevron-back' | 'chevron-forward'
  | 'close' | 'close-circle' | 'close-circle-outline'
  | 'document-attach-outline' | 'filter'
  | 'grid' | 'grid-outline'
  | 'link-outline' | 'logo-google' | 'log-out-outline'
  | 'pencil-outline' | 'person-circle' | 'person-circle-outline' | 'person-outline'
  | 'receipt' | 'receipt-outline'
  | 'search-outline' | 'shield-checkmark-outline' | 'sparkles-outline'
  | 'swap-vertical' | 'trash-outline';

type Props = {
  name: IoniconName | string;
  size?: number;
  color?: string;
  style?: object;
};

export function Ionicons({ name, size = 24, color = '#000', style }: Props) {
  const s = size;
  const stroke = color;
  const sw = 1.8;
  const sl = 'round' as const;

  const outlineProps = {
    stroke,
    strokeWidth: sw,
    strokeLinecap: sl,
    strokeLinejoin: sl,
    fill: 'none',
  } as const;

  const renderIcon = () => {
    switch (name) {
      case 'chevron-forward':
        return <Path {...outlineProps} d="M9 6l6 6-6 6" />;
      case 'chevron-back':
        return <Path {...outlineProps} d="M15 6l-6 6 6 6" />;
      case 'close':
        return <Path {...outlineProps} d="M6 6l12 12M18 6L6 18" />;
      case 'close-circle':
        return (
          <G>
            <Circle cx="12" cy="12" r="9" fill={color} />
            <Path stroke="white" strokeWidth={sw} strokeLinecap={sl} d="M9 9l6 6M15 9l-6 6" />
          </G>
        );
      case 'close-circle-outline':
        return (
          <G>
            <Circle cx="12" cy="12" r="9" {...outlineProps} />
            <Path {...outlineProps} d="M9 9l6 6M15 9l-6 6" />
          </G>
        );
      case 'checkmark':
        return <Path {...outlineProps} d="M5 13l4 4L19 7" />;
      case 'checkmark-circle':
        return (
          <G>
            <Circle cx="12" cy="12" r="9" fill={color} />
            <Path stroke="white" strokeWidth={sw} strokeLinecap={sl} strokeLinejoin={sl} fill="none" d="M8 12l3 3 5-5" />
          </G>
        );
      case 'add-circle-outline':
        return (
          <G>
            <Circle cx="12" cy="12" r="9" {...outlineProps} />
            <Path {...outlineProps} d="M12 8v8M8 12h8" />
          </G>
        );
      case 'alert-circle':
        return (
          <G>
            <Circle cx="12" cy="12" r="9" fill={color} />
            <Path stroke="white" strokeWidth={sw} strokeLinecap={sl} fill="none" d="M12 8v4M12 16h.01" />
          </G>
        );
      case 'alert-circle-outline':
        return (
          <G>
            <Circle cx="12" cy="12" r="9" {...outlineProps} />
            <Path {...outlineProps} d="M12 8v4M12 16h.01" />
          </G>
        );
      case 'search-outline':
        return (
          <G>
            <Circle cx="11" cy="11" r="7" {...outlineProps} />
            <Path {...outlineProps} d="M16.5 16.5l3.5 3.5" />
          </G>
        );
      case 'trash-outline':
        return (
          <G>
            <Path {...outlineProps} d="M4 7h16M10 11v6M14 11v6M5 7l1 12a2 2 0 002 2h8a2 2 0 002-2l1-12M9 7V4a1 1 0 011-1h4a1 1 0 011 1v3" />
          </G>
        );
      case 'pencil-outline':
        return <Path {...outlineProps} d="M15.232 5.232l3.536 3.536M9 13l-4 4 4-1 8.586-8.586a2 2 0 00-2.828-2.828L6 15" />;
      case 'person-outline':
        return (
          <G>
            <Path {...outlineProps} d="M12 11a4 4 0 100-8 4 4 0 000 8z" />
            <Path {...outlineProps} d="M5 20a7 7 0 0114 0" />
          </G>
        );
      case 'person-circle':
        return (
          <G>
            <Circle cx="12" cy="12" r="10" fill={color} />
            <Circle cx="12" cy="10" r="3" fill="white" />
            <Path stroke="none" fill="white" d="M6 20a6 6 0 0112 0" />
          </G>
        );
      case 'person-circle-outline':
        return (
          <G>
            <Circle cx="12" cy="12" r="10" {...outlineProps} />
            <Circle cx="12" cy="10" r="3" {...outlineProps} />
            <Path {...outlineProps} d="M6 20a6 6 0 0112 0" />
          </G>
        );
      case 'log-out-outline':
        return (
          <G>
            <Path {...outlineProps} d="M15 3h4a2 2 0 012 2v14a2 2 0 01-2 2h-4M10 17l5-5-5-5M15 12H3" />
          </G>
        );
      case 'filter':
        return <Path {...outlineProps} d="M3 4h18M7 9h10M10 14h4M11 19h2" />;
      case 'swap-vertical':
        return (
          <G>
            <Path {...outlineProps} d="M7 16V4M7 4L4 7M7 4l3 3" />
            <Path {...outlineProps} d="M17 8v12m0 0l3-3m-3 3l-3-3" />
          </G>
        );
      case 'bar-chart':
        return (
          <G>
            <Path fill={color} stroke="none" d="M4 10h4v10H4zM10 6h4v14h-4zM16 3h4v17h-4z" />
          </G>
        );
      case 'bar-chart-outline':
        return (
          <G>
            <Path {...outlineProps} d="M4 10h4v10H4zM10 6h4v14h-4zM16 3h4v17h-4z" />
          </G>
        );
      case 'grid':
        return (
          <G fill={color} stroke="none">
            <Path d="M4 4h6v6H4zM14 4h6v6h-6zM4 14h6v6H4zM14 14h6v6h-6z" />
          </G>
        );
      case 'grid-outline':
        return (
          <G>
            <Path {...outlineProps} d="M4 4h6v6H4zM14 4h6v6h-6zM4 14h6v6H4zM14 14h6v6h-6z" />
          </G>
        );
      case 'receipt':
      case 'receipt-outline':
        return (
          <G>
            <Path
              {...(name === 'receipt' ? { fill: color, stroke: 'none' } : outlineProps)}
              d="M6 2h12a1 1 0 011 1v18l-3-2-2 2-2-2-2 2-2-2-3 2V3a1 1 0 011-1z"
            />
            {name === 'receipt-outline' && (
              <G>
                <Path {...outlineProps} d="M9 8h6M9 12h6M9 16h3" />
              </G>
            )}
            {name === 'receipt' && (
              <G>
                <Path stroke="white" strokeWidth={sw} strokeLinecap={sl} fill="none" d="M9 8h6M9 12h6M9 16h3" />
              </G>
            )}
          </G>
        );
      case 'link-outline':
        return (
          <G>
            <Path {...outlineProps} d="M10 13a5 5 0 007.54.54l3-3a5 5 0 00-7.07-7.07l-1.72 1.71" />
            <Path {...outlineProps} d="M14 11a5 5 0 00-7.54-.54l-3 3a5 5 0 007.07 7.07l1.71-1.71" />
          </G>
        );
      case 'document-attach-outline':
        return (
          <G>
            <Path {...outlineProps} d="M14 2H6a2 2 0 00-2 2v16a2 2 0 002 2h12a2 2 0 002-2V8z" />
            <Path {...outlineProps} d="M14 2v6h6M12 18v-6M9 15h6" />
          </G>
        );
      case 'shield-checkmark-outline':
        return (
          <G>
            <Path {...outlineProps} d="M12 3L4 7v5c0 4.5 3.3 8.7 8 10 4.7-1.3 8-5.5 8-10V7l-8-4z" />
            <Path {...outlineProps} d="M9 12l2 2 4-4" />
          </G>
        );
      case 'sparkles-outline':
        return (
          <G>
            <Path {...outlineProps} d="M12 3v2M12 19v2M3 12H1M23 12h-2M4.22 4.22l1.42 1.42M18.36 18.36l1.42 1.42M4.22 19.78l1.42-1.42M18.36 5.64l1.42-1.42" />
            <Path {...outlineProps} d="M12 8a4 4 0 100 8 4 4 0 000-8z" />
          </G>
        );
      case 'logo-google':
        return (
          <G>
            <Path
              fill={color}
              stroke="none"
              d="M21.8 12.2c0-.7-.1-1.3-.2-2H12v3.8h5.5a4.7 4.7 0 01-2 3.1v2.5h3.3c1.9-1.8 3-4.4 3-7.4z"
            />
            <Path
              fill={color}
              stroke="none"
              d="M12 22c2.7 0 5-.9 6.7-2.4l-3.3-2.5c-.9.6-2.1 1-3.4 1-2.6 0-4.8-1.8-5.6-4.1H3v2.6C4.8 19.9 8.2 22 12 22z"
            />
            <Path
              fill={color}
              stroke="none"
              d="M6.4 14c-.2-.6-.3-1.3-.3-2s.1-1.4.3-2V7.4H3A9.9 9.9 0 002 12c0 1.6.4 3.2 1 4.6L6.4 14z"
            />
            <Path
              fill={color}
              stroke="none"
              d="M12 5.9c1.5 0 2.8.5 3.8 1.5l2.9-2.9C16.9 2.9 14.7 2 12 2 8.2 2 4.8 4.1 3 7.4L6.4 10c.8-2.3 3-4.1 5.6-4.1z"
            />
          </G>
        );
      default:
        return <Circle cx="12" cy="12" r="3" fill={color} />;
    }
  };

  return (
    <Svg width={s} height={s} viewBox="0 0 24 24" style={style as any}>
      {renderIcon()}
    </Svg>
  );
}
