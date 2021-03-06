package com.szadowsz.controlP5.drawable.label.style;

import processing.core.PApplet;

public enum AlignmentX {

	CENTER(PApplet.CENTER), LEFT(PApplet.LEFT), RIGHT(PApplet.RIGHT), LEFT_OUTSIDE(10), RIGHT_OUTSIDE(11);

	public final int code;

	private AlignmentX(int ncode) {
		code = ncode;
	}
}
