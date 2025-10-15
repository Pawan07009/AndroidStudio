📞 Android Call Recording Service (Java)
This project demonstrates a basic implementation of a background Service in Android that records audio during phone calls using the MediaRecorder API.
🔧 Features
- Starts recording audio when the service is triggered.
- Saves the recording as a .3gp file in the app's internal storage.
- Uses MediaRecorder.AudioSource.MIC for compatibility across devices (note: VOICE_CALL is restricted on Android 10+).
- Automatically stops and releases the recorder when the service is destroyed.
⚠️ Limitations
- Android 10 and above restrict access to call audio due to privacy policies. This implementation may not capture both sides of the call on newer devices.
- Legal compliance: Always inform users and obtain consent before recording calls. Laws vary by country and region.
