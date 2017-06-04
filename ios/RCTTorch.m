//
//  RCTTorch.m
//  Cubicphuse
//
//  Created by Ludo van den Boom on 06/04/2017.
//  Copyright © 2017 Cubicphuse. All rights reserved.
//

#import <AVFoundation/AVFoundation.h>
#import "RCTTorch.h"

@implementation RCTTorch

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(switchState:(BOOL *)newState)
{
    if ([AVCaptureDevice class]) {
        AVCaptureDevice *device = [AVCaptureDevice defaultDeviceWithMediaType:AVMediaTypeVideo];
        if ([device hasTorch]){
            [device lockForConfiguration:nil];

            if (newState) {
                [device setTorchMode:AVCaptureTorchModeOn];
            } else {
                [device setTorchMode:AVCaptureTorchModeOff];
            }

            [device unlockForConfiguration];
        }
    }
}

@end
