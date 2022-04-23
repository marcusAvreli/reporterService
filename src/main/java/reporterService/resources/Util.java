package reporterService.resources;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import reporterService.entity.MyStoredProcedure;

public class Util {
	public static String buildStoredProcedureCall(HashMap<String, Object> inputParams, String spName) {
		StringJoiner mystring = new StringJoiner(",");
		Set<String> paramNames = inputParams.keySet();
		String storedProcdureCall = buildStoredProcedureCall(paramNames, spName);

		return storedProcdureCall;
	}

	public static String buildStoredProcedureCall(Set<String> paramNames, String spName) {
		StringJoiner mystring = new StringJoiner(",");
		String message = "call";
		if (null != paramNames && !paramNames.isEmpty()) {
			message += " {0} ({1})";

			for (String paramName : paramNames) {

				mystring.add("?");
			}
		} else {
			message += " {0} ()";
			mystring.add("");
		}
		MessageFormat mf = new MessageFormat(message);
		String formatted = mf.format(new Object[] { spName, mystring });
		String storedProcdureCall = "{" + formatted + "}";
		// String storedProcdureCall = formatted;

		return storedProcdureCall;
	}

	public static Set<String> addItemToSet(Set<String> resultList, Object item) {

		if (null != item) {
			if (null == resultList || resultList.isEmpty()) {
				resultList = new HashSet<String>();
			}
			if (item instanceof List) {
				resultList.addAll((Collection<? extends String>) item);
			} else {
				resultList.add((String) item);
			}

		}

		return resultList;
	}

	public static MyStoredProcedure buildPrettyObject(List<MyStoredProcedure> myStoredProcedures) {
		MyStoredProcedure resultObject = null;
		Set<String> inputParams = null;
		if (null != myStoredProcedures && !myStoredProcedures.isEmpty()) {
			resultObject = new MyStoredProcedure();
			boolean firstRun = true;

			for (MyStoredProcedure mySp : myStoredProcedures) {
				if (firstRun) {
					firstRun = false;
					resultObject.setName(mySp.getName());
					resultObject.setId(mySp.getId());
					resultObject.setDescription(mySp.getDescription());
					String inputParam = mySp.getParameter_name();
					if (null != inputParam && !inputParam.isEmpty()) {
						inputParams = addItemToSet(inputParams, inputParam);
					}
				} else {
					String inputParam = mySp.getParameter_name();
					if (null != inputParam && !inputParam.isEmpty()) {
						inputParams = addItemToSet(inputParams, inputParam);
					}
				}

			}
		}
		if (null != resultObject) {
			resultObject.setInputParams(inputParams);
		}
		return resultObject;
	}
}
