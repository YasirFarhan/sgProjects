 /* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    // button click handler
    $('#search-button').click(function (event) {
//        url: 'search/characters',
        $.ajax({
            type: 'POST',
            url: 'search/characters',
            data: JSON.stringify({
                CharacterName: $('#search-character-name').val(),
                CharacterType: $('#search-Character-Type').val(),
                SuperPower: $('#search-Super-Power').val(),
                OrganizationName: $('#search-Organization-Name').val(),
                LocationName: $('#search-Location-Name').val(),
                SightingTimeStamp: $('#search-Sighting-Date').val(),
                SightingTime: $('#search-Sighting-Time').val()

            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (data) {
// clear error message
                $('#errorMessage').empty();
                fillCharacterTable(data);
            },
            error: function () {
                $('#errorMessage').empty();
                $('#errorMessage')
                        .append($('<li>')
                                .attr({class: 'list-group-item list-group-item-danger'})
                                .text('Error calling web service.  Please try again later.'));
            }

// ajax ends here
        });
// on click function ends here 
    });
// document.ready ends here 
});


// function to fill search results
function fillCharacterTable(data) {
    clearCharacterTable();
    var contentRows = $('#contentRows');
    console.log(data);

    $.each(data, function (index, characterDetails) {
        var characterName = characterDetails.characterName;
        var characterType = characterDetails.characterType;
        var superPower = characterDetails.superPower;
        var characterDescription = characterDetails.characterDescription;
        var characterOrganization = characterDetails.characterOrganization;
        var sightingLocations = characterDetails.sightingLocations;
        var characterOrganization = characterDetails.characterOrganization;
        var row = '<tr>';
        row += '<td>' + characterName + '</td>';
        row += '<td>' + characterType + '</td>';
        row += '<td>' + superPower + '</td>';
        // organization
        row += '<td>';
        $.each(characterOrganization, function (index, org) {
            row += org.orgName + '</br>';
        });
        +'</td>';

        row += '<td>';
        $.each(sightingLocations, function (index, sl) {
            row += sl.locationName + '</br>';
        });
        +'</td>';

        row += '</tr>';
        contentRows.append(row);
    });
}



function clearCharacterTable() {
    $('#contentRows').empty();
}