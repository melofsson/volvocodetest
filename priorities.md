


Jag beslutade mig för att skapa ett nytt Spring boot projekt,
för att enkelt kunna skapa REST endpoints och köra applikationen via Tomcat lokalt.

Vid initiering av Spring boot app via Spring initilz så skapas automatiskt en entry point. 


Jag börjar med att stämma av befintlig kod och hur den stämmer med given kravspecifikation. 

Checkar vilka delar som jag tycker ser ut att behöva ses över.

Efter att ha tittat genom koden kommer jag nu börja sätta upp tester,
som jag kan provskjuta mot koden kontinuerligt och som testar av de olika scenarion, 
Jag kommer använda datumen som fanns nedskrivna på postitlappen, som testdata.

Jag ska implementera en REST api endpoint så att man kan nå applikationen med olika inputs via http.

Rättar buggar och omstrukturerar koden. 

Om jag hade fortsatt så hade jag förlagt taxeringsregler i en databas och gjort logiken för avläsning gentemot taxeringsregler mer generisk för att kunna hantera olika data. 
