https://github.com/bernardinhio/Invoicer/blob/master/app/src/main/java/com/example/invoicerapp/data/InvoicerRepository.kt

I originally made this simple App some time ago to use DaggerHilt with Retrofit Instance and Retrofit Api, but I later I made a very common kind of  codes with the MVVM architecture having a Repository class that uses MutableStateFlows and a ViewModel class using MutableLiveData because this is the recommended approach since the LiveData offer “LifeCycle awareness” which means when the Fragment/Activity is paused or destroyed than the Observable LiveData stops updating the observes in View… In brief, such a code is a typical needed code in modern projects that use the latest approaches. I also used sealed class with data class to monitor the Status of the backend calls with retrofit and reactively updating the ViewModel from the Repository and then updating the Fragment. Sure I used basic single-Activity App with Navigation component and full Kotlin Code to implement a RecyclerView that shows the data returned from Retrofit. (also I used the ListAdapter  instead of the RecyclerView.Adapter because it is more efficient especially for cases when we don’t want every time to load all the Items of the RecyclerView but we want to compare if they are same or have same content to the old ones)

Some screenshots
Homepage
![Screenshot_1641772825](https://user-images.githubusercontent.com/20923486/148707114-18bd208f-71f4-45f3-96dc-fbd5b9dbc82f.png) 

Loading page
![Screenshot_1641772837](https://user-images.githubusercontent.com/20923486/148707135-cfe94dd2-0a3a-4c00-bc54-e07c4ef7a21f.png)

Result Success
![Screenshot_1641772848](https://user-images.githubusercontent.com/20923486/148707143-865bee18-c6d8-4624-8642-a0904c06a5a9.png)

Error No Internet
![Screenshot_1641772885](https://user-images.githubusercontent.com/20923486/148707156-67094b03-3ca9-4f01-9e72-f75b2611734e.png)

