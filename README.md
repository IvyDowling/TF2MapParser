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

#syntax
every element takes only one line, and additions to the previous element (doors, walls, etc) are on following lines beginning with -'command'
#scale can be set on the first line with the command
scale a
#the skybox is made by typing,
skybox x y z xs ys zs
every map must have a skybox before any elements are declared

#To make a spire, type:
spire x y z xs ys zs

#To make a room, type:
room x y z xs ys zs thickness-of-walls
#Each room can have walls deleted by typing
-del wall
where wall is the face that will be deleted, (sign)(axis)
 +z: top -z: bottom +x: left -x: right +y: back -y: front
#doors can be made on walls by typing 
-port x y xs ys
 the door is defined only with reference to the wall, therefore x,y
 every wall is observed from the positive direction of the axis.
 Z wall: x = Wx y = Wy
 X wall: x = Wy y = Wz
 Y wall: x = Wx y = Wz
Only one door can be placed per wall of the room**

#to make a barrier in the map, type
barr x y xs ys

#To make the map rotationally symmetrical, type:
mirror

Java's Scanner class is used so white-space on a single line is no big deal
Comments are made by including the '#' sign at the beginning of a line
#In general

SKYBOX
- Acts as the reference for the mirroring process
- Holds each of the following elements

SPIRES
- Are always rectangles
- Are ideally used as barriers and terrain instead of walls
- defined by their x y z coordinate and their xs ys zs volume

ROOMS
- Are defined by their x y z outside coordinates,
	their size xs ys zs, and the thickness of the walls

BARRIERS (barr)
- Are always the height of the skybox
- Used for making playable wall space (specifically for rocket jumping)

For an example file, check out RoomsDoorsExamples.txt
