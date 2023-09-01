/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.jimdo.jcountry;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class JCountryTest {
    @Test
    void testJCountryReturnsCountryDB() {
        JCountry jcountry = JCountry.getInstance();
        CountryDB countryDB = jcountry.getCountriesDB();
        assertNotNull(countryDB, "getCountriesDB should return an instance of the countries DB");

        LanguageDB languageDB = jcountry.getLanguageDB();
        assertNotNull(languageDB, "getLanguageDB should return an instance of the Language DB");
    }

    @Test
    void testJCountryDBReadsJson() {
        CountryDB countryDB = new CountryDBImpl(true);
        assertNotNull(countryDB, "getCountriesDB should return an instance of the countries DB");

        var dbByAlpha2 = countryDB.getCountriesMapByAlpha2();
        var dbByAlpha3 = countryDB.getCountriesMapByAlpha3();
        var dbByName = countryDB.getCountriesMapByName();
        assertNotNull(dbByAlpha2);
        assertNotNull(dbByAlpha3);
        assertNotNull(dbByName);

        assertTrue(!dbByAlpha2.isEmpty());
        assertTrue(!dbByAlpha3.isEmpty());
        assertTrue(!dbByName.isEmpty());

        assertEquals(dbByName.size(), dbByAlpha2.size());
        assertEquals(dbByAlpha2.size(), dbByAlpha3.size());
    }

    @Test
    void testJCountryReadTranslations() {
        CountryDB countryDB = new CountryDBImpl(true);

        Optional<ResourceBundle> bundle = countryDB.getCountriesTranslations(Locale.GERMAN);
        assertTrue(bundle.isPresent());

        Optional<ResourceBundle> bundleTwo = countryDB.getCountriesTranslations(new Locale("Unexistent Lang"));
        assertFalse(bundleTwo.isPresent());
    }

    @Test
    void testJCountryReadsTranslations() {
        // More than 50% of work population is covered with these languages.
        ArrayList<String> languages = new ArrayList<>(Arrays.asList("nl", "es", "de", "bn", "pt", "ru", "ja", "hi", "ar", "pa", "fr", "tr", "ko"));
        CountryDB countryDB = new CountryDBImpl(true);

        languages.forEach( it -> {
            Optional<ResourceBundle> bundle = countryDB.getCountriesTranslations(new Locale(it));
            assertTrue(!bundle.isEmpty());
        });
    }

    @Test
    void getTranslatedCountryName() {
        CountryDB countryDB = new CountryDBImpl(true);
        var dbByAlpha2 = countryDB.getCountriesMapByAlpha2();

        Optional<ResourceBundle> bundle = countryDB.getCountriesTranslations(Locale.GERMAN);
        var translatedCountryName = bundle.get().getString(dbByAlpha2.get("MX").getName());

        assertEquals("Mexiko", translatedCountryName);
    }


    @Test
    void testJCountryLanguageDBReadsJson() {
        LanguageDB languageDB = new LanguageDBImpl(true);
        assertNotNull(languageDB, "getLanguageDB should return an instance of the Language DB");

        var dbByAlpha2 = languageDB.getLanguagesMapByAlpha2();
        var dbByAlpha3 = languageDB.getLanguagesMapByAlpha3();
        var dbByName = languageDB.getLanguagesMapByName();
        assertNotNull(dbByAlpha2);
        assertNotNull(dbByAlpha3);
        assertNotNull(dbByName);

        assertTrue(!dbByAlpha2.isEmpty());
        assertTrue(!dbByAlpha3.isEmpty());
        assertTrue(!dbByName.isEmpty());

        // Not all languages have alpha_2 code!!!
        assertEquals(dbByName.size(), dbByAlpha3.size());
    }

    @Test
    void testJCountryLanguagesDBReadTranslations() {
        LanguageDB languageDB = new LanguageDBImpl(true);

        Optional<ResourceBundle> bundle = languageDB.getLanguagesTranslations(Locale.GERMAN);
        assertTrue(bundle.isPresent());

        Optional<ResourceBundle> bundleTwo = languageDB.getLanguagesTranslations(new Locale("Unexistent Lang"));
        assertFalse(bundleTwo.isPresent());
    }

    @Test
    void testJCountryLanguagesReadsTranslations() {
        // More than 50% of work population is covered with these languages.
        ArrayList<String> languages = new ArrayList<>(Arrays.asList("es", "de", "it", "ja", "hi", "ar", "pa", "fr", "tr", "nl"));
        LanguageDB languageDB = new LanguageDBImpl(true);

        languages.forEach( it -> {
            Optional<ResourceBundle> bundle = languageDB.getLanguagesTranslations(new Locale(it));
            assertTrue(!bundle.isEmpty(), it + " translation");
        });
    }

    @Test
    void getTranslatedLanguageName() {
        LanguageDB languageDB = new LanguageDBImpl(true);
        var dbByAlpha2 = languageDB.getLanguagesMapByAlpha2();

        Optional<ResourceBundle> bundle = languageDB.getLanguagesTranslations(Locale.GERMAN);
        var translatedCountryName = bundle.get().getString(dbByAlpha2.get("es").getName());

        assertEquals("Spanisch (Kastilisch)", translatedCountryName);
    }
}
