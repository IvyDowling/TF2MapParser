TF2MapGenerator
===============

A text-based Team Fortress 2 Map Creator

**Current Implementation
Running the map generator:
Open command prompt and navigate to the location of TF2MapGenerator.jar
type "java -jar TF2MapGenerator.jar {filepath}.txt {arguments}

Current argument list:
-o {desired-map-name}
-h (for help)

Syntax for creating from a text document
-Each doc can start with either the scale that the map should use, or the skybox.
-Every doc must include a skybox declaration, but scale is optional
scale 2
skybox x y z xs ys zs

#To make a spire, type:
spire x y z xs ys zs

#To make a room, type:
room x y z xs ys zs thickness-of-walls

#To make the map rotationally symmetrical, type:
mirror

SPIRES
- Are always rectangles
- Are ideally used as barriers and terrain instead of walls
- defined by their x y z coordinate and their xs ys zs volume

ROOMS
- Are defined by their x y z outside coordinates,
	their size xs ys zs, and the thickness of the walls
- Currently are closed, but in the future doors and wall deletion will be added.
	-workaround- I usually just delete the wall in Hammer before I compile...

For an example file, check out theTestor.txt
