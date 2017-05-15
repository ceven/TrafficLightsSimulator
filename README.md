# TrafficLightsSimulation
This project allows to simulate traffic lights changing colour over time at a given intersection. 

## Problem
The given problem is to simulate an intersection composed of 4 traffic lights of different directions (North (N), South (S), East (E) and West (W)). The lights can take 3 different colours, namely green, yellow and red. The lights automatically change every 5 minutes, and the green light transitions to yellow for 30 seconds before turning red.

## Assumptions
Based on real-life experience, I made the assumption that the intersection works in the following way:

 - The intersection is composed of two sets of opposite traffic lights, namely NS for North-South and EW for East-West, which much respectively change of colour at the same time (i.e. synchronously). In other words, the colour of the North (resp. East) light is always the same as the colour of the South (resp. West) light, at any point in time.
 - Initially, at the beginning of the simulation, all lights are red. 
 - The beginning of the simulation is the only time when all lights can be red at the same time. At any other time, a set of light (e.g. NS) must be red when the opposite set (e.g. EW) is either green or yellow.
 - The NS lights will transition to green first, as soon as the simulation starts.
 - Each set of synchronous lights stays green for a consecutive period of 4.30 minutes, followed by a 30-second yellow time, and immediately switching to red afterwards. As soon as one set of synchronous lights becomes red, the opposite set becomes green.

The previous assumptions could actually easily been changed with minimal impact on the code base. The current code already gives the flexibility to build and simulate a generic intersection of traffic lights, with any number of lights, and any type of interaction between the lights. It is also possible to customise the duration of a given traffic light colour.

## Requirements & How to run
This project requires Java 8. I use gradle (2.6) to manage dependencies, run the simulation and run tests. Please follow the instructions below to download and run the code:

 - Open a terminal prompt and clone this repository in the location of your choice: ```git clone https://github.com/ceven/TrafficLightsSimulator.git```
 - Navigate to the project root
 - Type ```gradle clean build``` to build the project and run unit tests
 - Type ```gradle run``` in a terminal prompt to run the 30-minute simulation. Logs will be printed in the console every time the colour of a light changes
 - Type ```gradle test``` to run the unit tests

## Design and implementation
The code is organised into 3 main packages:

 - ```light``` contains the implementation for: (1) a traffic light, the latter being composed of a colour and a direction, and (2) a synchronous traffic light, which is composed of traffic lights.
 - `state` regroups the different states that a set of synchronous lights can take: either green, yellow or red.
 - `intersection` contains a generic intersection model (TrafficIntersection) and the intersection model which is the focus of this problem (FourWayTrafficIntersection).

The simulation is run using either `MainSimulation` or `QuickSimulation` (the latter class allows to customise the colour duration).

I have used the following design patterns:
 - `Builder` to create traffic lights.
 - `State` to capture the state of a synchronous traffic light, and allow to switch between states easily.
 - `Observer` for synchronous lights to update each other of their changes: e.g. one turning red triggers the opposing set to turn green and vice-versa. The implementation allows an unlimited set of observers for each synchronous traffic light.

I also use asynchronous task scheduling for changing the colour of lights after a certain time.

I am aware that this solution has advantages and limitations. For example, I deliberately made the assumption that lights are responsible for updating their own state, or notifying other lights to update their state. This allows flexibility in adding lights and setting up interactions, but requires to give up control at the intersection level. I am always happy to receive feedback for my design and modelling choices.