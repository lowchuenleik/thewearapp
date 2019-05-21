# weatherapp
Requirements:
- requires installation of JSON-java package (https://github.com/stleary/JSON-java)
- soon -- javafx

APIs
- daily: https://www.weatherbit.io/api/weather-forecast-16-day
- hourly: https://www.weatherbit.io/api/weather-forecast-120-hour

What it does:
- fetches hourly data from weatherbit.io
- stores hourly data into JSONObject fullJSON and also truncates it into a List truncatedData (including on important weather stats)
- when truncating data, checks if the lastFetched time for getting the hourly data from weatherbit.io was over 1 hour ago. retrieves the data from weatherbit.io if so

Issues/To-improve:
- modularity (draw UML diagrams)
- include daily forecast
- Duration.between(now, lastFetched).getSeconds() may overflow? if used 20 years from now. potential solution: initialise lastFetched as week before when the object was first created
- fetch daily data

Miscellaneous
- swiping
- swing/awt: https://stackoverflow.com/questions/36800640/how-to-find-direction-of-swipe-in-jframe
- javafx: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/SwipeEvent.html

For reference
- OpenWeatherMap API client: https://github.com/akapribot/OWM-JAPIs/blob/master/src/main/java/net/aksingh/owmjapis/OpenWeatherMap.java
- Model app: https://github.com/jenz270/WeatherApplication
- Weatherbit.io API client: https://github.com/th3le0nk1ng/weatherbit-api-client
