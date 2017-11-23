# GoogleBooks
GoogleBooks list using REST

Search for a book, then display (some details) for it in a new Activity when clicked.

Used RxJava with Retrofit to retrieve the book list according to the search criteria and also for the book details based on the book id.
Used ViewModel(s) from the Android Support Library with LiveData as lifecycle aware Observables to pass data to the UI.
Used Paging Library with a TiledDataSource to provide pagination functionality for the RecyclerView list of books.

Possible future improvements:
- For now the models are in memory and separate for the book list and the book details. They are persistent when the device is rotated since the ViewModels survive activity recreation. A database (like Room) could be used for caching network call results and models between app runs and as the single source of truth. The complexity and thus the implementatin time would increase more than what's needed for a sample app. 
- UI was not a major focus, and only a limited set of fields are shown for book details. The other fields are just more of the same.
