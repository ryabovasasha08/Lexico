import UIKit
import FirebaseCore
import ComposeApp

final class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        FirebaseApp.configure()
        setupKoin()
        return true
    }

    private func setupKoin() {
        let provider = RemoteConfigAiKeyProvider()
        let aiModule = AiKeyProviderModuleFactoryKt.aiKeyProviderModule(provider: provider)
        KoinKt.startKoinIos(extraModules: [aiModule])
    }
}
