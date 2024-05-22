import { registerPlugin } from '@capacitor/core';

import type { ReferralPlugin } from './definitions';

const Referral = registerPlugin<ReferralPlugin>('Referral', {
  web: () => import('./web').then(m => new m.ReferralWeb()),
});

export * from './definitions';
export { Referral };
