package pl.devcrowd.app.parsers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pl.devcrowd.app.models.Presentation;
import pl.devcrowd.app.models.Speaker;
import pl.devcrowd.app.utils.DebugLog;

/**
 * JSON Parser class to get useful objects
 * 
 * @author Tofer
 * */
public final class JSONParser {

	private static final String PRESENTATION_TITLE = "title";
	private static final String PRESENTATION_DESCRIPTION = "description";
	private static final String PRESENTATION_ROOM = "room";
	private static final String PRESENTATION_START = "starts";
	private static final String PRESENTATION_END = "ends";
	private static final String PRESENTATION_SPEAKERS = "speakers";

	private static final String SPEAKER_NAME = "name";
	private static final String SPEAKER_PHOTOURL = "photoUrl";
	private static final String SPEAKER_DESCRIPTION = "description";

	private JSONParser() {
	}

	/**
	 * parse JSON to get List of Presentation objects
	 * 
	 * @param response
	 *            - JSON String from URL
	 * 
	 * @return List of Presentation objects
	 * */
	public static List<Presentation> getPresentationsFromString(
			final String response) {
		List<Presentation> listOfPresentations = new ArrayList<Presentation>();
		try {
			JSONArray responseArray = new JSONArray(response);
			for (int i = 0; i < responseArray.length(); i++) {
				listOfPresentations
						.add(parsePresentationArray(responseArray, i));
			}
		} catch (JSONException e) {
			DebugLog.e("JSONException during parse presenations");
		}
		return listOfPresentations;
	}

	/**
	 * parse JSON to get List of Speaker objects
	 * 
	 * @param response
	 *            - JSON String from URL
	 * 
	 * @return List of Speaker objects
	 * */
	public static List<Speaker> getSpeakersFromString(final String response) {
		List<Speaker> listOfSpeakers = new ArrayList<Speaker>();
		try {
			JSONArray responseArray = new JSONArray(response);
			for (int i = 0; i < responseArray.length(); i++) {
				listOfSpeakers.addAll(parseSpeakerObject(responseArray, i));
			}
		} catch (JSONException e) {
			DebugLog.e("JSONException during get speakers");

		}
		return listOfSpeakers;
	}

	/**
	 * add filled Presentation objects to List
	 * 
	 * @param responseArray
	 *            - JSON String from URL
	 * @param i
	 *            - index of current object
	 * 
	 * */
	private static Presentation parsePresentationArray(
			final JSONArray responseArray, int index) {
		Presentation presentation = new Presentation();
		try {
			JSONObject arrayElement = responseArray.getJSONObject(index);

			presentation.setTitle(getStringFromArray(arrayElement,
					PRESENTATION_TITLE));
			presentation.setDescription(getStringFromArray(arrayElement,
					PRESENTATION_DESCRIPTION));
			presentation.setHourStart(getStringFromArray(arrayElement,
					PRESENTATION_START));
			presentation.setHourEnd(getStringFromArray(arrayElement,
					PRESENTATION_END));
			presentation.setRoom(getStringFromArray(arrayElement,
					PRESENTATION_ROOM));

			JSONArray speakersArray = arrayElement
					.getJSONArray(PRESENTATION_SPEAKERS);
			ArrayList<String> speakersInput = new ArrayList<String>();
			for (int i = 0; i < speakersArray.length(); i++) {
				JSONObject speakersArrayElements = speakersArray
						.getJSONObject(i);
				speakersInput.add(getStringFromArray(speakersArrayElements,
						SPEAKER_NAME));
			}
			presentation.setSpeakers(speakersInput);

		} catch (JSONException e) {
			DebugLog.e("JSONException during parse presentation array");

		}
		return presentation;
	}

	/**
	 * add filled Speaker objects to List
	 * 
	 * @param responseArray
	 *            - JSON String from URL
	 * @param i
	 *            - index of current object
	 * 
	 * */
	private static List<Speaker> parseSpeakerObject(
			final JSONArray responseArray, int index) {
		List<Speaker> listOfSpeakers = new ArrayList<Speaker>();
		try {
			final JSONObject arrayElement = responseArray.getJSONObject(index);
			Speaker speaker = null;
			String presentationTitle = getStringFromArray(arrayElement,
					PRESENTATION_TITLE);
			JSONArray speakersArray = arrayElement
					.getJSONArray(PRESENTATION_SPEAKERS);
			for (int i = 0; i < speakersArray.length(); i++) {
				speaker = new Speaker();
				JSONObject speakerElement = speakersArray.getJSONObject(i);
				speaker.setName(getStringFromArray(speakerElement, SPEAKER_NAME));
				speaker.setPhotoUrl(getStringFromArray(speakerElement,
						SPEAKER_PHOTOURL));
				speaker.setDescription(getStringFromArray(speakerElement,
						SPEAKER_DESCRIPTION));
				speaker.setPresenationName(presentationTitle);
				listOfSpeakers.add(speaker);
			}
		} catch (JSONException e) {
			DebugLog.e("JSONException during parse speaker object");
		}
		return listOfSpeakers;
	}

	private static String getStringFromArray(final JSONObject arrayElement,
			final String key) throws JSONException {
		return arrayElement.has(key) ? arrayElement.getString(key) : "";
	}

}
