$(document).ready(function () {
loaddvds();
// $('#SearchedDvd').hide();
$('#searchButton').click(function(event){
  $('#errorMessages').empty().removeClass();
searchDvd();
});

$('#createDvd').click(function(event){
  $('#errorMessages').empty().removeClass();
$('#DvdTableDiv').hide();
// hideEditForm();
$('#newDvdForm').show();
});


$('#add-button').click(function(event){
  $('#errorMessages').empty().removeClass();
// check validation errors
var haveValidationErrors = checkAndDisplayValidationErrors($('#add-form').find('input'));
if (haveValidationErrors ) {
  return false;
  // if there are any validation errors below ajax call will not be made
}
// check validation error ends here

var year = $('#add-release-year').val();
var title= $('#add-dvd-title').val();

if (year.length!=4 ) {
  $('#errorMessages')
    .append($('<li>'))
    .attr({class: 'list-group-item list-group-item-danger'})
    .text('Please enter a 4-digit year');
} else if (title.length<1) {
  $('#errorMessages')
    .append($('<li>'))
    .attr({class: 'list-group-item list-group-item-danger'})
    .text('Please enter a title for the DVD');
} else {




$.ajax({
  type: 'POST',
  url:'http://localhost:8080/dvd',
  data: JSON.stringify({
  title: $('#add-dvd-title').val(),
releaseYear: $('#add-release-year').val(),
director: $('#add-Director').val(),
rating: $('#add-rating').val(),
notes: $('#add-notes').val()
  }),

headers: {
  'Accept': 'application/JSON',
  'Content-type': 'application/JSON',
  },
'dataType': 'JSON',
success: function() {
$('#errorMessages').empty().removeClass();
$('#add-dvd-title').val('');
$('#add-release-year').val('');
$('#add-Director').val('');
$('#add-notes').val('');
$('#add-rating');
loaddvds();
},
error: function(){
  $('#errorMessages')
    .append($('<li>'))
    .attr({class: 'list-group-item list-group-item-danger'})
    .text('Error calling webservice. Please try again latter.');
}
//$.ajax ends here
})
//else if ends here
}
// $('#add-button').click(function(even)         click ends here
});



 $('#edit-button').click(function(event){
 $('#errorMessages').empty().removeClass();
   // check validation errors
   var haveValidationErrors = checkAndDisplayValidationErrors($('#edit-form').find('input'));
   if (haveValidationErrors) {
     return false;
     // if there are any validation errors below ajax call will not be made
   }
   // check validation error ends here

   var year = $('#edit-release-year').val();
   var title= $('#edit-Dvd-title').val();
var dvdId = $('#edit-dvd-id').val();
console.log(dvdId);
   if (year.length!=4 ) {
     $('#errorMessages')
       .append($('<li>'))
       .attr({class: 'list-group-item list-group-item-danger'})
       .text('Please enter a 4-digit year');
   } else if (title.length<1) {
     $('#errorMessages')
       .append($('<li>'))
       .attr({class: 'list-group-item list-group-item-danger'})
       .text('Please enter a title for the DVD');
   } else {


$.ajax({
type: 'PUT',
url: 'http://localhost:8080/dvd/' + $('#edit-dvd-id').val(),
data: JSON.stringify({
dvdId: $('#edit-dvd-id').val(),
title: $('#edit-Dvd-title').val(),
releaseYear: $('#edit-release-year').val(),
director: $('#edit-director').val(),
notes: $('#edit-notes').val(),
rating: $('#edit-rating').val()
}),

headers: {
  'Accept': 'application/JSON',
  'Content-type': 'application/JSON',
  },
  dataType: 'JSON',

  success: function() {
$('#errorMessages').empty().removeClass();
hideEditForm();
loaddvds();
  },
  error: function(){
    $('#errorMessages')
      .append($('<li>'))
      .attr({class: 'list-group-item list-group-item-danger'})
      .text('Error calling webservice. Please try again latter.');
  }
})
// else ends here
}
// edit button click ends here
});
// document.ready() ends here
});



// function outside document.ready()
function loaddvds(){
  $('#errorMessages').empty().removeClass();
clearDvdTable();
  var contentRows = $('#contentRows');
  $.ajax({
    type: 'GET',
    url:'http://localhost:8080/dvds',

success: function(dvdArray) {
      $.each(dvdArray, function(index, dvd){
        var title= dvd.title;
        var releaseDate= dvd.releaseYear;
        var director= dvd.director;
        var rating= dvd.rating;
        var dvdId= dvd.dvdId;
        // table row variable
        var row ='<tr>';
        row +='<td><a onclick="showDvd('+ dvdId +')">'+title+'</a></td>';
            // row +='<td>'+ title+'</td>';
            row +='<td>'+ releaseDate+'</td>';
            row +='<td>'+director+'</td>';
            row +='<td>'+rating+'</td>';
            row +='<td><a onclick="showEditForm('+ dvdId +')">Edit</a></td>';
            row +='<td><a onclick="deleteDvd('+ dvdId +')">Delete</a></td>';
            row +='</tr>';
           contentRows.append(row);
      });

},

error: function(error){
$('#errorMessages')
  .append($('<li>'))
  .attr({class: 'list-group-item list-group-item-danger'})
  .text('Error calling webservice. Please try again latter.');
}
// $.ajax ends here
  });
  //function loaddvds ends here
$('#SearchedDvd').hide();
}

function clearDvdTable() {
  $('#contentRows').empty();
};



function showEditForm(dvdId) {
$('#errorMessages').empty().removeClass();
$('#edit-Dvd').show();
//getting information for a contact to be edited
$.ajax({
type: 'GET',
url: 'http://localhost:8080/dvd/'+ dvdId,


success: function (data, status) {
  $('#edit-Dvd-title').val(data.title);
  $('#edit-release-year').val(data.releaseYear);
  $('#edit-director').val(data.director);
  $('#edit-notes').val(data.notes);
  $('#edit-rating').val(data.rating);
  $('#edit-dvd-id').val(data.dvdId);
},
error: function() {
  $('#errorMessages')
    .append($('<li>'))
    .attr({class: 'list-group-item list-group-item-danger'})
    .text('Error calling webservice. Please try again latter.');
}
// $.ajax ends here
})

$('#DvdTableDiv').hide();
$('#editFormDiv').show();
$('#edit-button').show();

// function showEditForm ends here
}

function hideEditForm(){
$('#errorMessages').empty().removeClass();

$('#edit-Dvd-title').val('');
$('#edit-release-year').val('');
$('#edit-director').val('');
$('#edit-notes').val('');
$('#edit-rating').val('');

$('#editFormDiv').hide();
$('#DvdTableDiv').show();
}

function deleteDvd(dvdId) {
if (confirm ("Are you sure you want to delete this Dvd from your collection?")) {
$.ajax({
  type: 'DELETE',
  url: 'http://localhost:8080/dvd/'+ dvdId,
  success: function(){
    loaddvds();
  }
});
}
}


// error validation

function checkAndDisplayValidationErrors(input) {
$('#errorMessages').empty().removeClass();

var errorMessages=[];
input.each(function(){
if (!this.validity.valid) {
  var errorField =$('label[for= '+ this.id+'] ').text();
  errorMessages.push(errorField + ' ' +  this.validationMessage);
  }
});

if (errorMessages.length>0) {
  $.each(errorMessages, function (index, message) {
    $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
});

// return true indicating that there wre errors
} else {
  // return false, indicating that there were no errorMessages
  return false;
}
// function checkAndDisplayValidationErrors ends here
}


function  cancelAddDvd(){
  $('#errorMessages').empty().removeClass();
  $('#add-dvd-title').val('');
  $('#add-release-year').val('');
  $('#add-Director').val('');
  $('#add-notes').val('');
  $('#add-rating').val('');
  $('#newDvdForm').hide();
$('#DvdTableDiv').show();
  loaddvds();
}




// search DVD's

function searchDvd(){
$('#DvdTableDiv').hide();
$('#errorMessages').empty().removeClass();
$('#searchRows').empty();
clearDvdTable();


var searchCriteria = $('#searchCategory').val();
var SearchTerm = $('#searchTerm').val();
$('#SearchedDvd').show();
var searchRows = $('#searchRows');
if (searchCriteria.length>12 || SearchTerm.length<3) {
  $('#errorMessages')
    .append($('<li>'))
    .attr({class: 'list-group-item list-group-item-danger'})
    .text('Both Search Category and Search Term are required'
   );
} else {
  $.ajax({
    type: 'GET',
    url:'http://localhost:8080/dvds/'+searchCriteria+'/'+SearchTerm,
success: function(dvdArray) {
      $.each(dvdArray, function(index, dvd){
        var title= dvd.title;
        var releaseDate= dvd.releaseYear;
        var director= dvd.director;
        var rating= dvd.rating;
        var dvdId= dvd.dvdId;
        console.log(title);
        // table row variable
        var row ='<tr>';
            row +='<td><a onclick='+ title+'</td>';
            row +='<td>'+ releaseDate+'</td>';
            row +='<td>'+director+'</td>';
            row +='<td>'+rating+'</td>';
            row +='<td><a onclick="showEditForm('+ dvdId +')">Edit</a></td>';
            row +='<td><a onclick="deleteDvd('+ dvdId +')">Delete</a></td>';
            row +='</tr>';
           searchRows.append(row);
      });
},

error: function(error){
$('#errorMessages')
  .append($('<li>'))
  .attr({class: 'list-group-item list-group-item-danger'})
  .text('Error calling webservice -  '+

'try a different search criteria or search term - '+
   'Please try again latter.');
}
// $.ajax ends here
  });

}
}












function showDvd(dvdId) {
$('#errorMessages').empty().removeClass();

//getting information for a contact to be edited
$.ajax({
type: 'GET',
url: 'http://localhost:8080/dvd/'+ dvdId,


success: function (data, status) {
  $('#edit-Dvd-title').val(data.title);
  $('#edit-release-year').val(data.releaseYear);
  $('#edit-director').val(data.director);
  $('#edit-notes').val(data.notes);
  $('#edit-rating').val(data.rating);
  $('#edit-dvd-id').val(data.contactId);
},
error: function() {
  $('#errorMessages')
    .append($('<li>'))
    .attr({class: 'list-group-item list-group-item-danger'})
    .text('Error calling webservice. Please try again latter.');
}
// $.ajax ends here
})

$('#DvdTableDiv').hide();
$('#editFormDiv').show();
$('#edit-button').hide();
$('#edit-Dvd').hide();

// function showEditForm ends here
}
