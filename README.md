# owlBlockDiagram

OWLBlockDiagram is a high level ontology editor, applied to industrial processes. The computational tool generates a ontology representing all knowledge contained in a block diagram made by the user, through a drag and drop toll.

How to use it
----------------------------------
The software uses a simple and intuitive drag and drop tool to help the user modelling any industrial process.
All details needed can be found in the Tutorial file, in Help section.

How to contribute
----------------------------------
As a free software, there are many ways to contribute, and I encourage you to do so!

GitHub has a nice guide about contributing to open source softwares: https://guides.github.com/activities/contributing-to-open-source/

First thing: Play around with the code. Download the source code from GitHub and take a closer look into the software.

Then, you have a couple of options:
You can make suggestions of new features or improvements to the ones already implemented.
You may also take a look on the Road Path document to see if you find something interesting to implement yourself. If you do so, take a look on the 'Pull request' section on the link above.
You can report bugs. On the GitHub link I pasted above you can see how to create an Issue to report a bug. If you find it difficult or boring, or both, just send me an email (jeh.ajdo@gmail.com) :)

If you decide to do one of the above, add yourself to the authors section (Contributor: your_name (your_email)), but if you decide to adventure yourself trough the code, take a look at 'To Developers' section. It may help you to be aware of what you may find, and how to explore it.


To Developers
----------------------------------
The software was developed based in a MVCish architecture. The packets follow the structure: 

 - DTO (data to object) - Classes to parse the data contained in the ontology file to java objects
controller - There is only one controler to load .owl files
 - io - Classes to save or open program files
 - model - All inside work of the main interface elements
 - owlblockdiagram - contains the main class, to start the program
 - utils - Contais classes to be used in varius circunstances
 - view - All classes that deal with th interface

Also, all the interface was developed using VisualLibrary. You can find tutorials and documentation on VisualLibrary in the Netbeans official web page: https://platform.netbeans.org/graph/

When you do any modification in the code, add yourself to the authors section (Developer: your_name (your_email)).

Authors
----------------------------------
Main developer: Jéssica Oliveira (jeh.ajdo@gmail.com)


License
----------------------------------
OWLBlockDiagram, high level ontology editor.
    Copyright (C) 2015  Jéssica Oliveira

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.


http://opensource.org/licenses/GPL-3.0
