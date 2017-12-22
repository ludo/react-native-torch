// @flow
import { NativeModules, PermissionsAndroid } from 'react-native';

const { Torch } = NativeModules;

async function requestCameraPermission(
  title: string,
  message: string
): Boolean {
  try {
    const hasCameraPermission = await PermissionsAndroid.check(
      PermissionsAndroid.PERMISSIONS.CAMERA
    );

    if (hasCameraPermission) {
      return true;
    }

    const granted = await PermissionsAndroid.request(
      PermissionsAndroid.PERMISSIONS.CAMERA,
      {
        title,
        message
      }
    );

    if (granted === PermissionsAndroid.RESULTS.GRANTED) {
      return true;
    }
    return false;
  } catch (err) {
    console.warn(err);
    return false;
  }
}

const TorchWithPermissionCheck = {
  ...Torch,
  requestCameraPermission
};

/**
 * This exposes the native `Torch` module as a JS module.
 *
 * `Torch` has one function `switchState` which takes one parameter:
 *
 * `Boolean newState`: A boolean indicating next torch status.
 */

export default TorchWithPermissionCheck;
