import { NativeModules } from 'react-native';

const { Torch } = NativeModules;

/**
 * This exposes the native `Torch` module as a JS module.
 *
 * `Torch` has one function `switchState` which takes one parameter:
 *
 * `Boolean newState`: A boolean indicating next torch status.
 */
export default Torch;
