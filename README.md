HerpDerpCraft is a client-only mod that herps all the derps in Minecraft chat. Inspired by
Tanner Stokes' [HerpDerp YouTube comments extenstion][0] and Jamie Zawinski's
[HerpDerp WordPress comments plugin][1].

# Requirements

* Minecraft Forge client for 1.10.2, at least [12.18.1.2011][2]

# Installation

1. Download the [latest release JAR][3] or clone this repository and build a JAR file
1. Place the JAR file in the `mods/` directory of the client
1. Run/restart the client
1. Use the command `/herpderp` in-game to toggle
1. Open `config/HerpDerp.cfg` and modify filters as desired

# Features
This does not affect server chat; only you will see the effect.

This does not change chat messages you send to the server.

Whilst enabled, original chat messages are dumped to the log. Make sure your Launcher
profile is set to "[Keep the launcher open][4]" to access logging during play.

# To-do
* Per-player herp derp

# Bugs
* Formatting may be lost within chat messages
* Some servers are not yet supported (special chat format/syntax), but you can try to fix
this yourself in the config file

# Building

## Usage
Simply execute `gradle setupCIWorkspace` in the root directory of this repository. Then
execute `gradle build`. If subsequent builds cause problems, do `gradle clean`.

# Debugging

HerpDerpCraft makes use of `DEBUG` and `TRACE` logging levels for debugging. To enable
these messages, append this line to the client's JVM arguments:

> `-Dlog4j.configurationFile=log4j.xml`

Then in the root directory of the client, create the file `log4j.xml` with these contents:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration monitorInterval="5">
  <Appenders>
    <Console name="Console" target="SYSTEM_OUT">
      <PatternLayout pattern="[%d{HH:mm:ss} %-4level] %logger{36}: %msg%n"/>
    </Console>
  </Appenders>
  <Loggers>
    <Root level="INFO">
      <AppenderRef ref="Console"/>
    </Root>
    <Logger name="HerpDerp" level="ALL" additivity="false">
      <AppenderRef ref="Console"/>
    </Logger>
  </Loggers>
</Configuration>
```

[0]: http://www.tannr.com/herp-derp-youtube-comments/
[1]: http://www.jwz.org/herpderp/ 
[2]: http://files.minecraftforge.net/maven/net/minecraftforge/forge/1.10.2-12.18.1.2011/forge-1.10.2-12.18.1.2011-installer.jar
[3]: https://github.com/RoyCurtis/HerpDerpCraft/releases
[4]: http://i.imgur.com/hhXZMuP.png