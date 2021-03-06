/**
 * controlP5 is a processing gui library.
 *
 * 2006-2012 by Andreas Schlegel
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version. This library is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * @author Andreas Schlegel (http://www.sojamo.de)
 */
package com.szadowsz.controlP5.drawable.colour

/**
 * CColour Companion Object, holder of default themes and application functions.
 */
object CColour {
  private val ALPHA_MASK: Int = 0xff000000

  val THEME_RETRO = new CColour(0xff00698c, 0xff003652, 0xff08a2cf, 0xffffffff, 0xffffffff)
  val THEME_CP52014 = new CColour(0xff0074D9, 0xff002D5A, 0xff00aaff, 0xffffffff, 0xffffffff)
  val THEME_CP5BLUE = new CColour(0xff016c9e, 0xff02344d, 0xff00b4ea, 0xffffffff, 0xffffffff)
  val THEME_RED = new CColour(0xffaa0000, 0xff660000, 0xffff0000, 0xffffffff, 0xffffffff)
  val THEME_GREY = new CColour(0xffeeeeee, 0xffbbbbbb, 0xffffffff, 0xff555555, 0xff555555)
  val THEME_A = new CColour(0xff00FFC8, 0xff00D7FF, 0xffffff00, 0xff00B0FF, 0xff00B0FF)

  /**
   * Method to Generate a new CColour with the default colour scheme, THEME_CP5BLUE.
   *
   * @return a new CColour.
   */
  def apply(): CColour = new CColour()

  /**
   * Method to Generate a new CColour with a copy of the provided colour scheme.
   *
   * @param colour the provided colour scheme.
   * @return a new CColour.
   */
  def apply(colour: CColour) = new CColour(colour)

  /**
   * Method takes in 5 processing colour encoded ints each of which represent a colour as part of the UI colour scheme.
   *
   * @param fore the foreground colour of the colour scheme.
   * @param back the background colour of the colour scheme.
   * @param act the active colour of the colour scheme.
   * @param cap the caption colour of the colour scheme.
   * @param valu the value colour of the colour scheme.
   * @return a new CColour made up of those 5 colours.
   */
  def apply(fore: Int, back: Int, act: Int, cap: Int, valu: Int): CColour = new CColour(fore, back, act, cap, valu)
}


/**
 * A CColour instance contains the colour scheme of a controller including the foreground, background,
 * active, caption and value colours.
 *
 * @constructor Constructor takes in 5 processing colour encoded ints each of which represent a colour as part of the UI
 *              colour scheme.
 *
 * @param fore the foreground colour of the colour scheme.
 * @param back the background colour of the colour scheme.
 * @param act the active colour of the colour scheme.
 * @param cap the caption colour of the colour scheme.
 * @param valu the value colour of the colour scheme.
 */
final class CColour(fore: Int, back: Int, act: Int, cap: Int, valu: Int) extends Serializable {

  /**
   * The background colour of the colour scheme.
   */
  private var _background: Int = back

  /**
   * The foreground colour of the colour scheme.
   */
  private var _foreground: Int = fore

  /**
   * The active colour of the colour scheme.
   */
  private var _active: Int = act

  /**
   * The caption colour of the colour scheme.
   */
  private var _caption: Int = cap

  /**
   * The value colour of the colour scheme.
   */
  private var _value: Int = valu

  /**
   * Constructor to Generate a new CColour with a copy of the provided colour scheme.
   *
   * @param colour the provided colour scheme.
   */
  def this(colour: CColour) {
    this(colour.getForeground, colour.getBackground, colour.getActive, colour.getCaption, colour.getValue)
  }

  /**
   * Default Constructor generates a new CColour with the default colour scheme, THEME_CP5BLUE.
   */
  def this() {
    this(CColour.THEME_CP5BLUE)
  }

  /**
   * Protected Method to force all colours to be at least a tiny bit opaque. If you want something to be
   * invisible, disable it properly.
   *
   * @param colour - the colour value to check.
   * @return the colour value if valid, otherwise black.
   */
  private def ensureOpacity(colour: Int): Int = if ((colour & CColour.ALPHA_MASK) == 0) CColour.ALPHA_MASK else colour

  protected def getColourString(colour: Int): String = {
    "(" + (colour >> 16 & 0xff) + "," + (colour >> 8 & 0xff) + "," + (colour >> 0 & 0xff) + ")"
  }

  /**
   * Method to get the foreground colour.
   *
   * @return the foreground colour.
   */
  def getForeground: Int = _foreground

  /**
   * Method to get the background colour.
   *
   * @return the background colour.
   */
  def getBackground: Int = _background

  /**
   * Method to get the caption label colour
   *
   * @return the caption label colour.
   */
  def getCaption: Int = _caption

  /**
   * Method to get the value label colour.
   *
   * @return the value label colour.
   */
  def getValue: Int = _value

  /**
   * Method to get the active colour.
   *
   * @return the active colour.
   */
  def getActive: Int = _active


  /**
   * Method to set the foreground colour.
   *
   * @param foreground - the new foreground colour.
   * @return the updated CColour object.
   */
  def setForeground(foreground: Int): CColour = {
    _foreground = ensureOpacity(foreground)
    this
  }

  /**
   * Method to set the background colour.
   *
   * @param background - the new background colour.
   * @return the updated CColour object.
   */
  def setBackground(background: Int): CColour = {
    _background = ensureOpacity(background)
    this
  }

  /**
   * Method to set the object's caption label colour.
   *
   * @param caption - the new caption label colour.
   * @return the updated CColor
   */
  def setCaption(caption: Int): CColour = {
    _caption = ensureOpacity(caption)
    this
  }

  /**
   * Method to set the value colour.
   *
   * @param value - the colour to set.
   * @return the updated CColour.
   */
  def setValue(value: Int): CColour = {
    _value = ensureOpacity(value)
    this
  }

  /**
   * Method to set the active colour.
   *
   * @param active - the colour to set.
   * @return the updated CColour.
   */
  def setActive(active: Int): CColour = {
    _active = ensureOpacity(active)
    this
  }

  /**
   * Method to copy this colour to a colourable object.
   *
   * @param colourable - the colourable object that we want to colour.
   */
  def copyTo(colourable: CColourable[_]): Unit = {
    colourable.setColourBackground(_background)
    colourable.setColourForeground(_foreground)
    colourable.setColourActive(_active)
    colourable.setColourCaption(_caption)
    colourable.setColourValue(_value)
  }

  /**
   * Method to copy this colour to a colour object.
   *
   * @param colour - the colour object that we want to colour.
   */
  def copyTo(colour: CColour): Unit = {
    colour.setBackground(_background)
    colour.setForeground(_foreground)
    colour.setActive(_active)
    colour.setCaption(_caption)
    colour.setValue(_value)
  }


  /**
   * Method to get the String Representation of the Colour Scheme.
   *
   * @return the CColour object converted to a string.
   */
  override def toString: String = {
    val build = StringBuilder.newBuilder
    build.append("fg " + getColourString(_foreground))
    build.append(", bg " + getColourString(_background))
    build.append(", act " + getColourString(_active))
    build.append(", cap " + getColourString(_caption))
    build.append(", val " + getColourString(_value))
    build.toString()
  }
}