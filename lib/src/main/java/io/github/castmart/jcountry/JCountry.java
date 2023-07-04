/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.github.castmart.jcountry;

/**
 * Singleton class that loads the ISO DB's through the corresponding Classes.
 * The singleton is intended to only have one source of information and avoid redundant loading of files and translations.
 */
public class JCountry {

    private static JCountry instance;
    private final CountryDB countryDB = new CountryDBImpl();

    private final LanguageDB languageDB = new LanguageDBImpl();

    private JCountry() {}

    public static JCountry getInstance() {
        if (instance == null) {
            instance = new JCountry();
        }
        return instance;
    }

    /**
     * Get the ISO 3166-1 Countries.
     * @return a CountryDB instance containing the ISO-3166-1 countries info as well as the cuntry names translations.
     */
    public CountryDB getCountriesDB() {
        return countryDB;
    }

    /**
     * Get the ISO 639-2 languages DB.
     * @return an instance of LanguageDB
     */
    public LanguageDB getLanguageDB() {
        return languageDB;
    }
 }
