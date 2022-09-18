# P02_Trexis

Contains:

Login Screen
Accounts list screen (with 3 fragments supported by bottom navigation bar)
Transactions screen

Uses:
Hilt for dependency injection
Completely written in kotlin, coroutines
Architecture configuration MVVM
Retrofit for networking

/**/
The adapters are delibrately separated considering the changes needs to be done as per clients requirements (Came by experience)

Development is done on emulator
**
To run the real device, replace the ip of the local host inside app/build.gradle file, local server must be running
**
