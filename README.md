## Idea

The big idea is to have, on multiple levels, a follower - or a twitterBot. First, our aim is to use a Roomba, Arduino and Raspberry Pi to have a piece of hardware that moves around, following a target and simultaneously, using a speech to text API, listening to the target and tweeting everything said by the target, acting just like a follower on twitter that re-tweets a post for instance. In addition, our aim is to develop an Android application to support the twitterBot. The specifics of the hardware and mobile application are listed below in the process flow. 

## Process flow and requirements

This project draws on Arduino and virtual network knowledge gained from the Network Everything Interactive Media class taught by Scott Fitzgerald. I will work with Luis Morales on setting up the Roomba but will work alone on the Android application. The necessary hardware will also be obtained through this class. This would primarily involve 2 pieces of hardware - the Roomba and an Arduino with a WiFi shield. 

The process flow would be as follows:

1. The twitterBot listens to sound made by a "target". 
2. Using a publicly available speech to text API, this sound is converted to text.
3. The converted text is then posted to either an online database such as IBM's Cloudant or Xively, which provides an API endpoint from which a data stream can be received in JSON format.
4. The data will also be posted to a twitter account created for this Roomba, where followers can view data streams from the twitterBot.
5. The data will be pulled from this API endpoint and the user can, after logging into the Android application, view the stream of text that the twitterBot has listened to.
6. Using a text-speech API, the user can click on a particular element from data stream (a tweet size string) and can listen to it.
7. The above steps constitute the minimum viable product but some, if not all of the following steps will also be completed by release 4. 
        a. The ability to trigger events on the Roomba from the application. This would demonstrate two-way communication between the application and the hardware. This could be done using Near Field Communication, which Android supports natively on more recent versions of the software. 
        b. A map interface to track the Twittering Roombas registered to a user. This will involve the use of the Google Maps API and the Roomba also posting location information to the online database.

Check out the wiki for the timeline and a discussion about the components of this project.
