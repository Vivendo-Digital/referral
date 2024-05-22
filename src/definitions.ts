export interface ReferralPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
