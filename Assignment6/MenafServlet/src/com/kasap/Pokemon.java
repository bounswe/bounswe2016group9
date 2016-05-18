package com.kasap;

import org.apache.jena.query.QuerySolution;

public class Pokemon {
	private String pokemon;
	private String pokemonLabel;
	private int dexNumber;
	private String parts;
	public String getPokemon() {
		return pokemon;
	}


	public String getPokemonLabel() {
		return pokemonLabel;
	}


	public int getDexNumber() {
		return dexNumber;
	}


	public String getParts() {
		return parts;
	}


	public String getPartsLabel() {
		return partsLabel;
	}


	private String partsLabel;


	public Pokemon(QuerySolution sol){
		pokemon = sol.get("?pokemon").asLiteral().getString();
		pokemonLabel = sol.get("?pokemonLabel").asLiteral().getString();
		dexNumber = sol.get("?dexNumber").asLiteral().getInt();
		parts = sol.get("?parts").asLiteral().getString();
		partsLabel = sol.get("?partsLabel").asLiteral().getString();
		
	}


	public Pokemon(String string, String string2, int int1, String string3,
			String string4) {
		// TODO Auto-generated constructor stub
	}}
