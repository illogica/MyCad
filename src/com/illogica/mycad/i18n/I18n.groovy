package com.illogica.mycad.i18n

import com.illogica.mycad.Settings
import groovy.io.FileType
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

import java.nio.file.Path
import java.nio.file.Paths

/**
 * I18n support
 *
 * Basically just a static function tr() that will return a string according to the
 * locale set in the application.
 *
 * I18n is composed of a set of json files, one for each locale.
 * In each file there's this structure:
 *{*     "locale":"en"      //or it, de, etc...
 *     "hashCode1":"String1",
 *     "hashCode2":"String2",
 *     ...
 *     "hashCodeN":"StringN"
 *}*/

class I18n {
    static String i18nPath = "i18n"

    /**************************************
     *  Private classes and methods
     *************************************/

    /**
     * Small helper class to chreate the base "en" locale
     */
    private static class MyCadLocale {
        def locale = ""
    }

    private static List<File> getI18nFiles() {
        List<File> files = new ArrayList<File>()
        File dir = new File(i18nPath)
        dir.eachFile(FileType.FILES) {
            files.add(it)
        }
        return files
    }

    private static File getLocaleFile(String locale) {
        return new File(i18nPath, locale + ".json")
    }



    /**************************************
     *  Public classes and methods
     *************************************/


    /**
     * The "en.json" file is the base for all other locales.
     * Every time we need a new locale file we copy the en.json file and
     * change the "locale" property from "en" to the correct one.
     *
     * @param locale
     */
    static void generateLocaleFile(String locale) {
        File f = getLocaleFile(locale)
        if (f.exists()) {
            println "I won't overwrite existing locale $locale"
            return
        }
        f.createNewFile()

        if (locale == ("en")) {
            def obj = new MyCadLocale()
            obj.locale = "en"
            f.text = new JsonBuilder(obj).toPrettyString()
        } else {
            //make a copy of the "en" locale
            def obj = new JsonSlurper().parse(getLocaleFile("en"))
            obj.locale = locale
            f.text = new JsonBuilder(obj).toPrettyString()
        }
    }

    /**
     * Given an English string, returns the one with the proper locale.
     * If it's a new String, save it in all locale files.
     * All strings are indexed by their md5() value
     *
     * @param s
     */
    static String tr(String s) {
        def hash = s.md5()

        //Load en locale file
        File enLocaleFile = getLocaleFile("en")
        JsonSlurper slurper = new JsonSlurper()
        def enLocaleObj = slurper.parse(enLocaleFile)

        //If the string is not found in the en locale file,
        // add it in all locale files (also en)
        if (enLocaleObj."$hash" == null) {

            // Go through all the locale files and add the
            // new string in English language, to be updated later
            // directly in the json files
            List<File> localeFiles = getI18nFiles()
            for (File f in localeFiles) {
                JsonSlurper slurper2 = new JsonSlurper()
                def tmpObj = slurper2.parse(f)
                tmpObj."$hash" = s
                f.text = new JsonBuilder(tmpObj).toPrettyString()
            }
        }

        def currentLocale = Settings.getString(Settings.KEY_LOCALE)
        if(currentLocale == "en"){
            return s
        } else {
            File localeFile = getLocaleFile(Settings.getString(Settings.KEY_LOCALE))
            JsonSlurper slurper3 = new JsonSlurper()
            def localeObj = slurper3.parse(localeFile)
            return localeObj."$hash"
        }
    }
}
