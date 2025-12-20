import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinKt.startKoinIos()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}