My query was

###################################################################################################
SELECT ?pokemon ?pokemonLabel ?dexnumber  ?parts ?partsLabel WHERE {
  ?pokemon wdt:P31 wd:Q3966183 .
  
  ?pokemon wdt:P1112 ?dexnumber .
  ?pokemon wdt:P361 ?parts .
  SERVICE wikibase:label { bd:serviceParam wikibase:language "en" }
}
ORDER BY ?dexnumber
###################################################################################################

The user selects a Pokédex number and it lists 10 Pokémons which have the closest Pokédex numbers.

Here an example of 1 and 25 for Pokédex numbers:

Pokédex number 1:
###################################################################################################
pokemon			pokemonLabel	dexnumber	parts			partsLabel

 wd:Q847571		Bulbasaur		1	 		wd:Q18159502	first generation Pokémon
 wd:Q1636903	Ivysaur			2	 		wd:Q12015638	evolutionary line of Bulbasaur
 wd:Q2283930	Venusaur		3	 		wd:Q12015638	evolutionary line of Bulbasaur
 wd:Q3178753	Charmander		4	 		wd:Q12013598	evolutionary line of Charmander
 wd:Q1637365	Charmeleon		5	 		wd:Q12013598	evolutionary line of Charmander
 wd:Q844940		Charizard		6	 		wd:Q12013598	evolutionary line of Charmander
 wd:Q845294		Squirtle		7	 		wd:Q12033480	evolutionary line of Squirtle
 wd:Q1752151	Wartortle		8	 		wd:Q12033480	evolutionary line of Squirtle
 wd:Q1752154	Blastoise		9	 		wd:Q12033480	evolutionary line of Squirtle
 wd:Q1651039	Caterpie		10	 		wd:Q12052400	evolutionary line of Caterpie
###################################################################################################

Pokédex number 25:
###################################################################################################
pokemon			pokemonLabel	dexnumber	parts			partsLabel

 wd:Q2021999	Spearow			21	 		wd:Q3382169		Spearow and Fearow
 wd:Q2087817	Fearow			22	 		wd:Q3382169		Spearow and Fearow
 wd:Q1266064	Ekans			23	 		wd:Q12070885	Ekans and Arbok
 wd:Q1289969	Arbok			24	 		wd:Q12070885	Ekans and Arbok
 wd:Q9351		Pikachu			25	 		wd:Q18159502	first generation Pokémon
 wd:Q1647331	Raichu			26	 		wd:Q12074899	evolutionary line of Pichu
 wd:Q2002398	Sandshrew		27	 		wd:Q12126397	Sandshrew and Sandslash
 wd:Q2345521	Sandslash		28	 		wd:Q12126397	Sandshrew and Sandslash
 wd:Q2725378	Nidoran♀		29	 		wd:Q12126696	evolutionary line of Nidoran♀
 wd:Q1998207	Nidorina		30	 		wd:Q12126696	evolutionary line of Nidoran♀
###################################################################################################