const URL = 'http://localhost:8080';
let allBooks = [];
let allLibraries = [];

// when page is loaded/refreshed:
document.addEventListener('DOMContentLoaded', () => {
    
    // get all libraryDto's and display them
    fetch(URL + '/libraries/dtos', {
        method : 'GET'      
    })

    // convert JSON arary to js array of objects
    .then((returnedData) => {
        return returnedData.json();
    })

    // for each object, add data to visible library table
    .then((libraries) => {
        libraries.forEach(newLibraryDto => {
            addLibraryToTable(newLibraryDto);
        });
        return libraries;
    })

    /* after all libraries added to table, add another row 
        at the bottom of the table for adding a new Library */
    .then((libraries) => {
        
        const addLibRow = document.createElement('tr');
        addLibRow.innerHTML = '<td></td><td></td><td></td>';

        // locate last row in table and add element after
        document.getElementById('trLib' + libraries.at(-1).id).after() 
    })

    // add hover and click event listeners for every edit and delete button
    .then(() => {
        addEditEventListeners();
        addDeleteEventListeners();
    })
    .catch((error) => {
        // handle all 400 and 500 status code responses
        console.error(error);
    })
});

function addDeleteEventListeners() {
    const elements = document.querySelectorAll('.delete-button');
        elements.forEach(element => {
            element.addEventListener('mouseenter', () =>{
                element.style.filter = 'invert(100%) hue-rotate(120deg)';
            })
            element.addEventListener('mouseleave', () =>{
                element.style.filter = 'invert(0%) hue-rotate(0deg)';
            })
            element.addEventListener('click', () => {
                deleteLibraryRow(element);
            });
        });
}

function addEditEventListeners() {
    const elements = document.querySelectorAll('.edit-button');
        elements.forEach(element => {
            element.addEventListener('mouseenter', () =>{
                element.style.filter = 'saturate(800%)';
            })
            element.addEventListener('mouseleave', () =>{
                element.style.filter = 'saturate(100%)';
            })
            element.addEventListener('click', () => {
                editLibraryRow(element);
            });
        });
}

function editLibraryRow(editElement) {
    console.log(editElement);
}

function deleteLibraryRow(deleteElement) {
    console.log(deleteElement);
}

// when click on "Dashboard":
document.getElementById('dashboard-nav').addEventListener('click',() =>{
    document.getElementById('dashboard').style.display = 'block';
});

// when click on "Advanced Search":
document.getElementById('adv-search-nav').addEventListener('click',() =>{
    document.getElementById('dashboard').style.display = 'none';
});

document.getElementById('adv-search-nav').addEventListener('click',() =>{
    document.getElementById('dashboard').style.display = 'none';
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
    // apply flex to the container for the buttons
    buttonContainer.setAttribute('class','edit-delete-container')

    // linking source files
    editImg.setAttribute('src','edit.png');
    deleteImg.setAttribute('src','delete.png');

    // apply styling to edit and delete images
    editImg.setAttribute('class','edit-button');
    deleteImg.setAttribute('class','delete-button');

    // add tooltips to each image
    editImg.setAttribute('title','Edit Row');
    deleteImg.setAttribute('title','Delete Row');

    // add row id unique for library table
    tr.setAttribute('id', 'trLib' + newLibraryDto.id);
    editImg.setAttribute('id', 'editLib' + newLibraryDto.id);
    deleteImg.setAttribute('id', 'deleteLib' + newLibraryDto.id);
    // manage spacing of columns
    tr.setAttribute('class','d-flex');
    buttonColumn.setAttribute('class','col-2');
    name.setAttribute('class','col-4');
    location.setAttribute('class','col-4');
    currCap.setAttribute('class','col-2');

    // build row structure
    tr.appendChild(buttonColumn);
    tr.appendChild(name);
    tr.appendChild(location);
    tr.appendChild(currCap);
    buttonColumn.appendChild(buttonContainer);
    buttonContainer.appendChild(editImg);
    buttonContainer.appendChild(deleteImg);

    // add row to library table
    document.getElementById('library-table-body').appendChild(tr);

    // append to array
    allLibraries.push(newLibraryDto);
}