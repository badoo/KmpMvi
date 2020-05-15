//
//  KittenSwiftView.swift
//  app-ios
//
//  Created by Arkadii Ivanov on 5/14/20.
//  Copyright © 2020 Badoo. All rights reserved.
//

import SwiftUI
import Kittens

struct KittenSwiftView: View {
    @ObservedObject var proxy: KittenViewProxy

    var body: some View {
        NavigationView {
            content
            .navigationBarTitle("Kittens KMP Sample")
            .navigationBarItems(
                leading: ActivityIndicator(isAnimating: self.proxy.model?.isLoading ?? false, style: .medium),
                trailing: Button("Refresh") {
                    self.proxy.dispatch(event: KittenViewEvent.RefreshTriggered())
                }
            )
        }
    }
    
    private var content: some View {
        let model: KittenViewModel! = self.proxy.model

        return Group {
            if (model == nil) {
                EmptyView()
            } else if (model.isError) {
                Text("Error loading kittens :-(")
            } else {
                List {
                    ForEach(model.imageUrls) { item in
                        RemoteImage(url: item)
                            .listRowInsets(EdgeInsets())
                    }
                }
            }
        }
    }
}

struct KittenSwiftView_Previews: PreviewProvider {
    static var previews: some View {
        KittenSwiftView(proxy: KittenViewProxy())
    }
}
