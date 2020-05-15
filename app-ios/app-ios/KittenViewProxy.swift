//
//  KittenViewProxy.swift
//  app-ios
//
//  Created by Arkadii Ivanov on 27/03/2020.
//  Copyright © 2020 Badoo. All rights reserved.
//

import Foundation
import Kittens

class KittenViewProxy : AbstractMviView<KittenViewModel, KittenViewEvent>, KittenView, ObservableObject {
    @Published var model: KittenViewModel?
    
    override func render(model: KittenViewModel) {
        self.model = model
    }
}
