//
//  SceneDelegate.swift
//  app-ios
//
//  Created by Arkadii Ivanov on 27/03/2020.
//  Copyright © 2020 Badoo. All rights reserved.
//

import UIKit
import SwiftUI
import Kittens

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        if let windowScene = scene as? UIWindowScene {
            let window = UIWindow(windowScene: windowScene)
            window.rootViewController = UIHostingController(rootView: Kittens())
            self.window = window
            window.makeKeyAndVisible()
        }
    }
}
