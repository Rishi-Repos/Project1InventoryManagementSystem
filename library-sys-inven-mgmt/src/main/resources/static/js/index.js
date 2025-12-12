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
    // add double-click event listener for every row in library table
    .then(() => {
        addEditEventListeners();
        addDeleteEventListeners();
        addLibraryRowEventListeners();
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

    // add/re-add all event listeners for rows, edit, and delete buttons
    addEditEventListeners();
    addDeleteEventListeners();
    addLibraryRowEventListeners();

    // append to array
    allLibraries.push(newLibraryDto);
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
            <input id="save-library" name="save-button" type="submit" value="Save" class="btn btn-primary me-3"/>    
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

// send POST request to server and update view
function postLibrary(newLibraryDto) {
    fetch(URL + '/libraries', {
        method : 'POST',
        headers : {
            'Content-Type' : 'application/json'         
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

////////////////////////////////
//////// EDIT LIBRARY //////////
////////////////////////////////

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

// called when editImg is clicked
function editLibraryRow(editElement) {
    
    // identify the row
    // the replace method here removes everything but numbers from the string
    let libraryDto = allLibraries.find(library => library.id === parseInt(editElement.id.replace(/\D/g, '')));    

    // pre-populate edit form with current values
    document.getElementById('edit-library-name').setAttribute('value', libraryDto.name);
    document.getElementById('edit-library-location').setAttribute('value', libraryDto.location);
    document.getElementById('edit-library-maxCap').setAttribute('value', libraryDto.maxCap);
    document.getElementById('edit-library').setAttribute('data-id', libraryDto.id);

    // show form
    document.getElementById('edit-library-form').style.display = 'block';
    document.getElementById('delete-library-form').style.display = 'none';
}

// called when edit form is submitted
document.getElementById('edit-library-form').addEventListener('submit', (event) =>{
    event.preventDefault();
    let inputData = new FormData(document.getElementById('edit-library-form'));
    let editedLibraryDto = {
        id : document.getElementById('edit-library').dataset.id,
        name : inputData.get('edit-library-name'),         
        location : inputData.get('edit-library-location'),
        maxCap : inputData.get('edit-library-maxCap')
    };
    putLibrary(editedLibraryDto);
});

// send PUT request to server
function putLibrary(editedLibraryDto) {
    
    fetch(URL + '/libraries', {
        method : 'PUT',
        headers : {
            'Content-Type' : 'application/json'         
        },
        body : JSON.stringify(editedLibraryDto)      // turns a js object into JSON
    })

    // convert JSON to js object
    .then((returnedData) => {
        return returnedData.json();
    })

    // add data to visible library table
    .then((editedLibraryDto) => {
        updateLibraryInTable(editedLibraryDto);
    })
    .catch((error) => {
        // handle all 400 and 500 status code responses
        console.error(error);
    });
}

function updateLibraryInTable(editedLibraryDto) {
    
    document.getElementById('trLib' + editedLibraryDto.id).innerHTML = `
    <td class="col-4">${editedLibraryDto.name}</td>
    <td class="col-4">${editedLibraryDto.location}</td>
    <td class="col-2">${editedLibraryDto.maxCap}</td>
    <td class="col-2">
        <div class="edit-delete-container">
            <img src="edit.png" class="edit-button" title="Edit Row" id="editLib${editedLibraryDto.id}">
            <img src="delete.png" class="delete-button" title="Delete Row" id="deleteLib${editedLibraryDto.id}">
        </div>
    </td>
    `;

    //re-add event listeners after re-adding edit and delete buttons

    addEditEventListeners();
    addDeleteEventListeners();

    // hide form
    document.getElementById('edit-library-form').style.display = 'none';

    // reset form
    document.getElementById('edit-library-form').reset();
}

////////////////////////////////
//////// DELETE LIBRARY ////////
////////////////////////////////

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

function deleteLibraryRow(deleteElement) {
    
    // identify the row
    // the replace method here removes everything but numbers from the string
    let libraryDto = allLibraries.find(library => library.id === parseInt(deleteElement.id.replace(/\D/g, '')));    

    // pre-populate delete message with current values
    document.getElementById('delete-library-name').setAttribute('value', libraryDto.name);
    document.getElementById('delete-library-location').setAttribute('value', libraryDto.location);
    document.getElementById('delete-library-maxCap').setAttribute('value', libraryDto.maxCap);
    document.getElementById('delete-library').setAttribute('data-id', libraryDto.id);

    // show form
    document.getElementById('edit-library-form').style.display = 'none';
    document.getElementById('delete-library-form').style.display = 'block';
}

// called when cancel button is clicked
document.getElementById('cancel-delete-button').addEventListener('click', (event) =>{
    event.preventDefault();
    document.getElementById('delete-library-form').style.display = 'none';
});

// called when delete message is confirmed
document.getElementById('delete-library-form').addEventListener('submit', (event) =>{
    event.preventDefault();
    let inputData = new FormData(document.getElementById('delete-library-form'));
    let deleteLibraryDto = {
        id : document.getElementById('delete-library').dataset.id,
        name : inputData.get('delete-library-name'),         
        location : inputData.get('delete-library-location'),
        maxCap : inputData.get('delete-library-maxCap')
    };
    deleteLibrary(deleteLibraryDto);
});

// send DELETE request to server
function deleteLibrary(deleteLibraryDto) {
    
    fetch(URL + '/libraries', {
        method : 'DELETE',
        headers : {
            'Content-Type' : 'application/json'         
        },
        body : JSON.stringify(deleteLibraryDto)      // turns a js object into JSON
    })

    // add data to visible library table
    .then(() => {
        deleteLibraryInTable(deleteLibraryDto);
    })
    .catch((error) => {
        // handle all 400 and 500 status code responses
        console.error(error);
    });
}

// delete library in the view
function deleteLibraryInTable(deleteLibraryDto) {
    document.getElementById('trLib' + deleteLibraryDto.id).remove();

    // hide form
    document.getElementById('delete-library-form').style.display = 'none';

    // reset form
    document.getElementById('delete-library-form').reset();
}

/////////////////////////////
////// OPEN BOOK TABLE //////
/////////////////////////////

function addLibraryRowEventListeners() {
    const rows = document.querySelectorAll('tr');
    rows.forEach(row => {
        row.addEventListener('dblclick', () => {
            openBookTable(row);
        });
    });
}

function openBookTable(row) {
    
    fetch(URL + '/books/library/' + parseInt(row.id.replace(/\D/g, '')), {
        method : 'GET'      
    })
    .then((returnedData) => {
        return returnedData.json();
    })
    .then((booksinLib) => {
        booksinLib.forEach(newBookDto => {
            addBookToTable(newBookDto);
        });
    })
    .catch((error) => {
        // handle all 400 and 500 status code responses
        console.error(error);
    })
    
    //show table
    document.getElementById('new-book-form').style.display = 'block';

}

function addBookToTable(newBookDto) {
    let tr = document.createElement('tr');
    tr.setAttribute('class','d-flex');
    tr.innerHTML = `
    <td class="col-2">${newBookDto.title.title}</td>
    <td class="col-2">${newBookDto.title.authors[0].firstName + " " 
        + newBookDto.title.authors[0].middleName + " "
        + newBookDto.title.authors[0].lastName}</td>
    <td class="col-2">${newBookDto.title.genre}</td>
    <td class="col-2">${newBookDto.title.yearPublished}</td>
    <td class="col-2">${newBookDto.status}</td>
    <td class="col-2">
        <div class="edit-delete-container">
            <img src="edit.png" class="edit-button" title="Edit Row" id="editBook${newBookDto.id}">
            <img src="delete.png" class="delete-button" title="Delete Row" id="deleteBook${newBookDto.id}">
        </div>
    </td>
    `;
    document.getElementById('book-table-header').after(tr);
    
}

// called when cancel button is clicked
document.getElementById('close-books-button').addEventListener('click', (event) =>{
    event.preventDefault();
    document.getElementById('new-book-form').style.display = 'none';
});

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



