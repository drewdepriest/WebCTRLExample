# WebCTRLExample

In 2014, I built this sample class to demonstrate how to read a single point of the Automated Logic WebCTRL building automation system (BAS). At the time, I was one of maybe three developers building web apps with the WebCTRL SDK.

If memory serves, this would have been compatible with WebCTRL versions 4.1SP1, 5.2, 5.5, and 6.0 (the versions that supported .war or .addon apps, up until the time I left ALC). The class reads in an enthalpy point from the single outside air conditions program, and writes it to the browser.

In that time, I used this example to build a walkthrough guide for others in the company to start building their own web apps as well. Projects I built using the SDK include:

- SNMPTrApp - leveraged the SNMP4J open source API to enable a WebCTRL system to monitor data center infrastructure alarms delivered via SNMP traps
- SortApp - read in and sort live data from 110 campus building energy meters, to allow for display by kWh consumption in ascending order on a public-facing kiosk for Princeton University (BAS platforms using graphical programming cannot sort more than three values at a time, whereas Java can do it quite easily as a list of BigIntegers)

Cheers,
Drew
