$(document).ready(function () {
$('#get-weather').click(function(){
  var zipCode=$('#zip-code').val();
  var units=$('#units').val();
  var tempUnit="C";
  if (units=="Imperial") {
  tempUnit="F";
  }
  if (zipCode.length == 5) {
currentCondition(zipCode,units,tempUnit);
fiveDayforecast(zipCode,units,tempUnit);
} else {
$('#currentWeather').empty();
$('#weatherForecast').empty();
$('#cityName').empty();
   $('#errorMessages').empty()
     .append($('<li>'))
     .attr({class: 'list-group-item list-group-item-danger'})
     .text('Zip code must be five digits. you entered ' + zipCode);
 }
//on click ends here
  });

// document.ready() ends here
});






function fiveDayforecast(zipCode,units,tempUnit){
    $('#errorMessages')
    .empty().removeClass();
  var zipCode=$('#zip-code').val();
  var units=$('#units').val();
  var weatherForecast = $('#weatherForecast');
  weatherForecast.empty();
    var forecastHeading = $('#forecastHeading');
    forecastHeading.empty();
  $.ajax({
  type : 'GET',
  url:'http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=168efc8f6786ec309258e1d5cfdc99ed'+'&zip='+ zipCode+'&units='+ units,
  dataType:'jsonp',
  success: function(dataArray){
    var forecastHeading = $('#forecastHeading');
    var heading = '<p>'+" Five Day Forecast"+'</p>';
forecastHeading.append(heading);

  $('#zip-code').val('');
var i=0;
$.each(dataArray, function(index, list){

var description =    dataArray.list[i].weather[0].description;
var tempMin = dataArray.list[i].main.temp_min ;
var tempMax = dataArray.list[i].main.temp_max ;
var fullDate = (dataArray.list[i].dt_txt);
var day = fullDate.slice(8,10);
var month = fullDate.slice(5,7);
month = monthName(month);

var date = day +" "+ month;
var row ='<td width="20%">' + date ;
row+='</br>';
row += "<img src='http://openweathermap.org/img/w/"+dataArray.list[i].weather[0].icon+".png'>" +  description ;
row+='</br>';
row+=
"H " + tempMax+" "+ tempUnit +" " +
"L " + tempMin+" "+ tempUnit +" " +
       '</td>';
  weatherForecast.append(row);
i+=8;
//$.each ends here
 });
//success function ends here
},
    error: function(){
      $('#errorMessages')
        .append($('<li>'))
        .attr({class: 'list-group-item list-group-item-danger'})
        .text('Error calling webservice or invalid zipcode is entered. Please try again');
    }
  });
  }

  function currentCondition(zipCode,units,tempUnit){
    $('#errorMessages')
    .empty().removeClass();
  var currentWeather = $('#currentWeather');
  currentWeather.empty();
  $.ajax({
  type : 'GET',
  url:'http://api.openweathermap.org/data/2.5/weather?id=524901&APPID=168efc8f6786ec309258e1d5cfdc99ed'+'&zip='+ zipCode+'&units='+ units,
  dataType:'jsonp',
  success: function(data){
  $('#zip-code').val('');
  var currentCity = $('#cityName');
  currentCity.empty();
  var city = '<p>'+"Current Conditions in "+ data.name +'</p>';
  currentCity.append(city);
  var description = data.weather[0].description;
    var temp = data.main.temp ;
  var humidity = data.main.humidity;
  var wind = data.wind.speed;
  var row ='<tr>';
  row+= '<td width="10%">' +
  "<img src='http://openweathermap.org/img/w/"+data.weather[0].icon+".png'> "  + description + '</td>';
  row+= '<td width="40%">'+ "Temperature: " + temp+" "+ tempUnit +'</br>' +
        "Humidity: " + humidity+ " %"+ '</br>' +
        "Wind:"+wind+ " miles/hour "+
        '</td>';
  row+= '</tr>';
  currentWeather.append(row);
},
  error: function(){
    $('#errorMessages')
      .append($('<li>'))
      .attr({class: 'list-group-item list-group-item-danger'})
  .text('Error calling webservice or invalid zipcode is entered. Please try again');  }
  });
  }



  function monthName(month){
  var monthNames = ["January", "February", "March","April ","May","June","July","August ","September ","october","November","December"];
  return monthNames[month];
  }
