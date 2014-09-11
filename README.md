TF2MapGenerator
===============

A text-based Team Fortress 2 Map Creator

**Current Implementation**
Running the map generator:
Open command prompt and navigate to the location of TF2MapGenerator.jar
type "java -jar TF2MapGenerator.jar {filepath}.txt {arguments}

Current argument list:
-o {desired-map-name}
-h (for help)

Syntax for creating from a text document
-Every map starts with 3 numbers that define the size of the map
-This makes an enclosed box with the lower left corner at 0 0 0

x y z

To make a spire, type:

spire x y z xs ys zs

x y z       are the location of the lower left corner of the spire
xs ys zs    is the size of the spire
-spires are always rectangles

To make the map mirrored, type:
mirror
