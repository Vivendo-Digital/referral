import { WebPlugin } from '@capacitor/core';

import type { ReferralPlugin } from './definitions';

export class ReferralWeb extends WebPlugin implements ReferralPlugin {
  async getInstallReferrer(options: { value: string; }): Promise<{ value: string; }> {
    console.log('GETINSTALLREFERRER', options);
    return options;
  }
  async handleOpenUrl(options: { url: string; }): Promise<{ url: string; }> {
    console.log('HANDLEOPENURL', options);
    return options;
  }

}
