package persistence;

import org.json.JSONObject;

/*
 CITATION: This class was adapted from the CPSC210's JsonSerializationDemo repo
 Source: https://github.com/stleary/JSON-java
*/

// EFFECTS: returns this as JSON object
public interface Writable {
    JSONObject toJson();
}
