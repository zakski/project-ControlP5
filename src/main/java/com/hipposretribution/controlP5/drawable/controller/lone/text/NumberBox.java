package com.hipposretribution.controlP5.drawable.controller.lone.text;

/**
 * controlP5 is a processing gui library.
 * 
 * 2006-2012 by Andreas Schlegel
 * 
 * This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version. This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with this library; if not,
 * write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * @author Andreas Schlegel (http://www.sojamo.de)
 * @modified 12/23/2012
 * @version 2.0.4
 * 
 */

import processing.core.PApplet;
import processing.core.PVector;

import com.hipposretribution.controlP5.ControlP5;
import com.hipposretribution.controlP5.drawable.controller.CElement;
import com.hipposretribution.controlP5.drawable.controller.group.CTab;
import com.hipposretribution.controlP5.drawable.controller.view.ControllerView;
import com.hipposretribution.controlP5.drawable.controller.view.ControllerViewType;
import com.hipposretribution.controlP5.drawable.label.AlignmentX;
import com.hipposretribution.controlP5.drawable.label.AlignmentY;
import com.hipposretribution.controlP5.drawable.label.Label;

/**
 * Click and drag the mouse inside a numberbox and move up and down to change the value of a numberbox. By
 * default the value changes when dragging the mouse up and down. use setDirection(Controller.HORIZONTAL) to
 * change the mouse control to left and right.
 * 
 * Why do I get -1000000 as initial value when creating a numberbox without a default value? the value of a
 * numberbox defaults back to its minValue, which is -1000000. either use a default value or link a variable
 * to the numberbox - this is done by giving a float or int variable the same name as the numberbox.
 * 
 * Use setMultiplier(float) to change the sensitivity of values increasing/decreasing, by default the
 * multiplier is 1.
 * 
 * 
 * @example controllers/ControlP5numberbox
 * @nosuperclasses Controller Controller
 */
public class NumberBox extends CElement<NumberBox> {

	protected int cnt;

	protected boolean isActive;

	public static int LEFT = 0;

	public static int UP = 1;

	public static int RIGHT = 2;

	public static int DOWN = 3;

	protected int _myNumberCount = ControlP5.VERTICAL;

	protected float _myMultiplier = 1;

	public static int autoWidth = 69;

	public static int autoHeight = 19;

	public final PVector autoSpacing = new PVector(10, 20, 0);

	protected float scrollSensitivity = 0.1f;

	/**
	 * Convenience constructor to extend Numberbox.
	 * 
	 * @example use/ControlP5extendController
	 * @param theControlP5
	 * @param theName
	 */
	public NumberBox(ControlP5 theControlP5, String theName) {
		this(theControlP5, theControlP5.getDefaultTab(), theName, 0, 0, 0, autoWidth, autoHeight);
		theControlP5.register(theControlP5.papplet, theName, this);
	}

	/**
	 * 
	 * @param theControlP5 ControlP5
	 * @param theParent Tab
	 * @param theName String
	 * @param theDefaultValue float
	 * @param theX int
	 * @param theY int
	 * @param theWidth int
	 * @param theHeight int
	 */
	public NumberBox(ControlP5 theControlP5, CTab theParent, String theName, float theDefaultValue, int theX, int theY,
			int theWidth, int theHeight) {
		super(theControlP5, theParent, theName, theX, theY, theWidth, theHeight);
		_myMin = -Float.MAX_VALUE;
		_myMax = Float.MAX_VALUE;
		_myValue = theDefaultValue;
		_myValueLabel = new Label(cp5, "" + _myValue, theWidth, 12, color.getValue());
		if (Float.isNaN(_myValue)) {
			_myValue = 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ControllerInterfalce.updateInternalEvents
	 */
	@Override
	@ControlP5.Invisible
	public NumberBox updateInternalEvents(PApplet theApplet) {
		if (isActive) {
			if (!cp5.isAltDown()) {
				if (_myNumberCount == ControlP5.VERTICAL) {
					setValue(_myValue + cp5.mouseDY() * _myMultiplier);
				} else {
					setValue(_myValue + cp5.mouseDX() * _myMultiplier);
				}
			}
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controlP5.Controller#mousePressed()
	 */
	@Override
	@ControlP5.Invisible
	public void mousePressed() {
		isActive = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controlP5.Controller#mouseReleased()
	 */
	@Override
	@ControlP5.Invisible
	public void mouseReleased() {
		isActive = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controlP5.Controller#mouseReleasedOutside()
	 */
	@Override
	@ControlP5.Invisible
	public void mouseReleasedOutside() {
		mouseReleased();
	}

	/**
	 * 
	 * @param theMultiplier
	 * @return Numberbox
	 */
	public NumberBox setMultiplier(float theMultiplier) {
		_myMultiplier = theMultiplier;
		return this;
	}

	/**
	 * 
	 * @return float
	 */
	public float getMultiplier() {
		return _myMultiplier;
	}

	/**
	 * set the value of the numberbox.
	 * 
	 * @param theValue float
	 * @return Numberbox
	 */
	@Override
	public NumberBox setValue(float theValue) {
		_myValue = theValue;
		_myValue = Math.max(_myMin, Math.min(_myMax, _myValue));
		broadcast();
		_myValueLabel.setText(adjustValue(_myValue));
		return this;
	}

	/**
	 * assigns a random value to the controller.
	 * 
	 * @return Numberbox
	 */
	public NumberBox shuffle() {
		float r = (float) Math.random();
		if (getMax() != Float.MAX_VALUE && getMin() != -Float.MAX_VALUE) {
			setValue(PApplet.map(r, 0, 1, getMin(), getMax()));
		}
		return this;
	}

	public NumberBox setRange(float theMin, float theMax) {
		setMin(theMin);
		setMax(theMax);
		setValue(getValue());
		return this;
	}

	/**
	 * sets the sensitivity for the scroll behavior when using the mouse wheel or the scroll function of a
	 * multi-touch track pad. The smaller the value (closer to 0) the higher the sensitivity.
	 * 
	 * @param theValue
	 * @return Numberbox
	 */
	public NumberBox setScrollSensitivity(float theValue) {
		scrollSensitivity = theValue;
		return this;
	}

	/**
	 * changes the value of the numberbox when hovering and using the mouse wheel or the scroll function of a
	 * multi-touch track pad.
	 * 
	 * @param theRotationValue
	 * @return Numberbox
	 */
	@ControlP5.Invisible
	public NumberBox scrolled(int theRotationValue) {
		float f = getValue();
		f += (_myMultiplier == 1) ? (theRotationValue * scrollSensitivity) : theRotationValue * _myMultiplier;
		setValue(f);
		return this;
	}

	/**
	 * set the direction for changing the numberbox value when dragging the mouse. by default this is up/down
	 * (VERTICAL), use setDirection(Controller.HORIZONTAL) to change to left/right or back with
	 * setDirection(Controller.VERTICAL).
	 * 
	 * @param theValue
	 */
	public NumberBox setDirection(int theValue) {
		if (theValue == ControlP5.HORIZONTAL || theValue == ControlP5.VERTICAL) {
			_myNumberCount = theValue;
		} else {
			_myNumberCount = ControlP5.VERTICAL;
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controlP5.Controller#update()
	 */
	@Override
	public NumberBox update() {
		return setValue(_myValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NumberBox linebreak() {
		cp5.linebreak(this, true, autoWidth, autoHeight, autoSpacing);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@ControlP5.Invisible
	public NumberBox updateDisplayMode(ControllerViewType theMode) {
		displayMode = theMode;
		switch (theMode) {
		case DEFAULT:
			controllerView = new NumberboxView();
		case SPRITE:
		case IMAGE:
		case CUSTOM:
		default:
			break;
		}
		return this;
	}

	class NumberboxView implements ControllerView<NumberBox> {

		NumberboxView() {
			_myValueLabel.setAlignment(AlignmentX.LEFT, AlignmentY.CENTER).setPaddingX(0);
			captionLabel.setAlignment(AlignmentX.LEFT, AlignmentY.BOTTOM_OUTSIDE).setPaddingX(0);
		}

		@Override
		public void display(PApplet theApplet, NumberBox theController) {
			theApplet.fill(color.getBackground());
			theApplet.rect(0, 0, width, height);
			theApplet.fill((isActive) ? color.getActive() : color.getForeground());
			int h = height / 2;
			theApplet.triangle(0, h - 6, 6, h, 0, h + 6);
			_myValueLabel.draw(theApplet, 10, 0, theController);
			captionLabel.draw(theApplet, 0, 0, theController);
		}
	}
}
