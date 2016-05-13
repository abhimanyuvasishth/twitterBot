## Idea

The big idea is to have, on multiple levels, a follower - or a twitterBot. First, our aim is to use a Roomba and an Arduino Mega ADK to have a piece of hardware that moves around, following a target and simultaneously, using a text to speech API, gets tweets from Twitter and reads them out loud in an annoying voice. Our aim is to develop an Android application to support the twitter aspect of the twitterBot. The specifics of the hardware and mobile application are listed below in the process flow. 

## Process flow and requirements

This project draws on Arduino and virtual network knowledge gained from the Network Everything Interactive Media class taught by Scott Fitzgerald. I will work with Luis Morales on setting up the Roomba but will work alone on the Android application. The necessary hardware will also be obtained through this class. This would primarily involve 2 pieces of hardware - the Roomba and an Arduino with a WiFi shield. 

The process flow would be as follows:

1. The target wears a hat containing IR Leds
2. A modified PlayStation camera filters out visible light and monitors the IR LEDs
3. The information is sent to a Processing sketch
4. Using a computer vision and blob detection library, the position of the roomba and the target will be known and the roomba can be instructed to follow the target and will be given instructions to reroute every couple of seconds. 
5. The Android application gets tweets from a URL endpoint based on a hashtag or a screen name.
6. The data will be pulled from this API endpoint and the user can, after logging into the Android application, view the stream of text that the twitterBot has listened to.
7. Using a text-speech API, the user can click on a particular element from data stream (a tweetString size string) and can listen to it.
8. The above steps constitute the minimum viable product but some, if not all of the following steps will also be completed by release 4. 
        a. The ability to trigger events on the Roomba from the application. This would demonstrate two-way communication between the application and the hardware. This could be done using Near Field Communication, which Android supports natively on more recent versions of the software. 
        b. A map interface to track the Twittering Roombas registered to a user. This will involve the use of the Google Maps API and the Roomba also posting location information to the online database.

Check out the wiki for the timeline and a discussion about the components of this project.
