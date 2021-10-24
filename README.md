## Features
- The login screen contains an email and password text input field - both fields must not be empty to navigate to the next screen.
- The photos screen contains a list of photo list within scrollable list.

## Prerequisites
This repo contains an Android Studio project, which can be imported into Android Studio and then built and run on Android Devices or emulators.

The app will run on devices that meet these requirements:
- Minimum SDK: 23

Please disable animations on your device when running the UI tests.

## Implementation

I have implemented an MVVM architecture with a single Activity that supports a multiple fragment approach with the Jetpack Navigation Component. I built the application in interest of providing an architecture that could be easily and cleanly expanded upon should more features be added.

The DataRepository is intended to be the single data source made accessible to all ViewModels via dependency injection with the Koin Injection Framework.
The DataRepository is abstracted from the remote data source with intention that it could also provide access to a local database in the future. Although I did
omit a local database in this particular implementation, it could be preferable to save the photo api response locally, for more consistent data that
would not rely so heavily on the user having a network connection. However, in this example network errors are handlded gracefully.

I utilised Retrofit to handle accessing the API endpoints, from which the responses are sent back the the ViewModels as a sealed class which defines 
success and error types. This reponse is then observed within the Fragments via LiveData where the UI layer responds appropriately. 

The retrieved data is displayed in a RecyclerView via an Adapter. I also upgraded the Adapter 
to use DiffUtil which optimizes the RecyclerView to only refresh should the data change. Additionally, DataBinding is used in the Fragment and Adapter.

I have written Unit and UI tests to test the Viewmodels and Fragments.
