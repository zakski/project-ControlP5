package com.szadowsz.controlP5.drawable.controller.lone.chart;

/**
 * Used by Chart, single chart data is stored here including value, (label) text, and color.
 */
class ChartData {

	protected float _myValue;

	protected String _myText;

	protected int _myColor;

	public ChartData(float theValue) {
		this(theValue, "");
	}

	public ChartData(float theValue, String theText) {
		_myValue = theValue;
		_myText = theText;
	}

	public void setValue(float theValue) {
		_myValue = theValue;
	}

	public void setText(String theText) {
		_myText = theText;
	}

	public float getValue() {
		return _myValue;
	}

	public String getText() {
		return _myText;
	}

	public void setColor(int theColor) {
		_myColor = theColor;
	}

	public int getColor() {
		return _myColor;
	}

}