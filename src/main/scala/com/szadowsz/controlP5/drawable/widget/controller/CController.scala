package com.szadowsz.controlP5.drawable.widget.controller

import com.szadowsz.controlP5.drawable.layer.CLayer
import com.szadowsz.controlP5.drawable.widget.CBase
import com.szadowsz.controlP5.drawable.widget.group.CGroup
import com.szadowsz.controlP5.drawable.widget.view.CView
import com.szadowsz.processing.SVector
import processing.core.PGraphics

/**
 * @author Zakski : 04/10/2015.
 */
abstract class CController[Self <: CController[Self]](name: String, layer: CLayer, parent: CGroup[_, _], v: SVector, width: Int, height: Int)
  extends CBase[Self](name, layer, parent, v, width, height) {
  self: Self =>

  /**
   * How the Controller should be displayed.
   */
  protected var _view: CView[Self]


  def setView(view : CView[Self]): Unit = {
    view.init(this.asInstanceOf[Self])
    _view = view
  }

  /**
   * Method to draw the object in the given PApplet.
   *
   * @param graphics Processing context.
   */
  override def draw(graphics: PGraphics): Unit = {
    if (isVisible) {
      graphics.pushMatrix()
      graphics.translate(_position.x, _position.y)
      _view.display(graphics, this.asInstanceOf[Self])
      graphics.popMatrix()
    }
  }
}
