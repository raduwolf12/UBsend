/*
 * @author = Radu
 */
package com.acme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class OpeningTime.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpeningTime {

	/** The day. */
	private Integer day;

	/** The from. */
	private String from;

	/** The to. */
	private String to;

	/** The closed during. */
	private String closedDuring;
}
