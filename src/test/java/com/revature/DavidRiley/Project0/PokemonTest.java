package com.revature.DavidRiley.Project0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Pokemon Test has tests I made to test the Pokemon.builder method's efficacy.
 */

public class PokemonTest {

    @Test
    public void PokemonEquate(){
        // Just a generic test to test my buiilder method, to see if Bulbasaur is the testPoke's name.
        Pokemon testPoke = new Pokemon().builder().setId(1).setName("Bulbasaur").setType1(Type.GRASS).setType2(Type.POISON).setHp(50).setAtk(50).setDef(50).setsAtk(50)
                .setsDef(50).setSpd(50).setHt("1.08 m").setWt("1.11 kg");

        Assertions.assertEquals("Bulbasaur", testPoke.name());


    }

    @Test
    public void HalfPokemon(){
        Pokemon labRatPoke = new Pokemon().builder().setId(133).setName("Eevee").setType1(Type.NORMAL).setType2(Type.NONE);
        // Testing to see if I can create a Pokemon without all of the fields in my Pokemon class.

        Assertions.assertEquals(Type.NORMAL, labRatPoke.type1());
        Assertions.assertEquals(Type.GRASS, labRatPoke.type2());
        // Found that no matter how many assertions you have, if one fails, it doesn't test any more.

    }
}
