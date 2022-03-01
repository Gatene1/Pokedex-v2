package com.revature.DavidRiley.Project0;

import org.apache.catalina.mapper.Mapper;

/** The Pokemon class holds each method having to do with Pokemon as objects.
 *  So far, this includes a builder method, builder, and the getters and setters
 *  of each stat of a Pokemon.
 */

public class Pokemon {
    private int id, hp, atk, def, sAtk, sDef, spd;
    private float ht, wt;
    private String name, species;
    private Enum<Type> type1, type2;

    public Pokemon(int id, String name, Enum<Type> type1, Enum<Type> type2, int hp, int atk, int def, int sAtk, int sDef, int spd, String species, float ht, float wt){
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.sAtk = sAtk;
        this.sDef = sDef;
        this.spd = spd;
        this.species = species;
        this.ht = ht;
        this.wt = wt;
    }

    public Pokemon() { }
    // Used when just declaring/initializing a variable.

    public Pokemon builder(){
        return new Pokemon();
    }

    public int id() {
        return id;
    }

    public Pokemon setId(int id) {
        this.id = id;
        return this;
    }

    public int hp() {
        return hp;
    }

    public Pokemon setHp(int hp) {
        this.hp = hp;
        return this;
    }

    public int atk() {
        return atk;
    }

    public Pokemon setAtk(int atk) {
        this.atk = atk;
        return this;
    }

    public int def() {
        return def;
    }

    public Pokemon setDef(int def) {
        this.def = def;
        return this;
    }

    public int sAtk() {
        return sAtk;
    }

    public Pokemon setsAtk(int sAtk) {
        this.sAtk = sAtk;
        return this;
    }

    public int sDef() {
        return sDef;
    }

    public Pokemon setsDef(int sDef) {
        this.sDef = sDef;
        return this;
    }

    public int spd() {
        return spd;
    }

    public Pokemon setSpd(int spd) {
        this.spd = spd;
        return this;
    }

    public String name() {
        return name;
    }

    public Pokemon setName(String name) {
        this.name = name;
        return this;
    }

    public float ht() {
        return ht;
    }

    public Pokemon setHt(int ht) {
        this.ht = ht;
        return this;
    }

    public float wt() {
        return wt;
    }

    public Pokemon setWt(int wt) {
        this.wt = wt;
        return this;
    }

    public Enum<Type> type1() {
        return type1;
    }

    public Pokemon setType1(Enum<Type> type1) {
        this.type1 = type1;
        return this;
    }

    public Enum<Type> type2() {
        return type2;
    }

    public Pokemon setType2(Enum<Type> type2) {
        this.type2 = type2;
        return this;
    }

    public String species() {
        return species;
    }

    public Pokemon setSpecies(String species) {
        this.species = species;
        return this;
    }
}
