const URL = 'http://localhost:8080';
let allBooks = [];
let allLibraries = [];

//when page is loaded/refreshed
document.addEventListener('DOMContentLoaded', getAllLibsRequest());

async function getAllLibsRequest() {
    let returnedData = await fetch(URL + '/libraries/dtos', {
        method : 'GET'      
    });
    let libraries = await returnedData.json();

    libraries.forEach(newLibraryDto => {
        addLibraryToTable(newLibraryDto);
    });
}

async function getAllBooksByLibRequest(libraryDto) {
    let returnedData = await fetch(URL + '/books', {
        method : 'GET',
        headers : {
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify(libraryDto)      
    });
    let books = await returnedData.json();
    return books;
}

function addLibraryToTable(newLibraryDto) {

    let tr = document.createElement('tr');
    let name = document.createElement('td');
    let location = document.createElement('td');
    let currCap = document.createElement('td');
    let buttonColumn = document.createElement('td');
    let buttonContainer = document.createElement('div');  //Edit and delete buttons here
    let editImg = document.createElement('img');
    let deleteImg = document.createElement('img');

    name.innerText = newLibraryDto.name;
    location.innerText = newLibraryDto.location;
    
    //calculate current capacity
    currCap.innerText = '' + (parseInt(getAllBooksByLibRequest(newLibraryDto).length) / parseInt(newLibraryDto.maxCap));

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