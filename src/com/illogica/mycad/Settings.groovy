package com.illogica.mycad

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import javafx.scene.paint.Color

class Settings {

    static String KEY_LOCALE = "locale"

    // Drawing area settings
    static String KEY_DA_BACKGROUND = "drawing_area_background"
    static String KEY_DA_POINTER_SIZE = "drawing_area_pointer_size"
    static String KEY_DA_POINTER_WIDTH = "drawing_area_pointer_width"
    static String KEY_DA_POINTER_COLOR = "drawing_area_pointer_color"


    static String prefsPath = "prefs.json"
    static File prefsFile

    static void init(){
        prefsFile = new File(prefsPath)
        if(!prefsFile.exists()){
            prefsFile.createNewFile()
            prefsFile.text="{}"
        }
        checkDefaults()
    }

    /*******************************
     * Private functions
     ******************************/

    private static Object getJsonObj(){
        JsonSlurper slurper = new JsonSlurper()
        def jsonObject = slurper.parse(prefsFile)
        return jsonObject
    }

    private static void checkDefaults(){
        def obj = getJsonObj()
        if(!obj."$KEY_LOCALE")
            obj."$KEY_LOCALE" = "en"
        //TODO: add more defaults here


        prefsFile.write(new JsonBuilder(obj).toPrettyString())
    }

    /*******************************
     * Public functions
     ******************************/

    static int getInt(String key) {
        def obj = getJsonObj()
        if (obj."$key" != null) {
            return obj."$key"
        } else {
            return 0
        }
    }

    static void putInt(String key, int val){
        def obj = getJsonObj()
        obj."$key" = val
        prefsFile.write(new JsonBuilder(obj).toPrettyString())
    }

    static int getLong(String key) {
        def obj = getJsonObj()
        if (obj."$key" != null) {
            return obj."$key"
        } else {
            return 0L
        }
    }

    static void putLong(String key, long val){
        def obj = getJsonObj()
        obj."$key" = val
        prefsFile.write(new JsonBuilder(obj).toPrettyString())
    }

    static int getDouble(String key) {
        def obj = getJsonObj()
        if (obj."$key" != null) {
            return obj."$key"
        } else {
            return 0L
        }
    }

    static void putDouble(String key, double val){
        def obj = getJsonObj()
        obj."$key" = val
        prefsFile.write(new JsonBuilder(obj).toPrettyString())
    }

    static String getString(String key){
        def obj = getJsonObj()
        if (obj."$key" != null) {
            return obj."$key"
        } else {
            return ""
        }
    }

    static void putString(String key, String val){
        def obj = getJsonObj()
        obj."$key" = val
        prefsFile.write(new JsonBuilder(obj).toPrettyString())
    }

    static Color getColor(String key){
        def obj = getJsonObj()
        def colorArray = obj."$key" as List<Double>
        if (obj."$key" != null) {
            def color = Color.color(
                    colorArray[0],
                    colorArray[1],
                    colorArray[2],
                    colorArray[3])
            return color
        } else {
            return Color.BLACK
        }
    }

    static void putColor(String key, Color val){
        def obj = getJsonObj()
        def ar = []
        ar[0] = val.red
        ar[1] = val.green
        ar[2] = val.blue
        ar[3] = val.opacity
        obj."$key" = ar
        prefsFile.write(new JsonBuilder(obj).toPrettyString())
    }
}
