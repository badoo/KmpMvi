//
//  AppDelegate.swift
//  app-ios
//
//  Created by Arkadii Ivanov on 27/03/2020.
//  Copyright © 2020 Badoo. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        UITableView.appearance().separatorStyle = .none

        return true
    }
}
