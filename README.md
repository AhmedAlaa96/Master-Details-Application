# Master Details Application

<p> Master Details Application is an application to show airline list and details for each airline.<br/>Using MVVM architecture<p>

  * The application starts with a splash screen to then open to the main view displaying airlines list. Provide the option to filter this list by airline name, airline id or country using a search bar.
  * Selecting an airline entry will redirect to airline details page.
  * Airline details displaying name, country, slogan, logo, headquarter and clickable website link if it's valid line.
  * User can add a new airline to the existing list.
  * Support availability to data in offline mode.
  
## The application contain 2 parts
### **1. First part:**
  The online part using `RxJava` and `Retrofit` libraries to get data from https://www.instantwebtools.net/fake-rest-api first it return airline list with
  endporint: https://api.instantwebtools.net/v1/airlines 
 
  Response: 
  ```
  [
    {
        "id": 1,
        "name": "Quatar Airways",
        "country": "Quatar",
        "logo": "https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/300px-Qatar_Airways_Logo.svg.png",
        "slogan": "Going Places Together",
        "head_quaters": "Qatar Airways Towers, Doha, Qatar",
        "website": "www.qatarairways.com",
        "established": "1994"
    },
    {
        "id": 2,
        "name": "Singapore Airlines",
        "country": "Singapore",
        "logo": "https://upload.wikimedia.org/wikipedia/en/thumb/6/6b/Singapore_Airlines_Logo_2.svg/250px-Singapore_Airlines_Logo_2.svg.png",
        "slogan": "A Great Way to Fly",
        "head_quaters": "Airline House, 25 Airline Road, Singapore 819829",
        "website": "www.singaporeair.com",
        "established": "1947"
    }
]
  ```
  
  and using to show airline details.<br/>
  
  -----

  ### **2. Second part:**

  The offiline mode using `Room` database library and DAO to show data by the `viewModel` for the `MainActivity`.
  
