import Foundation
import Capacitor
import FirebaseAnalytics

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(ReferralPlugin)
public class ReferralPlugin: CAPPlugin {
    private let implementation = Referral()
    
    override public func load() {
            // Any setup code can go here
        }

    @objc func getInstallReferrer(_ call: CAPPluginCall) {
        // iOS does not have an equivalent to Play Install Referrer,
        // so we need to handle this differently.
        // We could fetch and log any relevant data here.
        call.reject("getInstallReferrer not supported on iOS")
    }
    
    @objc func handleOpenUrl(_ call: CAPPluginCall) {
        guard let url = call.getString("url") else {
            call.reject("Must provide a URL")
            return
        }
        logDeepLinkToFirebase(url: url)
        call.resolve([
            "url": url
        ])
    }

    private func logDeepLinkToFirebase(url: String) {
        Analytics.logEvent(AnalyticsEventCampaignDetails, parameters: [
            "source": url,
            "medium": "deeplink"
        ])
    }
    
}
