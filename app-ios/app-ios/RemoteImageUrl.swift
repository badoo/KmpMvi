//
//  RemoteImageUrl.swift
//  app-ios
//
//  Created by Arkadii Ivanov on 27/03/2020.
//  Copyright © 2020 Badoo. All rights reserved.
//

import SwiftUI

private let cache = DataCache()

// MARK: A very rough implementation of image loading. Should not be used in a real app.
class RemoteImageUrl: ObservableObject {
    @Published var data = Data()
    
    init(_ imageUrl: String) {
        guard let url = URL(string: imageUrl) else { return }

        if let cachedData = cache[url] {
            self.data = cachedData
        } else {
            load(url)
        }
    }

    private func load(_ url: URL) {
        URLSession.shared.dataTask(with: url) { (data, response, err) in
            guard let data = data else { return }
            DispatchQueue.main.async {
                cache[url] = data
                self.data = data
            }
        }.resume()
    }
}

private class DataCache {
    private let cache = NSCache<NSURL, NSData>()
    
    subscript(url: URL) -> Data? {
        get {
            cache.object(forKey: url as NSURL) as Data?
        }
        set(data) {
            if let newData = data {
                cache.setObject(newData as NSData, forKey: url as NSURL)
            } else {
                cache.removeObject(forKey: url as NSURL)
            }
        }
    }
}
