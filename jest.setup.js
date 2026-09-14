// Global mocks for native modules that can't run in Node
jest.mock('./src/lib/supabase', () => ({
  supabase: { from: jest.fn(), storage: { from: jest.fn() }, auth: { getSession: jest.fn() } },
}));

jest.mock('./src/lib/markdown', () => ({
  generateMarkdown: jest.fn().mockReturnValue('## Quittung\n'),
}));

jest.mock('./src/native/FoundationModels', () => ({
  FoundationModels: {
    isAvailable: jest.fn().mockResolvedValue(false),
    parseReceiptText: jest.fn().mockRejectedValue(new Error('stub')),
  },
}));

jest.mock('expo-image-manipulator', () => ({
  manipulateAsync: jest.fn().mockImplementation(async (uri) => ({ uri })),
  SaveFormat: { JPEG: 'jpeg' },
}));

jest.mock('expo-file-system/legacy', () => ({
  readAsStringAsync: jest.fn().mockResolvedValue(''),
}));
