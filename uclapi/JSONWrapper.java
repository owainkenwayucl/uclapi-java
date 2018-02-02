/**
 * This class wraps JSON.simple's JSON parsers with methods which return
 * default values rather than throwing NullPointerExceptions.
 */

package uclapi;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class JSONWrapper {

    /**
     * Use JSON.simple to get a value out of a JSONObject, returning "" if
     * the field is empty or does not exist.
     * @param item the object.
     * @param field the field.
     */
    public static String safeGetString(JSONObject item, String field) {
        try {
            return new String(((String)item.get(field)));
        } catch(NullPointerException e) {
            return new String("");
        }
    }

    /**
     * Use JSON.simple to get a value out of a JSONArray, returning "" if
     * the field is empty or does not exist.
     * @param item the array.
     * @param field the field.
     */
    public static String safeGetString(JSONArray item, int field) {
        try {
            return new String(((String)item.get(field)));
        } catch(NullPointerException e) {
            return new String("");
        }
    }

    /**
     * Use JSON.simple to get a value out of a JSONObject, returning 0 if
     * the field is empty or does not exist.
     * @param item the object.
     * @param field the field.
     */
    public static long safeGetLong(JSONObject item, String field) {
        try {
            try {
                return ((long)item.get(field));
            } catch (ClassCastException c) {
                return Long.parseLong((String)item.get(field));
            }
        } catch(NullPointerException e) {
            return 0;
        }
    }

    /**
     * Use JSON.simple to get a value out of a JSONObject, returning 0.0d if
     * the field is empty or does not exist.
     * @param item the object.
     * @param field the field.
     */
    public static double safeGetDouble(JSONObject item, String field) {
        try {
            try {
                return ((double)item.get(field));
            } catch (ClassCastException c) {
                return Double.parseDouble((String)item.get(field));
            }
        } catch(NullPointerException e) {
            return 0.0d;
        }
    }

}
