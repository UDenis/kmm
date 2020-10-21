//
//  DelayedUpdater.swift
//  iosApp
//
//  Created by  Денис on 20.10.2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import shared

class DelayedUpdater:ObservableObject {
    @Published var result = ""
    
    func caclFactorial() {
        FactorialIos().calc(arg: 12) { (r, error) -> () in
            self.result = r ?? ""
        }
    }
}
