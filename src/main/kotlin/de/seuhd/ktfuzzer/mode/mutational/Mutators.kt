package de.seuhd.ktfuzzer.mode.mutational

import kotlin.random.Random
import kotlin.text.count
import kotlin.text.deleteAt



/**
 * Character-level mutators from The Fuzzing Book's MutationFuzzer
 * (<https://www.fuzzingbook.org/html/MutationFuzzer.html>), plus [repeatRandomCharacter]. Each
 * function applies one small edit to an input string and returns the result.
 */
internal object Mutators {
    /** Deletes one randomly chosen character. */
    fun deleteRandomCharacter(input: String, random: Random): String {
        //Exercise 1: delete one randomly chosen character
        //length of input chars
        val len = input.count()
        // have a random number in the range of len
        val rnInt = random.nextInt(1, len)
        // with deleteAt we can delete at specific index

        //make StringBuilder object to access deleteAt(index) func
        val newString = StringBuilder(input)
        newString.deleteAt(rnInt)
        return newString.toString()

        // so easy to make
        //just had to use StringBuilder class
    }
    /** Inserts one character drawn uniformly from [alphabet] at a random position. */
    fun insertRandomCharacter(input: String, alphabet: List<Char>, random: Random): String{
        //"Exercise 1: insert one character from the alphabet at a random position")
        val len = input.count()
        // have a random number in the range of len
        val rnInt = random.nextInt(0, len+1)
        // with deleteAt we can delete at specific index

        //make StringBuilder object to access deleteAt(index) func
        val newString = StringBuilder(input)

        val lenAlphabet = alphabet.count()
        val randAlphabet = random.nextInt(0, lenAlphabet)
        newString.insert(rnInt, alphabet[randAlphabet])
        return newString.toString()
    }

    /** Flips one randomly chosen low bit of one randomly chosen character. */
    fun flipRandomCharacter(input: String, random: Random): String =
        TODO("Exercise 1: flip one random low bit of one randomly chosen character")

    /** Repeats one randomly chosen character a random number of times in place. */
    fun repeatRandomCharacter(input: String, random: Random): String =
        TODO("Exercise 1: repeat one randomly chosen character a random number of times")
}
