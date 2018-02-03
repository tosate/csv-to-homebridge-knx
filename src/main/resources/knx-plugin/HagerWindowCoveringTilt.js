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

		} else if (field === "CurrentHorizontalTiltAngle") {
			// CurrentPosition is DTP5.001 Percent (0..255)
			// need to convert to (-90..90)
			// Homekit is using angle in degrees and supports values from 0 to 90
			newValue = knxValue*180 / 255 - 90;
			this.myAPI.setValue("CurrentHorizontalTiltAngle", newValue);
		}

	} // onBusValueChange
	
	/*******************************************************************************************************************
	 * onHKValueChange is invoked if HomeKit is changing characteristic values
	 * 
	 */
	onHKValueChange(field, oldValue, newValue) {
		// value for KNX
		var knxValue;
		// homekit will only send a TargetPosition value, so we do not care about (non-) potential others


		if (field === "TargetPosition") {
			console.log('INFO: onHKValueChange(' + field + ", "+ oldValue + ", "+ newValue + ")");
			// update the PositionState characteristic:		
			// get the last current Position
			// var lastPos = this.myAPI.getValue("CurrentPosition");
			knxValue = 255 -newValue*255 / 100;
			
			this.myAPI.knxWrite("TargetPosition", knxValue, "DPT5");
		} else if (field === "TargetHorizontalTiltAngle") {
			console.log('INFO: onHKValueChange(' + field + ", "+ oldValue + ", "+ newValue + ")");
			
			knxValue = (newValue + 90)*255 / 180
			this.myAPI.knxWrite("TargetHorizontalTiltAngle", knxValue, "DPT5");
		}
	} // onHKValueChange
} // class	
module.exports=	WindowCoveringTilt;

	
/* **********************************************************************************************************************
 * The config for that should look like: LocalConstants is now used in this sample 
 * Reverse keyword is not allowed for custom handlers
 * 
"Services" : [ {
      "@type" : "WindowCovering",
      "ServiceType" : "WindowCovering",
      "Handler": "HagerWindowCoveringTilt",
      "ServiceName" : "Jalousie Wohnzimmer",
      "Characteristics" : [ {
        "Type" : "TargetPosition",
        "Set" : [ "2/4/1" ],
        "Listen" : [ "2/4/1" ],
        "DPT" : "DPT5"
      }, {
        "Type" : "CurrentPosition",
        "Listen" : [ "2/3/1" ],
        "DPT" : "DPT5"
      }, {
      	"Type" : "PositionState"
      }, {
      	"Type" : "TargetHorizontalTiltAngle",
      	"Set" : [ "2/6/1" ],
      	"Listen" : [ "2/6/1" ],
      	"DPT" : "DPT5"
      }, {
      	"Type" : "CurrentHorizontalTiltAngle",
      	"Listen" : [ "2/5/1" ],
      	"DPT" : "DPT5"
      } ]
    } ]
 * 
 * 
 */
