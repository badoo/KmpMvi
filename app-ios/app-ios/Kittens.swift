//
//  Kittens.swift
//  app-ios
//
//  Created by Arkadii Ivanov on 27/03/2020.
//  Copyright © 2020 Badoo. All rights reserved.
//

import SwiftUI
import Kittens

struct Kittens: View {
    @State private var holder: ComponentHolder?
    @State private var proxy = KittenViewProxy()
    
    var body: some View {
        KittenSwiftView(proxy: proxy)
            .onAppear(perform: onAppear)
            .onDisappear(perform: onDisappear)
    }

    private func onAppear() {
        if (self.holder == nil) {
            self.holder = ComponentHolder()
        }
        self.holder?.component.onViewCreated(view: self.proxy)
        self.holder?.component.onStart()
    }
    
    private func onDisappear() {
        self.holder?.component.onViewDestroyed()
        self.holder?.component.onStop()
    }
}

private class ComponentHolder {
    let component = KittenComponent()
    
    deinit {
        component.onDestroy()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        Kittens()
    }
}

