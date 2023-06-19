fetchWeather("Lakeland", 0);
fetchWeather("London", 1);
fetchWeather("New York", 2);
fetchWeather("Belgrade", 3);

fetchForecast("Lakeland", 0);
fetchForecast("London", 1);
fetchForecast("New York", 2);
fetchForecast("Belgrade", 3);

function fetchWeather(query, card){
    var url = 'https://api.openweathermap.org/data/2.5/weather?q=' + query + '&appid=d3dfecc49283e0eb9034c83274ffa572&units=imperial';

    fetch(url)
    .then(response => response.json())
    .then(data => renderWeather(data, card))
    .catch(err => console.error(err));
    
    
}

function fetchForecast(query, card){
    var url = 'https://api.openweathermap.org/data/2.5/forecast?q=' + query + '&appid=d3dfecc49283e0eb9034c83274ffa572&units=imperial';

    fetch(url)
    .then(response => response.json())
    .then(data => renderForecast(data, card))
    .catch(err => console.error(err));
}

function renderWeather(weather, card){
    var degree = String.fromCharCode(176);

    var city = document.getElementsByClassName('card-title');
    city[card].textContent = weather.name;


    var temperature = document.getElementsByClassName('card-subtitle');
    temperature[card].textContent = weather.weather[0].main + " - " + weather.main.temp + degree + "F";


    var iconID = document.getElementsByClassName("card-text");
    var id = weather.weather[0].id;


    if(id>=200 && id<=232){
        iconID[card].innerHTML = '<i class="bi bi-cloud-lightning"></i>';
    }
    else if(id>=300 && id<=321){
        iconID[card].innerHTML = '<i class="bi bi-cloud-rain"></i>';
    }
    else if(id>=500 && id<=531){
        iconID[card].innerHTML = '<i class="bi bi-cloud-rain-heavy"></i>';
    }
    else if(id>=600 && id<=622){
        iconID[card].innerHTML = '<i class="bi bi-cloud-snow"></i>';
    }
    else if(id>=700 && id<=771){
        iconID[card].innerHTML = '<i class="bi bi-cloud-fog"></i>';
    }
    else if(id == 781){
        iconID[card].innerHTML = '<i class="bi bi-tornado"></i>';
    }
    else if(id == 800){
        iconID[card].innerHTML = '<i class="bi bi-sun"></i>';
    }
    else if(id > 800 && id < 805){
        iconID[card].innerHTML = '<i class="bi bi-cloud"></i>';
    }
    else{
        iconID[card].innerHTML = '<i class="bi bi-patch-question"></i>';
    }

}


function renderForecast(weather, card){

    console.log(weather);
    var modalLink = document.getElementsByClassName("card-link");

    modalLink[card].addEventListener("click", function(event){
        event.preventDefault();

        //TITLE
        var title = document.getElementById("modal-title");
        title.textContent = "Forecast for " + weather.city.name;

        var output = document.getElementsByTagName("li");

        var hourArr = [];
        var arr = [];
        var idArr = [];
        var list = weather.list;
        var degree = String.fromCharCode(176);

        //looping through the list to get all hours
        for(var i = 0; i < list.length; i++){
            var hour = list[i].dt_txt;

            //getting all the hours
            var hourOnly = parseInt(hour.substring(10));

            //pushing into the array
            hourArr.push(hourOnly);
        }

        //looping through the array of hours to get the time of 3am
        for (var i = 0; i < hourArr.length; i++){

            //after we check if the time is 3am,
            //we get the min temperature and we attach to the html modal
            if(hourArr[i] == '3'){
                var minTemp = list[i].main.temp_min;
            }

            if(hourArr[i] == '15'){
                var maxTemp = list[i].main.temp_max;
                var state = list[i].weather[0].main;
                var wind = list[i].wind.speed;
                var id = list[i].weather[0].id;

                idArr.push(id);
                arr.push(maxTemp + degree + "F / " + minTemp + degree + "F - " + state + ", " + wind + " mph wind");

                for(var j = 0; j < output.length; j++){

                    if(idArr[j]>=200 && idArr[j]<=232){
                        output[j].innerHTML = '<i class="bi bi-cloud-lightning"></i> ' + arr[j];
                    }
                    else if(idArr[j]>=300 && idArr[j]<=321){
                        output[j].innerHTML = '<i class="bi bi-cloud-rain"></i> ' + arr[j];
                    }
                    else if(idArr[j]>=500 && idArr[j]<=531){
                        output[j].innerHTML = '<i class="bi bi-cloud-rain-heavy"></i> ' + arr[j];
                    }
                    else if(idArr[j]>=600 && idArr[j]<=622){
                        output[j].innerHTML = '<i class="bi bi-cloud-snow"></i> ' + arr[j];
                    }
                    else if(idArr[j]>=700 && idArr[j]<=771){
                        output[j].innerHTML = '<i class="bi bi-cloud-fog"></i> ' + arr[j];
                    }
                    else if(idArr[j] == 781){
                        output[ji].innerHTML = '<i class="bi bi-tornado"></i> ' + arr[j];
                    }
                    else if(idArr[j] == 800){
                        output[j].innerHTML = '<i class="bi bi-sun"></i> ' + arr[j];
                    }
                    else if(idArr[j] > 800 && idArr[j] < 805){
                        output[j].innerHTML = '<i class="bi bi-cloud"></i> ' + arr[j];
                    }
                    else{
                        output[j].innerHTML = '<i class="bi bi-patch-question"></i> ' + arr[j];
                    }
                }
                
            }
        }
    });
}


