//
//  RemoteImage.swift
//  app-ios
//
//  Created by Arkadii Ivanov on 27/03/2020.
//  Copyright © 2020 Badoo. All rights reserved.
//

import SwiftUI
import Foundation

struct RemoteImage: View {

    @ObservedObject var remoteImageUrl: RemoteImageUrl

    init(url: String) {
        remoteImageUrl = RemoteImageUrl(url)
    }

    var body: some View {
        Image(uiImage: (remoteImageUrl.data.isEmpty) ? UIImage() : UIImage(data: remoteImageUrl.data)!)
            .resizable()
            .aspectRatio(contentMode: .fit)
    }
}
