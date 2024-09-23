# Gilded Rose Inventory Management prooject
This project has a frontend and a backend.

Backend project is created with Spring

Clone the repository locally using this link: https://github.com/joseway04/gildedrose.git

## Frontend 
The frontedn app is created with Angular.So Angular, Typescript an NodeJS are required

Change directory to [path]\[to]\frontend\gilederose. Make sure to replace [path]\[to] with the path the project was cloned to.

Run npm to install dependencies.

Run ng serve

Open a browser window and type localhost:4200, assuming Anular is running on the default port.

##Backend

Open your text editor, such as Eclipse/IntelliJ

Navigate to [path]\[to]\backend\gildedrose. Make sure to replace [path]\[to] with the path the project was cloned to.

Import an existing Maven project

Run the project as Spring Boot Application. 

The project is running on the default port of 8080.

Open Postman then send a Post request to the following endpoint: http://localhost:8080/api/inventory 

Selct Body > raw > JSON.

The body of the file must be as following:
[ 
	{"name":"Aged Brie", "sellIn": 1, "quality": 1},
	{"name":"Aged Brie", "sellIn": 2, "quality": 60},
	{"name":"Backstage passes", "sellIn": -1, "quality": 2},
	{"name":"Backstage passes", "sellIn": 9, "quality": 2},
	{"name":"Sulfuras", "sellIn": 2, "quality": 2},
	{"name":"Normal Item", "sellIn": -1, "quality": 55},
	{"name":"Normal Item", "sellIn": 2, "quality": 2},
	{"name":"Normal Item", "sellIn": 5, "quality": 10},	//4 9
	{"name":"INVALID ITEM", "sellIn": 2, "quality": 2},
	{"name":"Conjured", "sellIn": 2, "quality": 2},
	{"name":"Conjured", "sellIn": -1, "quality": 5}
]


## Unit Tests
Open the ItemUpdaterTest and run it as JUnit to see different values used for testing the system behaviour.


