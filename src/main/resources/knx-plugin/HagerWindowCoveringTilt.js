/* Sample module - Simple handler for rolling shutter actuator  
 * 
 */
/* jshint esversion: 6, strict: true, node: true */
'use strict';

/**
 * @type {HandlerPattern}
 */
var HandlerPattern = require('./handlerpattern.js');
var log = require('debug')('WindowCoveringTilt');

/**
 * @class A custom handler for the "Jalousie Aktor" (rolling shutter/blinds actuator) - including Tilt
 * @extends HandlerPattern
 */
class WindowCoveringTilt extends HandlerPattern {

	/*******************************************************************************************************************
	 * onKNXValueChange is invoked if a Bus value for one of the bound addresses is received
	 * 
	 */
	onKNXValueChange(field, oldValue, knxValue) {
		// value for HomeKit
		var newValue;

		console.log('INFO: onKNXValueChange(' + field + ", "+ oldValue + ", "+ knxValue+ ")");
		
		if (field === "CurrentPosition") {
			// Current Position is sent by the actuator if the Movement has stopped a new postion is reached
			
			// CurrentPosition is DPT5.001 Percentage (0..255)
			// need to convert to (0..100) first
			// Homekit is using %-open, meaning 0% is closed/down
			newValue = 100 - knxValue*100 / 255;
			
			this.myAPI.setValue("CurrentPosition", newValue); // inform homekit
			this.myAPI.setValue("PositionState", 2); //stopped


			// return to stopped immediately, and set the Target to Current
			this.myAPI.setValue("TargetPosition", this.myAPI.getValue("CurrentPosition"));

		}

	} // onBusValueChange
	
	/*******************************************************************************************************************
	 * onHKValueChange is invoked if HomeKit is changing characteristic values
	 * 
	 */
	onHKValueChange(field, oldValue, newValue) {
		// homekit will only send a TargetPosition value, so we do not care about (non-) potential others


		if (field === "TargetPosition") {
			console.log('INFO: onHKValueChange(' + field + ", "+ oldValue + ", "+ newValue + ")");
			// update the PositionState characteristic:		
			// get the last current Position
			// var lastPos = this.myAPI.getValue("CurrentPosition");

			if (newValue < 20) {
				this.myAPI.knxWrite("TargetPosition", 1, "DPT1"); // send the new position to KNX bus
			} else if (newValue > 80){
				this.myAPI.knxWrite("TargetPosition", 0, "DPT1"); // send the new position to KNX bus
			} else if (newValue == 50) {
				this.myAPI.knxWrite("HoldPosition", 1, "DTP1");  // send the new position to KNX bus
			}
		}
	} // onHKValueChange
} // class	
module.exports=	WindowCoveringTilt;

	
/* **********************************************************************************************************************
 * The config for that should look like: LocalConstants is now used in this sample 
 * Reverse keyword is not allowed for custom handlers
 * 
"Services": [{
					"ServiceType": "WindowCovering",
    				"Handler": "WindowCoveringTilt2",
					"ServiceName": "Rollo",
					"Characteristics": [{
						"Type": "TargetPosition",
						"Set": ["2/0/10"],
						"DPT": "DPT1"
					}, {
						"Type": "CurrentPosition",
						"Listen": ["2/0/14"]
					}, {
						"Type": "HoldPosition",
						"Set": ["2/0/11"],
                        "DPT": "DPT1"
					}, {
						"Type": "PositionState"
					}]
				}


			]
		}
 * 
 * 
 */
