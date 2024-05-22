import { WebPlugin } from '@capacitor/core';

import type { ReferralPlugin } from './definitions';

export class ReferralWeb extends WebPlugin implements ReferralPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
