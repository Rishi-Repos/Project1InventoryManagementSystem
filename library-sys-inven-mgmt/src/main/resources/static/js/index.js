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
        
        const addLibraryForm = displayAddLibraryForm();

        // locate last row in table and add element after
        document.getElementById('trLib' + libraries.at(-1).id).after(addLibraryForm);
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

// add hover and click event listeners for every delete button, called by the 'DOMContentLoaded' event listener
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

// add hover and click event listeners for every edit button, called by the 'DOMContentLoaded' event listener
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

// add a row after last row in library table containing a form to add a library
function displayAddLibraryForm(){
    const addLibraryForm = document.createElement('tr');
    addLibraryForm.setAttribute('id','add-library-form-row')
    addLibraryForm.setAttribute('class','d-flex');
    addLibraryForm.innerHTML =                                    
        `<td class="col-4">
            <input id="new-library-name" name="new-library-name" class="form-control" type="text" placeholder="Enter Library Name">
        </td>
        <td class="col-4">
            <input id="new-library-location" name="new-library-location" class="form-control" type="text" placeholder="Enter Library Location">
        </td>
        <td class="col-2">
            <input id="new-library-maxCap" name="new-library-maxCap" class="form-control" type="number" placeholder="Enter Max Capacity">
        </td>
        <td class="col-2" style="justify-content: space-evenly;">
            <input id="save-library" name="save-button" type="submit" value="Save" class="btn btn-primary me-3" />    
            <button id="new-library-cancel-button" class="btn btn-danger">Cancel</button>
        </td>`;
    return addLibraryForm;
}

////////////////////////////////
//////// POST LIBRARY //////////
////////////////////////////////

document.getElementById('new-library-form').addEventListener('submit', (event) =>{
    event.preventDefault();
    let inputData = new FormData(document.getElementById('new-library-form'));
    let newLibraryDto = {
        name : inputData.get('new-library-name'),         
        location : inputData.get('new-library-location'),
        maxCap : inputData.get('new-library-maxCap')
    };
    postLibrary(newLibraryDto);
});

function postLibrary(newLibraryDto) {
    fetch(URL + '/libraries', {
        method : 'POST',
        headers : {
            'Content-Type' : 'application/json'         // make sure your server is expecting to receive JSON in the body
        },
        body : JSON.stringify(newLibraryDto)      // turns a js object into JSON
    })

    // convert JSON to js object
    .then((returnedData) => {
        return returnedData.json();
    })

    // add data to visible library table
    .then((newLibraryDto) => {
        addLibraryToTable(newLibraryDto);
        return newLibraryDto;
    })

    // remove the addLibraryForm row (no longer at the bottom)
    .then((newLibraryDto) => {
        document.getElementById('add-library-form-row').remove();
        return newLibraryDto;
    })

    // re-add the addLibraryForm row to the bottom
    .then((newLibraryDto) => {
        const addLibraryForm = displayAddLibraryForm();

        // locate last row in table and add element after
        document.getElementById('trLib' + newLibraryDto.id).after(addLibraryForm);
    })
    .catch((error) => {
        // handle all 400 and 500 status code responses
        console.error(error);
    });
}

function editLibraryRow(editElement) {
    fetch(URL + '/libraries/dtos', {
        method : 'PUT'      
    })
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
    tr.appendChild(name);
    tr.appendChild(location);
    tr.appendChild(currCap);
    tr.appendChild(buttonColumn);
    buttonColumn.appendChild(buttonContainer);
    buttonContainer.appendChild(editImg);
    buttonContainer.appendChild(deleteImg);

    // add row to library table
    document.getElementById('library-table-body').appendChild(tr);

    // append to array
    allLibraries.push(newLibraryDto);
}