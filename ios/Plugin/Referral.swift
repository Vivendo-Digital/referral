import Foundation

@objc public class Referral: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
