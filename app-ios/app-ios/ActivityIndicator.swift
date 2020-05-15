//
//  ActivityIndicator.swift
//  app-ios
//
//  Created by Arkadii Ivanov on 27/03/2020.
//  Copyright © 2020 Badoo. All rights reserved.
//

import UIKit
import SwiftUI

struct ActivityIndicator: UIViewRepresentable {

    var isAnimating: Bool
    let style: UIActivityIndicatorView.Style

    func makeUIView(context: UIViewRepresentableContext<ActivityIndicator>) -> UIActivityIndicatorView {
        return UIActivityIndicatorView(style: style)
    }

    func updateUIView(_ uiView: UIActivityIndicatorView, context: UIViewRepresentableContext<ActivityIndicator>) {
        isAnimating ? uiView.startAnimating() : uiView.stopAnimating()
    }
}
