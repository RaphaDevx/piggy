module.exports = {
  supabase: {
    from: jest.fn().mockReturnThis(),
    storage: { from: jest.fn().mockReturnThis() },
    auth: { getSession: jest.fn().mockResolvedValue({ data: { session: null } }) },
  },
};
