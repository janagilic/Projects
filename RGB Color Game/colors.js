// Declare the variables you want here
var square = document.querySelectorAll(".square");
var displayRGB = document.getElementById("colorDisplay");
var header = document.querySelector("h1");
var easybtn = document.querySelector(".mode");
//var hardbtn = document.querySelector(".mode");
var newColors = document.getElementById("reset");
var messageDisplay = document.getElementById("message");
var pickedColor;
var colorToGuess;
var colorToGuessIndex;
// This calls the init function when the page first loads
init();

//choosing the mode for the game either easy or hard and showing which one is selected
var mode = document.querySelectorAll(".mode");
for(var i = 0; i < mode.length; i++){
    mode[i].addEventListener("click", function(){
        mode[0].classList.remove("selected");
        mode[1].classList.remove("selected");
        this.classList.add("selected");
        if(this.textContent === "Easy"){
            var chosenMode = this.textContent;
            easy();
        }
        else{
            chosenMode = this.textContent;
            hard();
        }
        init();
    })
}


newColors.addEventListener('click', init);
//hardbtn.addEventListener('click', hard);
easybtn.addEventListener('click', easy);

// Init is just anything you want to have happen when the page loads
function init(){
    setupGame();
    // Add your setup code/functions here
    function rndColor(){
        let r = Math.floor(Math.random()*(256));
        let g = Math.floor(Math.random()*(256));
        let b = Math.floor(Math.random()*(256));
        return "rgb(" + r + "," + g + "," + b + ")";
    }
    
    //an array to store all the colors that have been added to the squares so we can pick one random color to guess it
    var randomColors = [];

    //loop through all the squares
    for(var i = 0; i < square.length; i++){

        //assign random colors to squares
        square[i].style.backgroundColor = rndColor();

        //fill the array with rgb colors that are given in the current website
        randomColors[i] = square[i].style.backgroundColor;
    }

    //choosing random color to guess from the array that we filled with rgb colors
    colorToGuessIndex = Math.floor(Math.random() * randomColors.length);

    //remembering the index of the random color
    colorToGuess = randomColors[colorToGuessIndex];

    //displaying the color we have to guess
    displayRGB.innerHTML = colorToGuess;
    
}

//this function does the comparing of the colors we click
function setupGame(){

    newColors.textContent = "New Colors";

    messageDisplay.textContent = " ";

    //loop through all the squares
    for(var i = 0; i < square.length; i++){

        //when we click on a square it is going to choose that color and store into variable pickedColor
        square[i].addEventListener('click', function(){
            var pickedColor = this.style.backgroundColor;

            //comparing color that we clicked on with the color that is displayed in the header
            if(pickedColor == colorToGuess){

                //if its correct display correct, change header background and all the squares into picked color, and ask to play again
                messageDisplay.textContent = "Correct!";
                header.style.backgroundColor = pickedColor;
                newColors.textContent = "Play again?";
                changeAllSquares(pickedColor);
            }
            else{
                
                //if its false, remove the guessed square and display try again
                this.style.backgroundColor = '#232323';
                messageDisplay.textContent = "Try Again";
            }
        });
    }
}

//changing all squares to guessed color
function changeAllSquares(pickedColor){
    for(var i = 0; i < square.length; i++){
        square[i].style.backgroundColor = pickedColor;
    }
}

//changing all squares into guessed color but when in easy mode
function changeAllSquaresEasy(pickedColor){
    for(var i = 0; i < square.length-3; i++){
        square[i].style.backgroundColor = pickedColor;

    }
    for(var i = 3; i < square.length; i++){
        square[i].style.backgroundColor = '#232323';
    }
}

//hard mode
function hard(){
    init();
    newColors.addEventListener("click", function(){
        hard();
    });
}

//easy mode
function easy(){
    function rndColor(){
        let r = Math.floor(Math.random()* 256);
        let g = Math.floor(Math.random()* 256);
        let b = Math.floor(Math.random()* 256);
        return "rgb(" + r + "," + g + "," + b + ")";
    }

    var randomColors = [];
    //console.log(square);
    for(var i = 0; i < square.length-3; i++){
        //assign random colors to squares
        square[i].style.backgroundColor = rndColor();
        //fill the array with rgb colors that are given in the current website
        randomColors[i] = square[i].style.backgroundColor;
    }

    colorToGuessIndex = Math.floor(Math.random() * randomColors.length);
    colorToGuess = randomColors[colorToGuessIndex];
    displayRGB.innerHTML = colorToGuess;

    messageDisplay.textContent = " ";

    //only looping through first three squares since that is the easy mode 
    for(var i = 0; i < square.length - 3; i++){

        square[i].addEventListener("click", function(){
            var pickedColor = this.style.backgroundColor;

            if(pickedColor == colorToGuess){
                messageDisplay.textContent = "Correct!";
                header.style.backgroundColor = pickedColor;
                newColors.textContent = "Play again?";
                changeAllSquaresEasy(pickedColor);
            }

            else{
                this.style.backgroundColor = '#232323';
                messageDisplay.textContent = "Try Again";
            }
        });
    }

    //other 3 squares are going to be invisible and we're not going to be working with them
    for(var i = 3; i < square.length; i++){
        square[i].style.backgroundColor = '#232323';
    }

    newColors.addEventListener("click", function(){
        easy();
    });
    
}


// I STRONGLY suggest you wrap all your remaining code in functions
// In fact you probably won't get the app working if you don't


