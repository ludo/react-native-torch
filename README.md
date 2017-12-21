# react-native-torch

[![npm version](https://badge.fury.io/js/react-native-torch.svg)](http://badge.fury.io/js/react-native-torch)

A simple React Native plugin to switch a flashlight on/off.

Currently supports both iOS (>= 8.0) and Android.

## Install

```shell
npm install --save react-native-torch
react-native link react-native-torch
```

## Usage

```js
import Torch from 'react-native-torch';

Torch.switchState(true) // Turn ON
Torch.switchState(false) // Turn OFF
```

A demo application [TorchDemo](https://github.com/ludo/TorchDemo) is also
available.
