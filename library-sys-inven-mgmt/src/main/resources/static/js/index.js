const URL = 'http://localhost:8080';
let allBooks = [];
let allLibraries = [];

//when page is loaded/refreshed
document.addEventListener('DOMContentLoaded', () => {
    fetch(URL + '/libraries/dtos', {
        method : 'GET'      
    })
    .then((returnedData) => {
        return returnedData.json();
    })
    .then((libraries) => {
        libraries.forEach(newLibraryDto => {
            addLibraryToTable(newLibraryDto);
        });

        // add a row at the bottom of the table for adding a new Library
        const addLibRow = document.createElement('tr');
        addLibRow.innerHTML = '<td></td><td></td><td></td>';
        document.getElementById('tr'+libraries.at(-1).id).after() 
    })
    .catch((error) => {
        // handle all 400 and 500 status code responses
        console.error(error);
    })
});

function addLibraryToTable(newLibraryDto) {

    let tr = document.createElement('tr');
    let name = document.createElement('td');
    let location = document.createElement('td');
    let currCap = document.createElement('td');
    let buttonColumn = document.createElement('td');
    let buttonContainer = document.createElement('div');  // Edit and delete buttons live here
    let editImg = document.createElement('img');
    let deleteImg = document.createElement('img');

    name.innerText = newLibraryDto.name;
    location.innerText = newLibraryDto.location;

    // calculate and populate current capacity
    fetch(URL + '/books/library/' + newLibraryDto.id, {
        method : 'GET'      
    })
    .then((returnedData) => {
        return returnedData.json();
    })
    .then((booksinLib) => {
        currCap.innerText = booksinLib.length + ' / ' + newLibraryDto.maxCap;
    })
    .catch((error) => {
        // handle all 400 and 500 status code responses
        console.error(error);
    })
    
    buttonContainer.setAttribute('class','edit-delete-container')
    editImg.setAttribute('src','edit.png');
    deleteImg.setAttribute('src','delete.png');
    editImg.setAttribute('class','edit-delete-buttons');
    deleteImg.setAttribute('class','edit-delete-buttons');
    tr.setAttribute('id', 'tr' + newLibraryDto.id);
    tr.setAttribute('class','d-flex');
    buttonColumn.setAttribute('class','col-2');
    name.setAttribute('class','col-4');
    location.setAttribute('class','col-4');
    currCap.setAttribute('class','col-2');

    tr.appendChild(buttonColumn);
    tr.appendChild(name);
    tr.appendChild(location);
    tr.appendChild(currCap);
    buttonColumn.appendChild(buttonContainer);
    buttonContainer.appendChild(editImg);
    buttonContainer.appendChild(deleteImg);

    document.getElementById('library-table-body').appendChild(tr);

    allLibraries.push(newLibraryDto);
}