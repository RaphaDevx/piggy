module.exports = {
  recognizeText: jest.fn().mockResolvedValue({ success: true, fullText: '' }),
  detectText: jest.fn().mockResolvedValue({ success: true, fullText: '' }),
  isAvailable: jest.fn().mockResolvedValue(true),
  getSupportedLanguages: jest.fn().mockResolvedValue(['de', 'en']),
  default: { recognizeText: jest.fn(), detectText: jest.fn() },
};
