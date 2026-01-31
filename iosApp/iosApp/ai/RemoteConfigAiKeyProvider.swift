import Foundation
import ComposeApp
import FirebaseRemoteConfig

final class RemoteConfigAiKeyProvider: NSObject, AiKeyProvider {
    func getGeminiApiKey(completionHandler: @escaping (String?, Error?) -> Void) {
        let remoteConfig = RemoteConfig.remoteConfig()
        let settings = RemoteConfigSettings()
        settings.minimumFetchInterval = 0
        remoteConfig.configSettings = settings

        remoteConfig.fetchAndActivate { _, error in
            if let error = error {
                completionHandler(nil, error)
                return
            }
            let value = remoteConfig["gemini_api_key"].stringValue ?? ""
            completionHandler(value, nil)
        }
    }
}
