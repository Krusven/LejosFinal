# LejosFinal

University Project to make a Lego Mindstorms Robot print the translation of user input.

The Robot is a programmable Lego Mindstorms ev3 that prints by moving a pencil (here on a transparent thus hard to see) with a y-and x-axis motor:
![image](https://user-images.githubusercontent.com/45241829/188287340-46a3a0ea-0578-4e81-8352-28c8bff1e529.png)

A user gets prompted to enter some words (limited in length) and a target language. 
The words are translated using the google tranlate API and the translation gets printed by the robot by creating a BufferedImage, extracting all color pixels into an array and then making the motors go to each coordinate and setting the pencil down to make a dot or a line between coordinates:

![image](https://user-images.githubusercontent.com/45241829/188287334-bb3417ab-a697-4617-b853-d7f12e447b71.png)

The following is the logic:

![image](https://user-images.githubusercontent.com/45241829/188287316-a424ee6d-1025-45ba-a605-ee33cf1d3963.png)
