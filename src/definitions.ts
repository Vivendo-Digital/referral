export interface ReferralPlugin {
  getInstallReferrer(options: { value: string }): Promise<{ value: string }>;
  handleOpenUrl(options: { url: string }): Promise<{ url: string }>;
}
