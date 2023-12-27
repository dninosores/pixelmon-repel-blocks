# Pixelmon Repel Blocks and Spawn Checker
A small sidemod for Pixelmon 1.16.5 that gives you access to a repel block that prevents pokemon from spawning in an area, 
a repel charm that prevents pokemon from spawning near you, and a spawn check item that you can use to see what
pokemon are able to spawn at your location.

[Download Here](https://github.com/dninosores/pixelmon-repel-blocks/releases/latest)

![screenshot](screenshot.png)

You can specify what types of spawns are checked in the config. By default
it allows you to check all possible spawns, but there are some spawns (like tall grass)
that don't occur naturally and so you might want to exclude them from the list of
spawners. Here's the config entry for all natural spawns:

```YAML
spawner-names:
- PLAYER
- legendary
- megaboss
- fishing
- headbutt
- rocksmash
- seaweed
- sweetscent
- forage
- curry
```