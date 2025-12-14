import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

struct ContentView: View {
    @State var rotationAngle = 0.0
    @State var isHomeRootScreen = false

    var body: some View {
        ZStack {
            if isHomeRootScreen {
                ComposeView()
            } else {
                Color.primaryColor // Fill background with the only color in xcassets
                Image("SplashIcon")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .rotationEffect(.degrees(rotationAngle))
                    .frame(width: 100)
                    .onAppear() {
                        withAnimation(.easeIn(duration: Double(SplashConstants.shared.ANIMATION_DURATION_SECONDS))) {
                            rotationAngle = 360.0
                        }

                        DispatchQueue.main.asyncAfter(deadline: .now() + Double(SplashConstants.shared.ANIMATION_DURATION_SECONDS), execute: {
                            isHomeRootScreen = true
                        })
                    }
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
            }
        }
        .ignoresSafeArea(.all)
    }
}
