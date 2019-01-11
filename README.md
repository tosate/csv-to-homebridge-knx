# CSV to Homebridge KNX

This tool uses a CSV input to generate the knx_config.json file needed for the [homebridge][b90314d1] KNX plugin. It currently supports the following services:
- ContactSensor
- Lightbulb
- OccupancySensor
- Outlet
- SmokeSensor
- Switch
- Thermostat
- WindowCovering

Even if you don't intend to use this tool, have a look at the knx_config.json file. It might give you some hints how to configure the above mentioned services for the homebridge-knx plugin.

  [b90314d1]: https://homebridge.io/ "homebridge"
