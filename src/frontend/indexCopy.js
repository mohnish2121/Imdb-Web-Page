const prox = "http://localhost:8010/proxy/";

const url = `${prox}api/movies`;
const main = document.getElementById("main");
const producerUrl = `${prox}api/producers`;
const actorUrl = `${prox}api/actors`;
var eid ;

const postEditBtn = document.getElementById("edit");

const getAllProducers = (producer)=> {
    const producerSelection = document.getElementById("producer-section")
        
    producer.forEach(data => {
       ({producer_id , name } = data);

        const producer = document.createElement("option");
        producer.value = producer_id ;
        producer.innerHTML = name;
        producerSelection.appendChild(producer);
    })
}

const getAllActors = (actors) => {
    const actorSelection = document.getElementById("actor-section")
        
    actors.forEach(data => {
        ({actor_id , name } = data);

        const actor = document.createElement("option");
        actor.value = actor_id ;
        actor.innerHTML = name;
        actorSelection.appendChild(actor);
    })
}

const getAllMovies = (movies) => {
    movies.forEach((movie) => {
        ({ movie_id,name, poster, plot , year, producer,actorsList} = movie);
            console.log(movie_id);

           const div = document.createElement("div");
           div.classList.add("movie-container");
           
           
           const img = document.createElement("img");
           img.classList.add("movie-poster");
           img.setAttribute("src", poster);

           const movieName = document.createElement("h2");
           movieName.classList.add("movie-name");
           movieName.innerHTML = name;
           
           const movieYear = document.createElement("h4");
           movieYear.classList.add("movie-year");
           movieYear.innerHTML = year;


           const moviePlot= document.createElement("p");
           moviePlot.classList.add("movie-plot");
            moviePlot.innerHTML = plot;

            const produc = document.createElement("p")
            produc.innerHTML = "producer : " + producer.name;

            const actorsBlock = document.createElement("p");
            actorsBlock.id = "actorsBlock";
            const actorlabel = document.createElement("p");
            actorlabel.innerHTML = "Actors : ";
            actorsBlock.appendChild(actorlabel)

            actorsList.forEach(actor => {
                const actorBlock = document.createElement("p");
                actorBlock.innerHTML = actor.name + ", " ;
                actorsBlock.appendChild(actorBlock);
            })


            const delBtn = document.createElement("button");
            delBtn.innerHTML = "Delete";
            delBtn.id= movie_id;
            delBtn.classList.add("delBtn")

            const editBtn = document.createElement("button");
            editBtn.innerHTML = "edit info"
            editBtn.setAttribute("edit-id", movie_id);
            editBtn.classList.add("editBtn");
            editBtn.setAttribute("type","button");

           div.appendChild(img);
           div.appendChild(movieName);
           div.appendChild(movieYear);
           div.appendChild(moviePlot);
           div.appendChild(produc);
           div.appendChild(actorsBlock);
           div.appendChild(delBtn);
           div.appendChild(editBtn);

           main.appendChild(div);

        })
}

window.addEventListener('load', ()=> {
    fetch(url).then(data => {
        return data.json();
    }).then(parsedata => {
        console.log(parsedata);
        
        ({actors, movies, producer} = parsedata);
        
        // fetch producer 
        
        getAllProducers(producer);

        // fetch movies 
        getAllMovies(movies);

        // fetch actor 
        
        getAllActors(actors);

    })

})





// Adding New Movie

const submitBtn = document.getElementById("submit-btn");

submitBtn.addEventListener("click", ()=> {

    const nameInput = document.getElementById("name-input").value;
    const posterInput = document.getElementById("poster-input").value;
    const yearInput = document.getElementById("year-input").value;
    const plotInput = document.getElementById("plot-input").value;
    const producerId  = document.getElementById("producer-section").value;
    const actorSection = document.getElementById("actor-section")

    let arr = [...actorSection.options].filter(option => option.selected).map(option => option.value);
    let actorarr = [];
    arr.map(movieId => {
        actorarr.push({actor_id : movieId})
    })


    const newMovie = {
        "name" : nameInput,
        "year" : yearInput,
        "plot" : plotInput,
        "poster" : posterInput,
        "producer": { 
            producer_id : producerId
        },
        "actorsList": actorarr

    }

    addNewMovie(newMovie)
    
})

const addNewMovie = (movie) => {

    fetch(url, {
        headers : {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(movie)
    });

}


// Delete Section 
const mainClicked = (e) => {
    if(e.target.getAttribute("class") === "delBtn") {
        deleteMovie(e);
    }
    if(e.target.getAttribute("class") === "editBtn") {
        editInfo(e);
    }
}

const deleteMovie = (e) => {
        
        const id = e.target.id;
        fetch(`${url}/${id}`, {
            method: 'DELETE'
        }).then(() => {
            location.reload();
        })
    
}


// edit section 

const editInfo = (e) => {
        e.preventDefault();
        console.log(e.target.innerHTML);
        document.getElementById("edit").disabled = false;
        
        eid = e.target.getAttribute("edit-id");
 
}

main.addEventListener('click',mainClicked);


const changeInfo = (e) => {
    e.preventDefault();
    let newName = document.getElementById("name-edit").value
    let newPoster = document.getElementById("poster-edit").value
    let newYear = document.getElementById("year-edit").value
    let newPlot = document.getElementById("plot-edit").value
    const producerId  = document.getElementById("producer-section").value;
    const actorSection = document.getElementById("actor-section")
    let arr = [...actorSection.options].filter(option => option.selected).map(option => option.value);
    let actorarr = [];
    console.log(arr);
    arr.map(actorId => {
        actorarr.push({actor_id : actorId})

    })

    let movie =  {
        "name" : newName,
        "year" : newYear,
        "plot" : newPlot,
        "poster" : newPoster ,
        "producer": {
            producer_id : producerId
        },
        "actorsList" : actorarr
    }
    
    fetch(`${url}/${eid}`,{
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'PUT',
        body: JSON.stringify(movie)
    }).then(() => {
        location.reload();
        document.getElementById("edit").disabled = true;
    });

    return false;
}

postEditBtn.addEventListener("click",changeInfo);

////////////////

// Add new producer 

const producerAddBtn = document.getElementById("add-new-producer-btn");
producerAddBtn.addEventListener("click", ()=> {
    const producerName = document.getElementById("new-producer-name").value
    const producerSex = document.getElementById("new-producer-sex").value
    const producerDob = document.getElementById("new-producer-dob").value
    const producerBio = document.getElementById("new-producer-bio").value

    const newProducer = {
        name : producerName,
        sex: producerSex,
        dob: producerDob,
        bio: producerBio
    }
    addNewProducer(newProducer);
})

const addNewProducer =(newProducer) =>{
    fetch(producerUrl, {
        headers : {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(newProducer)
    }).then(() => {
        location.reload();
    });
}


////////////////

// Add new actor

const actorAddBtn = document.getElementById("add-new-actor-btn");
actorAddBtn.addEventListener("click", ()=> {
    const actorName = document.getElementById("new-actor-name").value
    const actorSex = document.getElementById("new-actor-sex").value
    const actorDob = document.getElementById("new-actor-dob").value
    const actorBio = document.getElementById("new-actor-bio").value

    const newActor = {
        name : actorName,
        sex: actorSex,
        dob: actorDob,
        bio: actorBio
    }
    addNewActor(newActor);
})

const addNewActor =(newActor) =>{
    fetch(actorUrl, {
        headers : {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(newActor)
    }).then(() => {
        location.reload();
    });
}

