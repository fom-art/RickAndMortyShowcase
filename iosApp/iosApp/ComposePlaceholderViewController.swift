//
//  ComposePlaceholderViewController.swift
//  iosApp
//
//  Created by Artem Fomenko on 01.05.2025.
//  Copyright © 2025 orgName. All rights reserved.
//


import UIKit

class ComposePlaceholderViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()

        view.backgroundColor = UIColor.systemBackground

        let label = UILabel()
        label.text = "Loading Compose UI..."
        label.textColor = .label
        label.textAlignment = .center
        label.translatesAutoresizingMaskIntoConstraints = false

        view.addSubview(label)

        NSLayoutConstraint.activate([
            label.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            label.centerYAnchor.constraint(equalTo: view.centerYAnchor)
        ])
    }
}
