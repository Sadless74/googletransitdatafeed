/*
 * The Google Transit Data Feed project
 * 
 * TransXChange2GoogleTransit
 *
 * Version:	1.1
 * Date: 	22-Feb-2007
 * 
 * Copyright (C) 2007, Joachim Pfeiffer
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 * http://www.gnu.org
 * 
 */

package transxchange2GoogleTransitHandler;

import org.xml.sax.*;
import org.xml.sax.helpers.*;


// This class extends DefaultHandler to parse an transxchange xml file
// and generate corresponding Google Transit data feed structures
public class TransxchangeHandler extends DefaultHandler {
	
	TransxchangeAgency agencies;
	TransxchangeStops stops;
	TransxchangeRoutes routes;
	TransxchangeTrips trips;
	TransxchangeStopTimes stopTimes;
	TransxchangeCalendar calendar;
	TransxchangeCalendarDates calendarDates;

	static String googleTransitUrl = "";
	static String googleTransitTimezone = "";
	static String googleTransitDefaultRouteType = "";

	public void setUrl(String url) {
		googleTransitUrl = url;
	}

	public void setTimezone(String timezone) {
		googleTransitTimezone = timezone;
	}

	public void setDefaultRouteType(String defaultRouteType) {
		googleTransitDefaultRouteType = defaultRouteType;
	}
	
	public String getUrl() {
		return googleTransitUrl;
	}

	public String getTimezone() {
		return googleTransitTimezone;
	}

	public String getDefaultRouteType() {
		return googleTransitDefaultRouteType;
	}
	
	public TransxchangeAgency getAgencies() {
		return agencies;
	}
	
	public TransxchangeStops getStops() {
		return stops;
	}
	
	public TransxchangeRoutes getRoutes() {
		return routes;
	}
	
	public TransxchangeTrips getTrips() {
		return trips;
	}

	public TransxchangeStopTimes getStopTimes() {
		return stopTimes;
	}
	
	public TransxchangeCalendar getCalendar() {
		return calendar;
	}
	
	public TransxchangeCalendarDates getCalendarDates() {
		return calendarDates;
	}
	
	/*
	 * start element
	 */   	
	public void startElement(String uri, String name, String qName, Attributes atts) {
		int qualifierIx;
	
	    agencies.startElement(uri, name, qName, atts);
	    stops.startElement(uri, name, qName, atts);
	    routes.startElement(uri, name, qName, atts);
	    trips.startElement(uri, name, qName, atts);
	    stopTimes.startElement(uri, name, qName, atts);
	    calendar.startElement(uri, name, qName, atts);
	    calendarDates.startElement(uri, name, qName, atts);
	}
	
	/*
	 * parse element
	 */   	
	public void characters (char ch[], int start, int length) {
		agencies.characters(ch, start, length);
		stops.characters(ch, start, length);
		routes.characters(ch, start, length);
		trips.characters(ch, start, length);
		stopTimes.characters(ch, start, length);
		calendar.characters(ch, start, length);
		calendarDates.characters(ch, start, length);
	}
    
	/*
 	 * end element
 	 */   	
	public void endElement (String uri, String name, String qName) {
		// take care of element
		agencies.endElement(uri, name, qName);
		stops.endElement(uri, name, qName);
		routes.endElement(uri, name, qName);
		trips.endElement(uri, name, qName);
		stopTimes.endElement(uri, name, qName);
		calendar.endElement(uri, name, qName);
		calendarDates.endElement(uri, name, qName);
	
		// clear keys
		agencies.clearKeys(qName);
		stops.clearKeys(qName);
		routes.clearKeys(qName);
		trips.clearKeys(qName);
		stopTimes.clearKeys(qName);
		calendar.clearKeys(qName);
		calendarDates.clearKeys(qName);

	}

	/*
	 * complete and dump Google Transit data feed data structure
	 */   	
	public void endDocument() {
    
		// wrap up document parsing
		agencies.endDocument();
		stops.endDocument();
		routes.endDocument();
		trips.endDocument();
		stopTimes.endDocument();
		calendar.endDocument();
		calendarDates.endDocument();
        
		// Complete data structures (by filling in default values if necessary)
		agencies.completeData();
		stops.completeData();
		routes.completeData();
		trips.completeData();
		stopTimes.completeData();
		calendar.completeData();
		calendarDates.completeData();
    
		// Dump parsed data to System.out
/*
		agencies.dumpValues();
		stops.dumpValues();
		routes.dumpValues();
		trips.dumpValues();
		stopTimes.dumpValues();
		calendar.dumpValues();
		calendarDates.dumpValues();
*/
	}

	public TransxchangeHandler () {
		agencies = new TransxchangeAgency(this);
		stops = new TransxchangeStops(this);
		routes = new TransxchangeRoutes(this);
		trips = new TransxchangeTrips(this);
		stopTimes = new TransxchangeStopTimes(this);
		calendar = new TransxchangeCalendar(this);
		calendarDates = new TransxchangeCalendarDates(this);
	}
}